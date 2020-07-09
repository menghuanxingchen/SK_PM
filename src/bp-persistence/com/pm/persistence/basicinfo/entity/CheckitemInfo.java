package com.pm.persistence.basicinfo.entity;

import com.repast.core.annotation.Transient;
import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表checkitem_info 的实体类
 * @author 刘镝
 */
@Entity(name = "checkitem_info")
public class CheckitemInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 453527570414032698L;

  public CheckitemInfo clone() {
      CheckitemInfo o = null;
      try {
          o = (CheckitemInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 检查项目id
   */
  @ID
  @Column(name = "check_project_id", comment = "检查项目id")
  private String check_project_id;

  /**
   * 检查项目名称
   */
  @Column(name = "check_project_nm", comment = "检查项目名称")
  private String check_project_nm;

  /**
   * 设备类别
   */
  @Column(name = "machine_species_id", comment = "设备类别")
  private String machine_species_id;
  /**
   * 设备类别name
   */
  @Transient
  private String machine_species_name;

  /**
   * 设备类型
   */
  @Column(name = "machine_type_id", comment = "设备类型")
  private String machine_type_id;
  /**
   * 设备类型name
   */
  @Transient
  @Column(name = "machine_type_name")
  private String machine_type_name; 
  
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

  public String getCheck_project_id() {
       return check_project_id;
  }

  public void setCheck_project_id(String check_project_id) {
       this.check_project_id = check_project_id;
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

public String getMachine_species_name() {
	return machine_species_name;
}

public void setMachine_species_name(String machine_species_name) {
	this.machine_species_name = machine_species_name;
}

public String getMachine_type_name() {
	return machine_type_name;
}

public void setMachine_type_name(String machine_type_name) {
	this.machine_type_name = machine_type_name;
}

}
