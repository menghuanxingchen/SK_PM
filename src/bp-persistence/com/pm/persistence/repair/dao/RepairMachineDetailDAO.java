package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.entity.RepairMachineDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_machine_detail 的DAO类
 * @author 刘镝
 */
@Component("RepairMachineDetailDAO")
public class RepairMachineDetailDAO extends BaseDao {
  public final static String QueryTableSql = " SELECT T.*, "
		  									+ " (SELECT tb.maintenance_nm FROM maintenance_item_info tb	WHERE tb.maintenance_id = T.repair_detail) repair_detail_nm , "
		  									+ " (SELECT tb.machine_nm from machine_info tb where tb.machine_id = T.machine_id) machine_nm , "
		  									+ " (SELECT tb.code_nm from sys_code_info tb , machine_info mm where tb.code_value = mm.machine_species_id and mm.machine_id = T.machine_id and tb.code_group_value='machTypeCode') machine_species__nm , "
		  									+ " (SELECT tb.code_nm from sys_code_info tb , machine_info mm where tb.code_value = mm.machine_type_id and mm.machine_id = T.machine_id and tb.code_group_value=(SELECT DISTINCT tb1.sub_code_group_value FROM sys_code_info tb1, machine_info mm1 WHERE tb1.code_value = mm1.machine_species_id AND mm1.machine_id = T.machine_id AND tb1.code_group_value = 'machTypeCode')) machine_type_nm  "
									  		+ " FROM repair_machine_detail T WHERE 1=1 ";
  private JDBCHelper makeSearch(RepairMachineDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_machine_id", JDBCHelper.EQ, entiy.getRepair_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_result_id", JDBCHelper.EQ, entiy.getRepair_result_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_id", JDBCHelper.EQ, entiy.getMachine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. down_hour", JDBCHelper.EQ, entiy.getDown_hour());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.EQ, entiy.getRepair_detail());
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
  public Pagination<RepairMachineDetail> queryRepairMachineDetailList(RepairMachineDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), RepairMachineDetail.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<RepairMachineDetail> queryRepairMachineDetailList2(RepairMachineDetail entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), RepairMachineDetail.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveRepairMachineDetail(RepairMachineDetail entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateRepairMachineDetail(RepairMachineDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteRepairMachineDetail(RepairMachineDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public RepairMachineDetail getRepairMachineDetailById(String id) {
    return this.getEntityById(id, RepairMachineDetail.class);
  }
}
