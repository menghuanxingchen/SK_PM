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
 * 表pre_plan_prop_check_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "pre_plan_prop_check_detail")
public class PrePlanPropCheckDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 140176415760723591L;

  public PrePlanPropCheckDetail clone() {
      PrePlanPropCheckDetail o = null;
      try {
          o = (PrePlanPropCheckDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "id", comment = "")
  private String id;

  /**
   * 
   */
  @Column(name = "prop_plan_id", comment = "")
  private String prop_plan_id;

  /**
   * 检查内容
   */
  @Column(name = "check_detail", comment = "检查内容")
  private String check_detail;

  /**
   * 实际时间
   */
  @Column(name = "operate_date", comment = "实际时间")
  private String operate_date;

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
  
  /**
   * 编辑类型（修改或新增）
   */
  @Transient
  private String editType;

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getProp_plan_id() {
       return prop_plan_id;
  }

  public void setProp_plan_id(String prop_plan_id) {
       this.prop_plan_id = prop_plan_id;
  }

  public String getCheck_detail() {
       return check_detail;
  }

  public void setCheck_detail(String check_detail) {
       this.check_detail = check_detail;
  }

  public String getOperate_date() {
       return operate_date;
  }

  public void setOperate_date(String operate_date) {
       this.operate_date = operate_date;
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

}
