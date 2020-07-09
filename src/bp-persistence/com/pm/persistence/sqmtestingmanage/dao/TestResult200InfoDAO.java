package com.pm.persistence.sqmtestingmanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.sqmtestingmanage.entity.TestResult200Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult4Info;
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
@Component("TestResult200InfoDAO")
public class TestResult200InfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from test_result_200 t where 1=1 ";
  
  
		  
  private JDBCHelper makeSearch(TestResult200Info entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. lotnumver", JDBCHelper.EQ, entiy.getLotnumver());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. produnctCode", JDBCHelper.EQ, entiy.getProdunctCode());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. produnctName", JDBCHelper.EQ, entiy.getProdunctName());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. productType", JDBCHelper.EQ, entiy.getProductType());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. customName", JDBCHelper.EQ, entiy.getCustomName());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. unusualYn", JDBCHelper.EQ, entiy.getUnusualYn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. test_state", JDBCHelper.EQ, entiy.getTest_state());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getFilling_datest()+" 00:00:00");
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getFilling_dateed()+" 23:59:59");
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_pipeline", JDBCHelper.EQ, entiy.getFilling_pipeline());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. oil_in_pipeline", JDBCHelper.EQ, entiy.getOil_in_pipeline());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. labelling1", JDBCHelper.EQ, entiy.getLabelling1());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. labelling2", JDBCHelper.EQ, entiy.getLabelling2());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. labelling3", JDBCHelper.EQ, entiy.getLabelling3());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. labelling4", JDBCHelper.EQ, entiy.getLabelling4());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. labelling5", JDBCHelper.EQ, entiy.getLabelling5());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_weight", JDBCHelper.EQ, entiy.getFilling_weight());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_splash_yn", JDBCHelper.EQ, entiy.getFilling_splash_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_front_pressure", JDBCHelper.EQ, entiy.getFilling_front_pressure());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_back_pressure", JDBCHelper.EQ, entiy.getFilling_back_pressure());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_first_article", JDBCHelper.EQ, entiy.getFilling_first_article());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_dust_yn", JDBCHelper.EQ, entiy.getFilling_dust_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. recheck_weight", JDBCHelper.EQ, entiy.getRecheck_weight());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. reinspection_supplier_selection", JDBCHelper.EQ, entiy.getReinspection_supplier_selection());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. reinspection_IBC_leadseal", JDBCHelper.EQ, entiy.getReinspection_IBC_leadseal());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. scan_first_case", JDBCHelper.EQ, entiy.getScan_first_case());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
	      }
	      return jdbcHelper;
	  }

  
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
  public Pagination<TestResult200Info> queryTestResult200InfoList(TestResult200Info entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    helper.setDefaultOrderBy("lineId,produnctCode asc, filling_date desc");
    return getPage(helper.getSql(), helper.getParam(), TestResult200Info.class, pageIndex, pageSize);
  }


}
