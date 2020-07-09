package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.entity.ApproveInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表approve_info 的DAO类
 * @author 刘镝
 */
@Component("ApproveInfoDAO")
public class ApproveInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.*,(SELECT tb.user_nm from sys_user_info tb where tb.user_num = t.approve_user_id) as approve_user_nm FROM approve_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(ApproveInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_id", JDBCHelper.EQ, entiy.getApprove_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_user_id", JDBCHelper.EQ, entiy.getApprove_user_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_date", JDBCHelper.EQ, entiy.getApprove_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_yn", JDBCHelper.EQ, entiy.getApprove_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_step", JDBCHelper.EQ, entiy.getApprove_step());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_order_num", JDBCHelper.EQ, entiy.getRepair_order_num());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ApproveInfo> queryApproveInfoList(ApproveInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ApproveInfo.class, pageIndex, pageSize);
  }
  /**
   * 非分页查询entity List
   * @author 刘镝
   */
    public List<ApproveInfo> queryApproveInfoListNoPage(ApproveInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), ApproveInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveApproveInfo(ApproveInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateApproveInfo(ApproveInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteApproveInfo(ApproveInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ApproveInfo getApproveInfoById(String id) {
    return this.getEntityById(id, ApproveInfo.class);
  }
}
