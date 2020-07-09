package com.pm.business.workout.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.workout.dao.RepairMaterialCostDetailDAO;
import com.pm.persistence.workout.dao.WorkOutDetailDAO;
import com.pm.persistence.workout.dao.WorkOutInfoDAO;
import com.pm.persistence.workout.dao.WorkOutMachCostDAO;
import com.pm.persistence.workout.entity.RepairMaterialCostDetail;
import com.pm.persistence.workout.entity.WorkOutDetail;
import com.pm.persistence.workout.entity.WorkOutInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
@Service
public class WorkoutInfoService {
	
	private static Logger logger = Logger.getLogger("logger");

	@Resource
	private WorkOutInfoDAO  workOutInfoDAO;
	
	@Resource
	private WorkOutDetailDAO  workOutDetailDAO;
	
	@Resource
	private WorkOutMachCostDAO  workOutMachCostDAO;
	
	@Resource
	private RepairMaterialCostDetailDAO  repairMaterialCostDetailDAO;
	
	
	/**
	 * 分页查询entity List 1
	 * @author 刘镝
	 */
	  public Pagination<WorkOutInfo> queryWorkoutInfoList(WorkOutInfo entity, int pageIndex, int pageSize) {
	    return workOutInfoDAO.queryWorkOutInfoList(entity, pageIndex, pageSize);
	    }
	  
	  /**
	   * 新增项目管理信息
	   * @author fish
	   */
	    public int saveWorkOutInfo(WorkOutInfo entity) {
	    	try {
				logger.info("事务操作方法：SysUserInfoService--saveWorkOutInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--saveWorkOutInfo 异常！");
			}
	      return workOutInfoDAO.saveWorkOutInfo(entity);
	    }
	  
	  /**
		 * 修改实体对象entity
		 *@author SKCC
		 */
		  public int updateWorkOutInfo(WorkOutInfo entity) {
			  try {
					logger.info("事务操作方法：SysUserInfoService--updateWorkOutInfo");
					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
				} catch (Exception e) {
					logger.error("事务操作方法：SysUserInfoService--updateWorkOutInfo 异常！");
				}
			  return workOutInfoDAO.updateWorkOutInfo(entity);
		  }
		  
	  /**
		 * 修改实体对象entity
		 * @author SKCC
		 */
		  public int updateWorkOutDtlInfo(List<WorkOutDetail> WorkOutDetaillist,Processor processor) {
			  try {
					logger.info("事务操作方法：SysUserInfoService--updateWorkOutDtlInfo");
					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
				} catch (Exception e) {
					logger.error("事务操作方法：SysUserInfoService--updateWorkOutDtlInfo 异常！");
				}
			  //System.out.println("updateWorkOutDtlInfo start");
			  int uptCnt = 0;
			//保存货物信息
				if(WorkOutDetaillist != null){
					int i = 1;
					for(WorkOutDetail item:WorkOutDetaillist){
						if(item.getWork_out_detail_id() != null){
							item.setUpdate_id(processor.getCurrentUserId());
							item.setUpdate_time(new Date());
						    uptCnt = workOutDetailDAO.updateWorkOutDetail(item);
						}
						else{
							System.out.println("i:"+i);
						}
						i++;
					}	
				}
				return uptCnt;
		  }
		  
  /**
	 * 修改实体对象entity
	 * @author SKCC
	 */
	  public int updateRepairCostDtlInfo(List<RepairMaterialCostDetail> repairMaterialCostDetail,Processor processor,String id) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--updateRepairCostDtlInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--updateRepairCostDtlInfo 异常！");
			}
		  //System.out.println("updateWorkOutDtlInfo start");
		  int uptCnt = 0;
		//保存货物信息
		  int delCnt = repairMaterialCostDetailDAO.deleteRapairMaterialCostDetailById(id);
		  
		  if (delCnt >= 0){
			  if(repairMaterialCostDetail != null){
					int i = 1;
					for(RepairMaterialCostDetail item:repairMaterialCostDetail){
						//System.out.println(i);
						if(item.getRapair_material_cost_id() != null){
							item.setCreate_id(processor.getCurrentUserId());
							item.setCreate_time(new Date());
						    uptCnt = repairMaterialCostDetailDAO.saveNew(item);
						}
						else if(item.getRapair_material_cost_id() == null){
							item.setCreate_id(processor.getCurrentUserId());
							item.setCreate_time(new Date());
							item.setWork_out_id(id);
						    uptCnt = repairMaterialCostDetailDAO.saveRapairMaterialCostDetail(item);
						}
						i++;
					}	
				} 
		  }
			return uptCnt;
	  }	  
  /**
 * 修改实体对象entity
 * @author SKCC
 */
  public int addWorkOutDtlInfo(List<WorkOutDetail> WorkOutDetaillist,Processor processor,String workOutid) {
	  try {
			logger.info("事务操作方法：SysUserInfoService--addWorkOutDtlInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--addWorkOutDtlInfo 异常！");
		}
	  //System.out.println("addWorkOutDtlInfo start");
	  int addCnt = 0;
	//保存货物信息
		if(WorkOutDetaillist != null){
			int i = 1;
			for(WorkOutDetail item:WorkOutDetaillist){
				//System.out.println("workoutdetail_id"+item.getWork_out_detail_id());
				if(item.getWork_out_detail_id() == "" ){
					if (item.getUnit2() ==""){
						item.setWork_hour2("");
					}
					item.setCreate_id(processor.getCurrentUserId());
					item.setCreate_time(new Date());
					item.setWork_out_id(workOutid);
				 
					addCnt = workOutDetailDAO.saveWorkOutDetail(item);
				}
				i++;
			}	
		}
		return addCnt;
	   
  }	

  /**
   * rapairMaterialCostDetail
   * 修改实体对象entity
   * @author SKCC
   */
    public int addRepailMaterialCostDtlInfo(List<RepairMaterialCostDetail> RepairMaterialCostDetail,Processor processor,String workOutid) {
    	try {
			logger.info("事务操作方法：SysUserInfoService--addRepailMaterialCostDtlInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--addRepailMaterialCostDtlInfo 异常！");
		}
  	  //System.out.println("add rapairMaterialCostDetail start");
  	  int addCnt = 0;
  	//保存货物信息
  		if(RepairMaterialCostDetail != null){
  			int i = 1;
  			for(RepairMaterialCostDetail item:RepairMaterialCostDetail){
  				//System.out.println("workoutdetail_id"+item.getWork_out_id());
  				if(item.getRapair_material_cost_id() == null ){
 
  					item.setCreate_id(processor.getCurrentUserId());
  					item.setCreate_time(new Date());
  					item.setWork_out_id(workOutid);
  				 
  					addCnt = repairMaterialCostDetailDAO.saveRapairMaterialCostDetail(item);
  				}
  				i++;
  			}	
  		}
  		return addCnt;
  	   
    }	
  
	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	  public int deleteWorkOutInfo(WorkOutInfo entity) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--deleteWorkOutInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--deleteWorkOutInfo 异常！");
			}
		  return workOutInfoDAO.deleteWorkOutInfo(entity);
	  }
	  
  /**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	  public int deleteWorkOutDetailInfo(String work_out_id) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--deleteWorkOutDetailInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--deleteWorkOutDetailInfo 异常！");
			}
		  return workOutDetailDAO.deleteWorkOutDetailById(work_out_id);
	  }
	  
  /**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	  public int deleteRepairCostDetailInfo(String work_out_id) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--deleteRepairCostDetailInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--deleteRepairCostDetailInfo 异常！");
			}
		  return repairMaterialCostDetailDAO.deleteRapairMaterialCostDetailById(work_out_id);
	  }
	  
	/**
	 * 根据实体对象Id返回entity 
	 * @author SKCC
	 */
	  public WorkOutInfo getWorkoutInfoById(String id) {
		  return workOutInfoDAO.getWorkOutInfoByIdUse(id);
	  }
	  
  /**
	 * 根据实体对象Id返回entity 
	 * @author SKCC
	 */
	  public WorkOutInfo getWorkoutInfoByYm(String start_dt,String end_dt) {
		  return  workOutInfoDAO.getWorkOutInfoByYm(start_dt,end_dt);		 
	  }
	  
	/**
	 * 根据实体对象Id返回entity 
	 * @author SKCC
	 */
	  public WorkOutInfo getWorkoutInfoByYmSum(String start_dt,String end_dt) {
		  
 		  return workOutInfoDAO.getWorkoutInfoByYmSum(start_dt,end_dt);
	  }
	  
	/**
	 * 根据实体对象Id返回entity 
	 * @author SKCC
	 */
	  public List<WorkOutInfo> getWorkOutInfoByYmList(String start_dt,String end_dt) {
		  
 		  return workOutInfoDAO.getWorkOutInfoByYmList(start_dt,end_dt);
	  }
	  
	/**
	 * 根据实体对象Id返回entity 
	 * @author SKCC
	 */
	  public List<WorkOutDetail> getWorkoutDetailInfoById(String id,String type) {
		  
		  if("1".equals(type)){
			  return workOutDetailDAO.getWorkOutDetailInfoById(id,type);
		  }else {
			  return workOutDetailDAO.getWorkOutDetailInfoMachById(id,type);
		  }
	  }
  
  /**
	 * 根据实体对象Id返回entity
	 * 查询材料费用list 
	 * @author SKCC
	 */
	  public List<RepairMaterialCostDetail> getRepairCostDetailInfoById(String id,String type) {
		  return repairMaterialCostDetailDAO.getRepairCostDetailInfoById(id,type);
	  }
}
