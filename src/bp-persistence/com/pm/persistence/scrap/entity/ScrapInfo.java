package com.pm.persistence.scrap.entity;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表scrap_info 的实体类
 * @author 刘镝
 */
@Entity(name = "scrap_info")
public class ScrapInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public ScrapInfo clone() {
      ScrapInfo o = null;
      try {
          o = (ScrapInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 报废id
   */
  @ID
  @Column(name = "scrap_id", comment = "报废id")
  private String scrap_id;

  /**
   * 报废设备id
   */
  @Column(name = "scrap_machine_id", comment = "报废设备id")
  private String scrap_machine_id;
  
  /**
   * 报废设备名称
   */
  @Transient
  @Column(name = "scrap_machine_nm", comment = "报废设备名称")
  private String scrap_machine_nm;
  
  /**
   * 报废设备类别
   */
  @Column(name = "machine_species_id", comment = "报废设备类别")
  private String machine_species_id;

  /**
   * 报废设备类型
   */
  @Column(name = "machine_type_id", comment = "报废设备类型")
  private String machine_type_id;

  /**
   * 报废设备类型名称
   */
  @Transient
  @Column(name = "machine_type_nm", comment = "报废设备类型名称")
  private String machine_type_nm;
  /**
   * 报废数量
   */
  @Column(name = "scrap_num", comment = "报废数量")
  private String scrap_num;

  /**
   * 报废原因
   */
  @Column(name = "scrap_reason", comment = "报废原因")
  private String scrap_reason;

  /**
   * 报废日期
   */
  @Column(name = "scrap_date", comment = "报废日期")
  private String scrap_date;

  /**
   * 报废日期
   */
  @Transient
  @Column(name = "scrap_date_start", comment = "报废日期")
  private String scrap_date_start;
  /**
   * 报废日期
   */
  @Transient
  @Column(name = "scrap_date_end", comment = "报废日期")
  private String scrap_date_end;
  /**
   * 是否属于关键设备
   */
  @Column(name = "main_machine_yn", comment = "是否属于关键设备")
  private String main_machine_yn;
  
  /**
   * 是否属于关键设备名称
   */
  @Transient
  @Column(name = "main_machine_nm", comment = "是否属于关键设备")
  private String main_machine_nm;
  
  /**
   * 是否有配件
   */
  @Transient
  @Column(name = "spare_nm", comment = "是否有配件")
  private String spare_nm;
  
  /**
   * 申请人名称
   */
  @Transient
  @Column(name = "user_nm", comment = "申请人名称")
  private String user_nm;
  /**
   * 是否有配件
   */
  @Column(name = "spare_yn", comment = "是否有配件")
  private String spare_yn;

  /**
   * 报废状态
   */
  @Column(name = "scrap_state", comment = "报废状态")
  private String scrap_state;

  /**
   * 申请人
   */
  @Column(name = "apply_userid", comment = "申请人")
  private String apply_userid;

  /**
   * 科长审批
   */
  @Column(name = "approval_id1", comment = "科长审批")
  private String approval_id1;

  /**
   * 厂长审批
   */
  @Column(name = "approval_id2", comment = "厂长审批")
  private String approval_id2;

  /**
   * 
   */
  @Column(name = "create_id", comment = "")
  private String create_id;

  /**
   * 
   */
  @Column(name = "create_time", comment = "")
  private String create_time;

  /**
   * 
   */
  @Column(name = "update_id", comment = "")
  private String update_id;

  /**
   * 
   */
  @Column(name = "update_time", comment = "")
  private String update_time;
  
  @Column(name = "use_yn", comment = "")
  private String use_yn;
  
  @Transient
  private List<SysCodeInfo> stateYnList;

  public String getScrap_id() {
       return scrap_id;
  }

  public void setScrap_id(String scrap_id) {
       this.scrap_id = scrap_id;
  }

  public String getScrap_machine_id() {
       return scrap_machine_id;
  }

  public void setScrap_machine_id(String scrap_machine_id) {
       this.scrap_machine_id = scrap_machine_id;
  }

  public String getMachine_type_id() {
       return machine_type_id;
  }

  public void setMachine_type_id(String machine_type_id) {
       this.machine_type_id = machine_type_id;
  }

  public String getScrap_num() {
       return scrap_num;
  }

  public void setScrap_num(String scrap_num) {
       this.scrap_num = scrap_num;
  }

  public String getScrap_reason() {
       return scrap_reason;
  }

  public void setScrap_reason(String scrap_reason) {
       this.scrap_reason = scrap_reason;
  }

  public String getScrap_date() {
       return scrap_date;
  }

  public void setScrap_date(String scrap_date) {
       this.scrap_date = scrap_date;
  }

  public String getMain_machine_yn() {
       return main_machine_yn;
  }

  public void setMain_machine_yn(String main_machine_yn) {
       this.main_machine_yn = main_machine_yn;
  }

  public String getSpare_yn() {
       return spare_yn;
  }

  public void setSpare_yn(String spare_yn) {
       this.spare_yn = spare_yn;
  }

  public String getScrap_state() {
       return scrap_state;
  }

  public void setScrap_state(String scrap_state) {
       this.scrap_state = scrap_state;
  }

  public String getApply_userid() {
       return apply_userid;
  }

  public void setApply_userid(String apply_userid) {
       this.apply_userid = apply_userid;
  }

  public String getApproval_id1() {
       return approval_id1;
  }

  public void setApproval_id1(String approval_id1) {
       this.approval_id1 = approval_id1;
  }

  public String getApproval_id2() {
       return approval_id2;
  }

  public void setApproval_id2(String approval_id2) {
       this.approval_id2 = approval_id2;
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

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getScrap_machine_nm() {
	return scrap_machine_nm;
}

public void setScrap_machine_nm(String scrap_machine_nm) {
	this.scrap_machine_nm = scrap_machine_nm;
}

public String getMachine_type_nm() {
	return machine_type_nm;
}

public void setMachine_type_nm(String machine_type_nm) {
	this.machine_type_nm = machine_type_nm;
}

public String getMain_machine_nm() {
	return main_machine_nm;
}

public void setMain_machine_nm(String main_machine_nm) {
	this.main_machine_nm = main_machine_nm;
}

public String getSpare_nm() {
	return spare_nm;
}

public void setSpare_nm(String spare_nm) {
	this.spare_nm = spare_nm;
}

public String getScrap_date_start() {
	return scrap_date_start;
}

public void setScrap_date_start(String scrap_date_start) {
	this.scrap_date_start = scrap_date_start;
}

public String getScrap_date_end() {
	return scrap_date_end;
}

public void setScrap_date_end(String scrap_date_end) {
	this.scrap_date_end = scrap_date_end;
}

public List<SysCodeInfo> getStateYnList() {
	return stateYnList;
}

public void setStateYnList(List<SysCodeInfo> stateYnList) {
	this.stateYnList = stateYnList;
}

public String getUse_yn() {
	return use_yn;
}

public void setUse_yn(String use_yn) {
	this.use_yn = use_yn;
}

public String getUser_nm() {
	return user_nm;
}

public void setUser_nm(String user_nm) {
	this.user_nm = user_nm;
}


}
