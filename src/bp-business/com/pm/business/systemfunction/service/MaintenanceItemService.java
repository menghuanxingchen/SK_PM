package com.pm.business.systemfunction.service;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.systemfunction.dao.MaintenanceItemInfoDAO;
import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service("MaintenanceItemService")
public class MaintenanceItemService {
	
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource(name="MaintenanceItemInfoDAO")
	private MaintenanceItemInfoDAO maintenanceItemInfoDAO;
	
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<MaintenanceItemInfo> queryMaintainItemListpage(MaintenanceItemInfo en, int pageIndex, int pageSize) {
		Pagination<MaintenanceItemInfo> machineList = 
				maintenanceItemInfoDAO.queryMaintainItemListpage(en,pageIndex,pageSize);
		
		return machineList;
	}
	/**
	 * 保存设备
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int saveMaintenanceItemInfo(MaintenanceItemInfo en,
									Processor processor) throws Exception {
	 try {
			logger.info("事务操作方法：MaintenanceItemService--saveMaintenanceItemInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MaintenanceItemService--saveMaintenanceItemInfo 异常！");
		}
		String id = maintenanceItemInfoDAO.getUUId();
		en.setMaintenance_id(id);
		en.setCreate_id(processor.getCurrentUserId());
		en.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		int saveCnt = maintenanceItemInfoDAO.saveMaintenanceItemInfo(en);
		
		return saveCnt;
	}
	
	/**
	 * 
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public MaintenanceItemInfo getMaintenanceItemInfo(MaintenanceItemInfo machineInfo)throws Exception {
		MaintenanceItemInfo en=maintenanceItemInfoDAO.getMaintenanceItemInfoById(machineInfo.getMaintenance_id());
		return en;
	}
	/**
	 * update 
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateMaintenanceItemInfo(MaintenanceItemInfo machineInfo,Processor processor) throws Exception {
		try {
			logger.info("事务操作方法：MaintenanceItemService--updateMaintenanceItemInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MaintenanceItemService--updateMaintenanceItemInfo 异常！");
		}
		machineInfo.setUpdate_id(processor.getCurrentUserId());
		machineInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		int update_flag=maintenanceItemInfoDAO.updateMaintenanceItemInfo(machineInfo);
		return update_flag;
	}
}
