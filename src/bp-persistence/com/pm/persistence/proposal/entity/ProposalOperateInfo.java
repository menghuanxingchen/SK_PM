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
 * 表proposal_operate_info 的实体类
 * @author 刘镝
 */
@Entity(name = "proposal_operate_info")
public class ProposalOperateInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public ProposalOperateInfo clone() {
      ProposalOperateInfo o = null;
      try {
          o = (ProposalOperateInfo) super.clone();
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
   * 
   */
  @Column(name = "proposal_num", comment = "")
  private String proposal_num;

  /**
   * 
   */
  @Column(name = "operate_detail", comment = "")
  private String operate_detail;

  /**
   * 
   */
  @Column(name = "operate_expense", comment = "")
  private String operate_expense;

  /**
   * 
   */
  @Column(name = "improvement_effect", comment = "")
  private String improvement_effect;

  /**
   * 
   */
  @Column(name = "director_opinion", comment = "")
  private String director_opinion;

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

  public String getOperate_detail() {
       return operate_detail;
  }

  public void setOperate_detail(String operate_detail) {
       this.operate_detail = operate_detail;
  }

  public String getOperate_expense() {
       return operate_expense;
  }

  public void setOperate_expense(String operate_expense) {
       this.operate_expense = operate_expense;
  }

  public String getImprovement_effect() {
       return improvement_effect;
  }

  public void setImprovement_effect(String improvement_effect) {
       this.improvement_effect = improvement_effect;
  }

  public String getDirector_opinion() {
       return director_opinion;
  }

  public void setDirector_opinion(String director_opinion) {
       this.director_opinion = director_opinion;
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
