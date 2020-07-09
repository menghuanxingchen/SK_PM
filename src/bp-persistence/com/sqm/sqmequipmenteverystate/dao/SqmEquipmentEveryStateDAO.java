package com.sqm.sqmequipmenteverystate.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.sqm.sqmequipmenteverystate.entity.SqmEquipmentEveryState;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_equipment_every_state 的DAO类
 * @author 刘镝
 */
@Component("SqmEquipmentEveryStateDAO")
public class SqmEquipmentEveryStateDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from sqm_equipment_every_state t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(SqmEquipmentEveryState entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineName", JDBCHelper.EQ, entiy.getLineName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentName", JDBCHelper.EQ, entiy.getEquipmentName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. eState", JDBCHelper.EQ, entiy.getEState());
              jdbcHelper.putParam(JDBCHelper.AND,"T. eStateName", JDBCHelper.EQ, entiy.getEStateName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. inspector", JDBCHelper.EQ, entiy.getInspector());
              jdbcHelper.putParam(JDBCHelper.AND,"T. oldState", JDBCHelper.EQ, entiy.getOldState());
      }
      return jdbcHelper;
  }

/**
 * 更新绑定
 * @author 刘镝
 */
  private JDBCHelper makeUpd(SqmEquipmentEveryState entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. lineName", JDBCHelper.EQ, entiy.getLineName());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. equipmentName", JDBCHelper.EQ, entiy.getEquipmentName());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. eState", JDBCHelper.EQ, entiy.getEState());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. eStateName", JDBCHelper.EQ, entiy.getEStateName());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. inspector", JDBCHelper.EQ, entiy.getInspector());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. oldState", JDBCHelper.EQ, entiy.getOldState());
      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmEquipmentEveryState> querySqmEquipmentEveryStateList(SqmEquipmentEveryState entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmEquipmentEveryState.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<SqmEquipmentEveryState> querySqmEquipmentEveryStateList(SqmEquipmentEveryState entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmEquipmentEveryState.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public SqmEquipmentEveryState querySqmEquipmentEveryStateEO(SqmEquipmentEveryState entity) {
    List<SqmEquipmentEveryState> list = querySqmEquipmentEveryStateList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new SqmEquipmentEveryState();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSqmEquipmentEveryState(SqmEquipmentEveryState entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSqmEquipmentEveryState(SqmEquipmentEveryState entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int specialUpdSqmEquipmentEveryState(SqmEquipmentEveryState entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update sqm_equipment_every_state t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
  return this.executeSQL(sql,helper.getParam());
}
/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSqmEquipmentEveryState(SqmEquipmentEveryState entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SqmEquipmentEveryState getSqmEquipmentEveryStateById(String id) {
    return this.getEntityById(id, SqmEquipmentEveryState.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmEquipmentEveryState> queryDataList(SqmEquipmentEveryState entity, int pageIndex, int pageSize) {
    String sql = "select t.* from sqm_equipment_every_state t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getId())){
    //  sql = sql + " and id like '%" + entity.getId() + "%'";
    //}
    //sql = sql + " order by id desc";
    return getPage(sql, null, SqmEquipmentEveryState.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<SqmEquipmentEveryState> queryDataList(String inspector,String lineId,String equipmentId){
    String sql = "select * from sqm_equipment_every_state where 1=1 ";
    if(!StringUtils.isNullOrEmpty(inspector)) {
    	sql = sql + " and inspector = '" + inspector + "'";
    }    
    if(!StringUtils.isNullOrEmpty(lineId)) {
    	sql = sql + " and lineId = '" + lineId + "'";
    }
    if(!StringUtils.isNullOrEmpty(equipmentId)) {
    	sql = sql + " and equipmentId = '" + equipmentId + "'";
    }
    sql += " and inspector!='muban' ";
    return this.queryForList(sql,SqmEquipmentEveryState.class);
  }
  
  /**
   * 根据实体条件检索list
   * @author 刘镝
   */
    public List<SqmEquipmentEveryState> queryDataList2(){
      String sql = "select * from sqm_equipment_every_state where inspector='muban' ";
      return this.queryForList(sql,SqmEquipmentEveryState.class);
    }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkSqmEquipmentEveryStateEO(SqmEquipmentEveryState entity) {
    String sql = "";
    SqmEquipmentEveryState eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from sqm_equipment_every_state t where 1 = 1 ";
    }else{
        sql = "select count(*) from sqm_equipment_every_state t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }
  
  /**
   * 修改设备状态
   * @author 刘镝
   */
  public int resetUpdSqmEquipmentEveryState(SqmEquipmentEveryState entity,String type){
    String sql = "update sqm_equipment_every_state  set oldState= '1',eState= '1',eStateName='未检测' where lineId ='" + entity.getLineId() +"' and inspector ='"+entity.getInspector()+"'";
    String sql2 = "update sqm_equipment_every_state  set oldState= '2',eState= '2',eStateName='已检测正常' where lineId ='" + entity.getLineId() +"'";
    if(type.equals("2")) {
  	  String equipmentIds = entity.getEquipmentId();
  	  String[] equipmentId = equipmentIds.split(",");
  	  sql+=" and equipmentId not in ('"+equipmentId[0]+"','"+equipmentId[1]+"') and inspector ='"+entity.getInspector()+"'";
  	  sql2+=" and equipmentId  in ('"+equipmentId[0]+"','"+equipmentId[1]+"') and inspector ='"+entity.getInspector()+"'";
  	  this.executeSQL(sql2);
    }
    return this.executeSQL(sql);
  }
  
  /**
   * 修改设备状态
   * @author 刘镝
   */
  public int updSqmEquipmentEveryState(SqmEquipmentEveryState entity){
	    String beforsql = "update sqm_equipment_every_state  set oldState = eState ";
	    beforsql+=" where lineId ='" + entity.getLineId() +"' and equipmentId ='" + entity.getEquipmentId()+"' and inspector !='muban' ";
	    this.executeSQL(beforsql);
  	String eStateName="设备故障";
  	switch (entity.getEState()) {
  	case "1":
  		eStateName="未检测";
  		break;
  	case "2":
  		eStateName="已检测正常";
  		break;
  	case "3":
  		eStateName="已检测检测信息与计划不一致";
  		break;
  	case "4":
  		eStateName="设备故障";
  		break;
  	case "5":
  		eStateName="已检测检测信息与他人不一致";
  		break;
  	default:
  		eStateName="故障已解除待检";
  		break;
  	}
    String sql = "update sqm_equipment_every_state  set eState= '"+entity.getEState()+"',eStateName='"+eStateName+"' ";
    	    if(!StringUtils.isNullOrEmpty(entity.getOldState())) {
    	    	sql+= " ,oldState ='" + entity.getOldState()+"'";
    	    }
    	    sql+=" where lineId ='" + entity.getLineId() +"' and equipmentId ='" + entity.getEquipmentId()+"' and inspector !='muban' ";
    if(!StringUtils.isNullOrEmpty(entity.getInspector()) ) {
    	sql+= " and inspector ='" + entity.getInspector()+"'";
    }
    
    return this.executeSQL(sql);
  }

}
