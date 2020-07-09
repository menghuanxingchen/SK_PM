package com.sqm.sqmpushinfo.entity;

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
 * 表sqm_push_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sqm_push_info")
public class SqmPushInfo extends BaseEntity implements Serializable, Cloneable {

  public SqmPushInfo clone() {
      SqmPushInfo o = null;
      try {
          o = (SqmPushInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 主键id
   */
  @ID
  @Column(name = "push_id", comment = "主键id")
  private String push_id;

  /**
   * 推送内容
   */
  @Column(name = "push_msg", comment = "推送内容")
  private String push_msg;

  /**
   * 推送时间
   */
  @Column(name = "push_time", comment = "推送时间")
  private String push_time;

  /**
   * 推送类型
   */
  @Column(name = "push_type", comment = "推送类型")
  private String push_type;
  
  /**
   * 推送给谁
   */
  @Column(name = "push_to", comment = "推送给谁")
  private String push_to;
  
  /**
   * 推送来源（如：检修记录id）
   */
  @Column(name = "push_source", comment = "推送来源（如：检修记录id）")
  private String push_source;
  
  /**
   * 是否修改yn
   */
  @Column(name = "update_yn", comment = "是否修改yn")
  private String update_yn;
  
  /**
   * 推送类型nm
   */
  @Transient
  @Column(name = "push_type_name", comment = "推送类型nm")
  private String push_type_name;
  
  /**
   * 创建人
   */
  @Column(name = "create_id", comment = "创建人")
  private String create_id;

  public String getPush_type_name() {
	return push_type_name;
}

public void setPush_type_name(String push_type_name) {
	this.push_type_name = push_type_name;
}

public String getCreate_id() {
	return create_id;
}

public void setCreate_id(String create_id) {
	this.create_id = create_id;
}

public String getUpdate_yn() {
	return update_yn;
}

public void setUpdate_yn(String update_yn) {
	this.update_yn = update_yn;
}

public String getPush_source() {
	return push_source;
}

public void setPush_source(String push_source) {
	this.push_source = push_source;
}

public String getPush_to() {
	return push_to;
}

public void setPush_to(String push_to) {
	this.push_to = push_to;
}

public String getPush_id() {
       return push_id;
  }

  public void setPush_id(String push_id) {
       this.push_id = push_id;
  }

  public String getPush_msg() {
       return push_msg;
  }

  public void setPush_msg(String push_msg) {
       this.push_msg = push_msg;
  }

  public String getPush_time() {
       return push_time;
  }

  public void setPush_time(String push_time) {
       this.push_time = push_time;
  }

  public String getPush_type() {
       return push_type;
  }

  public void setPush_type(String push_type) {
       this.push_type = push_type;
  }

}
