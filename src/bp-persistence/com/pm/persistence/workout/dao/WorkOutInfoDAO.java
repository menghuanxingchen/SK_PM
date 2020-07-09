package com.pm.persistence.workout.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.workout.entity.WorkOutDetail;
import com.pm.persistence.workout.entity.WorkOutInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表work_out_info 的DAO类
 * @author 刘镝
 */
@Component("WorkOutInfoDAO")
public class WorkOutInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* ,(case when pay_yn ='Y' then '已结算' else '未结算' end )pay_nm ,(m.material_cost+t.human_cost+t.meal_cost+t.mach_cost) total_amt FROM work_out_info T"
  		+ " LEFT JOIN  (select sum(amount)  material_cost,work_out_id from rapair_material_cost_detail GROUP BY work_out_id) M on M.work_out_id = t.work_out_id"
  		+ " WHERE 1=1 ";
  private JDBCHelper makeSearch(WorkOutInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_id", JDBCHelper.EQ, entiy.getWork_out_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_nm", JDBCHelper.EQ, entiy.getWork_out_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_ym", JDBCHelper.EQ, entiy.getWork_out_ym());
              jdbcHelper.putParam(JDBCHelper.AND,"T. overtime_hour", JDBCHelper.EQ, entiy.getOvertime_hour());
              jdbcHelper.putParam(JDBCHelper.AND,"T. meal_hour", JDBCHelper.EQ, entiy.getMeal_hour());
              jdbcHelper.putParam(JDBCHelper.AND,"T. human_cost", JDBCHelper.EQ, entiy.getHuman_cost());
              jdbcHelper.putParam(JDBCHelper.AND,"T. meal_cost", JDBCHelper.EQ, entiy.getmeal_cost());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }
  
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<WorkOutInfo> queryWorkOutInfoList(WorkOutInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    String sql=helper.getSql();
	if(!"".equals(entity.getStart_dt())||entity.getStart_dt()!=null){
		sql=sql+" and T.work_out_ym >= '"+ entity.getStart_dt()+"'"; 
	}
	String endDt = entity.getEnd_dt().substring(0,7)+"-31";
	
	if(!"".equals(entity.getEnd_dt())||entity.getEnd_dt()!=null){
		sql=sql+" and T.work_out_ym <= '"+ endDt+"'"; 
	}
	
    return getPage(sql, helper.getParam(), WorkOutInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveWorkOutInfo(WorkOutInfo entity) {

		if(entity.getWork_out_id()==null){
			entity.setWork_out_id(this.getUUId());
		}
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateWorkOutInfo(WorkOutInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteWorkOutInfo(WorkOutInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public WorkOutInfo getWorkOutInfoById(String id) {
    return this.getEntityById(id, WorkOutInfo.class);
  }
  
  /**
   * 根据实体对象Id返回entity
   * @author SKCC
   */
    public WorkOutInfo getWorkOutInfoByIdUse(String id) {
  	  String sql = "select * from work_out_info T where 1 = 1 and work_out_id ='"+id+"'";
        return this.queryObject(sql, WorkOutInfo.class);
    }
    
/**
 * 根据实体对象Id返回entity
 * @author SKCC
 */
  public WorkOutInfo getWorkOutInfoByYm(String start_dt ,String end_dt) {
	  String sql = "SELECT ifnull(work_hour,0) work_hour ,IFNULL(overtime_hour,0) overtime_hour ,IFNULL(meal_hour,0) meal_hour, meal_cost,  mach_cost, work_out_id,pay_time FROM `work_out_info` where pay_yn ='Y'";
	  String endDt =end_dt +"-31";
	  
	  if(!"".equals(start_dt)||start_dt!=null){
			sql=sql+" and work_out_ym >= '"+ start_dt+"'"; 
		}
		if(!"".equals(end_dt)||end_dt!=null){
			sql=sql+" and work_out_ym <= '"+ endDt+"'"; 
		}
	  return this.queryObject(sql, WorkOutInfo.class);
  }
   
  /**
   * 根据实体对象Id返回entity
   * @author SKCC
   */
    public WorkOutInfo getWorkoutInfoByYmSum(String start_dt,String end_dt) {
  	  String sql = "SELECT sum(work_hour) work_hour,sum(overtime_hour) overtime_hour,truncate(sum(meal_cost),2) meal_cost,sum(meal_hour) meal_hour, truncate(sum(mach_cost),2) mach_cost ,max(pay_time) pay_time FROM `work_out_info` where 1=1 and pay_yn ='Y' ";
  	String endDt =end_dt +"-31";
  	
  	if(!"".equals(start_dt)||start_dt!=null){
		sql=sql+" and work_out_ym >= '"+ start_dt+"'"; 
	}
	if(!"".equals(end_dt)||end_dt!=null){
		sql=sql+" and work_out_ym <= '"+ endDt+"'"; 
	}

  	  return this.queryObject(sql, WorkOutInfo.class);
    }
    
/**
 * 根据实体对象Id返回entity
 * @author SKCC
 */
  public List<WorkOutInfo> getWorkOutInfoByYmList(String start_dt,String end_dt) {
	  String sql = "SELECT human_cost , meal_cost,  mach_cost, work_out_id,pay_time FROM `work_out_info` where 1=1 and pay_yn ='Y'";
	  
	  String endDt =end_dt +"-31";
	  
	  if(!"".equals(start_dt)||start_dt!=null){
			sql=sql+" and work_out_ym >= '"+ start_dt+"'"; 
	  }
	  if(!"".equals(end_dt)||end_dt!=null){
			sql=sql+" and work_out_ym <= '"+ endDt+"'"; 
	  }

	  return  this.queryForList(sql, WorkOutInfo.class);
 
  }
    
    
}
