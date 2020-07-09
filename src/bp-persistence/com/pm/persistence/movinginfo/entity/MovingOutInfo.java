package com.pm.persistence.movinginfo.entity;

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
 * 表moving_out_info 的实体类
 * @author 刘镝
 */
@Entity(name = "moving_out_info")
public class MovingOutInfo extends BaseEntity implements Serializable, Cloneable {

  public MovingOutInfo clone() {
      MovingOutInfo o = null;
      try {
          o = (MovingOutInfo) super.clone();
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
  @Column(name = "moutNo", comment = "搬入证编号")
  private String moutNo;

  /**
   * 搬入人姓名
   */
  @Column(name = "moutName", comment = "搬入人姓名")
  private String moutName;

  /**
   * 搬入人公司名称
   */
  @Column(name = "moutCompany", comment = "搬入人公司名称")
  private String moutCompany;

  /**
   * 车牌号
   */
  @Column(name = "moutCarno", comment = "车牌号")
  private String moutCarno;
  
  /**
   * 车牌号
   */
  @Transient
  @Column(name = "movingCarno", comment = "车牌号")
  private String movingCarno;

  /**
   * 电话
   */
  @Column(name = "moutPhone", comment = "电话")
  private String moutPhone;

  /**
   * sk联系人编号
   */
  @Column(name = "skApplyNo", comment = "sk联系人编号")
  private String skApplyNo;

  /**
   * sk联系人姓名
   */
  @Column(name = "skApplyName", comment = "sk联系人姓名")
  private String skApplyName;

  /**
   * sk部门
   */
  @Column(name = "skApplyDept", comment = "sk部门")
  private String skApplyDept;

  /**
   * 搬出时间
   */
  @Column(name = "moutTime", comment = "搬出时间")
  private String moutTime;

  /**
   * 目的类别
   */
  @Column(name = "moutGoalType", comment = "目的类别")
  private String moutGoalType;

  /**
   * 搬入目的描述
   */
  @Column(name = "moutGoalContent", comment = "搬入目的描述")
  private String moutGoalContent;

  /**
   * 是否原材料
   */
  @Column(name = "ylbanchuYN", comment = "是否原材料")
  private String ylbanchuYN;

  /**
   * 空车出库
   */
  @Column(name = "emptyOut", comment = "空车出库")
  private String emptyOut;

  /**
   * 有无出库单
   */
  @Column(name = "deliveryOrderYN", comment = "有无出库单")
  private String deliveryOrderYN;

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
   * 图片
   */
  @Column(name = "picInfo", comment = "图片")
  private String picInfo;
  
  /**
   * 确认图片
   */
  @Column(name = "picQueRenInfo", comment = "图片")
  private String picQueRenInfo;
  
  /**
   * 
   */
  @Transient
  @Column(name = "json_info", comment = "")
  private String json_info;
  
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
  /**
   * 
   */
  @Transient
  @Column(name = "applyPerson1", comment = "")
  private String applyPerson1;
  /**
   * 
   */
  @Transient
  @Column(name = "applyPerson2", comment = "")
  private String applyPerson2;
  
  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getMoutNo() {
       return moutNo;
  }

  public void setMoutNo(String moutNo) {
       this.moutNo = moutNo;
  }

  public String getMoutName() {
       return moutName;
  }

  public void setMoutName(String moutName) {
       this.moutName = moutName;
  }

  public String getMoutCompany() {
       return moutCompany;
  }

  public void setMoutCompany(String moutCompany) {
       this.moutCompany = moutCompany;
  }

  public String getMoutCarno() {
       return moutCarno;
  }

  public void setMoutCarno(String moutCarno) {
       this.moutCarno = moutCarno;
  }

  public String getMovingCarno() {
      return movingCarno;
 }

 public void setMovingCarno(String movingCarno) {
      this.movingCarno = movingCarno;
 }
 
  public String getMoutPhone() {
       return moutPhone;
  }

  public void setMoutPhone(String moutPhone) {
       this.moutPhone = moutPhone;
  }

  public String getSkApplyNo() {
       return skApplyNo;
  }

  public void setSkApplyNo(String skApplyNo) {
       this.skApplyNo = skApplyNo;
  }

  public String getSkApplyName() {
       return skApplyName;
  }

  public void setSkApplyName(String skApplyName) {
       this.skApplyName = skApplyName;
  }

  public String getSkApplyDept() {
       return skApplyDept;
  }

  public void setSkApplyDept(String skApplyDept) {
       this.skApplyDept = skApplyDept;
  }

  public String getMoutTime() {
       return moutTime;
  }

  public void setMoutTime(String moutTime) {
       this.moutTime = moutTime;
  }

  public String getMoutGoalType() {
       return moutGoalType;
  }

  public void setMoutGoalType(String moutGoalType) {
       this.moutGoalType = moutGoalType;
  }

  public String getMoutGoalContent() {
       return moutGoalContent;
  }

  public void setMoutGoalContent(String moutGoalContent) {
       this.moutGoalContent = moutGoalContent;
  }

  public String getYlbanchuYN() {
       return ylbanchuYN;
  }

  public void setYlbanchuYN(String ylbanchuYN) {
       this.ylbanchuYN = ylbanchuYN;
  }

  public String getEmptyOut() {
       return emptyOut;
  }

  public void setEmptyOut(String emptyOut) {
       this.emptyOut = emptyOut;
  }

  public String getDeliveryOrderYN() {
       return deliveryOrderYN;
  }

  public void setDeliveryOrderYN(String deliveryOrderYN) {
       this.deliveryOrderYN = deliveryOrderYN;
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

public String getPicQueRenInfo() {
    return picQueRenInfo;
}

public void setPicQueRenInfo(String picQueRenInfo) {
    this.picQueRenInfo = picQueRenInfo;
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

public String getApplyPerson1() {
    return applyPerson1;
}

public void setApplyPerson1(String applyPerson1) {
    this.applyPerson1 = applyPerson1;
}

public String getApplyPerson2() {
    return applyPerson2;
}

public void setApplyPerson2(String applyPerson2) {
    this.applyPerson2 = applyPerson2;
}
}
