package com.pm.business.machinemanager.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.machinemanager.dao.MachinePotPartInfoDAO;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
/**
 * 表machine_pot_part_info的SERVICE类
 * @author 刘镝
 */
@Service
public class MachinePotPartInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private MachinePotPartInfoDAO machinePotPartInfoDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<MachinePotPartInfo> queryMachinePotPartInfoList(MachinePotPartInfo machinePotPartInfo, int pageIndex, int pageSize) {
        return machinePotPartInfoDAO.queryMachinePotPartInfoList(machinePotPartInfo, pageIndex, pageSize);
    }
    /**
     * 查询信息
     * @author 刘镝
     */
    public List<MachinePotPartInfo> queryMachinePotPartInfoList(MachinePotPartInfo machinePotPartInfo) {
        return machinePotPartInfoDAO.queryMachinePotPartInfoList(machinePotPartInfo);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public MachinePotPartInfo getMachinePotPartInfoById(String machinePotPartInfo_id) {
        return machinePotPartInfoDAO.getMachinePotPartInfoById(machinePotPartInfo_id);
    }

    /**
     * 新增信息
     * @author 姜易平
     * @throws Exception 
     */
    public int insertMachinePotPartInfo(MachinePotPartInfo machinePotPartInfo,Processor processor) throws Exception{
    	machinePotPartInfo.setCreate_id(processor.getCurrentUserId());
        return machinePotPartInfoDAO.saveMachinePotPartInfo(machinePotPartInfo);
    }

    /**
     * 修改信息
     * @author 姜易平
     * @throws Exception 
     */
    public int updMachinePotPartInfo(MachinePotPartInfo machinePotPartInfo,Processor processor) throws Exception{
    	machinePotPartInfo.setUpdate_id(processor.getCurrentUserId());
        return machinePotPartInfoDAO.updateMachinePotPartInfo(machinePotPartInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelMachinePotPartInfo(MachinePotPartInfo machinePotPartInfo) {
        return machinePotPartInfoDAO.deleteMachinePotPartInfo(machinePotPartInfo);
    }

    /**
     * 添加前/更新前check
     * @author 姜易平
     */
    public boolean dbExistCheck(MachinePotPartInfo machinePotPartInfo){
        return machinePotPartInfoDAO.checkMachinePotPartInfoEO(machinePotPartInfo);
    }

    /**
     * 根据设备ID查询所属部件列表
     * @param machinePotPartInfo
     * @author 姜易平
     */
	public List<MachinePotPartInfo> queryDataListByPotId(MachinePotPartInfo machinePotPartInfo) {
		return machinePotPartInfoDAO.queryMachinePotPartInfoList(machinePotPartInfo);
	}
	
	/**
	 * 停机维修用
	 * @author 刘镝
	 */
     public List<MachinePotPartInfo> queryMachinePotPartInfoEmergentList(MachinePotPartInfo entity) {
		return machinePotPartInfoDAO.queryMachinePotPartInfoEmergentList(entity);
	 }
	   
	/**
     * 查询所属部件列表
     * @param machinePotPartInfo
     * @author gao
     */
	public List<MachinePotPartInfo> queryDataList(MachinePotPartInfo machinePotPartInfo) {
		return machinePotPartInfoDAO.queryDataList(machinePotPartInfo);
	}
	/**
     * 查询所属部件列表
     * @param machinePotPartInfo
     * @author gao
     */
	public List<MachinePotPartInfo> queryMachinePotPartInfobySQMList(MachinePotPartInfo machinePotPartInfo) {
		return machinePotPartInfoDAO.queryMachinePotPartInfobySQMList(machinePotPartInfo);
	}
	
}

