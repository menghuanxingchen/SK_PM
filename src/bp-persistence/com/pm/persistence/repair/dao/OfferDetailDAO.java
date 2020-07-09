package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.dto.OfferInfoDTO;
import com.pm.persistence.repair.entity.OfferDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表offer_detail 的DAO类
 * @author 刘镝
 */
@Component("OfferDetailDAO")
public class OfferDetailDAO extends BaseDao {
  public final static String QueryTableSql =  " SELECT T.* ,"
											  + " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.cost_type and tb.code_group_value='offer_expenses_type_group') cost_nm , "
											  + " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.spec and tb.code_group_value='transport_spec') spec_nm , "
											  + " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.spec and tb.code_group_value='tool_use_spec') tool_use_spec_nm , "
											  + " (SELECT tb.code_nm from sys_code_info tb where tb.code_value = T.spec and tb.code_group_value='person_expenses') person_expenses_spec_nm "
									  		  + " FROM offer_detail T WHERE 1=1 ";
  private JDBCHelper makeSearch(OfferDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. offer_detail_id", JDBCHelper.EQ, entiy.getOffer_detail_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. offer_id", JDBCHelper.EQ, entiy.getOffer_id());
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
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<OfferDetail> queryOfferDetailList(OfferDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), OfferDetail.class, pageIndex, pageSize);
  }

  /**
   * 查询entity
   * @author 刘镝
   */
    public List<OfferInfoDTO> queryOfferDetailListNoPage(OfferDetail entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), OfferInfoDTO.class);
    }
    
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveOfferDetail(OfferDetail entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateOfferDetail(OfferDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteOfferDetail(OfferDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public OfferDetail getOfferDetailById(String id) {
    return this.getEntityById(id, OfferDetail.class);
  }
}
