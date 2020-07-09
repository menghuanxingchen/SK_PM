package com.pm.persistence.preventplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.preventplan.dto.PrePlanInfoDTO;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_info 的DAO类
 * @author 刘镝
 */
@Component("PrePlanInfoDAO")
public class PrePlanInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT distinct T.*,mi.machine_species_id,mi.machine_type_id,(select code_nm from sys_code_info where code_value = t.pre_plan_cycle and code_group_value = 'cycleCode') as pre_plan_cycle_nm FROM pre_plan_info T,pre_plan_machine_info m,machine_info mi "
  		+ " WHERE 1=1 and T.pre_plan_id = m.pre_plan_id and mi.machine_id = m.pre_machine_id ";
  public static String QueryTableSqlPlanType = "SELECT distinct T.pre_plan_id,T. pre_plan_nm,T. pre_plan_cycle,T. pre_plan_group,T. pre_plan_state,T.operate_date,T.create_id,mi.machine_species_id,T. pre_pm_rate,mi.machine_type_id,(select code_nm from sys_code_info where code_value = t.pre_plan_cycle and code_group_value = 'cycleCode') as pre_plan_cycle_nm FROM pre_plan_info T,pre_plan_machine_info m,machine_info mi "
	  		+ " WHERE 1=1 and T.pre_plan_id = m.pre_plan_id and mi.machine_id = m.pre_machine_id ";
  private JDBCHelper makeSearch(PrePlanInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_id", JDBCHelper.EQ, entiy.getPre_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_nm", JDBCHelper.LIKE, entiy.getPre_plan_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_cycle", JDBCHelper.EQ, entiy.getPre_plan_cycle());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_date", JDBCHelper.GTQ, entiy.getStart_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_date", JDBCHelper.LTQ, entiy.getEnd_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_group", JDBCHelper.EQ, entiy.getPre_plan_group());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_plan_state", JDBCHelper.EQ, entiy.getPre_plan_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_mach_num", JDBCHelper.EQ, entiy.getChk_mach_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_mach_all", JDBCHelper.EQ, entiy.getChk_mach_all());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pre_pm_rate", JDBCHelper.EQ, entiy.getPre_pm_rate());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"MI.machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
              jdbcHelper.putParam(JDBCHelper.AND,"MI.machine_type_id", JDBCHelper.EQ, entiy.getMachine_type_id());
      }
     if(StringUtils.isNullOrEmpty(entiy.getOrderType())){
    	 jdbcHelper.putOrder(" T. pre_plan_date asc ");
     }else{
    	 jdbcHelper.putOrder(" T. pre_plan_state asc , "+entiy.getOrderType());
     }
    
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<PrePlanInfo> queryPrePlanInfoList(PrePlanInfo entity, int pageIndex, int pageSize) {
	  JDBCHelper helper = null;
	  String sql = "";
	  if(PmConstant.PLAN_LIST.equals(entity.getPlan_type())){
		  helper =  makeSearch(entity, QueryTableSql);
		  sql =  helper.getSql();
	  }else if(PmConstant.PLAN_TYPE_LIST.equals(entity.getPlan_type())){
		  helper = makeSearch(entity, QueryTableSqlPlanType );
		   sql = "SELECT * FROM (" +helper.getSql() +")A GROUP BY pre_plan_group ";
	  }else {
		  helper =  makeSearch(entity, QueryTableSql);
		  sql =  helper.getSql();
	  }
    return getPage(sql, helper.getParam(), PrePlanInfo.class, pageIndex, pageSize);
  }
  
  /**
   * 查询entity List
   * @author 刘镝
   */
    public List<PrePlanInfo> queryPrePlanInfoListNoPage(PrePlanInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(), helper.getParam(), PrePlanInfo.class);
    }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int savePrePlanInfo(PrePlanInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updatePrePlanInfo(PrePlanInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deletePrePlanInfo(PrePlanInfo entity) {
	  String sql = "";
	  if(!StringUtils.isNullOrEmpty(entity.getPre_plan_id())){
		  sql = " call p_delete_pre_plan_threetable_pro ('"+entity.getPre_plan_id()+"')";
	  }
	  return this.executeSQL(sql);
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public PrePlanInfo getPrePlanInfoById(String id) {
    return this.getEntityById(id, PrePlanInfo.class);
  }
  
  public int savePerPlan(PrePlanInfoDTO prePlanInfoDTO){
	  String sql = "";
	  if(PmConstant.CYCLE_HALFDAY.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert_hour ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }else if(PmConstant.CYCLE_TWOYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert2 ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }else if(PmConstant.CYCLE_FIVEYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert5year ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  else if(PmConstant.CYCLE_SIXYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert6year ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  else if(PmConstant.CYCLE_TWELVEYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert12year ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  else if(PmConstant.CYCLE_ELEVENYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert11year ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  else if(PmConstant.CYCLE_THREEYEAR.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert3 ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }else if(PmConstant.CYCLE_TWOMONTH.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert4 ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";	
	  }else if(PmConstant.CYCLE_TWODAY.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert5 ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";	
	  }else if(PmConstant.CYCLE_BANMONTH.equals(prePlanInfoDTO.getPre_plan_cycle())){
		  sql = " call p_pre_plan_insert6 ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";		  
	  }else{
		  sql = " call p_pre_plan_insert ('"+prePlanInfoDTO.getUser_id()+"','"+prePlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+prePlanInfoDTO.getPre_plan_cycle()+"','"+prePlanInfoDTO.getPre_plan_date()+"','"+prePlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+prePlanInfoDTO.getChk_type()+"','"+prePlanInfoDTO.getCheck_project_id()+"','"+prePlanInfoDTO.getPre_plan_date_end()+"','"+prePlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  

	  
	  return this.executeSQL(sql);
  }
  /**
   * 没有分页得到entity List
   */
    public List<PrePlanInfo> queryPrePlansGroupList(PrePlanInfo entity) {
    	String sql=" select * from pre_plan_info t where 1=1 ";
    			if(!"".equals(entity.getPre_plan_group())&&entity.getPre_plan_group()!=null){
    				sql=sql+" and t.pre_plan_group='"+entity.getPre_plan_group()+"' ";
    			}
    			if(!"".equals(entity.getPre_pm_rate())&&entity.getPre_pm_rate()!=null){
    				sql=sql+" and t.pre_pm_rate <> '0' ";
    			}
    				sql=sql+" and t.pre_plan_date>=CURDATE() "
    						+ " order by t.pre_plan_date desc";
    	return this.queryForList(sql,PrePlanInfo.class);
    }
    /**
     * 调用存储过程， 删除三张表数据
     */
    public int deletePrePlanBatchPro(PrePlanInfo entity){
  	  String sql = "";	 
  		  sql = " call p_delete_pre_plan_batch_pro('"+entity.getPre_plan_group()+"','"+entity.getPre_plan_date()+"')";
  	  return this.executeSQL(sql);
    }
    /**
     * 调用存储过程， 批量删除三张表数据
     */
    public int deleteCheck_detail(PrePlanInfo entity){
  	  String sql = "";	 
  		  sql = " DELETE from pre_plan_check_detail "
  		  		+ "WHERE plan_machine_id IN (select t.plan_machine_id "
  		  		+ "from pre_plan_machine_info t where t.pre_plan_id in( "+ entity.getPre_plan_group() +"))";
  		  
  	  return this.executeSQL(sql);
    }
    
    /**
     * 调用存储过程， 批量删除三张表数据
     */
    public int deleteMachine_info(PrePlanInfo entity){
  	  String sql = "";	 
  		  sql = " DELETE from pre_plan_machine_info where pre_plan_id in( "+ entity.getPre_plan_group() +")";
  	  return this.executeSQL(sql);
    }
    
    /**
     * 调用存储过程， 批量删除三张表数据
     */
    public int deletePlan_info(PrePlanInfo entity){
  	  String sql = "";	 
  		  sql = " DELETE from pre_plan_info WHERE pre_plan_id in( "+ entity.getPre_plan_group() +")";
  	  return this.executeSQL(sql);
    }
    /**
     * 无循环 单条插入
     */
    public int savePrePlanOnePro(PrePlanInfoDTO entity){
  	  String sql = "";
  	  sql = " call p_pre_plan_insert"
  		  		+ "('"+entity.getUser_id()+"','"+entity.getPre_plan_nm()+"',"
  		  		+ "'"+entity.getPre_plan_cycle()+"','"+entity.getPre_plan_date()+"','"+entity.getPre_machine_id()+"',"
  		  				+ "'"+entity.getChk_type()+"','"+entity.getCheck_project_id()+"','"+entity.getPre_plan_date_end()+"','"+entity.getPre_plan_group()+"') ";
  	  System.out.println(sql);
  	  return this.executeSQL(sql);
    }
    
    /**
     * update state 批量
     * @author sunaibo
     */
      public int updateStateBatch(PrePlanInfo entity) {
      	String sql=" update pre_plan_info "
      			+ " set pre_plan_state='"+entity.getPre_plan_state()+"' "        			
      			+ " where 1=1 and pre_plan_id in("+entity.getPre_plan_id()+") ";
        int flag =  this.executeSQL(sql);
        return flag;
      }
    
}
