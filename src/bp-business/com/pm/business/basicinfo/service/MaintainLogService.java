package com.pm.business.basicinfo.service;


import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.MaintainlogInfoDAO;
import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
@Service("MaintainLogService")
public class MaintainLogService {
	
	@Resource(name="MaintainlogInfoDAO")
	private MaintainlogInfoDAO maintainlogInfoDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<MaintainlogInfo> getMaintainLogList(MaintainlogInfo maintainlogInfo, int pageIndex, int pageSize) {
		Pagination<MaintainlogInfo> infoList = 
				maintainlogInfoDAO.queryMaintainlogInfoList(maintainlogInfo,pageIndex,pageSize);
		return infoList;
	}
	/**
	 * delete
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int deleteMaintainlogInfo(String id) throws Exception {				
		MaintainlogInfo info= maintainlogInfoDAO.getEntityById(id, MaintainlogInfo.class);
		 try {
				logger.info("事务操作方法：MaintainLogService--deleteMaintainlogInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：MaintainLogService--deleteMaintainlogInfo 异常！");
			}
		int delete_flag=maintainlogInfoDAO.deleteMaintainlogInfo(info);
		return delete_flag;
	}
	/**
	 * 保存
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int saveMaintainLogInfo(MaintainlogInfo maintainlogInfo,
									Processor processor) throws Exception {
		maintainlogInfo.setCreate_id(processor.getCurrentUserId());
		maintainlogInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		String ci_id = maintainlogInfoDAO.getUUId();
		maintainlogInfo.setMaintainlog_id(ci_id);
		maintainlogInfo.setCheck_state(PmConstant.PLAN_STATE_01);
		maintainlogInfo.setDept_type(processor.getDept());
		maintainlogInfo.setConfirm_yn(PmConstant.STATE_N);
		 try {
				logger.info("事务操作方法：MaintainLogService--saveMaintainlogInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：MaintainLogService--saveMaintainlogInfo 异常！");
			}
		int saveCnt =maintainlogInfoDAO.saveMaintainlogInfo(maintainlogInfo);
		
		return saveCnt;
	}
	/**
	 * 
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public MaintainlogInfo getMaintainLogInfo(MaintainlogInfo maintainlogInfo)throws Exception {
		MaintainlogInfo enti=maintainlogInfoDAO.getMaintainlogInfoById(maintainlogInfo.getMaintainlog_id());
		return enti;
	}
	/**
	 * update 
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateMaintainlogInfo(MaintainlogInfo maintainlogInfo,Processor processor) throws Exception {
		if("".equals(maintainlogInfo.getCheck_state())||maintainlogInfo.getCheck_state()==null){
			maintainlogInfo.setUpdate_id(processor.getCurrentUserId());
			maintainlogInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		}else{//确认 状态
			String createId = processor.getCurrentUserId();
			maintainlogInfo.setCheck_user(createId);
		}
		try {
			logger.info("事务操作方法：MaintainLogService--updateMaintainlogInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MaintainLogService--updateMaintainlogInfo 异常！");
		}
		int update_flag=maintainlogInfoDAO.updateMaintainlogInfo(maintainlogInfo);
		return update_flag;
	}
	
	/**
	 * update 
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateConfirmData(MaintainlogInfo maintainlogInfo,Processor processor) throws Exception {
		maintainlogInfo.setUpdate_id(processor.getCurrentUserId());
		maintainlogInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		maintainlogInfo.setConfirm_yn(PmConstant.STATE_Y);
		try {
			logger.info("事务操作方法：MaintainLogService--updateMaintainlogInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MaintainLogService--updateMaintainlogInfo 异常！");
		}
		int update_flag=maintainlogInfoDAO.updateMaintainlogInfo(maintainlogInfo);
		return update_flag;
	}
	/**
	 * update state 批量
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateStateBatch(MaintainlogInfo maintainlogInfo,Processor processor) throws Exception {
		maintainlogInfo.setUpdate_id(processor.getCurrentUserId());
		maintainlogInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		maintainlogInfo.setConfirm_yn(PmConstant.STATE_Y);
		try {
			logger.info("事务操作方法：MaintainLogService--updateStateBatch");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MaintainLogService--updateStateBatch 异常！");
		}
		int update_flag=maintainlogInfoDAO.updateStateBatch(maintainlogInfo);
		return update_flag;
	}
}
