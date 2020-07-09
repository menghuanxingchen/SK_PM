package com.pm.business.basicinfo.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.MachineInfoDAO;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service("MachineService")
public class MachineService {
	@Resource(name="MachineInfoDAO")
	private MachineInfoDAO machineInfoDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<MachineInfo> getMachineList(MachineInfo machineInfo, int pageIndex, int pageSize) {
		Pagination<MachineInfo> machineList = 
				machineInfoDAO.queryMachineInfoList(machineInfo,pageIndex,pageSize);
		
		return machineList;
	}
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<MachineInfo> getMachineListNames(MachineInfo machineInfo, int pageIndex, int pageSize) {
		Pagination<MachineInfo> machineList = 
				machineInfoDAO.queryMachineInfoListNames(machineInfo,pageIndex,pageSize);
		
		return machineList;
	}
	/**
	 * delete设备
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int deleteMachine(String id) throws Exception {				
		MachineInfo machineInfo	= machineInfoDAO.getMachineInfoById(id);
		try {
			logger.info("事务操作方法：MachineService--deleteMachine");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MachineService--deleteMachine 异常！");
		}
		int delete_flag=machineInfoDAO.deleteMachineInfo(machineInfo);
		return delete_flag;
	}
	
	/**
	 * 保存设备
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int saveMachineInfo(MachineInfo machineInfo,
									Processor processor) throws Exception {
		String machine_id = machineInfoDAO.getUUId();
		machineInfo.setMachine_id(machine_id);
		machineInfo.setCreate_id(processor.getCurrentUserId());
		machineInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		try {
			logger.info("事务操作方法：MachineService--saveMachineInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MachineService--saveMachineInfo 异常！");
		}
		int saveCnt = machineInfoDAO.saveMachineInfo(machineInfo);
		
		return saveCnt;
	}
	/**
	 * update 设备
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateMachineInfo(MachineInfo machineInfo,Processor processor) throws Exception {
		machineInfo.setUpdate_id(processor.getCurrentUserId());
		machineInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		try {
			logger.info("事务操作方法：MachineService--updateMachineInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：MachineService--updateMachineInfo 异常！");
		}
		int update_flag=machineInfoDAO.updateMachineInfo(machineInfo);
		return update_flag;
	}
	
	/**
	 * machineLIst 无分页
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<MachineInfo> getMachineListNo(MachineInfo machineInfo)throws Exception {
		List<MachineInfo> machineInfoList=machineInfoDAO.queryMachineInfoListNo(machineInfo);
		return machineInfoList;
	}
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<MachineInfo> getMachineListPop(MachineInfo machineInfo,String ids,int pageIndex, int pageSize) {
		Pagination<MachineInfo> machineList = 
				machineInfoDAO.queryMachineInfoListPop(machineInfo,ids,pageIndex,pageSize);
		return machineList;
	}
	
	/**
	 * machineLIst 无分页
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<MachineInfo> getMachineListCheckName(MachineInfo machineInfo)throws Exception {
		List<MachineInfo> machineInfoList=machineInfoDAO.getMachineListCheckName(machineInfo);
		return machineInfoList;
	}
}
