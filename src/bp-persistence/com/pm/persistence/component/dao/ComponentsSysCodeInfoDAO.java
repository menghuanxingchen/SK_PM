package com.pm.persistence.component.dao;


import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.component.entity.ComponentsSysCodeInfo;
import com.pm.persistence.inspectplan.entity.InsPlanCheckDetail;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sys_code_info 的DAO类
 * @author 刘镝
 */
@Component("ComponentsSysCodeInfoDAO")
public class ComponentsSysCodeInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from sys_code_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(ComponentsSysCodeInfo entiy, String sql) {
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
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_name", JDBCHelper.EQ, entiy.getCode_group_name());
              jdbcHelper.putParam(JDBCHelper.AND,"T. code_group_value1", JDBCHelper.EQ, entiy.getCode_group_value1());
      }
      return jdbcHelper;
  }




/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ComponentsSysCodeInfo> queryComponentsSysCodeInfoList(ComponentsSysCodeInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    
 //   System.err.println(helper.getSql());
//    System.err.println(helper.getParam());
    return getPage(helper.getSql(), helper.getParam(), ComponentsSysCodeInfo.class, pageIndex, pageSize);
    
    
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<ComponentsSysCodeInfo> queryComponentsSysCodeInfoListLike(ComponentsSysCodeInfo entity, int pageIndex, int pageSize) {
     
      
    String sql = "select t.* from sys_code_info t where t.code_group_value ='components_03' and  code_nm like '%"+entity.getCode_nm()+"%'";
//      System.err.println(helper.getParam());
      return getPage(sql, null, ComponentsSysCodeInfo.class, pageIndex, pageSize);
      
      
    }


/**
 * 查询entity List
 * @author 刘镝
 */
  public List<ComponentsSysCodeInfo> queryComponentsSysCodeInfoList(ComponentsSysCodeInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ComponentsSysCodeInfo.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public ComponentsSysCodeInfo queryComponentsSysCodeInfoEO(ComponentsSysCodeInfo entity) {
	 
	  
    List<ComponentsSysCodeInfo> list = selelLostComponents(entity);
   
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new ComponentsSysCodeInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 * @throws Exception 
 */
  public int saveComponentsSysCodeInfo(ComponentsSysCodeInfo entity) throws Exception {
	 
    if(entity.getCode_id()==null)
    entity.setCode_id(this.getUUId());
    entity.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
	   
    entity.setCode_group_value("components_03");
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 * @throws Exception 
 */
  public int updateComponentsSysCodeInfo(ComponentsSysCodeInfo entity) throws Exception {
	  entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    int flag =  this.saveUpdate(entity);
    
    return flag;
  }


  /**
   * 修改实体对象entity
   * @author 刘镝
 * @throws Exception 
   */
    public int updateInsPlanCheckDetail(ComponentsSysCodeInfo entity)  {
    	
     
      int flag =  this.saveUpdate(entity);
      return flag;
    }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteComponentsSysCodeInfo(ComponentsSysCodeInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ComponentsSysCodeInfo getComponentsSysCodeInfoById(String id) {
    return this.getEntityById(id, ComponentsSysCodeInfo.class);
  }
  
  /**
   * 根据实体对象Code_value返回entity
   * @author 刘镝
   */
   

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ComponentsSysCodeInfo> queryDataList(ComponentsSysCodeInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from sys_code_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getCode_id())){
    //  sql = sql + " and code_id like '%" + entity.getCode_id() + "%'";
    //}
    //sql = sql + " order by code_id desc";
    return getPage(sql, null, ComponentsSysCodeInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author Aubrey
 */
  public List<ComponentsSysCodeInfo> queryDataList(){
    String sql = "select * from sys_code_info where 1=1 and code_group_value = 'components_03' ";
    return this.queryForList(sql,ComponentsSysCodeInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkComponentsSysCodeInfoEO(ComponentsSysCodeInfo entity) {
    String sql = "";
    ComponentsSysCodeInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getCode_id())){
        sql = "select count(*) from sys_code_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from sys_code_info t where t.code_id <> '" + entity.getCode_id() +"'";
        eo.setCode_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }
  /**
   * 分页查询3级部件
   * @param entity
   * @param pageIndex
   * @param pageSize
   * @return
   */

  public  Pagination<ComponentsSysCodeInfo> selComponentsSysCodeInfo(ComponentsSysCodeInfo entity,int pageIndex, int pageSize){
	 String sql = "select t.* from sys_code_info t where t.code_group_value='"+"components_03"+"'";
	  //queryForList(sql,, pojoClass)
	  return getPage(sql, null, ComponentsSysCodeInfo.class, pageIndex, pageSize);
  }
  /**
   * 保存设备信息
   * @param entity
   * @return
 * @throws Exception 
   */
  public int addComponentsSysCodeInfo(ComponentsSysCodeInfo entity) throws Exception {
	
		    if(entity.getCode_id()==null) 
		    entity.setCode_id(this.getUUId());   
		    int flag =  this.saveNew(entity);		   
		    return flag;
		  
  }
  /**
   * 根据Code_nm查询数据
   * @param entity
   * @return
   */
  
  public List<ComponentsSysCodeInfo> queryComponentsSysCodeInfoList1(ComponentsSysCodeInfo entity) {
	   // JDBCHelper helper = makeSearch(entity, QueryTableSql);
	    String sql= " select t.* from sys_code_info t where t.code_group_value='"+"components_03"+"'"+"and t.code_nm='"
	   +entity.getCode_nm()+"'";
	    return getPage(sql, null, ComponentsSysCodeInfo.class, 1, -1).getRecords();
	  } 
  /**
   * 查询code_value最后一条数据
   * @param entity
   * @return
   */
  
  public  List<ComponentsSysCodeInfo> selelLostComponents(ComponentsSysCodeInfo entity) {
	  String sql = "select  max(cast(code_value as signed integer)) code_value from  sys_code_info where  code_value group by code_group_value HAVING code_group_value='components_03'";	
	  return queryForList(sql, ComponentsSysCodeInfo.class);
	  
  }
  
}
