package com.sqm.SqmInterface.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.pm.business.emergentRepair.service.EmergentRepairInfoService;
import com.pm.business.machinemanager.service.MachinePotPartInfoService;
import com.pm.business.sqmmanage.service.SqmmanageService;
import com.pm.business.sqmmanage.service.TestItemInfoService;
import com.pm.business.sqmmanage.service.TestingProcessInfoService;
import com.pm.business.sqmtestingmanage.service.SqmtestingManageService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.sqmtestingmanage.entity.TestResult18Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult200Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult4Info;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.repast.core.util.DateUtil;
import com.sqm.SqmInterface.service.SqmInterfaceService;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.TestResult.service.TestResultService;
import com.sqm.TestResultUpdate.service.TestResultUpdateService;
import com.sqm.sqmcomplaintfeedback.entity.SqmComplaintFeedback;
import com.sqm.sqmcomplaintfeedback.service.SqmComplaintFeedbackService;
import com.sqm.sqmequipmenteverystate.entity.SqmEquipmentEveryState;
import com.sqm.sqmequipmenteverystate.service.SqmEquipmentEveryStateService;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
import com.sqm.sqmequipmentstate.service.SqmEquipmentStateService;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
import com.sqm.sqmpushinfo.service.SqmPushInfoService;
import com.sqm.testoveryn.entity.TestOverYn;
import com.sqm.testoveryn.service.TestOverYnService;

/**
 * 表sqm_complaint_feedback的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/SqmInterfaceController")
public class SqmInterfaceController {

	@Resource
	private SqmInterfaceService sqmInterfaceService;

	@Resource
	private SqmtestingManageService sqmtestingManageService;

	@Resource
	private SysUserInfoService sysUserInfoService;

	@Resource
	private SqmmanageService sqmmanageService;

	@Resource
	private SqmPushInfoService sqmPushInfoService;

	@Resource
	private SqmComplaintFeedbackService sqmComplaintFeedbackService;

	@Resource
	private TestingProcessInfoService testingProcessInfoService;

	@Resource
	private TestResultService testResultService;

	@Resource
	private SysCodeInfoService sysCodeInfoService;

	@Resource
	private EmergentRepairInfoService emergentRepairInfoService;

	@Resource
	private MachinePotPartInfoService machinePotPartInfoService;
	
	@Resource
	private TestResultUpdateService testResultUpdateService;
	
	@Resource
	private TestItemInfoService testItemInfoService;
	
	@Resource
	private SqmEquipmentStateService sqmEquipmentStateService;
	
	@Resource
	private SqmEquipmentEveryStateService sqmEquipmentEveryStateService;
	
    @Resource
    private TestOverYnService testOverYnService;
    
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 灌装计划接口
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceFillingPlanDataList.json")
	public @ResponseBody JSONMap<String,Object> interfaceFillingPlanDataList(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if(StringUtils.isNullOrEmpty(fillingPlanInfo.getCNFYMD()))fillingPlanInfo.setCNFYMD(DateUtil.getNow(DateUtil.YMD));
			if(!StringUtils.isNullOrEmpty(fillingPlanInfo.getPRODNAME()))fillingPlanInfo.setPRODNAME(URLDecoder.decode(fillingPlanInfo.getPRODNAME(), "UTF-8"));
			List<FillingPlanInfo> fillingPlanInfoList = sqmInterfaceService.queryFillingPlanDataList2(fillingPlanInfo);
			if(fillingPlanInfoList.size()>0) {
				for (FillingPlanInfo planInfo : fillingPlanInfoList) {
					SqmComplaintFeedback sqmComplaintFeedback = new SqmComplaintFeedback();
					sqmComplaintFeedback.setProduct_pack(planInfo.getCAPA());
					sqmComplaintFeedback.setCf_type("1");//投诉
					List<SqmComplaintFeedback> list = sqmComplaintFeedbackService.querySqmComplaintFeedbackList(sqmComplaintFeedback);
					if(list.size()>0) {
						planInfo.setAdd_measures(list.get(0).getAdd_measures());//追加措施
						planInfo.setComplaint_content(list.get(0).getFeedback_content());//投诉内容
						planInfo.setComplaint_date(list.get(0).getCf_date());//投诉时间
					}
					List<FillingPlanInfo> latelyPROD = sqmInterfaceService.queryLatelyFillingPlanPROD(planInfo.getId());
					if(latelyPROD.size()>0) {
						planInfo.setLatelyPROD(latelyPROD.get(0).getPROD());
					}
				}
			}
			UIDataConvert<FillingPlanInfo> convert = new UIDataConvert<FillingPlanInfo>();
			convert.format(fillingPlanInfoList);
			for (FillingPlanInfo fillingPlanInfo2 : fillingPlanInfoList) {
				//获取生产流程
				TestingProcessInfo processInfo = new TestingProcessInfo();
				processInfo.setProductCode(fillingPlanInfo2.getPKG()+fillingPlanInfo2.getPROD());
				processInfo.setLineId(fillingPlanInfo2.getLINEID());
				List<TestingProcessInfo> testingProcessList = testingProcessInfoService.queryTestingProcessInfoListMonitor(processInfo);
				fillingPlanInfo2.setTestingProcessList(testingProcessList);
			}
			model.put("data", fillingPlanInfoList);
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
	 * 4升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceQuery4DataList.json")
	public @ResponseBody JSONMap<String,Object> query4DataList(@FormModel TestResult4Info testResult4Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult4Info> testResult4InfoList = sqmtestingManageService.queryTestResult4InfoList(testResult4Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult4Info> convert = new UIDataConvert<TestResult4Info>();

			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);


			convert.format(testResult4InfoList);
			model.put("data", testResult4InfoList);
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
	 * 18升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceQuery18DataList.json")
	public @ResponseBody JSONMap<String,Object> query18DataList(@FormModel TestResult18Info testResult18Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult18Info> testResult18InfoList = sqmtestingManageService.queryTestResult18InfoList(testResult18Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult18Info> convert = new UIDataConvert<TestResult18Info>();

			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);


			convert.format(testResult18InfoList);
			model.put("data", testResult18InfoList);
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
	 * 200升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceQuery200DataList.json")
	public @ResponseBody JSONMap<String,Object> query200DataList(@FormModel TestResult200Info testResult200Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult200Info> testResult200InfoList = sqmtestingManageService.queryTestResult200InfoList(testResult200Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult200Info> convert = new UIDataConvert<TestResult200Info>();

			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);


			convert.format(testResult200InfoList);
			model.put("data", testResult200InfoList);
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
	 * 投诉信息查询
	 * @param sqmComplaintFeedback
	 * @param processor
	 * @author 刘镝
	 */
	@RequestMapping("/interfaceComplaintDataList.json")
	public @ResponseBody JSONMap<String,Object> interfaceComplaintDataList(@FormModel SqmComplaintFeedback sqmComplaintFeedback,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			sqmComplaintFeedback.setCf_type("1");//投诉
			Pagination<SqmComplaintFeedback> dataList = sqmComplaintFeedbackService.querySqmComplaintFeedbackList(sqmComplaintFeedback, processor.getPageIndex(), processor.getPageSize());

			//列表中code转化name
			//UIDataConvert<SqmComplaintFeedback> convert = new UIDataConvert<SqmComplaintFeedback>();
			//IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
			//convert.addConvert(position_nm);
			//convert.format(dataList);

			model.put("data", dataList);
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
	 * 开始检测,获取检测流程及检测历史数据
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceTestingInfo.json")
	public @ResponseBody JSONMap<String,Object> insertStartTesting(@FormModel LineProductInfo lineProductInfo,@FormModel("startPackCode") String startPackCode,@FormModel("fourSYn") String fourSYn,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//检查检测状态表中是否存在检测员数据
			List<SqmEquipmentEveryState> dataList = sqmEquipmentEveryStateService.queryDataList(processor.getCurrentUserId(),"","");
			if(dataList.size()==0) {//没有则新增一组数据
				List<SqmEquipmentEveryState> list = sqmEquipmentEveryStateService.queryDataList2();
				for (SqmEquipmentEveryState sqmEquipmentEveryState : list) {
					sqmEquipmentEveryState.setId(null);
					sqmEquipmentEveryState.setInspector(processor.getCurrentUserId());
					sqmEquipmentEveryStateService.insertSqmEquipmentEveryState(sqmEquipmentEveryState);
				}
			}
			//灌装计划详情
			FillingPlanInfo planInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			List<TestingProcessInfo> testingProcessInfoList = testingProcessInfoService.getTestingProcessInfoByProductCode(planInfo.getPKG()+planInfo.getPROD(),planInfo.getLINEID());
			List<TestItemInfo> testItemInfoList = testItemInfoService.queryTestItemInfoList(planInfo.getLINEID(),null,planInfo.getPKG()+planInfo.getPROD());

			   if(testingProcessInfoList.size()==0) {//没有检测流程
				TestingProcessInfo testingProcessInfo=new TestingProcessInfo();
				testingProcessInfo.setCreate_id("auto");
				testingProcessInfo.setCreate_time(DateUtil.getNow(DateUtil.ymd));
				testingProcessInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
				testingProcessInfo.setProductName(planInfo.getPRODNAME());
				List<TestingProcessInfo> testingProcessInfoTempList = testingProcessInfoService.getTestingProcessInfoTempByLineId(planInfo.getLINEID());
					for (int i = 0; i < testingProcessInfoTempList.size(); i++) {
						TestingProcessInfo s = testingProcessInfoTempList.get(i);
						testingProcessInfo.setLineId(s.getLineId());
						testingProcessInfo.setLineName(s.getLineName());
						testingProcessInfo.setEquipmentId(s.getEquipmentId()); 
						testingProcessInfo.setEquipmentName(s.getEquipmentName()); 
						testingProcessInfo.setOrderNum(s.getOrderNum());
						testingProcessInfo.setUseYn(s.getUseYn());
						testingProcessInfoService.saveTestingProcessInfo(testingProcessInfo);
					}
			    }
				if(testItemInfoList.size()==0) {//没有检测项
					TestItemInfo	testItemInfo=new TestItemInfo();
					testItemInfo.setCreate_id("auto");
					testItemInfo.setCreate_time(DateUtil.getNow(DateUtil.ymd));
					testItemInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
					testItemInfo.setProductName(planInfo.getPRODNAME());
					testItemInfo.setLineId(planInfo.getLINEID());
					//查询出检测模板中的检测项
					TestItemInfo ti = new TestItemInfo();
					ti.setLineId(testItemInfo.getLineId());
					List<TestItemInfo> TestItemTemplatList = testItemInfoService.queryTestItemTemplatList(ti);
					for (int j = 0; j < TestItemTemplatList.size(); j++) {
						TestItemInfo y = TestItemTemplatList.get(j);
						testItemInfo.setLineName(y.getLineName());
						testItemInfo.setEquipmentId(y.getEquipmentId()); 
						testItemInfo.setEquipmentName(y.getEquipmentName()); 
						testItemInfo.setTestItem(y.getTestItem());
						testItemInfo.setTestResultNoID(y.getTestResultNoID());
						testItemInfo.setTableName(y.getTableName());
						testItemInfo.setOrderNum(y.getOrderNum());
						testItemInfo.setUserYn(y.getUserYn());
						testItemInfoService.saveTestItemInfo(testItemInfo);
					}
			}
				boolean matchingYn = testItemInfoService.queryFillingPlanAndTestingProcessAndTestingItmeMatchingYn(planInfo.getId());
			if(!matchingYn) {
				model.put("JumpYN", "N");
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE,"灌装计划的检测流程或检测项配置错误！");
			}
			else if(2==planInfo.getFILL_STATE()&&!processor.getCurrentUserId().equals(planInfo.getFault_user_id())) {
				model.put("JumpYN", "N");
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE,"生产线异常！故障申报人解除故障后才可继续检测！");
			}else if(3==planInfo.getFILL_STATE()) {
				model.put("JumpYN", "N");
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE,"灌装计划已经完成！");
			}else if(4==planInfo.getFILL_STATE()) {
				model.put("JumpYN", "N");
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE,"生产线故障！");
			}else if(sqmInterfaceService.queryTestingYn(planInfo)>0) {
				model.put("JumpYN", "N");
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE,"此生产线还有灌装计划未完成！");
			}else{
				TestOverYn queryTestOverYn = testOverYnService.queryTestOverYn(fillingPlanInfo.getId(), processor.getCurrentUserId());
				model = sqmInterfaceService.updateFillingPlanInfo(model,lineProductInfo,planInfo, processor, queryTestOverYn,startPackCode,fourSYn);	
			}

		}catch(Exception e){
			logger.error(e.toString());
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, e.getMessage());

		}
		return model;
	}
	
	/**
	 * 刷新检测页面
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceRefreshTestingPage.json")
	public @ResponseBody JSONMap<String,Object> interfaceRefreshTestingPage(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			SysCodeInfo sysCodeInfo = new SysCodeInfo();
			//灌装计划详情
			FillingPlanInfo planInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			TestOverYn queryTestOverYn = testOverYnService.queryTestOverYn(fillingPlanInfo.getId(), processor.getCurrentUserId());
	    	int testNum=queryTestOverYn.getTest_num();
			planInfo.setTestNum(testNum);
			//获取本人检测历史
			TestResult testResult = new TestResult();
			testResult.setPlanId(planInfo.getId());
			testResult.setCreate_id(processor.getCurrentUserId());
			List<TestResult> testResultList = testResultService.queryTestResultList(testResult,1,9999,"").getRecords();
			//获取生产流程
			TestingProcessInfo processInfo = new TestingProcessInfo();
			processInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
			processInfo.setLineId(planInfo.getLINEID());
			List<TestingProcessInfo> testingProcessList = testingProcessInfoService.queryTestingProcessInfoListNopage(processInfo,processor);
			for (TestingProcessInfo testingProcessInfo : testingProcessList) {
				List<TestItemInfo> testItemInfoList = testItemInfoService.queryTestItemInfoList(testingProcessInfo.getLineId(), testingProcessInfo.getEquipmentId(), testingProcessInfo.getProductCode());
				testingProcessInfo.setTestItemInfoList(testItemInfoList);
				testResult.setTest_num(planInfo.getTestNum());
				testResult.setEquipmentId(testingProcessInfo.getEquipmentId());
				List<TestResult> thisTestResultList = testResultService.queryTestResultList(testResult,1,9999,"").getRecords();
				for (TestItemInfo testItemInfo : testItemInfoList) {
					if("unpacker_list".equals(testItemInfo.getTestResultNoID())||"reinspection_supplier_selection".equals(testItemInfo.getTestResultNoID())||"filling_dust_yn".equals(testItemInfo.getTestResultNoID())) {
						sysCodeInfo.setCode_group_value(testItemInfo.getTestResultNoID());
						List<SysCodeInfo> codeInfoList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
						testItemInfo.setSysCodeInfoList(codeInfoList);		
					}
				  for (TestResult testResult2 : thisTestResultList) {
						if(testResult2.getTestItem().equals(testItemInfo.getId())) {
							testItemInfo.setTestResultId(testResult2.getId());
							testItemInfo.setTestResult(testResult2.getTestResult());
							testItemInfo.setTest_num(testResult2.getTest_num());
					 }
				  }
			   }
			}
			model.put("planInfo", planInfo);
			model.put("testingProcessList", testingProcessList);
			model.put("testResultList", testResultList);
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
	 * 监测画面
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceMonitorTestingPage.json")
	public @ResponseBody JSONMap<String,Object> interfaceMonitorTestingPage(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//灌装计划详情
			FillingPlanInfo planInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			//获取所有人检测历史
			TestResult testResult = new TestResult();
			testResult.setPlanId(planInfo.getId());
			List<TestResult> testResultList = testResultService.queryTestResultList(testResult,1,9999,"").getRecords();
			//获取生产流程
			TestingProcessInfo processInfo = new TestingProcessInfo();
			processInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
			processInfo.setLineId(planInfo.getLINEID());
			List<TestingProcessInfo> testingProcessList = testingProcessInfoService.queryTestingProcessInfoListMonitor(processInfo);
			for (TestingProcessInfo testingProcessInfo : testingProcessList) {
				List<TestItemInfo> testItemInfoList = testItemInfoService.queryTestItemInfoList(testingProcessInfo.getLineId(), testingProcessInfo.getEquipmentId(), testingProcessInfo.getProductCode());
				testingProcessInfo.setTestItemInfoList(testItemInfoList);
			}
			model.put("planInfo", planInfo);
			model.put("testingProcessList", testingProcessList);
			model.put("testResultList", testResultList);
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
	 * 监测画面-某设备检测记录
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceMonitortestEquipmentIdResult.json")
	public @ResponseBody JSONMap<String,Object> interfaceMonitortestEquipmentIdResult(@FormModel("planId") String planId,@FormModel("equipmentId") String equipmentId,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//获取所有人检测历史
			TestResult testResult = new TestResult();
			testResult.setEquipmentId(equipmentId);
			testResult.setPlanId(planId);
			List<TestResult> testResultList = testResultService.queryTestResultList(testResult,1,9999,"").getRecords();
			model.put("testResultList", testResultList);
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
	 * 结束本次检测
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceEndTestingInfo.json")
	public @ResponseBody JSONMap<String,Object> interfaceEndTestingInfo(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//灌装计划详情
			fillingPlanInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			TestOverYn queryTestOverYn = testOverYnService.queryTestOverYn(fillingPlanInfo.getId(), processor.getCurrentUserId());
            sqmInterfaceService.updateFillingPlanInfoOver2(fillingPlanInfo,processor,queryTestOverYn);
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
	 * 结束计划
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceOverFillingPlan.json")
	public @ResponseBody JSONMap<String,Object> interfaceOverFillingPlan(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel("packCode") String packCode,@FormModel("abnormalRemarks") String abnormalRemarks,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//检测员及线长完成的巡检次数
			int overYesNum = testOverYnService.queryTestOverYesNum(fillingPlanInfo.getId());
        	//灌装计划详情
        	fillingPlanInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
			sqmEquipmentState.setLineId(fillingPlanInfo.getLINEID());
			if(fillingPlanInfo.getFILL_STATE()==0) {
				model.put("OverPlanYN","N");
				model.put(SysConstant.OP_MESSAGE,"灌装计划还未进行！");
			}else if(fillingPlanInfo.getFILL_STATE()==2){
				model.put("OverPlanYN", "N");
				model.put(SysConstant.OP_MESSAGE,"生产线故障未解除！");
			}else if(fillingPlanInfo.getFILL_STATE()==4){
				model.put("OverPlanYN", "N");
				model.put(SysConstant.OP_MESSAGE,"生产线故障未解除！");
			}else if(fillingPlanInfo.getFILL_STATE()==3){
				model.put("OverPlanYN", "N");
				model.put(SysConstant.OP_MESSAGE,"灌装计划已结束，请勿重复点击！");
			}else {
				if(overYesNum>0&&("1".equals(processor.getSqm_role_code())||"2".equals(processor.getSqm_role_code()))) {
					model = sqmInterfaceService.updateFillingPlanInfoOverPlan(fillingPlanInfo,packCode,abnormalRemarks,processor);
				}else if(overYesNum>0&&(!"1".equals(processor.getSqm_role_code())&&!"2".equals(processor.getSqm_role_code()))) {
					model.put("OverPlanYN","N");
					model.put(SysConstant.OP_MESSAGE,"线长和检测员才可以结束计划！");
				}else {
					model.put("OverPlanYN","N");
					model.put(SysConstant.OP_MESSAGE,"至少完成一轮完整的巡检！");
				}	
			}
		
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE,e.getMessage());
		}
		return model;
	}

	/**
	 * 一键故障申报
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceFailureReport.json")
	public @ResponseBody JSONMap<String,Object> insertBreakdownInfo(@FormModel("testNum") int testNum,@FormModel("testResult") List<TestResult> testResult,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			model=sqmInterfaceService.insertBreakdownInfo(testNum, fillingPlanInfo, testResult, processor,model);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 故障申报（停机维修通道）
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceEmergentRepair.json")
	public @ResponseBody JSONMap<String,Object> interfaceEmergentRepair(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
        	emergentRepairInfo.setCreate_id(processor.getCurrentUserId());
    		emergentRepairInfo.setStart_time(DateUtil.getNow(DateUtil.Y_M_D_HM));
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_content())){
        			emergentRepairInfo.setRepair_content(URLDecoder.decode(emergentRepairInfo.getRepair_content(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_person())){
        			emergentRepairInfo.setRepair_person(URLDecoder.decode(emergentRepairInfo.getRepair_person(), "UTF-8"));
        			}
        	}
    		SysCodeInfo sysCodeInfo=new SysCodeInfo();
    		sysCodeInfo.setCode_group_value("line");
    		sysCodeInfo.setSub_code_group_value(emergentRepairInfo.getPot_id());
    		List<SysCodeInfo> codeInfoList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
        	if(codeInfoList.size()==1) {//是sqm生产线
        		SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
        		sqmEquipmentState.setLineId(codeInfoList.get(0).getCode_value());
        		sqmEquipmentState.setEquipmentId(emergentRepairInfo.getPot_part_id());
        		List<SqmEquipmentState> equipmentStateList = sqmEquipmentStateService.querySqmEquipmentStateList(sqmEquipmentState);
        		if(equipmentStateList.size()==0) {//不是sqm的设备
                    emergentRepairInfoService.insertEmergentRepairInfo(emergentRepairInfo);
                    model.put(SysConstant.OP_FLAG, true);
                    model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        		}else {//是sqm设备
            		fillingPlanInfo.setLINEID(codeInfoList.get(0).getCode_value());
            		//异常和故障
        			List<FillingPlanInfo> fillingPlanInfoFaultList = sqmInterfaceService.queryFaultPlanList(fillingPlanInfo);
        			if(fillingPlanInfoFaultList.size()>0) {
        				model.put(SysConstant.OP_FLAG, false);
        				model.put(SysConstant.OP_MESSAGE, "此生产线已是异常或故障状态，不可重复申报故障！");
        			}else {
        				//未进行和进行中
        				emergentRepairInfo.setSqm_relieve_yn("N");//未解除（解除故障按钮标识）
        				List<FillingPlanInfo> queryNotAndProgressPlanList = sqmInterfaceService.queryNotAndProgressPlanList(fillingPlanInfo);
            			model=sqmInterfaceService.interfaceEmergentRepair(emergentRepairInfo,fillingPlanInfo,processor,model,codeInfoList,queryNotAndProgressPlanList,sqmEquipmentState);
        			}
        		}
        	}else {
                emergentRepairInfoService.insertEmergentRepairInfo(emergentRepairInfo);
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
	 *  解除故障
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceTroubleshooting.json")
	public @ResponseBody JSONMap<String,Object> interfaceTroubleshooting(@FormModel("pushId") String pushId,@FormModel("sqmNormalParameter") String sqmNormalParameter,@FormModel TestResult testResult,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		 JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			    //灌装计划详情
			    FillingPlanInfo planInfo = sqmInterfaceService.getFillingPlanInfoById(fillingPlanInfo.getId());
			    if(processor.getCurrentUserId().equals(planInfo.getFault_user_id())) {
				    model = sqmInterfaceService.updateFillingPlanInfoTroubleshooting(pushId, testResult, planInfo,sqmNormalParameter, processor);
				    model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			    }else {
			    	model.put(SysConstant.OP_FLAG, false);
			    	model.put(SysConstant.OP_MESSAGE,"故障申报人才可解除故障！");
			    }
		 }catch(Exception e){
	            model.put(SysConstant.OP_FLAG, false);
	            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
	            System.out.println(e.getMessage());
	        }
	        return model;
	}
	
	/**
	 *  解除故障（停机维修通道）
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/interfaceRelieveFault.json")
	public @ResponseBody JSONMap<String,Object> interfaceRelieveFault(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel("deleteYn") String deleteYn,@FormModel Processor processor) {
		 JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			    String sqm_normal_parameter = emergentRepairInfo.getSqm_normal_parameter();
			    emergentRepairInfo = emergentRepairInfoService.getEmergentRepairInfoById(emergentRepairInfo.getEmergent_id());
                if(!StringUtils.isNullOrEmpty(sqm_normal_parameter))emergentRepairInfo.setSqm_normal_parameter(URLDecoder.decode(sqm_normal_parameter, "UTF-8"));
		        SysCodeInfo sysCodeInfo=new SysCodeInfo();
		        sysCodeInfo.setCode_group_value("line");
		        sysCodeInfo.setSub_code_group_value(emergentRepairInfo.getPot_id());
		        List<SysCodeInfo> codeInfoList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);

		        if(codeInfoList.size()==1) {//是sqm生产线
	    			SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
	    			sqmEquipmentState.setLineId(codeInfoList.get(0).getCode_value());
	    			sqmEquipmentState.setEquipmentId(emergentRepairInfo.getPot_part_id());
	    			List<SqmEquipmentState> equipmentStateList = sqmEquipmentStateService.querySqmEquipmentStateList(sqmEquipmentState);
	    			if(equipmentStateList.size()==1) {//是sqm设备
	    			    if("Y".equals(emergentRepairInfo.getSqm_relieve_yn())) {
	        	            model.put(SysConstant.OP_FLAG, false);
	        	            model.put(SysConstant.OP_MESSAGE, "故障已解除，不要重复点击！");
	    			    }else {
		    				fillingPlanInfo.setLINEID(codeInfoList.get(0).getCode_value());
		    				//异常或故障
		        			List<FillingPlanInfo> fillingPlanInfoFaultList = sqmInterfaceService.queryFaultPlanList(fillingPlanInfo);
						    model = sqmInterfaceService.updateInterfaceRelieveFault(fillingPlanInfoFaultList, emergentRepairInfo, fillingPlanInfo, processor,deleteYn);
	    			    }
	    			}else {
	    	            model.put(SysConstant.OP_FLAG, false);
	    	            model.put(SysConstant.OP_MESSAGE, "非SQM设备！");
	    			}
		        }else {
    	            model.put(SysConstant.OP_FLAG, false);
    	            model.put(SysConstant.OP_MESSAGE, "非SQM生产线！");
		        }
		 }catch(Exception e){
	            model.put(SysConstant.OP_FLAG, false);
	            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
	            System.out.println(e.getMessage());
	        }
	        return model;
	}
	
    /**
     * 新增检测信息
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/interfaceSaveTestResult.json")
    public @ResponseBody JSONMap<String,Object> interfaceSaveTestResult( @FormModel("pushId")String pushId, @FormModel("updateCause")String updateCause, @FormModel("testNum") int testNum,@FormModel FillingPlanInfo fillingPlanInfo,@FormModel("tips") String tips,@FormModel("testResult") List<TestResult> testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	model = sqmInterfaceService.saveTestResult(pushId,updateCause,testNum, fillingPlanInfo, tips, testResult, processor,model);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }
    
    /**
     * 通过推送push_id获取需要修改的检测项集合
     * @param testResult
     * @param processor
     * @author 刘镝
     */
	@RequestMapping("/interfaceQueryTestResultListByPush.json")
    public @ResponseBody JSONMap<String,Object> interfaceQueryTestResultListByPush(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	SysCodeInfo sysCodeInfo = new SysCodeInfo();
        	List<TestItemInfo> list = testResultService.queryTestItemInfoListByPush(sqmPushInfo,processor.getCurrentUserId());
        	for (TestItemInfo testItemInfo : list) {
				if("unpacker_list".equals(testItemInfo.getTestResultNoID())||"reinspection_supplier_selection".equals(testItemInfo.getTestResultNoID())||"filling_dust_yn".equals(testItemInfo.getTestResultNoID())) {
					sysCodeInfo.setCode_group_value(testItemInfo.getTestResultNoID());
                    List<SysCodeInfo> codeInfoList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
                    testItemInfo.setSysCodeInfoList(codeInfoList);		
				}
			}

        	//灌装计划详情
        	FillingPlanInfo fillingPlanInfo = sqmInterfaceService.getFillingPlanInfoById(list.get(0).getPlanId());
        	fillingPlanInfo.setTestNum(list.get(0).getTest_num());
        	if(3==fillingPlanInfo.getFILL_STATE()) {
                model.put(SysConstant.OP_FLAG, false);
                model.put(SysConstant.OP_MESSAGE,"该灌装计划已完成！");
                return model;
        	}else if(2==fillingPlanInfo.getFILL_STATE()&&!fillingPlanInfo.getFault_user_id().equals(processor.getCurrentUserId())) {
                model.put(SysConstant.OP_FLAG, false);
                model.put(SysConstant.OP_MESSAGE,"生产线异常！故障解除后才可继续检测！");
                return model;
        	}else {
            	//获取生产流程
    			TestingProcessInfo processInfo = new TestingProcessInfo();
    			processInfo.setLineId(fillingPlanInfo.getLINEID());
    			processInfo.setProductCode(fillingPlanInfo.getPKG()+fillingPlanInfo.getPROD());
    			processInfo.setEquipmentId(list.get(0).getEquipmentId());
    			List<TestingProcessInfo> testingProcessList = testingProcessInfoService.queryTestingProcessInfoListNopage(processInfo,processor);
    			testingProcessList.get(0).setTestItemInfoList(list);
            	model.put("planInfo", fillingPlanInfo);
            	model.put("equipmentId", list.get(0).getEquipmentId());
            	model.put("equipmentName", list.get(0).getEquipmentName());
            	model.put("testingProcessList", testingProcessList);
                model.put(SysConstant.OP_FLAG, true);
                model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);	
        	}

        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }
    
    /**
     * 通过灌装计划信息获取需要修改的检测项集合
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/interfaceQueryTestResultListByPlan.json")
    public @ResponseBody JSONMap<String,Object> interfaceQueryTestResultListByPlan(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	SysCodeInfo sysCodeInfo = new SysCodeInfo();
        	//获取本人检测历史
        	TestResult testResult = new TestResult();
        	testResult.setCreate_id(processor.getCurrentUserId());
        	testResult.setPlanId(fillingPlanInfo.getId());
        	testResult.setTest_num(fillingPlanInfo.getTestNum());
        	testResult.setEquipmentId(fillingPlanInfo.getEquipmentId());
        	List<TestItemInfo> list = testResultService.QueryTestResultListByPlan(testResult);
        	for (TestItemInfo testItemInfo : list) {
				if("unpacker_list".equals(testItemInfo.getTestResultNoID())||"reinspection_supplier_selection".equals(testItemInfo.getTestResultNoID())||"filling_dust_yn".equals(testItemInfo.getTestResultNoID())) {
					sysCodeInfo.setCode_group_value(testItemInfo.getTestResultNoID());
                    List<SysCodeInfo> codeInfoList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
                    testItemInfo.setSysCodeInfoList(codeInfoList);		
				}
			}
        	//灌装计划详情
        	if(list.size()>0) {
            	fillingPlanInfo = sqmInterfaceService.getFillingPlanInfoById(list.get(0).getPlanId());
            	fillingPlanInfo.setTestNum(list.get(0).getTest_num());
        	}
        	model.put("testResultList", list);
        	model.put("planInfo", fillingPlanInfo);
        	model.put("equipmentId", list.get(0).getEquipmentId());
        	model.put("equipmentName", list.get(0).getEquipmentName());
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
     * 获取推送列表（消息列表）
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/interfaceQueryPushList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
        	sqmPushInfo.setPush_to(processor.getCurrentUserId());
            Pagination<SqmPushInfo> dataList = sqmPushInfoService.querySqmPushInfoList(sqmPushInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<SqmPushInfo> convert = new UIDataConvert<SqmPushInfo>();
            IConvert position_nm = new SqlMapConvertImpl("push_type", "push_type_name", "sys_code_info", "code_nm","code_value", " and code_group_value='push_type' ");
            convert.addConvert(position_nm);
            convert.format(dataList);

            model.put("data", dataList);
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
     * 更新检测流程、检测项
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updateTestProcessAndTestIntem.json")
    public @ResponseBody JSONMap<String,Object> updateTestProcessAndTestIntem(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	sqmInterfaceService.updateTestProcessAndTestIntem(model);
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
