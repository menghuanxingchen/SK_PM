package com.pm.persistence.preventplan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

import com.pm.persistence.preventplan.entity.PrePlanCheckDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_check_detail 的DAO类
 * @author 刘镝
 */
@Component("PrePlanCheckDetailDAO")
public class PrePlanCheckDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.*,ci.check_project_nm,ci.machine_species_id,ci.machine_type_id FROM pre_plan_check_detail T,checkitem_info ci "
  											+ "	WHERE 1=1 and ci.check_project_id = T.check_project_id ";
  private JDBCHelper makeSearch(PrePlanCheckDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_machine_id", JDBCHelper.EQ, entiy.getPlan_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_project_id", JDBCHelper.EQ, entiy.getCheck_project_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_detail", JDBCHelper.EQ, entiy.getCheck_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_detail2", JDBCHelper.EQ, entiy.getCheck_detail2());
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
  public Pagination<PrePlanCheckDetail> queryPrePlanCheckDetailList(PrePlanCheckDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), PrePlanCheckDetail.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<PrePlanCheckDetail> queryPrePlanCheckDetailListByPlanMachineId(PrePlanCheckDetail entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), PrePlanCheckDetail.class);
    }


/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int savePrePlanCheckDetail(PrePlanCheckDetail entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updatePrePlanCheckDetail(PrePlanCheckDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanCheckDetail(PrePlanCheckDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public PrePlanCheckDetail getPrePlanCheckDetailById(String id) {
    return this.getEntityById(id, PrePlanCheckDetail.class);
  }
  
  public List<String[]> getPrePlanCheckDetail(String id){
	  String sql ="call p_pre_plan_check_detaill('"+id+"');";
	  System.out.print(sql);
	  List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
	  List<String[]> datalist = new ArrayList<String[]>();
	  for(int i=0;i<list.size();i++){
		  String[] st = new String[list.get(i).size()];
		  for(int m=0;m<list.get(i).size();m++){
			  st[m] = (String) list.get(i).values().toArray()[m];
		  }
		  datalist.add(st);
	  }
	return datalist;
	  
  } 
  
  public List<String[]> getPrePlanPropCheckDetail(String id){
	  String sql ="SELECT * from pre_plan_prop_check_detail_view where pre_plan_id='"+id+"'";
	  List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
	  List<String[]> datalist = new ArrayList<String[]>();
	  for(int i=0;i<list.size();i++){
		  String[] st1 = ((String) list.get(i).get("detail")).split(",");
		  String[] st = new String[2];
		  st[0] = (String) list.get(i).get("machine_id");
		  st[1] = (String) list.get(i).get("operate_date");
		  String[] both = (String[]) ArrayUtils.addAll(st, st1);
		  datalist.add(both);
	  }
	return datalist;
	  
  } 
}
