package com.sqm.sqmequipmentstate.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_equipment_state 的DAO类
 * @author 刘镝
 */
@Component("SqmEquipmentStateDAO")
public class SqmEquipmentStateDAO extends BaseDao {
  public final static String QueryTableSql = "select T.* from sqm_equipment_state T where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(SqmEquipmentState entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineName", JDBCHelper.EQ, entiy.getLineName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentName", JDBCHelper.EQ, entiy.getEquipmentName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. eState", JDBCHelper.EQ, entiy.getEState());
              jdbcHelper.putParam(JDBCHelper.AND,"T. eStateName", JDBCHelper.EQ, entiy.getEStateName());
      }
      return jdbcHelper;
  }

/**
 * 更新绑定
 * @author 刘镝
 */
  private JDBCHelper makeUpd(SqmEquipmentState entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. lineName", JDBCHelper.EQ, entiy.getLineName());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. equipmentName", JDBCHelper.EQ, entiy.getEquipmentName());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. eState", JDBCHelper.EQ, entiy.getEState());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. eStateName", JDBCHelper.EQ, entiy.getEStateName());
      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmEquipmentState> querySqmEquipmentStateList(SqmEquipmentState entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmEquipmentState.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<SqmEquipmentState> querySqmEquipmentStateList(SqmEquipmentState entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmEquipmentState.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public SqmEquipmentState querySqmEquipmentStateEO(SqmEquipmentState entity) {
    List<SqmEquipmentState> list = querySqmEquipmentStateList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new SqmEquipmentState();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSqmEquipmentState(SqmEquipmentState entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSqmEquipmentState(SqmEquipmentState entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 修改设备状态
 * @author 刘镝
 */
public int resetUpdSqmEquipmentState(SqmEquipmentState entity,String type){
  String sql = "update sqm_equipment_state  set eState= '1',eStateName='未检测' where lineId ='" + entity.getLineId() +"'";
  String sql2 = "update sqm_equipment_state  set eState= '2',eStateName='已检测正常' where lineId ='" + entity.getLineId() +"'";
  if(type.equals("2")) {
	  String equipmentIds = entity.getEquipmentId();
	  String[] equipmentId = equipmentIds.split(",");
	  sql+=" and equipmentId not in ('"+equipmentId[0]+"','"+equipmentId[1]+"')";
	  sql2+=" and equipmentId  in ('"+equipmentId[0]+"','"+equipmentId[1]+"')";
	  this.executeSQL(sql2);
  }
  return this.executeSQL(sql);
}

/**
 * 修改设备状态
 * @author 刘镝
 */
public int updSqmEquipmentState(SqmEquipmentState entity){
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
	case "6":
		eStateName="故障已解除待检";
		break;
	default:
		eStateName="设备故障（维修人员报修）";
		break;
	}
  String sql = "update sqm_equipment_state  set eState= '"+entity.getEState()+"',eStateName='"+eStateName+"' where lineId ='" + entity.getLineId() +"' and equipmentId ='" + entity.getEquipmentId()+"'";
  return this.executeSQL(sql);
}

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int specialUpdSqmEquipmentState(SqmEquipmentState entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update sqm_equipment_state t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
  return this.executeSQL(sql,helper.getParam());
}

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSqmEquipmentState(SqmEquipmentState entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SqmEquipmentState getSqmEquipmentStateById(String id) {
    return this.getEntityById(id, SqmEquipmentState.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmEquipmentState> queryDataList(SqmEquipmentState entity, int pageIndex, int pageSize) {
    String sql = "select t.* from sqm_equipment_state t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getId())){
    //  sql = sql + " and id like '%" + entity.getId() + "%'";
    //}
    //sql = sql + " order by id desc";
    return getPage(sql, null, SqmEquipmentState.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<SqmEquipmentState> queryDataList(SqmEquipmentState entity){
    String sql = "select * from sqm_equipment_state where 1=1 ";
    return this.queryForList(sql,SqmEquipmentState.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkSqmEquipmentStateEO(SqmEquipmentState entity) {
    String sql = "";
    SqmEquipmentState eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from sqm_equipment_state t where 1 = 1 ";
    }else{
        sql = "select count(*) from sqm_equipment_state t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
