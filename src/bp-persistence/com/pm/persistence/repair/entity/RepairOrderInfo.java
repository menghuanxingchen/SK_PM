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
 * 表repair_order_info 的实体类
 * @author 刘镝
 */
@Entity(name = "repair_order_info")
public class RepairOrderInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 6226966977647821188L;

  public RepairOrderInfo clone() {
      RepairOrderInfo o = null;
      try {
          o = (RepairOrderInfo) super.clone();
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
   * 维修单编号
   */
  @Column(name = "repair_order_num", comment = "维修单编号")
  private String repair_order_num;

  /**
   * 维修内容
   */
  @Column(name = "repair_detail", comment = "维修内容")
  private String repair_detail;

  /**
   * 维修担当
   */
  @Column(name = "repair_user_id", comment = "维修担当")
  private String repair_user_id;

  //liudi add start
  /**
   * 维修担当名
   */
  @Transient
  @Column(name = "repair_user_nm", comment = "维修担当名")
  private String repair_user_nm;
  //liudi add end
  
  /**
   * 要求完成时间
   */
  @Column(name = "require_time", comment = "要求完成时间")
  private String require_time;

  /**
   * 处理人
   */
  @Column(name = "deal_user_id", comment = "处理人")
  private String deal_user_id;

  /**
   * 审批日期
   */
  @Column(name = "approval_date", comment = "审批日期")
  private String approval_date;

  /**
   * 审批状态
   */
  @Column(name = "approval_state", comment = "审批状态")
  private String approval_state;

  /**
   * 审批步骤
   */
  @Column(name = "approval_step", comment = "审批步骤")
  private String approval_step;

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
   * 维修地点
   */
  @Column(name = "repair_place", comment = "维修地点")
  private String repair_place;
  
  //liudi add start
  /**
   * 维修地点名称
   */
  @Transient
  @Column(name = "repair_place_nm", comment = "维修地点名称")
  private String repair_place_nm;
  /**
   * 维修后总费用
   */
  @Transient
  @Column(name = "after_repair_tatal_all", comment = "维修后总费用")
  private String after_repair_tatal_all;
  //liudi add end
  /**
   * 是否结算
   */
  @Column(name = "pay_yn", comment = "是否结算")
  private String pay_yn;
  /**
   * 是否有费用
   */
  @Column(name = "repair_type", comment = "是否有费用")
  private String repair_type;
  /**
   *
   */
  @Column(name = "factory_manager_approval_date", comment = "")
  private String factory_manager_approval_date;
  /**
   * 
   */
  @Column(name = "distribute_date", comment = "")
  private String distribute_date;
  /**
   * 
   */
  @Column(name = "repair_date", comment = "")
  private String repair_date;
  /**
   * 
   */
  @Column(name = "factory_manager_confirm_date", comment = "")
  private String factory_manager_confirm_date;
  /**
   * 
   */
  @Column(name = "confirm_date", comment = "")
  private String confirm_date;
  /**
   * 
   */
  @Column(name = "account_date", comment = "")
  private String account_date;
  /**
   * 
   */
  @Column(name = "offer_date", comment = "")
  private String offer_date;
  @Transient
  @Column(name = "dept_nm", comment = "")
  private String dept_nm;
  
  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getRepair_order_num() {
       return repair_order_num;
  }

  public void setRepair_order_num(String repair_order_num) {
       this.repair_order_num = repair_order_num;
  }

  public String getRepair_detail() {
       return repair_detail;
  }

  public void setRepair_detail(String repair_detail) {
       this.repair_detail = repair_detail;
  }

  public String getRepair_user_id() {
       return repair_user_id;
  }

  public void setRepair_user_id(String repair_user_id) {
       this.repair_user_id = repair_user_id;
  }

  public String getRequire_time() {
       return require_time;
  }

  public void setRequire_time(String require_time) {
       this.require_time = require_time;
  }

  public String getDeal_user_id() {
       return deal_user_id;
  }

  public void setDeal_user_id(String deal_user_id) {
       this.deal_user_id = deal_user_id;
  }

  public String getApproval_date() {
       return approval_date;
  }

  public void setApproval_date(String approval_date) {
       this.approval_date = approval_date;
  }

  public String getApproval_state() {
       return approval_state;
  }

  public void setApproval_state(String approval_state) {
       this.approval_state = approval_state;
  }

  public String getApproval_step() {
       return approval_step;
  }

  public void setApproval_step(String approval_step) {
       this.approval_step = approval_step;
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

public String getRepair_place() {
	return repair_place;
}

public void setRepair_place(String repair_place) {
	this.repair_place = repair_place;
}

public String getPay_yn() {
	return pay_yn;
}

public void setPay_yn(String pay_yn) {
	this.pay_yn = pay_yn;
}

public String getRepair_type() {
	return repair_type;
}

public void setRepair_type(String repair_type) {
	this.repair_type = repair_type;
}

public String getDistribute_date() {
	return distribute_date;
}

public void setDistribute_date(String distribute_date) {
	this.distribute_date = distribute_date;
}

public String getRepair_date() {
	return repair_date;
}

public void setRepair_date(String repair_date) {
	this.repair_date = repair_date;
}

public String getFactory_manager_confirm_date() {
	return factory_manager_confirm_date;
}

public void setFactory_manager_confirm_date(String factory_manager_confirm_date) {
	this.factory_manager_confirm_date = factory_manager_confirm_date;
}

public String getConfirm_date() {
	return confirm_date;
}

public void setConfirm_date(String confirm_date) {
	this.confirm_date = confirm_date;
}

public String getAccount_date() {
	return account_date;
}

public void setAccount_date(String account_date) {
	this.account_date = account_date;
}

public String getOffer_date() {
	return offer_date;
}

public void setOffer_date(String offer_date) {
	this.offer_date = offer_date;
}

public String getRepair_user_nm() {
	return repair_user_nm;
}

public void setRepair_user_nm(String repair_user_nm) {
	this.repair_user_nm = repair_user_nm;
}

public String getDept_nm() {
	return dept_nm;
}

public void setDept_nm(String dept_nm) {
	this.dept_nm = dept_nm;
}

public String getRepair_place_nm() {
	return repair_place_nm;
}

public void setRepair_place_nm(String repair_place_nm) {
	this.repair_place_nm = repair_place_nm;
}

public String getFactory_manager_approval_date() {
	return factory_manager_approval_date;
}

public void setFactory_manager_approval_date(
		String factory_manager_approval_date) {
	this.factory_manager_approval_date = factory_manager_approval_date;
}

public String getAfter_repair_tatal_all() {
	return after_repair_tatal_all;
}

public void setAfter_repair_tatal_all(String after_repair_tatal_all) {
	this.after_repair_tatal_all = after_repair_tatal_all;
}
}
