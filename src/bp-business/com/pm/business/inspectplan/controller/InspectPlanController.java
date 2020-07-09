package com.pm.business.inspectplan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.inspectplan.service.InspectPlanService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
import com.pm.persistence.inspectplan.dto.InsPlanInfoDTO;
import com.pm.persistence.inspectplan.entity.InsPlanCheckDetail;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.inspectplan.entity.InsPlanMachineInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
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



@Controller
@RequestMapping("/InspectPlanController")
public class InspectPlanController {
	
	@Resource
	private InspectPlanService inspectPlanService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/inspectplan/inspectplan_list";
	}	
	
	/**
	 * 保存巡检计划
	 * @param InsPlanInfo,InsPlanMachineInfo,InsPlanCheckDetailDAO
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveInsMstThreeTable.json")
	public @ResponseBody JSONMap<String,Object> saveInsMstThreeTable(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,
																@FormModel("insMachineIdList") String[] insMachineIdList,
																@FormModel("insCheckIdList") String[] insCheckIdList,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		InsPlanInfoDTO insPlanInfoDTO = new InsPlanInfoDTO();
		insPlanInfoDTO.setUser_id(processor.getCurrentUserId());
		insPlanInfoDTO.setPre_plan_cycle(insPlanInfo.getIns_plan_cycle());
		insPlanInfoDTO.setPre_plan_date(insPlanInfo.getIns_plan_date());
		insPlanInfoDTO.setPre_plan_nm(insPlanInfo.getIns_plan_nm());
		insPlanInfoDTO.setChk_type("test");  //TODO
		insPlanInfoDTO.setPre_machine_id(subStringTOstr(insMachineIdList));
		insPlanInfoDTO.setCheck_project_id(subStringTOstr(insCheckIdList));
		insPlanInfoDTO.setPre_plan_date_end(insPlanInfo.getIns_plan_date_end());
		insPlanInfoDTO.setPre_plan_group("");
		try{					
			inspectPlanService.saveInsMstThreeTable(insPlanInfoDTO);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	public String subStringTOstr(String[] sb){
		String str = "";
		for(int i=0;i<sb.length;i++){
			str += sb[i]+",";
		}
		return str = str.substring(0, str.length()-1);
	}
	/**
	 * delete巡检计划
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteInspectplans.json")
	public @ResponseBody JSONMap<String,Object> deleteInspectplans(@FormModel("plan_id") String plan_id) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			inspectPlanService.deleteInspectplans(plan_id);			
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
	 * 查询提单列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectplanList.json")
	public @ResponseBody JSONMap<String,Object> queryInspectplanList(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<InsPlanInfo> inspectplanList = 
					inspectPlanService.getInsPlanList(insPlanInfo,processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<InsPlanInfo> convert = new UIDataConvert<InsPlanInfo>();
			IConvert ins_plan_cycle = new SqlMapConvertImpl("ins_plan_cycle", "ins_plan_cycle_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.CYCLE_GROUP+"'");
			convert.addConvert(ins_plan_cycle);	
			IConvert planState = new SqlMapConvertImpl("ins_plan_state", "ins_plan_state_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PLAN_STATE+"' ");
			convert.addConvert(planState);
			IConvert machine_species_name = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_name);
			IConvert create_name = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num","");
			convert.addConvert(create_name);
			//--fish 添加操作人
			IConvert update_name = new SqlMapConvertImpl("update_id", "update_name", "sys_user_info", "user_nm","user_num","");
			convert.addConvert(update_name);
			convert.format(inspectplanList);
			model.put("data", inspectplanList);			
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
	 * 查询提单列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectplanListGroupby.json")
	public @ResponseBody JSONMap<String,Object> queryInspectplanListGroupby(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<InsPlanInfo> inspectplanList = 
					inspectPlanService.getInsPlanListGroupby(insPlanInfo,processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<InsPlanInfo> convert = new UIDataConvert<InsPlanInfo>();
			IConvert machine_species_name = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_name);
			convert.format(inspectplanList);
			model.put("data", inspectplanList);			
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
	 * 修改巡检计划
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateInspectplans.json")
	public @ResponseBody JSONMap<String,Object> updateInspectplans(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = inspectPlanService.updateInspectplans(insPlanInfo);
			if(update_flag>0){
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
	 * 巡检计划检查项目详情LIst
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectplanCheckDetialList.json")
	public @ResponseBody JSONMap<String,Object> queryInspectplanCheckDetialList(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
		   //获得plan内容Entity
			InsPlanInfo en=inspectPlanService.queryInsPlansListNo(insPlanInfo).get(0);
			//获得详细checkinfoList
			List<String[]> infolist=inspectPlanService.getInsPlanCheckDetailList(insPlanInfo.getIns_plan_id());			
			model.put("en", en);	
			model.put("data", infolist);	
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
	 * 维修单新增页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPageList.json")
	public @ResponseBody JSONMap<String, Object> queryPageList(@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.CYCLE_GROUP);//计划周期
			List<SysCodeInfo> cycleList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.PLAN_STATE);//计划state
			List<SysCodeInfo> stateList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//排序下拉列表
			List<SysCodeInfo> orderList = sysCodeInfoService.getOrderList(PmConstant.ORDER_INSPECT_PLAN_GROUP);
			model.put("orderList", orderList); 
			model.put("cycleList", cycleList);
			model.put("machinespeciesList", machinespeciesList);
			model.put("stateList", stateList);
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
	 * 准备update 页
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryUpdatePage.json")
	public @ResponseBody JSONMap<String, Object> queryUpdatePage(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String,Object>();
		try {
			//得到planEntity
			insPlanInfo= inspectPlanService.queryInsPlansListNo(insPlanInfo).get(0);
			//得到周期list
			SysCodeInfo sysCodeInfo =new SysCodeInfo();
			sysCodeInfo.setCode_group_value(PmConstant.CYCLE_GROUP);
			List<SysCodeInfo> cycleList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//得到设备list
			List<InsPlanMachineInfo>  MachineInfoList = inspectPlanService.queryMachineViewList(insPlanInfo.getIns_plan_id());
			//得到checkitemList
			List<InsPlanCheckDetail>  checkitemInfoList = inspectPlanService.queryCheckItemViewList(insPlanInfo.getIns_plan_id());
			//得到'修改区分List'
			sysCodeInfo.setCode_group_value(PmConstant.UPDATE_FLAG);
			List<SysCodeInfo> updateflagList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			model.put("insPlanInfo", insPlanInfo);
			model.put("cycleList", cycleList);
			model.put("MachineInfoList", MachineInfoList);
			model.put("checkitemInfoList", checkitemInfoList);			
			model.put("updateflagList", updateflagList);
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
	 * update: first delete then insert
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateInsMstThreeTable.json")
	public @ResponseBody JSONMap<String, Object> updateInsMstThreeTable(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,
			@FormModel("insMachineIdList") String[] insMachineIdList,
			@FormModel("insCheckIdList") String[] insCheckIdList,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			
			String flag=insPlanInfo.getUpdate_flag();
			if(flag!=null&&!"".equals(flag)){
				//如果批量修改 判断是否有计划已经被操作，如果有则返回提示 不能进行批量操作
				List<InsPlanInfo> tempList= new ArrayList<InsPlanInfo>();
				if(flag.equals(PmConstant.UPDATE_FLAG_0)||flag.equals(PmConstant.UPDATE_FLAG_2)){	//批量更新或新增
					InsPlanInfo tempInfo= new InsPlanInfo();	
					tempInfo.setIns_pm_rate("0");
					tempInfo.setIns_plan_group(insPlanInfo.getIns_plan_group());
					tempInfo.setIns_plan_date(insPlanInfo.getIns_plan_date());
					tempList=inspectPlanService.queryplanGroupList(tempInfo);
				}
				if(tempList!=null&&tempList.size()!=0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE,"done");
				}else{
					InsPlanInfoDTO insPlanInfoDTO = new InsPlanInfoDTO();			
					insPlanInfoDTO.setUpdate_flag(insPlanInfo.getUpdate_flag());//单个删除 or 批量删除
					insPlanInfoDTO.setPlan_id(insPlanInfo.getIns_plan_id());//id
					insPlanInfoDTO.setPre_plan_nm(insPlanInfo.getIns_plan_nm());//name
					insPlanInfoDTO.setPre_plan_cycle(insPlanInfo.getIns_plan_cycle());//周期
					insPlanInfoDTO.setPre_plan_date(insPlanInfo.getIns_plan_date());//计划日期
					insPlanInfoDTO.setPre_plan_group(insPlanInfo.getIns_plan_group());//计划组id
					insPlanInfoDTO.setUser_id(processor.getCurrentUserId());
					insPlanInfoDTO.setChk_type("test");  //TODO
					insPlanInfoDTO.setPre_machine_id(subStringTOstr(insMachineIdList));
					insPlanInfoDTO.setCheck_project_id(subStringTOstr(insCheckIdList));	
					insPlanInfoDTO.setPre_plan_date_end(insPlanInfo.getIns_plan_date_end());
					inspectPlanService.updateInsMstThreeTable(insPlanInfoDTO);				
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
	 * 查询提单列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectplanListMob.json")
	public @ResponseBody JSONMap<String,Object> queryInspectplanListMob(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{			
			if("".equals(insPlanInfo.getDate_end())||insPlanInfo.getDate_end()==null){
				insPlanInfo.setDate_end(DateUtil.getNow(DateUtil.Y_M_D));
		    }
			List<InsPlanInfo> inspectplanList = 
					inspectPlanService.getInsPlanListMob(insPlanInfo);			
			model.put("data", inspectplanList);			
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
	 * 查询提单列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectMachineListMob.json")
	public @ResponseBody JSONMap<String,Object> queryInspectMachineListMob(@FormModel("insPlanMachineInfo") InsPlanMachineInfo insPlanMachineInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{			
			//得到planEntity
			InsPlanInfo  insPlanInfo = new InsPlanInfo();
			insPlanInfo.setIns_plan_id(insPlanMachineInfo.getIns_plan_id());
			insPlanInfo= inspectPlanService.queryInsPlansListNo(insPlanInfo).get(0);
			//得到设备list
			//List<InsPlanMachineInfo>  machineInfoList = inspectPlanService.queryMachineViewList(insPlanMachineInfo.getIns_plan_id());
			List<InsPlanMachineInfo>  machineInfoList = inspectPlanService.queryMachineViewListMob(insPlanMachineInfo);
			
			model.put("insPlanInfo", insPlanInfo);	
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
	 * 查询提单列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryInspectCheckItemListMob.json")
	public @ResponseBody JSONMap<String,Object> queryInspectCheckItemListMob(@FormModel("insPlanCheckDetail") InsPlanCheckDetail insPlanCheckDetail,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{	
			//得到设备info
			InsPlanMachineInfo insMachineInfo  = new InsPlanMachineInfo();
			insMachineInfo.setPlan_machine_id(insPlanCheckDetail.getPlan_machine_id());
			insMachineInfo=inspectPlanService.queryMachineViewListMob(insMachineInfo).get(0);
			//得到planEntity
			InsPlanInfo  insPlanInfo = new InsPlanInfo();
			insPlanInfo.setIns_plan_id(insMachineInfo.getIns_plan_id());
			insPlanInfo= inspectPlanService.queryInsPlansListNo(insPlanInfo).get(0);			
			//得到checkitemList
			List<InsPlanCheckDetail>  checkitemInfoList = inspectPlanService.queryCheckItemViewListMob(insPlanCheckDetail);
			model.put("insPlanInfo", insPlanInfo);	
			model.put("insMachineInfo", insMachineInfo);
			model.put("checkitemInfoList", checkitemInfoList);
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
	 * update: first delete then insert
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateInsMstThreeTableMob.json")
	public @ResponseBody JSONMap<String, Object> updateInsMstThreeTableMob(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo,
			@FormModel("insMachineInfo") InsPlanMachineInfo insMachineInfo,
			@FormModel("checkitemList")List<InsPlanCheckDetail> checkitemList,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			insPlanInfo.setOperate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			inspectPlanService.updateInsMstThreeTableMob(insPlanInfo,insMachineInfo,checkitemList,processor);				
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
	 * 批量确认 
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateStateBatch.json")
	public @ResponseBody JSONMap<String,Object> updateStateBatch(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = inspectPlanService.updateStateBatch(insPlanInfo,processor);
			if(update_flag>0){
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
	 * 批量delete
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteBatch.json")
	public @ResponseBody JSONMap<String,Object> deleteBatch(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = inspectPlanService.deleteBatch(insPlanInfo,processor);
			if(update_flag>0){
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
	 * 批量delete前批量验证
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/checkDeleteBatch.json")
	public @ResponseBody JSONMap<String,Object> checkDeleteBatch(@FormModel("insPlanInfo") InsPlanInfo insPlanInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<InsPlanInfo> checktempList=inspectPlanService.queryCheckInsPlansGroupList(insPlanInfo);
			model.put("checktempList", checktempList);	
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