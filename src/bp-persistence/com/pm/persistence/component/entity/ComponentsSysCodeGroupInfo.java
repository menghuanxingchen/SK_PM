package com.pm.persistence.component.entity;

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
 * 表sys_code_group_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sys_code_group_info")
public class ComponentsSysCodeGroupInfo extends BaseEntity implements Serializable, Cloneable {

  public ComponentsSysCodeGroupInfo clone() {
      ComponentsSysCodeGroupInfo o = null;
      try {
          o = (ComponentsSysCodeGroupInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * codeid
   */
  @ID
  @Column(name = "code_group_id", comment = "codeid")
  private String code_group_id;

  /**
   * code类型
   */
  @Column(name = "code_group_value", comment = "code类型")
  private String code_group_value;

  /**
   * code类型名称
   */
  @Column(name = "code_group_name", comment = "code类型名称")
  private String code_group_name;

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

  public String getCode_group_id() {
       return code_group_id;
  }

  public void setCode_group_id(String code_group_id) {
       this.code_group_id = code_group_id;
  }

  public String getCode_group_value() {
       return code_group_value;
  }

  public void setCode_group_value(String code_group_value) {
       this.code_group_value = code_group_value;
  }

  public String getCode_group_name() {
       return code_group_name;
  }

  public void setCode_group_name(String code_group_name) {
       this.code_group_name = code_group_name;
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

}
