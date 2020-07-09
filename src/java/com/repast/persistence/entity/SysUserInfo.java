package com.repast.persistence.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sys_user_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sys_user_info")
public class SysUserInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 5795186162289562464L;

  public SysUserInfo clone() {
      SysUserInfo o = null;
      try {
          o = (SysUserInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 用户ID
   */
  @ID
  @Column(name = "user_id", comment = "用户ID")
  private String user_id;

  /**
   * 工号
   */
  @Column(name = "user_num", comment = "工号")
  private String user_num;

  /**
   * 密码
   */
  @Column(name = "pwd", comment = "密码")
  private String pwd;

  /**
   * 用户姓名
   */
  @Column(name = "user_nm", comment = "用户姓名")
  private String user_nm;

  /**
   * 职位
   */
  @Column(name = "position_id", comment = "职位")
  private String position_id;
  
  /**
   * 职位名称
   */
  @Transient
  @Column(name = "position_nm", comment = "职位名称")
  private String position_nm;

  /**
   * 上级部门code
   */
  @Column(name = "sub_dept_code", comment = "下级部门code")
  private String sub_dept_code;
  
  /**
   * 上级部门名称
   */
  @Transient
  @Column(name = "sub_dept_nm", comment = "下级部门名称")
  private String sub_dept_nm;

  /**
   * 部门code
   */
  @Column(name = "dept_code", comment = "部门code")
  private String dept_code;
  
  
  /**
   * 部门名称
   */
  @Transient
  @Column(name = "dept_code_nm", comment = "部门名称")
  private String dept_code_nm;

  /**
   * 邮箱
   */
  @Column(name = "email", comment = "邮箱")
  private String email;

  /**
   * 手机
   */
  @Column(name = "mobile", comment = "手机")
  private String mobile;

  /**
   * 办公电话
   */
  @Column(name = "Telephone", comment = "办公电话")
  private String Telephone;

  /**
   * 权限code
   */
  @Column(name = "auth_code", comment = "权限code")
  private String auth_code;

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
   * 排序类型
   */
  @Transient
  @Column(name = "orderType", comment = "排序类型")
  private String orderType;
  
  /**
   * 登陆类型
   */
  @Transient
  @Column(name = "loginType", comment = "登陆类型")
  private String loginType;

  /**
   * 提案数
   */
  @Transient
  @Column(name = "reportCount", comment = "提案数")
  private int reportCount;
  
  
  /**
   * 同意数
   */
  @Transient
  @Column(name = "tongYiCount", comment = "同意数")
  private int tongYiCount;
  
  
  /**
   * 驳回数
   */
  @Transient
  @Column(name = "boHuiCount", comment = "驳回数")
  private int boHuiCount;
  
  
  /**
   * 未审批数
   */
  @Transient
  @Column(name = "weiShenCount", comment = "未审批数")
  private int weiShenCount;
  
  
  /**
   * 是否可用
   */
  @Transient
  @Column(name = "user_yn", comment = "是否可用")
  private int user_yn;
  
  /**
   * 是否参与评价
   */

  @Column(name = "evaluatYn", comment = "是否参与评价")
  private String evaluatYn;
  
  
  @Column(name = "registrationID", comment = "推送ID字段")
  private String registrationID;
  
  @Column(name = "pmregistrationID", comment = "推送ID字段")
  private String pmregistrationID;
  
  @Column(name = "sqm_role_code", comment = "sqm角色")
  private String sqm_role_code;
  
  /**
   * sqm角色nm
   */
  @Transient
  @Column(name = "sqm_role_nm", comment = "sqm角色nm")
  private String sqm_role_nm;
  
  public String getSqm_role_nm() {
	return sqm_role_nm;
}

public void setSqm_role_nm(String sqm_role_nm) {
	this.sqm_role_nm = sqm_role_nm;
}

public String getSqm_role_code() {
	return sqm_role_code;
}

public void setSqm_role_code(String sqm_role_code) {
	this.sqm_role_code = sqm_role_code;
}

public String getRegistrationID() {
	return registrationID;
}

public void setRegistrationID(String registrationID) {
	this.registrationID = registrationID;
}

public String getPmregistrationID() {
	return pmregistrationID;
}

public void setPmregistrationID(String pmregistrationID) {
	this.pmregistrationID = pmregistrationID;
}

public String getUser_id() {
       return user_id;
  }

  public void setUser_id(String user_id) {
       this.user_id = user_id;
  }

  public String getUser_num() {
       return user_num;
  }

  public void setUser_num(String user_num) {
       this.user_num = user_num;
  }

  public String getPwd() {
       return pwd;
  }

  public void setPwd(String pwd) {
       this.pwd = pwd;
  }

  public String getUser_nm() {
       return user_nm;
  }

  public void setUser_nm(String user_nm) {
       this.user_nm = user_nm;
  }

  public String getPosition_id() {
       return position_id;
  }

  public void setPosition_id(String position_id) {
       this.position_id = position_id;
  }

  public String getSub_dept_code() {
       return sub_dept_code;
  }

  public void setSub_dept_code(String sub_dept_code) {
       this.sub_dept_code = sub_dept_code;
  }

  public String getDept_code() {
       return dept_code;
  }

  public void setDept_code(String dept_code) {
       this.dept_code = dept_code;
  }

  public String getEmail() {
       return email;
  }

  public void setEmail(String email) {
       this.email = email;
  }

  public String getMobile() {
       return mobile;
  }

  public void setMobile(String mobile) {
       this.mobile = mobile;
  }

  public String getTelephone() {
       return Telephone;
  }

  public void setTelephone(String Telephone) {
       this.Telephone = Telephone;
  }

  public String getAuth_code() {
       return auth_code;
  }

  public void setAuth_code(String auth_code) {
       this.auth_code = auth_code;
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

public String getSub_dept_nm() {
	return sub_dept_nm;
}

public void setSub_dept_nm(String sub_dept_nm) {
	this.sub_dept_nm = sub_dept_nm;
}

public String getOrderType() {
	return orderType;
}

public void setOrderType(String orderType) {
	this.orderType = orderType;
}

public String getPosition_nm() {
	return position_nm;
}

public void setPosition_nm(String position_nm) {
	this.position_nm = position_nm;
}

public String getDept_code_nm() {
	return dept_code_nm;
}

public void setDept_code_nm(String dept_code_nm) {
	this.dept_code_nm = dept_code_nm;
}

public String getLoginType() {
	return loginType;
}

public void setLoginType(String loginType) {
	this.loginType = loginType;
}
public int getReportCount() {
	return reportCount;
}

public void setReportCount(int reportCount) {
	this.reportCount = reportCount;
}
public int getTongYiCount() {
	return tongYiCount;
}

public void setTongYiCount(int tongYiCount) {
	this.tongYiCount = tongYiCount;
}
public int getBoHuiCount() {
	return boHuiCount;
}

public void setBoHuiCount(int boHuiCount) {
	this.boHuiCount = boHuiCount;
}
public int getWeiShenCount() {
	return weiShenCount;
}

public void setWeiShenCount(int weiShenCount) {
	this.weiShenCount = weiShenCount;
}
public int getUser_yn() {
	return user_yn;
}

public void setUser_yn(int user_yn) {
	this.user_yn = user_yn;
}

public String getEvaluatYn() {
	return evaluatYn;
}

public void setEvaluatYn(String evaluatYn) {
	this.evaluatYn = evaluatYn;
}

}
