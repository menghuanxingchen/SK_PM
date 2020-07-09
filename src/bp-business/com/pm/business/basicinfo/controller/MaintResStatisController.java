package com.pm.business.basicinfo.controller;




import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.MaintResStatisService;
import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
import com.pm.persistence.repair.dto.MaintAreaStatisDTO;
import com.pm.persistence.repair.dto.MaintResStatisDTO;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.EChartsData;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;





@Controller
@RequestMapping("/MaintResStatisController")
public class MaintResStatisController {
	

	@Resource
	private MaintResStatisService maintResStatisService;

	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/maintainlog/maintainresults_statistic_list";
	}	
	
	/**
	 * MTBF报表页面
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/MTBFJsp")
	public String toMTBFJsp(){
		return "web/maintainlog/MTBF_log";
	}
	
	/**
	 * MTTA报表页面
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/MTTAJsp")
	public String toMTTAJsp(){
		return "web/maintainlog/MTTA_log";
	}	
	
	/**
	 * 
	 * MTTR报表页面
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/MTTRJsp")
	public String toMTTRJsp(){
		return "web/maintainlog/MTTR_log";
	}	
	
	/**
	 * 每日故障报表页面
	 * @return
	 * @author 高文超
	 */
	@RequestMapping("/MRSJJsp")
	public String toMRSJJsp(){
		return "web/maintainlog/MRSJ_log";
	}
	
	/**
	 * 每日故障报表页面
	 * @return
	 * @author 高文超
	 */
	@RequestMapping("/MRCSJsp")
	public String toMRCSJsp(){
		return "web/maintainlog/MRCS_log";
	}
	
	/**
	 * 二级部件停机次数统计
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/potPartStopCount")
	public String potPartStopCount(){
		return "web/maintainlog/potPartStopCount";
	}
	
	/**
	 * 停机维修报表
	 * @return
	 * @author 高文超
	 */
	@RequestMapping("/potPartStopReport")
	public String potPartStopReport(){
		return "web/maintainlog/potPartStopReport";
	}
	
	/**
	 * 三级子部件停机次数统计
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/codeStopCount")
	public String codeStopCount(){
		return "web/maintainlog/codeStopCount";
	}
	
	/**
	 * 列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryStatisOrderTypeList.json")
	public @ResponseBody JSONMap<String,Object> queryStatisOrderTypeList(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			Pagination<MaintResStatisDTO> infoList = 
					maintResStatisService.queryStatisOrderTypeList(maintResStatisDTO,processor.getPageIndex(), processor.getPageSize());
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
	 * 列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryStatisOrderMachineList.json")
	public @ResponseBody JSONMap<String,Object> queryStatisOrderMachineList(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			Pagination<MaintResStatisDTO> infoList = 
					maintResStatisService.queryStatisOrderMachineList(maintResStatisDTO,processor.getPageIndex(), processor.getPageSize());
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
	 * 列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryRepOrderList.json")
	public @ResponseBody JSONMap<String,Object> queryRepOrderList(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			Pagination<MaintResStatisDTO> infoList = 
					maintResStatisService.queryRepOrderList(maintResStatisDTO,processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<MaintResStatisDTO> convert = new UIDataConvert<MaintResStatisDTO>();
			IConvert check_name = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num", "");
			convert.addConvert(check_name);
			IConvert dept_code_name = new SqlMapConvertImpl("dept_code", "dept_code_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.DEPART_GROUP+"'");
			convert.addConvert(dept_code_name);
			IConvert repair_user_name = new SqlMapConvertImpl("repair_user_id", "repair_user_name", "sys_user_info", "user_nm","user_num", "");
			convert.addConvert(repair_user_name);
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
	@RequestMapping("/defaultJspArea")
	public String tablepageArea(){
		return "web/maintainlog/maint_area_statistic_list";
	}	
	/**
	 * 列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMaintAreaStatisList.json")
	public @ResponseBody JSONMap<String,Object> queryMaintAreaStatisList(@FormModel("maintAreaStatisDTO") MaintAreaStatisDTO maintAreaStatisDTO,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			Pagination<MaintAreaStatisDTO> infoList = 
					maintResStatisService.queryMaintAreaStatisList(maintAreaStatisDTO,processor.getPageIndex(), processor.getPageSize());
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
	 * 三级子部件停机次数报表
	 * @param selTime 所选日期
	 * @param processor
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/queryCodeStopCountECharts.json")
	public @ResponseBody JSONMap<String,Object> queryCodeStopCountECharts(@FormModel("selTime") String selTime,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			EChartsData infoList = 
					maintResStatisService.queryCodeStopCountECharts(selTime);
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
	 * 列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPotPartStopReport.json")
	public @ResponseBody JSONMap<String,Object> queryPotPartStopReport(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			
			Pagination<MaintResStatisDTO> infoList = 
					maintResStatisService.queryPotPartStopReport(maintResStatisDTO,processor.getPageIndex(), processor.getPageSize());
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
	 * 二级部件停机次数报表
	 * @param selTime 所选日期
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/queryPotPartStopCountECharts.json")
	public @ResponseBody JSONMap<String,Object> queryPotPartStopCountECharts(@FormModel("selTime") String selTime,@FormModel("selPotid") String selPotid,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//部件停机次数报表
			EChartsData infoList = 
					maintResStatisService.queryPotPartStopCountECharts(selTime,selPotid);
			//设备停机次数
			List<String> machineStopCount = 
					maintResStatisService.queryPotStopCount(selTime,selPotid);
			model.put("machineStopCount", machineStopCount);			
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
	 * 平均停机时间间隔
	 * @maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/queryMTBFECharts.json")
	public @ResponseBody JSONMap<String,Object> queryMTBFECharts(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,@FormModel("selPotId") String selPotId,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//平均停机时间间隔报表
			EChartsData infoList = 
					maintResStatisService.queryReportECharts(maintResStatisDTO,selPotId,"1","MTBF");
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
	 * 每日故障
	 * @maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 高文超
	 */
	@RequestMapping("/queryMRSJECharts.json")
	public @ResponseBody JSONMap<String,Object> queryMRSJECharts(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,@FormModel("selPotId") String selPotId,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//每日故障
			EChartsData infoList = 
					maintResStatisService.queryMRSJReportECharts(maintResStatisDTO,selPotId,"1");
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
	 * 每日故障
	 * @maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 高文超
	 */
	@RequestMapping("/queryMRCSECharts.json")
	public @ResponseBody JSONMap<String,Object> queryMRCSECharts(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,@FormModel("selPotId") String selPotId,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//每日故障
			EChartsData infoList = 
					maintResStatisService.queryMRCSReportECharts(maintResStatisDTO,selPotId,"1");
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
	 * 平均停机时间
	 * @maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/queryMTTAECharts.json")
	public @ResponseBody JSONMap<String,Object> queryMTTAECharts(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,@FormModel("selPotId") String selPotId,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//平均停机时间报表
			EChartsData infoList = 
					maintResStatisService.queryReportECharts(maintResStatisDTO,selPotId,"1","MTTA");
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
	 * 平均维修时间
	 * @maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param processor
	 * @return
	 * @author 姜易平
	 */
	@RequestMapping("/queryMTTRECharts.json")
	public @ResponseBody JSONMap<String,Object> queryMTTRECharts(@FormModel("maintResStatisDTO") MaintResStatisDTO maintResStatisDTO,@FormModel("selPotId") String selPotId,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			//平均维修时间报表
			EChartsData infoList = 
					maintResStatisService.queryReportECharts(maintResStatisDTO,selPotId,"2","MTTR");
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
	
	
}
