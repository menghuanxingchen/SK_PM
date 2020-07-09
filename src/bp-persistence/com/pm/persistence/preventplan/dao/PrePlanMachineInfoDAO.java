package com.pm.persistence.preventplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.preventplan.entity.PrePlanMachineInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_machine_info 的DAO类
 * @author 刘镝
 */
@Component("PrePlanMachineInfoDAO")
public class PrePlanMachineInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT distinct T.*,mi.machine_nm,mi.machine_species_id,mi.machine_type_id,mi.area_id "
  										  + " FROM pre_plan_machine_info T ,machine_info mi WHERE 1=1  and T.pre_machine_id = mi.machine_id ";
  private JDBCHelper makeSearch(PrePlanMachineInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_machine_id", JDBCHelper.EQ, entiy.getPlan_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_id", JDBCHelper.EQ, entiy.getPre_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_machine_id", JDBCHelper.EQ, entiy.getPre_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_type", JDBCHelper.EQ, entiy.getChk_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_check_num", JDBCHelper.EQ, entiy.getPre_check_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_check_all", JDBCHelper.EQ, entiy.getPre_check_all());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"mi.area_id", JDBCHelper.EQ, entiy.getArea_id());
              jdbcHelper.putParam(JDBCHelper.AND,"mi.machine_type_id ", JDBCHelper.EQ, entiy.getMachine_type_id());
              jdbcHelper.putParam(JDBCHelper.AND,"mi.machine_species_id ", JDBCHelper.EQ, entiy.getMachine_species_id());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<PrePlanMachineInfo> queryPrePlanMachineInfoList(PrePlanMachineInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), PrePlanMachineInfo.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<PrePlanMachineInfo> queryPrePlanMachineInfoListByPrePlanId(PrePlanMachineInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), PrePlanMachineInfo.class);
    }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int savePrePlanMachineInfo(PrePlanMachineInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updatePrePlanMachineInfo(PrePlanMachineInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanMachineInfo(PrePlanMachineInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public PrePlanMachineInfo getPrePlanMachineInfoById(String id) {
    return this.getEntityById(id, PrePlanMachineInfo.class);
  }
  
  /**
   * 根据实体对象Id返回entity
   * @author 刘镝
   */
    public PrePlanMachineInfo getPrePlanMachineInfoByPrePlanId(String id) {
    	 String sql = QueryTableSql +" and T. pre_plan_id = '"+id+"'";
         return this.queryObject(sql, PrePlanMachineInfo.class);
    }
}
