package com.pm.persistence.Sqmmanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysUserInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表 的DAO类
 * @author 刘镝
 */
@Component("SqmmanageDAO")
public class SqmmanageDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.*,S.code_nm AS lineName from line_product_info T "
  		+ "left join sys_code_info S on T.lineId = S.code_value AND S.code_group_value='line' WHERE 1=1 ";
  
  
		  
  private JDBCHelper makeSearch(LineProductInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
    	 	  jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.LIKE, entiy.getPot_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. intervalDate", JDBCHelper.EQ, entiy.getIntervalDate());
              jdbcHelper.putParam(JDBCHelper.AND,"T. continueDate", JDBCHelper.EQ, entiy.getContinueDate());
              jdbcHelper.putParam(JDBCHelper.AND,"T. breakdownDate", JDBCHelper.EQ, entiy.getBreakdownDate());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());

      }
     if(!StringUtils.isNullOrEmpty(entiy.getOrderType())){
    	 jdbcHelper.putOrder(entiy.getOrderType());
     }
      return jdbcHelper;
  }
  
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
  public Pagination<LineProductInfo> queryLineProductInfoList(LineProductInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    helper.setDefaultOrderBy("t.lineId");
    return getPage(helper.getSql(), helper.getParam(), LineProductInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveLineProductInfo(LineProductInfo entity) {
	entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateLineProductInfo(LineProductInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteLineProductInfo(LineProductInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

  /**
   * 取消检测
   * @author 刘镝
   */
    public int quxiaoDataFun(FillingPlanInfo fillingPlanInfo) {
  	  String sql = "update filling_plan_info set FILL_STATE='0',FILL_STDATE=null,FILL_EDDATE=null "
  	  		+ "where  id ='"+fillingPlanInfo.getId()+"'";
      return this.executeSQL(sql);
    }
    
    /**
     * 删除
     * @author 刘镝
     */
      public int deleteFillingPlanInfo(FillingPlanInfo fillingPlanInfo) {
    	  String sql = "delete from test_over_yn where  plan_id ='"+fillingPlanInfo.getId()+"'";
        return this.executeSQL(sql);
      }
      /**
       * 删除
       * @author 刘镝
       */
        public int deleteTestResult(FillingPlanInfo fillingPlanInfo) {
      	  String sql = "delete from test_result where  planId ='"+fillingPlanInfo.getId()+"'";
          return this.executeSQL(sql);
        }
    
/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public LineProductInfo getLineProductInfoById(String id) {
	  String sql = "select * from line_product_info where 1 = 1 and id ='"+id+"'";
      return this.queryObject(sql, LineProductInfo.class);
  }
  
  
  
    public List<LineProductInfo> queryLineProductInfoList(LineProductInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSql);
        return queryForList(helper.getSql(), helper.getParam(), LineProductInfo.class);
      }
    
    /**
     * 判断是否添加生产线
     * @author 刘镝
     */
      public Boolean checkLineProductInfo(String id) {
    	  String sql = "select count(*) from line_product_info where 1 = 1 and lineId ='"+id+"'";
    	  
	  if(this.queryForInt(sql, null) > 0){
	        return true;
	    }
	    return false;
      }
}
