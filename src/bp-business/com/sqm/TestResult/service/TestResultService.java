package com.sqm.TestResult.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.repast.core.system.Pagination;
import com.sqm.TestResult.dao.TestResultDAO;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
/**
 * 表test_result的SERVICE类
 * @author 刘镝
 */
@Service
public class TestResultService {
	private static Logger logger = Logger.getLogger("logger");

	@Resource
	private TestResultDAO testResultDAO;

	/**
	 * 分页查询信息
	 * @author 刘镝
	 */
	public Pagination<TestResult> queryTestResultList(TestResult testResult, int pageIndex, int pageSize,String type) {
		return testResultDAO.queryDataList(testResult, pageIndex, pageSize,type);
	}

	/**
	 * 查询详细信息
	 * @author 刘镝
	 */
	public TestResult getTestResultById(String testResult_id) {
		return testResultDAO.getTestResultById(testResult_id);
	}

	/**
	 * 新增信息
	 * @author 刘镝
	 */
	public String insertTestResult(TestResult testResult){
		return testResultDAO.saveTestResult(testResult);
	}

	/**
	 * 修改信息
	 * @author 刘镝
	 */
	public int updTestResult(TestResult testResult){
		return testResultDAO.updateTestResult(testResult);
	}

	/**
	 * 作废信息
	 * @author 刘镝
	 */
	public int cancelTestResult(TestResult testResult) {
		return testResultDAO.deleteTestResult(testResult);
	}

	/**
	 * 添加前/更新前check
	 * @author 刘镝
	 */
	public boolean dbExistCheck(TestResult testResult){
		return testResultDAO.checkTestResultEO(testResult);
	}

	/**
	 * 查询当前结束了几轮检测
	 * @author 刘镝
	 */
	public int queryDataTestNum(String create_id,String planId){
		return testResultDAO.queryDataTestNum(create_id,planId);
	}
	/**
	 * 查询本次检测项历史
	 * @author 刘镝
	 */
	public TestResult queryTestResult(String planId,String create_id,int test_num,String testItem){
		return testResultDAO.queryTestResult(planId, create_id, test_num,testItem);
	}

	/**
	 * 通过推送push_id获取需要修改的检测结果集合
	 * @author 刘镝
	 */
	public List<TestResult> queryTestResultListByPush(SqmPushInfo entity,String inspector){
		return testResultDAO.queryTestResultListByPush(entity, inspector);
	}
	
	/**
	 * 通过推送push_id获取需要修改的检测项集合
	 * @author 刘镝
	 */
	public List<TestItemInfo> queryTestItemInfoListByPush(SqmPushInfo entity,String inspector){
		return testResultDAO.queryTestItemInfoListByPush(entity, inspector);
	}
	
    /**
     * 通过灌装计划信息获取需要修改的检测项集合
     * @author 刘镝
     */
    public List<TestItemInfo> QueryTestResultListByPlan(TestResult entity){
    	return testResultDAO.QueryTestResultListByPlan(entity);
    }
    
    /**
     * 查询最近一条检测记录
     * @author 刘镝
     */
      public TestResult queryTestResultLately(String planId){
		return testResultDAO.queryTestResultLately(planId);
      }
      
  	/**
  	 * 查询已经检测过的设备id
  	 * @author 刘镝
  	 */
  	public List<TestItemInfo> QueryOldTestResultEquipmentIdList(TestResult testResult){
		return testResultDAO.QueryOldTestResultEquipmentIdList(testResult);
  		
  	}
}

