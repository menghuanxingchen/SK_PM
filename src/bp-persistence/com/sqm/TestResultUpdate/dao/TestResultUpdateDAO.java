package com.sqm.TestResultUpdate.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.sqm.TestResultUpdate.entity.TestResultUpdate;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_result_update 的DAO类
 * @author 刘镝
 */
@Component("TestResultUpdateDAO")
public class TestResultUpdateDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from test_result_update t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(TestResultUpdate entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. rid", JDBCHelper.EQ, entiy.getRid());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_cause", JDBCHelper.EQ, entiy.getUpdate_cause());
              jdbcHelper.putParam(JDBCHelper.AND,"T. test_result", JDBCHelper.EQ, entiy.getTest_result());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_num", JDBCHelper.EQ, entiy.getUpdate_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }

/**
 * 更新绑定
 * @author 刘镝
 */
  private JDBCHelper makeUpd(TestResultUpdate entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. rid", JDBCHelper.EQ, entiy.getRid());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_cause", JDBCHelper.EQ, entiy.getUpdate_cause());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. test_result", JDBCHelper.EQ, entiy.getTest_result());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_num", JDBCHelper.EQ, entiy.getUpdate_num());
              jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<TestResultUpdate> queryTestResultUpdateList(TestResultUpdate entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), TestResultUpdate.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<TestResultUpdate> queryTestResultUpdateList(TestResultUpdate entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), TestResultUpdate.class, 1, -1).getRecords();
  }

/**
 * 查询返回entity(首行)
 * @author 刘镝
 */
  public TestResultUpdate queryTestResultUpdateEO(TestResultUpdate entity) {
    List<TestResultUpdate> list = queryTestResultUpdateList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new TestResultUpdate();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveTestResultUpdate(TestResultUpdate entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateTestResultUpdate(TestResultUpdate entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 根据条件修改信息
 * @author 刘镝
 */
public int specialUpdTestResultUpdate(TestResultUpdate entity){
  JDBCHelper helper = makeUpd(entity, "");
  String sql = "update test_result_update t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
  return this.executeSQL(sql,helper.getParam());
}
/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteTestResultUpdate(TestResultUpdate entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public TestResultUpdate getTestResultUpdateById(String id) {
    return this.getEntityById(id, TestResultUpdate.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<TestResultUpdate> queryDataList(TestResultUpdate entity, int pageIndex, int pageSize) {
    String sql = "select t.* from test_result_update t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getId())){
    //  sql = sql + " and id like '%" + entity.getId() + "%'";
    //}
    //sql = sql + " order by id desc";
    return getPage(sql, null, TestResultUpdate.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<TestResultUpdate> queryDataList(TestResultUpdate entity){
    String sql = "select * from test_result_update where 1=1 ";
    return this.queryForList(sql,TestResultUpdate.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkTestResultUpdateEO(TestResultUpdate entity) {
    String sql = "";
    TestResultUpdate eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from test_result_update t where 1 = 1 ";
    }else{
        sql = "select count(*) from test_result_update t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
