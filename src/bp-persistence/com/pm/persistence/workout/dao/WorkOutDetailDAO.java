package com.pm.persistence.workout.dao;

import java.util.List;

import org.springframework.stereotype.Component;






import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.pm.persistence.workout.entity.WorkOutDetail;
import com.pm.persistence.workout.entity.WorkOutInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表work_out_detail 的DAO类
 * @author 刘镝
 */
@Component("WorkOutDetailDAO")
public class WorkOutDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM work_out_detail T WHERE 1=1 ";
  private static  String  DELETE_SQL = "delete from work_out_detail where 1=1 ";
  private JDBCHelper makeSearch(WorkOutDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_detail_id", JDBCHelper.EQ, entiy.getWork_out_detail_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_id", JDBCHelper.EQ, entiy.getWork_out_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. worker_nm", JDBCHelper.EQ, entiy.getWorker_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_hour", JDBCHelper.EQ, entiy.getWork_hour());
              jdbcHelper.putParam(JDBCHelper.AND,"T. type", JDBCHelper.EQ, entiy.getType());
              jdbcHelper.putParam(JDBCHelper.AND,"T. unit", JDBCHelper.EQ, entiy.getUnit());
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
  public Pagination<WorkOutDetail> queryWorkOutDetailList(WorkOutDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), WorkOutDetail.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveWorkOutDetail(WorkOutDetail entity) {
	//System.out.println("saveWorkOutDetail dao start");  
	
    if(entity.getWork_out_detail_id() == "")
    entity.setWork_out_detail_id(this.getUUId());
    //System.out.println("yy"+entity.getWork_out_detail_id()+"dd "+entity.getWork_out_id());
    
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateWorkOutDetail(WorkOutDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteWorkOutDetail(WorkOutDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

  /**
   * 删除实体对象entity
   * @author 刘镝
   */
    public int deleteWorkOutDetailById(String  work_out_id) {
    	String sql = DELETE_SQL+"AND work_out_id IN('"+work_out_id+"') ";
    	return super.executeSQL(sql, new Object[]{});
    }
    
/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public WorkOutDetail getWorkOutDetailById(String id) {
    return this.getEntityById(id, WorkOutDetail.class);
  }
  
  /**
   * 根据实体对象Id返回entity 
   * @author SKCC
   */
    public  List<WorkOutDetail> getWorkOutDetailInfoById(String id ,String type) {
  	  String sql = "select * from work_out_detail T where 1 = 1 and work_out_id ='"+id+"' and type ='"+type+"' order by worker_nm  ";
  	  ///System.out.println("getWorkOutDetailInfoById" +sql.toString());
        return this.queryForList(sql, WorkOutDetail.class);   
    }
    
    /**
     * 根据实体对象Id返回entity
     * @author SKCC
     */
      public  List<WorkOutDetail> getWorkOutDetailInfoMachById(String id ,String type) {
    	  String sql = "select T.*,c.use_hour_cost,c.use_day_cost from work_out_detail T"
    	  		+ " LEFT JOIN work_out_mach_cost c on t.worker_nm = c.mach_cost_nm "
    	  		+ "where 1 = 1 and work_out_id ='"+id+"' and type >='"+type+"' order by type, worker_nm  ";
    	  //0412-001 财务考勤表增加车辆费用录入界面
          return this.queryForList(sql, WorkOutDetail.class);   
      }
      
      
      
    
}
