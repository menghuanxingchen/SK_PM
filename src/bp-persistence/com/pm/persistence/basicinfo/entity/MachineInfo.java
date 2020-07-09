package com.pm.persistence.basicinfo.entity;

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
 * 表machine_info 的实体类
 * @author 刘镝
 */
@Entity(name = "machine_info")
public class MachineInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public MachineInfo clone() {
      MachineInfo o = null;
      try {
          o = (MachineInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 设备id
   */
  @ID
  @Column(name = "machine_id", comment = "设备id")
  private String machine_id;

  /**
   * 设备名称
   */
  @Column(name = "machine_nm", comment = "设备名称")
  private String machine_nm;

  /**
   * 设备类别
   */
  @Column(name = "machine_species_id", comment = "设备类别")
  private String machine_species_id;
  /**
   * 设备类别name
   */
  @Transient
  @Column(name = "machine_species_name", comment = "设备名称")
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
   * 区域
   */
  @Column(name = "area_id", comment = "区域")
  private String area_id;
  /**
   * 区域name
   */
  @Transient
  private String area_name;
  /**
   * 生产日期
   */
  @Column(name = "production_date", comment = "生产日期")
  private String production_date;

  /**
   * 使用年限
   */
  @Column(name = "use_year", comment = "使用年限")
  private String use_year;

  /**
   * 供应商
   */
  @Column(name = "supplier", comment = "供应商")
  private String supplier;

  /**
   * 关键备件
   */
  @Column(name = "part", comment = "关键备件")
  private String part;

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
   * 要事记项
   */
  @Column(name = "remark", comment = "要事记项")
  private String remark;

  public String getMachine_id() {
       return machine_id;
  }

  public void setMachine_id(String machine_id) {
       this.machine_id = machine_id;
  }

  public String getMachine_nm() {
       return machine_nm;
  }

  public void setMachine_nm(String machine_nm) {
       this.machine_nm = machine_nm;
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

  public String getArea_id() {
       return area_id;
  }

  public void setArea_id(String area_id) {
       this.area_id = area_id;
  }

  public String getProduction_date() {
       return production_date;
  }

  public void setProduction_date(String production_date) {
       this.production_date = production_date;
  }

  public String getUse_year() {
       return use_year;
  }

  public void setUse_year(String use_year) {
       this.use_year = use_year;
  }

  public String getSupplier() {
       return supplier;
  }

  public void setSupplier(String supplier) {
       this.supplier = supplier;
  }

  public String getPart() {
       return part;
  }

  public void setPart(String part) {
       this.part = part;
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

public String getMachine_type_name() {
	return machine_type_name;
}

public void setMachine_type_name(String machine_type_name) {
	this.machine_type_name = machine_type_name;
}

public String getArea_name() {
	return area_name;
}

public void setArea_name(String area_name) {
	this.area_name = area_name;
}

public String getRemark() {
	return remark;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public String getMachine_species_name() {
	return machine_species_name;
}

public void setMachine_species_name(String machine_species_name) {
	this.machine_species_name = machine_species_name;
}

}
