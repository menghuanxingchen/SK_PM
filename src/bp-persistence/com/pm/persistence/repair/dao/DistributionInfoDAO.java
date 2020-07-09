package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.entity.DistributionInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表distribution_info 的DAO类
 * @author 刘镝
 */
@Component("DistributionInfoDAO")
public class DistributionInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM distribution_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(DistributionInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. distribution_id", JDBCHelper.EQ, entiy.getDistribution_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_order_num", JDBCHelper.EQ, entiy.getRepair_order_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. require_date", JDBCHelper.EQ, entiy.getRequire_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. notice_yn", JDBCHelper.EQ, entiy.getNotice_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_id", JDBCHelper.EQ, entiy.getUser_id());
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
  public Pagination<DistributionInfo> queryDistributionInfoList(DistributionInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), DistributionInfo.class, pageIndex, pageSize);
  }
  /**
   * 非分页查询entity List
   * @author 刘镝
   */
    public List<DistributionInfo> queryDistributionInfoList(DistributionInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), DistributionInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveDistributionInfo(DistributionInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateDistributionInfo(DistributionInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteDistributionInfo(DistributionInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public DistributionInfo getDistributionInfoById(String id) {
    return this.getEntityById(id, DistributionInfo.class);
  }
}
