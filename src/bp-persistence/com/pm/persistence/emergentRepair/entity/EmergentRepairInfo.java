package com.pm.persistence.emergentRepair.entity;

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
 * 表emergent_repair_info 的实体类
 * @author 刘镝
 */
@Entity(name = "emergent_repair_info")
public class EmergentRepairInfo extends BaseEntity implements Serializable, Cloneable {

  public EmergentRepairInfo clone() {
      EmergentRepairInfo o = null;
      try {
          o = (EmergentRepairInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 主键ID
   */
  @ID
  @Column(name = "emergent_id", comment = "主键ID")
  private String emergent_id;

  /**
   * 职能
   */
  @Column(name = "job_type", comment = "职能")
  private String job_type;

  /**
   * 维修内容
   */
  @Column(name = "repair_content", comment = "维修内容")
  private String repair_content;

  /**
   * 维修日期
   */
  @Column(name = "repair_date", comment = "维修日期")
  private String repair_date;

  /**
   * 维修担当
   */
  @Column(name = "repair_person", comment = "维修担当")
  private String repair_person;

  /**
   * 设备
   */
  @Column(name = "pot_id", comment = "设备")
  private String pot_id;

  /**
   * 部件
   */
  @Column(name = "pot_part_id", comment = "部件")
  private String pot_part_id;

  /**
   * 子部件
   */
  @Column(name = "pot_son_part_id", comment = "子部件")
  private String pot_son_part_id;

  /**
   * 开始时间
   */
  @Column(name = "start_time", comment = "开始时间")
  private String start_time;

  /**
   * 结束时间
   */
  @Column(name = "end_time", comment = "结束时间")
  private String end_time;

  /**
   * 总用时
   */
  @Column(name = "total_time", comment = "总用时")
  private String total_time;

  /**
   * 状态
   */
  @Column(name = "repairType", comment = "状态")
  private String repairType;
  
  /**
   * 维修小时
   */
  @Column(name = "repairHours", comment = "维修小时")
  private String repairHours;
  
  /**
   * 维修开始
   */
  @Column(name = "repairStart_time", comment = "维修开始")
  private String repairStart_time;
  
  /**
   * 维修结束
   */
  @Column(name = "repairEnd_time", comment = "维修结束")
  private String repairEnd_time;
  
  /**
   * 维修内容
   */
  @Column(name = "repair_remark", comment = "维修内容")
  private String repair_remark;
  
  /**
   * 停机小时数
   */
  @Column(name = "emergent_hours", comment = "停机小时数")
  private String emergent_hours;
  
  /**
   * 创建人
   */
  @Column(name = "create_id", comment = "创建人")
  private String create_id;

  /**
   * 创建人姓名
   */
  @Column(name = "create_name", comment = "创建人姓名")
  private String create_name;

  
  /**
   * 创建时间
   */
  @Column(name = "create_time", comment = "创建时间")
  private String create_time;

  /**
   * 修改人
   */
  @Column(name = "update_id", comment = "修改人")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;
  
  /**
   * 维修类型名
   */
  @Transient
  @Column(name = "job_type_nm", comment = "维修类型名")
  private String job_type_nm;
  
  /**
   * 设备名
   */
  @Transient
  @Column(name = "pot_id_nm", comment = "设备名")
  private String pot_id_nm;
  
  /**
   * 部件名
   */
  @Transient
  @Column(name = "pot_part_id_nm", comment = "部件名")
  private String pot_part_id_nm;
  
  /**
   * 子部件名
   */
  @Transient
  @Column(name = "pot_son_part_id_nm", comment = "子部件名")
  private String pot_son_part_id_nm;
  
  /**
   * 更新人
   */
  @Transient
  @Column(name = "update_nm", comment = "更新人")
  private String update_nm;
  
  /**
   * 创建人
   */
  @Transient
  @Column(name = "create_nm", comment = "创建人")
  private String create_nm;
  
  /**
   * 创建开始
   */
  @Transient
  @Column(name = "search_start", comment = "创建人")
  private String search_start;
  
  /**
   * 创建人
   */
  @Transient
  @Column(name = "search_end", comment = "创建人")
  private String search_end;
  
  /**
   * sqm正常参数
   */
  @Column(name = "sqm_normal_parameter", comment = "sqm正常参数")
  private String sqm_normal_parameter;
  
  /**
   * sqm故障解除与否：Y解除，N未解除
   */
  @Column(name = "sqm_relieve_yn", comment = "sqm故障解除与否：Y解除，N未解除")
  private String sqm_relieve_yn;

  public String getSqm_relieve_yn() {
	return sqm_relieve_yn;
}

public void setSqm_relieve_yn(String sqm_relieve_yn) {
	this.sqm_relieve_yn = sqm_relieve_yn;
}

public String getSqm_normal_parameter() {
	return sqm_normal_parameter;
}

public void setSqm_normal_parameter(String sqm_normal_parameter) {
	this.sqm_normal_parameter = sqm_normal_parameter;
}

public String getEmergent_id() {
       return emergent_id;
  }

  public void setEmergent_id(String emergent_id) {
       this.emergent_id = emergent_id;
  }

  public String getJob_type() {
       return job_type;
  }

  public void setJob_type(String job_type) {
       this.job_type = job_type;
  }

  public String getRepair_content() {
       return repair_content;
  }

  public void setRepair_content(String repair_content) {
       this.repair_content = repair_content;
  }

  public String getRepair_date() {
       return repair_date;
  }

  public void setRepair_date(String repair_date) {
       this.repair_date = repair_date;
  }

  public String getRepair_person() {
       return repair_person;
  }

  public void setRepair_person(String repair_person) {
       this.repair_person = repair_person;
  }

  public String getPot_id() {
       return pot_id;
  }

  public void setPot_id(String pot_id) {
       this.pot_id = pot_id;
  }

  public String getPot_part_id() {
       return pot_part_id;
  }

  public void setPot_part_id(String pot_part_id) {
       this.pot_part_id = pot_part_id;
  }

  public String getPot_son_part_id() {
       return pot_son_part_id;
  }

  public void setPot_son_part_id(String pot_son_part_id) {
       this.pot_son_part_id = pot_son_part_id;
  }

  public String getStart_time() {
       return start_time;
  }

  public void setStart_time(String start_time) {
       this.start_time = start_time;
  }

  public String getEnd_time() {
       return end_time;
  }

  public void setEnd_time(String end_time) {
       this.end_time = end_time;
  }

  public String getTotal_time() {
       return total_time;
  }

  public void setTotal_time(String total_time) {
       this.total_time = total_time;
  }

  public String getCreate_id() {
       return create_id;
  }

  public void setCreate_id(String create_id) {
       this.create_id = create_id;
  }

  public String getCreate_name() {
      return create_name;
 }

 public void setCreate_name(String create_name) {
      this.create_name = create_name;
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

public String getJob_type_nm() {
	return job_type_nm;
}

public void setJob_type_nm(String job_type_nm) {
	this.job_type_nm = job_type_nm;
}

public String getPot_id_nm() {
	return pot_id_nm;
}

public void setPot_id_nm(String pot_id_nm) {
	this.pot_id_nm = pot_id_nm;
}

public String getPot_part_id_nm() {
	return pot_part_id_nm;
}

public void setPot_part_id_nm(String pot_part_id_nm) {
	this.pot_part_id_nm = pot_part_id_nm;
}

public String getPot_son_part_id_nm() {
	return pot_son_part_id_nm;
}

public void setPot_son_part_id_nm(String pot_son_part_id_nm) {
	this.pot_son_part_id_nm = pot_son_part_id_nm;
}

public String getRepairType() {
	return repairType;
}

public void setRepairType(String repairType) {
	this.repairType = repairType;
}

public String getRepairStart_time() {
	return repairStart_time;
}

public void setRepairStart_time(String repairStart_time) {
	this.repairStart_time = repairStart_time;
}
public String getRepairEnd_time() {
	return repairEnd_time;
}

public void setRepairEnd_time(String repairEnd_time) {
	this.repairEnd_time = repairEnd_time;
}
public String getRepairHours() {
	return repairHours;
}

public void setRepairHours(String repairHours) {
	this.repairHours = repairHours;
}
public String getUpdate_nm() {
	return update_nm;
}

public void setUpdate_nm(String update_nm) {
	this.update_nm = update_nm;
}

public String getCreate_nm() {
	return create_nm;
}

public void setCreate_nm(String create_nm) {
	this.create_nm = create_nm;
}

public String getSearch_start() {
	return search_start;
}

public void setSearch_start(String search_start) {
	this.search_start = search_start;
}

public String getSearch_end() {
	return search_end;
}

public void setSearch_end(String search_end) {
	this.search_end = search_end;
}
  
public String getRepair_remark() {
	return repair_remark;
}

public void setRepair_remark(String repair_remark) {
	this.repair_remark = repair_remark;
}

public String getEmergent_hours() {
	return emergent_hours;
}

public void setEmergent_hours(String emergent_hours) {
	this.emergent_hours = emergent_hours;
}
}
