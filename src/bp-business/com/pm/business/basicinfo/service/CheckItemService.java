package com.pm.business.basicinfo.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.CheckitemInfoDAO;
import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service("CheckItemService")
public class CheckItemService {
	@Resource(name="CheckitemInfoDAO")
	private CheckitemInfoDAO checkitemInfoDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 查询检查项目列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<CheckitemInfo> getCheckItemList(CheckitemInfo checkitemInfo, int pageIndex, int pageSize) {
		Pagination<CheckitemInfo> machineList = checkitemInfoDAO.queryCheckitemInfoList(checkitemInfo, pageIndex, pageSize);
				
		
		return machineList;
	}
	/**
	 * 查询检查项目列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<CheckitemInfo> getCheckItemListNames(CheckitemInfo checkitemInfo, int pageIndex, int pageSize) {
		Pagination<CheckitemInfo> machineList = checkitemInfoDAO.queryCheckitemInfoListNames(checkitemInfo, pageIndex, pageSize);
				
		
		return machineList;
	}
	/**
	 * delete检查项目
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int deleteCheckitem(String id) throws Exception {				
		CheckitemInfo checkitemInfo	= checkitemInfoDAO.getCheckitemInfoById(id);
		try {
			logger.info("事务操作方法：CheckItemService--deleteCheckitem");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：CheckItemService--deleteCheckitem 异常！");
		}
		int delete_flag=checkitemInfoDAO.deleteCheckitemInfo(checkitemInfo);
		return delete_flag;
	}
	
	/**
	 * 保存检查项目
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int saveCheckItemInfo(CheckitemInfo checkitemInfo,
									Processor processor) throws Exception {
		checkitemInfo.setCreate_id(processor.getCurrentUserId());
		checkitemInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		String ci_id = checkitemInfoDAO.getUUId();
		checkitemInfo.setCheck_project_id(ci_id);
		try {
			logger.info("事务操作方法：CheckItemService--saveCheckitemInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：CheckItemService--saveCheckitemInfo 异常！");
		}
		int saveCnt =checkitemInfoDAO.saveCheckitemInfo(checkitemInfo);
		
		return saveCnt;
	}
	/**
	 * update 设备
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateCheckitemInfo(CheckitemInfo checkitemInfo,Processor processor) throws Exception {
		checkitemInfo.setUpdate_id(processor.getCurrentUserId());
		checkitemInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		try {
			logger.info("事务操作方法：CheckItemService--updateCheckitemInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：CheckItemService--updateCheckitemInfo 异常！");
		}
		int update_flag=checkitemInfoDAO.updateCheckitemInfo(checkitemInfo);
		return update_flag;
	}
	
	/**
	 * CheckitemInfoList 无分页
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<CheckitemInfo> getCheckitemListNo(CheckitemInfo checkitemInfo)throws Exception {
		List<CheckitemInfo> CheckitemInfoList=checkitemInfoDAO.queryCheckitemInfoListNo(checkitemInfo);
		return CheckitemInfoList;
	}
	/**
	 * 查询巡检计划列表pop
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<CheckitemInfo> getCheckitemListPop(CheckitemInfo checkitemInfo,String ids,int pageIndex, int pageSize) {
		Pagination<CheckitemInfo> checkitemList = 
				checkitemInfoDAO.queryCheckitemListPop(checkitemInfo,ids,pageIndex, pageSize);
		
		return checkitemList;
	}
	/**
	 * CheckitemInfoList 无分页
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<CheckitemInfo> getCheckitemListCheckName(CheckitemInfo checkitemInfo)throws Exception {
		List<CheckitemInfo> CheckitemInfoList=checkitemInfoDAO.queryCheckitemInfoListCheckName(checkitemInfo);
		return CheckitemInfoList;
	}
}
