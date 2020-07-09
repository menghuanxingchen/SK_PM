package com.pm.persistence.sysinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sys_code_info 的DAO类
 * @author 刘镝
 */
@Component("SysCodeInfoDAO")
public class SysCodeInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.*,(SELECT CODE_NM FROM sys_code_info WHERE sub_code_group_value = T.code_group_value)AS sub_code_group_nm "
  										   + " FROM sys_code_info T WHERE 1=1 ";
  public final static String QueryTableSqlWithCode = "SELECT T.* , concat(T. code_value , T. code_nm)  code_nm2 "
  													+ " FROM sys_code_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(SysCodeInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_id", JDBCHelper.EQ, entiy.getCode_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_value", JDBCHelper.EQ, entiy.getCode_group_value());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_value", JDBCHelper.EQ, entiy.getCode_value());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_nm", JDBCHelper.LIKE, entiy.getCode_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_code_group_value", JDBCHelper.EQ, entiy.getSub_code_group_value());
              jdbcHelper.putParam(JDBCHelper.AND,"T. order_num", JDBCHelper.EQ, entiy.getOrder_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }
  private JDBCHelper makeSearch2(SysCodeInfo entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. code_id", JDBCHelper.EQ, entiy.getCode_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_value", JDBCHelper.EQ, entiy.getCode_group_value());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. code_value", JDBCHelper.EQ, entiy.getCode_value());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. code_nm", JDBCHelper.LIKE, entiy.getCode_nm());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_code_group_value", JDBCHelper.EQ, entiy.getSub_code_group_value());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. order_num", JDBCHelper.EQ, entiy.getOrder_num());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
	              jdbcHelper.putOrder("CONVERT(T. code_value,SIGNED)");
	      }
	      return jdbcHelper;
	  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SysCodeInfo> querySysCodeInfoList(SysCodeInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SysCodeInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author sunaibo
   */
    public Pagination<SysCodeInfo> querySysCodeInfoPageList(SysCodeInfo entity, int pageIndex, int pageSize) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      String sql=helper.getSql();
		if("".equals(entity.getCode_group_value())||entity.getCode_group_value()==null){
			sql=sql+" and T.code_group_value in ('"+PmConstant.machTypeSubCode_01+"','"+PmConstant.machTypeSubCode_02+"','"+PmConstant.machTypeSubCode_03+"','"+PmConstant.machTypeSubCode_04+"')";
		}
      return getPage(sql, helper.getParam(), SysCodeInfo.class, pageIndex, pageSize);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSysCodeInfo(SysCodeInfo entity) {
	  if(entity.getCode_id()==null)
			entity.setCode_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSysCodeInfo(SysCodeInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSysCodeInfo(SysCodeInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SysCodeInfo getSysCodeInfoById(String id) {
    return this.getEntityById(id, SysCodeInfo.class);
  }
  
  /**
   * 二级下拉联动
   * @param entity
   * @author fish
   */
  public List<SysCodeInfo> getSubCodeList(String code_group_value,String code_value){
	  String sql = "SELECT * from sys_code_info where code_group_value = (select sub_code_group_value "
	  		+ " from sys_code_info where code_group_value = '"+code_group_value+"' and code_value = '"+code_value+"' )";
	  return this.queryForList(sql, SysCodeInfo.class);
  }
  
  public int getSysCodeGroupInfoByCodeValue(SysCodeInfo entity) {
	  String sql = "SELECT count(*) FROM sys_code_info T WHERE 1=1 and code_group_value = ? AND code_value= ?";
	    return this.queryForInt(sql, new Object[]{entity.getCode_group_value(),entity.getCode_value()});
	  }
  
  
  public SysCodeInfo getSubCodeName(SysCodeInfo entity) {
	  String sql = "SELECT code_nm,code_value FROM sys_code_info T WHERE 1=1 and code_group_value = ? AND code_value= ?";
	    return this.queryObject(sql, new Object[]{entity.getCode_group_value(),entity.getCode_value()}, SysCodeInfo.class);
  }
  
  public SysCodeInfo getSubCodeInfo(SysCodeInfo entity) {
	  String sql = "SELECT * FROM sys_code_info T WHERE 1=1 and code_group_value = ? AND code_value= ?";
	    return this.queryObject(sql, new Object[]{entity.getCode_group_value(),entity.getCode_value()}, SysCodeInfo.class);
  }
  
  public List<SysCodeInfo> querySysCodeInfoListNoPage(SysCodeInfo entity) {
	    JDBCHelper helper = makeSearch(entity, QueryTableSql);
	    return this.queryForList(helper.getSql(), helper.getParam(), SysCodeInfo.class);
  }
  public List<SysCodeInfo> querySysCodeInfoListNoPageWithCode(SysCodeInfo entity) {
	    JDBCHelper helper = makeSearch2(entity, QueryTableSqlWithCode);
	    return this.queryForList(helper.getSql(), helper.getParam(), SysCodeInfo.class);
}
  public List<SysCodeInfo> querySysCodeInfoListNoPageWithoutCode(SysCodeInfo entity) {
	    JDBCHelper helper = makeSearch2(entity, QueryTableSql);
	    return this.queryForList(helper.getSql(), helper.getParam(), SysCodeInfo.class);
}
  /**
   * 获取code_value最大值
   */
  public SysCodeInfo getMaxCodeValue(String code_group_value){
	  String sql = "SELECT max(code_value) as max_code_value FROM sys_code_info T WHERE 1=1 and code_group_value = ? ";
	  return this.queryObject(sql,new Object[]{code_group_value}, SysCodeInfo.class);
  }

}
