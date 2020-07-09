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
 * 表offer_info 的实体类
 * @author 刘镝
 */
@Entity(name = "offer_info")
public class OfferInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 140176415760723591L;

  public OfferInfo clone() {
      OfferInfo o = null;
      try {
          o = (OfferInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 报价ID
   */
  @ID
  @Column(name = "offer_id", comment = "报价ID")
  private String offer_id;

  /**
   * 维修编号
   */
  @Column(name = "repair_order_num", comment = "维修编号")
  private String repair_order_num;

  /**
   * 供应商
   */
  @Column(name = "supplier_code", comment = "供应商")
  private String supplier_code;
  
  /**
   * 供应商名
   */
  @Transient
  @Column(name = "supplier_nm", comment = "供应商名")
  private String supplier_nm;

  /**
   * 人工单价
   */
  @Column(name = "user_price", comment = "人工单价")
  private String user_price;

  /**
   * 预估工时
   */
  @Column(name = "estimate_time", comment = "预估工时")
  private String estimate_time;

  /**
   * 税金
   */
  @Column(name = "tax", comment = "税金")
  private String tax;

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
  @Column(name = "tatal_all", comment = "")
  private String tatal_all;
  /**
   * 
   */
  @Column(name = "one_charge", comment = "")
  private String one_charge;

  public String getOffer_id() {
       return offer_id;
  }

  public void setOffer_id(String offer_id) {
       this.offer_id = offer_id;
  }

  public String getRepair_order_num() {
       return repair_order_num;
  }

  public void setRepair_order_num(String repair_order_num) {
       this.repair_order_num = repair_order_num;
  }

  public String getSupplier_code() {
       return supplier_code;
  }

  public void setSupplier_code(String supplier_code) {
       this.supplier_code = supplier_code;
  }

  public String getUser_price() {
       return user_price;
  }

  public void setUser_price(String user_price) {
       this.user_price = user_price;
  }

  public String getEstimate_time() {
       return estimate_time;
  }

  public void setEstimate_time(String estimate_time) {
       this.estimate_time = estimate_time;
  }

  public String getTax() {
       return tax;
  }

  public void setTax(String tax) {
       this.tax = tax;
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

public String getTatal_all() {
	return tatal_all;
}

public void setTatal_all(String tatal_all) {
	this.tatal_all = tatal_all;
}

public String getSupplier_nm() {
	return supplier_nm;
}

public void setSupplier_nm(String supplier_nm) {
	this.supplier_nm = supplier_nm;
}

public String getOne_charge() {
	return one_charge;
}

public void setOne_charge(String one_charge) {
	this.one_charge = one_charge;
}

}
