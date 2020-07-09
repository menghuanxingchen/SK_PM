package com.pm.business.report.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.report.service.ReportAllService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.common.print.ReportAllPrint;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.report.dto.ReportAllDto;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Processor;

/**
 * report
 * @author fish
 * @date 2016.11.16
 *
 */
@Controller
@RequestMapping("/ReportAllController")
public class ReportAllController {

	@Resource
	private ReportAllService reportAllService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/report/report_all";
	}
	

	/**
	 * reportAll-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeReportAllDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeReportAllDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询设备类别下拉列表
			List<SysCodeInfo> machTypeList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.MACH_TYPE_GROUP);//设备类别
			model.put("machTypeList", machTypeList);
			//巡查保养分类下拉列表
			List<SysCodeInfo> chkTypeList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PRE_INS_TYPE);//巡查保养分类
			model.put("chkTypeList", chkTypeList);
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
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getSpeciesCodeList.json")
	public @ResponseBody JSONMap<String,Object> getSpeciesCodeList(@FormModel("subGroupCode") String subGroupCode,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//下属部门下拉列表
			List<SysCodeInfo> machSpeciesList = sysCodeInfoService.getSubCodeList(PmConstant.MACH_TYPE_GROUP, subGroupCode);
			model.put("machSpeciesList", machSpeciesList);
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
	 * 获得全部报表
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPlanCheckDetail.json")
	public @ResponseBody JSONMap<String,Object> queryPlanCheckDetail(@FormModel ReportAllDto reportAllDto,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		List<List<String[]>> reportList = null;
		List<List<String[]>> reportPorpList = null;
		List<InsPlanInfo> insPlanInfoList = null; 
		List<PrePlanInfo> prePlanInfoList = null; 
		String pmRate = "";
		
		try{
			if(PmConstant.PRE_TYPE.equals(reportAllDto.getPlanType())){
				//预防性计划详情
				reportList = reportAllService.getReportPer(reportAllDto);
				//预见性计划详情
				reportPorpList = reportAllService.getReportPerProp(reportAllDto);
				//计划名称
				prePlanInfoList = reportAllService.getReportPerNmList(reportAllDto);
				
				pmRate = this.PMRate(prePlanInfoList);
				
				model.put("reportListEntity", prePlanInfoList);	
			}else{
				//巡检计划报表
				reportList = reportAllService.getReportIns(reportAllDto);
				//计划名称
				insPlanInfoList = reportAllService.getReportInsNmList(reportAllDto);
				
				pmRate = this.PMRate(insPlanInfoList);
				
				model.put("reportListEntity", insPlanInfoList);	
			}
			model.put("pmRate", pmRate);
			model.put("reportList", reportList);	
			model.put("reportPorpList", reportPorpList);	
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
	 * 大报表导出
	 * @param reportAllDto
	 * @return
	 */
	@RequestMapping("/reportExport.json")
	public @ResponseBody JSONMap<String,Object> reportExport(@FormModel ReportAllDto reportAllDto,@FormModel Processor processor,HttpServletRequest request) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		List<List<String[]>> reportList = null;
		List<List<String[]>> reportPorpList = null;
		List<InsPlanInfo> insPlanInfoList = null; 
		List<PrePlanInfo> prePlanInfoList = null; 
		ReportAllPrint reportAllPrint = new ReportAllPrint(request);
		String filePath = "";
		String pmRate = "";
		try{
			if(PmConstant.PRE_TYPE.equals(reportAllDto.getPlanType())){
				//预防性计划详情
				reportList = reportAllService.getReportPer(reportAllDto);
				//预见性计划详情
				reportPorpList = reportAllService.getReportPerProp(reportAllDto);
				//计划名称
				prePlanInfoList = reportAllService.getReportPerNmList(reportAllDto);
				
				pmRate = this.PMRate(prePlanInfoList);
				reportAllDto.setPm_rate(pmRate);
				
				filePath = reportAllPrint.mdPrint(reportList, reportPorpList, prePlanInfoList, reportAllDto);
			}else{
				//巡检计划报表
				reportList = reportAllService.getReportIns(reportAllDto);
				//计划名称
				insPlanInfoList = reportAllService.getReportInsNmList(reportAllDto);
				
				pmRate = this.PMRate(insPlanInfoList);
				reportAllDto.setPm_rate(pmRate);
				
				filePath = reportAllPrint.mdPrint(reportList, null, insPlanInfoList, reportAllDto);
			}
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
	 * PM完成率
	 * @param planInfoList
	 * @return
	 */
	public String PMRate(List planInfoList){
		if(planInfoList.size() == 0 ){
			return "";
		}
		
		int pmRateCount = planInfoList.size();
		int pmRateFull = 0;
		for(int i = 0; i < pmRateCount; i++){
			String fullRate = "0";
			if(planInfoList.get(i) instanceof PrePlanInfo){
				fullRate = ((PrePlanInfo)planInfoList.get(i)).getPre_pm_rate();
			}else if(planInfoList.get(i) instanceof InsPlanInfo){
				fullRate = ((InsPlanInfo)planInfoList.get(i)).getIns_pm_rate();
			}

			try{
				if(fullRate.indexOf(".") > 0){
					fullRate = fullRate.substring(0,fullRate.indexOf("."));
				}
			}catch(Exception e){
				
			}

			if(fullRate.equals("100")){
				pmRateFull++;
			}
		}
		
		try{
			return (int)Math.ceil((((double)pmRateFull)/pmRateCount)*100)+"%";
		}catch(Exception e){
			return "0";
		}
	}
}