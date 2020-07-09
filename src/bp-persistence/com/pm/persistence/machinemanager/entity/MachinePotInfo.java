package com.pm.persistence.machinemanager.entity;

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
 * 表machine_pot_info 的实体类
 * @author 刘镝
 */
@Entity(name = "machine_pot_info")
public class MachinePotInfo extends BaseEntity implements Serializable, Cloneable {

  public MachinePotInfo clone() {
      MachinePotInfo o = null;
      try {
          o = (MachinePotInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 一级分类id
   */
  @ID
  @Column(name = "pot_id", comment = "一级分类id")
  private String pot_id;


  /**
   * 名称
   */
  @Column(name = "pot_nm", comment = "名称")
  private String pot_nm;

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
   * 
   */
  @Column(name = "remark", comment = "")
  private String remark;

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

  public String getPot_id() {
       return pot_id;
  }

  public void setPot_id(String pot_id) {
       this.pot_id = pot_id;
  }

  
  public String getPot_nm() {
       return pot_nm;
  }

  public void setPot_nm(String pot_nm) {
       this.pot_nm = pot_nm;
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

  public String getRemark() {
       return remark;
  }

  public void setRemark(String remark) {
       this.remark = remark;
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
