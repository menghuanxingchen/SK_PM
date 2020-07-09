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
 * 表repair_result_info 的实体类
 * @author 刘镝
 */
@Entity(name = "repair_result_info")
public class RepairResultInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 2637929320279342055L;

  public RepairResultInfo clone() {
      RepairResultInfo o = null;
      try {
          o = (RepairResultInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 维修结果ID
   */
  @ID
  @Column(name = "repair_result_id", comment = "维修结果ID")
  private String repair_result_id;

  /**
   * 维修编号
   */
  @Column(name = "repair_order_num", comment = "维修编号")
  private String repair_order_num;

  /**
   * 人工单价
   */
  @Column(name = "unit_price", comment = "人工单价")
  private String unit_price;

  /**
   * 工时
   */
  @Column(name = "task_time", comment = "工时")
  private String task_time;

  /**
   * 一次性费用
   */
  @Column(name = "one_charge", comment = "一次性费用")
  private String one_charge;

  /**
   * 完成状态
   */
  @Column(name = "finish_state", comment = "完成状态")
  private String finish_state;

  /**
   * 完成实际日期
   */
  @Column(name = "finish_date", comment = "完成实际日期")
  private String finish_date;

  /**
   * 备注
   */
  @Column(name = "remark", comment = "备注")
  private String remark;

  /**
   * 维修内容
   */
  @Column(name = "repair_detail", comment = "维修内容")
  private String repair_detail;

  /**
   * 要求结束时间
   */
  @Column(name = "require_date", comment = "要求结束时间")
  private String require_date;

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
   * 维修后总价钱
   */
  @Column(name = "tatal_all", comment = "维修后总价钱")
  private String tatal_all;
  /**
   * 税金
   */
  @Column(name = "tax", comment = "税金")
  private String tax;
  
  /**
   * 单价是否可见权限设置
   */
  @Transient
  @Column(name = "showMoney", comment = "单价是否可见权限设置")
  private String showMoney;

  public String getRepair_result_id() {
       return repair_result_id;
  }

  public void setRepair_result_id(String repair_result_id) {
       this.repair_result_id = repair_result_id;
  }

  public String getRepair_order_num() {
       return repair_order_num;
  }

  public void setRepair_order_num(String repair_order_num) {
       this.repair_order_num = repair_order_num;
  }

  public String getUnit_price() {
       return unit_price;
  }

  public void setUnit_price(String unit_price) {
       this.unit_price = unit_price;
  }

  public String getTask_time() {
       return task_time;
  }

  public void setTask_time(String task_time) {
       this.task_time = task_time;
  }

  public String getOne_charge() {
       return one_charge;
  }

  public void setOne_charge(String one_charge) {
       this.one_charge = one_charge;
  }

  public String getFinish_state() {
       return finish_state;
  }

  public void setFinish_state(String finish_state) {
       this.finish_state = finish_state;
  }

  public String getFinish_date() {
       return finish_date;
  }

  public void setFinish_date(String finish_date) {
       this.finish_date = finish_date;
  }

  public String getRemark() {
       return remark;
  }

  public void setRemark(String remark) {
       this.remark = remark;
  }

  public String getRepair_detail() {
       return repair_detail;
  }

  public void setRepair_detail(String repair_detail) {
       this.repair_detail = repair_detail;
  }

  public String getRequire_date() {
       return require_date;
  }

  public void setRequire_date(String require_date) {
       this.require_date = require_date;
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

public String getShowMoney() {
	return showMoney;
}

public void setShowMoney(String showMoney) {
	this.showMoney = showMoney;
}

public String getTatal_all() {
	return tatal_all;
}

public void setTatal_all(String tatal_all) {
	this.tatal_all = tatal_all;
}

public String getTax() {
	return tax;
}

public void setTax(String tax) {
	this.tax = tax;
}

}
