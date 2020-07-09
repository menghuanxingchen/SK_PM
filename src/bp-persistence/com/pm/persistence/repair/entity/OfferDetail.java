package com.pm.persistence.repair.entity;

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
 * 表offer_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "offer_detail")
public class OfferDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 3019480109774740100L;

  public OfferDetail clone() {
      OfferDetail o = null;
      try {
          o = (OfferDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 报价详情id
   */
  @ID
  @Column(name = "offer_detail_id", comment = "报价详情id")
  private String offer_detail_id;

  /**
   * 报价ID
   */
  @Column(name = "offer_id", comment = "报价ID")
  private String offer_id;

  /**
   * 费用种类
   */
  @Column(name = "cost_type", comment = "费用种类")
  private String cost_type;

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
   * 修改时间
   */
  @Column(name = "total_price", comment = "合计")
  private String total_price;

  public String getOffer_detail_id() {
       return offer_detail_id;
  }

  public void setOffer_detail_id(String offer_detail_id) {
       this.offer_detail_id = offer_detail_id;
  }

  public String getOffer_id() {
       return offer_id;
  }

  public void setOffer_id(String offer_id) {
       this.offer_id = offer_id;
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

}
