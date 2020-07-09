package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.entity.OfferInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表offer_info 的DAO类
 * @author 刘镝
 */
@Component("OfferInfoDAO")
public class OfferInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* , "
									  		+ " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.supplier_code and tb.code_group_value='offer_supplier_group') supplier_nm "
									  		+ " FROM offer_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(OfferInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. offer_id", JDBCHelper.EQ, entiy.getOffer_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_order_num", JDBCHelper.EQ, entiy.getRepair_order_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. supplier_code", JDBCHelper.EQ, entiy.getSupplier_code());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_price", JDBCHelper.EQ, entiy.getUser_price());
              jdbcHelper.putParam(JDBCHelper.AND,"T. estimate_time", JDBCHelper.EQ, entiy.getEstimate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. tax", JDBCHelper.EQ, entiy.getTax());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
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
  public Pagination<OfferInfo> queryOfferInfoList(OfferInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), OfferInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<OfferInfo> queryOfferInfoList2(OfferInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), OfferInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveOfferInfo(OfferInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateOfferInfo(OfferInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteOfferInfo(OfferInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public OfferInfo getOfferInfoById(String id) {
    return this.getEntityById(id, OfferInfo.class);
  }
}
