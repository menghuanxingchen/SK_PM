package com.pm.persistence.projectsummary.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.projectsummary.entity.ProjectSummaryDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表project_summary_detail 的DAO类
 * @author 刘镝
 */
@Component("ProjectSummaryDetailDAO")
public class ProjectSummaryDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM project_summary_detail T WHERE 1=1 ";
  private JDBCHelper makeSearch(ProjectSummaryDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. project_detail_id", JDBCHelper.EQ, entiy.getProject_detail_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. project_id", JDBCHelper.EQ, entiy.getProject_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_date", JDBCHelper.EQ, entiy.getWork_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_content", JDBCHelper.EQ, entiy.getWork_content());
      }
     jdbcHelper.putOrder(" T. work_date desc  ");
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ProjectSummaryDetail> queryProjectSummaryDetailList(ProjectSummaryDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ProjectSummaryDetail.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<ProjectSummaryDetail> queryProjectSummaryDetailList(ProjectSummaryDetail entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), ProjectSummaryDetail.class);
    }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveProjectSummaryDetail(ProjectSummaryDetail entity) {
    if(entity.getProject_detail_id()==null)
    entity.setProject_detail_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProjectSummaryDetail(ProjectSummaryDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProjectSummaryDetail(ProjectSummaryDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }
  
  /**
   * 删除实体对象entity
   * @author 刘镝
   */
    public int deleteProjectSummaryDetailByProId(String id) {
    	int flag = 0;
    	if(!StringUtils.isNullOrEmpty(id)){
    		String sql= "delete from project_summary_detail where project_id = '"+id+"'";
    	   flag = this.executeSQL(sql);
    	}
      return flag;
    }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProjectSummaryDetail getProjectSummaryDetailById(String id) {
    return this.getEntityById(id, ProjectSummaryDetail.class);
  }
}
