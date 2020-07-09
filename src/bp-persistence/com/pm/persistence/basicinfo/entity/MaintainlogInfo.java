package com.pm.persistence.basicinfo.entity;

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
 * 表maintainlog_info 的实体类
 * @author 刘镝
 */
@Entity(name = "maintainlog_info")
public class MaintainlogInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public MaintainlogInfo clone() {
      MaintainlogInfo o = null;
      try {
          o = (MaintainlogInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 维修日志id
   */
  @ID
  @Column(name = "maintainlog_id", comment = "维修日志id")
  private String maintainlog_id;
  
  /**
   * 职能区分
   */
  @Column(name = "subdept_type", comment = "职能区分")
  private String subdept_type;
  
  /**
   * 职能区分名称
   */
  @Transient
  @Column(name = "subdept_type_nm", comment = "职能区分")
  private String subdept_type_nm;
  
  /**
   * 部门区分
   */
  @Column(name = "dept_type", comment = "部门区分")
  private String dept_type;

  /**
   * 
   */
  @Column(name = "log_contents", comment = "内容")
  private String log_contents;

  /**
   * 维修类型
   */
  @Column(name = "maintain_type", comment = "维修类型")
  private String maintain_type;
  /**
   * 维修类型
   */
  @Transient
  @Column(name = "maint_type__name")
  private String maint_type__name;
  
  /**
   * 维修时间
   */
  @Column(name = "maintain_time", comment = "维修时间")
  private String maintain_time;

  /**
   * 人工
   */
  @Column(name = "human_amount", comment = "人工")
  private String human_amount;

  /**
   * 三方人员工时
   */
  @Column(name = "time_amount", comment = "三方人员工时")
  private String time_amount;

  /**
   * 确认状态
   */
  @Column(name = "check_state", comment = "确认状态")
  private String check_state;
  /**
   * 确认状态_name
   */
  @Transient
  @Column(name = "check_state_name")
  private String check_state_name;

  /**
   * 确认人
   */
  @Column(name = "check_user", comment = "确认人")
  private String check_user;

  /**
   * 维护人员
   */
  @Column(name = "maintain_user", comment = "维护人员")
  private String maintain_user;

  /**
   * 备注
   */
  @Column(name = "log_remark", comment = "备注")
  private String log_remark;
   
   @Column(name = "confirm_yn", comment = "确认")
   private String confirm_yn;
   
 
  @Column(name = "create_id")
  private String create_id;
  @Transient
  @Column(name = "create_name")
  private String create_name;
  @Column(name = "create_time")
  private String create_time;
  
  @Column(name = "update_id")
  private String update_id;
  
  @Column(name = "update_time")
  private String update_time;
  
  @Transient
  private String date_start;
  @Transient
  private String date_end;
  
  //灌装机id
  @Column(name = "machineid")
  private String machineid;
  //灌装机name
  @Transient
  @Column(name = "machinename")
  private String machinename;
  
  public String getMaintainlog_id() {
       return maintainlog_id;
  }

  public void setMaintainlog_id(String maintainlog_id) {
       this.maintainlog_id = maintainlog_id;
  }

  public String getLog_contents() {
       return log_contents;
  }

  public void setLog_contents(String log_contents) {
       this.log_contents = log_contents;
  }

  public String getMaintain_type() {
       return maintain_type;
  }

  public void setMaintain_type(String maintain_type) {
       this.maintain_type = maintain_type;
  }

  public String getMaintain_time() {
       return maintain_time;
  }

  public void setMaintain_time(String maintain_time) {
       this.maintain_time = maintain_time;
  }

  public String getHuman_amount() {
       return human_amount;
  }

  public void setHuman_amount(String human_amount) {
       this.human_amount = human_amount;
  }

  public String getTime_amount() {
       return time_amount;
  }

  public void setTime_amount(String time_amount) {
       this.time_amount = time_amount;
  }

  public String getCheck_state() {
       return check_state;
  }

  public void setCheck_state(String check_state) {
       this.check_state = check_state;
  }

  public String getCheck_user() {
       return check_user;
  }

  public void setCheck_user(String check_user) {
       this.check_user = check_user;
  }

  public String getMaintain_user() {
       return maintain_user;
  }

  public void setMaintain_user(String maintain_user) {
       this.maintain_user = maintain_user;
  }

  public String getLog_remark() {
       return log_remark;
  }

  public void setLog_remark(String log_remark) {
       this.log_remark = log_remark;
  }

public String getDate_start() {
	return date_start;
}

public void setDate_start(String date_start) {
	this.date_start = date_start;
}

public String getDate_end() {
	return date_end;
}

public void setDate_end(String date_end) {
	this.date_end = date_end;
}

public String getCheck_state_name() {
	return check_state_name;
}

public void setCheck_state_name(String check_state_name) {
	this.check_state_name = check_state_name;
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

public String getSubdept_type() {
	return subdept_type;
}

public void setSubdept_type(String subdept_type) {
	this.subdept_type = subdept_type;
}

public String getDept_type() {
	return dept_type;
}

public void setDept_type(String dept_type) {
	this.dept_type = dept_type;
}

public String getSubdept_type_nm() {
	return subdept_type_nm;
}

public void setSubdept_type_nm(String subdept_type_nm) {
	this.subdept_type_nm = subdept_type_nm;
}

public String getCreate_name() {
	return create_name;
}

public void setCreate_name(String create_name) {
	this.create_name = create_name;
}

public String getConfirm_yn() {
	return confirm_yn;
}

public void setConfirm_yn(String confirm_yn) {
	this.confirm_yn = confirm_yn;
}

public String getMachineid() {
	return machineid;
}

public void setMachineid(String machineid) {
	this.machineid = machineid;
}

public String getMaint_type__name() {
	return maint_type__name;
}

public void setMaint_type__name(String maint_type__name) {
	this.maint_type__name = maint_type__name;
}

public String getMachinename() {
	return machinename;
}

public void setMachinename(String machinename) {
	this.machinename = machinename;
}

}
