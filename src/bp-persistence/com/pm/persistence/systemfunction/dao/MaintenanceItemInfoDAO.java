package com.pm.persistence.systemfunction.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表maintenance_item_info 的DAO类
 * @author 刘镝
 */
@Component("MaintenanceItemInfoDAO")
public class MaintenanceItemInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM maintenance_item_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(MaintenanceItemInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintenance_id", JDBCHelper.EQ, entiy.getMaintenance_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintenance_nm", JDBCHelper.LIKE, entiy.getMaintenance_nm());
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
  public Pagination<MaintenanceItemInfo> queryMaintenanceItemInfoList(MaintenanceItemInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), MaintenanceItemInfo.class, pageIndex, pageSize);
  }
  /**
   * 非分页查询entity List
   * @author 刘镝
   */
    public List<MaintenanceItemInfo> queryMaintenanceItemInfoList2(MaintenanceItemInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), MaintenanceItemInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveMaintenanceItemInfo(MaintenanceItemInfo entity) {
    if(entity.getMaintenance_id()==null)
    entity.setMaintenance_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateMaintenanceItemInfo(MaintenanceItemInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMaintenanceItemInfo(MaintenanceItemInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MaintenanceItemInfo getMaintenanceItemInfoById(String id) {
    return this.getEntityById(id, MaintenanceItemInfo.class);
  }
  /**
   * 分页 List 
   * @author sunaibo
   */
    public Pagination<MaintenanceItemInfo> queryMaintainItemListpage(MaintenanceItemInfo entity,int pageIndex, int pageSize) {
  	  String QueryTableSqlNames="select t.*,v.sub_code_nm as machine_type_name"
  	  		  + ",(select code_nm from sys_code_info where code_group_value = 'machTypeCode' and code_value = machine_species_id) as machine_species_nm " //增加设备类别名称 --by fish
  	  		  + " from maintenance_item_info  t,"
  		      +" (select a.code_value as sub_code_value,a.code_nm as sub_code_nm,a.code_group_value,b.code_value,b.code_nm,b.code_group_value as parent_group_value from sys_code_info a, sys_code_info b"
  		      +" where a.code_group_value= b.sub_code_group_value "
  		      +" and b.code_group_value='"+PmConstant.MACH_TYPE_GROUP+"') v "
  		      +" where 1=1 "
  		      + " and  t.machine_species_id=v.code_value "
  		      +" and t.machine_type_id=v.sub_code_value ";
  	  JDBCHelper helper = makeSearch(entity, QueryTableSqlNames);
      return getPage(helper.getSql(), helper.getParam(), MaintenanceItemInfo.class, pageIndex, pageSize);
    }
}
