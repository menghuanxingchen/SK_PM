package com.pm.persistence.movinginfo.entity;

import java.io.Serializable;
import java.util.Date;

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
 * 表moving_in_info 的实体类
 * @author 刘镝
 */
@Entity(name = "moving_in_info")
public class MovingInInfo extends BaseEntity implements Serializable, Cloneable {

  public MovingInInfo clone() {
      MovingInInfo o = null;
      try {
          o = (MovingInInfo) super.clone();
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
   * 搬入证编号
   */
  @Column(name = "minNo", comment = "搬入证编号")
  private String minNo;

  /**
   * 搬入人姓名
   */
  @Column(name = "minName", comment = "搬入人姓名")
  private String minName;

  /**
   * 搬入人公司名称
   */
  @Column(name = "minCompany", comment = "搬入人公司名称")
  private String minCompany;

  /**
   * 车牌号
   */
  @Column(name = "minCarno", comment = "车牌号")
  private String minCarno;

  /**
   * 车牌号
   */
  @Transient
  @Column(name = "movingCarno", comment = "车牌号")
  private String movingCarno;
  
  /**
   * 电话
   */
  @Column(name = "minPhone", comment = "电话")
  private String minPhone;

  /**
   * sk联系人编号
   */
  @Column(name = "skContactsNo", comment = "sk联系人编号")
  private String skContactsNo;

  /**
   * sk联系人姓名
   */
  @Column(name = "skContactsName", comment = "sk联系人姓名")
  private String skContactsName;

  /**
   * 搬入时间
   */
  @Column(name = "minTime", comment = "搬入时间")
  private String minTime;

  /**
   * 目的类别
   */
  @Column(name = "minGoalType", comment = "目的类别")
  private String minGoalType;

  /**
   * 搬入目的描述
   */
  @Column(name = "minGoalContent", comment = "搬入目的描述")
  private String minGoalContent;

  /**
   * 是否需要搬出
   */
  @Column(name = "moutYN", comment = "是否需要搬出")
  private String moutYN;

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
  @Column(name = "user_yn", comment = "")
  private String user_yn;

  /**
   * 审批状态
   */
  @Column(name = "approval_state", comment = "审批状态")
  private String approval_state;
  
  /**
   * 审批线
   */
  @Column(name = "approval_step", comment = "审批线")
  private String approval_step;
  /**
   * 处理人
   */
  @Column(name = "deal_user_id", comment = "处理人")
  private String deal_user_id;
  
  /**
   * sk联系人审批时间
   */
  @Column(name = "apply_date", comment = "sk联系人审批时间")
  private String apply_date;
  /**
   * 审批世间
   */
  @Column(name = "approval_date", comment = "审批世间")
  private String approval_date;
  
  /**
   * 确认日期
   */
  @Column(name = "confirm_date", comment = "确认日期")
  private String confirm_date;
  
  /**
   * 厂长审批日期
   */
  @Column(name = "factory_manager_approval_date", comment = "厂长审批日期")
  private String factory_manager_approval_date;
  
  /**
   * 驳回日期
   */
  @Column(name = "reject_date", comment = "驳回日期")
  private String reject_date;
  
  /**
   * 
   */
  @Transient
  @Column(name = "json_info", comment = "")
  private String json_info;
  /**
   * 
   */

  @Column(name = "picInfo", comment = "")
  private String picInfo;
  
  
  /**
   * 
   */
  @Transient
  @Column(name = "deal_user_name", comment = "")
  private String deal_user_name;
  
  /**
   * 
   */
  @Transient
  @Column(name = "approval_state_name", comment = "")
  private String approval_state_name;
  
  
  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getMinNo() {
       return minNo;
  }

  public void setMinNo(String minNo) {
       this.minNo = minNo;
  }

  public String getMinName() {
       return minName;
  }

  public void setMinName(String minName) {
       this.minName = minName;
  }

  public String getMinCompany() {
       return minCompany;
  }

  public void setMinCompany(String minCompany) {
       this.minCompany = minCompany;
  }

  public String getMinCarno() {
       return minCarno;
  }

  public void setMinCarno(String minCarno) {
       this.minCarno = minCarno;
  }

  public String getMovingCarno() {
      return movingCarno;
 }

 public void setMovingCarno(String movingCarno) {
      this.movingCarno = movingCarno;
 }
 
  public String getMinPhone() {
       return minPhone;
  }

  public void setMinPhone(String minPhone) {
       this.minPhone = minPhone;
  }

  public String getSkContactsNo() {
       return skContactsNo;
  }

  public void setSkContactsNo(String skContactsNo) {
       this.skContactsNo = skContactsNo;
  }

  public String getSkContactsName() {
       return skContactsName;
  }

  public void setSkContactsName(String skContactsName) {
       this.skContactsName = skContactsName;
  }

  public String getMinTime() {
       return minTime;
  }

  public void setMinTime(String minTime) {
       this.minTime = minTime;
  }

  public String getMinGoalType() {
       return minGoalType;
  }

  public void setMinGoalType(String minGoalType) {
       this.minGoalType = minGoalType;
  }

  public String getMinGoalContent() {
       return minGoalContent;
  }

  public void setMinGoalContent(String minGoalContent) {
       this.minGoalContent = minGoalContent;
  }

  public String getMoutYN() {
       return moutYN;
  }

  public void setMoutYN(String moutYN) {
       this.moutYN = moutYN;
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

  public String getUser_yn() {
       return user_yn;
  }

  public void setUser_yn(String user_yn) {
       this.user_yn = user_yn;
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

public String getDeal_user_id() {
    return deal_user_id;
}

public void setDeal_user_id(String deal_user_id) {
    this.deal_user_id = deal_user_id;
}
public String getApply_date() {
	   return apply_date;
}

public void setApply_date(String apply_date) {
   this.apply_date = apply_date;
}
public String getApproval_date() {
   return approval_date;
}

public void setApproval_date(String approval_date) {
   this.approval_date = approval_date;
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

public void setFactory_manager_approval_date(String factory_manager_approval_date) {
   this.factory_manager_approval_date = factory_manager_approval_date;
}
public String getReject_date() {
	   return reject_date;
}

public void setReject_date(String reject_date) {
this.reject_date = reject_date;
}

  public String getJson_info() {
      return json_info;
 }

 public void setJson_info(String json_info) {
      this.json_info = json_info;
 }
 public String getPicInfo() {
     return picInfo;
}

public void setPicInfo(String picInfo) {
     this.picInfo = picInfo;
}

public String getDeal_user_name() {
    return deal_user_name;
}
public void setDeal_user_name(String deal_user_name) {
    this.deal_user_name = deal_user_name;
}

public String getApproval_state_name() {
    return approval_state_name;
}

public void setApproval_state_name(String approval_state_name) {
    this.approval_state_name = approval_state_name;
}
}
