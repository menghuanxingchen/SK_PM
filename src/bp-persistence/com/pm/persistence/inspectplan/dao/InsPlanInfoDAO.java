package com.pm.persistence.inspectplan.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
import com.pm.persistence.inspectplan.dto.InsPlanInfoDTO;
import com.pm.persistence.inspectplan.entity.InsPlanCheckDetail;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.inspectplan.entity.InsPlanMachineInfo;
import com.pm.persistence.preventplan.dto.PrePlanInfoDTO;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表ins_plan_info 的DAO类
 * @author 刘镝
 */
@Component("InsPlanInfoDAO")
public class InsPlanInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM ins_plan_info T WHERE 1=1 ";
  public final static String QueryTableSqlNew = "SELECT DISTINCT T.*,(weekday(t.ins_plan_date)+1) as weekday, B.machine_species_id,(select t1.code_nm from sys_code_info t1 where t1.code_value = t.ins_plan_cycle and t1.code_group_value = 'cycleCode') as 'ins_plan_cycle_name' FROM ins_plan_info T, ins_plan_machine_info A, machine_info B "
		  										+" WHERE 1=1 and A.ins_plan_id = T.ins_plan_id AND A.ins_machine_id = B.machine_id ";
 
  public final static String QueryTableSqlNew2 = "SELECT DISTINCT T.*,(weekday(t.ins_plan_date)+1) as weekday, B.machine_species_id,(select t1.code_nm from sys_code_info t1 where t1.code_value = t.ins_plan_cycle and t1.code_group_value = 'cycleCode') as 'ins_plan_cycle_name' FROM ins_plan_info T, ins_plan_machine_info A, machine_info B "
			+" WHERE 1=1 and A.ins_plan_id = T.ins_plan_id AND A.ins_machine_id = B.machine_id   AND t.ins_plan_state <> '03' ";
  private JDBCHelper makeSearch(InsPlanInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_id", JDBCHelper.EQ, entiy.getIns_plan_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_nm", JDBCHelper.LIKE, entiy.getIns_plan_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_cycle", JDBCHelper.EQ, entiy.getIns_plan_cycle());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_date", JDBCHelper.EQ, entiy.getIns_plan_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_date", JDBCHelper.GTQ, entiy.getDate_start());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_date", JDBCHelper.LTQ, entiy.getDate_end());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_group", JDBCHelper.EQ, entiy.getIns_plan_group());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_state", JDBCHelper.EQ, entiy.getIns_plan_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_mach_num", JDBCHelper.EQ, entiy.getChk_mach_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. chk_mach_all", JDBCHelper.EQ, entiy.getChk_mach_all());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_pm_rate", JDBCHelper.EQ, entiy.getIns_pm_rate());
              jdbcHelper.putParam(JDBCHelper.AND,"T. confirm_yn", JDBCHelper.EQ, entiy.getConfirm_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"B. machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
     }
     if(StringUtils.isNullOrEmpty(entiy.getOrderType())){
    	 jdbcHelper.putOrder(" T. ins_plan_date asc ");
     }else{
    	 jdbcHelper.putOrder(" T. ins_plan_state asc , "+entiy.getOrderType());
     }
    
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<InsPlanInfo> queryInsPlanInfoList(InsPlanInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSqlNew);
    return getPage(helper.getSql(), helper.getParam(), InsPlanInfo.class, pageIndex, pageSize);
  }
  private JDBCHelper makeSearch1(InsPlanInfo entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_nm", JDBCHelper.LIKE, entiy.getIns_plan_nm());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_cycle", JDBCHelper.EQ, entiy.getIns_plan_cycle());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_date", JDBCHelper.GTQ, entiy.getDate_start());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_date", JDBCHelper.LTQ, entiy.getDate_end());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_group", JDBCHelper.EQ, entiy.getIns_plan_group());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. ins_plan_state", JDBCHelper.EQ, entiy.getIns_plan_state());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
	     }
	      return jdbcHelper;
	  }
  /**
   * 分页查询entity List Groupby
   * @author 刘镝
   */
    public Pagination<InsPlanInfo> queryInsPlanInfoListGroupby(InsPlanInfo entity, int pageIndex, int pageSize) {
     String sql = " select T.* FROM (SELECT DISTINCT C.*, B.machine_species_id,(	SELECT t1.code_nm	FROM sys_code_info t1	WHERE t1.code_value = C.ins_plan_cycle AND t1.code_group_value = 'cycleCode') AS 'ins_plan_cycle_name' FROM ins_plan_info C, ins_plan_machine_info A, machine_info B "
    		 		+" WHERE 1 = 1 AND A.ins_plan_id = C.ins_plan_id AND A.ins_machine_id = B.machine_id order by C.ins_plan_date DESC ) T where 1=1 ";
     JDBCHelper helper = makeSearch1(entity, sql);
     sql=helper.getSql()+" GROUP BY T.ins_plan_group ";
     return getPage(sql, helper.getParam(), InsPlanInfo.class, pageIndex, pageSize);
    }
  /**
   * reportAll 查询planid
   * @author fish
   */
    public List<InsPlanInfo> queryInsPlansListNew(InsPlanInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSqlNew);
      return this.queryForList(helper.getSql(),helper.getParam(),InsPlanInfo.class);
    }
    
  /**
   * 没有分页得到entity List
   * @author sunaibo
   */
    public List<InsPlanInfo> queryInsPlansList(InsPlanInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return this.queryForList(helper.getSql(),helper.getParam(),InsPlanInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveInsPlanInfo(InsPlanInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateInsPlanInfo(InsPlanInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteInsPlanInfo(InsPlanInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public InsPlanInfo getInsPlanInfoById(String id) {
    return this.getEntityById(id, InsPlanInfo.class);
  }
  /**
   * 调用存储过程， 删除三张表数据
   * @author sunaibo
   */
  public int deleteInsPlanPro(InsPlanInfo entity){
	  String sql = "";	 
		  sql = " call delete_ins_plan_threetable_pro('"+entity.getIns_plan_id()+"')";
	  return this.executeSQL(sql);
  }
  /**
   * 调用存储过程， 删除三张表数据
   * @author sunaibo
   */
  public int deleteInsPlanBatchPro(InsPlanInfo entity){
	  String sql = "";	 
		  sql = " call delete_ins_plan_batch_pro('"+entity.getIns_plan_group()+"','"+entity.getIns_plan_date()+"')";
	  return this.executeSQL(sql);
  }
  /**
   * 调用存储过程，循环插入三张表
   * @author sunaibo
   */
  public int saveInsPlanPro(InsPlanInfoDTO insPlanInfoDTO){
	  String sql = "";
	  if(PmConstant.CYCLE_HALFDAY.equals(insPlanInfoDTO.getPre_plan_cycle())){
		  sql = " call ins_plan_insert_hour_pro "
		  		+ "('"+insPlanInfoDTO.getUser_id()+"','"+insPlanInfoDTO.getPre_plan_nm()+"',"
				  	+ "'"+insPlanInfoDTO.getPre_plan_cycle()+"','"+insPlanInfoDTO.getPre_plan_date()+"','"+insPlanInfoDTO.getPre_machine_id()+"',"
				  	+ "'"+insPlanInfoDTO.getChk_type()+"','"+insPlanInfoDTO.getCheck_project_id()+"','"+insPlanInfoDTO.getPre_plan_date_end()+"','"+insPlanInfoDTO.getPre_plan_group()+"') ";
	  }else{
	  sql = " call ins_plan_insert_pro"
	  		+ "('"+insPlanInfoDTO.getUser_id()+"','"+insPlanInfoDTO.getPre_plan_nm()+"',"
	  		+ "'"+insPlanInfoDTO.getPre_plan_cycle()+"','"+insPlanInfoDTO.getPre_plan_date()+"','"+insPlanInfoDTO.getPre_machine_id()+"',"
	  				+ "'"+insPlanInfoDTO.getChk_type()+"','"+insPlanInfoDTO.getCheck_project_id()+"','"+insPlanInfoDTO.getPre_plan_date_end()+"','"+insPlanInfoDTO.getPre_plan_group()+"') ";
	  }
	  return this.executeSQL(sql);
  }
  /**
   * 无循环 单条插入
   * @author sunaibo
   */
  public int saveInsPlanOnePro(InsPlanInfoDTO insPlanInfoDTO){
	  String sql = "";
	  sql = " call ins_plan_insert_pro"
		  		+ "('"+insPlanInfoDTO.getUser_id()+"','"+insPlanInfoDTO.getPre_plan_nm()+"',"
		  		+ "'"+insPlanInfoDTO.getPre_plan_cycle()+"','"+insPlanInfoDTO.getPre_plan_date()+"','"+insPlanInfoDTO.getPre_machine_id()+"',"
		  				+ "'"+insPlanInfoDTO.getChk_type()+"','"+insPlanInfoDTO.getCheck_project_id()+"','"+insPlanInfoDTO.getPre_plan_date_end()+"','"+insPlanInfoDTO.getPre_plan_group()+"') ";
	  return this.executeSQL(sql);
  }
  /**
   * 没有分页得到entity List
   * @author sunaibo
   */
    public List<InsPlanMachineInfo> queryMachineInfoViewList(String plan_id) {
      String sql="select * from ins_machine_view where ins_plan_id='"+plan_id+"'";
      return this.queryForList(sql,InsPlanMachineInfo.class);
    }
    /**
     * 没有分页得到entity List
     * @author sunaibo
     */
      public List<InsPlanCheckDetail> queryCheckItemViewList(String plan_id) {
        String sql=" SELECT t.* from checkitem_info t "
        		+ " where t.check_project_id in( "
        		+ "	select DISTINCT(v.check_project_id)from ins_plan_check_detial_view v "
        		+ " where ins_plan_id='"+plan_id+"') ";
        return this.queryForList(sql,InsPlanCheckDetail.class);
      }
      /**
       * 没有分页得到entity List
       * @author sunaibo
       */
        public List<InsPlanInfo> queryInsPlansGroupList(InsPlanInfo entity) {
        	String sql=" select * from ins_plan_info t where 1=1 ";
        			if(!"".equals(entity.getIns_plan_group())&&entity.getIns_plan_group()!=null){
        				sql=sql+" and t.ins_plan_group='"+entity.getIns_plan_group()+"' ";
        			}
        			if(!"".equals(entity.getIns_pm_rate())&&entity.getIns_pm_rate()!=null){
        				sql=sql+" and t.ins_pm_rate <> '0' ";
        			}
        			if(!"".equals(entity.getIns_plan_date())&&entity.getIns_plan_date()!=null){
        				sql=sql+" and t.ins_plan_date>='"+entity.getIns_plan_date()+"' ";
        			}
        				
        			sql=sql+ " order by t.ins_plan_date desc";
        	return this.queryForList(sql,InsPlanInfo.class);
        }
        
    /**
     * 分页查询entity List
     * @author 刘镝
     */
      public List<InsPlanInfo> queryInsPlanInfoListMob(InsPlanInfo entity) {    	  
        JDBCHelper helper = makeSearch(entity, QueryTableSqlNew2);
      /*  String sql = helper.getSql() +" and t.ins_plan_state<>'"+PmConstant.PLAN_STATE_03+"'";*/
        return queryForList( helper.getSql(),helper.getParam(),InsPlanInfo.class);
      }
      /**
       * update state 批量
       * @author sunaibo
       */
        public int updateStateBatch(InsPlanInfo entity) {
        	String sql=" update ins_plan_info "
        			+ " set ins_plan_state='"+entity.getIns_plan_state()+"' "        			
        			+ " where 1=1 and ins_plan_id in("+entity.getIns_plan_id()+") ";
          int flag =  this.executeSQL(sql);
          return flag;
        }
        /**
         * delete 批量
         * @author sunaibo
         */
          public int deleteBatch(InsPlanInfo entity) {
          	String sql=" delete from ins_plan_info "
          			+ " where  ins_plan_id in("+entity.getIns_plan_id()+") and chk_mach_num= 0";
            int flag =  this.executeSQL(sql);
            return flag;
          }
          /**
           * 没有分页得到entity List
           * @author xuning
           */
          public List<InsPlanInfo> queryCheckInsPlansGroupList(InsPlanInfo entity) {
            	String sql=" select * from ins_plan_info where ins_plan_id in("+entity.getIns_plan_id()+")  and chk_mach_num<> 0 ";
            	return this.queryForList(sql,InsPlanInfo.class);
            }
}
