package com.pm.persistence.Sqmmanage.entity;

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
 * 表sample_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sample_info")
public class SampleInfo extends BaseEntity implements Serializable, Cloneable {

  public SampleInfo clone() {
      SampleInfo o = null;
      try {
          o = (SampleInfo) super.clone();
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
  @Column(name = "PKG", comment = "")
  private String PKG;
  /**
   * 
   */
  @Column(name = "isNew", comment = "")
  private String isNew;
  /**
   * 
   */
  @Column(name = "prod", comment = "")
  private String prod;
  /**
   * 
   */
  @Column(name = "isComplete", comment = "")
  private String isComplete;
  
  /**
   * 
   */
  @Column(name = "strStan", comment = "")
  private String strStan;
  
  /**
   * 
   */
  @Column(name = "colcnt", comment = "")
  private String colcnt;
  


  /**
   * 
   */
  @Column(name = "pSort", comment = "")
  private String pSort;
  
  /**
   * 
   */
  @Column(name = "PKGNAME", comment = "")
  private String PKGNAME;

  /**
   * 
   */
  @Column(name = "PRODCODE", comment = "")
  private String PRODCODE;

  /**
   * 
   */
  @Column(name = "PRODNAME", comment = "")
  private String PRODNAME;

  /**
   * 
   */
  @Column(name = "SAMPLEV", comment = "")
  private String SAMPLEV;

  /**
   * 
   */
  @Column(name = "SAMPLEVCODE", comment = "")
  private String SAMPLEVCODE;

  /**
   * 
   */
  @Column(name = "SAMPLEVDATE", comment = "")
  private String SAMPLEVDATE;

  /**
   * 
   */
  @Column(name = "SAMPLEVBIGO", comment = "")
  private String SAMPLEVBIGO;

  /**
   * 
   */
  @Column(name = "SAMPLEVWEIGHT", comment = "")
  private String SAMPLEVWEIGHT;

  /**
   * 
   */
  @Column(name = "SAMPLET", comment = "")
  private String SAMPLET;

  /**
   * 
   */
  @Column(name = "SAMPLETCODE", comment = "")
  private String SAMPLETCODE;

  /**
   * 
   */
  @Column(name = "SAMPLETDATE", comment = "")
  private String SAMPLETDATE;

  /**
   * 
   */
  @Column(name = "SAMPLETBIGO", comment = "")
  private String SAMPLETBIGO;

  /**
   * 
   */
  @Column(name = "SAMPLETWEIGHT", comment = "")
  private String SAMPLETWEIGHT;

  /**
   * 
   */
  @Column(name = "SAMPLEB", comment = "")
  private String SAMPLEB;

  /**
   * 
   */
  @Column(name = "SAMPLEBCODE", comment = "")
  private String SAMPLEBCODE;

  /**
   * 
   */
  @Column(name = "SAMPLEBDATE", comment = "")
  private String SAMPLEBDATE;

  /**
   * 
   */
  @Column(name = "SAMPLEBBIGO", comment = "")
  private String SAMPLEBBIGO;

  /**
   * 
   */
  @Column(name = "SAMPLEBWEIGHT", comment = "")
  private String SAMPLEBWEIGHT;

  /**
   * 
   */
  @Column(name = "SAMPLEC", comment = "")
  private String SAMPLEC;

  /**
   * 
   */
  @Column(name = "SAMPLECCODE", comment = "")
  private String SAMPLECCODE;

  /**
   * 
   */
  @Column(name = "SAMPLECDATE", comment = "")
  private String SAMPLECDATE;

  /**
   * 
   */
  @Column(name = "SAMPLECBIGO", comment = "")
  private String SAMPLECBIGO;

  /**
   * 
   */
  @Column(name = "SAMPLECWEIGHT", comment = "")
  private String SAMPLECWEIGHT;

  /**
   * 
   */
  @Column(name = "BIGO", comment = "")
  private String BIGO;
  
  /**
   * 
   */
  @Column(name = "REVDATE", comment = "")
  private String REVDATE;
  
  /**
   * 
   */
  @Column(name = "REVNO", comment = "")
  private String REVNO;
  
  /**
   * 
   */
  @Column(name = "PKGPROD", comment = "")
  private String PKGPROD;
  
  /**
   * 
   */
  @Column(name = "WEIGHT", comment = "")
  private String WEIGHT;
  
  /**
   * 
   */
  @Column(name = "SAMPLE1", comment = "")
  private String SAMPLE1;
  
  /**
   * 
   */
  @Column(name = "SAMPLE2", comment = "")
  private String SAMPLE2;
  
  /**
   * 
   */
  @Column(name = "SAMPLE3", comment = "")
  private String SAMPLE3;
  
  /**
   * 
   */
  @Column(name = "SAMPLE4", comment = "")
  private String SAMPLE4;
  
  /**
   * 
   */
  @Column(name = "create_id", comment = "")
  private String create_id;

  /**
   * 
   */
  @Column(name = "create_time", comment = "")
  private String create_time;

  /**
   * 
   */
  @Column(name = "update_id", comment = "")
  private String update_id;

  /**
   * 
   */
  @Column(name = "update_time", comment = "")
  private String update_time;

  /**
   * 正常、关闭
   */
  @Column(name = "userYn", comment = "正常、关闭")
  private String userYn;

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getPKG() {
       return PKG;
  }

  public void setPKG(String PKG) {
       this.PKG = PKG;
  }
  public String getIsNew() {
      return isNew;
  }
	
  public void setIsNew(String isNew) {
	      this.isNew = isNew;
  }
  public String getProd() {
     return prod;
	}
	
	public void setProd(String prod) {
	     this.prod = prod;
	}
	public String getIsComplete() {
	    return isComplete;
	}
	
	public void setIsComplete(String isComplete) {
	    this.isComplete = isComplete;
	}
	public String getStrStan() {
	    return strStan;
	}
	
	public void setStrStan(String strStan) {
	    this.strStan = strStan;
	}
	public String getColcnt() {
	    return colcnt;
	}
	
	public void setColcnt(String colcnt) {
	    this.colcnt = colcnt;
	}

	public String getPSort() {
	    return pSort;
	}
	
	public void setPSort(String pSort) {
	    this.pSort = pSort;
	}
  public String getPKGNAME() {
       return PKGNAME;
  }

  public void setPKGNAME(String PKGNAME) {
       this.PKGNAME = PKGNAME;
  }

  public String getPRODCODE() {
       return PRODCODE;
  }

  public void setPRODCODE(String PRODCODE) {
       this.PRODCODE = PRODCODE;
  }

  public String getPRODNAME() {
       return PRODNAME;
  }

  public void setPRODNAME(String PRODNAME) {
       this.PRODNAME = PRODNAME;
  }

  public String getSAMPLEV() {
       return SAMPLEV;
  }

  public void setSAMPLEV(String SAMPLEV) {
       this.SAMPLEV = SAMPLEV;
  }

  public String getSAMPLEVCODE() {
       return SAMPLEVCODE;
  }

  public void setSAMPLEVCODE(String SAMPLEVCODE) {
       this.SAMPLEVCODE = SAMPLEVCODE;
  }

  public String getSAMPLEVDATE() {
       return SAMPLEVDATE;
  }

  public void setSAMPLEVDATE(String SAMPLEVDATE) {
       this.SAMPLEVDATE = SAMPLEVDATE;
  }

  public String getSAMPLEVBIGO() {
       return SAMPLEVBIGO;
  }

  public void setSAMPLEVBIGO(String SAMPLEVBIGO) {
       this.SAMPLEVBIGO = SAMPLEVBIGO;
  }

  public String getSAMPLEVWEIGHT() {
       return SAMPLEVWEIGHT;
  }

  public void setSAMPLEVWEIGHT(String SAMPLEVWEIGHT) {
       this.SAMPLEVWEIGHT = SAMPLEVWEIGHT;
  }

  public String getSAMPLET() {
       return SAMPLET;
  }

  public void setSAMPLET(String SAMPLET) {
       this.SAMPLET = SAMPLET;
  }

  public String getSAMPLETCODE() {
       return SAMPLETCODE;
  }

  public void setSAMPLETCODE(String SAMPLETCODE) {
       this.SAMPLETCODE = SAMPLETCODE;
  }

  public String getSAMPLETDATE() {
       return SAMPLETDATE;
  }

  public void setSAMPLETDATE(String SAMPLETDATE) {
       this.SAMPLETDATE = SAMPLETDATE;
  }

  public String getSAMPLETBIGO() {
       return SAMPLETBIGO;
  }

  public void setSAMPLETBIGO(String SAMPLETBIGO) {
       this.SAMPLETBIGO = SAMPLETBIGO;
  }

  public String getSAMPLETWEIGHT() {
       return SAMPLETWEIGHT;
  }

  public void setSAMPLETWEIGHT(String SAMPLETWEIGHT) {
       this.SAMPLETWEIGHT = SAMPLETWEIGHT;
  }

  public String getSAMPLEB() {
       return SAMPLEB;
  }

  public void setSAMPLEB(String SAMPLEB) {
       this.SAMPLEB = SAMPLEB;
  }

  public String getSAMPLEBCODE() {
       return SAMPLEBCODE;
  }

  public void setSAMPLEBCODE(String SAMPLEBCODE) {
       this.SAMPLEBCODE = SAMPLEBCODE;
  }

  public String getSAMPLEBDATE() {
       return SAMPLEBDATE;
  }

  public void setSAMPLEBDATE(String SAMPLEBDATE) {
       this.SAMPLEBDATE = SAMPLEBDATE;
  }

  public String getSAMPLEBBIGO() {
       return SAMPLEBBIGO;
  }

  public void setSAMPLEBBIGO(String SAMPLEBBIGO) {
       this.SAMPLEBBIGO = SAMPLEBBIGO;
  }

  public String getSAMPLEBWEIGHT() {
       return SAMPLEBWEIGHT;
  }

  public void setSAMPLEBWEIGHT(String SAMPLEBWEIGHT) {
       this.SAMPLEBWEIGHT = SAMPLEBWEIGHT;
  }

  public String getSAMPLEC() {
       return SAMPLEC;
  }

  public void setSAMPLEC(String SAMPLEC) {
       this.SAMPLEC = SAMPLEC;
  }

  public String getSAMPLECCODE() {
       return SAMPLECCODE;
  }

  public void setSAMPLECCODE(String SAMPLECCODE) {
       this.SAMPLECCODE = SAMPLECCODE;
  }

  public String getSAMPLECDATE() {
       return SAMPLECDATE;
  }

  public void setSAMPLECDATE(String SAMPLECDATE) {
       this.SAMPLECDATE = SAMPLECDATE;
  }

  public String getSAMPLECBIGO() {
       return SAMPLECBIGO;
  }

  public void setSAMPLECBIGO(String SAMPLECBIGO) {
       this.SAMPLECBIGO = SAMPLECBIGO;
  }

  public String getSAMPLECWEIGHT() {
       return SAMPLECWEIGHT;
  }

  public void setSAMPLECWEIGHT(String SAMPLECWEIGHT) {
       this.SAMPLECWEIGHT = SAMPLECWEIGHT;
  }

  public String getBIGO() {
      return BIGO;
 }

 public void setBIGO(String BIGO) {
      this.BIGO = BIGO;
 }
 
 public String getWEIGHT() {
     return WEIGHT;
}

public void setWEIGHT(String WEIGHT) {
     this.WEIGHT = WEIGHT;
}

 public String getREVDATE() {
     return REVDATE;
}

public void setREVDATE(String REVDATE) {
     this.REVDATE = REVDATE;
}


public String getREVNO() {
    return REVNO;
}

public void setREVNO(String REVNO) {
    this.REVNO = REVNO;
}

public String getPKGPROD() {
    return PKGPROD;
}

public void setPKGPROD(String PKGPROD) {
    this.PKGPROD = PKGPROD;
}

public String getSAMPLE1() {
    return SAMPLE1;
}

public void setSAMPLE1(String SAMPLE1) {
    this.SAMPLE1 = SAMPLE1;
}

public String getSAMPLE2() {
    return SAMPLE2;
}

public void setSAMPLE2(String SAMPLE2) {
    this.SAMPLE2 = SAMPLE2;
}
public String getSAMPLE3() {
    return SAMPLE3;
}

public void setSAMPLE3(String SAMPLE3) {
    this.SAMPLE3 = SAMPLE3;
}
public String getSAMPLE4() {
    return SAMPLE4;
}

public void setSAMPLE4(String SAMPLE4) {
    this.SAMPLE4 = SAMPLE4;
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

  public String getUserYn() {
       return userYn;
  }

  public void setUserYn(String userYn) {
       this.userYn = userYn;
  }

}
