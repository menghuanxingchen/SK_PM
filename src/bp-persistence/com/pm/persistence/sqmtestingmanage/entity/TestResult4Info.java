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
 * 表test_result_4 的实体类
 * @author 刘镝
 */
@Entity(name = "test_result_4")
public class TestResult4Info extends BaseEntity implements Serializable, Cloneable {

  public TestResult4Info clone() {
      TestResult4Info o = null;
      try {
          o = (TestResult4Info) super.clone();
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
   * 罐装日期
   */
  @Column(name = "filling_date", comment = "罐装日期")
  private String filling_date;
  
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
   * 灌装机包材是否合格
   */
  @Column(name = "filling_pakage_qualified", comment = "灌装机包材是否合格")
  private String filling_pakage_qualified;

  /**
   *  螺纹口喷码机（WC专用）油品规格
   */
  @Column(name = "thread_mouth_wc", comment = " 螺纹口喷码机（WC专用）油品规格")
  private String thread_mouth_wc;

  /**
   * 照相机产品CODE
   */
  @Column(name = "camera_product_code", comment = "照相机产品CODE")
  private String camera_product_code;

  /**
   * 铝箔剔除机测试正常
   */
  @Column(name = "aluminum_foil_stripper", comment = "铝箔剔除机测试正常")
  private String aluminum_foil_stripper;

  /**
   * 封口机功率
   */
  @Column(name = "powerof_sealing_machine", comment = "封口机功率")
  private String powerof_sealing_machine;

  /**
   * 瓶身喷码机日期
   */
  @Column(name = "bottlebody_printer_date", comment = "瓶身喷码机日期")
  private String bottlebody_printer_date;

  /**
   * 瓶身喷码机批号
   */
  @Column(name = "bottlebody_printer_lot", comment = "瓶身喷码机批号")
  private String bottlebody_printer_lot;

  /**
   * 瓶身喷码机清晰可见
   */
  @Column(name = "bottlebody_distinct", comment = "瓶身喷码机清晰可见")
  private String bottlebody_distinct;

  /**
   * 开箱机纸箱CODE
   */
  @Column(name = "unpacker_code", comment = "开箱机纸箱CODE")
  private String unpacker_code;

  /**
   *  开箱机胶带选择（列表）
   */
  @Column(name = "unpacker_list", comment = " 开箱机胶带选择（列表）")
  private String unpacker_list;

  /**
   * 整箱剔除机箱重配方
   */
  @Column(name = "weight_removal_formula", comment = "整箱剔除机箱重配方")
  private String weight_removal_formula;

  /**
   * 整箱剔除机是否能剔除
   */
  @Column(name = "ehether_cullercan_cull", comment = "整箱剔除机是否能剔除")
  private String ehether_cullercan_cull;

  /**
   * 整箱剔除机外箱喷码日期
   */
  @Column(name = "excludingcode_spraying_date", comment = "整箱剔除机外箱喷码日期")
  private String excludingcode_spraying_date;

  /**
   * 整箱剔除机批号
   */
  @Column(name = "stripping_machinebatch_number", comment = "整箱剔除机批号")
  private String stripping_machinebatch_number;

  /**
   * 整箱剔除机小标检查（1L、4L）
   */
  @Column(name = "Inspection_stripping_machine", comment = "整箱剔除机小标检查（1L、4L）")
  private String Inspection_stripping_machine;

  /**
   * 整箱剔除机产品规格确认
   */
  @Column(name = "product_specification_confirmation", comment = "整箱剔除机产品规格确认")
  private String product_specification_confirmation;

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
   * 鐞嗘《鏈虹姸鎬�
   */
  @Column(name = "oilbarrel", comment = "鐞嗘《鏈虹姸鎬�")
  private String oilbarrel;

  /**
   * 浜у搧Code
   */
  @Column(name = "oilbarrelCode", comment = "浜у搧Code")
  private String oilbarrelCode;

  /**
   * 澶嶆绉伴噸閲�
   */
  @Column(name = "recheck_weight", comment = "澶嶆绉伴噸閲�")
  private String recheck_weight;

  /**
   * 澶嶆绉癐BC渚涘簲鍟嗛�夋嫨
   */
  @Column(name = "reinspection_supplier_selection", comment = "澶嶆绉癐BC渚涘簲鍟嗛�夋嫨")
  private String reinspection_supplier_selection;

  /**
   * 澶嶆绉癐BC閾呭皝
   */
  @Column(name = "reinspection_IBC_leadseal", comment = "澶嶆绉癐BC閾呭皝")
  private String reinspection_IBC_leadseal;
  
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

  public String getFilling_date() {
       return filling_date;
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

  public String getFilling_pakage_qualified() {
       return filling_pakage_qualified;
  }

  public void setFilling_pakage_qualified(String filling_pakage_qualified) {
       this.filling_pakage_qualified = filling_pakage_qualified;
  }

  public String getThread_mouth_wc() {
       return thread_mouth_wc;
  }

  public void setThread_mouth_wc(String thread_mouth_wc) {
       this.thread_mouth_wc = thread_mouth_wc;
  }

  public String getCamera_product_code() {
       return camera_product_code;
  }

  public void setCamera_product_code(String camera_product_code) {
       this.camera_product_code = camera_product_code;
  }

  public String getAluminum_foil_stripper() {
       return aluminum_foil_stripper;
  }

  public void setAluminum_foil_stripper(String aluminum_foil_stripper) {
       this.aluminum_foil_stripper = aluminum_foil_stripper;
  }

  public String getPowerof_sealing_machine() {
       return powerof_sealing_machine;
  }

  public void setPowerof_sealing_machine(String powerof_sealing_machine) {
       this.powerof_sealing_machine = powerof_sealing_machine;
  }

  public String getBottlebody_printer_date() {
       return bottlebody_printer_date;
  }

  public void setBottlebody_printer_date(String bottlebody_printer_date) {
       this.bottlebody_printer_date = bottlebody_printer_date;
  }

  public String getBottlebody_printer_lot() {
       return bottlebody_printer_lot;
  }

  public void setBottlebody_printer_lot(String bottlebody_printer_lot) {
       this.bottlebody_printer_lot = bottlebody_printer_lot;
  }

  public String getBottlebody_distinct() {
       return bottlebody_distinct;
  }

  public void setBottlebody_distinct(String bottlebody_distinct) {
       this.bottlebody_distinct = bottlebody_distinct;
  }

  public String getUnpacker_code() {
       return unpacker_code;
  }

  public void setUnpacker_code(String unpacker_code) {
       this.unpacker_code = unpacker_code;
  }

  public String getUnpacker_list() {
       return unpacker_list;
  }

  public void setUnpacker_list(String unpacker_list) {
       this.unpacker_list = unpacker_list;
  }

  public String getWeight_removal_formula() {
       return weight_removal_formula;
  }

  public void setWeight_removal_formula(String weight_removal_formula) {
       this.weight_removal_formula = weight_removal_formula;
  }

  public String getEhether_cullercan_cull() {
       return ehether_cullercan_cull;
  }

  public void setEhether_cullercan_cull(String ehether_cullercan_cull) {
       this.ehether_cullercan_cull = ehether_cullercan_cull;
  }

  public String getExcludingcode_spraying_date() {
       return excludingcode_spraying_date;
  }

  public void setExcludingcode_spraying_date(String excludingcode_spraying_date) {
       this.excludingcode_spraying_date = excludingcode_spraying_date;
  }

  public String getStripping_machinebatch_number() {
       return stripping_machinebatch_number;
  }

  public void setStripping_machinebatch_number(String stripping_machinebatch_number) {
       this.stripping_machinebatch_number = stripping_machinebatch_number;
  }

  public String getInspection_stripping_machine() {
       return Inspection_stripping_machine;
  }

  public void setInspection_stripping_machine(String Inspection_stripping_machine) {
       this.Inspection_stripping_machine = Inspection_stripping_machine;
  }

  public String getProduct_specification_confirmation() {
       return product_specification_confirmation;
  }

  public void setProduct_specification_confirmation(String product_specification_confirmation) {
       this.product_specification_confirmation = product_specification_confirmation;
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

  public String getOilbarrel() {
       return oilbarrel;
  }

  public void setOilbarrel(String oilbarrel) {
       this.oilbarrel = oilbarrel;
  }

  public String getOilbarrelCode() {
       return oilbarrelCode;
  }

  public void setOilbarrelCode(String oilbarrelCode) {
       this.oilbarrelCode = oilbarrelCode;
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
}
