package com.pm.persistence.movinginfo.entity;

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
 * 表moving_goods_info 的实体类
 * @author 刘镝
 */
@Entity(name = "moving_goods_info")
public class MovingGoodsInfo extends BaseEntity implements Serializable, Cloneable {

  public MovingGoodsInfo clone() {
      MovingGoodsInfo o = null;
      try {
          o = (MovingGoodsInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "id", comment = "")
  private String id;

  /**
   * 
   */
  @Column(name = "minNo", comment = "")
  private String minNo;

  /**
   * 
   */
  @Column(name = "goodsName", comment = "")
  private String goodsName;

  /**
   * 
   */
  @Column(name = "goodsCount", comment = "")
  private String goodsCount;

  /**
   * 
   */
  @Column(name = "goodsRemark", comment = "")
  private String goodsRemark;

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
   * 
   */
  @Column(name = "user_yn", comment = "")
  private String user_yn;

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getMinNo() {
       return minNo;
  }

  public void setMinNo(String minNo) {
       this.minNo = minNo;
  }

  public String getGoodsName() {
       return goodsName;
  }

  public void setGoodsName(String goodsName) {
       this.goodsName = goodsName;
  }

  public String getGoodsCount() {
       return goodsCount;
  }

  public void setGoodsCount(String goodsCount) {
       this.goodsCount = goodsCount;
  }

  public String getGoodsRemark() {
       return goodsRemark;
  }

  public void setGoodsRemark(String goodsRemark) {
       this.goodsRemark = goodsRemark;
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

  public String getUser_yn() {
       return user_yn;
  }

  public void setUser_yn(String user_yn) {
       this.user_yn = user_yn;
  }

}
