package com.pm.persistence.proposal.dto;

import com.repast.core.annotation.Entity;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company: 表proposal_info 的实体类
 * 
 * @author 刘镝
 */
@Entity(name = "proposal_info")
public class ProposalInfoDTO{
	
	private String number;

	private String id;

	
	private String proposal_num;

	
	private String proposal_detail;

	
	private String deal_user_id;
	
	private String orderItem;
	
	private String userFlag;
	
	private String require_date;

	private String approval_date;

	
	private String approval_state;

	
	private String approval_step;

	
	private String create_id;

	
	
	private String create_time;

	
	private String update_id;

	
	private String update_time;

	
	private String proposal_type;

	
	private String distribute_date;

	
	private String deal_date;

	
	private String confirm_date;


	private String factory_manager_approval_date;

	
	private String anticipate_result;

	
	private String resource_input;

	
	private String comparative_conclusion;

	
	private String operate_user;
	
	private String flag;
	
	private String editYnFlag;
	
	private String deleteYnFlag;
	
	private String approval_state_nm;
	
	private String deal_user_id_nm;
	
	private String create_id_nm;
	
	private String operate_user_nm;
	
	private String dept_nm;
	
	private String proposal_type_nm;
	
	private String dept_code;
	
	private String operate_user_dept_code;
	
	private String sub_dept_code;
	
	private String operate_user_sub_dept_code;
	
	private String start_dt;
	
	private String end_dt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProposal_num() {
		return proposal_num;
	}

	public void setProposal_num(String proposal_num) {
		this.proposal_num = proposal_num;
	}

	public String getProposal_detail() {
		return proposal_detail;
	}

	public void setProposal_detail(String proposal_detail) {
		this.proposal_detail = proposal_detail;
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

	public String getProposal_type() {
		return proposal_type;
	}

	public void setProposal_type(String proposal_type) {
		this.proposal_type = proposal_type;
	}

	public String getDistribute_date() {
		return distribute_date;
	}

	public void setDistribute_date(String distribute_date) {
		this.distribute_date = distribute_date;
	}

	public String getDeal_date() {
		return deal_date;
	}

	public void setDeal_date(String deal_date) {
		this.deal_date = deal_date;
	}

	public String getConfirm_date() {
		return confirm_date;
	}

	public void setConfirm_date(String confirm_date) {
		this.confirm_date = confirm_date;
	}

	public String getFactory_manager_approval_date() {
		return factory_manager_approval_date;
	}

	public void setFactory_manager_approval_date(
			String factory_manager_approval_date) {
		this.factory_manager_approval_date = factory_manager_approval_date;
	}

	public String getAnticipate_result() {
		return anticipate_result;
	}

	public void setAnticipate_result(String anticipate_result) {
		this.anticipate_result = anticipate_result;
	}

	public String getResource_input() {
		return resource_input;
	}

	public void setResource_input(String resource_input) {
		this.resource_input = resource_input;
	}

	public String getComparative_conclusion() {
		return comparative_conclusion;
	}

	public void setComparative_conclusion(String comparative_conclusion) {
		this.comparative_conclusion = comparative_conclusion;
	}

	public String getOperate_user() {
		return operate_user;
	}

	public void setOperate_user(String operate_user) {
		this.operate_user = operate_user;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEditYnFlag() {
		return editYnFlag;
	}

	public void setEditYnFlag(String editYnFlag) {
		this.editYnFlag = editYnFlag;
	}

	public String getDeleteYnFlag() {
		return deleteYnFlag;
	}

	public void setDeleteYnFlag(String deleteYnFlag) {
		this.deleteYnFlag = deleteYnFlag;
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

	public String getCreate_id_nm() {
		return create_id_nm;
	}

	public void setCreate_id_nm(String create_id_nm) {
		this.create_id_nm = create_id_nm;
	}

	public String getOperate_user_nm() {
		return operate_user_nm;
	}

	public void setOperate_user_nm(String operate_user_nm) {
		this.operate_user_nm = operate_user_nm;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getProposal_type_nm() {
		return proposal_type_nm;
	}

	public void setProposal_type_nm(String proposal_type_nm) {
		this.proposal_type_nm = proposal_type_nm;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getOperate_user_dept_code() {
		return operate_user_dept_code;
	}

	public void setOperate_user_dept_code(String operate_user_dept_code) {
		this.operate_user_dept_code = operate_user_dept_code;
	}

	public String getSub_dept_code() {
		return sub_dept_code;
	}

	public void setSub_dept_code(String sub_dept_code) {
		this.sub_dept_code = sub_dept_code;
	}

	public String getOperate_user_sub_dept_code() {
		return operate_user_sub_dept_code;
	}

	public void setOperate_user_sub_dept_code(String operate_user_sub_dept_code) {
		this.operate_user_sub_dept_code = operate_user_sub_dept_code;
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

	public String getRequire_date() {
		return require_date;
	}

	public void setRequire_date(String require_date) {
		this.require_date = require_date;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
