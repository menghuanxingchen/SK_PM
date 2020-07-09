package com.pm.business.movinginfo.controller;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.JiGuangPush.JPush;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pm.business.basicinfo.service.CheckItemService;
import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.movinginfo.service.MovingInService;
import com.pm.business.proposal.service.ProposalInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.proposal.entity.ProposalApproveInfo;
import com.pm.persistence.proposal.entity.ProposalInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.MailUtil.MailUtil;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.repast.core.util.DateUtil;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;




@Controller
@RequestMapping("/MovingInController")
public class MovingInController {

	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@Resource
	private ProposalInfoService proposalInfoService;
	
	@Resource
	private MovingInService movingInService;
	
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/movinginfo/moving_in";
	}	
	
	/**
	 * 保存申请信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/uploadPicEntityData.json")
	
	public @ResponseBody JSONMap<String,Object> uploadPicEntityData(@RequestParam(value="picInfo",required=false) MultipartFile picInfo) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{

	        File targetFile=null;
	        String url="";//返回存储路径
	        String st = this.getClass().getResource("/").getPath() ;
	        String fileName=picInfo.getOriginalFilename();//获取文件名加后缀
	        if(fileName!=null&&fileName!=""){   
	            String returnUrl = "F:\\Program Files (x86)\\Apache Software Foundation\\apache-tomcat-8.0.48\\apache-tomcat-8.0.48\\webapps\\PMpicture\\";//存储路径
	           
	            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
	 
	            //先判断文件是否存在
	         
	            //获取文件夹路径
	            File file1 =new File(returnUrl); 
	           
	            //如果文件夹不存在则创建    
	            if(!file1 .exists()){       
	            	file1.mkdirs(); 
	            }
	            file1.createNewFile();  
	            
	            //将图片存入文件夹
	            targetFile = new File(file1, fileName);
	            try {
	            	//将上传的文件写到服务器上指定的文件。
	            	picInfo.transferTo(targetFile);
	                url="http://114.113.147.106:8888/PMpicture/"+fileName;
	            } catch (Exception e) {
	                e.printStackTrace();
	               
	            }
	        }
	        model.put("filePath", url);
	        model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
	    return model;
	}
	
	/**
	 * 保存申请信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/saveTemporaryEntityData.json")
	public @ResponseBody JSONMap<String,Object> saveTemporaryEntityData(@FormModel MovingInInfo movingInInfo,
			@FormModel ("approveInfoList") List<ProposalApproveInfo> approveInfoList,
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if(("").equals(movingInInfo.getMinCarno()) || null == movingInInfo.getMinCarno()){
				movingInInfo.setMinCarno(movingInInfo.getMovingCarno()); 
			}
			
			List<MovingInInfo> havelist = movingInService.queryMovingGoodsInfoList(movingInInfo);
			if(havelist.size()>0){
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "请勿重复申请！");
				
				return model;
			}
			//搬入证插入前删除
			movingInService.deleteMovingInInfo(movingInInfo);
			//插入搬入证表
			movingInInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			movingInInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			movingInInfo.setCreate_id(processor.getCurrentUserId());
			movingInInfo.setUpdate_id(processor.getCurrentUserId());
			movingInInfo.setApproval_state(PmConstant.MOVINGAPPROVE_WAIT);
			movingInInfo.setApproval_step(PmConstant.MOVINGDRAFT);
			movingInInfo.setUser_yn("Y");
			movingInService.saveMovingInApplyInfo(movingInInfo,processor);

			 //插入搬入证物品信息表
		    Gson gson = new Gson();
		    MovingGoodsInfo m = new MovingGoodsInfo();
		    m.setMinNo(movingInInfo.getMinNo());
		    movingInService.deleteMovingGoodsInfo(m);
		    
		    //把json序列化为List对象
		    List<MovingGoodsInfo> evaList = gson.fromJson(movingInInfo.getJson_info(), new TypeToken<List<MovingGoodsInfo>>() {}.getType());
		    for (int i = 0; i < evaList.size(); i++) {
		    	  MovingGoodsInfo s = (MovingGoodsInfo)evaList.get(i);
		    	  s.setMinNo(movingInInfo.getMinNo());
		    	  movingInService.saveMovingGoodsInfoData(s,processor);
		    }
		      //申请人
		    ProposalApproveInfo user1 = new ProposalApproveInfo();
		    user1.setApprove_user_id(movingInInfo.getSkContactsNo());
		    approveInfoList.add(user1);
		    
		    /*SysUserInfo movingInInfoUser = sysUserInfoService.getSysUserInfoById(movingInInfo.getSkContactsNo());
		    SysUserInfo sysUserInfo = new SysUserInfo();
		    //申请人所在部门科长
			sysUserInfo.setDept_code(movingInInfoUser.getDept_code());
			sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
			sysUserInfo.setUser_yn(1);
			List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
		      
			if(sysUserInfoList.size()>0){
				ProposalApproveInfo user2 = new ProposalApproveInfo();
				user2.setApprove_user_id(sysUserInfoList.get(0).getUser_num());
			    approveInfoList.add(user2);
			}
			
		    
		    //厂长
		    ProposalApproveInfo user3 = new ProposalApproveInfo();
		    user3.setApprove_user_id("lg00064");
		    approveInfoList.add(user3);*/
			
		    
		    //门卫
		    ProposalApproveInfo user4 = new ProposalApproveInfo();
		    user4.setApprove_user_id("lg00999");
		    approveInfoList.add(user4);
		    
		    //插入审批流程之前删除，为了驳回再申请
		    ProposalApproveInfo delPro = new ProposalApproveInfo();
		    delPro.setProposal_num(movingInInfo.getMinNo());
		    proposalInfoService.deleteProposalApproveInfo(delPro);
		    
		  	int count=1;
		  	//更新流程表
			for(ProposalApproveInfo approveInfo:approveInfoList){
				approveInfo.setProposal_num(movingInInfo.getMinNo());
				
				if(count == 1){
					approveInfo.setApprove_step(count+"");
					approveInfo.setApprove_yn(PmConstant.STATE_N);
					//approveInfo.setApproval_state(PmConstant.APPROVE_STATE);
				}else if(count == 2){
					//sk联系人--》到门卫
					approveInfo.setApprove_step("4");
					//设置处理人
					movingInInfo.setDeal_user_id(approveInfo.getApprove_user_id());
					approveInfo.setApprove_yn(PmConstant.STATE_N);

				}else{
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}
				approveInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setCreate_id(processor.getCurrentUserId());
				proposalInfoService.saveApproveInfo(approveInfo);
				count++;
			}
			
			//更新搬入证处理人
			MovingInInfo info = new MovingInInfo();
			info.setMinNo(movingInInfo.getMinNo());
			List<MovingInInfo> list = movingInService.queryMovingGoodsInfoList(info);
			if(list.size()>0){
				info = list.get(0);
				info.setDeal_user_id(movingInInfo.getSkContactsNo());
				movingInService.updateMovingInInfo(info);
			}
			
			
			 
		    //给sk联系人发送邮件
		    SysUserInfo mailsysUserInfo = new SysUserInfo();
		    mailsysUserInfo.setUser_num(movingInInfo.getSkContactsNo());
		    
		    MailUtil mu = new MailUtil();
		    //推送
		    FillingPlanInfo planInfo = new FillingPlanInfo();
			SqmPushInfo sqmPushInfo = new SqmPushInfo();
			
			List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
			if(mailsysUserInfoList.size()>0){
				//发邮件
				mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
				mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
				mu.setContent("&nbsp; &nbsp; &nbsp; &nbsp;您好: 您有一个搬入证（编号："+movingInInfo.getMinNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
				//推送
				sqmPushInfo.setPush_msg("您有一个搬入证（编号："+movingInInfo.getMinNo()+"）待审批！");
				sqmPushInfo.setPush_type("MOVINGIN");
				sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
				
				if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
					JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingInInfo.getMinNo(),planInfo);
				}
			}
			
			MailUtil.sendMail(mu);
			
			
			 model.put(SysConstant.OP_FLAG, true);
			 model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
