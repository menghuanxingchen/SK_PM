package com.pm.persistence.sysinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.sysinfo.entity.SysCodeGroupInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sys_code_group_info 的DAO类
 * @author 刘镝
 */
@Component("SysCodeGroupInfoDAO")
public class SysCodeGroupInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM sys_code_group_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(SysCodeGroupInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_id", JDBCHelper.EQ, entiy.getCode_group_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_value", JDBCHelper.EQ, entiy.getCode_group_value());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_name", JDBCHelper.EQ, entiy.getCode_group_name());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SysCodeGroupInfo> querySysCodeGroupInfoList(SysCodeGroupInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SysCodeGroupInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSysCodeGroupInfo(SysCodeGroupInfo entity) {
	  if(entity.getCode_group_id()==null)
			entity.setCode_group_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSysCodeGroupInfo(SysCodeGroupInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSysCodeGroupInfo(SysCodeGroupInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SysCodeGroupInfo getSysCodeGroupInfoById(String id) {
    return this.getEntityById(id, SysCodeGroupInfo.class);
  }
  
  public int getSysCodeGroupInfoByCodeGroupValue(String id) {
	  String sql = "SELECT count(*) FROM sys_code_group_info T WHERE 1=1 AND code_group_value='"+id+"'";
	    return this.queryForInt(sql, null);
	  }
  
  public List<SysCodeGroupInfo> querySysCodeGroupInfoListNoPage(SysCodeGroupInfo entity) {
	    JDBCHelper helper = makeSearch(entity, QueryTableSql);
	    return this.queryForList(helper.getSql(), helper.getParam(), SysCodeGroupInfo.class);
  }
}
