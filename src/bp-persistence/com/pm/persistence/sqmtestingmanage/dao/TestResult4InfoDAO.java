package com.pm.persistence.sqmtestingmanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
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
@Component("TestResult4InfoDAO")
public class TestResult4InfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from test_result_4 t where 1=1 ";
  
  
		  
  private JDBCHelper makeSearch(TestResult4Info entiy, String sql) {
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
	              jdbcHelper.putParam(JDBCHelper.AND,"T. filling_pakage_qualified", JDBCHelper.EQ, entiy.getFilling_pakage_qualified());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. thread_mouth_wc", JDBCHelper.EQ, entiy.getThread_mouth_wc());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. camera_product_code", JDBCHelper.EQ, entiy.getCamera_product_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. aluminum_foil_stripper", JDBCHelper.EQ, entiy.getAluminum_foil_stripper());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. powerof_sealing_machine", JDBCHelper.EQ, entiy.getPowerof_sealing_machine());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. bottlebody_printer_date", JDBCHelper.EQ, entiy.getBottlebody_printer_date());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. bottlebody_printer_lot", JDBCHelper.EQ, entiy.getBottlebody_printer_lot());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. bottlebody_distinct", JDBCHelper.EQ, entiy.getBottlebody_distinct());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. unpacker_code", JDBCHelper.EQ, entiy.getUnpacker_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. unpacker_list", JDBCHelper.EQ, entiy.getUnpacker_list());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. weight_removal_formula", JDBCHelper.EQ, entiy.getWeight_removal_formula());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ehether_cullercan_cull", JDBCHelper.EQ, entiy.getEhether_cullercan_cull());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. excludingcode_spraying_date", JDBCHelper.EQ, entiy.getExcludingcode_spraying_date());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. stripping_machinebatch_number", JDBCHelper.EQ, entiy.getStripping_machinebatch_number());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. Inspection_stripping_machine", JDBCHelper.EQ, entiy.getInspection_stripping_machine());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. product_specification_confirmation", JDBCHelper.EQ, entiy.getProduct_specification_confirmation());
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
  public Pagination<TestResult4Info> queryTestResult4InfoList(TestResult4Info entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    helper.setDefaultOrderBy("lineId,produnctCode asc, filling_date desc");
    return getPage(helper.getSql(), helper.getParam(), TestResult4Info.class, pageIndex, pageSize);
  }


}
