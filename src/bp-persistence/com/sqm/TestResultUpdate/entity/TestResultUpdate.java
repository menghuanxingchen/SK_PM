package com.sqm.TestResultUpdate.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_result_update 的实体类
 * @author 刘镝
 */
@Entity(name = "test_result_update")
public class TestResultUpdate extends BaseEntity implements Serializable, Cloneable {

  public TestResultUpdate clone() {
      TestResultUpdate o = null;
      try {
          o = (TestResultUpdate) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 主键
   */
  @ID
  @Column(name = "id", comment = "主键")
  private String id;

  /**
   * 修改的检测记录id
   */
  @Column(name = "rid", comment = "修改的检测记录id")
  private String rid;

  /**
   * 修改原因
   */
  @Column(name = "update_cause", comment = "修改原因")
  private String update_cause;

  /**
   * 检测结果
   */
  @Column(name = "test_result", comment = "检测结果")
  private String test_result;

  /**
   * 创建userid
   */
  @Column(name = "create_id", comment = "创建userid")
  private String create_id;

  /**
   * 原检测记录创建时间
   */
  @Column(name = "create_time", comment = "原检测记录创建时间")
  private String create_time;

  /**
   * 本数据创建时间
   */
  @Column(name = "create_time2", comment = "本数据创建时间")
  private String create_time2;
  
  /**
   * 检测次数
   */
  @Column(name = "test_num", comment = "检测次数")
  private Integer test_num;

  /**
   * 修改次数
   */
  @Column(name = "update_num", comment = "修改次数")
  private Integer update_num;

  /**
   * 更新时间
   */
  @Column(name = "update_time", comment = "更新时间")
  private String update_time;
  
  

  public String getCreate_time2() {
	return create_time2;
}

public void setCreate_time2(String create_time2) {
	this.create_time2 = create_time2;
}

public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getRid() {
       return rid;
  }

  public void setRid(String rid) {
       this.rid = rid;
  }

  public String getUpdate_cause() {
       return update_cause;
  }

  public void setUpdate_cause(String update_cause) {
       this.update_cause = update_cause;
  }

  public String getTest_result() {
       return test_result;
  }

  public void setTest_result(String test_result) {
       this.test_result = test_result;
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

  public Integer getTest_num() {
       return test_num;
  }

  public void setTest_num(Integer test_num) {
       this.test_num = test_num;
  }

  public Integer getUpdate_num() {
       return update_num;
  }

  public void setUpdate_num(Integer update_num) {
       this.update_num = update_num;
  }

  public String getUpdate_time() {
       return update_time;
  }

  public void setUpdate_time(String update_time) {
       this.update_time = update_time;
  }

}
