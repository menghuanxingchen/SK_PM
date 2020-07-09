package com.sqm.SqmInterface.service;


import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.dao.FillingPlanDAO;
import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.dao.TestItemInfoDAO;
import com.pm.persistence.Sqmmanage.dao.TestingProcessInfoDAO;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.pm.persistence.emergentRepair.dao.EmergentRepairInfoDAO;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.pm.persistence.machinemanager.dao.MachinePotPartInfoDAO;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.sqmtestingmanage.dao.TestResult18InfoDAO;
import com.pm.persistence.sqmtestingmanage.dao.TestResult200InfoDAO;
import com.pm.persistence.sqmtestingmanage.dao.TestResult4InfoDAO;
import com.pm.persistence.sqmtestingmanage.entity.TestResult18Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult200Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult4Info;
import com.pm.persistence.sysinfo.dao.SysCodeInfoDAO;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.TestResult.dao.TestResultDAO;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.TestResultUpdate.dao.TestResultUpdateDAO;
import com.sqm.TestResultUpdate.entity.TestResultUpdate;
import com.sqm.sqmequipmenteverystate.dao.SqmEquipmentEveryStateDAO;
import com.sqm.sqmequipmenteverystate.entity.SqmEquipmentEveryState;
import com.sqm.sqmequipmentstate.dao.SqmEquipmentStateDAO;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
import com.sqm.sqmpushinfo.dao.SqmPushInfoDAO;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
import com.sqm.sqmpushinfo.service.SqmPushInfoService;
import com.sqm.testoveryn.dao.TestOverYnDAO;
import com.sqm.testoveryn.entity.TestOverYn;
/**
 * 表sqm_complaint_feedback的SERVICE类
 * @author 刘镝
 */
@Service
public class SqmInterfaceService {
	private static Logger logger = Logger.getLogger("logger");


	@Resource
	private FillingPlanDAO fillingPlanDAO;

	@Resource
	private TestResult4InfoDAO testResult4InfoDAO;

	@Resource
	private TestResult18InfoDAO testResult18InfoDAO;

	@Resource
	private TestResult200InfoDAO testResult200InfoDAO;

	@Resource
	private SqmPushInfoDAO sqmPushInfoDAO;

	@Resource
	private SqmmanageDAO sqmmanageDao;

	@Resource
	private SysUserInfoDAO sysUserInfoDao;

	@Resource
	private SqmPushInfoService sqmPushInfoService;

	@Resource
	private TestResultDAO testResultDAO;

	@Resource
	private SqmEquipmentStateDAO sqmEquipmentStateDAO;

	@Resource
	private TestingProcessInfoDAO testingProcessInfoDAO;

	@Resource
	private  TestItemInfoDAO testItemInfoDAO;

	@Resource
	private  TestResultUpdateDAO testResultUpdateDAO;

	@Resource
	private  MachinePotPartInfoDAO machinePotPartInfoDAO;

	@Resource
	private  EmergentRepairInfoDAO emergentRepairInfoDAO;

	@Resource
	private  SysCodeInfoDAO sysCodeInfoDAO;

	@Resource
	private TestOverYnDAO testOverYnDAO;

	@Resource
	private SqmEquipmentEveryStateDAO sqmEquipmentEveryStateDAO;
	
	/**
	 * 分页查询用户信息
	 * @author gao
	 */
	public Pagination<FillingPlanInfo> interfaceFillingPlanDataList(FillingPlanInfo entity, int pageIndex, int pageSize) {
		return fillingPlanDAO.interfaceFillingPlanDataList(entity, pageIndex, pageSize);
	}

	/**
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<TestResult4Info> queryTestResult4InfoList(TestResult4Info entity, int pageIndex, int pageSize) {
		return testResult4InfoDAO.queryTestResult4InfoList(entity, pageIndex, pageSize);
	}
	/**
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<TestResult18Info> queryTestResult18InfoList(TestResult18Info entity, int pageIndex, int pageSize) {
		return testResult18InfoDAO.queryTestResult18InfoList(entity, pageIndex, pageSize);
	}

	/**
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<TestResult200Info> queryTestResult200InfoList(TestResult200Info entity, int pageIndex, int pageSize) {
		return testResult200InfoDAO.queryTestResult200InfoList(entity, pageIndex, pageSize);
	}

	/**
	 * 新增灌装计划
	 * @author gao
	 */
	public int saveFillingPlanInfo(FillingPlanInfo entity) {
		try {
			logger.info("事务操作方法：SqmInterfaceService--saveFillingPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SqmInterfaceService--saveFillingPlanInfo 异常！");
		}

		return fillingPlanDAO.saveFillingPlanInfo(entity);
	}

	
	/**
	 * 密度
	 * @author gao
	 */
	public int saveDensityInfo(FillingPlanInfo entity) {
		try {
			logger.info("事务操作方法：SqmInterfaceService--saveDensityInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SqmInterfaceService--saveDensityInfo 异常！");
		}

		return fillingPlanDAO.saveDensityInfo(entity);
	}
	/**
	 * 修改灌装计划状态及计划时间
	 * @author gao
	 */
	public int updateFillingPlanInfo(FillingPlanInfo entity) {
		try {
			logger.info("事务操作方法：SqmInterfaceService--updateFillingPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SqmInterfaceService--updateFillingPlanInfo 异常！");
		}

		return fillingPlanDAO.updateFillingPlanInfo(entity);
	}

	/**
	 * 结束计划
	 * @author 
	 */
	public int updateFillingPlanInfoOver(FillingPlanInfo entity) {
		try {
			logger.info("事务操作方法：SqmInterfaceService--updateFillingPlanInfoOver");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SqmInterfaceService--updateFillingPlanInfoOver 异常！");
		}
		return fillingPlanDAO.updateFillingPlanInfoOver(entity);
	}

	/**
	 * 删除实体对象entity
	 * @author gao
	 */
	public int deleteFillingPlanInfo(String NowDate) {
		try {
			logger.info("事务操作方法：SqmInterfaceService--deleteFillingPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SqmInterfaceService--deleteFillingPlanInfo 异常！");
		}
		return fillingPlanDAO.deleteFillingPlanInfo(NowDate);
	}

	/**
	 * 更新检测信息不一致的推送被修改状态
	 * @author 刘镝
	 */
	public int updSqmPushInfoUpdate_yn(String pushId,TestResult testResult,String type){
		return sqmPushInfoDAO.updSqmPushInfoUpdate_yn(pushId, testResult, type);

	}

	/**
	 * 计时推送
	 * @author shi
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public void interfaceTestTimingPushDataList(LineProductInfo lineProductInfo,FillingPlanInfo fillingPlanInfo,String type,Processor processor,String now) throws NumberFormatException, ParseException {
		String userId = processor.getCurrentUserId();  
		LineProductInfo lineProductInfo2 = sqmmanageDao.queryLineProductInfoList(lineProductInfo).get(0);//生产线产品信息
		String lineId = lineProductInfo2.getLineId();//生产线ID
		String lineName = lineProductInfo2.getLineName();//生产线名称
		String continueDate = lineProductInfo2.getContinueDate();//检测持续时间
		String intervalDate = lineProductInfo2.getIntervalDate();//检测间隔时间
		String breakdownDate = lineProductInfo2.getBreakdownDate();//故障持续时间
		lineProductInfo.setLineName(lineName);
		//停止我的此生产线旧计时任务
		WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();
		Timer oldTimer = (Timer)webApplicationContext.getServletContext().getAttribute(userId+lineId);
		if(oldTimer!=null) {
			stopTime(oldTimer);
			webApplicationContext.getServletContext().removeAttribute(userId+lineId);
		}
		//开始我的此生产线新计时任务
		Timer newTimer=null;
		switch (type) {
		case "1":
			//检测持续时间开始计时
			newTimer =startTime(Integer.parseInt(continueDate)*60*1000,userId,"continueDate",lineProductInfo,fillingPlanInfo,now);
			break;
		case "2":
			//检测间隔时间开始计时
			newTimer =startTime(Integer.parseInt(intervalDate)*60*1000,userId,"intervalDate",lineProductInfo,fillingPlanInfo,now);
			break;
		case "3":
			//故障持续时间开始计时 
			newTimer =startTime(Integer.parseInt(breakdownDate)*60*1000,userId,"breakdownDate",lineProductInfo,fillingPlanInfo,now);
			break;
		default:
			break;
		}
		if(newTimer!=null)webApplicationContext.getServletContext().setAttribute(userId+lineId, newTimer);

	}

	/** 
	 * 推送内容及去向
	 * @param args 需要发给的人工号
	 * @param type 推送类型
	 * @param lineName 生产线名称
	 * @return  
	 */ 
	public  TimerTask runTimes(final String args,final String type,final LineProductInfo lineProductInfo,final FillingPlanInfo fillingPlanInfo,final String now) {     
		TimerTask task = new TimerTask() {  
			public void run() {
				String lineId = lineProductInfo.getLineId();//生产线id
				String lineName = lineProductInfo.getLineName();//生产线名称
				String prod = fillingPlanInfo.getPROD();//产品code
				String cnfymd = fillingPlanInfo.getCNFYMD();//日期
				String pushInfo="";
				switch (type) {
				case "continueDate"://检测持续时间到
					pushInfo=args+"请注意"+lineName+"您持续检测时间到，加快速度";
					break;
				case "intervalDate"://检测间隔时间到
					pushInfo=args+"生产线"+lineName+"日期"+cnfymd+"产品code"+prod+"下一轮抽检时间到,上次结束时间为"+now;
					break;
				default://故障持续时间到
					pushInfo=lineName+"生产线故障处理超时！";
					break;
				}
				System.err.println(pushInfo); 
				WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();
				webApplicationContext.getServletContext().removeAttribute(args+lineId);
				try {
					SqmPushInfo sqmPushInfo = new SqmPushInfo();
					sqmPushInfo.setPush_msg(pushInfo);
					sqmPushInfo.setPush_to(args);
					sqmPushInfo.setPush_type(type);
					sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,fillingPlanInfo);
					if("breakdownDate".equals(type)) {//故障超时推送给所有人
						sqmPushInfo.setPush_id(null);
						sqmPushInfo.setPush_to("ALL");
						sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,fillingPlanInfo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}  
		};  
		return task;  
	}  
	/** 
	 * 从当前时间立刻发起定时器
	 * @param time 时间持续 
	 * @param args 需要输出的字符串 
	 * @return 
	 * @throws ParseException 
	 */  
	public  Timer startTime(long time,final String args,String type,LineProductInfo lineProductInfo,FillingPlanInfo fillingPlanInfo,String now) throws ParseException {  
		Timer timer = new Timer();  
		timer.schedule(runTimes(args,type,lineProductInfo,fillingPlanInfo,now), time);// 输出  
		return timer;  
	}  
	/** 
	 * 停止定时器
	 * @param timer 
	 */  
	public  void stopTime(Timer timer) {  
		timer.cancel();  
	}

	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	public FillingPlanInfo getFillingPlanInfoById(String id) {
		return fillingPlanDAO.getFillingPlanInfoById(id);
	}

	/**
	 * 结束计划插入检测结果表
	 * @author 刘镝
	 */
	public int insertTestResultInfo(String sql){
		return testResultDAO.executeSQL(sql);

	}

	/**
	 * 查询灌装计划
	 * @author 刘镝
	 */
	public List<FillingPlanInfo> queryFillingPlanDataList(FillingPlanInfo entity) {
		return fillingPlanDAO.queryFillingPlanDataList(entity);
	}
	
	/**
	 * 查询昨日未完成的及今日的灌装计划
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryFillingPlanDataList2(FillingPlanInfo entity) throws Exception {
		return fillingPlanDAO.queryFillingPlanDataList2(entity);
	}
	
	/**
	 * 查询上一次该生产线生产了哪个产品
	 * @author 刘镝
	 */
	public List<FillingPlanInfo> queryLatelyFillingPlanPROD(String id) {
		return fillingPlanDAO.queryLatelyFillingPlanPROD(id);
	}

	/**
	 * 开始检测
	 * @author 刘镝
	 * @throws Exception 
	 */
	public JSONMap<String,Object> updateFillingPlanInfo(JSONMap<String,Object> model,LineProductInfo lineProductInfo,FillingPlanInfo planInfo,Processor processor,TestOverYn testOverYn,String startPackCode,String fourSYn) throws Exception {
		int oldTestNum=StringUtils.isNullOrEmpty(testOverYn.getTest_num()+"")?0:testOverYn.getTest_num();
		//修改灌装计划状态
		switch (planInfo.getFILL_STATE()) {
		case 0:
			//修改状态监测表
			SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
			sqmEquipmentState.setLineId(planInfo.getLINEID());
			sqmEquipmentStateDAO.resetUpdSqmEquipmentState(sqmEquipmentState,"1");

			//修改本人状态检测表
			SqmEquipmentEveryState sqmEquipmentEveryState = new SqmEquipmentEveryState();
			sqmEquipmentEveryState.setLineId(planInfo.getLINEID());
			sqmEquipmentEveryState.setInspector(processor.getCurrentUserId());
			sqmEquipmentEveryStateDAO.resetUpdSqmEquipmentEveryState(sqmEquipmentEveryState,"1");

			//由未进行改为进行中状态
			String FILL_STDATE = DateUtil.getNow(DateUtil.Y_M_D_HMS);
			planInfo.setFILL_STATE(1);//进行中
			planInfo.setFILL_STDATE(FILL_STDATE);//首人首次检测时间
			planInfo.setTestNum(1);
			planInfo.setStart_pack_code(startPackCode);//开始物流码
			planInfo.setFourSYn(fourSYn);//是否5S检测
			fillingPlanDAO.updateFillingPlanInfo(planInfo);
			testOverYn.setCreate_id(processor.getCurrentUserId());
			testOverYn.setTest_num(1);
			testOverYn.setPlan_id(planInfo.getId());
			testOverYn.setOver_yn("n");
			testOverYnDAO.saveTestOverYn(testOverYn);
			break;
		case 1://进行中
			//获取生产流程
			TestingProcessInfo processInfo = new TestingProcessInfo();
			processInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
			processInfo.setLineId(planInfo.getLINEID());
			List<TestingProcessInfo> testingProcessList = testingProcessInfoDAO.queryTestingProcessInfoListNopage(processInfo,processor);
			String equipmentIds = testingProcessList.get(0).getEquipmentId()+","+testingProcessList.get(1).getEquipmentId();

			//修改本人状态检测表
			SqmEquipmentEveryState sqmEquipmentEveryState2 = new SqmEquipmentEveryState();
			sqmEquipmentEveryState2.setLineId(planInfo.getLINEID());
			sqmEquipmentEveryState2.setInspector(processor.getCurrentUserId());
			sqmEquipmentEveryState2.setEquipmentId(equipmentIds);

			testOverYn.setCreate_id(processor.getCurrentUserId());
			testOverYn.setPlan_id(planInfo.getId());

			if(testOverYn.getTest_num()==0) {
				testOverYn.setTest_num(1);
				testOverYn.setOver_yn("n");
				testOverYnDAO.saveTestOverYn(testOverYn);
				planInfo.setTestNum(1);
				sqmEquipmentEveryStateDAO.resetUpdSqmEquipmentEveryState(sqmEquipmentEveryState2,"1");
			}else {
				if("y".equals(testOverYn.getOver_yn())) {//本次检测已结束
					testOverYn.setTest_num(testOverYn.getTest_num()+1);
					testOverYn.setOver_yn("n");
					testOverYn.setId(null);
					testOverYnDAO.saveTestOverYn(testOverYn);
					planInfo.setTestNum(testOverYn.getTest_num());
					sqmEquipmentEveryStateDAO.resetUpdSqmEquipmentEveryState(sqmEquipmentEveryState2,"2");
				}else {//本次检测还没完成
					planInfo.setTestNum(testOverYn.getTest_num());
				}
			}
			break;
		case 2://故障异常
			planInfo.setTestNum(testOverYn.getTest_num());
			break;
		default:
			break;
		}

		//获取本人检测历史
		TestResult testResult = new TestResult();
		testResult.setPlanId(planInfo.getId());
		testResult.setCreate_id(processor.getCurrentUserId());
		List<TestResult> testResultList = testResultDAO.queryDataList(testResult,"");

		//获取生产流程
		TestingProcessInfo processInfo = new TestingProcessInfo();
		processInfo.setProductCode(planInfo.getPKG()+planInfo.getPROD());
		processInfo.setLineId(planInfo.getLINEID());
		List<TestingProcessInfo> testingProcessList = testingProcessInfoDAO.queryTestingProcessInfoListNopage(processInfo,processor);
		SysCodeInfo sysCodeInfo = new SysCodeInfo();
		for (TestingProcessInfo testingProcessInfo : testingProcessList) {
			List<TestItemInfo> testItemInfoList = testItemInfoDAO.queryTestItemInfoList(testingProcessInfo.getLineId(), testingProcessInfo.getEquipmentId(), testingProcessInfo.getProductCode());
			testingProcessInfo.setTestItemInfoList(testItemInfoList);
			testResult.setTest_num(planInfo.getTestNum());
			testResult.setEquipmentId(testingProcessInfo.getEquipmentId());
			List<TestResult> thisTestResultList = testResultDAO.queryDataList(testResult,"");
			for (TestItemInfo testItemInfo : testItemInfoList) {
				if("unpacker_list".equals(testItemInfo.getTestResultNoID())||"reinspection_supplier_selection".equals(testItemInfo.getTestResultNoID())||"filling_dust_yn".equals(testItemInfo.getTestResultNoID())) {
					sysCodeInfo.setCode_group_value(testItemInfo.getTestResultNoID());
					List<SysCodeInfo> codeInfoList = sysCodeInfoDAO.querySysCodeInfoListNoPage(sysCodeInfo);
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
		model.put("JumpYN", "Y");
		model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		if(planInfo.getFILL_STATE().equals("0")||(planInfo.getTestNum()-oldTestNum==1)) {
			//计时推送
			lineProductInfo.setLineId(planInfo.getLINEID());
			interfaceTestTimingPushDataList(lineProductInfo,planInfo,"1", processor,DateUtil.getNow(DateUtil.Y_M_D_HMS));	
		}
		return model;
	}

	/**
	 * 新增检测信息
	 * @author 刘镝
	 * @return 
	 * @throws Exception 
	 */
	public JSONMap<String, Object>  saveTestResult(String pushId,String updateCause,int testNum,FillingPlanInfo fillingPlanInfo,String tips,List<TestResult> testResult,Processor processor,JSONMap<String,Object> model) throws Exception {

		TestItemInfo testItemInfoById = testItemInfoDAO.getTestItemInfoById(testResult.get(0).getTestItem());
		String equipmentId = testItemInfoById.getEquipmentId();
		String equipmentName = testItemInfoById.getEquipmentName();

		//灌装计划详情
		FillingPlanInfo planInfo = getFillingPlanInfoById(fillingPlanInfo.getId());
		
		 if(2==planInfo.getFILL_STATE()&&!planInfo.getFault_user_id().equals(processor.getCurrentUserId())) {
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE,"生产线异常！故障解除后才可继续检测！");
    	}else if(4==planInfo.getFILL_STATE()) {
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE,"生产线故障！故障解除后才可继续检测！");
    	}else if(planInfo.getFILL_STATE()==3) {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, "该罐装计划已完成！");
		}else {

    		//监测设备状态
    		SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
    		sqmEquipmentState.setLineId(planInfo.getLINEID());
    		sqmEquipmentState.setEquipmentId(equipmentId);
    		sqmEquipmentState = sqmEquipmentStateDAO.querySqmEquipmentStateList(sqmEquipmentState).get(0);

    		//本人检测设备状态
    		SqmEquipmentEveryState sqmEquipmentEveryState = new SqmEquipmentEveryState();
    		sqmEquipmentEveryState.setLineId(planInfo.getLINEID());
    		sqmEquipmentEveryState.setEquipmentId(equipmentId);
    		sqmEquipmentEveryState.setInspector(processor.getCurrentUserId());
    		sqmEquipmentEveryState = sqmEquipmentEveryStateDAO.querySqmEquipmentEveryStateList(sqmEquipmentEveryState).get(0);

    		TestResult testResult0 = testResult.get(0);
    		testResult0.setEquipmentId(equipmentId);
    		testResult0.setPlanId(planInfo.getId());
    		testResult0.setTest_num(testNum);
    		testResult0.setCreate_id(processor.getCurrentUserId());

    		String TestItemNames=testItemInfoById.getLineName()+"线,产品code："+planInfo.getPROD()+"第"+testNum+"次检测"+equipmentName+"设备的";
    		String time = DateUtil.getNow(DateUtil.Y_M_D_HMS);

    		String resultId ="";
    		boolean b=false;
    		TestResult testResultById = null;
    		SqmPushInfo sqmPushInfo = new SqmPushInfo();

    		String flag="1";//保存
    		if(!StringUtils.isNullOrEmpty(testResult.get(0).getId())) {
    			if("6".equals(sqmEquipmentEveryState.getEState())) {//故障已解除待检
    				flag="3";//修改（不计修改次数）
    			}else {
    				flag="2";//修改
    			}
    			testResultById = testResultDAO.getTestResultById(testResult.get(0).getId());
    		}

    		if("1".equals(flag)) {//保存
    			for (TestResult testResult2 : testResult) {
    				TestItemInfo testItemInfo = testItemInfoDAO.getTestItemInfoById(testResult2.getTestItem());
    				testResult2.setId(null);
    				testResult2.setLineId(planInfo.getLINEID());
    				testResult2.setLotnumver(planInfo.getLOTNO());
    				testResult2.setProdunctCode(planInfo.getPROD());
    				testResult2.setCreate_id(processor.getCurrentUserId());
    				testResult2.setCreate_time(time);
    				testResult2.setUpdate_num(0);
    				testResult2.setUser_yn(1);
    				testResult2.setTest_num(testNum);
    				testResult2.setTestItemName(testItemInfo.getTestItem());
    				testResult2.setEquipmentId(testItemInfo.getEquipmentId());
    				testResult2.setPlanId(planInfo.getId());
    				testResult2.setTestResult(URLDecoder.decode(testResult2.getTestResult(), "UTF-8"));
    				switch (testResult2.getPlanfit_yn()) {
    				case "y":
    					testResult2.setrState("1");//正常 
    					break;
    				case "n":
    					testResult2.setrState("2");//与计划值不一致 
    					break;
    				default:
    					break;
    				}
    				resultId = testResultDAO.saveTestResult(testResult2);
    			}

    		}else if("2".equals(flag)) {//修改
    			for (TestResult testResult2 : testResult) {
    				testResult2.setTestResult(URLDecoder.decode(testResult2.getTestResult(), "UTF-8"));
    				TestResult oldTestResult = testResultDAO.getTestResultById(testResult2.getId());
    				if(!oldTestResult.getTestResult().equals(testResult2.getTestResult())) {
    					//插入检测修改记录表
    					TestResultUpdate testResultUpdate = new TestResultUpdate();
    					testResultUpdate.setRid(testResult2.getId());
    					testResultUpdate.setCreate_id(oldTestResult.getCreate_id());
    					testResultUpdate.setCreate_time(oldTestResult.getCreate_time());
    					testResultUpdate.setTest_result(oldTestResult.getTestResult());
    					testResultUpdate.setTest_num(oldTestResult.getTest_num());
    					testResultUpdate.setUpdate_num(oldTestResult.getUpdate_num());
    					testResultUpdate.setUpdate_cause(oldTestResult.getUpdate_cause());
    					testResultUpdate.setUpdate_time(oldTestResult.getUpdate_time());
    					testResultUpdate.setCreate_time2(time);
    					testResultUpdateDAO.saveTestResultUpdate(testResultUpdate);
    					//更新检测数据
    					testResult2.setUpdate_num(oldTestResult.getUpdate_num()+1);
    					testResult2.setUpdate_time(time);

    					if("1".equals(updateCause)) {
    						updateCause="与其他人不一致";
    					}else {
    						updateCause="与计划值不一致";
    					}
    					switch (testResult2.getPlanfit_yn()) {
    					case "y":
    						testResult2.setrState("1");//正常 
    						break;
    					case "n":
    						testResult2.setrState("2");//与计划值不一致 
    						break;
    					default:
    						break;
    					}
    					testResult2.setUpdate_cause(updateCause);
    					testResult2.setTestResult(testResult2.getTestResult());
    					testResultDAO.updateTestResult(testResult2);
    				}

    			}
    		}else {//修改（不计修改次数）
    			for (TestResult testResult2 : testResult) {
    				testResult2.setTestResult(URLDecoder.decode(testResult2.getTestResult(), "UTF-8"));
    				TestResult oldTestResult = testResultDAO.getTestResultById(testResult2.getId());
    				if(!oldTestResult.getTestResult().equals(testResult2.getTestResult())) {
    					//插入检测修改记录表
    					TestResultUpdate testResultUpdate = new TestResultUpdate();
    					testResultUpdate.setRid(testResult2.getId());
    					testResultUpdate.setCreate_id(oldTestResult.getCreate_id());
    					testResultUpdate.setCreate_time(oldTestResult.getCreate_time());
    					testResultUpdate.setTest_result(oldTestResult.getTestResult());
    					testResultUpdate.setTest_num(oldTestResult.getTest_num());
    					testResultUpdate.setUpdate_num(oldTestResult.getUpdate_num());
    					testResultUpdate.setUpdate_cause(oldTestResult.getUpdate_cause());
    					testResultUpdate.setUpdate_time(oldTestResult.getUpdate_time());
    					testResultUpdate.setCreate_time2(time);
    					testResultUpdateDAO.saveTestResultUpdate(testResultUpdate);
    					//更新检测数据
    					testResult2.setTestResult(testResult2.getTestResult());
    					switch (testResult2.getPlanfit_yn()) {
    					case "y":
    						testResult2.setrState("1");//正常 
    						break;
    					case "n":
    						testResult2.setrState("2");//与计划值不一致 
    						break;
    					default:
    						break;
    					}
    					testResultDAO.updateTestResult(testResult2);
    				}

    			}

    		}

    		if("3".equals(tips)) {//与计划值不一致

    			for (TestResult testResult2 : testResult) {

    				if("n".equals(testResult2.getPlanfit_yn())) {
    					resultId=testResult2.getId();
    					TestItemInfo testItemInfo = testItemInfoDAO.getTestItemInfoById(testResult2.getTestItem());
    					TestItemNames+=testItemInfo.getTestItem()+"、";
    				}
    			}
    			TestItemNames=TestItemNames.substring(0, TestItemNames.length()-1)+"检测项与计划值不一致";
    			//与计划值不一致推送
    			sqmPushInfo.setPush_msg(TestItemNames);
    			sqmPushInfo.setPush_to(processor.getCurrentUserId());
    			sqmPushInfo.setCreate_id(processor.getCurrentUserId());
    			sqmPushInfo.setPush_type("planDisaccord");
    			sqmPushInfo.setPush_source(resultId);//修改数据的来源
    			sqmEquipmentState.setEState("3");
    			sqmEquipmentEveryState.setEState("3");
    			if("2".equals(flag)) {//修改操作
    				if(!StringUtils.isNullOrEmpty(pushId))updSqmPushInfoUpdate_yn(pushId,testResultById,"only");
    			}
    		}else {

    			//获取此次所有人检测历史
    			List<TestResult> list = testResultDAO.queryDataList(testResult0,"1");
    			List<String> names=new ArrayList<String>();
    			List<String> users=new ArrayList<String>();
    			List<String> items=new ArrayList<String>();
    			if(list.size()!=0) {

    				for (int i = 0; i < list.size(); i++) {
    					for (int j = 0; j < testResult.size(); j++) {
    						if(testResult.get(j).getTestItem().equals(list.get(i).getTestItem())) {
    							if(!(testResult.get(j).getTestResult()).equals(list.get(i).getTestResult())) {
    								if(("复检称".equals(list.get(i).getEquipmentName())&&("重量".equals(list.get(i).getTestItemName())))) {
    									continue;
    								}else {
    									resultId=testResult.get(j).getId();
    									b=true;
    									names.add(list.get(i).getTestItemName());
    									users.add(list.get(i).getCreate_id());
    									items.add(list.get(i).getTestItem());
    								}
    							}

    						}
    					}
    				}
    			}

    			//不一致的检测项名称
    			List<String> removeDuplicate = removeDuplicate(names);
    			for (String string : removeDuplicate) {
    				TestItemNames+=string+"、";
    			}
    			TestItemNames=TestItemNames.substring(0, TestItemNames.length()-1)+"检测项与其他人不一致";
    			//不一致的检测员
    			String pushTousers="";
    			List<String> removeUsers = removeDuplicate(users);
    			for (String string : removeUsers) {
    				pushTousers+=string+",";
    			}
    			pushTousers=pushTousers+processor.getCurrentUserId();
    			
    			//不一致的检测项id
    			List<String> removeItemIds = removeDuplicate(items);
                if(removeItemIds.size()>0) {//修改部分检测项结果状态
                	testResultDAO.updateTestResultToDisaccord(testResult.get(0), removeItemIds);
                	testResultDAO.updateTestResultToNormal(testResult.get(0), removeItemIds);
                }else {//修改全部检测项结果状态
                	testResultDAO.updateTestResultAllToNormal(testResult.get(0));
                }
               
    			if(b) {//与他人不一致
    				sqmPushInfo.setPush_msg(TestItemNames);
    				sqmPushInfo.setPush_to(pushTousers);
    				sqmPushInfo.setPush_type("otherDisaccord");
    				sqmPushInfo.setPush_source(resultId);//修改数据的来源
    				sqmPushInfo.setCreate_id(processor.getCurrentUserId());
    				sqmEquipmentState.setEState("5");
    				sqmEquipmentEveryState.setEState("5");
    				if("2".equals(flag)) {//修改操作
    					if(!StringUtils.isNullOrEmpty(pushId))updSqmPushInfoUpdate_yn(pushId,testResultById,"only");
    				}
    			}else {//正常

    				if("2".equals(flag)) {//修改操作
    					updSqmPushInfoUpdate_yn(pushId,testResultById,"all");
    				}
    				sqmEquipmentState.setEState("2");
    				sqmEquipmentEveryState.setEState("2");
    			}

    		}

    		sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState);
    		sqmEquipmentStateDAO.updSqmEquipmentState(sqmEquipmentState);
    		List<SqmEquipmentEveryState> dataList = sqmEquipmentEveryStateDAO.queryDataList("",planInfo.getLINEID(),equipmentId);
    		switch (sqmEquipmentEveryState.getEState()) {
    		case "2"://正常
    			for (SqmEquipmentEveryState sqmEquipmentEveryState2 : dataList) {
    				if(sqmEquipmentEveryState2.getEState().equals("5")) {//与他人不一致
    					sqmEquipmentEveryState2.setEState("2");//改为已检测正常
    					sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState2);
    				}
    			}
    			break;
    		case "5"://与他人不一致
    			for (SqmEquipmentEveryState sqmEquipmentEveryState2 : dataList) {
    				if(sqmEquipmentEveryState2.getEState().equals("2")) {//已检测正常
    					sqmEquipmentEveryState2.setOldState("2");
    					sqmEquipmentEveryState2.setEState("5");//改为与他人不一致
    					sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState2);
    				}
    			}
    			break;
    		default:
    			break;
    		}
    		if(!StringUtils.isNullOrEmpty(sqmPushInfo.getPush_msg()))sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
    	}
		 return model;

	}

	public static List<String> removeDuplicate(List<String> list) {
		HashSet h = new HashSet(list);   
		list.clear();   
		list.addAll(h);   
		return list;   
	}

	/**
	 * 一键故障申报
	 * @author 刘镝
	 * @return 
	 * @throws Exception 
	 */
	public synchronized  JSONMap<String, Object>  insertBreakdownInfo(int testNum,FillingPlanInfo fillingPlanInfo,List<TestResult> testResult,Processor processor,JSONMap<String,Object> model) throws Exception {
		SysUserInfo sysUserInfo = sysUserInfoDao.getSysUserInfoById(processor.getCurrentUserId());
		
		String pot_id="";
		String pot_nm="";
		String resultId ="";
		String time = DateUtil.getNow(DateUtil.Y_M_D_HMS);

		//灌装计划详情
		FillingPlanInfo planInfo = getFillingPlanInfoById(fillingPlanInfo.getId());

		if(planInfo.getFILL_STATE()==2) {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, "不要重复申报故障！");
		}else if(planInfo.getFILL_STATE()==4) {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, "维修人员已申报故障！");
		}else if(planInfo.getFILL_STATE()==3) {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, "该罐装计划已完成！");
		}else {

		String lineId = planInfo.getLINEID();//生产线id
		String produnctCode = planInfo.getPROD();//产品id
		String lotnumver = planInfo.getLOTNO();//批次

		//检测项详情
		TestItemInfo testItemInfoById = testItemInfoDAO.getTestItemInfoById(testResult.get(0).getTestItem());
		String equipmentId = testItemInfoById.getEquipmentId();

		for (TestResult testResult2 : testResult) {
			testResult2.setTestResult(URLDecoder.decode(testResult2.getTestResult(), "UTF-8"));
			TestItemInfo testItemInfo = testItemInfoDAO.getTestItemInfoById(testResult2.getTestItem());
			//更新检测数据
			TestResult result = testResultDAO.queryTestResult(planInfo.getId(), processor.getCurrentUserId(),testNum,testResult2.getTestItem());
			if(testItemInfo.getTestItem().equals("设备状态")) {
				testResult2.setrState("4");//故障
			}else {
				if(StringUtils.isNullOrEmpty(testResult2.getTestResult())) {
					testResult2.setrState("0");//未检
				}else {
					testResult2.setrState("1");//已检测正常
				}
				
			}
			if(result==null) {
				testResult2.setId(null);
				testResult2.setUser_yn(1);
				testResult2.setLineId(lineId);
				testResult2.setLotnumver(lotnumver);
				testResult2.setProdunctCode(produnctCode);
				testResult2.setEquipmentId(equipmentId);
				testResult2.setTestItemName(testItemInfo.getTestItem());
				testResult2.setCreate_id(processor.getCurrentUserId());
				testResult2.setCreate_time(time);
				testResult2.setUpdate_num(0);
				testResult2.setTest_num(testNum);
				testResult2.setPlanId(planInfo.getId());
				resultId =testResultDAO.saveTestResult(testResult2);
			}else {
				//插入检测修改记录表
				TestResultUpdate testResultUpdate = new TestResultUpdate();
				testResultUpdate.setRid(result.getId());
				testResultUpdate.setCreate_id(result.getCreate_id());
				testResultUpdate.setCreate_time(result.getCreate_time());
				testResultUpdate.setTest_result(result.getTestResult());
				testResultUpdate.setTest_num(testNum);
				testResultUpdate.setUpdate_num(result.getUpdate_num());
				testResultUpdate.setUpdate_time(result.getUpdate_time());
				testResultUpdate.setCreate_time2(time);
				testResultUpdateDAO.saveTestResultUpdate(testResultUpdate);

				//更新检测数据
				resultId =result.getId();
				result.setTestResult(testResult2.getTestResult());
				result.setrState(testResult2.getrState());
				testResultDAO.updateTestResult(result);
			}

		}

		//插入停机维修信息数据
		if("1".equals(lineId)||"2".equals(lineId)||"3".equals(lineId)) {//1-4L
			switch (lineId) {
			case "1":
				pot_id="38";
				pot_nm="1L";
				break;
			case "2":
				pot_id="39";
				pot_nm="4L";
				break;
			default:
				pot_id="40";
				pot_nm="3-6L";
				break;
			}

		}else if("4".equals(lineId)) {//18L
			pot_id="41";
			pot_nm="18L";
		}else {//200L+
			switch (lineId) {
			case "5":
				pot_id="42";
				pot_nm="200L-A";
				break;
			case "6":
				pot_id="42";
				pot_nm="200L-B";
				break;
			case "7":
				pot_id="43";
				pot_nm="1000L";
				break;
			case "8":
				pot_id="42";
				pot_nm="200L_DEX";
				break;
			default:
				pot_id="42";
				pot_nm="BLUK";
				break;
			}	
		}
		MachinePotPartInfo machinePotPartInfo = machinePotPartInfoDAO.getMachinePotPartInfoById(equipmentId);
		EmergentRepairInfo emergentRepairInfo = new EmergentRepairInfo();
		emergentRepairInfo.setStart_time(time.substring(0, time.length()-3));
		emergentRepairInfo.setCreate_id(processor.getCurrentUserId());
		emergentRepairInfo.setPot_id(pot_id);
		emergentRepairInfo.setPot_part_id(equipmentId);
		emergentRepairInfo.setCreate_time(time);
		emergentRepairInfo.setJob_type("1");
		emergentRepairInfo.setRepair_date(DateUtil.getNow(DateUtil.Y_M_D));
		emergentRepairInfo.setRepair_content(pot_nm+"生产线"+machinePotPartInfo.getPot_part_nm()+"设备故障请及时维修!故障申报人："+sysUserInfo.getUser_nm());
		emergentRepairInfo.setRepairType("N");
		emergentRepairInfo.setRepair_person("王健,周麟,孙文学,曹万引");
		emergentRepairInfoDAO.saveEmergentRepairInfo(emergentRepairInfo);

		//修改灌装计划状态
		planInfo.setFILL_STATE(2);//异常
		planInfo.setFault_user_id(processor.getCurrentUserId());
		planInfo.setFault_time(time);
		planInfo.setEquipmentId(equipmentId);
		updateFillingPlanInfo(planInfo);

		//更新监测设备状态表
		SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
		sqmEquipmentState.setLineId(planInfo.getLINEID());
		sqmEquipmentState.setEquipmentId(planInfo.getEquipmentId());
		sqmEquipmentState.setEState("4");//设备故障
		sqmEquipmentStateDAO.updSqmEquipmentState(sqmEquipmentState);

		//更新此生产线所有人检测设备状态表
		SqmEquipmentEveryState sqmEquipmentEveryState = new SqmEquipmentEveryState();
		sqmEquipmentEveryState.setLineId(planInfo.getLINEID());
		sqmEquipmentEveryState.setEquipmentId(planInfo.getEquipmentId());
		sqmEquipmentEveryState.setEState("4");//设备故障
		sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState);

		//故障推送
		SqmPushInfo sqmPushInfo = new SqmPushInfo();
		sqmPushInfo.setPush_msg(pot_nm+"生产线"+machinePotPartInfo.getPot_part_nm()+"设备故障！故障申报人："+sysUserInfo.getUser_nm());
		sqmPushInfo.setPush_to("ALL");
		sqmPushInfo.setPush_type("equipmentfailure");
		sqmPushInfo.setPush_source(resultId);
		sqmPushInfo.setCreate_id(processor.getCurrentUserId());
		sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);

		//故障计时
		LineProductInfo lineProductInfo = new LineProductInfo();
		lineProductInfo.setLineId(lineId);
		interfaceTestTimingPushDataList(lineProductInfo,planInfo,"3", processor,DateUtil.getNow(DateUtil.Y_M_D_HMS));
		model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
	  }
		 
		return model;
	}
	
	/**
	 * 故障申报（停机维修通道）
	 * @author 刘镝
	 * @return 
	 * @throws Exception 
	 */
	public synchronized  JSONMap<String, Object>  interfaceEmergentRepair(EmergentRepairInfo emergentRepairInfo,FillingPlanInfo fillingPlanInfo,Processor processor,JSONMap<String,Object> model,List<SysCodeInfo> codeInfoList,List<FillingPlanInfo> fillingPlanInfoList,SqmEquipmentState sqmEquipmentState) throws Exception {
		String time = DateUtil.getNow(DateUtil.Y_M_D_HMS);
		SysUserInfo sysUserInfo = sysUserInfoDao.getSysUserInfoById(processor.getCurrentUserId());
		String line_nm = codeInfoList.get(0).getCode_nm();
		boolean pushYn=false;
		boolean insertEmergentRepairYn=false;
        	if(fillingPlanInfoList.size()>0) {//生产计划可查到最近该类型生产线
    				for (FillingPlanInfo fillingPlanInfo2 : fillingPlanInfoList) {
    					if(fillingPlanInfo2.getFILL_STATE()==0||fillingPlanInfo2.getFILL_STATE()==1) {//未进行、进行中
    						//修改灌装计划状态
    						fillingPlanInfo2.setFILL_STATE(4);//故障
    						updateFillingPlanInfo(fillingPlanInfo2);
    						//更新监测设备状态表
    						sqmEquipmentState.setEState("4");//设备故障
    						sqmEquipmentStateDAO.updSqmEquipmentState(sqmEquipmentState);
    					}
    				}
    				pushYn=true;
    				insertEmergentRepairYn=true;
    				model.put(SysConstant.OP_FLAG, true);
    				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
    		}else {//查不到该生产线的最近可进行的生产计划
    			pushYn=false;
    			insertEmergentRepairYn=true;
    			model.put(SysConstant.OP_FLAG, true);
    			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
    		}

		if(insertEmergentRepairYn) {//插入停机维修表
			//插入停机维修表
			emergentRepairInfo.setCreate_time(time);
			emergentRepairInfo.setJob_type("1");
			emergentRepairInfo.setRepairType("N");
			emergentRepairInfo.setRepair_person("王健,周麟,孙文学,曹万引");
			emergentRepairInfoDAO.saveEmergentRepairInfo(emergentRepairInfo);
		}
		if(pushYn) {//故障推送
			SqmPushInfo sqmPushInfo = new SqmPushInfo();
			sqmPushInfo.setPush_msg(line_nm+"生产线"+emergentRepairInfo.getRepair_content()+"故障申报人："+sysUserInfo.getUser_nm());
			sqmPushInfo.setPush_to("ALL");
			sqmPushInfo.setPush_type("emergentRepair");//停机维修通道报故障，sqm系统不可点击进入检测画面
			sqmPushInfo.setCreate_id(processor.getCurrentUserId());
			sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,fillingPlanInfo);
		}
		return model;
	}

	/**
	 * 结束本次检测
	 * @author 刘镝
	 * @throws Exception 
	 */
	public void updateFillingPlanInfoOver2(FillingPlanInfo fillingPlanInfo,Processor processor,TestOverYn testOverYn) throws Exception {
		//结束本次检测计时
		LineProductInfo lineProductInfo = new LineProductInfo();
		lineProductInfo.setLineId(fillingPlanInfo.getLINEID());
		testOverYn.setOver_yn("y");
		testOverYnDAO.updateTestOverYn(testOverYn);
		interfaceTestTimingPushDataList(lineProductInfo,fillingPlanInfo,"2", processor,DateUtil.getNow(DateUtil.Y_M_D_HMS));
	}

	/**
	 * 解除故障
	 * @author 刘镝
	 * @throws Exception 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public JSONMap<String,Object> updateFillingPlanInfoTroubleshooting(String pushId,TestResult testResult,FillingPlanInfo planInfo,String sqmNormalParameter,Processor processor) throws Exception{
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		SysUserInfo sysUserInfo = sysUserInfoDao.getSysUserInfoById(processor.getCurrentUserId());

		String pot_id="";
		String pot_nm="";
		int testNum = testOverYnDAO.queryTestOverYn(planInfo.getId(), processor.getCurrentUserId()).getTest_num();//检测次数
		planInfo.setTestNum(testNum);
		String time=DateUtil.getNow(DateUtil.Y_M_D_HMS);
		String lineId = planInfo.getLINEID();//生产线id
		String equipmentId = planInfo.getEquipmentId();//设备id
		String fault_user_id = planInfo.getFault_user_id();//故障申报人
		String fault_time = planInfo.getFault_time();//故障申报时间

		//修改灌装计划状态
		planInfo.setFILL_STATE(1);//进行中
		planInfo.setFault_time(null);
		planInfo.setEquipmentId(null);
		planInfo.setFault_user_id(null);
		updateFillingPlanInfo(planInfo);

		//更新检测数据
		TestResult result = testResultDAO.queryTestResult(planInfo.getId(), processor.getCurrentUserId(),testNum,testResult.getTestItem());

		//插入检测修改记录表
		TestResultUpdate testResultUpdate = new TestResultUpdate();
		testResultUpdate.setRid(result.getId());
		testResultUpdate.setCreate_id(result.getCreate_id());
		testResultUpdate.setCreate_time(result.getCreate_time());
		testResultUpdate.setTest_result(result.getTestResult());
		testResultUpdate.setTest_num(result.getTest_num());
		testResultUpdate.setUpdate_num(result.getUpdate_num());
		testResultUpdate.setUpdate_cause(result.getUpdate_cause());
		testResultUpdate.setUpdate_time(result.getUpdate_time());
		testResultUpdate.setCreate_time2(time);//故障解除时间
		testResultUpdateDAO.saveTestResultUpdate(testResultUpdate);

		//更新检测数据
		result.setUpdate_time(time);
		result.setUpdate_cause("故障");
		result.setTestResult("y");
		result.setrState("1");//未检
		testResultDAO.updateTestResult(result);

		//解除故障计时
		if("1".equals(lineId)||"2".equals(lineId)||"3".equals(lineId)) {//1-4L
			switch (lineId) {
			case "1":
				pot_id="38";
				pot_nm="1L";
				break;
			case "2":
				pot_id="39";
				pot_nm="4L";
				break;
			default:
				pot_id="40";
				pot_nm="3-6L";
				break;
			}

		}else if("4".equals(lineId)) {//18L
			pot_id="41";
			pot_nm="18L";
		}else {//200L+
			switch (lineId) {
			case "5":
				pot_id="42";
				pot_nm="200L-A";
				break;
			case "6":
				pot_id="42";
				pot_nm="200L-B";
				break;
			case "7":
				pot_id="43";
				pot_nm="1000L";
				break;
			case "8":
				pot_id="42";
				pot_nm="200L_DEX";
				break;
			default:
				pot_id="42";
				pot_nm="BLUK";
				break;
			}	
		}

		//更新监测设备状态表
		SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
		sqmEquipmentState.setLineId(lineId);
		sqmEquipmentState.setEquipmentId(equipmentId);
		sqmEquipmentState.setEState("6");//故障已解除待检
		sqmEquipmentStateDAO.updSqmEquipmentState(sqmEquipmentState);

		//更新他人检测设备状态表
		List<SqmEquipmentEveryState> dataList = sqmEquipmentEveryStateDAO.queryDataList("",planInfo.getLINEID(),equipmentId);
		for (SqmEquipmentEveryState sqmEquipmentEveryState2 : dataList) {
			if(!sqmEquipmentEveryState2.getInspector().equals(processor.getCurrentUserId())) {
				sqmEquipmentEveryState2.setEState(sqmEquipmentEveryState2.getOldState());
				sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState2);
			}
		}

		//更新本人检测设备状态表
		SqmEquipmentEveryState sqmEquipmentEveryState = new SqmEquipmentEveryState();
		sqmEquipmentEveryState.setLineId(planInfo.getLINEID());
		sqmEquipmentEveryState.setEquipmentId(equipmentId);
		sqmEquipmentEveryState.setInspector(processor.getCurrentUserId());
		sqmEquipmentEveryState.setEState("6");//故障已解除待检
		sqmEquipmentEveryStateDAO.updSqmEquipmentEveryState(sqmEquipmentEveryState);

		//修改推送已读状态
		updSqmPushInfoUpdate_yn(pushId,result,"all");
		
		//修改停机维修结束故障时间
		EmergentRepairInfo emergentRepairInfo=new EmergentRepairInfo();
		emergentRepairInfo.setCreate_id(fault_user_id);
		emergentRepairInfo.setCreate_time(fault_time);
		emergentRepairInfo.setPot_part_id(equipmentId);
		List<EmergentRepairInfo> list = emergentRepairInfoDAO.queryDataList(emergentRepairInfo);
		if(list.size()==1) {
			EmergentRepairInfo eri = list.get(0);
			eri.setEnd_time(time.substring(0, time.length()-3));
			eri.setRepairType("Y");
			String totalTime = getDatePoor(eri.getEnd_time(), eri.getStart_time());
			eri.setTotal_time(totalTime);
			eri.setEmergent_hours(totalTime);
			eri.setSqm_normal_parameter(URLDecoder.decode(sqmNormalParameter, "UTF-8"));
			emergentRepairInfoDAO.updateEmergentRepairInfo(eri);
		}
		
		//推送故障解除
		MachinePotPartInfo machinePotPartInfo = machinePotPartInfoDAO.getMachinePotPartInfoById(equipmentId);
		SqmPushInfo sqmPushInfo = new SqmPushInfo();//故障解除推送
		sqmPushInfo.setPush_msg(pot_nm+"生产线"+machinePotPartInfo.getPot_part_nm()+"设备故障解除！"+"故障解除人："+sysUserInfo.getUser_nm());
		sqmPushInfo.setCreate_id(processor.getCurrentUserId());
		sqmPushInfo.setPush_to("ALL");
		sqmPushInfo.setPush_type("Troubleshooting");
		sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);

		//开始检测计时
		LineProductInfo lineProductInfo = new LineProductInfo();
		lineProductInfo.setLineId(testResult.getLineId());
		interfaceTestTimingPushDataList(lineProductInfo,planInfo,"1", processor,DateUtil.getNow(DateUtil.Y_M_D_HMS));
		model.put("planInfo", planInfo);
		return model;
	}
	
	/**
	 * 解除故障（停机维修通道）
	 * @author 刘镝
	 * @throws Exception 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public JSONMap<String,Object> updateInterfaceRelieveFault(List<FillingPlanInfo> fillingPlanInfoList,EmergentRepairInfo emergentRepairInfo,FillingPlanInfo planInfo,Processor processor,String deleteYn) throws Exception{
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		String time=DateUtil.getNow(DateUtil.Y_M_D_HMS);
		String equipmentId=emergentRepairInfo.getPot_part_id();
		SysUserInfo sysUserInfo = sysUserInfoDao.getSysUserInfoById(processor.getCurrentUserId());
        for (FillingPlanInfo fillingPlanInfo : fillingPlanInfoList) {
			if(fillingPlanInfo.getFILL_STATE()==4) {//故障
				//查询是否曾经检测过
				List<TestOverYn> testOverYnList = testOverYnDAO.queryDataListByPlanId(fillingPlanInfo.getId());
                if(testOverYnList.size()>0) {
                	fillingPlanInfo.setFILL_STATE(1);//进行中
                }else {
                	fillingPlanInfo.setFILL_STATE(0);//未进行
                }
                fillingPlanDAO.updateFillingPlanInfo(fillingPlanInfo);//恢复原状态
                if("N".equals(deleteYn)) {
                   testOverYnDAO.specialUpdTestOverYnByPlanId("y",fillingPlanInfo.getId());//将未结束本轮检测的结束，重新开始下一轮
                }
			}
		}
        
		//更新监测设备状态表
        if(fillingPlanInfoList.size()>0) {
    		SqmEquipmentState sqmEquipmentState = new SqmEquipmentState();
    		sqmEquipmentState.setLineId(fillingPlanInfoList.get(0).getLINEID());
    		sqmEquipmentState.setEquipmentId(equipmentId);
    		sqmEquipmentState.setEState("1");//未检测
    		sqmEquipmentStateDAO.updSqmEquipmentState(sqmEquipmentState);
        }

		//操作停机维修表
		if("Y".equals(deleteYn)) {
			//删除停机维修记录
			emergentRepairInfoDAO.deleteEmergentRepairInfo(emergentRepairInfo);
		}else {
			//修改停机维修结束故障时间
			emergentRepairInfo.setEnd_time(time.substring(0, time.length()-3));
		    String totalTime = getDatePoor(emergentRepairInfo.getEnd_time(), emergentRepairInfo.getStart_time());
			emergentRepairInfo.setTotal_time(totalTime);
			emergentRepairInfo.setEmergent_hours(totalTime);
			emergentRepairInfo.setSqm_relieve_yn("Y");
			emergentRepairInfoDAO.updateEmergentRepairInfo(emergentRepairInfo);
		}
		
		//推送故障解除
		MachinePotPartInfo machinePotPartInfo = machinePotPartInfoDAO.getMachinePotPartInfoById(equipmentId);
		SqmPushInfo sqmPushInfo = new SqmPushInfo();//故障解除推送
		sqmPushInfo.setPush_msg(machinePotPartInfo.getPot_nm()+"生产线"+machinePotPartInfo.getPot_part_nm()+"设备故障解除！"+"故障解除人："+sysUserInfo.getUser_nm());
		sqmPushInfo.setCreate_id(processor.getCurrentUserId());
		sqmPushInfo.setPush_to("ALL");
		sqmPushInfo.setPush_type("emergentRepair");
		sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);
	    model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		return model;
	}
	
	public static String getDatePoor(String endTime, String startTime) throws ParseException {
		SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDate = sdfFrom.parse(startTime);
		Date endDate = sdfFrom.parse(endTime);
		long nm = 1000 * 60;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - startDate.getTime();
		// 计算差多少分钟
		long min = diff/ nm;
		return min+"";
		}

	/**
	 * 结束计划
	 * @author 刘镝
	 * @throws Exception 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public JSONMap<String,Object> updateFillingPlanInfoOverPlan(FillingPlanInfo fillingPlanInfo,String packCode,String abnormalRemarks,Processor processor) throws Exception{
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		if(!StringUtils.isNullOrEmpty(abnormalRemarks))abnormalRemarks=URLDecoder.decode(abnormalRemarks, "UTF-8");
		if(!StringUtils.isNullOrEmpty(packCode))packCode=URLDecoder.decode(packCode, "UTF-8");
		//灌装计划详情
		FillingPlanInfo planInfo = getFillingPlanInfoById(fillingPlanInfo.getId());
		String lineId = planInfo.getLINEID();

		//修改灌装计划
		String fILL_EDDATE=DateUtil.getNow(DateUtil.Y_M_D_HMS);
		planInfo.setFILL_STATE(3);//已完成
		planInfo.setFILL_EDDATE(fILL_EDDATE);
		planInfo.setOver_plan_user(processor.getCurrentUserId());
		updateFillingPlanInfoOver(planInfo);

		//生成检测结果inset语句
		TestingProcessInfo testingProcessInfo = testingProcessInfoDAO.getTestingProcessInfoByProductCode(planInfo.getPKG()+planInfo.getPROD(),planInfo.getLINEID()).get(0);
		List<TestItemInfo> queryContrastTestItemInfo = testItemInfoDAO.queryContrastTestItemInfo(planInfo.getId());
		String sql="insert into "+queryContrastTestItemInfo.get(0).getTableName()+" ";
		String name="(";
		String values="(";
		for (TestItemInfo testItemInfo : queryContrastTestItemInfo) {
			name+=""+testItemInfo.getTestResultNoID()+",";
			values+="'"+testItemInfo.getTestResult()+"',";
		}
		name=name+"lineId,lotnumver,produnctCode,produnctName,productType,customName,unusualYn,test_state,filling_date,create_id,create_time,user_yn,pack_code,start_pack_code,abnormal_remarks)";
		values=values+"'"+lineId+"','"+planInfo.getLOTNO()+"','"+planInfo.getPROD()+"','"+planInfo.getPRODNAME()+"','"+testingProcessInfo.getProductType()+"','"+testingProcessInfo.getCustomName()+"','n','3','"
				+planInfo.getCNFYMD()+"','"+processor.getCurrentUserId()+"','"+DateUtil.getNow(DateUtil.Y_M_D_HMS)+"',1,'"+packCode+"','"+planInfo.getStart_pack_code()+"','"+abnormalRemarks+"')";
		sql=sql+name+" values "+values;
		insertTestResultInfo(sql);

		//结束检测计时
		LineProductInfo lineProductInfo = new LineProductInfo();
		lineProductInfo.setLineId(fillingPlanInfo.getLINEID());
		interfaceTestTimingPushDataList(lineProductInfo,planInfo,"4", processor,DateUtil.getNow(DateUtil.Y_M_D_HMS));

		//通知所有人灌装计划结束
		SqmPushInfo sqmPushInfo = new SqmPushInfo();
		sqmPushInfo.setPush_msg(planInfo.getLINENAME()+"生产线,产品code："+planInfo.getPROD()+"灌装计划结束！");
		sqmPushInfo.setPush_to("ALL");
		sqmPushInfo.setCreate_id(processor.getCurrentUserId());
		sqmPushInfo.setPush_type("OverFillingPlan");
		sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);
		model.put("planInfo", planInfo);
		model.put("OverPlanYN", "Y");
		model.put(SysConstant.OP_FLAG, true);
		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		return model;
	}
	
	/**
	 * 查询同生产线同一天进行中的灌装计划数量
	 * @author 刘镝
	 * @throws Exception 
	 */
	public int queryTestingYn(FillingPlanInfo entity) throws Exception {
		return fillingPlanDAO.queryTestingYn(entity);
	}
	
	/**
	 * 查询同生产线前一天到未来五天的灌装计划（未进行、进行中）
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryNotAndProgressPlanList(FillingPlanInfo entity) throws Exception {
		return fillingPlanDAO.queryNotAndProgressPlanList(entity);
	}
	
	/**
	 * 查询同生产线前一天到未来五天的灌装计划（故障）
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryFaultPlanList(FillingPlanInfo entity) throws Exception {
		return fillingPlanDAO.queryFaultPlanList(entity);
	}
	
	/**
	 * 更新检测流程及检测项
	 * @author 刘镝
	 * @throws Exception 
	 */
	public JSONMap<String,Object> updateTestProcessAndTestIntem(JSONMap<String,Object> model) throws Exception {
		String update_time=DateUtil.getNow(DateUtil.ymd);
		TestItemInfo testItemInfo = new TestItemInfo();
		List<TestingProcessInfo> testingProcessInfoGroup = testingProcessInfoDAO.getTestingProcessInfoInLineId("1,2,3,4,5,6,7,8,10");
		int i=0;
		int j=0;
		int k=0;
		for (TestingProcessInfo testingProcessInfo : testingProcessInfoGroup) {
			List<TestingProcessInfo> testingProcessInfoByProductCode = testingProcessInfoDAO.getTestingProcessInfoByProductCode(testingProcessInfo.getProductCode(), testingProcessInfo.getLineId());
                for (TestingProcessInfo testingProcessInfo3 : testingProcessInfoByProductCode) {//流程
						testItemInfo.setLineId(testingProcessInfo3.getLineId());
						List<TestItemInfo> queryTestItemTemplatList = testItemInfoDAO.queryTestItemTemplatList(testItemInfo);
						List<TestItemInfo> queryTestItemInfoList = testItemInfoDAO.queryTestItemInfoListAllState(testingProcessInfo3.getLineId(), testingProcessInfo3.getProductCode(),"","");
						for (TestItemInfo testItemInfo2 : queryTestItemTemplatList) {
							for (TestItemInfo testItemInfo3 : queryTestItemInfoList) {
								if((testItemInfo2.getEquipmentId().equals(testItemInfo3.getEquipmentId()))&&(testItemInfo2.getTestItem().equals(testItemInfo3.getTestItem()))&&(!testItemInfo2.getOrderNum().equals(testItemInfo3.getOrderNum()))) {
									testItemInfo3.setOrderNum(testItemInfo2.getOrderNum());
									testItemInfo3.setUpdate_time(update_time);
									testItemInfo3.setUpdate_id("auto");
									testItemInfoDAO.updateTestItemInfo(testItemInfo3);
									j++;
								}
							}
					}
			}
		}
		
		List<TestingProcessInfo> testingProcessInfoGroup2 = testingProcessInfoDAO.getTestingProcessInfoInLineId("1,2,3,4,5,6,7,8,10");//有理桶机的线
		for (TestingProcessInfo testingProcessInfo : testingProcessInfoGroup2) {
			List<TestItemInfo> queryTestItemInfoList = testItemInfoDAO.queryTestItemInfoListAllState(testingProcessInfo.getLineId(), testingProcessInfo.getProductCode(),"灌装机","温度");
		    if(queryTestItemInfoList.size()==0) {
		    	String lineId = testingProcessInfo.getLineId();
		    	String equipmentId="";
		    	String orderNum="";
                switch (lineId) {
				case "1":
					equipmentId="408";
					orderNum="16";
					break;
				case "2":
					equipmentId="425";
					orderNum="16";
					break;
				case "3":
					equipmentId="441";
					orderNum="16";
					break;
				case "4":
					equipmentId="456";
					orderNum="13";
					break;
				case "5":
					equipmentId="466";
					orderNum="24";
					break;
				case "6":
					equipmentId="466";
					orderNum="24";
					break;
				case "7":
					equipmentId="472";
					orderNum="14";
					break;
				case "8":
					equipmentId="466";
					orderNum="24";
					break;
				default:
					equipmentId="466";
					orderNum="24";
					break;
				}
                testItemInfo.setEquipmentId(equipmentId);
                testItemInfo.setEquipmentName("灌装机");
                testItemInfo.setCreate_id("auto");
                testItemInfo.setCreate_time(update_time);
                testItemInfo.setTestItem("温度");
                testItemInfo.setOrderNum(orderNum);
                testItemInfo.setUserYn("Y");
                testItemInfo.setLineId(lineId);
                testItemInfo.setLineName(testingProcessInfo.getLineName());
                testItemInfo.setProductCode(testingProcessInfo.getProductCode());
                testItemInfo.setProductName(testingProcessInfo.getProductName());
		    	testItemInfoDAO.saveTestItemInfo(testItemInfo);
		    	k++;
		   }
		}
		 model.put("修改检测流程条数：", i);
		 model.put("修改检测项条数：", j);
		 model.put("新增检测项条数：", k);
		return model;
	}
}

