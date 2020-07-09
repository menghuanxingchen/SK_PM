package com.pm.persistence.basicinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表machine_info 的DAO类
 * @author 刘镝
 */
@Component("MachineInfoDAO")
public class MachineInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM machine_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(MachineInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_id", JDBCHelper.EQ, entiy.getMachine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_nm", JDBCHelper.LIKE, entiy.getMachine_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_type_id", JDBCHelper.EQ, entiy.getMachine_type_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. area_id", JDBCHelper.EQ, entiy.getArea_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. production_date", JDBCHelper.EQ, entiy.getProduction_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. use_year", JDBCHelper.EQ, entiy.getUse_year());
              jdbcHelper.putParam(JDBCHelper.AND,"T. supplier", JDBCHelper.EQ, entiy.getSupplier());
              jdbcHelper.putParam(JDBCHelper.AND,"T. part", JDBCHelper.EQ, entiy.getPart());
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
  public Pagination<MachineInfo> queryMachineInfoList(MachineInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), MachineInfo.class, pageIndex, pageSize);
  }
  /**
   * 无分页 List
   * @author sunaibo
   */
    public List<MachineInfo> queryMachineInfoListNo(MachineInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), MachineInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveMachineInfo(MachineInfo entity) {
    if(entity.getMachine_id()==null)
    entity.setMachine_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateMachineInfo(MachineInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMachineInfo(MachineInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MachineInfo getMachineInfoById(String id) {
    return this.getEntityById(id, MachineInfo.class);
  }
  /**
   * 无分页 List pop
   * @author sunaibo
   */
    public Pagination<MachineInfo> queryMachineInfoListPop(MachineInfo entity,String ids,int pageIndex, int pageSize) {
    	 String QueryTableSqlpop="select t.*,v.sub_code_nm as machine_type_name from machine_info  t,"
   		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
   		      +" where a.code_group_value= b.sub_code_group_value "
   		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
   		      +" where 1=1 "
   		      + " and  t.machine_species_id=v.code_value "
   		      +" and t.machine_type_id=v.sub_code_value ";
   	  JDBCHelper helper = makeSearch(entity, QueryTableSqlpop);
      String sql = helper.getSql()+" and t.machine_id not in ("+ids+") ";
      return getPage(sql, helper.getParam(), MachineInfo.class, pageIndex, pageSize);
    }
    /**
     * 分页 List 
     * @author sunaibo
     */
      public Pagination<MachineInfo> queryMachineInfoListNames(MachineInfo entity,int pageIndex, int pageSize) {
    	  String QueryTableSqlNames="select t.*,v.sub_code_nm as machine_type_name,v.code_nm as machine_species_name from machine_info  t,"
    		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
    		      +" where a.code_group_value= b.sub_code_group_value "
    		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
    		      +" where 1=1 "
    		      + " and  t.machine_species_id=v.code_value "
    		      +" and t.machine_type_id=v.sub_code_value ";
    	  JDBCHelper helper = makeSearch(entity, QueryTableSqlNames);
        return getPage(helper.getSql(), helper.getParam(), MachineInfo.class, pageIndex, pageSize);
      }
      
      /**
       * 无分页 List
       * @author sunaibo
       */
        public List<MachineInfo> getMachineListCheckName(MachineInfo entity) {
          String sql = QueryTableSql;
          if(entity.getMachine_id()!=null){
        	  sql = sql+" and T.machine_id<>'"+entity.getMachine_id()+"'";
          }
          sql = sql+" and T.machine_nm='"+entity.getMachine_nm()+"'";
          return this.queryForList(sql, MachineInfo.class);
        }
        
        /**
         * 无分页 List pop
         * @author   tianhong
         */
          public List<MachineInfo> queryMachineInfoListPopForRepairMob(MachineInfo entity,String ids) {
          	 String QueryTableSqlpop="select t.*,v.sub_code_nm as machine_type_name from machine_info  t,"
         		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
         		      +" where a.code_group_value= b.sub_code_group_value "
         		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
         		      +" where 1=1 "
         		      + " and  t.machine_species_id=v.code_value "
         		      +" and t.machine_type_id=v.sub_code_value ";
         	  JDBCHelper helper = makeSearch(entity, QueryTableSqlpop);
//            String sql = helper.getSql()+" and t.machine_id not in ("+ids+") ";
            return queryForList(helper.getSql(), helper.getParam(), MachineInfo.class);
          }
}
