package com.pm.business.sqmmanage.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.TestingProcessInfoDAO;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
@Service
public class TestingProcessInfoService {
	private static Logger logger = Logger.getLogger("logger");

	@Resource
	private TestingProcessInfoDAO testingProcessInfoDAO;

	public List<TestingProcessInfo> queryTestingProcessInfoList(TestingProcessInfo entity) {
		return testingProcessInfoDAO.queryTestingProcessInfoList(entity);
	}

	public List<TestingProcessInfo> queryTestingProcessTempInfoList(TestingProcessInfo entity) {
		return testingProcessInfoDAO.queryTestingProcessTempInfoList(entity);
	}
	/**
	 * 分页查询用户信息
	 * @author fish
	 */
	public Pagination<TestingProcessInfo> queryTestingProcessInfoList(TestingProcessInfo entity, int pageIndex, int pageSize) {
		return testingProcessInfoDAO.queryTestingProcessInfoList(entity, pageIndex, pageSize);
	}

	/**
	 * 查询产品检测流程
	 * @author 刘镝
	 */
	public List<TestingProcessInfo> queryTestingProcessInfoListNopage(TestingProcessInfo entity,Processor processor) {
		return testingProcessInfoDAO.queryTestingProcessInfoListNopage(entity,processor);
	}

	/**
	 * 查询产品监测流程
	 * @author 刘镝
	 */
	public List<TestingProcessInfo> queryTestingProcessInfoListMonitor(TestingProcessInfo entity) {
		return testingProcessInfoDAO.queryTestingProcessInfoListMonitor(entity);
	}

	/**
	 * 新增用户信息
	 * @author fish
	 */
	public int saveTestingProcessInfo(TestingProcessInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--saveSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--saveSysUserInfo 异常！");
		}

		return testingProcessInfoDAO.saveTestingProcessInfo(entity);
	}

	/**
	 * 修改用户信息
	 * @author fish
	 */
	public int updateTestingProcessInfo(TestingProcessInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--updateSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--updateSysUserInfo 异常！");
		}

		return testingProcessInfoDAO.updateTestingProcessInfo(entity);
	}

	/**
	 * 删除用户信息
	 * @author 刘镝
	 */
	public int deleteTestingProcessInfo(TestingProcessInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--deleteSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--deleteSysUserInfo 异常！");
		}
		return testingProcessInfoDAO.deleteTestingProcessInfo(entity);
	}

	public TestingProcessInfo getTestingProcessInfoById(String id) {
		return testingProcessInfoDAO.getTestingProcessInfoById(id);
	}

	public Boolean checkTestingProcessInfo(TestingProcessInfo testingProcessInfo) {
		return testingProcessInfoDAO.checkTestingProcessInfo(testingProcessInfo);
	}

	public  List<TestingProcessInfo> getTestingProcessInfoByProductCode(String productCode,String lineId) {
		return testingProcessInfoDAO.getTestingProcessInfoByProductCode(productCode,lineId);
	}
	
	public List<TestingProcessInfo> getTestingProcessInfoTempByLineId(String lineId) {
		return testingProcessInfoDAO.getTestingProcessInfoTempByLineId(lineId);
	}
}
