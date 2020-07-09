package com.pm.persistence.Sqmmanage.entity;

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
 * 表line_product_info 的实体类
 * @author 刘镝
 */
@Entity(name = "line_product_info")
public class LineProductInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public LineProductInfo clone() {
      LineProductInfo o = null;
      try {
          o = (LineProductInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * ID主键
   */
  @ID
  @Column(name = "id", comment = "ID主键")
  private String id;
  
  /**
   * 生产线ID
   */

  @Column(name = "lineId", comment = "生产线ID")
  private String lineId;

  /**
   * 生产线name
   */
  @Transient
  @Column(name = "lineName", comment = "生产线name")
  private String lineName;
  
  /**
   * 设备id
   */

  @Column(name = "pot_id", comment = "设备id")
  private String pot_id;
  
  

  /**
   * 检测间隔时间
   */
  @Column(name = "intervalDate", comment = "检测间隔时间")
  private String intervalDate;

  /**
   * 检测持续时间
   */
  @Column(name = "continueDate", comment = "检测持续时间")
  private String continueDate;

  /**
   * 故障时间
   */
  @Column(name = "breakdownDate", comment = "故障时间")
  private String breakdownDate;

  
  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
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

  
  public String getId() {
      return id;
 }

 public void setId(String id) {
      this.id = id;
 }
 
  public String getLineId() {
       return lineId;
  }

  public void setLineId(String lineId) {
       this.lineId = lineId;
  }

  public String getLineName() {
      return lineName;
 }

 public void setLineName(String lineName) {
      this.lineName = lineName;
 }
 
  public String getPot_id() {
      return pot_id;
 }

 public void setPot_id(String pot_id) {
      this.pot_id = pot_id;
 }


  public String getIntervalDate() {
       return intervalDate;
  }

  public void setIntervalDate(String intervalDate) {
       this.intervalDate = intervalDate;
  }

  public String getContinueDate() {
       return continueDate;
  }

  public void setContinueDate(String continueDate) {
       this.continueDate = continueDate;
  }

  public String getBreakdownDate() {
       return breakdownDate;
  }

  public void setBreakdownDate(String breakdownDate) {
       this.breakdownDate = breakdownDate;
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

	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
