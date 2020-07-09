package com.sqm.testoveryn.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_over_yn 的实体类
 * @author 刘镝
 */
@Entity(name = "test_over_yn")
public class TestOverYn extends BaseEntity implements Serializable, Cloneable {

  public TestOverYn clone() {
      TestOverYn o = null;
      try {
          o = (TestOverYn) super.clone();
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
   * 灌装计划id
   */
  @Column(name = "plan_id", comment = "灌装计划id")
  private String plan_id;

  /**
   * 检测员id
   */
  @Column(name = "create_id", comment = "检测员id")
  private String create_id;

  /**
   * 检测次数
   */
  @Column(name = "test_num", comment = "检测次数")
  private int test_num;

  /**
   * 本轮检测是否结束
   */
  @Column(name = "over_yn", comment = "本轮检测是否结束")
  private String over_yn;

  /**
   * 创建时间
   */
  @Column(name = "create_time", comment = "创建时间")
  private String create_time;

  
  public String getCreate_time() {
	return create_time;
}

public void setCreate_time(String create_time) {
	this.create_time = create_time;
}

public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getPlan_id() {
       return plan_id;
  }

  public void setPlan_id(String plan_id) {
       this.plan_id = plan_id;
  }

  public String getCreate_id() {
       return create_id;
  }

  public void setCreate_id(String create_id) {
       this.create_id = create_id;
  }

  public int getTest_num() {
       return test_num;
  }

  public void setTest_num(int test_num) {
       this.test_num = test_num;
  }

  public String getOver_yn() {
       return over_yn;
  }

  public void setOver_yn(String over_yn) {
       this.over_yn = over_yn;
  }

}
