package com.pm.business.preventplan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.preventplan.service.PrePlanInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.preventplan.dto.PrePlanInfoDTO;
import com.pm.persistence.preventplan.entity.PrePlanCheckDetail;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.preventplan.entity.PrePlanMachineInfo;
import com.pm.persistence.preventplan.entity.PrePlanPropCheckDetail;
import com.pm.persistence.preventplan.entity.PrePlanPropMachineInfo;
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

/**
 * 保养计划
 * @author fish
 * @date 2016.11.2
 *
 */
@Controller
@RequestMapping("/PrePlanInfoController")
public class PrePlanInfoController {
	
	@Resource
	private PrePlanInfoService prePlanInfoService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/preplaninfo/preplan_list";
	}
	
	/**
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询设备类别下拉列表
			List<SysCodeInfo> departTypeList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.MACH_TYPE_GROUP);//设备列表group code 
			model.put("departTypeList", departTypeList);
			//计划周期下拉列表
			List<SysCodeInfo> cycleList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.CYCLE_GROUP);//计划周期group code
			model.put("cycleList", cycleList);
			//排序下拉列表
			List<SysCodeInfo> orderList = sysCodeInfoService.getOrderList(PmConstant.ORDER_PER_PLAN_GROUP);
			model.put("orderList", orderList); 
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
	 * 保养列表查询
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			Pagination<PrePlanInfo> prePlanInfoList = prePlanInfoService.queryPrePlanInfoList(prePlanInfo, processor.getPageIndex(),processor.getPageSize());
			UIDataConvert<PrePlanInfo> convert = new UIDataConvert<PrePlanInfo>();
			//列表中周期code name转化
			IConvert planCycle = new SqlMapConvertImpl("pre_plan_cycle", "pre_plan_cycle_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.CYCLE_GROUP+"' ");
			convert.addConvert(planCycle);
			//列表中计划状态的code-name转换
			IConvert planState = new SqlMapConvertImpl("pre_plan_state", "pre_plan_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PLAN_STATE+"' ");
			convert.addConvert(planState);
			//---sunaibo
			IConvert machine_species_name = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_name);
			IConvert create_name = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num","");
			convert.addConvert(create_name);
			IConvert update_name = new SqlMapConvertImpl("update_id", "update_name", "sys_user_info", "user_nm","user_num","");
			convert.addConvert(update_name);
			convert.format(prePlanInfoList);
			model.put("data", prePlanInfoList);
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
	 * 保养列表查询-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataListMList.json")
	public @ResponseBody JSONMap<String,Object> queryDataListMList(@FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			List<PrePlanInfo> prePlanInfoList = prePlanInfoService.queryPrePlanInfoListNoPage(prePlanInfo);
			model.put("data", prePlanInfoList);
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
	 * 设备列表-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMachListMList.json")
	public @ResponseBody JSONMap<String,Object> queryMachListMList(@FormModel PrePlanMachineInfo prePlanMachineInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//根据计划id获得计划实体
			PrePlanInfo prePlanInfoEo = prePlanInfoService.getPrePlanInfoById(prePlanMachineInfo.getPre_plan_id());
			model.put("prePlanInfoEo", prePlanInfoEo);
			//列表查询（分页）
			List<PrePlanMachineInfo> prePlanMachineInfoList = prePlanInfoService.queryPrePlanMachineInfoList(prePlanMachineInfo);
			model.put("data", prePlanMachineInfoList);
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
	 * 添加信息前
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeAddEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeAddEntity(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//周期comb
			List<SysCodeInfo> planCycleList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.CYCLE_GROUP);
			model.put("planCycleList", planCycleList);
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
	 * 录入信息
	 * @param perMachineIdList
	 * @param perCheckIdList
	 * @param prePlanInfo
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel("perMachineIdList") String[] perMachineIdList, @FormModel("perCheckIdList") String[] perCheckIdList,
															  @FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		PrePlanInfoDTO prePlanInfoDTO  = new PrePlanInfoDTO();
		prePlanInfoDTO.setUser_id(processor.getCurrentUserId()); //用户id
		prePlanInfoDTO.setPre_plan_cycle(prePlanInfo.getPre_plan_cycle());//计划周期
		prePlanInfoDTO.setPre_plan_date(prePlanInfo.getPre_plan_date());//计划日期
		prePlanInfoDTO.setPre_plan_nm(prePlanInfo.getPre_plan_nm());//计划名称
		prePlanInfoDTO.setPre_machine_id(subStringTOstr(perMachineIdList));//设备列表
		prePlanInfoDTO.setCheck_project_id(subStringTOstr(perCheckIdList));//检查项目列表
		prePlanInfoDTO.setPre_plan_date_end(prePlanInfo.getPre_plan_date_end());
		prePlanInfoDTO.setPre_plan_group("");
		try{
			prePlanInfoService.savePerPlan(prePlanInfoDTO);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	/**
	 * 字符集和转换成字符串
	 * @param sb
	 * @return str
	 */
	private String subStringTOstr(String[] sb){
		String str = "";
		for(int i=0;i<sb.length;i++){
			str += sb[i]+",";
		}
		return str = str.substring(0, str.length()-1);
	}
	
	/**
	 * 修改信息前获取信息
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel  PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<SysCodeInfo> planCycleList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.CYCLE_GROUP);//周期comb
			model.put("planCycleList", planCycleList);
			//得到'修改区分List'
			List<SysCodeInfo> updateflagList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.UPDATE_FLAG);//修改类型
			//根据计划id获得计划实体
			PrePlanInfo prePlanInfoEo = prePlanInfoService.getPrePlanInfoById(prePlanInfo.getPre_plan_id());
			//根据计划id获得设备列表
			List<PrePlanMachineInfo> machList = prePlanInfoService.queryPrePlanMachineInfoListByPrePlanId(prePlanInfo.getPre_plan_id());
			//根据计划id获得检查项目列表queryPrePlanMachineInfoListByPrePlanId
			List<PrePlanCheckDetail> checkList = prePlanInfoService.queryPrePlanCheckDetailListByPlanMachineId(machList.get(0).getPlan_machine_id());
			model.put("prePlanInfoEo", prePlanInfoEo);
			model.put("machList", machList);
			model.put("checkList", checkList);
			model.put("updateflagList", updateflagList);
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
	 * 修改信息
	 * @param perMachineIdList
	 * @param perCheckIdList
	 * @param prePlanInfo
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel("perMachineIdList") String[] perMachineIdList, @FormModel("perCheckIdList") String[] perCheckIdList,
																 @FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		PrePlanInfoDTO prePlanInfoDTO  = new PrePlanInfoDTO();
		prePlanInfoDTO.setUser_id(processor.getCurrentUserId()); //用户id
		prePlanInfoDTO.setPre_plan_cycle(prePlanInfo.getPre_plan_cycle());//计划周期
		prePlanInfoDTO.setPre_plan_date(prePlanInfo.getPre_plan_date());//计划日期
		prePlanInfoDTO.setPre_plan_nm(prePlanInfo.getPre_plan_nm());//计划名称
		prePlanInfoDTO.setPre_machine_id(subStringTOstr(perMachineIdList));//设备列表
		prePlanInfoDTO.setCheck_project_id(subStringTOstr(perCheckIdList));//检查项目列表
		prePlanInfoDTO.setPre_plan_id(prePlanInfo.getPre_plan_id());//计划id
		prePlanInfoDTO.setPre_plan_group(prePlanInfo.getPre_plan_group());//计划组id
		prePlanInfoDTO.setPre_plan_date_end(prePlanInfo.getPre_plan_date_end());
		prePlanInfoDTO.setUpdate_flag(prePlanInfo.getUpdate_flag());//单个删除 or 批量删除
		try{
			String flag=prePlanInfo.getUpdate_flag();
			if(flag!=null&&!"".equals(flag)){
				//如果批量修改 判断是否有计划已经被操作，如果有则返回提示 不能进行批量操作
				List<PrePlanInfo> tempList= new ArrayList<PrePlanInfo>();
				if(flag.equals(PmConstant.UPDATE_FLAG_0)&&flag.equals(PmConstant.UPDATE_FLAG_2)){	//批量更新
					PrePlanInfo tempInfo= new PrePlanInfo();	
					tempInfo.setPre_pm_rate("0");
					tempInfo.setPre_plan_group(prePlanInfo.getPre_plan_group());
					tempList=prePlanInfoService.queryplanGroupList(tempInfo);
				}
				if(tempList!=null&&tempList.size()!=0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE,"done");
				}else{
					prePlanInfoService.updatePreMstThreeTable(prePlanInfoDTO);				
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 单个删除计划
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfoService.deletePrePlanInfo(prePlanInfo);
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
	 * 确认
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateConfirmData.json")
	public @ResponseBody JSONMap<String,Object> updateConfirmData(@FormModel PrePlanInfo prePlanInfo,@FormModel SysCodeInfo sysCodeInfo ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfo.setPre_plan_state(PmConstant.PLAN_STATE_03);//计划状态-确认
			prePlanInfoService.updatePrePlanInfo(prePlanInfo);
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
	 * 获得计划实绩详情
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPrePlanCheckDetail.json")
	public @ResponseBody JSONMap<String,Object> queryPrePlanCheckDetail(@FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//预防性计划详情
			List<String[]> prePlanCheckDetailList=prePlanInfoService.getPrePlanCheckDetail(prePlanInfo);
			//预见性计划详情
			List<String[]> prePlanPropCheckDetailTitleList=prePlanInfoService.getPrePlanPropCheckDetail(prePlanInfo);
			//计划实体-计划名
			PrePlanInfo entity = prePlanInfoService.getPrePlanInfoById(prePlanInfo.getPre_plan_id());
			model.put("prePlanInfo", entity);	
			model.put("data", prePlanCheckDetailList);	
			model.put("dataprop", prePlanPropCheckDetailTitleList);	
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
	 * 获得计划检查项目-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPrePlanCheckItem.json")
	public @ResponseBody JSONMap<String,Object> queryPrePlanCheckItem(@FormModel PrePlanCheckDetail prePlanCheckDetail ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//得到设备info
			PrePlanMachineInfo preMachineInfo  = new PrePlanMachineInfo();
			preMachineInfo.setPlan_machine_id(prePlanCheckDetail.getPlan_machine_id());
			preMachineInfo=prePlanInfoService.queryPrePlanMachineInfoListNoPage(preMachineInfo).get(0);
			//得到planEntity
			PrePlanInfo  prePlanInfo = new PrePlanInfo();
			prePlanInfo.setPre_plan_id(preMachineInfo.getPre_plan_id());
			prePlanInfo = prePlanInfoService.queryPrePlanInfoListNoPage(prePlanInfo).get(0);		
			
			List<PrePlanCheckDetail> prePlanCheckDetailList =  prePlanInfoService.queryPrePlanCheckDetailListByPlanMachineId(prePlanCheckDetail.getPlan_machine_id());
			model.put("prePlanCheckDetailList", prePlanCheckDetailList);
			model.put("prePlanInfo", prePlanInfo);	
			model.put("preMachineInfo", preMachineInfo);
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
	 * 保存计划检查项目录入内容-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updatePerThreeTableMob.json")
	public @ResponseBody JSONMap<String,Object> updatePerThreeTableMob(@FormModel PrePlanInfo prePlanInfo,
			@FormModel("prePlanMachineInfo") PrePlanMachineInfo prePlanMachineInfo,
			@FormModel("checkitemList")List<PrePlanCheckDetail> checkitemList,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfo.setOperate_date(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			prePlanInfoService.updatePerThreeTableMob(prePlanInfo,prePlanMachineInfo,checkitemList,processor);				
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
	 * 获得预见性设备-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPrePlanPropMachList.json")
	public @ResponseBody JSONMap<String,Object> queryPrePlanPropMachList(@FormModel PrePlanPropMachineInfo prePlanPropMachineInfo ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<PrePlanPropMachineInfo> prePlanPropMachineInfoList =  prePlanInfoService.queryPrePlanPropMachineInfoListNoPage(prePlanPropMachineInfo);
			PrePlanPropMachineInfo entity = null;
				int cnt = 5 - prePlanPropMachineInfoList.size();
				for(int j=0;j<prePlanPropMachineInfoList.size();j++){
					prePlanPropMachineInfoList.get(j).setEditType(PmConstant.EDITTYPE_UPDATE);
				}
				if(cnt>0){
					for(int i=0;i<cnt;i++){
						entity = new PrePlanPropMachineInfo();
						entity.setEditType(PmConstant.EDITTYPE_ADD);
						entity.setPre_plan_id(prePlanPropMachineInfo.getPre_plan_id());
						prePlanPropMachineInfoList.add(entity);
					}
				}
			model.put("prePlanPropMachineInfoList", prePlanPropMachineInfoList);
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
	 * 保存预见性维护设备录入内容-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updatePrePlanPropMachineInfo.json")
	public @ResponseBody JSONMap<String,Object> updatePrePlanPropMachineInfo(@FormModel("prePlanPropMachineInfo") List<PrePlanPropMachineInfo> prePlanPropMachineInfo,@FormModel("chk_type") String chk_type,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfoService.updatePrePlanPropMachineInfo(prePlanPropMachineInfo,chk_type,processor);				
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
	 * 查询预见性维护检查内容-手机端
	 * 
	 */
	@RequestMapping("/queryPrePlanPropCheckitemList.json")
	public @ResponseBody JSONMap<String,Object> queryPrePlanPropCheckitemList(@FormModel PrePlanPropCheckDetail prePlanPropCheckDetail ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			PrePlanPropMachineInfo prePlanPropMachineInfo = prePlanInfoService.getPrePlanPropMachineInfoById(prePlanPropCheckDetail.getProp_plan_id());
			List<PrePlanPropCheckDetail> prePlanPropCheckDetailList =  prePlanInfoService.queryPrePlanPropCheckDetailListNoPage(prePlanPropCheckDetail);
			PrePlanPropCheckDetail entity = null;
			int cnt = Integer.parseInt(prePlanPropMachineInfo.getPre_check_all()) - prePlanPropCheckDetailList.size();
			for(int j=0;j<prePlanPropCheckDetailList.size();j++){
				prePlanPropCheckDetailList.get(j).setEditType(PmConstant.EDITTYPE_UPDATE);
			}
			if(cnt>0){
				for(int i=0;i<cnt;i++){
					entity = new PrePlanPropCheckDetail();
					entity.setEditType(PmConstant.EDITTYPE_ADD);
					entity.setProp_plan_id(prePlanPropCheckDetail.getProp_plan_id());
					prePlanPropCheckDetailList.add(entity);
				}
			}
			model.put("prePlanPropCheckDetailList", prePlanPropCheckDetailList);
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
	 * 保存预见性维护检查内容录入内容-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updatePrePlanPropCheckitemInfo.json")
	public @ResponseBody JSONMap<String,Object> updatePrePlanPropCheckitemInfo(@FormModel("prePlanPropCheckDetail") List<PrePlanPropCheckDetail> prePlanPropCheckDetail,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfoService.updatePrePlanPropCheckitemInfo(prePlanPropCheckDetail,processor);				
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
	 * 批量确认 
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateStateBatch.json")
	public @ResponseBody JSONMap<String,Object> updateStateBatch(@FormModel("prePlanInfo") PrePlanInfo prePlanInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = prePlanInfoService.updateStateBatch(prePlanInfo,processor);
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
	 * 批量删除计划
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteStateBatch.json")
	public @ResponseBody JSONMap<String,Object> deleteStateBatch(@FormModel PrePlanInfo prePlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			prePlanInfoService.deleteStateBatch(prePlanInfo);
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

/**
 * 单个删除计划
 * @param prePlanInfo
 * @param processor
 * @return
 *//*
@RequestMapping("/deleteEntityData.json")
public @ResponseBody JSONMap<String,Object> deletePropMachEntityData(@FormModel PrePlanPropMachineInfo prePlanPropMachineInfo,@FormModel Processor processor) {
	JSONMap<String,Object> model = new JSONMap<String,Object>();
	try{
		prePlanInfoService.deletePrePlanInfo(prePlanInfo);
		model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
	}catch(Exception e){
		e.printStackTrace();
		model.put(SysConstant.OP_FLAG, false);
		model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
	}
	return model;
}*/
