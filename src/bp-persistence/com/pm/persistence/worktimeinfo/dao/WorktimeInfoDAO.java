package com.pm.persistence.worktimeinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.worktimeinfo.entity.WorktimeInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表worktime_info 的DAO类
 * @author 刘镝
 */
@Component("WorktimeInfoDAO")
public class WorktimeInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from worktime_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(WorktimeInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. workhour_id", JDBCHelper.EQ, entiy.getWorkhour_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. date_group", JDBCHelper.EQ, entiy.getDate_group());
              jdbcHelper.putParam(JDBCHelper.AND,"T. days", JDBCHelper.EQ, entiy.getDays());
              jdbcHelper.putParam(JDBCHelper.AND,"T. week", JDBCHelper.EQ, entiy.getWeek());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_hour", JDBCHelper.EQ, entiy.getWork_hour());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
     jdbcHelper.putOrder(" T.days+0");
      return jdbcHelper;
  }
  
  /**
   * 2018-08起时长查询
   * @param entiy
   * @param sql
   * @return
   */
  private JDBCHelper makeSearch2(WorktimeInfo entiy, String sql) {
	  JDBCHelper jdbcHelper = new JDBCHelper(sql);
	  if (entiy != null) {
		  jdbcHelper.putParam(JDBCHelper.AND,"T. workhour_id", JDBCHelper.EQ, entiy.getWorkhour_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. date_group", JDBCHelper.LTQ, entiy.getDate_group());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. days", JDBCHelper.EQ, entiy.getDays());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. week", JDBCHelper.EQ, entiy.getWeek());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. work_hour", JDBCHelper.EQ, entiy.getWork_hour());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
	  }
	  jdbcHelper.putOrder(" T.days+0");
	  return jdbcHelper;
  }

  /**
   * 查询总时长,查询时长起始时间从2018-08开始算
   * @author Aubrey
   */
    public int queryTotalTime(WorktimeInfo entity) {
    	String sql = "select sum(work_hour)*60 from worktime_info t where 1=1 and date_group >= '2018-08'";
    	JDBCHelper helper = makeSearch2(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
  
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<WorktimeInfo> queryWorktimeInfoList(WorktimeInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), WorktimeInfo.class, 1, -1);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<WorktimeInfo> queryWorktimeInfoList(WorktimeInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), WorktimeInfo.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public WorktimeInfo queryWorktimeInfoEO(WorktimeInfo entity) {
    List<WorktimeInfo> list = queryWorktimeInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new WorktimeInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveWorktimeInfo(WorktimeInfo entity) {
    if(entity.getWorkhour_id()==null)
    entity.setWorkhour_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }
  
  /**
   * 保存实体对象Listentity
   * @author 刘镝
   */
    public int saveWorktimeInfoList(List<WorktimeInfo> worktimeInfoList) {

      String sql="insert into worktime_info values ";
      for(WorktimeInfo e:worktimeInfoList){
    	  sql = sql+"('"+this.getUUId()+"','"+e.getDate_group()+"','"+e.getDays()+"','"+e.getWeek()+"','"+e.getWork_hour()+"','"+e.getUpdate_id()+"','"+e.getUpdate_time()+"'),";
      }
      sql = sql.substring(0, sql.length()-1);
      return this.executeSQL(sql);
    }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateWorktimeInfo(WorktimeInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteWorktimeInfo(WorktimeInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }
  
  /**
   * 保存实体对象Listentity
   * @author 刘镝
   */
    public int delWorktimeInfoList(List<WorktimeInfo> worktimeInfoList) {

      String sql="delete from worktime_info where workhour_id in ( ";
      for(WorktimeInfo e:worktimeInfoList){
    	  sql = sql+"'"+e.getWorkhour_id()+"',";
      }
      sql = sql.substring(0, sql.length()-1);
      sql = sql+");";
      return this.executeSQL(sql);
    }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public WorktimeInfo getWorktimeInfoById(String id) {
    return this.getEntityById(id, WorktimeInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<WorktimeInfo> queryDataList(WorktimeInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from worktime_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getWorkhour_id())){
    //  sql = sql + " and workhour_id like '%" + entity.getWorkhour_id() + "%'";
    //}
    //sql = sql + " order by workhour_id desc";
    return getPage(sql, null, WorktimeInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<WorktimeInfo> queryDataList(WorktimeInfo entity){
    String sql = "select * from worktime_info where 1=1 ";
    return this.queryForList(sql,WorktimeInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkWorktimeInfoEO(WorktimeInfo entity) {
    String sql = "";
    WorktimeInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getWorkhour_id())){
        sql = "select count(*) from worktime_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from worktime_info t where t.workhour_id <> '" + entity.getWorkhour_id() +"'";
        eo.setWorkhour_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
