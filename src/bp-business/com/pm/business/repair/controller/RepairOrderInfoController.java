package com.pm.business.repair.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.repair.service.RepairOrderInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.business.workout.service.WorkoutInfoService;
import com.pm.common.print.PrintBase;
import com.pm.common.print.RepairAccountPrint;
import com.pm.common.print.RepairPrint;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.repair.dto.OfferInfoDTO;
import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.repair.entity.ApproveInfo;
import com.pm.persistence.repair.entity.DistributionInfo;
import com.pm.persistence.repair.entity.OfferDetail;
import com.pm.persistence.repair.entity.OfferInfo;
import com.pm.persistence.repair.entity.RepairCostDetail;
import com.pm.persistence.repair.entity.RepairCostDetailTemp;
import com.pm.persistence.repair.entity.RepairMachineDetail;
import com.pm.persistence.repair.entity.RepairOrderInfo;
import com.pm.persistence.repair.entity.RepairResultInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.pm.persistence.workout.entity.RepairMaterialCostDetail;
import com.pm.persistence.workout.entity.WorkOutInfo;
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

@Controller
@RequestMapping("/RepairOrderInfoController")
public class RepairOrderInfoController {

	@Resource
	private RepairOrderInfoService repairOrderInfoService;
	
	@Resource
	private SysCodeInfoService sysCodeInfoService;

	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@Resource
	private WorkoutInfoService workoutInfoService;

	@RequestMapping("/defaultJsp")
	public String tablemanagePage() {
		return "web/repair/repair_order_list";
	}
	
	//-----------------------------------------------以下是下拉列表管理
	
	/**
	 * 人员分配页下拉列表查询
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList.json")
	public @ResponseBody JSONMap<String, Object> queryDownList(
			@FormModel SysCodeInfo sysCodeInfo,@FormModel SysUserInfo sysUserInfo,@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.DEPART_GROUP);
			List<SysCodeInfo> departGroupList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
//			sysCodeInfo.setCode_group_value(PmConstant.DEPART_GROUP_SUB1);
//			List<SysCodeInfo> departGroupSubList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.REPAIR_CATEGORY);
			List<SysCodeInfo> repairCategoryList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.STATE_YN);
			List<SysCodeInfo> stateYnList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.REPAIR_CLASSIFY);
			List<SysCodeInfo> repairClassifyList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
			
			sysUserInfo.setDept_code(PmConstant.ADMIN_EQUIP_DEPART);
			sysUserInfo.setSub_dept_code(PmConstant.ELECTRICAL_DEPART);
			List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
			if(entity!=null){
				model.put("requireTime", entity.getRequire_time());
			}
			
			model.put("departGroupList", departGroupList);
			model.put("departGroupSubList", "");
			model.put("repairCategoryList", repairCategoryList);
			model.put("stateYnList", stateYnList);
			model.put("repairClassifyList", repairClassifyList);
			model.put("sysUserInfoList", sysUserInfoList);
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
	 * 人员分配页下拉列表下级部门,用户查询
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList2.json")
	public @ResponseBody JSONMap<String, Object> queryDownList2(
			@FormModel SysCodeInfo sysCodeInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			if(PmConstant.ADMIN_EQUIP_DEPART.equals(sysUserInfo.getDept_code())){
				sysCodeInfo.setCode_group_value(PmConstant.ADMIN_EQUIP_DEPART_GROUP);
			}else if(PmConstant.PRODUCE_DEPART.equals(sysUserInfo.getDept_code())){
				sysCodeInfo.setCode_group_value(PmConstant.PRODUCE_DEPART_GROUP);
			}else if(PmConstant.PRODUCE_MANAGE_DEPART.equals(sysUserInfo.getDept_code())){
				sysCodeInfo.setCode_group_value(PmConstant.PRODUCE_MANAGE_DEPART_GROUP);
			}
//			else if(PmConstant.FINANCE_DEPART.equals(sysUserInfo.getDept_code())){
//				sysCodeInfo.setCode_group_value(PmConstant.FINANCE_DEPART_GROUP);
//			}
			List<SysCodeInfo> departGroupSubList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			if(sysUserInfo!=null){
				if("".equals(sysUserInfo.getSub_dept_code())){
					sysUserInfo.setSub_dept_code("1");
				}
			}
			List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			model.put("departGroupSubList", ("".equals(sysUserInfo.getDept_code()))?"":departGroupSubList);
			model.put("sysUserInfoList", sysUserInfoList);
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
	 * 维修单新增页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList3.json")
	public @ResponseBody JSONMap<String, Object> queryDownList3(
			@FormModel SysCodeInfo sysCodeInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.AREACODE);
			List<SysCodeInfo> repairPlaceList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
			
			sysUserInfo.setPosition_id(PmConstant.FACTORY_MANAGER);
			List<SysUserInfo> factoryManagerInfo = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
			List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setDept_code(processor.getDept());
			List<SysUserInfo> sysUserInfoList2 = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setDept_code(PmConstant.PRODUCE_MANAGE_DEPART);
			List<SysUserInfo> sysUserInfoList3 = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setPosition_id(null);
			sysUserInfo.setDept_code(null);
			sysUserInfo.setUser_num(processor.getCurrentUserId());
			List<SysUserInfo> loginUser = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			model.put("repairPlaceList", repairPlaceList);
			model.put("sysUserInfoList", sysUserInfoList);
			model.put("factoryManagerInfo", factoryManagerInfo);
			model.put("sysUserInfo2", (sysUserInfoList2.size()>0)?sysUserInfoList2.get(0):"");
			model.put("sysUserInfo3", (sysUserInfoList3.size()>0)?sysUserInfoList3.get(0):"");
			model.put("loginUser", loginUser);
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
	 * 维修单列表页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList4.json")
	public @ResponseBody JSONMap<String, Object> queryDownList4(
			@FormModel SysCodeInfo sysCodeInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.DEPART_GROUP);
			List<SysCodeInfo> departGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.APPROVE_GROUP);
			List<SysCodeInfo> approveGrouplist = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.USER_CHOOSE);
			List<SysCodeInfo> userChooseList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.AREACODE);
			List<SysCodeInfo> repairPlaceList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.REPAIR_CATEGORY);
			List<SysCodeInfo> repairCategorylist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			
			sysCodeInfo.setCode_group_value(PmConstant.PROPOSAL_APPROVE_STATE);
			List<SysCodeInfo> proposalApproveStatelist = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
	
			sysCodeInfo.setCode_group_value(PmConstant.REPAIR_CLASSIFY);
			List<SysCodeInfo> repairClassifyList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);
			
			List<SysUserInfo> allUser = sysUserInfoService.querySysUserInfoListForDealUser(sysUserInfo);
			
			model.put("departGrouplist", departGrouplist);
			model.put("approveGrouplist", approveGrouplist);
			model.put("departGroupSubList", "");
			model.put("userChooseList", userChooseList);
			model.put("repairPlaceList", repairPlaceList);
			model.put("repairCategorylist", repairCategorylist);
			model.put("allUser", allUser);
			model.put("loginUserId", processor.getCurrentUserId());
			model.put("proposalApproveStatelist", proposalApproveStatelist);
			model.put("repairClassifyList", repairClassifyList);
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
	 * 报价页面供应商下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList5.json")
	public @ResponseBody JSONMap<String, Object> queryDownList5(
			@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_SUPPLIER_GROUP);
			List<SysCodeInfo> offerSupplierGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.TRANSPORT_SPEC);
			List<SysCodeInfo> transportSpecGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.TOOL_USE_SPEC);
			List<SysCodeInfo> toolUseSpecGrouplist = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.PERSON_EXPENSES);
			List<SysCodeInfo> personExpensesGrouplist = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(sysCodeInfo);
			model.put("offerSupplierGrouplist", offerSupplierGrouplist);
			model.put("transportSpecGrouplist", transportSpecGrouplist);
			model.put("toolUseSpecGrouplist", toolUseSpecGrouplist);
			model.put("personExpensesGrouplist", personExpensesGrouplist);
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
	 * 报价页面费用类型下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList6.json")
	public @ResponseBody JSONMap<String, Object> queryDownList6(
			@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_EXPENSES_TYPE_GROUP);
			List<SysCodeInfo> expensesTypeGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_UNIT);
			List<SysCodeInfo> offerUnitGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			model.put("expensesTypeGrouplist", expensesTypeGrouplist);
			model.put("offerUnitGrouplist", offerUnitGrouplist);
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
	 * 维修结果页面维修内容三级下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList7.json")
	public @ResponseBody JSONMap<String, Object> queryDownList7(
			@FormModel MaintenanceItemInfo maintenanceItemInfo,@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<MaintenanceItemInfo> list = repairOrderInfoService.queryMaintenanceItemInfoList(maintenanceItemInfo);
			model.put("maintenanceItemInfoList", list);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
//-----------------------------------------------以下是维修单管理
	
	/**
	 * 维修单列表查询
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepairOrderInfoList.json")
	public @ResponseBody JSONMap<String, Object> queryRepairOrderInfoList(
			@FormModel ("repairOrderInfo")RepairOrderInfoDTO repairOrderInfo,@FormModel ("pageflag")String pageflag,
			@FormModel ("unpayedpageflag")String unpayedpageflag,
			@FormModel ("repair_user_flag")String repair_user_flag,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			String pageflag2 = (pageflag==null)?"":pageflag;
			String unpayedpageflag2 = (unpayedpageflag==null)?"":unpayedpageflag;
			String repair_user_flag2 = (repair_user_flag==null)?"":repair_user_flag;
			Pagination<RepairOrderInfoDTO> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfoList(repairOrderInfo,pageflag2,unpayedpageflag2,repair_user_flag2,processor.getPageIndex(), processor.getPageSize(),processor);
			List<RepairOrderInfoDTO> list = repairOrderInfoList.getRecords();
			for(RepairOrderInfoDTO item:list){
				if(PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
					item.setFlag("7");
				}
				if(processor.getCurrentUserId().equals(item.getDeal_user_id())){
					if(PmConstant.APPROVE_WAIT.equals(item.getApproval_state()) && PmConstant.APPROVE.equals(item.getApproval_step())
							|| (PmConstant.FACTORY_MANAGER_APPROVE_WAIT.equals(item.getApproval_state()) && PmConstant.FACTORY_MANAGER_APPROVE.equals(item.getApproval_step()))){
						item.setFlag("1");//shenpi bohui
					}else if(PmConstant.DISTRIBUT_WAIT.equals(item.getApproval_state()) && PmConstant.DISTRIBUT.equals(item.getApproval_step())){
						item.setFlag("2");//fenpei
					}else if(PmConstant.OFFER_WAIT.equals(item.getApproval_state()) && PmConstant.FACTORY_MANAGER_APPROVE.equals(item.getApproval_step())){
						item.setFlag("3");//baojia
					}else if(PmConstant.CONFIRM_WAIT.equals(item.getApproval_state()) && PmConstant.CONFIRM.equals(item.getApproval_step())
							|| (PmConstant.FACTORY_MANAGER_CONFIRM_WAIT.equals(item.getApproval_state()) && PmConstant.FACTORY_MANAGER_CONFIRM.equals(item.getApproval_step()))){
						item.setFlag("4");//queren
					}else if(PmConstant.REPAIR_WAIT.equals(item.getApproval_state()) && PmConstant.REAPIR.equals(item.getApproval_step())){
						item.setFlag("5");//weixiu
					}else{
						item.setFlag("6");
					}
				}
				if(PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || processor.getCurrentUserId().equals(item.getCreate_id())){
					item.setEditYnFlag("Y");
				}else{
					item.setEditYnFlag("N");
				}
				if(PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || (PmConstant.APPROVE_WAIT.equals(item.getApproval_state()) && processor.getCurrentUserId().equals(item.getCreate_id()))){
					item.setDeleteYnFlag("Y");
				}else{
					item.setDeleteYnFlag("N");
				}
			}
			repairOrderInfoList.setRecords(list);
			UIDataConvert<RepairOrderInfoDTO> convert = new UIDataConvert<RepairOrderInfoDTO>();
			IConvert approvalStatus = new SqlMapConvertImpl("approval_state","approval_state_nm", "sys_code_info","code_nm", "code_value","and code_group_value='approve_group'");
			IConvert dealUser = new SqlMapConvertImpl("deal_user_id","deal_user_id_nm", "sys_user_info","user_nm", "user_num","");
			IConvert createUser = new SqlMapConvertImpl("create_id","create_id_nm", "sys_user_info","user_nm", "user_num","");
			IConvert repairUser = new SqlMapConvertImpl("repair_user_id","repair_user_id_nm", "sys_user_info","user_nm", "user_num","");
			IConvert userDepart = new SqlMapConvertImpl("dept_code","dept_nm", "sys_code_info","code_nm", "code_value","and code_group_value='depart_group'");
			IConvert repiairPlace = new SqlMapConvertImpl("repair_place","repair_place_nm", "sys_code_info","code_nm", "code_value","and code_group_value='areacode'");
			IConvert moneyYn = new SqlMapConvertImpl("repair_type","repair_type_nm", "sys_code_info","code_nm", "code_value","and code_group_value='repair_category_group'");
			IConvert repairClassify = new SqlMapConvertImpl("repair_classify","repair_classify_nm", "sys_code_info","code_nm", "code_value","and code_group_value='repair_classify'");
			convert.addConvert(approvalStatus);
			convert.addConvert(dealUser);
			convert.addConvert(createUser);
			convert.addConvert(repairUser);
			convert.addConvert(userDepart);
			convert.addConvert(repiairPlace);
			convert.addConvert(moneyYn);
			convert.addConvert(repairClassify);
			convert.format(repairOrderInfoList);
			model.put("data", repairOrderInfoList);
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
	 * 维修单新增
	 * 
	 * @param repairOrderInfo
	 * @param approveInfoList
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addRepairOrderInfoData.json")
	public @ResponseBody JSONMap<String, Object> addRepairOrderInfoData(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel ("approveInfoList") List<ApproveInfo> approveInfoList, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			repairOrderInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			repairOrderInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			repairOrderInfo.setCreate_id(processor.getCurrentUserId());
			repairOrderInfo.setUpdate_id(processor.getCurrentUserId());
			repairOrderInfo.setApproval_state(PmConstant.APPROVE_WAIT);
			repairOrderInfo.setApproval_step(PmConstant.APPROVE);
			repairOrderInfo.setPay_yn(PmConstant.STATE_N);
			String str = repairOrderInfoService.saveRepairOrderInfo(repairOrderInfo);
			int count=1;
			MailUtil mu = new MailUtil();
			for(ApproveInfo approveInfo:approveInfoList){
				approveInfo.setRepair_order_num(str);
				approveInfo.setApprove_step(count+"");
				if(count == 1){
					approveInfo.setApprove_yn(PmConstant.STATE_Y);
					approveInfo.setApproval_state(PmConstant.APPROVE_STATE);
				}else if(count == 2){
					repairOrderInfo.setDeal_user_id(approveInfo.getApprove_user_id());
					approveInfo.setApprove_yn(PmConstant.STATE_N);
					sysUserInfo.setUser_num(approveInfo.getApprove_user_id());
					List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
					if(sysUserInfoList.size()>0){
						mu.setToEmails(sysUserInfoList.get(0).getEmail());
						mu.setToName(sysUserInfoList.get(0).getUser_nm());
						mu.setContent(PmConstant.MAIL_APPROVE);
					}
				}else{
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}
				approveInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setCreate_id(processor.getCurrentUserId());
				approveInfo.setUpdate_id(processor.getCurrentUserId());
				repairOrderInfoService.saveApproveInfo(approveInfo);
				count++;
			}
			RepairOrderInfo info = new RepairOrderInfo();
			info.setRepair_order_num(str);
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(info);
			if(list.size()>0){
				info = list.get(0);
				info.setDeal_user_id(repairOrderInfo.getDeal_user_id());
				repairOrderInfoService.updateRepairOrderInfo(info);
			}
			//发送邮件
			MailUtil.sendMail(mu);
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
	 * 维修单修改前查询
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepairOrderBeforeUpdate.json")
	public @ResponseBody JSONMap<String, Object> queryRepairOrderBeforeUpdate(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			String draftUser = "";
			List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(repairOrderInfoList.size()>0){
				model.put("repairOrderInfo", repairOrderInfoList.get(0));
				approveInfo.setRepair_order_num(repairOrderInfoList.get(0).getRepair_order_num());
				List<ApproveInfo> approveList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
				for(ApproveInfo item:approveList){
					if(PmConstant.DRAFT.equals(item.getApprove_step())){
						draftUser = item.getApprove_user_id();
					}else if(PmConstant.APPROVE.equals(item.getApprove_step())){
						model.put("approveUser2", item);
					}else if(PmConstant.DISTRIBUT.equals(item.getApprove_step())){
						model.put("approveUser3", item);
					}
				}
			}
			sysUserInfo.setUser_num(draftUser);
			List<SysUserInfo> loginUser = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			model.put("loginUser", loginUser);
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
	 * 修改维修单信息
	 * 
	 * @param repairOrderInfo
	 * @param approveInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateRepairOrderInfoData.json")
	public @ResponseBody JSONMap<String, Object> updateRepairOrderInfoData(
			@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel ("approveInfoList")List<ApproveInfo> approveInfoList, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			int count=1;
			String create_id="";
			String create_time="";
			ApproveInfo entity = new ApproveInfo();
			RepairOrderInfo repairOrderEntity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
			if(repairOrderEntity!=null){
				repairOrderInfo.setRepair_order_num(repairOrderEntity.getRepair_order_num());
				entity.setRepair_order_num(repairOrderInfo.getRepair_order_num());
			}
			List<ApproveInfo> list = repairOrderInfoService.queryApproveInfoListNoPage(entity);
			if(list.size()>0){
				create_id=list.get(0).getCreate_id();
				create_time=list.get(0).getCreate_time();
				for(ApproveInfo item:list){
					repairOrderInfoService.deleteApproveInfo(item);
				}
			}
			for(ApproveInfo approveInfo:approveInfoList){
				approveInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
				approveInfo.setApprove_step(count+"");
				if(count == 1){
					approveInfo.setApprove_yn(PmConstant.STATE_Y);
					approveInfo.setApproval_state(PmConstant.APPROVE_STATE);
				}else if(count == 2){
					repairOrderInfo.setDeal_user_id(approveInfo.getApprove_user_id());
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}else{
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}
				approveInfo.setCreate_time(create_time);
				approveInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setCreate_id(create_id);
				approveInfo.setUpdate_id(processor.getCurrentUserId());
				repairOrderInfoService.saveApproveInfo(approveInfo);
				count++;
				}
				repairOrderInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				repairOrderInfo.setUpdate_id(processor.getCurrentUserId());
				int cnt = repairOrderInfoService.updateRepairOrderInfo(repairOrderInfo);
				if(cnt>0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}

	/**
	 * 删除维修单信息
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteRepairOrderInfoData.json")
	public @ResponseBody JSONMap<String, Object> deleteRepairOrderInfoData(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
		if(entity!=null){
			approveInfo.setRepair_order_num(entity.getRepair_order_num());
		}
		List<ApproveInfo> list = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
		for(ApproveInfo info:list){
			repairOrderInfoService.deleteApproveInfo(info);
		}
		int cnt = repairOrderInfoService.deleteRepairOrderInfo(repairOrderInfo);
		if (cnt > 0) {
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} else {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		return model;
	}
	
	/**
	 * 维修单录入时详情查询
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepairOrderForOrderDetail.json")
	public @ResponseBody JSONMap<String, Object> queryRepairOrderForOrderDetail(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo, @FormModel SysCodeInfo sysCodeInfo,
			@FormModel SysUserInfo sysUserInfo,@FormModel DistributionInfo distributionInfo,@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		String draftUser = "";
		List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
		if(repairOrderInfoList.size()>0){
			approveInfo.setRepair_order_num(repairOrderInfoList.get(0).getRepair_order_num());
			distributionInfo.setRepair_order_num(repairOrderInfoList.get(0).getRepair_order_num());
			List<ApproveInfo> approveList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
			List<DistributionInfo> distributionInfoList = repairOrderInfoService.queryDistributionInfoList(distributionInfo);
			if(approveList.size()>0){
				for(ApproveInfo item: approveList){
					if(PmConstant.APPROVE_STATE.equals(item.getApproval_state())){
						item.setApproval_nm("已审批");
					}else if(PmConstant.REJECT_STATE.equals(item.getApproval_state())){
						item.setApproval_nm("驳回");
					}else{
						item.setApproval_nm("待审批");
						if(distributionInfoList.size()>0){
							if(PmConstant.FACTORY_MANAGER_APPROVE.equals(item.getApprove_step()) && "N".equals(distributionInfoList.get(0).getRepair_type())){
								item.setApproval_nm("不审批");
							}
						}
					}
					if(PmConstant.APPROVE.equals(item.getApprove_step())){
						model.put("appuser1", item);
					}else if(PmConstant.DISTRIBUT.equals(item.getApprove_step())){
						model.put("appuser2", item);
					}else if(PmConstant.FACTORY_MANAGER_APPROVE.equals(item.getApprove_step())){
						model.put("appuser3", item);
					}else if(PmConstant.CONFIRM.equals(item.getApprove_step())){
						model.put("appuser4", item);
					}else if(PmConstant.FACTORY_MANAGER_CONFIRM.equals(item.getApprove_step())){
						model.put("appuser5", item);
					}else if(PmConstant.DRAFT.equals(item.getApprove_step())){
						draftUser = item.getApprove_user_id();
					}
					model.put("repairOrderInfo", repairOrderInfoList.get(0));
				}
			}
		}
		sysCodeInfo.setCode_group_value(PmConstant.AREACODE);
		List<SysCodeInfo> repairPlaceList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
		
		sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
		
		sysUserInfo.setPosition_id(PmConstant.FACTORY_MANAGER);
		List<SysUserInfo> factoryManagerInfo = sysUserInfoService.querySysUserInfoList(sysUserInfo);
		
		sysUserInfo.setPosition_id(null);
		sysUserInfo.setUser_num(draftUser);
		List<SysUserInfo> loginUser = sysUserInfoService.querySysUserInfoList(sysUserInfo);
		
		if(repairOrderInfoList.size()>0){
			String repair_user_id = repairOrderInfoList.get(0).getRepair_user_id();
			if(!"".equals(repair_user_id) && repair_user_id != null){
				sysUserInfo.setUser_num(repair_user_id);
				List<SysUserInfo> repairUserList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
				model.put("repairUser", (repairUserList.size()>0)?repairUserList.get(0).getUser_nm():"");
			}
		}
		model.put("repairPlaceList", repairPlaceList);
		model.put("sysUserInfoList", sysUserInfoList);
		model.put("factoryManagerInfo", factoryManagerInfo);
		model.put("loginUser", loginUser);
		model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		return model;
	}
	
	//-----------------------------------------------以下是维修结果管理
	
	/**
	 * 维修结果查询
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getRepairResultInfoById.json")
	public @ResponseBody JSONMap<String, Object> getRepairResultInfoById(
			@FormModel RepairResultInfo repairResultInfo,@FormModel ("id")String id,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo repairOrderEntity = repairOrderInfoService.getRepairOrderInfoById(id);
		if(repairOrderEntity!=null){
			repairResultInfo.setRepair_order_num(repairOrderEntity.getRepair_order_num());
		}
		RepairResultInfo entity = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
		if(entity != null){
			model.put("repairresultinfo", entity);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}else{
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		
		return model;
	}

	/**
	 * 维修结果新增
	 * 
	 * @param approveInfo
	 * @param repairOrderInfo
	 * @param repairResultInfo
	 * @param repairCostDetail
	 * @param repairMachineDetail
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addRepairResultInfo.json")
	public @ResponseBody JSONMap<String, Object> addRepairResultInfo(
			@FormModel ApproveInfo approveInfo,
			@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel RepairResultInfo repairResultInfo,
			@FormModel ("repairCostDetailList")List<RepairCostDetail> repairCostDetailList,
			@FormModel ("repairMachineDetailList")List<RepairMachineDetail> repairMachineDetailList,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(list.size()>0){
				repairResultInfo.setRepair_order_num(list.get(0).getRepair_order_num());
				RepairResultInfo repairResultEntity = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
				if(repairResultEntity != null){
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, "已存在维修结果");
				}else{
					repairResultInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					repairResultInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					repairResultInfo.setFinish_date(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					repairResultInfo.setCreate_id(processor.getCurrentUserId());
					repairResultInfo.setUpdate_id(processor.getCurrentUserId());
					String repair_result_id = repairOrderInfoService.saveRepairResultInfo(repairResultInfo);
					for(RepairCostDetail item:repairCostDetailList){
						item.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setCreate_id(processor.getCurrentUserId());
						item.setUpdate_id(processor.getCurrentUserId());
						item.setRepair_result_id(repair_result_id);
						repairOrderInfoService.saveRepairCostDetail(item);
					}
					for(RepairMachineDetail item:repairMachineDetailList){
						item.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setCreate_id(processor.getCurrentUserId());
						item.setUpdate_id(processor.getCurrentUserId());
						item.setRepair_result_id(repair_result_id);
						repairOrderInfoService.saveRepairMachineDetail(item);
					}
					
					RepairOrderInfo entity = list.get(0);
					entity.setApproval_state(PmConstant.CONFIRM_WAIT);
					entity.setRepair_date(DateUtil.getNow(DateUtil.Y_M_D));
					entity.setApproval_step(PmConstant.CONFIRM);
					entity.setUpdate_id(processor.getCurrentUserId());
					entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					approveInfo.setRepair_order_num(list.get(0).getRepair_order_num());
					approveInfo.setApprove_step(PmConstant.CONFIRM);
					List<ApproveInfo> approList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
					SysUserInfo sysUserInfo = new SysUserInfo();
					if(approList.size()>0){
						entity.setDeal_user_id(approList.get(0).getApprove_user_id());
						sysUserInfo.setUser_num(approList.get(0).getApprove_user_id());
					}
					int cnt = repairOrderInfoService.updateRepairOrderInfo(entity);
					if(cnt>0){
						MailUtil mu = new MailUtil();
						List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
						if(sysUserInfoList.size()>0){
							mu.setToEmails(sysUserInfoList.get(0).getEmail());
							mu.setToName(sysUserInfoList.get(0).getUser_nm());
							mu.setContent(PmConstant.MAIL_CONFIRM);
						}
						MailUtil.sendMail(mu);
					}
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
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
	 * 维修结果查询
	 * 
	 * @param repairResultInfo
	 * @param repairCostDetail
	 * @param repairMachineDetail
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getRepairResultInfo.json")
	public @ResponseBody JSONMap<String, Object> getRepairResultInfo(
			@FormModel RepairResultInfo repairResultInfo,
			@FormModel RepairCostDetail repairCostDetail,
			@FormModel RepairMachineDetail repairMachineDetail,
			@FormModel OfferInfo offerInfo,
			@FormModel SysCodeInfo sysCodeInfo,@FormModel MaintenanceItemInfo maintenanceItemInfo,@FormModel ("id")String id,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo repairOrderInfo = repairOrderInfoService.getRepairOrderInfoById(id);
		if(repairOrderInfo!=null){
			repairResultInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
			offerInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
			List<OfferInfo> offerList = repairOrderInfoService.queryOfferInfoList(offerInfo);
			if(offerList.size()>0){
				model.put("offerTax", offerList.get(0).getTax());
			}
			model.put("repairOrderEntity", repairOrderInfo);
		}
		RepairResultInfo entity = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
		if(entity != null){
			model.put("repairResultTax", entity.getTax());
			model.put("tatalAll", entity.getTatal_all());
			if(PmConstant.FACTORY_MANAGER.equals(processor.getAuth()) || PmConstant.SECTION_CHIEF.equals(processor.getAuth()) 
					|| PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
				entity.setShowMoney(PmConstant.STATE_Y);
			}else{
				entity.setShowMoney(PmConstant.STATE_N);
			}
			repairCostDetail.setRepair_result_id(entity.getRepair_result_id());
			repairMachineDetail.setRepair_result_id(entity.getRepair_result_id());
			List<RepairCostDetail> repairCostDetailList = repairOrderInfoService.queryRepairCostDetailList(repairCostDetail);
			if(repairCostDetailList.size()>0){
				boolean tempFlag = false;
				if(PmConstant.FACTORY_MANAGER.equals(processor.getAuth()) || PmConstant.SECTION_CHIEF.equals(processor.getAuth()) 
						|| PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
					tempFlag = true;
				}
				for(RepairCostDetail item:repairCostDetailList){
					if(tempFlag){
						item.setShowMoney(PmConstant.STATE_Y);
					}else{
						item.setShowMoney(PmConstant.STATE_N);
					}
				}
			}
			List<RepairMachineDetail> repairMachineDetailList = repairOrderInfoService.queryRepairMachineDetailList(repairMachineDetail);
			UIDataConvert<RepairMachineDetail> convert = new UIDataConvert<RepairMachineDetail>();
			IConvert machineNm = new SqlMapConvertImpl("machine_id","machine_nm", "machine_info","machine_nm", "machine_id","");
			convert.addConvert(machineNm);
			convert.format(repairMachineDetailList);
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_EXPENSES_TYPE_GROUP);
			List<SysCodeInfo> expensesTypeGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			List<MaintenanceItemInfo> repairDetailGrouplist = repairOrderInfoService.queryMaintenanceItemInfoList(maintenanceItemInfo);
			if(repairCostDetailList.size()>0){
				for(RepairCostDetail item:repairCostDetailList){
					item.setExpensesTypeGrouplist(expensesTypeGrouplist);
				}
			}
			if(repairMachineDetailList.size()>0){
				for(RepairMachineDetail item:repairMachineDetailList){
					item.setRepairDetailGrouplist(repairDetailGrouplist);
				}
			}
			model.put("repairResult", entity);
			model.put("repairCostDetailList", repairCostDetailList);
			model.put("repairMachineDetailList", repairMachineDetailList);
			model.put("expensesTypeGrouplist", expensesTypeGrouplist);
			model.put("repairDetailGrouplist", repairDetailGrouplist);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}else{
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		
		return model;
	}
	
	/**
	 * 维修结果新增时 维修内容查询 取前15个字
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getRepairDetailNm.json")
	public @ResponseBody JSONMap<String, Object> getRepairDetailNm(
			@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel DistributionInfo distributionInfo,
			@FormModel OfferInfo offerInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
		if(entity != null){
			String str = entity.getRepair_detail();
//			if(str.length()>15){
//				str = entity.getRepair_detail().substring(0,15);
//			}
			distributionInfo.setRepair_order_num(entity.getRepair_order_num());
			List<DistributionInfo> list = repairOrderInfoService.queryDistributionInfoList(distributionInfo);
			offerInfo.setRepair_order_num(entity.getRepair_order_num());
			List<OfferInfo> offerList = repairOrderInfoService.queryOfferInfoList(offerInfo);
			if(list.size()>0){
				model.put("requireTime", list.get(0).getRequire_date());
			}
			if(offerList.size()>0){
				model.put("offerTax", offerList.get(0).getTax());
				model.put("totalAll", offerList.get(0).getTatal_all());
			}
			if(PmConstant.FACTORY_MANAGER.equals(processor.getAuth()) || PmConstant.SECTION_CHIEF.equals(processor.getAuth()) 
					|| PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
				model.put("showMoney", "Y");
			}else{
				model.put("showMoney", "N");
			}
			model.put("repairOrderEntity", entity);
			model.put("repairDetailNm", str);
			model.put("expanseYn", entity.getRepair_type());
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}else{
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		
		return model;
	}
	
	//-----------------------------------------------以下是人员分配管理
	
	/**
	 * 人员分配新增
	 * 
	 * @param distributionInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addDistributionInfoData.json")
	public @ResponseBody JSONMap<String, Object> addDistributionInfoData(
			@FormModel DistributionInfo distributionInfo,@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(list.size()>0){
				distributionInfo.setRepair_order_num(list.get(0).getRepair_order_num());
				List<DistributionInfo> distributList = repairOrderInfoService.queryDistributionInfoList(distributionInfo);
				if(distributList.size()>0){
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, "已存在分配记录");
				}else{
					distributionInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					distributionInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					distributionInfo.setCreate_id(processor.getCurrentUserId());
					distributionInfo.setUpdate_id(processor.getCurrentUserId());
					int cnt = repairOrderInfoService.saveDistributionInfo(distributionInfo);
					if(cnt>0){
						RepairOrderInfo entity = list.get(0);
						if(PmConstant.EXPENSES_N.equals(distributionInfo.getRepair_type())){
							entity.setApproval_state(PmConstant.REPAIR_WAIT);
							entity.setApproval_step(PmConstant.REAPIR);
						}else{
							entity.setApproval_state(PmConstant.OFFER_WAIT);
							entity.setApproval_step(PmConstant.FACTORY_MANAGER_APPROVE);
						}
						entity.setDistribute_date(DateUtil.getNow(DateUtil.Y_M_D));
						entity.setRepair_type(distributionInfo.getRepair_type());
						entity.setRepair_user_id(distributionInfo.getUser_id());
						entity.setDeal_user_id(distributionInfo.getUser_id());
						entity.setUpdate_id(processor.getCurrentUserId());
						entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						int count = repairOrderInfoService.updateRepairOrderInfo(entity);
						if(count>0){
							approveInfo.setRepair_order_num(distributionInfo.getRepair_order_num());
							approveInfo.setApprove_step(PmConstant.DISTRIBUT);
							List<ApproveInfo> approList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
							if(approList.size()>0){
								ApproveInfo info = approList.get(0);
								info.setApprove_yn(PmConstant.STATE_Y);
								info.setApproval_state(PmConstant.APPROVE_STATE);
								repairOrderInfoService.updateApproveInfo(info);
							}
						}
						MailUtil mu = new MailUtil();
						SysUserInfo sysUserInfo = new SysUserInfo();
						sysUserInfo.setUser_num(distributionInfo.getUser_id());
						List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
						if(sysUserInfoList.size()>0){
							mu.setToEmails(sysUserInfoList.get(0).getEmail());
							mu.setToName(sysUserInfoList.get(0).getUser_nm());
							mu.setContent(PmConstant.MAIL_DEAL);
						}
						MailUtil.sendMail(mu);
						if(PmConstant.STATE_Y.equals(distributionInfo.getNotice_yn())){
							sysUserInfo.setUser_num("");
							sysUserInfo.setPosition_id(PmConstant.FACTORY_MANAGER);
							sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
							if(sysUserInfoList.size()>0){
								mu.setToEmails(sysUserInfoList.get(0).getEmail());
								mu.setToName(sysUserInfoList.get(0).getUser_nm());
								mu.setContent(PmConstant.MAIL_FACTORY_MANAGER_ALARAM);
							}
							MailUtil.sendMail(mu);
						}
					}
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}

		return model;
	}

	//-----------------------------------------------以下是报价管理
	
	/**
	 * 报价结果查询
	 * 
	 * @param offerInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getRepairOfferInfoById.json")
	public @ResponseBody JSONMap<String, Object> getRepairOfferInfoById(
			@FormModel OfferInfo offerInfo,@FormModel ("id")String id,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo repairOrderEntity = repairOrderInfoService.getRepairOrderInfoById(id);
		if(repairOrderEntity!=null){
			offerInfo.setRepair_order_num(repairOrderEntity.getRepair_order_num());
		}
		List<OfferInfo> list = repairOrderInfoService.queryOfferInfoList(offerInfo);
		if(list.size()>0){
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}else{
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		
		return model;
	}
	
	/**
	 * 报价新增
	 * 
	 * @param offerInfo
	 * @param offerDetail
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addOfferInfo.json")
	public @ResponseBody JSONMap<String, Object> addOfferInfo(
			@FormModel OfferInfo offerInfo, @FormModel ("offerDetailList")List<OfferDetail> offerDetailList
			,@FormModel RepairOrderInfo repairOrderInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
			if(entity!=null){
				offerInfo.setRepair_order_num(entity.getRepair_order_num());
				List<OfferInfo> offerInfoList = repairOrderInfoService.queryOfferInfoList(offerInfo);
				if(offerInfoList.size()>0){
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, "已存在报价记录");
				}else{
					offerInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					offerInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					offerInfo.setCreate_id(processor.getCurrentUserId());
					offerInfo.setUpdate_id(processor.getCurrentUserId());
					String offer_id = repairOrderInfoService.saveOfferInfo(offerInfo);
					for(OfferDetail item:offerDetailList){
						item.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						item.setCreate_id(processor.getCurrentUserId());
						item.setUpdate_id(processor.getCurrentUserId());
						item.setOffer_id(offer_id);
						repairOrderInfoService.saveOfferDetail(item);
					}
					
					entity.setOffer_date(DateUtil.getNow(DateUtil.Y_M_D));
					entity.setApproval_step(PmConstant.FACTORY_MANAGER_APPROVE);
					entity.setApproval_state(PmConstant.FACTORY_MANAGER_APPROVE_WAIT);
					sysUserInfo.setPosition_id(PmConstant.FACTORY_MANAGER);
					List<SysUserInfo> factoryManagerInfo = sysUserInfoService.querySysUserInfoList(sysUserInfo);
					MailUtil mu = new MailUtil();
					if(factoryManagerInfo.size()>0){
						entity.setDeal_user_id(factoryManagerInfo.get(0).getUser_num());
						mu.setToEmails(factoryManagerInfo.get(0).getEmail());
						mu.setToName(factoryManagerInfo.get(0).getUser_nm());
						mu.setContent(PmConstant.MAIL_APPROVE);
					}
					entity.setUpdate_id(processor.getCurrentUserId());
					entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					int cnt = repairOrderInfoService.updateRepairOrderInfo(entity);
					if(cnt>0){
						MailUtil.sendMail(mu);
					}
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
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
	 * 报价详情查询
	 * 
	 * @param offerInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryOfferDetailEntity.json")
	public @ResponseBody JSONMap<String, Object> queryOfferDetailEntity(
			@FormModel OfferInfo offerInfo,@FormModel RepairOrderInfo repairOrderInfo,@FormModel SysCodeInfo sysCodeInfo,@FormModel OfferDetail offerDetail, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
		if(entity!= null){
			offerInfo.setRepair_order_num(entity.getRepair_order_num());
		}
		List<OfferInfo> list1 = repairOrderInfoService.queryOfferInfoList(offerInfo);
		if(list1.size()>0){
			offerDetail.setOffer_id(list1.get(0).getOffer_id());
			List<OfferInfoDTO> offerDetailList = repairOrderInfoService.queryOfferDetailListNoPage(offerDetail);
			if(offerDetailList.size()>0){
				boolean tempFlag = false;
				if(PmConstant.FACTORY_MANAGER.equals(processor.getAuth()) || PmConstant.SECTION_CHIEF.equals(processor.getAuth()) 
						|| PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
					tempFlag = true;
				}
				for(OfferInfoDTO item:offerDetailList){
					if(tempFlag){
						item.setShowMoney(PmConstant.STATE_Y);
					}else{
						item.setShowMoney(PmConstant.STATE_N);
					}
				}
			}
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_EXPENSES_TYPE_GROUP);
			List<SysCodeInfo> expensesTypeGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_UNIT);
			List<SysCodeInfo> offerUnitGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.PERSON_EXPENSES);
			List<SysCodeInfo> personExpensesGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.TRANSPORT_SPEC);
			List<SysCodeInfo> transportSpecGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.TOOL_USE_SPEC);
			List<SysCodeInfo> toolUseSpecGrouplist = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(sysCodeInfo);
			for(OfferInfoDTO item:offerDetailList){
				item.setExpensesTypeGrouplist(expensesTypeGrouplist);
				item.setOfferUnitGrouplist(offerUnitGrouplist);
				item.setPersonExpensesGrouplist(personExpensesGrouplist);
				item.setTransportSpecGrouplist(transportSpecGrouplist);
				item.setToolUseSpecGrouplist(toolUseSpecGrouplist);
			}
			sysCodeInfo.setCode_group_value(PmConstant.OFFER_SUPPLIER_GROUP);
			List<SysCodeInfo> offerSupplierGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			model.put("OfferInfo", list1.get(0));
			model.put("offerDetailList", offerDetailList);
			model.put("offerSupplierGrouplist", offerSupplierGrouplist);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}else{
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		return model;
	}
	
	//-----------------------------------------------以下是审批，驳回，确认管理
	
	/**
	 * 审批/驳回 /确认 操作
	 * 
	 * @param repairOrderInfo
	 * @param approveInfo
	 * @param updateflag
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateApproveAndReject.json")
	public @ResponseBody JSONMap<String, Object> updateApproveAndReject(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo, @FormModel ("updateflag")String updateflag,@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
			approveInfo.setRepair_order_num(entity.getRepair_order_num());
			List<ApproveInfo> approList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
			ApproveInfo tempEntity = new ApproveInfo();
			approveInfo.setApprove_step(entity.getApproval_step());
			String tempApproUser = "";
			String draftUser = "";
			if(approList.size()>0){
				int tempstep = -1;
				for(int i = 0;i<approList.size();i++){
					if(approveInfo.getApprove_step().equals(approList.get(i).getApprove_step())){
						tempstep =  Integer.parseInt(approList.get(i).getApprove_step())+1;
						tempApproUser = approList.get(i).getApprove_user_id();
					}
					if(PmConstant.DRAFT.equals(approList.get(i).getApprove_step())){
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
				entity.setApproval_state(PmConstant.REJECT);
				entity.setDeal_user_id("");
				tempEntity.setApproval_state(PmConstant.REJECT_STATE);
				tempEntity.setApprove_user_id(tempApproUser);
				sysUserInfo.setUser_num(draftUser);
				mu.setContent(PmConstant.MAIL_REJECT);
			}else if(PmConstant.APPROVE_STATE.equals(updateflag)){
				if(PmConstant.APPROVE_WAIT.equals(entity.getApproval_state())){
					entity.setApproval_state(PmConstant.DISTRIBUT_WAIT);
					entity.setApproval_step(PmConstant.DISTRIBUT);
					entity.setApproval_date(DateUtil.getNow(DateUtil.Y_M_D));
				}else if(PmConstant.FACTORY_MANAGER_APPROVE_WAIT.equals(entity.getApproval_state())){
					entity.setFactory_manager_approval_date(DateUtil.getNow(DateUtil.Y_M_D));
					entity.setApproval_state(PmConstant.REPAIR_WAIT);
					entity.setApproval_step(PmConstant.REAPIR);
					tempEntity.setApprove_user_id(entity.getRepair_user_id());
					entity.setDeal_user_id(entity.getRepair_user_id());
				}
				tempEntity.setApproval_state(PmConstant.APPROVE_STATE);
				mu.setContent(PmConstant.MAIL_DEAL);
			}else if(PmConstant.CONFIRM_STATE.equals(updateflag)){
				if(PmConstant.CONFIRM_WAIT.equals(entity.getApproval_state())){
					entity.setApproval_state(PmConstant.FACTORY_MANAGER_CONFIRM_WAIT);
					entity.setApproval_step(PmConstant.FACTORY_MANAGER_CONFIRM);
					entity.setConfirm_date(DateUtil.getNow(DateUtil.Y_M_D));
					mu.setContent(PmConstant.MAIL_CONFIRM);
				}else if(PmConstant.FACTORY_MANAGER_CONFIRM_WAIT.equals(entity.getApproval_state())){
					entity.setApproval_state(PmConstant.DONE);
					entity.setDeal_user_id("");
					entity.setFactory_manager_confirm_date(DateUtil.getNow(DateUtil.Y_M_D));
					sendMailFlag = false;
				}
				tempEntity.setApproval_state(PmConstant.APPROVE_STATE);
			}
			sysUserInfo.setUser_num(tempEntity.getApprove_user_id());
			entity.setUpdate_id(processor.getCurrentUserId());
			entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			int cnt = repairOrderInfoService.updateRepairOrderInfo(entity);
			if(cnt>0){
				List<ApproveInfo> list = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
				if(list.size()>0){
					ApproveInfo info = list.get(0);
					info.setApprove_yn(PmConstant.STATE_Y);
					info.setApproval_state(tempEntity.getApproval_state());
					info.setUpdate_id(processor.getCurrentUserId());
					info.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					int count = repairOrderInfoService.updateApproveInfo(info);
					if(count>0){
						if(sendMailFlag){
							List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo);
							if(sysUserInfoList.size()>0){
								mu.setToEmails(sysUserInfoList.get(0).getEmail());
								mu.setToName(sysUserInfoList.get(0).getUser_nm());
							}
							MailUtil.sendMail(mu);
						}
						model.put(SysConstant.OP_FLAG, true);
						model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
					}else{
						model.put(SysConstant.OP_FLAG, false);
						model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
					}
				}
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		
		return model;
	}
	
	//以下是财务结算管理
	
	/**
	 * 结算 操作
	 * 
	 * @param repairOrderInfo
	 * @param approveInfo
	 * @param updateflag
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateForAccount.json")
	public @ResponseBody JSONMap<String, Object> updateForAccount(
			@FormModel RepairOrderInfo repairOrderInfo, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			RepairOrderInfo entity = repairOrderInfoService.getRepairOrderInfoById(repairOrderInfo.getId());
			if(entity!=null){
				entity.setAccount_date(repairOrderInfo.getAccount_date()+" 00:00:00 ");
				entity.setPay_yn(PmConstant.STATE_Y);
				entity.setUpdate_id(processor.getCurrentUserId());
				entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				repairOrderInfoService.updateRepairOrderInfo(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		
		return model;
	}
	
	//以下是手机版管理
	
	/**
	 * 维修单列表
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepairOrderInfoListMob.json")
	public @ResponseBody JSONMap<String, Object> queryRepairOrderInfoListMob(
			@FormModel ("repairOrderInfo")RepairOrderInfoDTO repairOrderInfo, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<RepairOrderInfoDTO> list = repairOrderInfoService.queryRepairOrderInfoListMob(repairOrderInfo,processor);
			model.put("data", list);
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
	 * 
	 * 临时维修费用列表查询
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepairCostDetailTempListMob.json")
	public @ResponseBody JSONMap<String, Object> queryRepairCostDetailTempListMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<RepairCostDetailTemp> list = repairOrderInfoService.queryRepairCostDetailTempList(repairCostDetailTemp);
			model.put("data", list);
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
	 * 
	 * 临时维修费用实体查询
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getRepairCostDetailTempByIdMob.json")
	public @ResponseBody JSONMap<String, Object> getRepairCostDetailTempByIdMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp,@FormModel SysCodeInfo sysCodeInfo, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<RepairCostDetailTemp> list = repairOrderInfoService.queryRepairCostDetailTempList(repairCostDetailTemp);
			sysCodeInfo.setCode_group_value(PmConstant.TRANSPORT_SPEC);
			List<SysCodeInfo> transportSpecGrouplist = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			model.put("repairCostDetailTemp", (list.size()>0)?list.get(0):"");
			model.put("transportSpecGrouplist", transportSpecGrouplist);
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
	 * 
	 * 临时维修费用新增
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveRepairCostDetailTempMob.json")
	public @ResponseBody JSONMap<String, Object> saveRepairCostDetailTempMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			repairCostDetailTemp.setCreate_id(processor.getCurrentUserId());
			repairCostDetailTemp.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			int count = repairOrderInfoService.saveRepairCostDetailTemp(repairCostDetailTemp);
			if(count>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 
	 * 临时维修费用修改
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateRepairCostDetailTempMob.json")
	public @ResponseBody JSONMap<String, Object> updateRepairCostDetailTempMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			repairCostDetailTemp.setUpdate_id(processor.getCurrentUserId());
			repairCostDetailTemp.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			int count = repairOrderInfoService.updateRepairCostDetailTemp(repairCostDetailTemp);
			if(count>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 
	 * 临时维修费用删除
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteRepairCostDetailTempMob.json")
	public @ResponseBody JSONMap<String, Object> deleteRepairCostDetailTempMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			int count = repairOrderInfoService.deleteRepairCostDetailTemp(repairCostDetailTemp);
			if(count>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 手机版维修结果新增
	 * 
	 * @param approveInfo
	 * @param repairOrderInfo
	 * @param repairResultInfo
	 * @param repairCostDetail
	 * @param repairMachineDetail
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addRepairResultInfoForMob.json")
	public @ResponseBody JSONMap<String, Object> addRepairResultInfoForMob(
			@FormModel ApproveInfo approveInfo,
			@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel ("repairResultInfo")RepairResultInfo repairResultInfo,
			@FormModel RepairCostDetailTemp repairCostDetailTemp,
			@FormModel ("repairCostDetailList")List<RepairCostDetail> repairCostDetailList,
			@FormModel ("repairMachineDetailList")List<RepairMachineDetail> repairMachineDetailList,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(list.size()>0){
				repairResultInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				repairResultInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				repairResultInfo.setFinish_date(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				repairResultInfo.setCreate_id(processor.getCurrentUserId());
				repairResultInfo.setUpdate_id(processor.getCurrentUserId());
				repairResultInfo.setRepair_order_num(list.get(0).getRepair_order_num());
				String repair_result_id = repairOrderInfoService.saveRepairResultInfo(repairResultInfo);
				List<RepairCostDetailTemp> RepairCostDetailTempList = repairOrderInfoService.queryRepairCostDetailTempList(repairCostDetailTemp);
				RepairCostDetail RepairCostDetail = new RepairCostDetail();
				for(RepairCostDetailTemp item:RepairCostDetailTempList){
					RepairCostDetail.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					RepairCostDetail.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					RepairCostDetail.setCreate_id(processor.getCurrentUserId());
					RepairCostDetail.setUpdate_id(processor.getCurrentUserId());
					RepairCostDetail.setRepair_result_id(repair_result_id);
					RepairCostDetail.setAmount(item.getAmount());
					RepairCostDetail.setUnit_price(item.getUnit_price());
					RepairCostDetail.setCost_detail(item.getCost_detail());
					RepairCostDetail.setTotal_price(item.getTotal_price());
					RepairCostDetail.setRemark(item.getRemark());
					RepairCostDetail.setCost_type(item.getCost_type());
					repairOrderInfoService.saveRepairCostDetail(RepairCostDetail);
				}
				for(RepairMachineDetail item:repairMachineDetailList){
					item.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					item.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					item.setCreate_id(processor.getCurrentUserId());
					item.setUpdate_id(processor.getCurrentUserId());
					item.setRepair_result_id(repair_result_id);
					repairOrderInfoService.saveRepairMachineDetail(item);
				}
				
				RepairOrderInfo entity = list.get(0);
				entity.setApproval_state(PmConstant.CONFIRM_WAIT);
				entity.setRepair_date(DateUtil.getNow(DateUtil.Y_M_D));
				entity.setApproval_step(PmConstant.CONFIRM);
				entity.setUpdate_id(processor.getCurrentUserId());
				entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setRepair_order_num(list.get(0).getRepair_order_num());
				approveInfo.setApprove_step(PmConstant.CONFIRM);
				List<ApproveInfo> approList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
				if(approList.size()>0){
					entity.setDeal_user_id(approList.get(0).getApprove_user_id());
				}
				repairOrderInfoService.updateRepairOrderInfo(entity);
				
				RepairCostDetailTemp repairCostDetailTempEntity = new RepairCostDetailTemp();
				List<RepairCostDetailTemp> tempList = repairOrderInfoService.queryRepairCostDetailTempList(repairCostDetailTempEntity);
				for(RepairCostDetailTemp item:tempList){
					repairOrderInfoService.deleteRepairCostDetailTemp(item);
				}
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			} else {
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}

		return model;
	}
	
	/**
	 * 手机端维修单新增
	 * 
	 * @param repairOrderInfo
	 * @param approveInfoList
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addRepairOrderInfoDataForMob.json")
	public @ResponseBody JSONMap<String, Object> addRepairOrderInfoDataForMob(
			@FormModel RepairOrderInfo repairOrderInfo,
			@FormModel SysUserInfo sysUserInfo,
			@FormModel ("approveInfoList") List<ApproveInfo> approveInfoList, @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			repairOrderInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			repairOrderInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			repairOrderInfo.setCreate_id(processor.getCurrentUserId());
			repairOrderInfo.setUpdate_id(processor.getCurrentUserId());
			repairOrderInfo.setApproval_state(PmConstant.APPROVE_WAIT);
			repairOrderInfo.setApproval_step(PmConstant.APPROVE);
			repairOrderInfo.setPay_yn(PmConstant.STATE_N);
			String str = repairOrderInfoService.saveRepairOrderInfo(repairOrderInfo);
			
			sysUserInfo.setPosition_id(PmConstant.FACTORY_MANAGER);
			List<SysUserInfo> factoryManagerInfo = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setPosition_id(PmConstant.SECTION_CHIEF);
			sysUserInfo.setDept_code(processor.getDept());
			List<SysUserInfo> approveauaser1 = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			sysUserInfo.setDept_code(PmConstant.ADMIN_EQUIP_DEPART);
			List<SysUserInfo> approveauaser2 = sysUserInfoService.querySysUserInfoList(sysUserInfo);
			
			for(int i=0;i<6;i++){
				ApproveInfo entity = new ApproveInfo();
				if(i==0 || i==4){
					entity.setApprove_user_id(processor.getCurrentUserId());
					approveInfoList.add(i,entity);
				}else if(i==1){
					entity.setApprove_user_id((approveauaser1.size()>0)?approveauaser1.get(0).getUser_num():"");
					approveInfoList.add(i,entity);
				}else if(i==2){
					entity.setApprove_user_id((approveauaser2.size()>0)?approveauaser2.get(0).getUser_num():"");
					approveInfoList.add(i,entity);
				}else if(i==3 || i==5){
					entity.setApprove_user_id((factoryManagerInfo.size()>0)?factoryManagerInfo.get(0).getUser_num():"");
					approveInfoList.add(i,entity);
				}
			}
			int count=1;
			for(ApproveInfo approveInfo:approveInfoList){
				approveInfo.setRepair_order_num(str);
				approveInfo.setApprove_step(count+"");
				if(count == 1){
					approveInfo.setApprove_yn(PmConstant.STATE_Y);
					approveInfo.setApproval_state(PmConstant.APPROVE_STATE);
				}else if(count == 2){
					repairOrderInfo.setDeal_user_id(approveInfo.getApprove_user_id());
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}else{
					approveInfo.setApprove_yn(PmConstant.STATE_N);
				}
				approveInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				approveInfo.setCreate_id(processor.getCurrentUserId());
				approveInfo.setUpdate_id(processor.getCurrentUserId());
				repairOrderInfoService.saveApproveInfo(approveInfo);
				count++;
			}
			RepairOrderInfo info = new RepairOrderInfo();
			info.setRepair_order_num(str);
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(info);
			if(list.size()>0){
				info = list.get(0);
				info.setDeal_user_id(repairOrderInfo.getDeal_user_id());
				repairOrderInfoService.updateRepairOrderInfo(info);
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
	 * 手机端临时维修费详情查询
	 * 
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryCostDetailListForMob.json")
	public @ResponseBody JSONMap<String, Object> queryCostDetailListForMob(
		   @FormModel RepairCostDetailTemp repairCostDetailTemp , @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<RepairCostDetailTemp> list = repairOrderInfoService.queryRepairCostDetailTempList(repairCostDetailTemp);
			model.put("repairCostDetailTempList", list);
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
	 * 维修结果查询
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMachineInfoListPopForRepairMob.json")
	public @ResponseBody JSONMap<String, Object> queryMachineInfoListPopForRepairMob(
			@FormModel MachineInfo machineInfo,@FormModel ("ids")String ids,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<MachineInfo> machineInfoList = repairOrderInfoService.queryMachineInfoListPopForRepairMob(machineInfo,ids);
			model.put("machineInfoList", machineInfoList);
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
	 * 临时材料费删除
	 * 
	 * @param repairOrderInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteRepairCostDetailTempForRepairMob.json")
	public @ResponseBody JSONMap<String, Object> deleteRepairCostDetailTempForRepairMob(
			@FormModel RepairCostDetailTemp repairCostDetailTemp,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			int cnt = repairOrderInfoService.deleteRepairCostDetailTemp(repairCostDetailTemp);
			if(cnt>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 
	 * 维修单导出
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/repairExport.json")
	public @ResponseBody JSONMap<String, Object> repairExport(
			@FormModel RepairOrderInfo repairOrderInfo, @FormModel Processor processor,HttpServletRequest request) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			
			//审批流信息取得
			Map map审批流 = this.GetApproveline(repairOrderInfo);
			//维修单信息取得
			Map map维修单 = this.GetReapirOrder(repairOrderInfo);
			//维修报价信息取得
			List list报价 = this.GetReapirOffer((String)map维修单.get("repair_result_id"));
			list报价.add(0, map维修单);
			//维修结果信息取得
			List list维修结果 = this.GetReapirResult((String)map维修单.get("repair_result_id"));
			//维修前期报价信息取得 TODO
			List list前期报价 = this.GetBeforeReapirOffer((String)map维修单.get("offer_id"));//新增模板
			list前期报价.add(0, map维修单);
			
			List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			//人工费信息取得
			List list人工费 = this.GetPersonAccountResult(repairOrderInfoList,request);
			Double personmoney = Double.valueOf("0");
			if(list人工费.size()>0){
				for(int i=0;i<list人工费.size();i++){
					Map map = (Map) list人工费.get(i);
					Double time = Double.valueOf(map.get("人工时").toString());
					Double price = Double.valueOf(map.get("单价").toString());
					personmoney+= time*price;
				}
			}
			
			//材料费信息取得
			List list材料费 = this.GetItemAccountResult(repairOrderInfoList,request);
			Double materialmoney = Double.valueOf("0");
			if(list材料费.size()>0){
				Map map = (Map) list材料费.get(0);
				materialmoney = Double.valueOf(map.get("单价").toString());
			}
			
			RepairPrint repairPrint = new RepairPrint(request);
			String outPutUrl = repairPrint.mdPrint(map审批流, map维修单, list报价, list维修结果,list前期报价,personmoney,materialmoney);
			
			model.put("filePath", outPutUrl);
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
	 * 审批流信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private Map<String,String> GetApproveline(RepairOrderInfo repairOrderInfo){
		//审批流信息取得
		List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
		Map map审批流 = new HashMap();
		if(repairOrderInfoList.size() > 0){
			//起草
			String lcCreate_time = repairOrderInfoList.get(0).getCreate_time().substring(0,10);
			//初审
			String lcApprove_date = repairOrderInfoList.get(0).getApproval_date();
			//分配
			String lcDistribute_date = repairOrderInfoList.get(0).getDistribute_date();
			//报价
			String lcOffer_date = repairOrderInfoList.get(0).getOffer_date();
			//报价审批
			String lcFactory_manager_approval_date = repairOrderInfoList.get(0).getFactory_manager_approval_date();
			//维修
			String lcRepair_date = repairOrderInfoList.get(0).getRepair_date();
			//验证
			String lcConfirm_date = repairOrderInfoList.get(0).getConfirm_date();
			//Approval
			String lcFactory_manager_confirm_date = repairOrderInfoList.get(0).getFactory_manager_confirm_date();
			//结算
			String lcAccount_date = repairOrderInfoList.get(0).getAccount_date();

			//报价and维修人
			String repairName = repairOrderInfoList.get(0).getRepair_user_nm();
			map审批流.put("报价time", lcOffer_date);
			map审批流.put("维修time", lcRepair_date);
			
			map审批流.put("报价", repairName);
			map审批流.put("维修", repairName);
			
			ApproveInfo approveInfo = new ApproveInfo();
			approveInfo.setRepair_order_num(repairOrderInfoList.get(0).getRepair_order_num());
			List<ApproveInfo> approveList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);

			for(int i=0; i < approveList.size(); i++){
				approveInfo = approveList.get(i);
				String lcApprove_user_nm = approveInfo.getApprove_user_nm();
				//申请
				if(PmConstant.DRAFT.equals(approveInfo.getApprove_step())){
					map审批流.put("申请", lcApprove_user_nm);
					map审批流.put("申请time", lcCreate_time);
				}
				//初审
				else if(PmConstant.APPROVE.equals(approveInfo.getApprove_step())){
					map审批流.put("初审", lcApprove_user_nm);
					map审批流.put("初审time", lcApprove_date);
				}
				//分配
				else if(PmConstant.DISTRIBUT.equals(approveInfo.getApprove_step())){
					map审批流.put("分配", lcApprove_user_nm);
					map审批流.put("分配time", lcDistribute_date);
				}
				//报价审批
				else if(PmConstant.FACTORY_MANAGER_APPROVE.equals(approveInfo.getApprove_step())){
					map审批流.put("报价审批", lcApprove_user_nm);
					map审批流.put("报价审批time", lcFactory_manager_approval_date);
				}
				//验证
				else if(PmConstant.CONFIRM.equals(approveInfo.getApprove_step())){
					map审批流.put("验证", lcApprove_user_nm);
					map审批流.put("验证time", lcConfirm_date);
				}
				//Approval
				else if(PmConstant.FACTORY_MANAGER_CONFIRM.equals(approveInfo.getApprove_step())){
					map审批流.put("Approval", lcApprove_user_nm);
					map审批流.put("Approvaltime", lcFactory_manager_confirm_date);
				}
			}
		}
		
		return map审批流;
	}

	/**
	 * 维修单信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private Map<String,String> GetReapirOrder(RepairOrderInfo repairOrderInfo){
		Map map维修单 = new HashMap();
		if(repairOrderInfo != null){
			List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(repairOrderInfoList.size()>0){
				RepairOrderInfo repairOrderInfo2 = repairOrderInfoList.get(0);
				map维修单.put("申请编号", repairOrderInfo2.getRepair_order_num());
				map维修单.put("申请部门", repairOrderInfo2.getDept_nm());
				map维修单.put("维修地点", repairOrderInfo2.getRepair_place_nm());
				map维修单.put("计划完成日期", repairOrderInfo2.getRequire_time());
				map维修单.put("问题描述", repairOrderInfo2.getRepair_detail());
				map维修单.put("备注", repairOrderInfo2.getRemark());
				
				RepairResultInfo repairResultInfo = new RepairResultInfo();
				OfferInfo offerInfo = new OfferInfo();
				repairResultInfo.setRepair_order_num(repairOrderInfo2.getRepair_order_num());
				offerInfo.setRepair_order_num(repairOrderInfo2.getRepair_order_num());
				repairResultInfo = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
				List<OfferInfo> offerList= repairOrderInfoService.queryOfferInfoList(offerInfo);
				if(offerList.size()>0){
					offerInfo = offerList.get(0);
				}
				if(repairResultInfo!=null){
					map维修单.put("一次性费用", repairResultInfo.getOne_charge());
					map维修单.put("维修结果", "已完成");
					map维修单.put("种类", "人工费");
					map维修单.put("描述", offerInfo.getRemark());
					map维修单.put("工时", repairResultInfo.getTask_time());
					map维修单.put("单价", repairResultInfo.getUnit_price());
					map维修单.put("维修税金",repairResultInfo.getTax());
					map维修单.put("repair_result_id", repairResultInfo.getRepair_result_id());
					map维修单.put("offer_id", offerInfo.getOffer_id());
					map维修单.put("报价一次性费用", offerInfo.getOne_charge());
					map维修单.put("税金", offerInfo.getTax());
					map维修单.put("报价种类", "人工费");
					map维修单.put("报价描述", offerInfo.getRemark());
					map维修单.put("报价工时", offerInfo.getEstimate_time());
					map维修单.put("报价单价", offerInfo.getUser_price());
				}else{
					map维修单.put("一次性费用", "0");
					map维修单.put("维修结果", "未完成");
					map维修单.put("种类", "");
					map维修单.put("工时", "");
					map维修单.put("单价", "0");
					map维修单.put("维修税金","0");
					map维修单.put("offer_id", offerInfo.getOffer_id());
					map维修单.put("报价一次性费用", offerInfo.getOne_charge());
					map维修单.put("税金", offerInfo.getTax());
					map维修单.put("报价种类", "");
					map维修单.put("报价工时", "");
					map维修单.put("报价单价", "0");
				}
				
			}
		}
		return map维修单;
	}
	
	/**
	 * 报价 信息取得
	 * @param repair_result_id
	 * @return
	 */
	private List GetReapirOffer(String repair_result_id){
		List list报价内容 = new ArrayList();
		if(!"".equals(repair_result_id) && repair_result_id !=null){
			RepairCostDetail repairCostDetail  = new RepairCostDetail();
			repairCostDetail.setRepair_result_id(repair_result_id);
			//报价信息
			List<RepairCostDetail> repairCostDetailList = repairOrderInfoService.queryRepairCostDetailList(repairCostDetail);
			
			for(int i = 0; i < repairCostDetailList.size(); i++){
				Map map报价内容 = new HashMap();
				repairCostDetail  = repairCostDetailList.get(i);
				map报价内容.put("种类", repairCostDetail.getCost_nm());
				map报价内容.put("描述", repairCostDetail.getCost_detail());
				map报价内容.put("工时", repairCostDetail.getAmount());
				map报价内容.put("单价", repairCostDetail.getUnit_price());
				list报价内容.add(map报价内容);
			}
		}
		return list报价内容;
	}
	
	/**
	 * 维修前期报价 信息取得
	 * @param offer_id
	 * @return
	 */
	private List GetBeforeReapirOffer(String offer_id){
		List list前期报价 = new ArrayList();
		if(!"".equals(offer_id) && offer_id !=null){
			OfferDetail offerDetail  = new OfferDetail();
			offerDetail.setOffer_id(offer_id);
			//报价信息
			List<OfferInfoDTO> offerDetailList = repairOrderInfoService.queryOfferDetailListNoPage(offerDetail);
			OfferInfoDTO entity = new OfferInfoDTO();
			for(int i = 0; i < offerDetailList.size(); i++){
				Map map前期报价 = new HashMap();
				entity  = offerDetailList.get(i);
				map前期报价.put("报价种类", entity.getCost_nm());
				map前期报价.put("报价描述", entity.getCost_detail());
				map前期报价.put("报价工时", entity.getAmount());
				map前期报价.put("报价单价", entity.getUnit_price());
				list前期报价.add(map前期报价);
			}
		}
		return list前期报价;
	}
	
	/**
	 * 维修结果 信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private List GetReapirResult(String repair_result_id){
		List list维修结果 = new ArrayList();
		if(!"".equals(repair_result_id) && repair_result_id !=null){
			RepairMachineDetail repairMachineDetail  = new RepairMachineDetail();
			repairMachineDetail.setRepair_result_id(repair_result_id);
			//维修结果信息
			List<RepairMachineDetail> repairMachineDetailList = repairOrderInfoService.queryRepairMachineDetailList(repairMachineDetail);
			
			for(int i = 0; i < repairMachineDetailList.size(); i++){
				Map map维修结果 = new HashMap();
				repairMachineDetail  = repairMachineDetailList.get(i);
				String machine_species__nm = (repairMachineDetail.getMachine_species__nm()==null)?"":repairMachineDetail.getMachine_species__nm();
				String machine_type_nm = (repairMachineDetail.getMachine_type_nm()==null)?"":repairMachineDetail.getMachine_type_nm();
				String mepair_detail_nm = (repairMachineDetail.getRepair_detail_nm()==null)?"":repairMachineDetail.getRepair_detail_nm();
				map维修结果.put("设备名称", (repairMachineDetail.getMachine_nm()==null)?"":repairMachineDetail.getMachine_nm());
				map维修结果.put("停机时间", (repairMachineDetail.getDown_hour()==null)?"":repairMachineDetail.getDown_hour());
				map维修结果.put("维修内容", machine_species__nm+">="+machine_type_nm+">="+mepair_detail_nm);
				list维修结果.add(map维修结果);
			}
		}
		return list维修结果;
	}
	
	/**
	 * 
	 * 结算单导出
	 * @param repairCostDetailTemp
	 * @param processor
	 * @return
	 */
	@RequestMapping("/accountExport.json")
	public @ResponseBody JSONMap<String, Object> accountExport(
			@FormModel RepairOrderInfoDTO repairOrderInfoDTO,@FormModel RepairOrderInfo repairOrderInfo, @FormModel Processor processor,HttpServletRequest request) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			List<RepairOrderInfo> repairOrderInfoList = repairOrderInfoService.queryRepairOrderInfoList4(repairOrderInfoDTO);
			//人工费信息取得
			List list人工费 = this.GetPersonAccountResult(repairOrderInfoList,request);
			
			//增加固定人力费和餐补
			List list人工费1 = this.GetHumenCost(list人工费,repairOrderInfoDTO.getStart_dt().substring(0,7),repairOrderInfoDTO.getEnd_dt().substring(0,7));
		
			//材料费信息取得
			List list材料费 = this.GetItemAccountResult(repairOrderInfoList,request);
			
			//增加机械费
			List list材料费1 = this.GetMachinCost(list材料费,repairOrderInfoDTO.getStart_dt().substring(0,7),repairOrderInfoDTO.getEnd_dt().substring(0,7));
			 
			//信息取得
			Map map维修费用明细 = new HashMap();
			map维修费用明细.put("开始日期", repairOrderInfoDTO.getStart_dt().substring(0,10));
			map维修费用明细.put("结束日期", repairOrderInfoDTO.getEnd_dt().substring(0,10));
			map维修费用明细.put("供应商", PmConstant.SUPPLIER_NAME);
			RepairAccountPrint feiyongPrint = new RepairAccountPrint(request);
			String filePath = feiyongPrint.mdPrint(map维修费用明细, list人工费1, list材料费1);
			
			model.put("filePath", filePath);
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
	 * 固定人力费 餐补  信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private List GetHumenCost(List list人工费,String start_dt,String end_dt){
		//一个月期间内
		WorkOutInfo workOutInfo =new WorkOutInfo();

		List<WorkOutInfo> list = workoutInfoService.getWorkOutInfoByYmList(start_dt,end_dt);
		int count = list.size();
		
		String id_ym="";
		if (start_dt.equals(end_dt)){
			id_ym = start_dt.substring(5,7)+"月份"; 
			  if(count == 1){
				  workOutInfo = workoutInfoService.getWorkoutInfoByYm(start_dt, end_dt);
			  }else if(count > 1){
				  workOutInfo = workoutInfoService.getWorkoutInfoByYmSum(start_dt, end_dt);
			  }
		}else{
			  workOutInfo = workoutInfoService.getWorkoutInfoByYmSum(start_dt, end_dt);
			  id_ym = start_dt.substring(5,7)+"-"+end_dt.substring(5,7)+"月份";
		}
		
		//结算时间
		String pay_time ="";
		if(!"".equals(workOutInfo.getPay_time()) && workOutInfo.getWork_hour()!=null){
			pay_time=workOutInfo.getPay_time().toString().substring(0, 10).replace("-", "/");
		}
		//固定人力费
			if(!"".equals(workOutInfo.getWork_hour()) && workOutInfo.getWork_hour()!=null){
				double HumanCost=0;
				
			   HumanCost = Double.parseDouble(workOutInfo.getWork_hour()) * 8 + Double.parseDouble(workOutInfo.getOvertime_hour()) ;
			  
				Map 固定人力费 = new HashMap();
				固定人力费.put("全项完成日期",id_ym);
				固定人力费.put("人工时",String.valueOf(HumanCost));
				固定人力费.put("单价",PmConstant.USER_PRICE_WITH_TAX);
				固定人力费.put("项目名称", "固定人力费");
				固定人力费.put("部门", "");
				固定人力费.put("备注", "");
				固定人力费.put("结算日期",pay_time);
				
				list人工费.add(固定人力费);
			}
			//餐补
			if(!"".equals(workOutInfo.getMeal_hour()) && workOutInfo.getMeal_hour()!=null){

				Map 餐补 = new HashMap();
				餐补.put("全项完成日期",id_ym);
				餐补.put("人工时",workOutInfo.getMeal_hour());
				餐补.put("单价",PmConstant.MEAL_PRICE_WITH_TAX);
				餐补.put("项目名称", "餐补");
				餐补.put("部门", "");
				餐补.put("备注", "");
				餐补.put("结算日期",pay_time);
				list人工费.add(餐补);
			}
		 
		return list人工费;
	}
	
	/**
	 * 机械费  信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private List GetMachinCost(List list材料费,String start_dt,String end_dt){
		//机械费信息
		WorkOutInfo workOutInfo =new WorkOutInfo();
		String id_ym="";
		String pay_time ="";//结算时间
		List<WorkOutInfo> workOutInfoList = workoutInfoService.getWorkOutInfoByYmList(start_dt,end_dt);
		int count = workOutInfoList.size();
		
		if(count == 1){
			if (start_dt.equals(end_dt)){
				id_ym = start_dt.substring(5,7)+"月份";
			}else{
				id_ym = start_dt.substring(5,7)+"-"+end_dt.substring(5,7)+"月份";
			}
			
			workOutInfo = workoutInfoService.getWorkoutInfoByYm(start_dt, end_dt);
			 
			if(!"".equals(workOutInfo.getPay_time()) && workOutInfo.getWork_hour()!=null){
				pay_time=workOutInfo.getPay_time().toString().substring(0, 10).replace("-", "/");
			}
			
			//材料费信息
			if(null != workOutInfo && !"".equals(workOutInfo.getWork_out_id()) && workOutInfo.getWork_out_id()!=null){
				List<RepairMaterialCostDetail> repairMaterialList =workoutInfoService.getRepairCostDetailInfoById(workOutInfo.getWork_out_id(),"");
				//RepairMaterialCostDetail repairMaterialCostDetail=new RepairMaterialCostDetail();
				for(int i = 0; i < repairMaterialList.size(); i++){
					
					
					Map 材料费 = new HashMap();
					//repairMaterialCostDetail = repairMaterialList.get(i);
					材料费.put("全项完成日期",repairMaterialList.get(i).getConfirm_date());
					材料费.put("费用种类","材料费");
					材料费.put("数量","1");
					材料费.put("单价",repairMaterialList.get(i).getAmount());
					材料费.put("事由",repairMaterialList.get(i).getMaterial_cost_detail());
					材料费.put("部门",repairMaterialList.get(i).getDept_nm() );
					材料费.put("备注",repairMaterialList.get(i).getRemark());
					材料费.put("结算日期",pay_time);
					list材料费.add(材料费);
				}
			}
		}else if(count > 1){
			
		
			workOutInfo = workoutInfoService.getWorkoutInfoByYmSum(start_dt, end_dt);
	
			//机械费累加
			if (start_dt.equals(end_dt)){
				id_ym = start_dt.substring(5,7)+"月份";
			}else {
				id_ym = start_dt.substring(5,7)+"-"+end_dt.substring(5,7)+"月份";
			}
			    //材料费
				for(int i = 0; i < workOutInfoList.size(); i++){
					
					//结算时间
					if(!"".equals(workOutInfoList.get(i).getPay_time()) && workOutInfoList.get(i).getPay_time()!=null){
						pay_time=workOutInfoList.get(i).getPay_time().toString().substring(0, 10).replace("-", "/");
					}
					//材料费信息
					if(!"".equals(workOutInfoList.get(i).getWork_out_id()) && workOutInfoList.get(i).getWork_out_id()!=null){
						List<RepairMaterialCostDetail> repairMaterialList =workoutInfoService.getRepairCostDetailInfoById(workOutInfoList.get(i).getWork_out_id(),"");
						for(int j = 0; j < repairMaterialList.size(); j++){
							Map 材料费 = new HashMap();
							材料费.put("全项完成日期",repairMaterialList.get(j).getConfirm_date());
							材料费.put("费用种类","材料费");
							材料费.put("数量","1");
							材料费.put("单价",repairMaterialList.get(j).getAmount());
							材料费.put("事由",repairMaterialList.get(j).getMaterial_cost_detail());
							材料费.put("部门",repairMaterialList.get(j).getDept_nm() );
							材料费.put("备注",repairMaterialList.get(j).getRemark());
							材料费.put("结算日期",pay_time);
							list材料费.add(材料费);
						}
					}
				}
				
		}else {
			return list材料费; 
		}
		
		//结算时间
		if(!"".equals(workOutInfo.getPay_time()) && workOutInfo.getWork_hour()!=null){
			pay_time=workOutInfo.getPay_time().toString().substring(0, 10).replace("-", "/");
		}
		
		if(null != workOutInfo && null != workOutInfo.getMach_cost() && !"".equals(workOutInfo.getMach_cost())){
			
			Map 机械费 = new HashMap();
			机械费.put("全项完成日期",id_ym);
			机械费.put("费用种类","材料费");
			机械费.put("数量","1");
			机械费.put("单价",workOutInfo.getMach_cost());
			机械费.put("事由", "机械费");
			机械费.put("部门", "生产支援科");
			机械费.put("备注", "");
			机械费.put("结算日期",pay_time);
			list材料费.add(机械费);
		}
		return list材料费;
	}
	
	/**
	 * 人工费  信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private List GetPersonAccountResult(List<RepairOrderInfo> list,HttpServletRequest request){
		List list人工服务费明细 = new ArrayList();
		//人工费工时
		Double personTasktimeCount = Double.valueOf("0");
		Double personMealCount = Double.valueOf("0");
		if(list.size()>0){
			RepairOrderInfo repairOrderInfo = new RepairOrderInfo();
			PrintBase printbase = new PrintBase(request);
			for(int i = 0; i < list.size(); i++){
				personTasktimeCount = Double.valueOf("0");
				personMealCount = Double.valueOf("0");
				repairOrderInfo = list.get(i);
				RepairResultInfo repairResultInfo = new RepairResultInfo();
				OfferInfo offerInfo = new OfferInfo();
				RepairCostDetail repairCostDetail  = new RepairCostDetail();
				repairResultInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
				repairResultInfo = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
				List<RepairCostDetail> repairCostList = new ArrayList<>();
				if(repairResultInfo != null){
					repairCostDetail.setRepair_result_id(repairResultInfo.getRepair_result_id());
					repairCostList= repairOrderInfoService.queryRepairCostDetailList(repairCostDetail);
					//2017.2.24 by fish
					if((!"".equals(repairResultInfo.getTask_time()) && repairResultInfo.getTask_time()!=null)
							&& (!"".equals(repairResultInfo.getUnit_price()) && repairResultInfo.getUnit_price()!=null)){
						personTasktimeCount = personTasktimeCount + printbase.tRound(repairResultInfo.getTask_time());
					}
					//2017.2.24 by fish
				}
				offerInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
				List<OfferInfo> offerList= repairOrderInfoService.queryOfferInfoList(offerInfo);
//				if((!"".equals(repairResultInfo.getTask_time()) && repairResultInfo.getTask_time()!=null)
//						&& (!"".equals(repairResultInfo.getUnit_price()) && repairResultInfo.getUnit_price()!=null)){
//					Map map人工服务费明细 = new HashMap();
//					map人工服务费明细.put("完成日期", repairOrderInfo.getFactory_manager_confirm_date());
//					map人工服务费明细.put("人工时", repairResultInfo.getTask_time());
//					map人工服务费明细.put("单价", repairResultInfo.getUnit_price());
//					String lc项目名称 =repairOrderInfo.getRepair_detail();
////					try{
////						lc项目名称=repairOrderInfo.getRepair_detail().substring(0,15);
////					}catch(Exception e){
////						lc项目名称=repairOrderInfo.getRepair_detail();
////					}
//					map人工服务费明细.put("项目名称", lc项目名称);
//					map人工服务费明细.put("部门", repairOrderInfo.getDept_nm());
//					map人工服务费明细.put("备注", "");
//					list人工服务费明细.add(map人工服务费明细);
//				}
				//2017.2.24 by fish
				/*if((!"".equals(repairResultInfo.getTask_time()) && repairResultInfo.getTask_time()!=null)
						&& (!"".equals(repairResultInfo.getUnit_price()) && repairResultInfo.getUnit_price()!=null)){
					personTasktimeCount = personTasktimeCount + printbase.tRound(repairResultInfo.getTask_time());
				}*/
				//2017.2.24 by fish
//				if(repairCostList.size()>0){
//					for(int j = 0; j < repairCostList.size(); j++){
//						repairCostDetail = repairCostList.get(j);
//						if(PmConstant.PERSON_EXPENSES_TYPE.equals(repairCostDetail.getCost_type())){
//							Map map人工费明细 = new HashMap();
//							map人工费明细.put("完成日期", repairOrderInfo.getFactory_manager_confirm_date());
//							map人工费明细.put("人工时", repairCostDetail.getAmount());
//							map人工费明细.put("单价", repairCostDetail.getUnit_price());
//							map人工费明细.put("项目名称", repairCostDetail.getCost_detail());
//							map人工费明细.put("部门", repairOrderInfo.getDept_nm());
//							map人工费明细.put("备注", repairCostDetail.getRemark());
//							list人工服务费明细.add(map人工费明细);
//						}
//					}
//				}
				if(repairCostList.size()>0){
				for(int j = 0; j < repairCostList.size(); j++){
					repairCostDetail = repairCostList.get(j);
					if(PmConstant.PERSON_EXPENSES_TYPE.equals(repairCostDetail.getCost_type())){
						if(PmConstant.MEAL_SUPPLY_EXPENSES.equals(repairCostDetail.getSpec())){
							personMealCount = personMealCount + printbase.tRound(repairCostDetail.getAmount());
						}else{
							personTasktimeCount = personTasktimeCount + printbase.tRound(repairCostDetail.getAmount());
						}
					}
				}
				if(personMealCount != 0){
					Map map人工费明细 = new HashMap();
					map人工费明细.put("维修单号", repairOrderInfo.getRepair_order_num());
					if(repairOrderInfo.getFactory_manager_confirm_date()!=null){ //2017.2.24 by fish
						map人工费明细.put("全项完成日期", repairOrderInfo.getFactory_manager_confirm_date().substring(0,10).replace("-", "/"));
					}
					if(repairOrderInfo.getAccount_date()!=null){ //2017.2.24 by fish
						map人工费明细.put("结算日期", repairOrderInfo.getAccount_date().substring(0,10).replace("-", "/"));
					}
					map人工费明细.put("计划完成日期", repairOrderInfo.getRequire_time());
					map人工费明细.put("人工时", String.valueOf(personMealCount));
					map人工费明细.put("单价",PmConstant.MEAL_PRICE_WITH_TAX);
					map人工费明细.put("项目名称", repairOrderInfo.getRepair_detail());
					map人工费明细.put("部门", repairOrderInfo.getDept_nm());
					map人工费明细.put("备注", "餐补");
					list人工服务费明细.add(map人工费明细);
				}
				if(personTasktimeCount != 0){
					Map map人工费明细 = new HashMap();
					map人工费明细.put("维修单号", repairOrderInfo.getRepair_order_num());
					if(repairOrderInfo.getFactory_manager_confirm_date()!=null){ //2017.2.24 by fish
					map人工费明细.put("全项完成日期", repairOrderInfo.getFactory_manager_confirm_date().substring(0,10).replace("-", "/"));
					}
					if(repairOrderInfo.getAccount_date()!=null){ //2017.2.24 by fish
					map人工费明细.put("结算日期", repairOrderInfo.getAccount_date().substring(0,10).replace("-", "/"));
					}
					map人工费明细.put("计划完成日期", repairOrderInfo.getRequire_time());
					map人工费明细.put("人工时", String.valueOf(personTasktimeCount));
					map人工费明细.put("单价",PmConstant.USER_PRICE_WITH_TAX);
					map人工费明细.put("项目名称", repairOrderInfo.getRepair_detail());
					map人工费明细.put("部门", repairOrderInfo.getDept_nm());
					map人工费明细.put("备注", "人工费");
					list人工服务费明细.add(map人工费明细);
				}
			}
		}
	}
		return list人工服务费明细;
	}
	
	/**
	 * 材料费  信息取得
	 * @param repairOrderInfo
	 * @return
	 */
	private List GetItemAccountResult(List<RepairOrderInfo> list,HttpServletRequest request){
		List list维修材料费 = new ArrayList();
		//税金
		Double repairtaxCount = Double.valueOf("0");
		if(list.size()>0){
			RepairOrderInfo repairOrderInfo = new RepairOrderInfo();
			PrintBase printbase = new PrintBase(request);
			for(int i = 0; i < list.size(); i++){
				repairOrderInfo = list.get(i);
				RepairResultInfo repairResultInfo = new RepairResultInfo();
				RepairCostDetail repairCostDetail  = new RepairCostDetail();
				OfferInfo OfferInfo  = new OfferInfo();
				repairResultInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
				repairResultInfo = repairOrderInfoService.getRepairResultInfoById(repairResultInfo);
				OfferInfo.setRepair_order_num(repairOrderInfo.getRepair_order_num());
				List<OfferInfo> offerList = repairOrderInfoService.queryOfferInfoList(OfferInfo);
				if(offerList.size()>0){
					OfferInfo = offerList.get(0);
				}
				List<RepairCostDetail> repairCostList = new ArrayList<RepairCostDetail>();
				if(repairResultInfo!=null){ //2017.2.24 by fish
					repairCostDetail.setRepair_result_id(repairResultInfo.getRepair_result_id());
					repairCostList= repairOrderInfoService.queryRepairCostDetailList(repairCostDetail);
				}
				if(repairCostList.size()>0){
//					for(int j = 0; j < repairCostList.size(); j++){
//						repairCostDetail = repairCostList.get(j);
//						if(!PmConstant.PERSON_EXPENSES_TYPE.equals(repairCostDetail.getCost_type())){
//							Map map维修材料费 = new HashMap();
//							map维修材料费.put("完成日期", repairOrderInfo.getFactory_manager_confirm_date());
//							map维修材料费.put("费用种类", repairCostDetail.getCost_nm());
//							map维修材料费.put("金额", "");
//							map维修材料费.put("数量", repairCostDetail.getAmount());
//							map维修材料费.put("单价", repairCostDetail.getUnit_price());
//							map维修材料费.put("事由",repairOrderInfo.getRepair_detail());
//							map维修材料费.put("部门", repairOrderInfo.getDept_nm());
//							map维修材料费.put("备注",  "");
//							list维修材料费.add(map维修材料费);
//						}
//					}
					Double allcount = Double.valueOf("0");//维修报价时材料费分类是合同固定运费的情况
					
					for(int j = 0; j < repairCostList.size(); j++){
						repairCostDetail = repairCostList.get(j);
						if(!PmConstant.PERSON_EXPENSES_TYPE.equals(repairCostDetail.getCost_type())){
							allcount = allcount + printbase.tRound(repairCostDetail.getAmount())*printbase.tRound(repairCostDetail.getUnit_price());
						}
					}
					if(repairResultInfo!=null){ //2017.2.24 by fish
						allcount = allcount + printbase.tRound(repairResultInfo.getTax());
					}
					if(allcount != 0){
						Map map维修材料费 = new HashMap();
						map维修材料费.put("维修单号", repairOrderInfo.getRepair_order_num());
						if(repairOrderInfo.getFactory_manager_confirm_date()!=null){ //2017.2.24 by fish
							map维修材料费.put("全项完成日期", repairOrderInfo.getFactory_manager_confirm_date().substring(0,10).replace("-", "/"));
						}
						if(repairOrderInfo.getAccount_date()!=null){ //2017.2.24 by fish
						map维修材料费.put("结算日期", repairOrderInfo.getAccount_date().substring(0,10).replace("-", "/"));
						}
						map维修材料费.put("计划完成日期", repairOrderInfo.getRequire_time());
						map维修材料费.put("费用种类", "材料费");
						map维修材料费.put("金额", "");
						map维修材料费.put("数量", "1");
						map维修材料费.put("单价", String.valueOf(allcount));
						map维修材料费.put("事由",repairOrderInfo.getRepair_detail());
						map维修材料费.put("部门", repairOrderInfo.getDept_nm());
						map维修材料费.put("备注",  "");
						list维修材料费.add(map维修材料费);
					}
				}
//				if(!"".equals(repairResultInfo.getOne_charge()) && repairResultInfo.getOne_charge()!=null){
//					Map map一次性费用 = new HashMap();
//					map一次性费用.put("完成日期", repairOrderInfo.getFactory_manager_confirm_date());
//					map一次性费用.put("费用种类", "一次性费用");
//					map一次性费用.put("金额", repairResultInfo.getOne_charge());
//					map一次性费用.put("数量", "");
//					map一次性费用.put("单价", "");
//					map一次性费用.put("事由",repairOrderInfo.getRepair_detail());
////					map一次性费用.put("事由",(offerList.size()>0)?offerList.get(0).getRemark():"");
//					map一次性费用.put("部门", repairOrderInfo.getDept_nm());
//					map一次性费用.put("备注",  "");
//					list维修材料费.add(map一次性费用);
//				}
				
				
				//税金累积
//				repairtaxCount = repairtaxCount + printbase.tRound(repairResultInfo.getTax());
			}
		}
		
//		if(list维修材料费.size() > 0){
//			((Map)list维修材料费.get(0)).put("维修税金", repairtaxCount);
//		}
		return list维修材料费;
	}
	
	//以下是报价人认为不需要报价直接维修的管理
	/**
	 * 直接维修
	 * 
	 * @param distributionInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateGoToRepair.json")
	public @ResponseBody JSONMap<String, Object> updateGoToRepair(
			@FormModel RepairOrderInfo repairOrderInfo,@FormModel ApproveInfo approveInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<RepairOrderInfo> list = repairOrderInfoService.queryRepairOrderInfo(repairOrderInfo);
			if(list.size()>0){
				RepairOrderInfo entity = list.get(0);
				entity.setApproval_state(PmConstant.REPAIR_WAIT);
				entity.setApproval_step(PmConstant.REAPIR);
				entity.setRepair_type(PmConstant.STATE_N);
				entity.setRepair_user_id(processor.getCurrentUserId());
				entity.setDeal_user_id(processor.getCurrentUserId());
				entity.setUpdate_id(processor.getCurrentUserId());
				entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				int count = repairOrderInfoService.updateRepairOrderInfo(entity);
				if(count>0){
					approveInfo.setRepair_order_num(entity.getRepair_order_num());
					approveInfo.setApprove_step(PmConstant.FACTORY_MANAGER_APPROVE);
					List<ApproveInfo> approList = repairOrderInfoService.queryApproveInfoListNoPage(approveInfo);
					if(approList.size()>0){
						ApproveInfo info = approList.get(0);
						info.setApprove_yn(PmConstant.STATE_Y);
						info.setApproval_state(PmConstant.APPROVE_STATE);
						repairOrderInfoService.updateApproveInfo(info);
					}
				}
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
