package com.sqm.sqmcomplaintfeedback.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.sqm.sqmcomplaintfeedback.entity.SqmComplaintFeedback;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_complaint_feedback 的DAO类
 * @author 刘镝
 */
@Component("SqmComplaintFeedbackDAO")
public class SqmComplaintFeedbackDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from sqm_complaint_feedback t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(SqmComplaintFeedback entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lotnumber", JDBCHelper.EQ, entiy.getLotnumber());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cf_date", JDBCHelper.EQ, entiy.getCf_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cf_source", JDBCHelper.EQ, entiy.getCf_source());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cf_type", JDBCHelper.EQ, entiy.getCf_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cf_address", JDBCHelper.EQ, entiy.getCf_address());
              jdbcHelper.putParam(JDBCHelper.AND,"T. product_type", JDBCHelper.EQ, entiy.getProduct_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. product_rank", JDBCHelper.EQ, entiy.getProduct_rank());
              jdbcHelper.putParam(JDBCHelper.AND,"T. product_pack", JDBCHelper.EQ, entiy.getProduct_pack());
              jdbcHelper.putParam(JDBCHelper.AND,"T. number_barrel", JDBCHelper.EQ, entiy.getNumber_barrel());
              jdbcHelper.putParam(JDBCHelper.AND,"T. number_litres", JDBCHelper.EQ, entiy.getNumber_litres());
              jdbcHelper.putParam(JDBCHelper.AND,"T. feedback_type", JDBCHelper.EQ, entiy.getFeedback_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. feedback_content", JDBCHelper.EQ, entiy.getFeedback_content());
              jdbcHelper.putParam(JDBCHelper.AND,"T. processing_result", JDBCHelper.EQ, entiy.getProcessing_result());
              jdbcHelper.putParam(JDBCHelper.AND,"T. close_yn", JDBCHelper.EQ, entiy.getClose_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. add_measures", JDBCHelper.EQ, entiy.getAdd_measures());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_person", JDBCHelper.EQ, entiy.getCreate_person());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_date", JDBCHelper.EQ, entiy.getCreate_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. upd_date", JDBCHelper.EQ, entiy.getUpd_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. upd_person", JDBCHelper.EQ, entiy.getUpd_person());
      }
      return jdbcHelper;
  }

/**
 * 更新绑定
 * @author 刘镝
 */
  private JDBCHelper makeUpd(SqmComplaintFeedback entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. lotnumber", JDBCHelper.EQ, entiy.getLotnumber());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. cf_date", JDBCHelper.EQ, entiy.getCf_date());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. cf_source", JDBCHelper.EQ, entiy.getCf_source());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. cf_type", JDBCHelper.EQ, entiy.getCf_type());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. cf_address", JDBCHelper.EQ, entiy.getCf_address());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. product_type", JDBCHelper.EQ, entiy.getProduct_type());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. product_rank", JDBCHelper.EQ, entiy.getProduct_rank());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. product_pack", JDBCHelper.EQ, entiy.getProduct_pack());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. number_barrel", JDBCHelper.EQ, entiy.getNumber_barrel());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. number_litres", JDBCHelper.EQ, entiy.getNumber_litres());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. feedback_type", JDBCHelper.EQ, entiy.getFeedback_type());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. feedback_content", JDBCHelper.EQ, entiy.getFeedback_content());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. processing_result", JDBCHelper.EQ, entiy.getProcessing_result());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. close_yn", JDBCHelper.EQ, entiy.getClose_yn());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. add_measures", JDBCHelper.EQ, entiy.getAdd_measures());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_person", JDBCHelper.EQ, entiy.getCreate_person());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_date", JDBCHelper.EQ, entiy.getCreate_date());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. upd_date", JDBCHelper.EQ, entiy.getUpd_date());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. upd_person", JDBCHelper.EQ, entiy.getUpd_person());
      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmComplaintFeedback> querySqmComplaintFeedbackList(SqmComplaintFeedback entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmComplaintFeedback.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<SqmComplaintFeedback> querySqmComplaintFeedbackList(SqmComplaintFeedback entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmComplaintFeedback.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public SqmComplaintFeedback querySqmComplaintFeedbackEO(SqmComplaintFeedback entity) {
    List<SqmComplaintFeedback> list = querySqmComplaintFeedbackList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new SqmComplaintFeedback();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSqmComplaintFeedback(SqmComplaintFeedback entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSqmComplaintFeedback(SqmComplaintFeedback entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int specialUpdSqmComplaintFeedback(SqmComplaintFeedback entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update sqm_complaint_feedback t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
  return this.executeSQL(sql,helper.getParam());
}
/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSqmComplaintFeedback(SqmComplaintFeedback entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SqmComplaintFeedback getSqmComplaintFeedbackById(String id) {
    return this.getEntityById(id, SqmComplaintFeedback.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmComplaintFeedback> queryDataList(SqmComplaintFeedback entity, int pageIndex, int pageSize) {
    String sql = "select * from sqm_complaint_feedback  where 1=1 ";
    if(!StringUtils.isNullOrEmpty(entity.getCf_type())){
      sql = sql + " and cf_type = '" + entity.getCf_type() + "'";
    }
    if(!StringUtils.isNullOrEmpty(entity.getCf_source())){
        sql = sql + " and cf_source = '" + entity.getCf_source() + "'";
      }
    if(!StringUtils.isNullOrEmpty(entity.getFeedback_content())){
        sql = sql + " and feedback_content like '%" + entity.getFeedback_content() + "%'";
      }
    if(!StringUtils.isNullOrEmpty(entity.getProduct_pack())){//包装（升）
        sql = sql + " and product_pack = '" + entity.getProduct_pack() + "'";
      }
/*    sql = sql + " order create_date desc";*/
    return getPage(sql, null, SqmComplaintFeedback.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<SqmComplaintFeedback> queryDataList(SqmComplaintFeedback entity){
	    String sql = "select * from sqm_complaint_feedback  where 1=1 ";
	    if(!StringUtils.isNullOrEmpty(entity.getCf_type())){
	      sql = sql + " and cf_type = '" + entity.getCf_type() + "'";
	    }
	    if(!StringUtils.isNullOrEmpty(entity.getCf_source())){
	        sql = sql + " and cf_source = '" + entity.getCf_source() + "'";
	      }
	    if(!StringUtils.isNullOrEmpty(entity.getFeedback_content())){
	        sql = sql + " and feedback_content like '%" + entity.getFeedback_content() + "%'";
	      }
	    if(!StringUtils.isNullOrEmpty(entity.getProduct_pack())){//包装（升）
	        sql = sql + " and product_pack = '" + entity.getProduct_pack() + "'";
	      }
	    sql = sql + " order by create_date desc";
    return this.queryForList(sql,SqmComplaintFeedback.class);
  }
  

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkSqmComplaintFeedbackEO(SqmComplaintFeedback entity) {
    String sql = "";
    SqmComplaintFeedback eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from sqm_complaint_feedback t where 1 = 1 ";
    }else{
        sql = "select count(*) from sqm_complaint_feedback t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
