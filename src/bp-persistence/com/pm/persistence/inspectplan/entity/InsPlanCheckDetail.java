package com.pm.persistence.inspectplan.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表ins_plan_check_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "ins_plan_check_detail")
public class InsPlanCheckDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public InsPlanCheckDetail clone() {
      InsPlanCheckDetail o = null;
      try {
          o = (InsPlanCheckDetail) super.clone();
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
  @Column(name = "plan_machine_id", comment = "")
  private String plan_machine_id;

  /**
   * 巡检检查项目id
   */
  @Column(name = "check_project_id", comment = "巡检检查项目id")
  private String check_project_id;
  /**
   * 巡检检查项目name
   */
  @Transient
  @Column(name = "check_project_nm")
  private String check_project_nm;
  /**
   * 设备类别
   */
  @Transient
  @Column(name = "machine_species_id")
  private String machine_species_id;
  /**
   * 设备类型
   */
  @Transient
  @Column(name = "machine_type_id")
  private String machine_type_id;
  /**
   * 检查内容
   */
  @Column(name = "check_detail", comment = "检查内容")
  private String check_detail;

  /**
   * 检查内容2
   */
  @Column(name = "check_detail2", comment = "检查内容2")
  private String check_detail2;

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

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getPlan_machine_id() {
       return plan_machine_id;
  }

  public void setPlan_machine_id(String plan_machine_id) {
       this.plan_machine_id = plan_machine_id;
  }

  public String getCheck_project_id() {
       return check_project_id;
  }

  public void setCheck_project_id(String check_project_id) {
       this.check_project_id = check_project_id;
  }

  public String getCheck_detail() {
       return check_detail;
  }

  public void setCheck_detail(String check_detail) {
       this.check_detail = check_detail;
  }

  public String getCheck_detail2() {
       return check_detail2;
  }

  public void setCheck_detail2(String check_detail2) {
       this.check_detail2 = check_detail2;
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

public String getCheck_project_nm() {
	return check_project_nm;
}

public void setCheck_project_nm(String check_project_nm) {
	this.check_project_nm = check_project_nm;
}

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getMachine_type_id() {
	return machine_type_id;
}

public void setMachine_type_id(String machine_type_id) {
	this.machine_type_id = machine_type_id;
}



}
