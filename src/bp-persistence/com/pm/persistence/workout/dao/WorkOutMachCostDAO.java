package com.pm.persistence.workout.dao;

import org.springframework.stereotype.Component;


import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.pm.persistence.workout.entity.WorkOutMachCost;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表work_out_mach_cost 的DAO类
 * @author 刘镝
 */
@Component("WorkOutMachCostDAO")
public class WorkOutMachCostDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM work_out_mach_cost T WHERE 1=1 ";
  private JDBCHelper makeSearch(WorkOutMachCost entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. mach_cost_id", JDBCHelper.EQ, entiy.getMach_cost_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. mach_cost_nm", JDBCHelper.EQ, entiy.getMach_cost_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. use_hour_cost", JDBCHelper.EQ, entiy.getUse_hour_cost());
              jdbcHelper.putParam(JDBCHelper.AND,"T. use_day_cost", JDBCHelper.EQ, entiy.getUse_day_cost());
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
  public Pagination<WorkOutMachCost> queryWorkOutMachCostList(WorkOutMachCost entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), WorkOutMachCost.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveWorkOutMachCost(WorkOutMachCost entity) {
    if(entity.getMach_cost_id()==null)
    entity.setMach_cost_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateWorkOutMachCost(WorkOutMachCost entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteWorkOutMachCost(WorkOutMachCost entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public WorkOutMachCost getWorkOutMachCostById(String id) {
    return this.getEntityById(id, WorkOutMachCost.class);
  }
}
