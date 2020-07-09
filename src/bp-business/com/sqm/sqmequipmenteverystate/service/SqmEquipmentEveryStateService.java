package com.sqm.sqmequipmenteverystate.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.sqm.sqmequipmenteverystate.dao.SqmEquipmentEveryStateDAO;
import com.sqm.sqmequipmenteverystate.entity.SqmEquipmentEveryState;
/**
 * 表sqm_equipment_every_state的SERVICE类
 * @author 刘镝
 */
@Service
public class SqmEquipmentEveryStateService {
	private static Logger logger = Logger.getLogger("logger");

	@Resource
	private SqmEquipmentEveryStateDAO sqmEquipmentEveryStateDAO;

	/**
	 * 分页查询信息
	 * @author 刘镝
	 */
	public Pagination<SqmEquipmentEveryState> querySqmEquipmentEveryStateList(SqmEquipmentEveryState sqmEquipmentEveryState, int pageIndex, int pageSize) {
		return sqmEquipmentEveryStateDAO.queryDataList(sqmEquipmentEveryState, pageIndex, pageSize);
	}

	/**
	 * 查询详细信息
	 * @author 刘镝
	 */
	public SqmEquipmentEveryState getSqmEquipmentEveryStateById(String sqmEquipmentEveryState_id) {
		return sqmEquipmentEveryStateDAO.getSqmEquipmentEveryStateById(sqmEquipmentEveryState_id);
	}

	/**
	 * 新增信息
	 * @author 刘镝
	 */
	public int insertSqmEquipmentEveryState(SqmEquipmentEveryState sqmEquipmentEveryState){
		return sqmEquipmentEveryStateDAO.saveSqmEquipmentEveryState(sqmEquipmentEveryState);
	}

	/**
	 * 修改信息
	 * @author 刘镝
	 */
	public int updSqmEquipmentEveryState(SqmEquipmentEveryState sqmEquipmentEveryState){
		return sqmEquipmentEveryStateDAO.updateSqmEquipmentEveryState(sqmEquipmentEveryState);
	}

	/**
	 * 作废信息
	 * @author 刘镝
	 */
	public int cancelSqmEquipmentEveryState(SqmEquipmentEveryState sqmEquipmentEveryState) {
		return sqmEquipmentEveryStateDAO.deleteSqmEquipmentEveryState(sqmEquipmentEveryState);
	}

	/**
	 * 添加前/更新前check
	 * @author 刘镝
	 */
	public boolean dbExistCheck(SqmEquipmentEveryState sqmEquipmentEveryState){
		return sqmEquipmentEveryStateDAO.checkSqmEquipmentEveryStateEO(sqmEquipmentEveryState);
	}

	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	public  List<SqmEquipmentEveryState> queryDataList(String inspector,String lineId,String equipmentId){
		return sqmEquipmentEveryStateDAO.queryDataList(inspector, lineId, equipmentId);
	}
	
	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	public List<SqmEquipmentEveryState> queryDataList2(){
		return sqmEquipmentEveryStateDAO.queryDataList2();
		
	}
}

