package com.pm.business.sqmtestingmanage.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.FillingPlanDAO;
import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.dao.SvaluationManageDAO;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.sqmtestingmanage.dao.TestResult18InfoDAO;
import com.pm.persistence.sqmtestingmanage.dao.TestResult200InfoDAO;
import com.pm.persistence.sqmtestingmanage.dao.TestResult4InfoDAO;
import com.pm.persistence.sqmtestingmanage.entity.TestResult18Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult200Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult4Info;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class SqmtestingManageService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private TestResult4InfoDAO testResult4InfoDAO;
	
	@Resource
	private TestResult18InfoDAO testResult18InfoDAO;
	
	@Resource
	private TestResult200InfoDAO testResult200InfoDAO;
	
	@Resource
	private FillingPlanDAO fillingPlanDAO;
	

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
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<FillingPlanInfo> queryLineProductInfoList(FillingPlanInfo entity, int pageIndex, int pageSize) {
		return fillingPlanDAO.queryLineProductInfoList(entity,pageIndex, pageSize);
  	}
}
