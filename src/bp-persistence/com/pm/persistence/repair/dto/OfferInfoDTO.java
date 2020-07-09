package com.pm.persistence.repair.dto;

import java.util.List;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表offer_info 的实体类
 * @author 刘镝
 */
public class OfferInfoDTO{

  /**
   * 报价ID
   */
  private String offer_id;

  /**
   * 维修编号
   */
  private String repair_order_num;

  /**
   * 供应商
   */
  private String supplier_code;
  /**
   * 一次性费用
   */
  private String one_charge;
  /**
   * 运费对应的规格名称
   */
  private String spec_nm;
  /**
   * 使用工具对应的规格名称
   */
  private String tool_use_spec_nm;
  /**
   * 人工费对应的规格名称
   */
  private String person_expenses_spec_nm;

  /**
   * 人工单价
   */
  private String user_price;

  /**
   * 预估工时
   */
  private String estimate_time;

  /**
   * 税金
   */
  private String tax;

  /**
   * 备注
   */
  private String remark;
  
  /**
   * 报价详情id
   */
  private String offer_detail_id;

  /**
   * 费用种类
   */
  private String cost_type;
  /**
   * 费用种类名
   */
  private String cost_nm;
  /**
   * 费用名称
   */
  private String cost_detail;

  /**
   * 规格
   */
  private String spec;

  /**
   * 使用数量
   */
  private String amount;

  /**
   * 单位
   */
  private String unit;

  /**
   * 品牌
   */
  private String brand;

  /**
   * 单价
   */
  private String unit_price;

  /**
   * 首次创建userid
   */
  private String create_id;

  /**
   * 首次创建时间
   */
  private String create_time;

  /**
   * 修改userid
   */
  private String update_id;

  /**
   * 修改时间
   */
  private String update_time;
  
  /**
   * 单价是否可见权限设置
   */
  private String showMoney;
  
  /**
   * 
   */
  private List<SysCodeInfo> expensesTypeGrouplist;
  
  /**
   * 
   */
  private List<SysCodeInfo> offerUnitGrouplist;
  
  /**
   * 
   */
  private List<SysCodeInfo> personExpensesGrouplist;
  /**
   * 
   */
  private List<SysCodeInfo> transportSpecGrouplist;
  /**
   * 
   */
  private List<SysCodeInfo> toolUseSpecGrouplist;
  
  /**
   * 
   */
  private String total_price;

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

public String getOffer_detail_id() {
	return offer_detail_id;
}

public void setOffer_detail_id(String offer_detail_id) {
	this.offer_detail_id = offer_detail_id;
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

public List<SysCodeInfo> getExpensesTypeGrouplist() {
	return expensesTypeGrouplist;
}

public void setExpensesTypeGrouplist(List<SysCodeInfo> expensesTypeGrouplist) {
	this.expensesTypeGrouplist = expensesTypeGrouplist;
}

public List<SysCodeInfo> getOfferUnitGrouplist() {
	return offerUnitGrouplist;
}

public void setOfferUnitGrouplist(List<SysCodeInfo> offerUnitGrouplist) {
	this.offerUnitGrouplist = offerUnitGrouplist;
}

public String getTotal_price() {
	return total_price;
}

public void setTotal_price(String total_price) {
	this.total_price = total_price;
}

public String getCost_nm() {
	return cost_nm;
}

public void setCost_nm(String cost_nm) {
	this.cost_nm = cost_nm;
}

public String getOne_charge() {
	return one_charge;
}

public void setOne_charge(String one_charge) {
	this.one_charge = one_charge;
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

public List<SysCodeInfo> getPersonExpensesGrouplist() {
	return personExpensesGrouplist;
}

public void setPersonExpensesGrouplist(List<SysCodeInfo> personExpensesGrouplist) {
	this.personExpensesGrouplist = personExpensesGrouplist;
}

public List<SysCodeInfo> getTransportSpecGrouplist() {
	return transportSpecGrouplist;
}

public void setTransportSpecGrouplist(List<SysCodeInfo> transportSpecGrouplist) {
	this.transportSpecGrouplist = transportSpecGrouplist;
}

public List<SysCodeInfo> getToolUseSpecGrouplist() {
	return toolUseSpecGrouplist;
}

public void setToolUseSpecGrouplist(List<SysCodeInfo> toolUseSpecGrouplist) {
	this.toolUseSpecGrouplist = toolUseSpecGrouplist;
}

}
