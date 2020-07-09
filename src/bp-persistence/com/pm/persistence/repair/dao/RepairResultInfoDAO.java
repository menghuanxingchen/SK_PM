package com.pm.persistence.repair.dao;

import org.springframework.stereotype.Component;



import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.JDBCHelper;
import com.pm.persistence.repair.entity.RepairResultInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_result_info 的DAO类
 * @author 刘镝
 */
@Component("RepairResultInfoDAO")
public class RepairResultInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM repair_result_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(RepairResultInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_result_id", JDBCHelper.EQ, entiy.getRepair_result_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_order_num", JDBCHelper.EQ, entiy.getRepair_order_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. unit_price", JDBCHelper.EQ, entiy.getUnit_price());
              jdbcHelper.putParam(JDBCHelper.AND,"T. task_time", JDBCHelper.EQ, entiy.getTask_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. one_charge", JDBCHelper.EQ, entiy.getOne_charge());
              jdbcHelper.putParam(JDBCHelper.AND,"T. finish_state", JDBCHelper.EQ, entiy.getFinish_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. finish_date", JDBCHelper.EQ, entiy.getFinish_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.EQ, entiy.getRepair_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. require_date", JDBCHelper.EQ, entiy.getRequire_date());
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
  public Pagination<RepairResultInfo> queryRepairResultInfoList(RepairResultInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), RepairResultInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveRepairResultInfo(RepairResultInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

  /**
   * 保存实体对象entity
   * @author 刘镝
   */
    public String saveRepairResultInfo2(RepairResultInfo entity,Processor processor) {
    	String sqlSP = "exec sp_repair_result_info ?";
		Object[] inPareData = new Object[] { 
				entity.getRepair_result_id(),
				entity.getRepair_order_num(),
				entity.getUnit_price(),
				entity.getTask_time(),
				entity.getOne_charge(),
				entity.getFinish_state(),
				entity.getFinish_date(),
				entity.getRemark(),
				entity.getRepair_detail(),
				entity.getRequire_date(),
				entity.getCreate_id(),
				entity.getCreate_time(),
				entity.getUpdate_id(),
				entity.getUpdate_time()
				};
		return this.getJdbcTemplate().queryForObject(sqlSP, inPareData, String.class);
    }
    
/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateRepairResultInfo(RepairResultInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteRepairResultInfo(RepairResultInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
//  public RepairResultInfo getRepairResultInfoById(String id) {
//    return this.getEntityById(id, RepairResultInfo.class);
//  }
  public RepairResultInfo getRepairResultInfoById(RepairResultInfo entity) {
	    JDBCHelper helper = makeSearch(entity, QueryTableSql);
	    return this.queryObject(helper.getSql(), helper.getParam(), RepairResultInfo.class);
  }
  
}
