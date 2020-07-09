package com.pm.persistence.proposal.entity;

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
 * 表proposal_approve_info 的实体类
 * @author 刘镝
 */
@Entity(name = "proposal_approve_info")
public class ProposalApproveInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public ProposalApproveInfo clone() {
      ProposalApproveInfo o = null;
      try {
          o = (ProposalApproveInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 审批id
   */
  @ID
  @Column(name = "approve_id", comment = "审批id")
  private String approve_id;

  /**
   * 审批人
   */
  @Column(name = "approve_user_id", comment = "审批人")
  private String approve_user_id;
  
  /**
   * 审批人
   */
  @Transient
  @Column(name = "approve_user_nm", comment = "审批人")
  private String approve_user_nm;

  /**
   * 审批日期
   */
  @Column(name = "approve_date", comment = "审批日期")
  private String approve_date;

  /**
   * 审批与否
   */
  @Column(name = "approve_yn", comment = "审批与否")
  private String approve_yn;

  /**
   * 审批状态
   */
  @Column(name = "approval_state", comment = "审批状态")
  private String approval_state;

  /**
   * 审批步骤
   */
  @Column(name = "approve_step", comment = "审批步骤")
  private String approve_step;

  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
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
  @Column(name = "proposal_num", comment = "")
  private String proposal_num;
  
  /**
   * 
   */
  @Transient
  @Column(name = "approval_nm", comment = "")
  private String approval_nm;

  public String getApprove_id() {
       return approve_id;
  }

  public void setApprove_id(String approve_id) {
       this.approve_id = approve_id;
  }

  public String getApprove_user_id() {
       return approve_user_id;
  }

  public void setApprove_user_id(String approve_user_id) {
       this.approve_user_id = approve_user_id;
  }

  public String getApprove_date() {
       return approve_date;
  }

  public void setApprove_date(String approve_date) {
       this.approve_date = approve_date;
  }

  public String getApprove_yn() {
       return approve_yn;
  }

  public void setApprove_yn(String approve_yn) {
       this.approve_yn = approve_yn;
  }

  public String getApproval_state() {
       return approval_state;
  }

  public void setApproval_state(String approval_state) {
       this.approval_state = approval_state;
  }

  public String getApprove_step() {
       return approve_step;
  }

  public void setApprove_step(String approve_step) {
       this.approve_step = approve_step;
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

  public String getProposal_num() {
       return proposal_num;
  }

  public void setProposal_num(String proposal_num) {
       this.proposal_num = proposal_num;
  }

public String getApproval_nm() {
	return approval_nm;
}

public void setApproval_nm(String approval_nm) {
	this.approval_nm = approval_nm;
}

public String getApprove_user_nm() {
	return approve_user_nm;
}

public void setApprove_user_nm(String approve_user_nm) {
	this.approve_user_nm = approve_user_nm;
}

}
