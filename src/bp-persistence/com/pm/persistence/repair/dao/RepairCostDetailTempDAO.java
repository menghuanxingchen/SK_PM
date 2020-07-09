package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.entity.RepairCostDetailTemp;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_cost_detail_temp 的DAO类
 * @author 刘镝
 */
@Component("RepairCostDetailTempDAO")
public class RepairCostDetailTempDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* , "
		  									+ " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.cost_type and tb.code_group_value='offer_expenses_type_group') cost_type_nm "
									  		+ "FROM repair_cost_detail_temp T WHERE 1=1 ";
  private JDBCHelper makeSearch(RepairCostDetailTemp entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_cost_id", JDBCHelper.EQ, entiy.getRepair_cost_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_result_id", JDBCHelper.EQ, entiy.getRepair_result_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cost_type", JDBCHelper.EQ, entiy.getCost_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cost_detail", JDBCHelper.EQ, entiy.getCost_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. spec", JDBCHelper.EQ, entiy.getSpec());
              jdbcHelper.putParam(JDBCHelper.AND,"T. amount", JDBCHelper.EQ, entiy.getAmount());
              jdbcHelper.putParam(JDBCHelper.AND,"T. unit", JDBCHelper.EQ, entiy.getUnit());
              jdbcHelper.putParam(JDBCHelper.AND,"T. brand", JDBCHelper.EQ, entiy.getBrand());
              jdbcHelper.putParam(JDBCHelper.AND,"T. unit_price", JDBCHelper.EQ, entiy.getUnit_price());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. total_price", JDBCHelper.EQ, entiy.getTotal_price());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<RepairCostDetailTemp> queryRepairCostDetailTempList(RepairCostDetailTemp entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), RepairCostDetailTemp.class, pageIndex, pageSize);
  }
  /**
   * 非分页查询entity List
   * @author 刘镝
   */
    public List<RepairCostDetailTemp> queryRepairCostDetailTempList2(RepairCostDetailTemp entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), RepairCostDetailTemp.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveRepairCostDetailTemp(RepairCostDetailTemp entity) {
    if(entity.getRepair_cost_id()==null)
    entity.setRepair_cost_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateRepairCostDetailTemp(RepairCostDetailTemp entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteRepairCostDetailTemp(RepairCostDetailTemp entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public RepairCostDetailTemp getRepairCostDetailTempById(String id) {
    return this.getEntityById(id, RepairCostDetailTemp.class);
  }
}
