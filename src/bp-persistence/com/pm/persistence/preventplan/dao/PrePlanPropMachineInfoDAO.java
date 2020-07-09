package com.pm.persistence.preventplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.preventplan.entity.PrePlanPropMachineInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_prop_machine_info 的DAO类
 * @author 刘镝
 */
@Component("PrePlanPropMachineInfoDAO")
public class PrePlanPropMachineInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM pre_plan_prop_machine_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(PrePlanPropMachineInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. prop_plan_id", JDBCHelper.EQ, entiy.getProp_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_id", JDBCHelper.EQ, entiy.getPre_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. prop_machine_id", JDBCHelper.EQ, entiy.getProp_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_type", JDBCHelper.EQ, entiy.getChk_type());
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
  public Pagination<PrePlanPropMachineInfo> queryPrePlanPropMachineInfoList(PrePlanPropMachineInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), PrePlanPropMachineInfo.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<PrePlanPropMachineInfo> queryPrePlanPropMachineInfoListNoPage(PrePlanPropMachineInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), PrePlanPropMachineInfo.class);
    }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int savePrePlanPropMachineInfo(PrePlanPropMachineInfo entity) {
	entity.setProp_plan_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updatePrePlanPropMachineInfo(PrePlanPropMachineInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanPropMachineInfo(PrePlanPropMachineInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public PrePlanPropMachineInfo getPrePlanPropMachineInfoById(String id) {
    return this.getEntityById(id, PrePlanPropMachineInfo.class);
  }
}
