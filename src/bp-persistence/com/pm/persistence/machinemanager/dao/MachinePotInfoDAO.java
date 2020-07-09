package com.pm.persistence.machinemanager.dao;

import org.springframework.stereotype.Component;


import java.util.List;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表machine_pot_info 的DAO类
 * @author 刘镝
 */
@Component("MachinePotInfoDAO")
public class MachinePotInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from machine_pot_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(MachinePotInfo entiy, String sql,String contant) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.EQ, entiy.getPot_id());
              if(contant.equals("LIKE")) {
            	  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_nm", JDBCHelper.LIKE, entiy.getPot_nm());
              }else if(contant.equals("EQ")) {
            	  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_nm", JDBCHelper.EQ, entiy.getPot_nm());
              }
              jdbcHelper.putParam(JDBCHelper.AND,"T. production_date", JDBCHelper.EQ, entiy.getProduction_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. use_year", JDBCHelper.EQ, entiy.getUse_year());
              jdbcHelper.putParam(JDBCHelper.AND,"T. supplier", JDBCHelper.EQ, entiy.getSupplier());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 姜易平
 */
  public Pagination<MachinePotInfo> queryMachinePotInfoList(MachinePotInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql,"LIKE");
    return getPage(helper.getSql(), helper.getParam(), MachinePotInfo.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<MachinePotInfo> queryMachinePotInfoList(MachinePotInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql,"LIKE");
    return getPage(helper.getSql(), helper.getParam(), MachinePotInfo.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public MachinePotInfo queryMachinePotInfoEO(MachinePotInfo entity) {
    List<MachinePotInfo> list = queryMachinePotInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new MachinePotInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 姜易平
 * @throws Exception 
 */
  public int saveMachinePotInfo(MachinePotInfo entity) throws Exception {
    if(entity.getPot_id()==null)
    entity.setPot_id(this.getUUId());
    entity.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 * @throws Exception 
 */
  public int updateMachinePotInfo(MachinePotInfo entity) throws Exception {
	entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 根据条件修改信息
 * @author 刘镝
 *
public int specialUpdMachinePotInfo(MachinePotInfo entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update machine_pot_info t set "+ helper.getUpdSql() +" where t.pot_id ='" + entity.getPot_id() +"'";
  return this.executeSQL(sql,helper.getParam());
}*/
  
  
/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMachinePotInfo(MachinePotInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 姜易平
 */
  public MachinePotInfo getMachinePotInfoById(String id) {
    return this.getEntityById(id, MachinePotInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MachinePotInfo> queryDataList(MachinePotInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from machine_pot_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getPot_id())){
    //  sql = sql + " and pot_id like '%" + entity.getPot_id() + "%'";
    //}
    //sql = sql + " order by pot_id desc";
    return getPage(sql, null, MachinePotInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<MachinePotInfo> queryDataList(MachinePotInfo entity){
    String sql = "select * from machine_pot_info where 1=1 ";
    return this.queryForList(sql,MachinePotInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkMachinePotInfoEO(MachinePotInfo entity) {
    String sql = "";
    MachinePotInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getPot_id())){
        sql = "select count(*) from machine_pot_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from machine_pot_info t where t.pot_id <> '" + entity.getPot_id() +"'";
        eo.setPot_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql,"EQ");
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
