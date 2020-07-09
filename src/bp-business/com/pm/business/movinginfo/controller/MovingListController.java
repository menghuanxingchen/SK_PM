package com.pm.business.movinginfo.controller;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JiGuangPush.JPush;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pm.business.basicinfo.service.CheckItemService;
import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.movinginfo.service.MovingInService;
import com.pm.business.movinginfo.service.MovingListService;
import com.pm.business.movinginfo.service.MovingOutService;
import com.pm.business.proposal.service.ProposalInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.movinginfo.dto.MovingInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.movinginfo.entity.MovingOutInfo;
import com.pm.persistence.proposal.entity.ProposalApproveInfo;
import com.pm.persistence.proposal.entity.ProposalInfo;
import com.pm.persistence.proposal.entity.ProposalOperateInfo;
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
@RequestMapping("/MovingListController")
public class MovingListController {

	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@Resource
	private MovingListService movingListService;
	
	@Resource
	private MovingInService movingInService;
	
	@Resource
	private MovingOutService movingOutService;
	
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@Resource
	private ProposalInfoService proposalInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/movinginfo/moving_list";
	}	
	
	@RequestMapping("/movingInDetailJsp")
	public String mInpage(){
		return "web/movinginfo/moving_in_detail";
	}
	
	@RequestMapping("/movingOutDetailJsp")
	public String mOutpage(){
		return "web/movinginfo/moving_out_detail";
	}
	
	/**
	 * 查询搬入搬出列表
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel MovingInfo movingInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if("6".equals(movingInfo.getApplyType())){
				movingInfo.setApproval_state("6");
			}
			if("1".equals(movingInfo.getApplyType())){
				movingInfo.setDeal_user_id(processor.getCurrentUserId());;
			}
			Pagination<MovingInfo> movingInfoList = movingListService.queryDataList(movingInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<MovingInfo> convert = new UIDataConvert<MovingInfo>();
			
			convert.format(movingInfoList);
			model.put("data", movingInfoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	
	/**
	 * 查询搬入详细
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMovingInDataList.json")
	public @ResponseBody JSONMap<String,Object> queryMovingInDataList(@FormModel MovingInfo movingInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			MovingInInfo movingInInfo = movingListService.queryMovingInDataList(movingInfo);
			UIDataConvert<MovingInInfo> convert = new UIDataConvert<MovingInInfo>();
			 
			convert.format(movingInInfo);
			model.put("data", movingInInfo);
			
			List<MovingGoodsInfo> movingGoodsList = movingListService.queryMovingGoodsInfoList(movingInfo);
			
			model.put("movingGoodsList", movingGoodsList);
			
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 查询搬出详细
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMovingOutDataList.json")
	public @ResponseBody JSONMap<String,Object> queryMovingOutDataList(@FormModel MovingInfo movingInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			MovingOutInfo movingOutInfo = movingListService.queryMovingOutDataList(movingInfo);
			UIDataConvert<MovingOutInfo> convert = new UIDataConvert<MovingOutInfo>();
			 
			convert.format(movingOutInfo);
			model.put("data", movingOutInfo);
			
			List<MovingGoodsInfo> movingGoodsList = movingListService.queryMovingGoodsInfoList(movingInfo);
			
			model.put("movingGoodsList", movingGoodsList);
			
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 维修单列表页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList.json")
	public @ResponseBody JSONMap<String, Object> queryDownList(
			@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			
			List<SysUserInfo> allUser = sysUserInfoService.querySysUserInfoListFormoving();
			
			model.put("allUser", allUser);
			
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 删除信息
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteMovingInfoData.json")
	public @ResponseBody JSONMap<String, Object> deleteMovingInfoData(
			@FormModel MovingInfo movingInfo,@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		
			int cnt = movingListService.deleteMovingInfoData(movingInfo);
			if (cnt > 0) {
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			} else {
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		
		return model;
	}

	//以下是下拉列表管理
		/**
		 * 提案书新增页面下拉列表
		 * 
		 * @param sysCodeInfo
		 * @param sysUserInfo
		 * @param processor
		 * @return
		 */
		@RequestMapping("/queryDownListForProposal.json")
		public @ResponseBody JSONMap<String, Object> queryDownListForProposal(
				@FormModel MovingInfo movingInfo,@FormModel SysUserInfo sysUserInfo,
				@FormModel Processor processor) {
			JSONMap<String, Object> model = new JSONMap<String, Object>();
			try {
				
				//申请人
				MovingInInfo movingInInfo = movingListService.queryMovingInDataList(movingInfo);
				SysUserInfo movingInInfoUser = sysUserInfoService.getSysUserInfoById(movingInInfo.getSkContactsNo());
				
				//申请人所在部门科长
				sysUserInfo.setDept_code(movingInInfoUser.getDept_code());
				sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
				List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
				
				
				model.put("movingInInfo", movingInInfo);
				model.put("sysUserInfoList", sysUserInfoList);
				
				ProposalApproveInfo proposalApproveInfo = new ProposalApproveInfo();
				proposalApproveInfo.setProposal_num(movingInfo.getMovingNo());
				List<ProposalApproveInfo> approveList =proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
				if(approveList.size()>0){
					for(ProposalApproveInfo item: approveList){
						if(PmConstant.APPROVE_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("已审批");
						}else if(PmConstant.REJECT_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("驳回");
						}else if(PmConstant.CONFIRM_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("已确认");
						}else{
							if("4".equals(item.getApprove_step())){
								item.setApproval_nm("待确认");
							}else{
								item.setApproval_nm("待审批");
							}
							
						}
						
						if(PmConstant.MOVINGDRAFT.equals(item.getApprove_step())){
							model.put("appuser1", item);
						}else if(PmConstant.MOVINGAPPROVE.equals(item.getApprove_step())){
							model.put("appuser2", item);
						}else if(PmConstant.MOVINGFACTORY_MANAGER_APPROVE.equals(item.getApprove_step())){
							model.put("appuser3", item);
						}else if(PmConstant.MOVINGCONFIRM.equals(item.getApprove_step())){
							model.put("appuser4", item);
						}
						
					}
				}
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
			}
			return model;
		}
	
		
		//以下是下拉列表管理
		/**
		 * 提案书新增页面下拉列表
		 * 
		 * @param sysCodeInfo
		 * @param sysUserInfo
		 * @param processor
		 * @return
		 */
		@RequestMapping("/queryMoutDownListForProposal.json")
		public @ResponseBody JSONMap<String, Object> queryMoutDownListForProposal(
				@FormModel MovingInfo movingInfo,@FormModel SysUserInfo sysUserInfo,
				@FormModel Processor processor) {
			JSONMap<String, Object> model = new JSONMap<String, Object>();
			try {
				
				//申请人
				MovingOutInfo movingOutInfo = movingListService.queryMovingOutDataList(movingInfo);
				SysUserInfo movingInInfoUser = sysUserInfoService.getSysUserInfoById(movingOutInfo.getSkApplyNo());
				
				//申请人所在部门科长
				//sysUserInfo.setDept_code(movingInInfoUser.getDept_code());
				//sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
				//List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
				
				
				model.put("movingOutInfo", movingOutInfo);
				
				
				ProposalApproveInfo proposalApproveInfo = new ProposalApproveInfo();
				proposalApproveInfo.setProposal_num(movingInfo.getMovingNo());
				List<ProposalApproveInfo> approveList =proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
				if(approveList.size()>0){
					for(ProposalApproveInfo item: approveList){
						if(PmConstant.APPROVE_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("已审批");
						}else if(PmConstant.REJECT_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("驳回");
						}else if(PmConstant.CONFIRM_STATE.equals(item.getApproval_state())){
							item.setApproval_nm("已确认");
						}else{
							if("4".equals(item.getApprove_step())){
								item.setApproval_nm("待确认");
							}else{
								item.setApproval_nm("待审批");
							}
							
						}
						
						if(PmConstant.MOVINGDRAFT.equals(item.getApprove_step())){
							model.put("appuser1", item);
						}else if(PmConstant.MOVINGAPPROVE.equals(item.getApprove_step())){
							model.put("appuser2", item);
						}else if(PmConstant.MOVINGFACTORY_MANAGER_APPROVE.equals(item.getApprove_step())){
							model.put("appuser3", item);
						}else if(PmConstant.MOVINGCONFIRM.equals(item.getApprove_step())){
							model.put("appuser4", item);
						}
						
					}
				}
				
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
			}
			return model;
		}
				
		/**
		 * 审批/驳回 /确认 操作
		 * 
		 * @param repairOrderInfo
		 * @param approveInfo
		 * @param updateflag
		 * @param processor
		 * @return
		 */
		@RequestMapping("/updateProposalInfoApproveAndReject.json")
		public @ResponseBody JSONMap<String, Object> updateProposalInfoApproveAndReject(
				@FormModel MovingInfo movingInfo,@FormModel ProposalApproveInfo proposalApproveInfo,
				@FormModel ProposalOperateInfo proposalOperateInfo,@FormModel ("updateflag")String updateflag,@FormModel Processor processor) {
			JSONMap<String, Object> model = new JSONMap<String, Object>();
			try {
				
				MovingInInfo entity = movingListService.queryMovingInDataList(movingInfo);
				proposalApproveInfo.setProposal_num(entity.getMinNo());
				List<ProposalApproveInfo> approList = proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
				ProposalApproveInfo tempEntity = new ProposalApproveInfo();
				proposalApproveInfo.setApprove_step(entity.getApproval_step());
				String tempApproUser = "";
				String draftUser = "";
				/*
				//发邮件查询用户
				ProposalApproveInfo firstProposalApproveInfo = new ProposalApproveInfo();
				firstProposalApproveInfo.setProposal_num(entity.getMinNo());
				firstProposalApproveInfo.setApprove_step("1");
				List<ProposalApproveInfo> mailapproList = proposalInfoService.queryProposalApproveInfoList(firstProposalApproveInfo);
				*/
				
				if(approList.size()>0){
					int tempstep = -1;
					for(int i = 0;i<approList.size();i++){
						if(proposalApproveInfo.getApprove_step().equals(approList.get(i).getApprove_step())){
							//sk联系人审批完 直接门卫确认
							tempstep =  Integer.parseInt(approList.get(i).getApprove_step())+3;
							tempApproUser = approList.get(i).getApprove_user_id();
						}
						if(PmConstant.MOVINGAPPROVE_WAIT.equals(approList.get(i).getApprove_step())){
							draftUser = approList.get(i).getApprove_user_id();
						}
					}
					for(int i = 0;i<approList.size();i++){
						if((tempstep+"").equals(approList.get(i).getApprove_step())){
							tempEntity.setApprove_user_id(approList.get(i).getApprove_user_id());
						}
					}
				}
				MailUtil mu = new MailUtil();
				SysUserInfo sysUserInfo = new SysUserInfo();
				boolean sendMailFlag = true;
				entity.setDeal_user_id(tempEntity.getApprove_user_id());
				if(PmConstant.REJECT_STATE.equals(updateflag)){
					entity.setApproval_state(PmConstant.MOVINGREJECT);
					entity.setDeal_user_id("");
					entity.setReject_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
					
					//审批状态是驳回
					tempEntity.setApproval_state(PmConstant.REJECT_STATE);
					tempEntity.setApprove_user_id(tempApproUser);
					sysUserInfo.setUser_num(draftUser);
					
					/*
					//发送驳回邮件
					if(mailapproList.size()>0){
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(mailapproList.get(0).getApprove_user_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您的搬入证申请（编号："+movingInfo.getMovingNo()+"）被驳回，请确认！ 链接地址    http://114.113.147.106:8888/PM/ ");
						}
						MailUtil.sendMail(mu);
					}*/
		

				}else if(PmConstant.APPROVE_STATE.equals(updateflag)){
					/*//厂长审批
					if(PmConstant.MOVINGCZAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGCONFIRM_WAIT);
						entity.setApproval_step(PmConstant.MOVINGCONFIRM);
						entity.setFactory_manager_approval_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
						//发送驳回邮件
						if(mailapproList.size()>0){
							SysUserInfo mailsysUserInfo = new SysUserInfo();
						    mailsysUserInfo.setUser_num(mailapproList.get(0).getApprove_user_id());
					
							List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
							if(mailsysUserInfoList.size()>0){
								mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
								mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
								mu.setContent("&nbsp; &nbsp; 您好: 您的搬入证申请（编号："+movingInfo.getMovingNo()+"）厂长已审批完成，请确认！ 链接地址    http://114.113.147.106:8888/PM/ ");
							}
							MailUtil.sendMail(mu);
						}
					}
					//科长审批
					if(PmConstant.MOVINGKZAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGCZAPPROVE_WAIT);
						entity.setApproval_step(PmConstant.MOVINGFACTORY_MANAGER_APPROVE);
						entity.setApproval_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
						//给科长发送邮件
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您有一个搬入证（编号："+movingInfo.getMovingNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
						}
						MailUtil.sendMail(mu);
					}*/
					//sk联系人审批
					if(PmConstant.MOVINGAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGCONFIRM_WAIT);
						entity.setApproval_step(PmConstant.MOVINGCONFIRM);
						entity.setApply_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						/*
						//给科长发送邮件
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您有一个搬入证（编号："+movingInfo.getMovingNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
						}
						MailUtil.sendMail(mu);*/
					}
					//审批状态  是审批
					tempEntity.setApproval_state(PmConstant.APPROVE_STATE);
				}
			/*	else if(PmConstant.MOVINGFACTORY_MANAGER_APPROVE.equals(entity.getApproval_state())){
					entity.setApproval_state(PmConstant.MOVINGDONE);
					entity.setDeal_user_id("");
					entity.setFactory_manager_approval_date(DateUtil.getNow(DateUtil.Y_M_D));
					sendMailFlag = false;
					
					String director_opinion ="";
					if(proposalOperateInfo != null){
						director_opinion = proposalOperateInfo.getDirector_opinion();
						proposalOperateInfo.setDirector_opinion("");
					}
					if(entity != null){
						proposalOperateInfo.setProposal_num(entity.getMinNo());
						List<ProposalOperateInfo> proposalOperateList = proposalInfoService.queryProposalOperateInfoList(proposalOperateInfo);
						if(proposalOperateList.size()>0){
							proposalOperateInfo = proposalOperateList.get(0);
							proposalOperateInfo.setDirector_opinion(director_opinion);
							proposalInfoService.updateProposalOperateInfo(proposalOperateInfo);
						}
					}
					
					
					//发送审批邮件
					String htmlSubject = "搬入证已确认";
					String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>"+draftUser+"</strong></span></p>"+
							 "<p>&nbsp; &nbsp; &nbsp; &nbsp;您的搬入证编号为："+entity.getMinNo()+"已确认完成，请查看！</p>";
		 

				}*/
				else if(PmConstant.CONFIRM_STATE.equals(updateflag)){
					if(PmConstant.MOVINGCONFIRM_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGDONE);
						entity.setApproval_step(PmConstant.MOVINSTEPGDONE);
						entity.setConfirm_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
					}
					//审批状态  是确认
					tempEntity.setApproval_state(PmConstant.CONFIRM_STATE);
				}
				sysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				entity.setUpdate_id(processor.getCurrentUserId());
				entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				int cnt = movingInService.updateMovingInInfo(entity);
				if(cnt>0){
					List<ProposalApproveInfo> list = proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
					if(list.size()>0){
						ProposalApproveInfo info = list.get(0);
						info.setApprove_yn(PmConstant.STATE_Y);
						info.setApproval_state(tempEntity.getApproval_state());
						info.setUpdate_id(processor.getCurrentUserId());
						info.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						int count = proposalInfoService.updateProposalApproveInfo(info);
						if(count>0){

							model.put(SysConstant.OP_FLAG, true);
							model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
						}else{
							model.put(SysConstant.OP_FLAG, false);
							model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
			}
			
			return model;
		}
		
		/**
		 * 审批/驳回 /确认 操作
		 * 
		 * @param repairOrderInfo
		 * @param approveInfo
		 * @param updateflag
		 * @param processor
		 * @return
		 */
		@RequestMapping("/updateOutProposalInfoApproveAndReject.json")
		public @ResponseBody JSONMap<String, Object> updateOutProposalInfoApproveAndReject(
				@FormModel MovingInfo movingInfo,@FormModel ProposalApproveInfo proposalApproveInfo,
				@FormModel ProposalOperateInfo proposalOperateInfo,@FormModel ("updateflag")String updateflag,@FormModel Processor processor) {
			JSONMap<String, Object> model = new JSONMap<String, Object>();
			try {
				
				MovingOutInfo entity = movingListService.queryMovingOutDataList(movingInfo);
				proposalApproveInfo.setProposal_num(entity.getMoutNo());
				List<ProposalApproveInfo> approList = proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
				ProposalApproveInfo tempEntity = new ProposalApproveInfo();
				proposalApproveInfo.setApprove_step(entity.getApproval_step());
				String tempApproUser = "";
				String draftUser = "";
				
				//查询用户，搬出证由2开始
				ProposalApproveInfo firstProposalApproveInfo = new ProposalApproveInfo();
				firstProposalApproveInfo.setProposal_num(entity.getMoutNo());
				firstProposalApproveInfo.setApprove_step("2");
				List<ProposalApproveInfo> mailapproList = proposalInfoService.queryProposalApproveInfoList(firstProposalApproveInfo);
				
				
				if(approList.size()>0){
					int tempstep = -1;
					for(int i = 0;i<approList.size();i++){
						if(proposalApproveInfo.getApprove_step().equals(approList.get(i).getApprove_step())){
							tempstep =  Integer.parseInt(approList.get(i).getApprove_step())+1;
							tempApproUser = approList.get(i).getApprove_user_id();
						}
						if(PmConstant.MOVINGAPPROVE_WAIT.equals(approList.get(i).getApprove_step())){
							draftUser = approList.get(i).getApprove_user_id();
						}
					}
					for(int i = 0;i<approList.size();i++){
						if((tempstep+"").equals(approList.get(i).getApprove_step())){
							tempEntity.setApprove_user_id(approList.get(i).getApprove_user_id());
						}
					}
				}
				MailUtil mu = new MailUtil();
				
				//推送
			    FillingPlanInfo planInfo = new FillingPlanInfo();
				SqmPushInfo sqmPushInfo = new SqmPushInfo();
				
				
				SysUserInfo sysUserInfo = new SysUserInfo();
				boolean sendMailFlag = true;
				entity.setDeal_user_id(tempEntity.getApprove_user_id());
				if(PmConstant.REJECT_STATE.equals(updateflag)){
					entity.setApproval_state(PmConstant.MOVINGREJECT);
					entity.setDeal_user_id("");
					entity.setReject_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
					tempEntity.setApproval_state(PmConstant.REJECT_STATE);
					tempEntity.setApprove_user_id(tempApproUser);
					sysUserInfo.setUser_num(draftUser);
					
					//发送驳回邮件
					if(mailapproList.size()>0){
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(mailapproList.get(0).getCreate_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您的搬出证（编号："+movingInfo.getMovingNo()+"）被驳回，请确认！ 链接地址    http://114.113.147.106:8888/PM/ ");
						
							//推送
							sqmPushInfo.setPush_msg("您好: 您的搬出证（编号："+movingInfo.getMovingNo()+"）被驳回，请确认！");
							sqmPushInfo.setPush_type("MOVINGBO");
							sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
							if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
								JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingInfo.getMovingNo(),planInfo);
							}
						}
						MailUtil.sendMail(mu);
					}
				}else if(PmConstant.APPROVE_STATE.equals(updateflag)){
					//厂长审批
					if(PmConstant.MOVINGCZAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGCONFIRM_WAIT);
						entity.setApproval_step(PmConstant.MOVINGCONFIRM);
						entity.setFactory_manager_approval_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
						//发送驳回邮件
						if(mailapproList.size()>0){
							SysUserInfo mailsysUserInfo = new SysUserInfo();
						    mailsysUserInfo.setUser_num(mailapproList.get(0).getCreate_id());
					
							List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
							if(mailsysUserInfoList.size()>0){
								mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
								mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
								mu.setContent("&nbsp; &nbsp; 您好: 您的搬出证申请（编号："+movingInfo.getMovingNo()+"）已审批完成，请确认！ 链接地址    http://114.113.147.106:8888/PM/ ");
							
								//推送
								sqmPushInfo.setPush_msg("您好: 您的搬出证申请（编号："+movingInfo.getMovingNo()+"）已审批完成，请确认！");
								sqmPushInfo.setPush_type("MOVINGBO");
								sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
								if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
									JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingInfo.getMovingNo(),planInfo);
								}

							}
							MailUtil.sendMail(mu);
						}
					}
					//科长审批
					if(PmConstant.MOVINGKZAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGCZAPPROVE_WAIT);
						entity.setApproval_step(PmConstant.MOVINGFACTORY_MANAGER_APPROVE);
						entity.setApproval_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
						//给科长发送邮件
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您有搬出证（编号："+movingInfo.getMovingNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
						
							//推送
							sqmPushInfo.setPush_msg("您好: 您有搬出证（编号："+movingInfo.getMovingNo()+"）待审批！");
							sqmPushInfo.setPush_type("MOVINGBO");
							sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
							if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
								JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingInfo.getMovingNo(),planInfo);								
							}
						}
						MailUtil.sendMail(mu);
					}
					//sk联系人审批
					if(PmConstant.MOVINGAPPROVE_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGKZAPPROVE_WAIT);
						entity.setApproval_step(PmConstant.MOVINGAPPROVE);
						entity.setApply_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						
						//给科长发送邮件
						SysUserInfo mailsysUserInfo = new SysUserInfo();
					    mailsysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				
						List<SysUserInfo> mailsysUserInfoList = sysUserInfoService.querySysUserInfoList(mailsysUserInfo);
						if(mailsysUserInfoList.size()>0){
							mu.setToEmails(mailsysUserInfoList.get(0).getEmail());
							mu.setToName(mailsysUserInfoList.get(0).getUser_nm());
							mu.setContent("&nbsp; &nbsp; 您好: 您有搬出证（编号："+movingInfo.getMovingNo()+"）待审批！ 链接地址    http://114.113.147.106:8888/PM/ ");
						
							//推送
							sqmPushInfo.setPush_msg("您好: 您有搬出证（编号："+movingInfo.getMovingNo()+"）待审批！");
							sqmPushInfo.setPush_type("MOVINGBO");
							sqmPushInfo.setPush_to(mailsysUserInfoList.get(0).getUser_num());
							if(!"".equals(mailsysUserInfoList.get(0).getPmregistrationID()) && null != mailsysUserInfoList.get(0).getPmregistrationID()){
								JPush.movingjpushPeople(mailsysUserInfoList.get(0).getPmregistrationID(),sqmPushInfo,"android",movingInfo.getMovingNo(),planInfo);							
							}


						}
						MailUtil.sendMail(mu);
					}
					
					tempEntity.setApproval_state(PmConstant.APPROVE_STATE);
					

				}
			/*	else if(PmConstant.MOVINGFACTORY_MANAGER_APPROVE.equals(entity.getApproval_state())){
					entity.setApproval_state(PmConstant.MOVINGDONE);
					entity.setDeal_user_id("");
					entity.setFactory_manager_approval_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
					sendMailFlag = false;
					
					String director_opinion ="";
					if(proposalOperateInfo != null){
						director_opinion = proposalOperateInfo.getDirector_opinion();
						proposalOperateInfo.setDirector_opinion("");
					}
					if(entity != null){
						proposalOperateInfo.setProposal_num(entity.getMoutNo());
						List<ProposalOperateInfo> proposalOperateList = proposalInfoService.queryProposalOperateInfoList(proposalOperateInfo);
						if(proposalOperateList.size()>0){
							proposalOperateInfo = proposalOperateList.get(0);
							proposalOperateInfo.setDirector_opinion(director_opinion);
							proposalInfoService.updateProposalOperateInfo(proposalOperateInfo);
						}
					}
					
					
					//发送审批邮件
					String htmlSubject = "搬入证已确认";
					String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>"+draftUser+"</strong></span></p>"+
							 "<p>&nbsp; &nbsp; &nbsp; &nbsp;您的搬入证编号为："+entity.getMoutNo()+"已确认完成，请查看！</p>";
		 

				}*/
				else if(PmConstant.CONFIRM_STATE.equals(updateflag)){
					if(PmConstant.MOVINGCONFIRM_WAIT.equals(entity.getApproval_state())){
						entity.setApproval_state(PmConstant.MOVINGDONE);
						entity.setApproval_step(PmConstant.MOVINGCONFIRM);
						entity.setConfirm_date(DateUtil.getNow(DateUtil.Y_M_D_HM));
						entity.setPicQueRenInfo(movingInfo.getPicQueRenInfo());//确认图片
						/*String improvement_effect ="";
						if(proposalOperateInfo != null){
							improvement_effect = proposalOperateInfo.getImprovement_effect();
							proposalOperateInfo.setImprovement_effect("");
						}
						if(entity != null){
							proposalOperateInfo.setProposal_num(entity.getMoutNo());
							List<ProposalOperateInfo> proposalOperateList = proposalInfoService.queryProposalOperateInfoList(proposalOperateInfo);
							if(proposalOperateList.size()>0){
								proposalOperateInfo = proposalOperateList.get(0);
								proposalOperateInfo.setImprovement_effect(improvement_effect);
								proposalInfoService.updateProposalOperateInfo(proposalOperateInfo);
							}
						}*/

					}
					tempEntity.setApproval_state(PmConstant.CONFIRM_STATE);
				}
				sysUserInfo.setUser_num(tempEntity.getApprove_user_id());
				entity.setUpdate_id(processor.getCurrentUserId());
				entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				int cnt = movingOutService.updateMovingOutInfo(entity);
				if(cnt>0){
					List<ProposalApproveInfo> list = proposalInfoService.queryProposalApproveInfoList(proposalApproveInfo);
					if(list.size()>0){
						ProposalApproveInfo info = list.get(0);
						info.setApprove_yn(PmConstant.STATE_Y);
						info.setApproval_state(tempEntity.getApproval_state());
						info.setUpdate_id(processor.getCurrentUserId());
						info.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						int count = proposalInfoService.updateProposalApproveInfo(info);
						if(count>0){

							model.put(SysConstant.OP_FLAG, true);
							model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
						}else{
							model.put(SysConstant.OP_FLAG, false);
							model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
			}
			
			return model;
			}
		
}
