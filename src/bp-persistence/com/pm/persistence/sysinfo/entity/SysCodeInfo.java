package com.pm.persistence.sysinfo.entity;

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
 * 表sys_code_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sys_code_info")
public class SysCodeInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 3019480109774740100L;

  public SysCodeInfo clone() {
      SysCodeInfo o = null;
      try {
          o = (SysCodeInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * codeid
   */
  @ID
  @Column(name = "code_id", comment = "codeid")
  private String code_id;

  /**
   * code类型
   */
  @Column(name = "code_group_value", comment = "code类型")
  private String code_group_value;
  
  /**
   * code类型
   */
  @Transient
  @Column(name = "code_group_name", comment = "code类型")
  private String code_group_name;
  /**
   * code值
   */
  @Column(name = "code_value", comment = "code值")
  private String code_value;

  /**
   * code值名称
   */
  @Column(name = "code_nm", comment = "code值名称")
  private String code_nm;

  /**
   * 上级code值
   */
  @Column(name = "sub_code_group_value", comment = "下级code值")
  private String sub_code_group_value;
  
  /**
   * 上级code值
   */
  @Transient
  @Column(name = "sub_code_group_nm", comment = "下级code名称")
  private String sub_code_group_nm;

  /**
   * 序列号
   */
  @Column(name = "order_num", comment = "序列号")
  private String order_num;

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
   * code值名称
   */
  @Transient
  @Column(name = "code_nm2", comment = "code值名称")
  private String code_nm2;
  
  /**
   * code_value最大值
   */
  @Transient
  @Column(name = "max_code_value", comment = "code_value最大值")
  private String max_code_value;
  
  
  public String getCode_id() {
       return code_id;
  }

  public void setCode_id(String code_id) {
       this.code_id = code_id;
  }

  public String getCode_group_value() {
       return code_group_value;
  }

  public void setCode_group_value(String code_group_value) {
       this.code_group_value = code_group_value;
  }

  public String getCode_value() {
       return code_value;
  }

  public void setCode_value(String code_value) {
       this.code_value = code_value;
  }

  public String getCode_nm() {
       return code_nm;
  }

  public void setCode_nm(String code_nm) {
       this.code_nm = code_nm;
  }

  public String getOrder_num() {
       return order_num;
  }

  public void setOrder_num(String order_num) {
       this.order_num = order_num;
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

public String getCode_group_name() {
	return code_group_name;
}

public void setCode_group_name(String code_group_name) {
	this.code_group_name = code_group_name;
}

public String getSub_code_group_value() {
	return sub_code_group_value;
}

public void setSub_code_group_value(String sub_code_group_value) {
	this.sub_code_group_value = sub_code_group_value;
}

public String getCode_nm2() {
	return code_nm2;
}

public void setCode_nm2(String code_nm2) {
	this.code_nm2 = code_nm2;
}

public String getMax_code_value() {
	return max_code_value;
}

public void setMax_code_value(String max_code_value) {
	this.max_code_value = max_code_value;
}

public String getSub_code_group_nm() {
	return sub_code_group_nm;
}

public void setSub_code_group_nm(String sub_code_group_nm) {
	this.sub_code_group_nm = sub_code_group_nm;
}

}
