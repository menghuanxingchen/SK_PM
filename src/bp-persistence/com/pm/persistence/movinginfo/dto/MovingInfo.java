package com.pm.persistence.movinginfo.dto;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_info 的实体类
 * @author 刘镝
 */
public class MovingInfo extends BaseEntity implements Serializable, Cloneable {

   private static final long serialVersionUID = 1L;

   /**
    * 搬入搬出No
    */
   private String movingNo;
   /**
    * 现审批人
    */
   private String deal_user_name;
   /**
    * 现审批状态
    */
   private String approval_state;
   /**
    * 现审批状态
    */
   private String approval_state_name;
   /**
    * 现审批人
    */
   private String deal_user_id;
   
   /**
    * 搬入搬出类型
    */
   private String movingType;
   
   /**
    * 搬入搬出类型
    */
   private String applyType;
   
   /**
    * 搬入搬出人
    */
   private String movingPerson;
  /**
   * 车牌
   */
  private String movingCarno;

  /**
   * 开始
   */
  private String movingStdate;
  
  /**
   * 结束
   */
  private String movingEddate;
  /**
   * 公司
   */
  private String movingCompany;
  /**
   * 目的
   */
  private String movingGoalType;
  /**
   * 目的内容
   */
  private String movingGoalContent;
  

  /**
   * 是否需要搬出
   */
  private String moutYN;
  
  /**
   * 电话
   */
  private String movingPhone;
  /**
   * sk联系人
   */
  private String skContactsNo;
  
  /**
   * 日期
   */
  private String movingTime;
  
  /**
   * 确认图片
   */
  private String picQueRenInfo;
  
  /**
   * 申请人
   */
  private String skApplyName;
  /**
   * 是否
   */
  private String cntApprove;
  /**
   * create_id
   */
  private String create_id;
  
  public String getMovingNo() {
	return movingNo;
	}
	
	public void setMovingNo(String movingNo) {
		this.movingNo = movingNo;
	}
	public String getDeal_user_name() {
	  return deal_user_name;
	}
	
	public void setDeal_user_name(String deal_user_name) {
		this.deal_user_name = deal_user_name;
	}
	public String getDeal_user_id() {
		  return deal_user_id;
	}
	
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
	}
	public String getApproval_state() {
		  return approval_state;
	}
	
	public void setApproval_state(String approval_state) {
		this.approval_state = approval_state;
	}
	public String getApproval_state_name() {
		  return approval_state_name;
	}
	
	public void setApproval_state_name(String approval_state_name) {
		this.approval_state_name = approval_state_name;
	}
	
    public String getMovingType() {
    	return movingType;
	}
	
	public void setMovingType(String movingType) {
		this.movingType = movingType;
	}
	
	public String getApplyType() {
		return applyType;
	}
	
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
		
	public String getMovingPerson() {
		return movingPerson;
	}
	
	public void setMovingPerson(String movingPerson) {
		this.movingPerson = movingPerson;
	}
	
	public String getMovingCarno() {
		return movingCarno;
	}
	
	public void setMovingCarno(String movingCarno) {
		this.movingCarno = movingCarno;
	}
	
	public String getMovingStdate() {
		return movingStdate;
	}
	
	public void setMovingStdate(String movingStdate) {
		this.movingStdate = movingStdate;
	}
	
	public String getMovingEddate() {
		return movingEddate;
	}
	
	public void setMovingEddate(String movingEddate) {
		this.movingEddate = movingEddate;
	}
	
	public String getMovingCompany() {
		return movingCompany;
	}
	
	public void setMovingCompany(String movingCompany) {
		this.movingCompany = movingCompany;
	}
	
	public String getMovingGoalType() {
		return movingGoalType;
	}
	
	public void setMovingGoalType(String movingGoalType) {
		this.movingGoalType = movingGoalType;
	}
	
	public String getMovingGoalContent() {
		return movingGoalContent;
	}
	
	public void setMovingGoalContent(String movingGoalContent) {
		this.movingGoalContent = movingGoalContent;
	}
	
	public String getMovingPhone() {
		return movingPhone;
	}
	
	public void setMovingPhone(String movingPhone) {
		this.movingPhone = movingPhone;
	}
   public String getMoutYN() {
       return moutYN;
   }

   public void setMoutYN(String moutYN) {
       this.moutYN = moutYN;
   }
   public String getMovingTime() {
       return movingTime;
   }

   public void setMovingTime(String movingTime) {
       this.movingTime = movingTime;
   }
   public String getSkContactsNo() {
       return skContactsNo;
   }

   public void setSkContactsNo(String skContactsNo) {
       this.skContactsNo = skContactsNo;
   }
   
   public String getPicQueRenInfo() {
       return picQueRenInfo;
   }

   public void setPicQueRenInfo(String picQueRenInfo) {
       this.picQueRenInfo = picQueRenInfo;
   }
   
   public String getSkApplyName() {
       return skApplyName;
   }

   public void setSkApplyName(String skApplyName) {
       this.skApplyName = skApplyName;
   }
   public String getCntApprove() {
       return cntApprove;
   }

   public void setCntApprove(String cntApprove) {
       this.cntApprove = cntApprove;
   }
   public String getCreate_id() {
       return create_id;
   }

   public void setCreate_id(String create_id) {
       this.create_id = create_id;
   }
}
