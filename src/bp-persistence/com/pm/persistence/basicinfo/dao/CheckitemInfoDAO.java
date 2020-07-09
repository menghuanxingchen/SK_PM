package com.pm.persistence.basicinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表checkitem_info 的DAO类
 * @author 刘镝
 */
@Component("CheckitemInfoDAO")
public class CheckitemInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM checkitem_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(CheckitemInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_project_id", JDBCHelper.EQ, entiy.getCheck_project_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_project_nm", JDBCHelper.LIKE, entiy.getCheck_project_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_type_id", JDBCHelper.EQ, entiy.getMachine_type_id());
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
  public Pagination<CheckitemInfo> queryCheckitemInfoList(CheckitemInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), CheckitemInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<CheckitemInfo> queryCheckitemInfoListNames(CheckitemInfo entity, int pageIndex, int pageSize) {
    	String QueryTableSqlNames="select t.*,v.sub_code_nm as machine_type_name from checkitem_info  t,"
  		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
  		      +" where a.code_group_value= b.sub_code_group_value "
  		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
  		      +" where 1=1 "
  		      + " and  t.machine_species_id=v.code_value "
  		      +" and t.machine_type_id=v.sub_code_value ";
  	  JDBCHelper helper = makeSearch(entity, QueryTableSqlNames);
      return getPage(helper.getSql(), helper.getParam(), CheckitemInfo.class, pageIndex, pageSize);
    }
  /**
   * 无分页List
   * @author sunaibo
   */
    public List<CheckitemInfo> queryCheckitemInfoListNo(CheckitemInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), CheckitemInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveCheckitemInfo(CheckitemInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateCheckitemInfo(CheckitemInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteCheckitemInfo(CheckitemInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public CheckitemInfo getCheckitemInfoById(String id) {
    return this.getEntityById(id, CheckitemInfo.class);
  }
  /**
   * 无分页 List pop
   * @author sunaibo
   */
    public Pagination<CheckitemInfo> queryCheckitemListPop(CheckitemInfo entity,String ids,int pageIndex, int pageSize) {
    	String QueryTableSqlpop="select t.*,v.sub_code_nm as machine_type_name from checkitem_info  t,"
    		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
    		      +" where a.code_group_value= b.sub_code_group_value "
    		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
    		      +" where 1=1 "
    		      + " and  t.machine_species_id=v.code_value "
    		      +" and t.machine_type_id=v.sub_code_value ";
    	  JDBCHelper helper = makeSearch(entity, QueryTableSqlpop);
      String sql = helper.getSql()+" and t.check_project_id not in ("+ids+") ";
      return getPage(sql, helper.getParam(), CheckitemInfo.class, pageIndex, pageSize);
    }
    
    /**
     * 无分页List
     * @author sunaibo
     */
      public List<CheckitemInfo> queryCheckitemInfoListCheckName(CheckitemInfo entity) {
        String sql=QueryTableSql;
        if(entity.getCheck_project_id()!=null){
      	  sql = sql+" and T.check_project_id<>'"+entity.getCheck_project_id()+"'";
        }
        sql = sql+" and T.check_project_nm='"+entity.getCheck_project_nm()+"'";
        return this.queryForList(sql, CheckitemInfo.class);
      }
}
