package com.pm.persistence.preventplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.preventplan.entity.PrePlanPropCheckDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_prop_check_detail 的DAO类
 * @author 刘镝
 */
@Component("PrePlanPropCheckDetailDAO")
public class PrePlanPropCheckDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM pre_plan_prop_check_detail T WHERE 1=1 ";
  private JDBCHelper makeSearch(PrePlanPropCheckDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. prop_plan_id", JDBCHelper.EQ, entiy.getProp_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_detail", JDBCHelper.EQ, entiy.getCheck_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. operate_date", JDBCHelper.EQ, entiy.getOperate_date());
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
  public Pagination<PrePlanPropCheckDetail> queryPrePlanPropCheckDetailList(PrePlanPropCheckDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), PrePlanPropCheckDetail.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<PrePlanPropCheckDetail> queryPrePlanPropCheckDetailListNoPage(PrePlanPropCheckDetail entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), PrePlanPropCheckDetail.class);
    }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int savePrePlanPropCheckDetail(PrePlanPropCheckDetail entity) {
	  entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updatePrePlanPropCheckDetail(PrePlanPropCheckDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanPropCheckDetail(PrePlanPropCheckDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }
  

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanPropCheckDetailByPlanId(String id) {
  String  sql = "delete from pre_plan_prop_check_detail where prop_plan_id = '"+id+"'";
    return this.executeSQL(sql);
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public PrePlanPropCheckDetail getPrePlanPropCheckDetailById(String id) {
    return this.getEntityById(id, PrePlanPropCheckDetail.class);
  }
}
