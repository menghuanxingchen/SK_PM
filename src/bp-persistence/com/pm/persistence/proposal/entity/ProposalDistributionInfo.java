package com.pm.persistence.proposal.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表proposal_distribution_info 的实体类
 * @author 刘镝
 */
@Entity(name = "proposal_distribution_info")
public class ProposalDistributionInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public ProposalDistributionInfo clone() {
      ProposalDistributionInfo o = null;
      try {
          o = (ProposalDistributionInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 分配ID
   */
  @ID
  @Column(name = "proposal_distribution_id", comment = "分配ID")
  private String proposal_distribution_id;

  /**
   * 维修编号
   */
  @Column(name = "proposal_num", comment = "维修编号")
  private String proposal_num;

  /**
   * 要求结束时间
   */
  @Column(name = "require_date", comment = "要求结束时间")
  private String require_date;

  /**
   * 分配人
   */
  @Column(name = "user_id", comment = "分配人")
  private String user_id;

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

  public String getProposal_distribution_id() {
       return proposal_distribution_id;
  }

  public void setProposal_distribution_id(String proposal_distribution_id) {
       this.proposal_distribution_id = proposal_distribution_id;
  }

  public String getProposal_num() {
       return proposal_num;
  }

  public void setProposal_num(String proposal_num) {
       this.proposal_num = proposal_num;
  }

  public String getRequire_date() {
       return require_date;
  }

  public void setRequire_date(String require_date) {
       this.require_date = require_date;
  }

  public String getUser_id() {
       return user_id;
  }

  public void setUser_id(String user_id) {
       this.user_id = user_id;
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

}
