package com.pm.persistence.proposal.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company: 表proposal_info 的实体类
 * 
 * @author 刘镝
 */
@Entity(name = "proposal_info")
public class ProposalInfo extends BaseEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = 3019480109774740100L;

	public ProposalInfo clone() {
		ProposalInfo o = null;
		try {
			o = (ProposalInfo) super.clone();
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
	@Column(name = "proposal_num", comment = "维修单编号")
	private String proposal_num;

	/**
	 * 维修内容
	 */
	@Column(name = "proposal_detail", comment = "维修内容")
	private String proposal_detail;

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
	@Column(name = "proposal_type", comment = "")
	private String proposal_type;

	/**
   * 
   */
	@Column(name = "distribute_date", comment = "")
	private String distribute_date;

	/**
   * 
   */
	@Column(name = "deal_date", comment = "")
	private String deal_date;

	/**
   * 
   */
	@Column(name = "confirm_date", comment = "")
	private String confirm_date;

	/**
   * 
   */
	@Column(name = "factory_manager_approval_date", comment = "")
	private String factory_manager_approval_date;

	/**
   * 
   */
	@Column(name = "anticipate_result", comment = "")
	private String anticipate_result;

	/**
   * 
   */
	@Column(name = "resource_input", comment = "")
	private String resource_input;

	/**
   * 
   */
	@Column(name = "comparative_conclusion", comment = "")
	private String comparative_conclusion;

	/**
   * 
   */
	@Column(name = "operate_user", comment = "")
	private String operate_user;
	/**
	 * 内容分类  
	*/
    @Transient
	@Column(name = "proposal_type_nm", comment = "")
	private String proposal_type_nm;
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

	public String getProposal_type_nm() {
		return proposal_type_nm;
	}

	public void setProposal_type_nm(String proposal_type_nm) {
		this.proposal_type_nm = proposal_type_nm;
	}
	

}
