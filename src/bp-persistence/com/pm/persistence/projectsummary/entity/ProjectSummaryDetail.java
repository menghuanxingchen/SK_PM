package com.pm.persistence.projectsummary.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表project_summary_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "project_summary_detail")
public class ProjectSummaryDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public ProjectSummaryDetail clone() {
      ProjectSummaryDetail o = null;
      try {
          o = (ProjectSummaryDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 项目详细id
   */
  @ID
  @Column(name = "project_detail_id", comment = "项目详细id")
  private String project_detail_id;

  /**
   * 项目id
   */
  @Column(name = "project_id", comment = "项目id")
  private String project_id;

  /**
   * 实际时间
   */
  @Column(name = "work_date", comment = "实际时间")
  private String work_date;

  /**
   * 工作内容
   */
  @Column(name = "work_content", comment = "工作内容")
  private String work_content;
  
  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
  private String create_id;

  /**
   * 首次创建时间
   */
  @Column(name = "create_time", comment = "首次创建时间")
  private String create_time;

  /**
   * 修改userid
   */
  @Column(name = "update_id", comment = "修改userid")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;

  public String getProject_detail_id() {
       return project_detail_id;
  }

  public void setProject_detail_id(String project_detail_id) {
       this.project_detail_id = project_detail_id;
  }

  public String getProject_id() {
       return project_id;
  }

  public void setProject_id(String project_id) {
       this.project_id = project_id;
  }

  public String getWork_date() {
       return work_date;
  }

  public void setWork_date(String work_date) {
       this.work_date = work_date;
  }

  public String getWork_content() {
       return work_content;
  }

  public void setWork_content(String work_content) {
       this.work_content = work_content;
  }

public String getCreate_id() {
	return create_id;
}

public void setCreate_id(String create_id) {
	this.create_id = create_id;
}

public String getCreate_time() {
	return create_time;
}

public void setCreate_time(String create_time) {
	this.create_time = create_time;
}

public String getUpdate_id() {
	return update_id;
}

public void setUpdate_id(String update_id) {
	this.update_id = update_id;
}

public String getUpdate_time() {
	return update_time;
}

public void setUpdate_time(String update_time) {
	this.update_time = update_time;
}

}
