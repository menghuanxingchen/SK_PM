package com.pm.persistence.machinemanager.dao;

import org.springframework.stereotype.Component;


import java.util.List;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表machine_pot_part_info 的DAO类
 * @author 刘镝
 */
@Component("MachinePotPartInfoDAO")
public class MachinePotPartInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select T.* from machine_pot_part_info T where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(MachinePotPartInfo entiy, String sql,String contant) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_id", JDBCHelper.EQ, entiy.getPot_part_id());
              if(contant.equals("LIKE")) {
            	  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_nm", JDBCHelper.LIKE, entiy.getPot_part_nm());
              }else if(contant.equals("EQ")){
            	  jdbcHelper.putParam(JDBCHelper.AND,"T. pot_part_nm", JDBCHelper.EQ, entiy.getPot_part_nm());
              }
              jdbcHelper.putParam(JDBCHelper.AND,"T. pot_id", JDBCHelper.EQ, entiy.getPot_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sqmMark", JDBCHelper.EQ, entiy.getSqmMark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sqmOrderNum", JDBCHelper.EQ, entiy.getSqmOrderNum());
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
 * @author 刘镝
 */
  public Pagination<MachinePotPartInfo> queryMachinePotPartInfoList(MachinePotPartInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql,"LIKE");
    return getPage(helper.getSql(), helper.getParam(), MachinePotPartInfo.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<MachinePotPartInfo> queryMachinePotPartInfoList(MachinePotPartInfo entity) {

	  String sql=" select * from machine_pot_part_info t where 1=1 and pot_part_id in("+entity.getPot_part_id()+")";
      return this.queryForList(sql,MachinePotPartInfo.class);
  }
  
/**
  * 停机维修用
  * @author 刘镝
  */
   public List<MachinePotPartInfo> queryMachinePotPartInfoEmergentList(MachinePotPartInfo entity) {

  	  String sql=" select * from machine_pot_part_info t where 1=1 and pot_id in("+entity.getPot_id()+")";
       return this.queryForList(sql,MachinePotPartInfo.class);
    }

/**
 * 查询返回entity(首行)
 * @author 姜易平
 */
  public MachinePotPartInfo queryMachinePotPartInfoEO(MachinePotPartInfo entity) {
    List<MachinePotPartInfo> list = queryMachinePotPartInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new MachinePotPartInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 * @throws Exception 
 */
  public int saveMachinePotPartInfo(MachinePotPartInfo entity) throws Exception {
    if(entity.getPot_part_id()==null)
    entity.setPot_part_id(this.getUUId());
    entity.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 姜易平
 * @throws Exception 
 */
  public int updateMachinePotPartInfo(MachinePotPartInfo entity) throws Exception {
	entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMachinePotPartInfo(MachinePotPartInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MachinePotPartInfo getMachinePotPartInfoById(String id) {
	  String sql = "select t.*,m.pot_nm from machine_pot_part_info t join machine_pot_info m on t.pot_id=m.pot_id where t.pot_part_id = '"+id+"'";
	  //return this.getEntityById(id, MachinePotPartInfo.class);
	  return this.queryObject(sql, MachinePotPartInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MachinePotPartInfo> queryDataList(MachinePotPartInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from machine_pot_part_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getPot_part_id())){
    //  sql = sql + " and pot_part_id like '%" + entity.getPot_part_id() + "%'";
    //}
    //sql = sql + " order by pot_part_id desc";
    return getPage(sql, null, MachinePotPartInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<MachinePotPartInfo> queryDataList(MachinePotPartInfo entity){
    String sql = "select * from machine_pot_part_info where 1=1 ";
    if(!StringUtils.isNullOrEmpty(entity.getPot_part_id())){
      sql = sql + " and pot_part_id = '" + entity.getPot_part_id() + "'";
    }
    if(!StringUtils.isNullOrEmpty(entity.getPot_id())){
      sql = sql + " and pot_id = '" + entity.getPot_id() + "'";
    }
    return this.queryForList(sql,MachinePotPartInfo.class);
  }

  /**
   * 查询entity List
   * @author gao
   */
    public List<MachinePotPartInfo> queryMachinePotPartInfobySQMList(MachinePotPartInfo entity) {
  	  String sql=" select * from machine_pot_part_info t where 1=1 and pot_id ='"+entity.getPot_id()+"' and sqmMark='SQM'";
  		
  		return this.queryForList(sql,MachinePotPartInfo.class);
    }
  
/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkMachinePotPartInfoEO(MachinePotPartInfo entity) {
    String sql = "";
    MachinePotPartInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getPot_part_id())){
        sql = "select count(*) from machine_pot_part_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from machine_pot_part_info t where t.pot_part_id <> '" + entity.getPot_part_id() +"'";
        eo.setPot_part_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql,"EQ");
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
