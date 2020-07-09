package com.pm.business.emergentRepair.service;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.emergentRepair.dao.EmergentRepairInfoDAO;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.repast.core.system.Pagination;
/**
 * 表emergent_repair_info的SERVICE类
 * @author 刘镝
 */
@Service
public class EmergentRepairInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private EmergentRepairInfoDAO emergentRepairInfoDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<EmergentRepairInfo> queryEmergentRepairInfoList(EmergentRepairInfo emergentRepairInfo, int pageIndex, int pageSize) {
        return emergentRepairInfoDAO.queryEmergentRepairInfoList(emergentRepairInfo, pageIndex, pageSize);
    }
    
    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<EmergentRepairInfo> queryEmergentRepairInfoList2(EmergentRepairInfo emergentRepairInfo, int pageIndex, int pageSize) {
        return emergentRepairInfoDAO.queryEmergentRepairInfoList2(emergentRepairInfo, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public EmergentRepairInfo getEmergentRepairInfoById(String emergentRepairInfo_id) {
        return emergentRepairInfoDAO.getEmergentRepairInfoById(emergentRepairInfo_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     */
    public int insertEmergentRepairInfo(EmergentRepairInfo emergentRepairInfo){
        return emergentRepairInfoDAO.saveEmergentRepairInfo(emergentRepairInfo);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updEmergentRepairInfo(EmergentRepairInfo emergentRepairInfo){
        return emergentRepairInfoDAO.updateEmergentRepairInfo(emergentRepairInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelEmergentRepairInfo(EmergentRepairInfo emergentRepairInfo) {
        return emergentRepairInfoDAO.deleteEmergentRepairInfo(emergentRepairInfo);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(EmergentRepairInfo emergentRepairInfo){
        return emergentRepairInfoDAO.checkEmergentRepairInfoEO(emergentRepairInfo);
    }
}

