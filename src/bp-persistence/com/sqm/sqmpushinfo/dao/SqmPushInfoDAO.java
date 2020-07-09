package com.sqm.sqmpushinfo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_push_info 的DAO类
 * @author 刘镝
 */
@Component("SqmPushInfoDAO")
public class SqmPushInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from sqm_push_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(SqmPushInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_id", JDBCHelper.EQ, entiy.getPush_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_msg", JDBCHelper.EQ, entiy.getPush_msg());
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_time", JDBCHelper.EQ, entiy.getPush_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_type", JDBCHelper.EQ, entiy.getPush_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_to", JDBCHelper.EQ, entiy.getPush_to());
              jdbcHelper.putParam(JDBCHelper.AND,"T. push_source", JDBCHelper.EQ, entiy.getPush_to());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_yn", JDBCHelper.EQ, entiy.getUpdate_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              
      }
      return jdbcHelper;
  }

/**
 * 更新绑定
 * @author 刘镝
 */
  private JDBCHelper makeUpd(SqmPushInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. push_msg", JDBCHelper.EQ, entiy.getPush_msg());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. push_time", JDBCHelper.EQ, entiy.getPush_time());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. push_type", JDBCHelper.EQ, entiy.getPush_type());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. push_to", JDBCHelper.EQ, entiy.getPush_to());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. push_source", JDBCHelper.EQ, entiy.getPush_to());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_yn", JDBCHelper.EQ, entiy.getUpdate_yn());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());

      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<SqmPushInfo> querySqmPushInfoList(SqmPushInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmPushInfo.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<SqmPushInfo> querySqmPushInfoList(SqmPushInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SqmPushInfo.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public SqmPushInfo querySqmPushInfoEO(SqmPushInfo entity) {
    List<SqmPushInfo> list = querySqmPushInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new SqmPushInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public String saveSqmPushInfo(SqmPushInfo entity) {
    if(entity.getPush_id()==null)
    entity.setPush_id(this.getUUId());
    this.saveNew(entity);
    return entity.getPush_id();
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSqmPushInfo(SqmPushInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int specialUpdSqmPushInfo(SqmPushInfo entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update sqm_push_info t set "+ helper.getUpdSql() +" where t.push_id ='" + entity.getPush_id() +"'";
  return this.executeSQL(sql,helper.getParam());
}

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int updSqmPushInfoUpdate_yn(String pushId,TestResult testResult,String type){
	int executeSQL =0;
	String beforesql="select a.push_id from (select s.push_source,s.push_id,t.lineId,t.lotnumver,t.produnctCode,t.create_time,t.test_num,t.equipmentId,t.planId  from test_result t,sqm_push_info s  where t.id=s.push_source and t.planId ='"+testResult.getPlanId()+"') a WHERE  a.test_num="+testResult.getTest_num()+" and equipmentId='"+testResult.getEquipmentId()+"'";
	List<SqmPushInfo> list = queryForList(beforesql,SqmPushInfo.class);
	if(!StringUtils.isNullOrEmpty(pushId)||list.size()>0) {
		  String sql = " UPDATE sqm_push_info set update_yn= 'y' where push_id ";
		  if("only".equals(type)) {//更新此条
			  sql+=" = '"+pushId+"'";
		  }else if("all".equals(type)){//更新全部（该设备所有的检测项正常）
			  sql+=" in ( select a.push_id from (select s.push_source,s.push_id,t.lineId,t.lotnumver,t.produnctCode,t.create_time,t.test_num,t.equipmentId,t.planId  from test_result t,sqm_push_info s  where t.id=s.push_source and t.planId ='"+testResult.getPlanId()+"') a WHERE  a.test_num="+testResult.getTest_num()+" and equipmentId='"+testResult.getEquipmentId()+"')";
		  }
		  executeSQL = this.executeSQL(sql);
	}
  return executeSQL;
}

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSqmPushInfo(SqmPushInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SqmPushInfo getSqmPushInfoById(String id) {
    return this.getEntityById(id, SqmPushInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 * @throws Exception 
 */
  public Pagination<SqmPushInfo> queryDataList(SqmPushInfo entity, int pageIndex, int pageSize) throws Exception {
	String date=DateUtil.getNow(DateUtil.Y_M_D);
    String sql = "select t.* from sqm_push_info t where 1=1 ";
    if(!StringUtils.isNullOrEmpty(entity.getUpdate_yn())){
      sql = sql + " and update_yn = '" + entity.getUpdate_yn() + "'";
    }
    if(!StringUtils.isNullOrEmpty(entity.getPush_msg())){
        sql = sql + " and push_msg like '%" + entity.getPush_msg() + "%'";
      }
    if(!StringUtils.isNullOrEmpty(entity.getPush_type())){
        sql = sql + " and push_type = '" + entity.getPush_type() + "'";
      }
    if(!StringUtils.isNullOrEmpty(entity.getPush_to())){
        sql = sql + " and (push_to like '%" + entity.getPush_to() + "%' or push_to = 'ALL' )";
      }
    sql = sql + " and push_time like '%"+date+"%' order by push_time desc";
    return getPage(sql, null, SqmPushInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<SqmPushInfo> queryDataList(SqmPushInfo entity){
    String sql = "select * from sqm_push_info where 1=1 ";
    return this.queryForList(sql,SqmPushInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkSqmPushInfoEO(SqmPushInfo entity) {
    String sql = "";
    SqmPushInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getPush_id())){
        sql = "select count(*) from sqm_push_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from sqm_push_info t where t.push_id <> '" + entity.getPush_id() +"'";
        eo.setPush_id("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
