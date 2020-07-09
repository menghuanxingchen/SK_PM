package com.pm.business.movinginfo.controller;



import java.io.File;
import java.util.Date;
import java.util.List;
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
import com.pm.business.movinginfo.service.MovingOutService;
import com.pm.business.proposal.service.ProposalInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.movinginfo.entity.MovingOutInfo;
import com.pm.persistence.proposal.entity.ProposalApproveInfo;
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
@RequestMapping("/MovingOutController")
public class MovingOutController {

	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@Resource
	private MovingOutService movingOutService;
	
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@Resource
	private ProposalInfoService proposalInfoService;
	
	
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/movinginfo/moving_out";
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
	 * 保存确认信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/uploadQueRenPicEntityData.json")
	public @ResponseBody JSONMap<String,Object> uploadQueRenPicEntityData(@RequestParam(value="picQueRenInfo",required=false) MultipartFile picInfo) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{

	        File targetFile=null;
	        String url="";//返回存储路径

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
	public @ResponseBody JSONMap<String,Object> saveTemporaryEntityData(@FormModel MovingOutInfo movingOutInfo,
			@FormModel ("approveInfoList") List<ProposalApproveInfo> approveInfoList,
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if(("").equals(movingOutInfo.getMoutCarno()) || null == movingOutInfo.getMoutCarno()){
				movingOutInfo.setMoutCarno(movingOutInfo.getMovingCarno()); 
			}
			
			List<MovingOutInfo> havelist = movingOutService.queryMovingGoodsInfoList(movingOutInfo);
			if(havelist.size()>0){
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "请勿重复申请！");
				
				return model;
			}
			
			movingOutInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			movingOutInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			movingOutInfo.setCreate_id(processor.getCurrentUserId());
			movingOutInfo.setUpdate_id(processor.getCurrentUserId());
			if(!movingOutInfo.getMoutGoalType().equals("仓库提货")&& !movingOutInfo.getMoutGoalType().equals("空车出厂")
					|| !"".equals(movingOutInfo.getApplyPerson1())){
				movingOutInfo.setApproval_state(PmConstant.MOVINGKZAPPROVE_WAIT);
				movingOutInfo.setApproval_step(PmConstant.MOVINGAPPROVE);
			}else{
				movingOutInfo.setApproval_state(PmConstant.MOVINGCONFIRM_WAIT);
				movingOutInfo.setApproval_step(PmConstant.MOVINGCONFIRM);
			}
			
			movingOutInfo.setUser_yn("Y");
			movingOutService.saveMovingOutApplyInfo(movingOutInfo,processor);

			 //实例化Gson
		      Gson gson = new Gson();
		      if(movingOutInfo.getJson_info().length() > 10){
		    	//把json序列化为List对象
			      List<MovingGoodsInfo> evaList = gson.fromJson(movingOutInfo.getJson_info(), new TypeToken<List<MovingGoodsInfo>>() {}.getType());
			      for (int i = 0; i < evaList.size(); i++) {
			    	  MovingGoodsInfo s = (MovingGoodsInfo)evaList.get(i);
			    	  s.setMinNo(movingOutInfo.getMoutNo());
			    	  movingOutService.saveMovingGoodsInfoData(s,processor);
			      }
		      }
		      
		      /*//申请人
			    ProposalApproveInfo user1 = new ProposalApproveInfo();
			    user1.setApprove_user_id(movingOutInfo.getSkApplyNo());
			    approveInfoList.add(user1);*/
			   
			    SysUserInfo movingInInfoUser = sysUserInfoService.getSysUserInfoById(movingOutInfo.getSkApplyNo());
			    SysUserInfo sysUserInfo = new SysUserInfo();
			    //申请人所在部门科长
				sysUserInfo.setDept_code(movingInInfoUser.getDept_code());
				sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
				sysUserInfo.setUser_yn(1);
				List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			      
				SysUserInfo mailsysUserInfo = new SysUserInfo();
				
				//类型为  不需要领导审批
				if((sysUserInfoList.size()>0 && !movingOutInfo.getMoutGoalType().equals("仓库提货")&& !movingOutInfo.getMoutGoalType().equals("空车出厂")) 
						|| !"".equals(movingOutInfo.getApplyPerson1())){
					ProposalApproveInfo user2 = new ProposalApproveInfo();
					
					//如果选择审批人的话  就用审批人审批
					if(!"".equals(movingOutInfo.getApplyPerson1())){
						user2.setApprove_user_id(movingOutInfo.getApplyPerson1());
						 mailsysUserInfo.setUser_num(movingOutInfo.getApplyPerson1()); //发邮件
					}else{
						user2.setApprove_user_id(sysUserInfoList.get(0).getUser_num());
						mailsysUserInfo.setUser_num(sysUserInfoList.get(0).getUser_num());//发邮件
					}
					
				    approveInfoList.add(user2);
				    
				  //给sk联系人科长发送邮件
				    MailUtil mu = new MailUtil();
				    //推送
				    FillingPlanInfo planInfo = new FillingPlanInfo();
					SqmPushInfo sqmPushInfo = new SqmPushInfo();
					
					List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
					if(mailsysUserInfoList.size()>0){
						mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
						mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
						mu.setContent("&nbsp; &nbsp; &nbsp; &nbsp;您好: 您有一个搬出证（编号："+movingOutInfo.getMoutNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
						
						//推送
						sqmPushInfo.setPush_msg("您有一个搬出证（编号："+movingOutInfo.getMoutNo()+"）待审批！");
						sqmPushInfo.setPush_type("MOVINGOUT");
						sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
						
						if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
							JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingOutInfo.getMoutNo(),planInfo);
						}
					}
					MailUtil.sendMail(mu);
				}
				
				int count=2;
				
				//类型为  不需要领导审批
				if((!movingOutInfo.getMoutGoalType().equals("仓库提货")&& !movingOutInfo.getMoutGoalType().equals("空车出厂")) 
						|| !"".equals(movingOutInfo.getApplyPerson1())){
					 //厂长
				    ProposalApproveInfo user3 = new ProposalApproveInfo();
				    
				    //如果选择审批人的话  就用审批人审批
				    if(!"".equals(movingOutInfo.getApplyPerson2())){
				    	user3.setApprove_user_id(movingOutInfo.getApplyPerson2());
				    }else{
				    	user3.setApprove_user_id("lg00064");
				    }
				    
				    approveInfoList.add(user3);
				}else{
					//直接门卫确认
					count = 4;
				}
			   
			    
			    //门卫
			    ProposalApproveInfo user4 = new ProposalApproveInfo();
			    user4.setApprove_user_id("lg00999");
			    approveInfoList.add(user4);
			      
			  	
			  	//更新流程表
				for(ProposalApproveInfo approveInfo:approveInfoList){
					approveInfo.setProposal_num(movingOutInfo.getMoutNo());
					approveInfo.setApprove_step(count+"");
					if(count == 2){
						approveInfo.setApprove_yn(PmConstant.STATE_N);
						//approveInfo.setApproval_state(PmConstant.APPROVE_STATE);
					}else if(count == 3){
						//设置处理人
						//movingOutInfo.setDeal_user_id(approveInfo.getApprove_user_id());
						approveInfo.setApprove_yn(PmConstant.STATE_N);

					}else{
						approveInfo.setApprove_yn(PmConstant.STATE_N);
					}
					approveInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					approveInfo.setCreate_id(processor.getCurrentUserId());
					proposalInfoService.saveApproveInfo(approveInfo);
					count++;
				}
				
				//更新搬出证处理人
				MovingOutInfo info = new MovingOutInfo();
				info.setMoutNo(movingOutInfo.getMoutNo());
				List<MovingOutInfo> list = movingOutService.queryMovingGoodsInfoList(info);
				if(list.size()>0){
					info = list.get(0);
					
					if(sysUserInfoList.size()>0){
						//类型为  不需要领导审批,直接门卫确认,其他的就是由科长
						if(!movingOutInfo.getMoutGoalType().equals("仓库提货")&& !movingOutInfo.getMoutGoalType().equals("空车出厂")
								|| !"".equals(movingOutInfo.getApplyPerson1())){
							
							//如果选择审批人的话  就用审批人审批
						    if(!"".equals(movingOutInfo.getApplyPerson1())){
						    	info.setDeal_user_id(movingOutInfo.getApplyPerson1());
						    }else{
						    	info.setDeal_user_id(sysUserInfoList.get(0).getUser_num());
						    }
							
							
						}else{
							info.setDeal_user_id("lg00999");
						}
					}
					
					movingOutService.updateMovingOutInfo(info);
				}
		      
			 model.put(SysConstant.OP_FLAG, true);
			 model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 查询
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询登录人
			
			model.put("processor", processor);

			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}

}
