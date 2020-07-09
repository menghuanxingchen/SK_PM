package com.pm.persistence.repair.dto;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表Repair_Order_info 的实体类
 * @author 刘镝
 */
public class RepairOrderInfoDTO {

	 /**
	   * 
	   */
	  private String id;

	  /**
	   * 维修单编号
	   */
	  private String repair_order_num;

	  /**
	   * 维修内容
	   */
	  private String repair_detail;

	  /**
	   * 维修担当
	   */
	  private String repair_user_id;
	  /**
	   * 是否有费用名
	   */
	  private String repair_type_nm;
	  /**
	   * 是否有费用
	   */
	  private String repair_type;
	  /**
	   * 要求完成时间
	   */
	  private String require_time;
	  /**
	   * 要求完成时间
	   */
	  private String require_date;
	  /**
	   * 维修后总费用
	   */
	  private String after_repair_tatal_all;

	  /**
	   * 处理人
	   */
	  private String deal_user_id;
	  
	  /**
	   * 维修类型
	   */
	  private String repair_classify;

	  /**
	   * 维修类型
	   */
	  private String repair_classify_nm;

	  /**
	   * 审批日期
	   */
	  private String approval_date;

	  /**
	   * 
	   */
	  private String repair_place_nm;
	  /**
	   * 审批状态
	   */
	  private String approval_state;

	  /**
	   * 审批步骤
	   */
	  private String approval_step;

	  /**
	   * 备注
	   */
	  private String remark;

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
	   * 维修地点
	   */
	  private String repair_place;

	  /**
	   * 
	   */
	  private String flag;
	  /**
	   * 
	   */
	  private String pay_yn;
	  
	  /**
	   * 
	   */
	  private String approval_state_nm;
	  
	  /**
	   * 
	   */
	  private String deal_user_id_nm;
	  /**
	   * 
	   */
	  private String dept_code;
	  /**
	   * 
	   */
	  private String sub_dept_code;
	  /**
	   * 
	   */
	  private String dept_nm;
	  
	  /**
	   * 
	   */
	  private String create_id_nm;
	  
	  /**
	   * 
	   */
	  private String repair_user_id_nm;
	  
	  /**
	   * 
	   */
	  private String start_dt;
	  
	  /**
	   * 
	   */
	  private String end_dt;
	  
	  /**
	   * 
	   */
	  private String factory_manager_approval_date;
	  
	  /**
	   * 
	   */
	  private String orderItem;
	  
	  /**
	   * 
	   */
	  private String editYnFlag;
	  /**
	   * 
	   */
	  private String deleteYnFlag;
	  
	  /**
	   * 
	   */
	  private String tatal_all;

	  /**
	   * 
	   */
	  private String distribute_date;
	  /**
	   * 
	   */
	  private String repair_date;
	  /**
	   * 
	   */
	  private String factory_manager_confirm_date;
	  /**
	   * 
	   */
	  private String confirm_date;
	  /**
	   * 
	   */
	  private String account_date;
	  /**
	   * 
	   */
	  private String offer_date;
	  
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getApproval_state_nm() {
		return approval_state_nm;
	}

	public void setApproval_state_nm(String approval_state_nm) {
		this.approval_state_nm = approval_state_nm;
	}

	public String getDeal_user_id_nm() {
		return deal_user_id_nm;
	}

	public void setDeal_user_id_nm(String deal_user_id_nm) {
		this.deal_user_id_nm = deal_user_id_nm;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getCreate_id_nm() {
		return create_id_nm;
	}

	public void setCreate_id_nm(String create_id_nm) {
		this.create_id_nm = create_id_nm;
	}

	public String getRepair_user_id_nm() {
		return repair_user_id_nm;
	}

	public void setRepair_user_id_nm(String repair_user_id_nm) {
		this.repair_user_id_nm = repair_user_id_nm;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}

	public String getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public String getEditYnFlag() {
		return editYnFlag;
	}

	public void setEditYnFlag(String editYnFlag) {
		this.editYnFlag = editYnFlag;
	}

	public String getPay_yn() {
		return pay_yn;
	}

	public void setPay_yn(String pay_yn) {
		this.pay_yn = pay_yn;
	}

	public String getSub_dept_code() {
		return sub_dept_code;
	}

	public void setSub_dept_code(String sub_dept_code) {
		this.sub_dept_code = sub_dept_code;
	}

	public String getRepair_place_nm() {
		return repair_place_nm;
	}

	public void setRepair_place_nm(String repair_place_nm) {
		this.repair_place_nm = repair_place_nm;
	}

	public String getRequire_date() {
		return require_date;
	}

	public void setRequire_date(String require_date) {
		this.require_date = require_date;
	}

	public String getTatal_all() {
		return tatal_all;
	}

	public void setTatal_all(String tatal_all) {
		this.tatal_all = tatal_all;
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

	public String getRepair_type_nm() {
		return repair_type_nm;
	}

	public void setRepair_type_nm(String repair_type_nm) {
		this.repair_type_nm = repair_type_nm;
	}

	public String getFactory_manager_approval_date() {
		return factory_manager_approval_date;
	}

	public void setFactory_manager_approval_date(
			String factory_manager_approval_date) {
		this.factory_manager_approval_date = factory_manager_approval_date;
	}

	public String getDeleteYnFlag() {
		return deleteYnFlag;
	}

	public void setDeleteYnFlag(String deleteYnFlag) {
		this.deleteYnFlag = deleteYnFlag;
	}

	public String getAfter_repair_tatal_all() {
		return after_repair_tatal_all;
	}

	public void setAfter_repair_tatal_all(String after_repair_tatal_all) {
		this.after_repair_tatal_all = after_repair_tatal_all;
	}

	public String getRepair_classify() {
		return repair_classify;
	}

	public void setRepair_classify(String repair_classify) {
		this.repair_classify = repair_classify;
	}

	public String getRepair_classify_nm() {
		return repair_classify_nm;
	}

	public void setRepair_classify_nm(String repair_classify_nm) {
		this.repair_classify_nm = repair_classify_nm;
	}

	
	  
}
