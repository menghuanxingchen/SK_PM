package com.pm.persistence.projectsummary.entity;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表project_summary_info 的实体类
 * @author 刘镝
 */
@Entity(name = "project_summary_info")
public class ProjectSummaryInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 5795186162289562464L;

  public ProjectSummaryInfo clone() {
      ProjectSummaryInfo o = null;
      try {
          o = (ProjectSummaryInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 序号
   */
  @Transient
  @Column(name = "number", comment = " 序号")
  private String number;
  /**
   * 项目ID
   */
  @ID
  @Column(name = "project_id", comment = "项目ID")
  private String project_id;

  /**
   * 项目内容
   */
  @Column(name = "content", comment = "项目内容")
  private String content;

  /**
   * 项目名称
   */
  @Column(name = "project_nm", comment = "项目名称")
  private String project_nm;
  
  /**
   * 大项目名称+步骤
   */
  @Transient
  @Column(name = "projectNm", comment = "项目名称")
  private String projectNm;

  /**
   * 负责人
   */
  @Column(name = "duty_user", comment = "负责人")
  private String duty_user;

  /**
   * 计划开始时间
   */
  @Column(name = "plan_start_date", comment = "计划开始时间")
  private String plan_start_date;

  /**
   * 计划结束时间
   */
  @Column(name = "plan_end_date", comment = "计划结束时间")
  private String plan_end_date;

  /**
   * 实际完成时间
   */
  @Column(name = "real_end_date", comment = "实际完成时间")
  private String real_end_date;

  /**
   * 项目状态
   */
  @Column(name = "state", comment = "项目状态")
  private String state;

   
   /**
    * 项目状态名称
    */
  	@Transient
   @Column(name = "state_nm", comment = "项目状态名称")
   private String state_nm;
  /**
   * 项目类型
   */
  @Column(name = "project_type", comment = "项目类型")
  private String project_type;
  
  /**
   * 项目类型
   */
  @Transient
  @Column(name = "project_type_nm", comment = "项目类型名称")
  private String project_type_nm;

  /**
   * 项目步骤id
   */
  @Column(name = "big_project_step", comment = "项目步骤id")
  private String big_project_step;
  
  /**
   * 项目步骤名称
   */
  @Transient
  @Column(name = "big_project_step_nm", comment = "项目步骤名称")
  private String big_project_step_nm;
  
  /**
   * 项目组id
   */
  @Column(name = "big_project_group_id", comment = "项目组id")
  private String big_project_group_id;

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
  
  @Transient
  @Column(name = "work_date1", comment = "实际时间1")
  private String work_date1;
  
  @Transient
  @Column(name = "work_date2", comment = "实际时间2")
  private String work_date2;
  
  @Transient
  @Column(name = "work_content1", comment = "实际内容1")
  private String work_content1;
  
  @Transient
  @Column(name = "work_content2", comment = "实际内容2")
  private String work_content2;
  
  @Transient
  @Column(name = "work_date", comment = "实际时间")
  private String work_date;
  
  @Transient
  @Column(name = "work_content", comment = "实际内容")
  private String work_content;
  
  @Transient
  private List<SysCodeInfo> projectPlanState;
  
  @Transient
  private List<ProjectSummaryDetail> projectSummaryDetailList;
  
  @Transient
  private List<SysCodeInfo> projectStep;
  
  /**
   * 交期
   */
  @Column(name = "delivery_time", comment = "交期")
  private String delivery_time;

  public String getProject_id() {
       return project_id;
  }

  public void setProject_id(String project_id) {
       this.project_id = project_id;
  }

  public String getContent() {
       return content;
  }

  public void setContent(String content) {
       this.content = content;
  }

  public String getProject_nm() {
       return project_nm;
  }

  public void setProject_nm(String project_nm) {
       this.project_nm = project_nm;
  }

  public String getDuty_user() {
       return duty_user;
  }

  public void setDuty_user(String duty_user) {
       this.duty_user = duty_user;
  }

  public String getPlan_start_date() {
       return plan_start_date;
  }

  public void setPlan_start_date(String plan_start_date) {
       this.plan_start_date = plan_start_date;
  }

  public String getPlan_end_date() {
       return plan_end_date;
  }

  public void setPlan_end_date(String plan_end_date) {
       this.plan_end_date = plan_end_date;
  }

  public String getReal_end_date() {
       return real_end_date;
  }

  public void setReal_end_date(String real_end_date) {
       this.real_end_date = real_end_date;
  }

  public String getState() {
       return state;
  }

  public void setState(String state) {
       this.state = state;
  }

  public String getProject_type() {
       return project_type;
  }

  public void setProject_type(String project_type) {
       this.project_type = project_type;
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

public String getWork_content1() {
	return work_content1;
}

public void setWork_content1(String work_content1) {
	this.work_content1 = work_content1;
}

public String getWork_content2() {
	return work_content2;
}

public void setWork_content2(String work_content2) {
	this.work_content2 = work_content2;
}

public String getProject_type_nm() {
	return project_type_nm;
}

public void setProject_type_nm(String project_type_nm) {
	this.project_type_nm = project_type_nm;
}

public String getState_nm() {
	return state_nm;
}

public void setState_nm(String state_nm) {
	this.state_nm = state_nm;
}

public String getBig_project_step() {
	return big_project_step;
}

public void setBig_project_step(String big_project_step) {
	this.big_project_step = big_project_step;
}

public String getProjectNm() {
	return projectNm;
}

public void setProjectNm(String projectNm) {
	this.projectNm = projectNm;
}

public List<SysCodeInfo> getProjectPlanState() {
	return projectPlanState;
}

public void setProjectPlanState(List<SysCodeInfo> projectPlanState) {
	this.projectPlanState = projectPlanState;
}

public List<SysCodeInfo> getProjectStep() {
	return projectStep;
}

public void setProjectStep(List<SysCodeInfo> projectStep) {
	this.projectStep = projectStep;
}

public String getBig_project_group_id() {
	return big_project_group_id;
}

public void setBig_project_group_id(String big_project_group_id) {
	this.big_project_group_id = big_project_group_id;
}

public String getBig_project_step_nm() {
	return big_project_step_nm;
}

public void setBig_project_step_nm(String big_project_step_nm) {
	this.big_project_step_nm = big_project_step_nm;
}

public String getWork_date1() {
	return work_date1;
}

public void setWork_date1(String work_date1) {
	this.work_date1 = work_date1;
}

public String getWork_date2() {
	return work_date2;
}

public void setWork_date2(String work_date2) {
	this.work_date2 = work_date2;
}

public List<ProjectSummaryDetail> getProjectSummaryDetailList() {
	return projectSummaryDetailList;
}

public void setProjectSummaryDetailList(List<ProjectSummaryDetail> projectSummaryDetailList) {
	this.projectSummaryDetailList = projectSummaryDetailList;
}

public String getDelivery_time() {
	return delivery_time;
}

public void setDelivery_time(String delivery_time) {
	this.delivery_time = delivery_time;
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

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}


}
