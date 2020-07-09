package com.pm.persistence.repair.entity;

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
 * 表repair_cost_detail_temp 的实体类
 * @author 刘镝
 */
@Entity(name = "repair_cost_detail_temp")
public class RepairCostDetailTemp extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public RepairCostDetailTemp clone() {
      RepairCostDetailTemp o = null;
      try {
          o = (RepairCostDetailTemp) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 维修费用id
   */
  @ID
  @Column(name = "repair_cost_id", comment = "维修费用id")
  private String repair_cost_id;

  /**
   * 维修结果ID
   */
  @Column(name = "repair_result_id", comment = "维修结果ID")
  private String repair_result_id;

  /**
   * 费用种类
   */
  @Column(name = "cost_type", comment = "费用种类")
  private String cost_type;
  /**
   * 费用种类
   */
  @Transient
  @Column(name = "cost_type_nm", comment = "费用种类")
  private String cost_type_nm;

  /**
   * 费用名称
   */
  @Column(name = "cost_detail", comment = "费用名称")
  private String cost_detail;

  /**
   * 规格
   */
  @Column(name = "spec", comment = "规格")
  private String spec;

  /**
   * 使用数量
   */
  @Column(name = "amount", comment = "使用数量")
  private String amount;

  /**
   * 单位
   */
  @Column(name = "unit", comment = "单位")
  private String unit;

  /**
   * 品牌
   */
  @Column(name = "brand", comment = "品牌")
  private String brand;

  /**
   * 单价
   */
  @Column(name = "unit_price", comment = "单价")
  private String unit_price;

  /**
   * 备注
   */
  @Column(name = "remark", comment = "备注")
  private String remark;

  /**
   * 首次创建userid
   */
  @Column(name = "create_id", comment = "首次创建userid")
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
   * 
   */
  @Column(name = "total_price", comment = "")
  private String total_price;

  public String getRepair_cost_id() {
       return repair_cost_id;
  }

  public void setRepair_cost_id(String repair_cost_id) {
       this.repair_cost_id = repair_cost_id;
  }

  public String getRepair_result_id() {
       return repair_result_id;
  }

  public void setRepair_result_id(String repair_result_id) {
       this.repair_result_id = repair_result_id;
  }

  public String getCost_type() {
       return cost_type;
  }

  public void setCost_type(String cost_type) {
       this.cost_type = cost_type;
  }

  public String getCost_detail() {
       return cost_detail;
  }

  public void setCost_detail(String cost_detail) {
       this.cost_detail = cost_detail;
  }

  public String getSpec() {
       return spec;
  }

  public void setSpec(String spec) {
       this.spec = spec;
  }

  public String getAmount() {
       return amount;
  }

  public void setAmount(String amount) {
       this.amount = amount;
  }

  public String getUnit() {
       return unit;
  }

  public void setUnit(String unit) {
       this.unit = unit;
  }

  public String getBrand() {
       return brand;
  }

  public void setBrand(String brand) {
       this.brand = brand;
  }

  public String getUnit_price() {
       return unit_price;
  }

  public void setUnit_price(String unit_price) {
       this.unit_price = unit_price;
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

  public String getTotal_price() {
       return total_price;
  }

  public void setTotal_price(String total_price) {
       this.total_price = total_price;
  }

public String getCost_type_nm() {
	return cost_type_nm;
}

public void setCost_type_nm(String cost_type_nm) {
	this.cost_type_nm = cost_type_nm;
}

}
