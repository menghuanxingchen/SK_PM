package com.pm.persistence.preventplan.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_prop_machine_info 的实体类
 * @author 刘镝
 */
@Entity(name = "pre_plan_prop_machine_info")
public class PrePlanPropMachineInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 8144900203709037900L;

  public PrePlanPropMachineInfo clone() {
      PrePlanPropMachineInfo o = null;
      try {
          o = (PrePlanPropMachineInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "prop_plan_id", comment = "")
  private String prop_plan_id;

  /**
   * 保养计划id
   */
  @Column(name = "pre_plan_id", comment = "保养计划id")
  private String pre_plan_id;

  /**
   * 预见性设备
   */
  @Column(name = "prop_machine_id", comment = "预见性设备")
  private String prop_machine_id;

  /**
   * 检查类型分类
   */
  @Column(name = "chk_type", comment = "检查类型分类")
  private String chk_type;

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
   * 编辑类型（修改或新增）
   */
  @Transient
  private String editType;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;
  
  /**
   * 检查项目总数
   */
  @Column(name = "pre_check_all", comment = "检查项目总数")
  private String pre_check_all;

  public String getProp_plan_id() {
       return prop_plan_id;
  }

  public void setProp_plan_id(String prop_plan_id) {
       this.prop_plan_id = prop_plan_id;
  }

  public String getPre_plan_id() {
       return pre_plan_id;
  }

  public void setPre_plan_id(String pre_plan_id) {
       this.pre_plan_id = pre_plan_id;
  }

  public String getProp_machine_id() {
       return prop_machine_id;
  }

  public void setProp_machine_id(String prop_machine_id) {
       this.prop_machine_id = prop_machine_id;
  }

  public String getChk_type() {
       return chk_type;
  }

  public void setChk_type(String chk_type) {
       this.chk_type = chk_type;
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

public String getEditType() {
	return editType;
}

public void setEditType(String editType) {
	this.editType = editType;
}

public String getPre_check_all() {
	return pre_check_all;
}

public void setPre_check_all(String pre_check_all) {
	this.pre_check_all = pre_check_all;
}

}
