package com.sqm.sqmequipmentstate.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.sqm.sqmequipmentstate.dao.SqmEquipmentStateDAO;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
/**
 * 表sqm_equipment_state的SERVICE类
 * @author 刘镝
 */
@Service
public class SqmEquipmentStateService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private SqmEquipmentStateDAO sqmEquipmentStateDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<SqmEquipmentState> querySqmEquipmentStateList(SqmEquipmentState sqmEquipmentState, int pageIndex, int pageSize) {
        return sqmEquipmentStateDAO.queryDataList(sqmEquipmentState, pageIndex, pageSize);
    }
    
    /**
     * 不分页查询信息
     * @author 刘镝
     */
    public List<SqmEquipmentState> querySqmEquipmentStateList(SqmEquipmentState sqmEquipmentState) {
        return sqmEquipmentStateDAO.querySqmEquipmentStateList(sqmEquipmentState);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public SqmEquipmentState getSqmEquipmentStateById(String sqmEquipmentState_id) {
        return sqmEquipmentStateDAO.getSqmEquipmentStateById(sqmEquipmentState_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     */
    public int insertSqmEquipmentState(SqmEquipmentState sqmEquipmentState){
        return sqmEquipmentStateDAO.saveSqmEquipmentState(sqmEquipmentState);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updSqmEquipmentState(SqmEquipmentState sqmEquipmentState){
        return sqmEquipmentStateDAO.updateSqmEquipmentState(sqmEquipmentState);
    }
    
    /**
     * 修改设备状态
     * @author 刘镝
     * @return 
     */
    public int updSqmEquipmentState2(SqmEquipmentState entity,String type){
		return sqmEquipmentStateDAO.resetUpdSqmEquipmentState(entity, type);
    	
    }
    
    /**
     * 修改设备状态
     * @author 刘镝
     */
    public int updSqmEquipmentState3(SqmEquipmentState entity){
    	return sqmEquipmentStateDAO.updSqmEquipmentState(entity);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelSqmEquipmentState(SqmEquipmentState sqmEquipmentState) {
        return sqmEquipmentStateDAO.deleteSqmEquipmentState(sqmEquipmentState);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(SqmEquipmentState sqmEquipmentState){
        return sqmEquipmentStateDAO.checkSqmEquipmentStateEO(sqmEquipmentState);
    }
}

