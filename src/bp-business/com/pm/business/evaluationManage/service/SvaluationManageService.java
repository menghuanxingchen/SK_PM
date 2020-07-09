package com.pm.business.evaluationManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.dao.SvaluationManageDAO;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class SvaluationManageService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private SvaluationManageDAO svaluationManageDAO;

		/**
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<EvaluationManageInfo> queryEvaluationManageInfoList(EvaluationManageInfo entity, int pageIndex, int pageSize) {
		return svaluationManageDAO.queryEvaluationManageInfoList(entity, pageIndex, pageSize);
  	}
  	/**
	 * 分页查询信息
	 * @author gao
	 */
	public Pagination<EvaluationManageInfo> queryEvaluationSaveInfoList(EvaluationManageInfo entity, int pageIndex, int pageSize) {
		return svaluationManageDAO.queryEvaluationSaveInfoList(entity, pageIndex, pageSize);
	}
	/**
	 * 生成信息
	 * @author gao
	 */
	public int saveEvaluationManageInfo(EvaluationManageInfo entity) {
		try {
			logger.info("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo 异常！");
		}
	
	  return svaluationManageDAO.saveEvaluationManageInfo(entity);
	}
	
	/**
	 * 结束信息
	 * @author gao
	 */
	public int saveEndEvaluationManageInfo(EvaluationManageInfo entity) {
		try {
			logger.info("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo 异常！");
		}
	
	  return svaluationManageDAO.saveEndEvaluationManageInfo(entity);
	}
	/**
	 * check
	 * @author gao
	 */
	public List<EvaluationManageInfo> checkListValueData(EvaluationManageInfo entity) {
		return svaluationManageDAO.checkListValueData(entity);
	}
	/**
	 * 保存
	 * @author gao
	 */
	public int saveTemporaryEntityData(EvaluationManageInfo entity,Processor processor) {
		try {
			logger.info("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：EvaluationManageInfo--saveEvaluationManageInfo 异常！");
		}
		int i = 0;
		
		if(processor.getCurrentUserId().equals(entity.getEvaluation_one())){
			i += svaluationManageDAO.saveTemporaryEvaluationOne(entity);
		}else if(processor.getCurrentUserId().equals(entity.getEvaluation_two())){
			i += svaluationManageDAO.saveTemporaryEvaluationTwo(entity);
		}
	  return i;
	}
}
