package com.pm.business.basicinfo.controller;




import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.basicinfo.service.MaintainLogService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
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





@Controller
@RequestMapping("/MaintainLogController")
public class MaintainLogController {
	

	@Resource
	private MaintainLogService maintainLogService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	@Resource
	private MachineService machineService;
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/maintainlog/maintainlog_list";
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
			sysCodeInfo.setCode_group_value(PmConstant.MAINTAINTYPE);//维修类型
			List<SysCodeInfo> mltypeList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			sysCodeInfo.setCode_group_value(PmConstant.PLAN_STATE);//状态
			List<SysCodeInfo> mlstateList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			List<SysCodeInfo> deptList = sysCodeInfoService.getSubCodeList(PmConstant.DEPART_GROUP,processor.getDept());
			SysCodeInfo subDept = new SysCodeInfo();
			subDept.setCode_value(processor.getSubdept());
			for(int i=0;i<deptList.size();i++){
				if(subDept.getCode_value().equals(deptList.get(i).getCode_value())){
					subDept.setCode_nm(deptList.get(i).getCode_nm());
				}
			}
			
			//查询部门下拉列表
			List<SysCodeInfo> departList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.DEPART_GROUP);//部门group code 
			//电仪下灌装机设备
			MachineInfo ma = new MachineInfo();
			ma.setMachine_species_id(PmConstant.MACHINE_SPECIECE_1);//电仪
			ma.setMachine_type_id(PmConstant.MACHINE_TYPE_7);//灌装
			List<MachineInfo> machineList=machineService.getMachineListNo(ma);
			
			model.put("departList", departList);
			
			model.put("mltypeList", mltypeList);
			model.put("mlstateList", mlstateList);
			model.put("subDept", subDept);
			model.put("machineList", machineList);
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
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMaitainLogList.json")
	public @ResponseBody JSONMap<String,Object> queryMaitainLogList(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<MaintainlogInfo> infoList = 
					maintainLogService.getMaintainLogList(maintainlogInfo,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<MaintainlogInfo> convert = new UIDataConvert<MaintainlogInfo>();
			IConvert check_state_name = new SqlMapConvertImpl("check_state", "check_state_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.PLAN_STATE+"'");
			convert.addConvert(check_state_name);
			IConvert create_name = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num", "");
			convert.addConvert(create_name);
			IConvert subdept_type_nm = new SqlMapConvertImpl("subdept_type", "subdept_type_nm", "sys_code_info", "code_nm","code_value", " AND code_group_value = (select sub_code_group_value from sys_code_info where code_group_value = '"+PmConstant.DEPART_GROUP+"' and code_value = '"+processor.getDept()+"') ");
			convert.addConvert(subdept_type_nm);
			IConvert maint_type__name = new SqlMapConvertImpl("maintain_type", "maint_type__name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MAINTAINTYPE+"'");
			convert.addConvert(maint_type__name);
			IConvert machinename = new SqlMapConvertImpl("machineid", "machinename", "machine_info", "machine_nm","machine_id", "");
			convert.addConvert(machinename);
			convert.format(infoList);	
			model.put("data", infoList);			
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
	 * delete
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteMaintainLog.json")
	public @ResponseBody JSONMap<String,Object> deleteMaintainLog(@FormModel("id") String id) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int delet_flag = maintainLogService.deleteMaintainlogInfo(id);
			if(delet_flag>0){
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
	 * 保存设备
	 * @param InsPlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveMaintainLogInfo.json")
	public @ResponseBody JSONMap<String,Object> saveMaintainLogInfo(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int savecount = maintainLogService.saveMaintainLogInfo(maintainlogInfo,processor);
			if(savecount>0){
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
	 * 得到Entity
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMaintainLogInfo.json")
	public @ResponseBody JSONMap<String,Object> queryMaintainLogInfo(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo,															
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			MaintainlogInfo logInfo = maintainLogService.getMaintainLogInfo(maintainlogInfo);
			SysCodeInfo sysCodeInfo= new SysCodeInfo();
			sysCodeInfo.setCode_group_value(PmConstant.MAINTAINTYPE);//维修类型
			List<SysCodeInfo> mltypeList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			List<SysCodeInfo> deptList = sysCodeInfoService.getSubCodeList(PmConstant.DEPART_GROUP,processor.getDept());
			for(int i=0;i<deptList.size();i++){
				if(processor.getSubdept().equals(deptList.get(i).getCode_value())){
					logInfo.setSubdept_type_nm(deptList.get(i).getCode_nm());
				}
			}
			
			//电仪下灌装机设备
			MachineInfo ma = new MachineInfo();
			ma.setMachine_species_id(PmConstant.MACHINE_SPECIECE_1);//电仪
			ma.setMachine_type_id(PmConstant.MACHINE_TYPE_7);//灌装
			List<MachineInfo> machineList=machineService.getMachineListNo(ma);
			
			model.put("mltypeList", mltypeList);			
			model.put("maintainlogInfo", logInfo);	
			model.put("machineList", machineList);
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
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateMaintainlogInfo.json")
	public @ResponseBody JSONMap<String,Object> updateMaintainlogInfo(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = maintainLogService.updateMaintainlogInfo(maintainlogInfo,processor);
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
	 * 确认 --fish
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateConfirmData.json")
	public @ResponseBody JSONMap<String,Object> updateConfirmData(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = maintainLogService.updateConfirmData(maintainlogInfo,processor);
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
	 * 批量确认 
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateStateBatch.json")
	public @ResponseBody JSONMap<String,Object> updateStateBatch(@FormModel("maintainlogInfo") MaintainlogInfo maintainlogInfo										
																	,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = maintainLogService.updateStateBatch(maintainlogInfo,processor);
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
}
