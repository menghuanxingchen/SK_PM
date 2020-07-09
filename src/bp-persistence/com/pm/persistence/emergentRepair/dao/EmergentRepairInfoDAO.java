package com.pm.persistence.emergentRepair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表emergent_repair_info 的DAO类
 * @author 刘镝
 */
@Component("EmergentRepairInfoDAO")
public class EmergentRepairInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from emergent_repair_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(EmergentRepairInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. emergent_id", JDBCHelper.EQ, entiy.getEmergent_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. job_type", JDBCHelper.EQ, entiy.getJob_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_content", JDBCHelper.EQ, entiy.getRepair_content());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_date", JDBCHelper.LIKE, entiy.getRepair_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_person", JDBCHelper.EQ, entiy.getRepair_person());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.EQ, entiy.getPot_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_id", JDBCHelper.EQ, entiy.getPot_part_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_son_part_id", JDBCHelper.EQ, entiy.getPot_son_part_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. start_time", JDBCHelper.EQ, entiy.getStart_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. end_time", JDBCHelper.EQ, entiy.getEnd_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. total_time", JDBCHelper.EQ, entiy.getTotal_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sqm_normal_parameter", JDBCHelper.EQ, entiy.getSqm_normal_parameter());
      }
      return jdbcHelper;
  }
  
  /**
   * 2018-08起查询停机/维修时长
   * @param entiy
   * @param sql
   * @return
   */
  private JDBCHelper makeSearch3(EmergentRepairInfo entiy, String sql) {
	  JDBCHelper jdbcHelper = new JDBCHelper(sql);
	  if (entiy != null) {
		  jdbcHelper.putParam(JDBCHelper.AND,"T. emergent_id", JDBCHelper.EQ, entiy.getEmergent_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. job_type", JDBCHelper.EQ, entiy.getJob_type());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_content", JDBCHelper.EQ, entiy.getRepair_content());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_date", JDBCHelper.LTQ, entiy.getRepair_date());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_person", JDBCHelper.EQ, entiy.getRepair_person());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.EQ, entiy.getPot_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_id", JDBCHelper.EQ, entiy.getPot_part_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_son_part_id", JDBCHelper.EQ, entiy.getPot_son_part_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. start_time", JDBCHelper.EQ, entiy.getStart_time());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. end_time", JDBCHelper.EQ, entiy.getEnd_time());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. total_time", JDBCHelper.EQ, entiy.getTotal_time());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
		  jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
          jdbcHelper.putParam(JDBCHelper.AND,"T. sqm_normal_parameter", JDBCHelper.EQ, entiy.getSqm_normal_parameter());
	  }
	  return jdbcHelper;
  }
  
  /**
   * 检索绑定
   * @author 刘镝
   */
    private JDBCHelper makeSearch2(EmergentRepairInfo entiy, String sql) {
       JDBCHelper jdbcHelper = new JDBCHelper(sql);
       if (entiy != null) {
                jdbcHelper.putParam(JDBCHelper.AND,"T. emergent_id", JDBCHelper.EQ, entiy.getEmergent_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. job_type", JDBCHelper.EQ, entiy.getJob_type());
                jdbcHelper.putParam(JDBCHelper.AND,"T. repair_content", JDBCHelper.LIKE, entiy.getRepair_content());
                jdbcHelper.putParam(JDBCHelper.AND,"T. repair_date", JDBCHelper.LTQ, entiy.getSearch_end());
                jdbcHelper.putParam(JDBCHelper.AND,"T. repair_date", JDBCHelper.GTQ, entiy.getSearch_start());
                jdbcHelper.putParam(JDBCHelper.AND,"T. repair_person", JDBCHelper.EQ, entiy.getRepair_person());
                jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.EQ, entiy.getPot_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_id", JDBCHelper.EQ, entiy.getPot_part_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. pot_son_part_id", JDBCHelper.EQ, entiy.getPot_son_part_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. start_time", JDBCHelper.EQ, entiy.getStart_time());
                jdbcHelper.putParam(JDBCHelper.AND,"T. end_time", JDBCHelper.EQ, entiy.getEnd_time());
                jdbcHelper.putParam(JDBCHelper.AND,"T. total_time", JDBCHelper.EQ, entiy.getTotal_time());
                jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
                jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
                jdbcHelper.putParam(JDBCHelper.AND,"T. sqm_normal_parameter", JDBCHelper.EQ, entiy.getSqm_normal_parameter());
        }
       jdbcHelper.putOrder("T. repair_date desc,T.create_time desc");
        return jdbcHelper;
    }


    /**
     * 查询子部件的停机次数
     * @author Aubrey
     */
    public int queryCodeStopCount2(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1";
    	JDBCHelper helper = makeSearch(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询子部件的停机次数 ，起始于2018-08
     * @author Aubrey
     */
    public int queryCodeStopCount(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00'";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询子部件的停机总时长
     * @author Aubrey
     */
    public int queryCodeStopTotalTime(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,start_time,end_time)) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' ";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
   
    /**
     * 查询子部件的停机总时长
     * @author Aubrey
     */
    public int queryDYCodeStopTotalTime(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,start_time,end_time)) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' ";
    	JDBCHelper helper = makeSearch2(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询子部件的停机次数 ，起始于2018-08
     * @author Aubrey
     */
    public int queryDYCodeStopCount(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' and repairType='Y' ";
    	JDBCHelper helper = makeSearch2(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }

    
    /**
     * 查询子部件的停机当月维修时长
     * @author Aubrey
     */
    public int queryDYWXCodeStopTotalTime(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,repairStart_time,repairEnd_time)) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' ";
    	JDBCHelper helper = makeSearch2(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询子部件的停机当月维修次数 ，起始于2018-08
     * @author Aubrey
     */
    public int queryDYWXCodeStopCount(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' and repairType='Y' ";
    	JDBCHelper helper = makeSearch2(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    
    
    
    
    /**
     * 查询子部件的停机总维修时长
     * @author Aubrey
     */
    public int queryZWXCodeStopTotalTime(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,repairStart_time,repairEnd_time)) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' ";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询子部件的停机总维修次数 ，起始于2018-08
     * @author Aubrey
     */
    public int queryZWXCodeStopCount(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' and repairType='Y' ";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    
    /**
     * 查询间隔子部件的停机次数 ，起始于2018-08
     * @author Aubrey
     */
    public int queryjgCodeStopCount(EmergentRepairInfo entity) {
    	String sql = "select count(*) from emergent_repair_info t where 1=1 and repair_date >= '2018-08-00' ";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    /**
     * 查询间隔子部件的停机总时长
     * @author Aubrey
     */
    public int queryjgCodeStopTotalTime(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,start_time,end_time)) from emergent_repair_info t where 1=1  ";
    	JDBCHelper helper = makeSearch3(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }
    
    
    
    /**
     * 查询子部件的停机总时长
     * @author gaowenchao
     */
    public int queryCodeStopTotalTimeByMR(EmergentRepairInfo entity) {
    	String sql = "select sum(timestampdiff(minute,start_time,end_time)) from emergent_repair_info t where 1=1 ";
    	JDBCHelper helper = makeSearch(entity, sql);
    	return queryForInt(helper.getSql(), helper.getParam());
    }

    
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<EmergentRepairInfo> queryEmergentRepairInfoList(EmergentRepairInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), EmergentRepairInfo.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<EmergentRepairInfo> queryEmergentRepairInfoList2(EmergentRepairInfo entity, int pageIndex, int pageSize) {
      JDBCHelper helper = makeSearch2(entity, QueryTableSql);
      return getPage(helper.getSql(), helper.getParam(), EmergentRepairInfo.class, pageIndex, pageSize);
    }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<EmergentRepairInfo> queryEmergentRepairInfoList(EmergentRepairInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), EmergentRepairInfo.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public EmergentRepairInfo queryEmergentRepairInfoEO(EmergentRepairInfo entity) {
    List<EmergentRepairInfo> list = queryEmergentRepairInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new EmergentRepairInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveEmergentRepairInfo(EmergentRepairInfo entity) {
    if(entity.getEmergent_id()==null)
    entity.setEmergent_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateEmergentRepairInfo(EmergentRepairInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteEmergentRepairInfo(EmergentRepairInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public EmergentRepairInfo getEmergentRepairInfoById(String id) {
    return this.getEntityById(id, EmergentRepairInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<EmergentRepairInfo> queryDataList(EmergentRepairInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from emergent_repair_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getEmergent_id())){
    //  sql = sql + " and emergent_id like '%" + entity.getEmergent_id() + "%'";
    //}
    //sql = sql + " order by emergent_id desc";
    return getPage(sql, null, EmergentRepairInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<EmergentRepairInfo> queryDataList(EmergentRepairInfo entity){
    String sql = "select * from emergent_repair_info where 1=1 ";
    if(!StringUtils.isNullOrEmpty(entity.getPot_part_id())) {
    	sql+=" and pot_part_id = '"+entity.getPot_part_id()+"'";
    }
    if(!StringUtils.isNullOrEmpty(entity.getCreate_id())) {
    	sql+=" and create_id = '"+entity.getCreate_id()+"'";
    }
    if(!StringUtils.isNullOrEmpty(entity.getCreate_time())) {
    	sql+=" and create_time = '"+entity.getCreate_time()+"'";
    }
    return this.queryForList(sql,EmergentRepairInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkEmergentRepairInfoEO(EmergentRepairInfo entity) {
    String sql = "";
    EmergentRepairInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getEmergent_id())){
        sql = "select count(*) from emergent_repair_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from emergent_repair_info t where t.emergent_id <> '" + entity.getEmergent_id() +"'";
        eo.setEmergent_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
