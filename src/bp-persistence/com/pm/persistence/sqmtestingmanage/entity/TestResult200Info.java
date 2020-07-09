package com.pm.persistence.sqmtestingmanage.entity;

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
 * 表test_result_200 的实体类
 * @author 刘镝
 */
@Entity(name = "test_result_200")
public class TestResult200Info extends BaseEntity implements Serializable, Cloneable {

  public TestResult200Info clone() {
      TestResult200Info o = null;
      try {
          o = (TestResult200Info) super.clone();
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
   * 生产线
   */
  @Column(name = "lineId", comment = "生产线")
  private String lineId;

  /**
   * 批次号
   */
  @Column(name = "lotnumver", comment = "批次号")
  private String lotnumver;

  /**
   * 产品Code
   */
  @Column(name = "produnctCode", comment = "产品Code")
  private String produnctCode;

  /**
   * 产品名称
   */
  @Column(name = "produnctName", comment = "产品名称")
  private String produnctName;

  /**
   * 选型
   */
  @Column(name = "productType", comment = "选型")
  private String productType;

  /**
   * 客户
   */
  @Column(name = "customName", comment = "客户")
  private String customName;

  /**
   * 是否有异常
   */
  @Column(name = "unusualYn", comment = "是否有异常")
  private String unusualYn;

  /**
   * 状态
   */
  @Column(name = "test_state", comment = "状态")
  private String test_state;
  /**
   * 罐装日期st
   */
  @Column(name = "filling_datest", comment = "罐装日期st")
  private String filling_datest;
  
  /**
   * 罐装日期ed
   */
  @Column(name = "filling_dateed", comment = "罐装日期ed")
  private String filling_dateed;

  /**
   * 罐装日期
   */
  @Column(name = "filling_date", comment = "罐装日期")
  private String filling_date;

  /**
   * 灌装机管线
   */
  @Column(name = "filling_pipeline", comment = "灌装机管线")
  private String filling_pipeline;

  /**
   * 进油管线
   */
  @Column(name = "oil_in_pipeline", comment = "进油管线")
  private String oil_in_pipeline;

  /**
   * 贴标1
   */
  @Column(name = "labelling1", comment = "贴标1")
  private String labelling1;

  /**
   * 贴标2
   */
  @Column(name = "labelling2", comment = "贴标2")
  private String labelling2;

  /**
   * 贴标3
   */
  @Column(name = "labelling3", comment = "贴标3")
  private String labelling3;

  /**
   * 贴标4
   */
  @Column(name = "labelling4", comment = "贴标4")
  private String labelling4;

  /**
   * 贴标5
   */
  @Column(name = "labelling5", comment = "贴标5")
  private String labelling5;

  /**
   * 灌装机重量
   */
  @Column(name = "filling_weight", comment = "灌装机重量")
  private String filling_weight;

  /**
   * 灌装机是否溅油
   */
  @Column(name = "filling_splash_yn", comment = "灌装机是否溅油")
  private String filling_splash_yn;

  /**
   * 灌装机前压力表压力
   */
  @Column(name = "filling_front_pressure", comment = "灌装机前压力表压力")
  private String filling_front_pressure;

  /**
   * 灌装机后压力表压力
   */
  @Column(name = "filling_back_pressure", comment = "灌装机后压力表压力")
  private String filling_back_pressure;

  /**
   * 灌装机首样送检
   */
  @Column(name = "filling_first_article", comment = "灌装机首样送检")
  private String filling_first_article;

  /**
   * 灌装机防尘盖（白、ZIC、SCL32、潍柴）
   */
  @Column(name = "filling_dust_yn", comment = "灌装机防尘盖（白、ZIC、SCL32、潍柴）")
  private String filling_dust_yn;

  /**
   * 复检称重量
   */
  @Column(name = "recheck_weight", comment = "复检称重量")
  private String recheck_weight;

  /**
   * 复检称IBC供应商选择
   */
  @Column(name = "reinspection_supplier_selection", comment = "复检称IBC供应商选择")
  private String reinspection_supplier_selection;

  /**
   * 复检称IBC铅封
   */
  @Column(name = "reinspection_IBC_leadseal", comment = "复检称IBC铅封")
  private String reinspection_IBC_leadseal;

  /**
   * 第一箱扫码
   */
  @Column(name = "scan_first_case", comment = "第一箱扫码")
  private String scan_first_case;

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

  /**
   * 璐存爣1
   */
  @Column(name = "labelling11", comment = "璐存爣1")
  private String labelling11;

  /**
   * 璐存爣2
   */
  @Column(name = "labelling21", comment = "璐存爣2")
  private String labelling21;

  /**
   * 璐存爣3
   */
  @Column(name = "labelling31", comment = "璐存爣3")
  private String labelling31;

  /**
   * 璐存爣4
   */
  @Column(name = "labelling41", comment = "璐存爣4")
  private String labelling41;

  /**
   * 璐存爣5
   */
  @Column(name = "labelling51", comment = "璐存爣5")
  private String labelling51;

  /**
   * 结束物流码
   */
  @Column(name = "pack_code", comment = "结束物流码")
  private String pack_code;
  
  /**
   * 开始物流码
   */
  @Column(name = "start_pack_code", comment = "开始物流码")
  private String start_pack_code;

  /**
   * 异常备注
   */
  @Column(name = "abnormal_remarks", comment = "异常备注")
  private String abnormal_remarks;
  
public String getAbnormal_remarks() {
	return abnormal_remarks;
}

public void setAbnormal_remarks(String abnormal_remarks) {
	this.abnormal_remarks = abnormal_remarks;
}
  
  public String getStart_pack_code() {
	return start_pack_code;
}

public void setStart_pack_code(String start_pack_code) {
	this.start_pack_code = start_pack_code;
}

public String getPack_code() {
	return pack_code;
}

public void setPack_code(String pack_code) {
	this.pack_code = pack_code;
}
  
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

  public String getLotnumver() {
       return lotnumver;
  }

  public void setLotnumver(String lotnumver) {
       this.lotnumver = lotnumver;
  }

  public String getProdunctCode() {
       return produnctCode;
  }

  public void setProdunctCode(String produnctCode) {
       this.produnctCode = produnctCode;
  }

  public String getProdunctName() {
       return produnctName;
  }

  public void setProdunctName(String produnctName) {
       this.produnctName = produnctName;
  }

  public String getProductType() {
       return productType;
  }

  public void setProductType(String productType) {
       this.productType = productType;
  }

  public String getCustomName() {
       return customName;
  }

  public void setCustomName(String customName) {
       this.customName = customName;
  }

  public String getUnusualYn() {
       return unusualYn;
  }

  public void setUnusualYn(String unusualYn) {
       this.unusualYn = unusualYn;
  }

  public String getTest_state() {
       return test_state;
  }

  public void setTest_state(String test_state) {
       this.test_state = test_state;
  }
  public void setFilling_datest(String filling_datest) {
      this.filling_datest = filling_datest;
 }
 public String getFilling_datest() {
     return filling_datest;
}

public void setFilling_dateed(String filling_dateed) {
     this.filling_dateed = filling_dateed;
}
public String getFilling_dateed() {
    return filling_dateed;
}
  public String getFilling_date() {
       return filling_date;
  }

  public void setFilling_date(String filling_date) {
       this.filling_date = filling_date;
  }

  public String getFilling_pipeline() {
       return filling_pipeline;
  }

  public void setFilling_pipeline(String filling_pipeline) {
       this.filling_pipeline = filling_pipeline;
  }

  public String getOil_in_pipeline() {
       return oil_in_pipeline;
  }

  public void setOil_in_pipeline(String oil_in_pipeline) {
       this.oil_in_pipeline = oil_in_pipeline;
  }

  public String getLabelling1() {
       return labelling1;
  }

  public void setLabelling1(String labelling1) {
       this.labelling1 = labelling1;
  }

  public String getLabelling2() {
       return labelling2;
  }

  public void setLabelling2(String labelling2) {
       this.labelling2 = labelling2;
  }

  public String getLabelling3() {
       return labelling3;
  }

  public void setLabelling3(String labelling3) {
       this.labelling3 = labelling3;
  }

  public String getLabelling4() {
       return labelling4;
  }

  public void setLabelling4(String labelling4) {
       this.labelling4 = labelling4;
  }

  public String getLabelling5() {
       return labelling5;
  }

  public void setLabelling5(String labelling5) {
       this.labelling5 = labelling5;
  }

  public String getFilling_weight() {
       return filling_weight;
  }

  public void setFilling_weight(String filling_weight) {
       this.filling_weight = filling_weight;
  }

  public String getFilling_splash_yn() {
       return filling_splash_yn;
  }

  public void setFilling_splash_yn(String filling_splash_yn) {
       this.filling_splash_yn = filling_splash_yn;
  }

  public String getFilling_front_pressure() {
       return filling_front_pressure;
  }

  public void setFilling_front_pressure(String filling_front_pressure) {
       this.filling_front_pressure = filling_front_pressure;
  }

  public String getFilling_back_pressure() {
       return filling_back_pressure;
  }

  public void setFilling_back_pressure(String filling_back_pressure) {
       this.filling_back_pressure = filling_back_pressure;
  }

  public String getFilling_first_article() {
       return filling_first_article;
  }

  public void setFilling_first_article(String filling_first_article) {
       this.filling_first_article = filling_first_article;
  }

  public String getFilling_dust_yn() {
       return filling_dust_yn;
  }

  public void setFilling_dust_yn(String filling_dust_yn) {
       this.filling_dust_yn = filling_dust_yn;
  }

  public String getRecheck_weight() {
       return recheck_weight;
  }

  public void setRecheck_weight(String recheck_weight) {
       this.recheck_weight = recheck_weight;
  }

  public String getReinspection_supplier_selection() {
       return reinspection_supplier_selection;
  }

  public void setReinspection_supplier_selection(String reinspection_supplier_selection) {
       this.reinspection_supplier_selection = reinspection_supplier_selection;
  }

  public String getReinspection_IBC_leadseal() {
       return reinspection_IBC_leadseal;
  }

  public void setReinspection_IBC_leadseal(String reinspection_IBC_leadseal) {
       this.reinspection_IBC_leadseal = reinspection_IBC_leadseal;
  }

  public String getScan_first_case() {
       return scan_first_case;
  }

  public void setScan_first_case(String scan_first_case) {
       this.scan_first_case = scan_first_case;
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

  public String getLabelling11() {
       return labelling11;
  }

  public void setLabelling11(String labelling11) {
       this.labelling11 = labelling11;
  }

  public String getLabelling21() {
       return labelling21;
  }

  public void setLabelling21(String labelling21) {
       this.labelling21 = labelling21;
  }

  public String getLabelling31() {
       return labelling31;
  }

  public void setLabelling31(String labelling31) {
       this.labelling31 = labelling31;
  }

  public String getLabelling41() {
       return labelling41;
  }

  public void setLabelling41(String labelling41) {
       this.labelling41 = labelling41;
  }

  public String getLabelling51() {
       return labelling51;
  }

  public void setLabelling51(String labelling51) {
       this.labelling51 = labelling51;
  }
}
