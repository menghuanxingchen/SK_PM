package com.pm.business.systemfunction.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.CheckitemInfoDAO;
import com.pm.persistence.sysinfo.dao.SysCodeInfoDAO;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service("SupplierService")
public class SupplierService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource(name="CheckitemInfoDAO")
	private CheckitemInfoDAO checkitemInfoDAO;
	@Resource(name="SysCodeInfoDAO")
	private SysCodeInfoDAO sysCodeInfoDAO;
	
	
	/**
	 * 查询列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<SysCodeInfo> querySysCodeInfoList(SysCodeInfo sysCodeInfo, int pageIndex, int pageSize) {
		Pagination<SysCodeInfo> infoList = sysCodeInfoDAO.querySysCodeInfoList(sysCodeInfo, pageIndex, pageSize);
		return infoList;
	}
	
	/**
	 * 保存
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int saveSysCodeInfo(SysCodeInfo sysCodeInfo,
									Processor processor) throws Exception {
		try {
			logger.info("事务操作方法：SupplierService--saveSysCodeInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SupplierService--saveSysCodeInfo 异常！");
		}
		sysCodeInfo.setCreate_id(processor.getCurrentUserId());
		sysCodeInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		String id = sysCodeInfoDAO.getUUId();
		sysCodeInfo.setCode_id(id);
		//code_value 递增
		SysCodeInfo temp = new SysCodeInfo();
		temp.setCode_group_value(sysCodeInfo.getCode_group_value());
		int num=sysCodeInfoDAO.querySysCodeInfoListNoPage(temp).size()+1;
		sysCodeInfo.setCode_value(num+"");
		int saveCnt =sysCodeInfoDAO.saveSysCodeInfo(sysCodeInfo);		
		return saveCnt;
	}
	
	/**
	 * 查询entity
	 * @param entity
	 * @param processor
	 * @return
	 */
	public SysCodeInfo querySysCodeInfo(SysCodeInfo sysCodeInfo) {
		SysCodeInfo info = sysCodeInfoDAO.getSysCodeInfoById(sysCodeInfo.getCode_id());
		return info;
	}
	/**
	 * update 
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateSysCodeInfo(SysCodeInfo sysCodeInfo,Processor processor) throws Exception {
		try {
			logger.info("事务操作方法：SupplierService--updateSysCodeInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SupplierService--updateSysCodeInfo 异常！");
		}
		sysCodeInfo.setUpdate_id(processor.getCurrentUserId());
		sysCodeInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		int update_flag=sysCodeInfoDAO.updateSysCodeInfo(sysCodeInfo);
		return update_flag;
	}
	
}
