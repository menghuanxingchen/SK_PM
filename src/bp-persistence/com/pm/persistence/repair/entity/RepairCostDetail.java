package com.pm.persistence.repair.entity;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_cost_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "repair_cost_detail")
public class RepairCostDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 535045266592458248L;

  public RepairCostDetail clone() {
      RepairCostDetail o = null;
      try {
          o = (RepairCostDetail) super.clone();
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
   * 规格名
   */
  @Transient
  @Column(name = "spec_nm", comment = "规格名")
  private String spec_nm;
  /**
   * 工具使用规格名
   */
  @Transient
  @Column(name = "tool_use_spec_nm", comment = "工具使用规格名")
  private String tool_use_spec_nm;
  /**
   * 人工费规格名
   */
  @Transient
  @Column(name = "person_expenses_spec_nm", comment = "人工费规格名")
  private String person_expenses_spec_nm;
  
  /**
   * 单价是否可见权限设置
   */
  @Transient
  @Column(name = "showMoney", comment = "单价是否可见权限设置")
  private String showMoney;

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
   * 合计
   */
  @Column(name = "total_price", comment = "合计")
  private String total_price;
  
  /**
   * 种类名
   */
  @Transient
  @Column(name = "cost_nm", comment = "种类名")
  private String cost_nm;
  
  /**
   * 
   * 
   */
  @Transient
  @Column(name = "", comment = "")
  private  List<SysCodeInfo> expensesTypeGrouplist;
 
  public String getRepair_cost_id() {
       return repair_cost_id;
  }

  public void setRepair_cost_id(String repair_cost_id) {
       this.repair_cost_id = repair_cost_id;
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

public String getRepair_result_id() {
	return repair_result_id;
}

public void setRepair_result_id(String repair_result_id) {
	this.repair_result_id = repair_result_id;
}

public String getTotal_price() {
	return total_price;
}

public void setTotal_price(String total_price) {
	this.total_price = total_price;
}

public List<SysCodeInfo> getExpensesTypeGrouplist() {
	return expensesTypeGrouplist;
}

public void setExpensesTypeGrouplist(List<SysCodeInfo> expensesTypeGrouplist) {
	this.expensesTypeGrouplist = expensesTypeGrouplist;
}

public String getCost_nm() {
	return cost_nm;
}

public void setCost_nm(String cost_nm) {
	this.cost_nm = cost_nm;
}

public String getSpec_nm() {
	return spec_nm;
}

public void setSpec_nm(String spec_nm) {
	this.spec_nm = spec_nm;
}

public String getTool_use_spec_nm() {
	return tool_use_spec_nm;
}

public void setTool_use_spec_nm(String tool_use_spec_nm) {
	this.tool_use_spec_nm = tool_use_spec_nm;
}

public String getShowMoney() {
	return showMoney;
}

public void setShowMoney(String showMoney) {
	this.showMoney = showMoney;
}

public String getPerson_expenses_spec_nm() {
	return person_expenses_spec_nm;
}

public void setPerson_expenses_spec_nm(String person_expenses_spec_nm) {
	this.person_expenses_spec_nm = person_expenses_spec_nm;
}

}
