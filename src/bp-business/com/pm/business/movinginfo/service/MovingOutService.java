package com.pm.business.movinginfo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.dao.SvaluationManageDAO;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.movinginfo.dao.MovingInInfoDAO;
import com.pm.persistence.movinginfo.dao.MovingOutInfoDAO;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.movinginfo.entity.MovingOutInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class MovingOutService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private MovingOutInfoDAO movingOutInfoDAO;

	
	/**
	 * 生成信息
	 * @author gao
	 */
	public int saveMovingOutApplyInfo(MovingOutInfo entity,Processor processor) {
		try {
			logger.info("事务操作方法：MovingInService--saveMovingInApplyInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MovingInService--saveMovingInApplyInfo 异常！");
		}
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 
		entity.setCreate_id(processor.getCurrentUserId());
		entity.setCreate_time(df.format(day));
		entity.setUser_yn("1");
	  return movingOutInfoDAO.saveMovingOutApplyInfo(entity);
	}
	
	/**
	 * 保存物品list
	 * @author gao
	 */
	public int saveMovingGoodsInfoData(MovingGoodsInfo entity,Processor processor) {
		try {
			logger.info("事务操作方法：MovingInService--saveMovingInApplyInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MovingInService--saveMovingInApplyInfo 异常！");
		}
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 
		entity.setCreate_id(processor.getCurrentUserId());
		entity.setCreate_time(df.format(day));
		entity.setUser_yn("1");
	  return movingOutInfoDAO.saveMovingGoodsInfoData(entity);
	}
	
	/**
	 * 更新
	 * @author gao
	 */
	public int updateMovingOutInfo(MovingOutInfo entity) {
		try {
			logger.info("事务操作方法：MovingInService--updateMovingInInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MovingInService--updateMovingInInfo 异常！");
		}

	  return movingOutInfoDAO.updateMovingOutInfo(entity);
	}
	
	/**
	 * 获取
	 * @author gao
	 */
	public List<MovingOutInfo> queryMovingGoodsInfoList(MovingOutInfo entity) {
		try {
			logger.info("事务操作方法：MovingInService--queryMovingGoodsInfoList");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MovingInService--queryMovingGoodsInfoList 异常！");
		}

	  return movingOutInfoDAO.queryMovingInfoList(entity);
	}
}
