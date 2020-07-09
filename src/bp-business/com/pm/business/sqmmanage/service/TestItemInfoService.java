package com.pm.business.sqmmanage.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.dao.TestItemInfoDAO;
import com.pm.persistence.Sqmmanage.dao.TestingProcessInfoDAO;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class TestItemInfoService {
	private static Logger logger = Logger.getLogger("logger");

	@Resource
	private TestItemInfoDAO testItemInfoDAO;

	/**
	 * 分页查询用户信息
	 * @author fish
	 */
	public Pagination<TestItemInfo> queryTestItemInfoList(TestItemInfo entity, int pageIndex, int pageSize) {
		return testItemInfoDAO.queryTestItemInfoList(entity, pageIndex, pageSize);
	}

	/**
	 * 查询检测项
	 * @author 刘镝
	 */
	public List<TestItemInfo> queryTestItemInfoList(String lineId,String equipmentId,String productCode) {
		return testItemInfoDAO.queryTestItemInfoList(lineId, equipmentId, productCode);

	}

	/**
	 * 新增用户信息
	 * @author fish
	 */
	public int saveTestItemInfo(TestItemInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--saveSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--saveSysUserInfo 异常！");
		}

    	
      return testItemInfoDAO.saveTestItemInfo(entity);
    }
    


	/**
	 * 修改用户信息
	 * @author fish
	 */
	public int updateTestItemInfo(TestItemInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--updateSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--updateSysUserInfo 异常！");
		}

		return testItemInfoDAO.updateTestItemInfo(entity);
	}

	/**
	 * 删除用户信息
	 * @author 刘镝
	 */
	public int deleteTestItemInfo(TestItemInfo entity) {
		try {
			logger.info("事务操作方法：SysUserInfoService--deleteSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--deleteSysUserInfo 异常！");
		}
		return testItemInfoDAO.deleteTestItemInfo(entity);
	}

	public TestItemInfo getTestItemInfoById(String id) {
		return testItemInfoDAO.getTestItemInfoById(id);
	}

	/**
	 * 查询检测项模板
	 * @param TestItemInfo
	 * @author gao
	 */

	public List<TestItemInfo> queryTestItemTemplatList(TestItemInfo testItemInfo) {
		return testItemInfoDAO.queryTestItemTemplatList(testItemInfo);
	}

	public Boolean checkLineItemInfo(TestItemInfo testItemInfo) {
		return testItemInfoDAO.checkLineItemInfo(testItemInfo);
	}

	/**
	 * 联合查询检测结果与表字段对应关系
	 * @author 刘镝
	 */
	public List<TestItemInfo> queryContrastTestItemInfo(String planId) {
		return testItemInfoDAO.queryContrastTestItemInfo(planId);
	}
	
    /**
     * 查询灌装计划与检测流程、检测项是否匹配成功
     * @author 刘镝
     */
      public boolean queryFillingPlanAndTestingProcessAndTestingItmeMatchingYn(String planId) {
    	  return testItemInfoDAO.queryFillingPlanAndTestingProcessAndTestingItmeMatchingYn(planId);
      }
}
