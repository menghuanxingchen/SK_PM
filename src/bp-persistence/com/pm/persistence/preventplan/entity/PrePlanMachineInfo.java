package com.pm.persistence.preventplan.entity;

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
 * 表pre_plan_machine_info 的实体类
 * @author 刘镝
 */
@Entity(name = "pre_plan_machine_info")
public class PrePlanMachineInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 3019480109774740100L;

  public PrePlanMachineInfo clone() {
      PrePlanMachineInfo o = null;
      try {
          o = (PrePlanMachineInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * plan_machine_id
   */
  @ID
  @Column(name = "plan_machine_id", comment = "plan_machine_id")
  private String plan_machine_id;

  /**
   * 保养计划id
   */
  @Column(name = "pre_plan_id", comment = "保养计划id")
  private String pre_plan_id;

  /**
   * 设备id
   */
  @Column(name = "pre_machine_id", comment = "设备id")
  private String pre_machine_id;

  /**
   * 设备类型
   */
  @Transient
  @Column(name = "machine_species_id", comment = "设备类型")
  private String machine_species_id;
  
  /**
   * 设备类别
   */
  @Transient
  @Column(name = "machine_type_id", comment = "设备类别")
  private String machine_type_id;
  /**
   * 检查类型分类
   */
  @Column(name = "chk_type", comment = "检查类型分类")
  private String chk_type;

  /**
   * 已检查项目数
   */
  @Column(name = "pre_check_num", comment = "已检查项目数")
  private String pre_check_num;
  
  /**
   * pm完成率
   */
  @Column(name = "m_pm_rate", comment = "pm完成率")
  private String m_pm_rate;

  /**
   * 需检查项目总数
   */
  @Column(name = "pre_check_all", comment = "需检查项目总数")
  private String pre_check_all;

  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
  private String create_id;

  /**
   * 
   */
  @Column(name = "create_time", comment = "")
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
   * 设备名称
   */
  @Transient
  @Column(name = "machine_nm", comment = "设备名称")
  private String machine_nm;
  
  /**
   * 实际操作时间
   */
  @Column(name = "operate_date", comment = "实际操作时间")
  private String operate_date;
  
  /**
   * 区域
   */
  @Transient
  @Column(name = "area_id", comment = "区域")
  private String area_id;

  public String getPlan_machine_id() {
       return plan_machine_id;
  }

  public void setPlan_machine_id(String plan_machine_id) {
       this.plan_machine_id = plan_machine_id;
  }

  public String getPre_plan_id() {
       return pre_plan_id;
  }

  public void setPre_plan_id(String pre_plan_id) {
       this.pre_plan_id = pre_plan_id;
  }

  public String getPre_machine_id() {
       return pre_machine_id;
  }

  public void setPre_machine_id(String pre_machine_id) {
       this.pre_machine_id = pre_machine_id;
  }

  public String getChk_type() {
       return chk_type;
  }

  public void setChk_type(String chk_type) {
       this.chk_type = chk_type;
  }

  public String getPre_check_num() {
       return pre_check_num;
  }

  public void setPre_check_num(String pre_check_num) {
       this.pre_check_num = pre_check_num;
  }

  public String getPre_check_all() {
       return pre_check_all;
  }

  public void setPre_check_all(String pre_check_all) {
       this.pre_check_all = pre_check_all;
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

public String getMachine_nm() {
	return machine_nm;
}

public void setMachine_nm(String machine_nm) {
	this.machine_nm = machine_nm;
}

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getMachine_type_id() {
	return machine_type_id;
}

public void setMachine_type_id(String machine_type_id) {
	this.machine_type_id = machine_type_id;
}

public String getM_pm_rate() {
	return m_pm_rate;
}

public void setM_pm_rate(String m_pm_rate) {
	this.m_pm_rate = m_pm_rate;
}

public String getOperate_date() {
	return operate_date;
}

public void setOperate_date(String operate_date) {
	this.operate_date = operate_date;
}

public String getArea_id() {
	return area_id;
}

public void setArea_id(String area_id) {
	this.area_id = area_id;
}

}
