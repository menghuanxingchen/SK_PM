package com.pm.persistence.workout.entity;

import java.io.Serializable;
import java.util.Date;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表work_out_info 的实体类
 * @author 刘镝
 */
@Entity(name = "work_out_info")
public class WorkOutInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 5795186162289562464L;

  public WorkOutInfo clone() {
      WorkOutInfo o = null;
      try {
          o = (WorkOutInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 出勤表ID
   */
  @ID
  @Column(name = "work_out_id", comment = "出勤表ID")
  private String work_out_id;

  /**
   * 出勤表名称
   */
  @Column(name = "work_out_nm", comment = "出勤表名称")
  private String work_out_nm;

  /**
   * 出勤表年月
   */
  @Column(name = "work_out_ym", comment = "出勤表年月")
  private String work_out_ym;

  /**
   * 出勤表加班时间/小时
   */
  @Column(name = "overtime_hour", comment = "出勤表加班时间/小时")
  private String overtime_hour;

  /**
   * 餐位/小时
   */
  @Column(name = "meal_hour", comment = "餐位/小时")
  private String meal_hour;

  /**
   * 餐位/小时
   */
  @Column(name = "work_hour", comment = "出勤/天数")
  private String work_hour;

  
  public String getWork_hour() {
	return work_hour;
}

public void setWork_hour(String work_hour) {
	this.work_hour = work_hour;
}

/**
   * 固定人力费
   */
  @Column(name = "human_cost", comment = "固定人力费")
  private String human_cost;

  /**
   * 餐补
   */
  @Column(name = "meal_cost", comment = "餐补")
  private String meal_cost;
  
  /**
   * 机械费
   */
  @Column(name = "mach_cost", comment = "机械费")
  private String mach_cost;

  /**
   * 总费用
   */
  @Transient
  @Column(name = "total_amt", comment = "总费用")
  private String total_amt;

/**
   * 首次创建userid
   */
  @Column(name = "create_id", comment = "首次创建userid")
  private String create_id;

/**
   * 首次创建时间
   */
  @Column(name = "create_time", comment = "首次创建时间")
  private Date create_time;

  /**
   * 修改userid
   */
  @Column(name = "update_id", comment = "修改userid")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private Date update_time;
  
  /**
   * 开始年月
   */
  @Transient
  @Column(name = "start_dt", comment = "开始年月")
  private String start_dt;
  
  /**
   * 结束年月
   */
  @Transient
  @Column(name = "end_dt", comment = "结束年月")
  private String end_dt;
  
  /**
   * 结算时间
   */
  @Column(name = "pay_time", comment = "修改时间")
  private Date pay_time;
  
  
  public Date getPay_time() {
	return pay_time;
}

public void setPay_time(Date pay_time) {
	this.pay_time = pay_time;
}

/**
   * 结算状态_code
   */
  @Column(name = "pay_yn", comment = "结算状态")
  private String pay_yn;
 
  /**
   * 结算状态_value
   * 已支付 未支付
   */
  @Transient
  @Column(name = "pay_nm", comment = "结算状态value")
  private String pay_nm;
 
  /**
   * 按钮状态
   */
  @Transient
  @Column(name = "flag", comment = "按钮状态")
  private String flag;
  
  public String getFlag() {
	return flag;
}

public void setFlag(String flag) {
	this.flag = flag;
}

public String getEditYnFlag() {
	return editYnFlag;
}

public void setEditYnFlag(String editYnFlag) {
	this.editYnFlag = editYnFlag;
}

public String getDeleteYnFlag() {
	return deleteYnFlag;
}

public void setDeleteYnFlag(String deleteYnFlag) {
	this.deleteYnFlag = deleteYnFlag;
}

/**
   * 按钮修改使用状态
   */
  @Transient
  @Column(name = "editYnFlag", comment = "修改状态")
  private String editYnFlag;
 
  /**
   * 删除按钮使用状态
   */
  @Transient
  @Column(name = "deleteYnFlag", comment = "删除状态")
  private String deleteYnFlag;
  
  
  public String getPay_yn() {
	return pay_yn;
}

public void setPay_yn(String pay_yn) {
	this.pay_yn = pay_yn;
}

public String getPay_nm() {
	return pay_nm;
}

public void setPay_nm(String pay_nm) {
	this.pay_nm = pay_nm;
}

public String getClosing_yn() {
	return pay_yn;
}

public String getStart_dt() {
	return start_dt;
}

public void setStart_dt(String start_dt) {
	this.start_dt = start_dt;
}

public String getEnd_dt() {
	return end_dt;
}

public void setEnd_dt(String end_dt) {
	this.end_dt = end_dt;
}

public String getWork_out_id() {
       return work_out_id;
  }

  public void setWork_out_id(String work_out_id) {
       this.work_out_id = work_out_id;
  }

  public String getWork_out_nm() {
       return work_out_nm;
  }

  public void setWork_out_nm(String work_out_nm) {
       this.work_out_nm = work_out_nm;
  }

  public String getWork_out_ym() {
       return work_out_ym;
  }

  public void setWork_out_ym(String work_out_ym) {
       this.work_out_ym = work_out_ym;
  }

  public String getOvertime_hour() {
       return overtime_hour;
  }

  public void setOvertime_hour(String overtime_hour) {
       this.overtime_hour = overtime_hour;
  }

  public String getMeal_hour() {
       return meal_hour;
  }

  public void setMeal_hour(String meal_hour) {
       this.meal_hour = meal_hour;
  }

  public String getHuman_cost() {
       return human_cost;
  }

  public void setHuman_cost(String human_cost) {
       this.human_cost = human_cost;
  }

  public String getmeal_cost() {
       return meal_cost;
  }

  public void setmeal_cost(String meal_cost) {
       this.meal_cost = meal_cost;
  }

  public String getCreate_id() {
       return create_id;
  }

  public void setCreate_id(String create_id) {
       this.create_id = create_id;
  }

  public Date getCreate_time() {
       return create_time;
  }

  public void setCreate_time(Date create_time) {
       this.create_time = create_time;
  }

  public String getUpdate_id() {
       return update_id;
  }

  public void setUpdate_id(String update_id) {
       this.update_id = update_id;
  }

  public Date getUpdate_time() {
       return update_time;
  }

  public void setUpdate_time(Date update_time) {
       this.update_time = update_time;
  }
  
  public String getMach_cost() {
	return mach_cost;
  }

  public void setMach_cost(String mach_cost) {
	this.mach_cost = mach_cost;
  }
  
  public String getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}

}
