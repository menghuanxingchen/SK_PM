package com.pm.business.machinemanager.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.machinemanager.dao.MachinePotInfoDAO;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
/**
 * 表machine_pot_info的SERVICE类
 * @author 刘镝
 */
@Service
public class MachinePotInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private MachinePotInfoDAO machinePotInfoDAO;

    /**
     * 分页查询信息
     * @author 姜易平
     */
    public Pagination<MachinePotInfo> queryMachinePotInfoList(MachinePotInfo machinePotInfo, int pageIndex, int pageSize) {
        return machinePotInfoDAO.queryMachinePotInfoList(machinePotInfo, pageIndex, pageSize);
    }

    /**
     * 分页查询信息
     * @author gao
     */
    public List<MachinePotInfo> queryMachinePotInfoList(MachinePotInfo machinePotInfo) {
        return machinePotInfoDAO.queryMachinePotInfoList(machinePotInfo);
    }
    
    /**
     * 查询详细信息
     * @author 刘镝
     */
    public MachinePotInfo getMachinePotInfoById(String machinePotInfo_id) {
        return machinePotInfoDAO.getMachinePotInfoById(machinePotInfo_id);
    }

    /**
     * 新增信息
     * @author 姜易平
     * @throws Exception 
     */
    public int insertMachinePotInfo(MachinePotInfo machinePotInfo,Processor processor) throws Exception{
    	machinePotInfo.setCreate_id(processor.getCurrentUserId());
    	return machinePotInfoDAO.saveMachinePotInfo(machinePotInfo);
    }

    /**
     * 修改信息
     * @author 姜易平
     * @throws Exception 
     */
    public int updMachinePotInfo(MachinePotInfo machinePotInfo,Processor processor) throws Exception{
    	machinePotInfo.setUpdate_id(processor.getCurrentUserId());
        return machinePotInfoDAO.updateMachinePotInfo(machinePotInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelMachinePotInfo(MachinePotInfo machinePotInfo) {
        return machinePotInfoDAO.deleteMachinePotInfo(machinePotInfo);
    }

    /**
     * 添加前/更新前check
     * @author 姜易平
     */
    public boolean dbExistCheck(MachinePotInfo machinePotInfo){
        return machinePotInfoDAO.checkMachinePotInfoEO(machinePotInfo);
    }
}

