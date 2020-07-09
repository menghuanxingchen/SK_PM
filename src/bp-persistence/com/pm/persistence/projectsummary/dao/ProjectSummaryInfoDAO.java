package com.pm.persistence.projectsummary.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.projectsummary.dto.ProjectSummaryInfoDto;
import com.pm.persistence.projectsummary.entity.ProjectSummaryInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 *  
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表project_summary_info 的DAO类
 * @author 刘镝
 */
@Component("ProjectSummaryInfoDAO")
public class ProjectSummaryInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM project_summary_info T WHERE 1=1 ";
  public final static String QueryTwoMonthSql = " SELECT psd.work_date as workDate,psd.work_content as workContent FROM project_summary_info T,project_summary_detail psd "
										  	  + " WHERE 1=1 AND T.project_id = psd.project_id ORDER BY psd.work_date DESC LIMIT 2 ";
  public final static String QueryDataListSql = " SELECT T.*,CONCAT(T.project_nm,(select code_nm from sys_code_info where code_group_value='bigProStep' and code_value =  T.big_project_step)) as projectNm,"
  		+ " ( SELECT psd.work_date AS work_content FROM project_summary_info T1, project_summary_detail psd WHERE 1 = 1 AND t.project_id = psd.project_id AND T1.project_id = T.project_id ORDER BY psd.work_date DESC LIMIT 1 ) AS work_date1,"
  		+ " ( SELECT psd.work_content AS work_content FROM project_summary_info T1, project_summary_detail psd WHERE 1 = 1 AND t.project_id = psd.project_id AND T1.project_id = T.project_id ORDER BY psd.work_date DESC LIMIT 1 ) AS work_content1,"
  		+ " ( SELECT psd.work_content FROM project_summary_info T2, project_summary_detail psd WHERE 1 = 1 AND T2.project_id = psd.project_id AND T2.project_id = T.project_id ORDER BY psd.work_date DESC LIMIT 1,1 ) AS work_content2,"
  		+ " ( SELECT psd.work_date FROM project_summary_info T2, project_summary_detail psd WHERE 1 = 1 AND T2.project_id = psd.project_id AND T2.project_id = T.project_id ORDER BY psd.work_date DESC LIMIT 1, 1 ) AS work_date2"
  		+ " FROM project_summary_info T WHERE 1=1 ";
  public final static String QueryBigGroup = "Select T.* FROM (SELECT project_nm,project_type, big_project_group_id FROM project_summary_info  WHERE	1 = 1 and project_type = '1' GROUP BY big_project_group_id ) T WHERE 1= 1";
  private JDBCHelper makeSearch(ProjectSummaryInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. project_id", JDBCHelper.EQ, entiy.getProject_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. content", JDBCHelper.EQ, entiy.getContent());
              jdbcHelper.putParam(JDBCHelper.AND,"T. project_nm", JDBCHelper.LIKE, entiy.getProject_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. duty_user", JDBCHelper.EQ, entiy.getDuty_user());
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_start_date", JDBCHelper.GTQ, entiy.getPlan_start_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_end_date", JDBCHelper.LTQ, entiy.getPlan_end_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. real_end_date", JDBCHelper.EQ, entiy.getReal_end_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. state", JDBCHelper.EQ, entiy.getState());
              jdbcHelper.putParam(JDBCHelper.AND,"T. project_type", JDBCHelper.EQ, entiy.getProject_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. big_project_step", JDBCHelper.EQ, entiy.getBig_project_step());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
     jdbcHelper.putOrder(" project_type,big_project_group_id,plan_start_date desc ");
      return jdbcHelper;
  }
  
  private JDBCHelper makeSearch2(ProjectSummaryInfo entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. project_nm", JDBCHelper.LIKE, entiy.getProject_nm());
	      }
	      return jdbcHelper;
	  }
  
	 /**
	 * 分页查询entity List
	 * @author 刘镝
	 */
  public Pagination<ProjectSummaryInfo> queryProjectSummaryInfoList(ProjectSummaryInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryDataListSql);
    return getPage(helper.getSql(), helper.getParam(), ProjectSummaryInfo.class, pageIndex, pageSize);
  }
  
	 /**
	 * 分页查询entity List
	 * @author 刘镝
	 */
public Pagination<ProjectSummaryInfo> queryProjectSummaryInfoListBigGroup(ProjectSummaryInfo entity, int pageIndex, int pageSize) {
 JDBCHelper helper = makeSearch2(entity, QueryBigGroup);
 return getPage(helper.getSql(), helper.getParam(), ProjectSummaryInfo.class, pageIndex, pageSize);
}
  
  /**
   * 查询最近两个行为的时间和内容
   * @author fish
   */
  public List<ProjectSummaryInfoDto> queryTwoMonthInfo(){
	    return this.queryForList(QueryTwoMonthSql, ProjectSummaryInfoDto.class);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public String saveProjectSummaryInfo(ProjectSummaryInfo entity) {
    if(entity.getProject_id()==null)
    entity.setProject_id(this.getUUId());
    String id = entity.getProject_id();
    if(PmConstant.PROJECT_TYPE_BIG.equals(entity.getProject_type())&&"".equals(entity.getBig_project_group_id())){
    	entity.setBig_project_group_id(this.getUUId());
    }
    int flag =  this.saveNew(entity);
    if(flag>0){
    	 return id;
    }else{
    	return "false";
    }
   
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProjectSummaryInfo(ProjectSummaryInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }
  
  /**
   * 修改实体对象entity
   * @author 刘镝
   */
    public int updateBigProName(ProjectSummaryInfo entity) {
      String sql = "UPDATE   project_summary_info SET project_nm = '"+entity.getProject_nm()+"' WHERE big_project_group_id = '"+entity.getBig_project_group_id()+"' ";
      return this.executeSQL(sql);
    }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProjectSummaryInfo(ProjectSummaryInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProjectSummaryInfo getProjectSummaryInfoById(String id) {
    return this.getEntityById(id, ProjectSummaryInfo.class);
  }
  
  /**
   * 根据实体对象Id返回entity
   * @author 刘镝
   */
    public ProjectSummaryInfo getProjectSummaryInfoByIdForExcel(String id) {
    	String sql = " SELECT T.*,s1.code_nm as project_type_nm,s2.code_nm as state_nm FROM project_summary_info T,sys_code_info s1,sys_code_info s2 WHERE 1=1 and s1.code_group_value = 'projectPlanType' "
    			+ " and s2.code_group_value = 'projectPlanState' and s1.code_value = T.project_type and s2.code_value = T.state and T.project_id = '"+id+"'";
      return this.queryObject(sql, ProjectSummaryInfo.class);
    }
}
