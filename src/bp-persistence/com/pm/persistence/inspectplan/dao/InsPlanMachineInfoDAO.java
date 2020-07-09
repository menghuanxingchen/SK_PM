package com.pm.persistence.inspectplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.inspectplan.entity.InsPlanMachineInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表ins_plan_machine_info 的DAO类
 * @author 刘镝
 */
@Component("InsPlanMachineInfoDAO")
public class InsPlanMachineInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM ins_plan_machine_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(InsPlanMachineInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_machine_id", JDBCHelper.EQ, entiy.getPlan_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_id", JDBCHelper.EQ, entiy.getIns_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_machine_id", JDBCHelper.EQ, entiy.getIns_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_type", JDBCHelper.EQ, entiy.getChk_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_check_num", JDBCHelper.EQ, entiy.getIns_check_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_check_all", JDBCHelper.EQ, entiy.getIns_check_all());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. area_id", JDBCHelper.EQ, entiy.getArea_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_type_id", JDBCHelper.EQ, entiy.getMachine_type_id());
              
     }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<InsPlanMachineInfo> queryInsPlanMachineInfoList(InsPlanMachineInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), InsPlanMachineInfo.class, pageIndex, pageSize);
  }
  /**
   * 没有分页得到entity List
   * @author sunaibo
   */
    public List<InsPlanMachineInfo> queryInsPlanMachineList(InsPlanMachineInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(),helper.getParam(),InsPlanMachineInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveInsPlanMachineInfo(InsPlanMachineInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateInsPlanMachineInfo(InsPlanMachineInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteInsPlanMachineInfo(InsPlanMachineInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public InsPlanMachineInfo getInsPlanMachineInfoById(String id) {
    return this.getEntityById(id, InsPlanMachineInfo.class);
  }
  /**
   * 没有分页得到entity List
   * @author sunaibo
   */
    public List<InsPlanMachineInfo> queryMachineInfoViewListMob(InsPlanMachineInfo entity) {
      String sql="select * from ins_machine_view t where 1=1 ";
      JDBCHelper helper = makeSearch(entity, sql);
      return this.queryForList(helper.getSql(),helper.getParam(),InsPlanMachineInfo.class);
    }
}
