package com.pm.persistence.seccheckinfo.dao;

import org.springframework.stereotype.Component;

import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.seccheckinfo.dto.SecCheckInfoDTO;
import com.pm.persistence.seccheckinfo.entity.SecCheckInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sec_check_info 的DAO类
 * @author 刘镝
 */
@Component("SecCheckInfoDAO")
public class SecCheckInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM sec_check_info T WHERE 1=1 and t.up_check_date<= YEAR( NOW( ) ) ";
  private JDBCHelper makeSearch(SecCheckInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_check_id", JDBCHelper.EQ, entiy.getSec_check_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_type", JDBCHelper.EQ, entiy.getWork_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_detail", JDBCHelper.EQ, entiy.getSec_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_num", JDBCHelper.EQ, entiy.getSec_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. up_check_date", JDBCHelper.EQ, entiy.getUp_check_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. frequency", JDBCHelper.EQ, entiy.getFrequency());
              jdbcHelper.putParam(JDBCHelper.AND,"T. failure_date", JDBCHelper.EQ, entiy.getFailure_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cur_state", JDBCHelper.EQ, entiy.getCur_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_plan_date", JDBCHelper.EQ, entiy.getSec_plan_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_check_date", JDBCHelper.EQ, entiy.getSec_check_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_result", JDBCHelper.EQ, entiy.getSec_result());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_dept", JDBCHelper.EQ, entiy.getCheck_dept());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }
  private JDBCHelper makeSearch2(SecCheckInfoDTO entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. work_type", JDBCHelper.EQ, entiy.getWork_type());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getStart_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getEnd_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sec_detail", JDBCHelper.LIKE, entiy.getSec_detail());
	              if(!"".equals(entiy.getOrderType())&&entiy.getOrderType()!=null){
	            	  jdbcHelper.putOrder("  T. "+entiy.getOrderType());
	              }
/*	              if("1".equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. approval_date desc");
	              }else{
	            	  jdbcHelper.putOrder("T. repair_order_num desc");
	              }*/
	      }
	      return jdbcHelper;
	  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SecCheckInfo> querySecCheckInfoList(SecCheckInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SecCheckInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author xuning
   */
    public Pagination<SecCheckInfoDTO> querySecCheckInfoList2(SecCheckInfoDTO entity, int pageIndex, int pageSize) {
      JDBCHelper helper = makeSearch2(entity, QueryTableSql);
      return getPage(helper.getSql(), helper.getParam(), SecCheckInfoDTO.class, pageIndex, pageSize);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSecCheckInfo(SecCheckInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSecCheckInfo(SecCheckInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSecCheckInfo(SecCheckInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SecCheckInfo getSecCheckInfoById(String id) {
    return this.getEntityById(id, SecCheckInfo.class);
  }
  /**
   * 删除计划送检日期在今年之后，并且类别与传的参数类别一样
   * @author xuning
   */
  public int deleteSecCheckInfoUpdate(SecCheckInfo entity){
	  String sql = "";	 
		  sql = "delete from sec_check_info  where sec_plan_date>YEAR( NOW( ) ) and work_type="+entity.getWork_type();
	  return this.executeSQL(sql);
  }
}
