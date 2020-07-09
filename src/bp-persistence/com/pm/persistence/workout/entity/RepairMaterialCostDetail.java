package com.pm.persistence.workout.entity;

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
 * 表rapair_material_cost_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "rapair_material_cost_detail")
public class RepairMaterialCostDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public RepairMaterialCostDetail clone() {
      RepairMaterialCostDetail o = null;
      try {
          o = (RepairMaterialCostDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 考勤统计详情id
   */
  @ID
  @Column(name = "rapair_material_cost_id", comment = "考勤统计详情id")
  private String rapair_material_cost_id;

  /**
   * 
   */
  @Column(name = "work_out_id", comment = "")
  private String work_out_id;

  /**
   * 完成日期
   */
  @Column(name = "confirm_date", comment = "完成日期")
  private String confirm_date;

  /**
   * 费用类型
   */
  @Column(name = "cost_type", comment = "费用类型")
  private String cost_type;

  /**
   * 费用名称
   */
  @Column(name = "cost_nm", comment = "费用名称")
  private String cost_nm;

  /**
   * 费用
   */
  @Column(name = "amount", comment = "费用")
  private String amount;

  /**
   * 单位
   */
  @Column(name = "material_cost_detail", comment = "单位")
  private String material_cost_detail;

  /**
   * 部门code
   */
  @Column(name = "dept_code", comment = "部门code")
  private String dept_code;

  /**
   * 部门名
   */
  @Column(name = "dept_nm", comment = "部门名")
  private String dept_nm;

  /**
   * 备注
   */
  @Column(name = "remark", comment = "备注")
  private String remark;

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

  public String getRapair_material_cost_id() {
       return rapair_material_cost_id;
  }

  public void setRapair_material_cost_id(String rapair_material_cost_id) {
       this.rapair_material_cost_id = rapair_material_cost_id;
  }

  public String getWork_out_id() {
       return work_out_id;
  }

  public void setWork_out_id(String work_out_id) {
       this.work_out_id = work_out_id;
  }

  public String getConfirm_date() {
       return confirm_date;
  }

  public void setConfirm_date(String confirm_date) {
       this.confirm_date = confirm_date;
  }

  public String getCost_type() {
       return cost_type;
  }

  public void setCost_type(String cost_type) {
       this.cost_type = cost_type;
  }

  public String getCost_nm() {
       return cost_nm;
  }

  public void setCost_nm(String cost_nm) {
       this.cost_nm = cost_nm;
  }

  public String getAmount() {
       return amount;
  }

  public void setAmount(String amount) {
       this.amount = amount;
  }

  public String getMaterial_cost_detail() {
       return material_cost_detail;
  }

  public void setMaterial_cost_detail(String material_cost_detail) {
       this.material_cost_detail = material_cost_detail;
  }

  public String getDept_code() {
       return dept_code;
  }

  public void setDept_code(String dept_code) {
       this.dept_code = dept_code;
  }

  public String getDept_nm() {
       return dept_nm;
  }

  public void setDept_nm(String dept_nm) {
       this.dept_nm = dept_nm;
  }

  public String getRemark() {
       return remark;
  }

  public void setRemark(String remark) {
       this.remark = remark;
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

}
