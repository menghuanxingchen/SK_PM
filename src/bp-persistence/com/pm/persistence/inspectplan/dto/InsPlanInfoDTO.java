package com.pm.persistence.inspectplan.dto;

import java.io.Serializable;

import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_info 的实体类
 * @author 刘镝
 */
public class InsPlanInfoDTO extends BaseEntity implements Serializable, Cloneable {

   private static final long serialVersionUID = 1L;

   private String user_id;
   /**
    * 巡检计划id
    */
   private String plan_id;
  /**
   * 巡检计划名称
   */
  private String pre_plan_nm;

  /**
   * 计划周期
   */
  private String pre_plan_cycle;
  

  /**
   * 计划日期
   */
  private String pre_plan_date;
  /**
   * 保存同group_id下的最大日期
   */
  private String pre_plan_date_end;

  /**
   * 计划组id
   */
  private String pre_plan_group;
  
  private String pre_machine_id;
  
  private String chk_type;
  
  private String check_project_id;
  
  private String update_flag;

  public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public String getPre_plan_nm() {
	return pre_plan_nm;
}

public void setPre_plan_nm(String pre_plan_nm) {
	this.pre_plan_nm = pre_plan_nm;
}

public String getPre_plan_cycle() {
	return pre_plan_cycle;
}

public void setPre_plan_cycle(String pre_plan_cycle) {
	this.pre_plan_cycle = pre_plan_cycle;
}

public String getPre_plan_date() {
	return pre_plan_date;
}

public void setPre_plan_date(String pre_plan_date) {
	this.pre_plan_date = pre_plan_date;
}

public String getPre_plan_group() {
	return pre_plan_group;
}

public void setPre_plan_group(String pre_plan_group) {
	this.pre_plan_group = pre_plan_group;
}

public String getPre_machine_id() {
	return pre_machine_id;
}

public void setPre_machine_id(String pre_machine_id) {
	this.pre_machine_id = pre_machine_id;
}

public String getChk_type() {
	return chk_type;
}

public void setChk_type(String chk_type) {
	this.chk_type = chk_type;
}

public String getCheck_project_id() {
	return check_project_id;
}

public void setCheck_project_id(String check_project_id) {
	this.check_project_id = check_project_id;
}

public String getUpdate_flag() {
	return update_flag;
}

public void setUpdate_flag(String update_flag) {
	this.update_flag = update_flag;
}

public String getPlan_id() {
	return plan_id;
}

public void setPlan_id(String plan_id) {
	this.plan_id = plan_id;
}

public String getPre_plan_date_end() {
	return pre_plan_date_end;
}

public void setPre_plan_date_end(String pre_plan_date_end) {
	this.pre_plan_date_end = pre_plan_date_end;
}

}
