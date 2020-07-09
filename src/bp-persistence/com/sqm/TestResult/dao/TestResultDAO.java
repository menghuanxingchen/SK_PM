package com.sqm.TestResult.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_result 的DAO类
 * @author 刘镝
 */
@Component("TestResultDAO")
public class TestResultDAO extends BaseDao {
	public final static String QueryTableSql = "select t.* from test_result t where 1=1 ";

	/**
	 * 检索绑定
	 * @author 刘镝
	 */
	private JDBCHelper makeSearch(TestResult entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. lotnumver", JDBCHelper.EQ, entiy.getLotnumver());
			jdbcHelper.putParam(JDBCHelper.AND,"T. produnctCode", JDBCHelper.EQ, entiy.getProdunctCode());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
			jdbcHelper.putParam(JDBCHelper.AND,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_num", JDBCHelper.EQ, entiy.getUpdate_num());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_cause", JDBCHelper.EQ, entiy.getUpdate_cause());
			jdbcHelper.putParam(JDBCHelper.AND,"T. testResult", JDBCHelper.EQ, entiy.getTestResult());
			jdbcHelper.putParam(JDBCHelper.AND,"T. testItem", JDBCHelper.EQ, entiy.getTestItem());
			jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. rState", JDBCHelper.EQ, entiy.getrState());
		}
		return jdbcHelper;
	}

	/**
	 * 更新绑定
	 * @author 刘镝
	 */
	private JDBCHelper makeUpd(TestResult entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. lotnumver", JDBCHelper.EQ, entiy.getLotnumver());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. produnctCode", JDBCHelper.EQ, entiy.getProdunctCode());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_num", JDBCHelper.EQ, entiy.getUpdate_num());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. update_cause", JDBCHelper.EQ, entiy.getUpdate_cause());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. testResult", JDBCHelper.EQ, entiy.getTestResult());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. testItem", JDBCHelper.EQ, entiy.getTestItem());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. testItemName", JDBCHelper.EQ, entiy.getTestItemName());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. rState", JDBCHelper.EQ, entiy.getrState());
		}
		return jdbcHelper;
	}

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<TestResult> queryTestResultList(TestResult entity, int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), TestResult.class, pageIndex, pageSize);
	}

	/**
	 * 查询entity List
	 * @author 刘镝
	 */
	public List<TestResult> queryTestResultList(TestResult entity) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), TestResult.class, 1, -1).getRecords();
	}

	/**
	 * 查询返回entity(首行)
	 * @author 刘镝
	 */
	public TestResult queryTestResultEO(TestResult entity) {
		List<TestResult> list = queryTestResultList(entity);
		if(list.size() > 0){
			return list.get(0);
		}else{
			return new TestResult();
		}
	}

	/**
	 * 保存实体对象entity
	 * @author 刘镝
	 */
	public String saveTestResult(TestResult entity) {
		if(entity.getId()==null)
			entity.setId(this.getUUId());
		this.saveNew(entity);
		return entity.getId();
	}

	/**
	 * 修改实体对象entity
	 * @author 刘镝
	 */
	public int updateTestResult(TestResult entity) {
		int flag =  this.saveUpdate(entity);
		return flag;
	}

	/**
	 * 根据条件修改信息
	 * @author 刘镝
	 */
	public int specialUpdTestResult(TestResult entity){
		JDBCHelper helper = makeUpd(entity, "");
		String sql = "update test_result t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
		return this.executeSQL(sql,helper.getParam());
	}
	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	public int deleteTestResult(TestResult entity) {
		int flag =  this.saveRemove(entity);
		return flag;
	}

	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	public TestResult getTestResultById(String id) {
		return this.getEntityById(id, TestResult.class);
	}

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<TestResult> queryDataList(TestResult entity, int pageIndex, int pageSize,String type) {
		String sql = "select t.*,s.user_nm as create_name,m.equipmentName from test_result t,sys_user_info s,sqm_equipment_state m  where 1=1 and t.create_id = s.user_num and t.equipmentId=m.equipmentId and t.lineId=m.lineId ";
		if(!StringUtils.isNullOrEmpty(entity.getLineId())){
			sql = sql + " and t.lineId = '" + entity.getLineId() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getLotnumver())){
			sql = sql + " and t.lotnumver = '" + entity.getLotnumver() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getProdunctCode())){
			sql = sql + " and t.produnctCode = '" + entity.getProdunctCode() + "'";
		}
		if("1".equals(type)) {
			sql = sql + " and t.create_id != '" + entity.getCreate_id() + "'";
			entity.setCreate_id(null);
		}
		if(entity.getTest_num()!=null){
			sql = sql + " and t.test_num = '" + entity.getTest_num() + "'";
		}
		if(entity.getPlanId()!=null){
			sql = sql + " and t.planId = '" + entity.getPlanId() + "'";
		}
		if(entity.getCreate_time()!=null){
			sql = sql + " and t.create_time like '%" + entity.getCreate_time().substring(0, 10) + "%'";
		}
		if(entity.getEquipmentId()!=null){
			sql = sql + " and t.equipmentId = '" + entity.getEquipmentId() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getCreate_id())){
			sql = sql + " and t.create_id = '" + entity.getCreate_id() + "'";
		}
		sql = sql + " order by t.create_time  desc,t.create_id,t.test_num desc";
		return getPage(sql, null, TestResult.class, pageIndex, pageSize);
	}
	
	/**
	 * 不分页查询entity List
	 * @author 刘镝
	 */
	public List<TestResult> queryDataList(TestResult entity,String type) {
		String sql = "select t.*,s.user_nm as create_name,m.equipmentName from test_result t,sys_user_info s,sqm_equipment_state m  where 1=1 and t.create_id = s.user_num and t.equipmentId=m.equipmentId and t.lineId=m.lineId ";
		if(!StringUtils.isNullOrEmpty(entity.getLineId())){
			sql = sql + " and t.lineId = '" + entity.getLineId() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getLotnumver())){
			sql = sql + " and t.lotnumver = '" + entity.getLotnumver() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getProdunctCode())){
			sql = sql + " and t.produnctCode = '" + entity.getProdunctCode() + "'";
		}
		if("1".equals(type)) {
			sql = sql + " and t.create_id != '" + entity.getCreate_id() + "'";
			entity.setCreate_id(null);
		}
		if(entity.getTest_num()!=null){
			sql = sql + " and t.test_num = '" + entity.getTest_num() + "'";
		}
		if(entity.getPlanId()!=null){
			sql = sql + " and t.planId = '" + entity.getPlanId() + "'";
		}
		if(entity.getCreate_time()!=null){
			sql = sql + " and t.create_time like '%" + entity.getCreate_time().substring(0, 10) + "%'";
		}
		if(entity.getEquipmentId()!=null){
			sql = sql + " and t.equipmentId = '" + entity.getEquipmentId() + "'";
		}
		if(!StringUtils.isNullOrEmpty(entity.getCreate_id())){
			sql = sql + " and t.create_id = '" + entity.getCreate_id() + "'";
		}
		sql = sql + " order by t.create_time  desc,t.create_id,t.test_num desc";
		return queryForList(sql,TestResult.class);
	}


	/**
	 * 查询当前结束了几轮检测
	 * @author 刘镝
	 */
	public int queryDataTestNum(String create_id,String planId){
		String sql = "select max(test_num) test_num from test_result where  planId ='"+planId+"'  and create_id='"+create_id+"'";
		List<TestResult> list = this.queryForList(sql,TestResult.class);
		return list.get(0).getTest_num()==null?0:list.get(0).getTest_num();
	}

	/**
	 * 查询本次检测项历史
	 * @author 刘镝
	 */
	public TestResult queryTestResult(String planId,String create_id,int test_num,String testItem){
		String sql = "select *  from test_result where  create_id='"+create_id+"' and planId='"+planId+"'  and test_num="+test_num+" and testItem='"+testItem+"'";
		List<TestResult> list = this.queryForList(sql,TestResult.class);
		return list.size()>0?list.get(0):null;
	}


	/**
	 * 查询最近一条检测记录
	 * @author 刘镝
	 */
	public TestResult queryTestResultLately(String planId){
		String sql = "SELECT * from test_result WHERE  planId='"+planId+"'  ORDER BY create_time DESC LIMIT 0,1 ";
		List<TestResult> list = this.queryForList(sql,TestResult.class);
		return  list.size()==0?null:list.get(0);
	}


	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	public List<TestResult> queryDataList(TestResult entity){
		String sql = "select * from test_result where 1=1 ";
		return this.queryForList(sql,TestResult.class);
	}

	/**
	 * 通过推送push_id获取需要修改的检测结果集合
	 * @author 刘镝
	 */
	public List<TestResult> queryTestResultListByPush(SqmPushInfo entity,String inspector){
		String sql = " SELECT k.*,i.equipmentName FROM test_result k,(select * from test_result WHERE id= (SELECT push_source  FROM sqm_push_info WHERE push_id ='"+entity.getPush_id()+"')) t," 
				+" test_item_info i WHERE "
				+" k.lineId = t.lineId and k.lotnumver=t.lotnumver and k.produnctCode=t.produnctCode "
				+" and k.test_num=t.test_num and t.create_time LIKE CONCAT('%',substring(k.create_time, 1, 10),'%') and k.testItem=i.id  and k.equipmentId=t.equipmentId ";
		if(!StringUtils.isNullOrEmpty(inspector)) {
			sql+=" and k.create_id ='"+inspector+"'";//检测员
		}
		return this.queryForList(sql,TestResult.class);
	}

	/**
	 * 通过推送push_id获取需要修改的检测项集合
	 * @author 刘镝
	 */
	public List<TestItemInfo> queryTestItemInfoListByPush(SqmPushInfo entity,String inspector){
		String sql = "SELECT i.*,k.testResult,k.test_num,k.id as testResultId,k.planId FROM test_result k,(select * from test_result WHERE id= (SELECT push_source  FROM sqm_push_info WHERE push_id ='"+entity.getPush_id()+"')) t," 
				+" test_item_info i WHERE "
				+" k.lineId = t.lineId and k.lotnumver=t.lotnumver and k.produnctCode=t.produnctCode "
				+" and k.test_num=t.test_num and t.create_time LIKE CONCAT('%',substring(k.create_time, 1, 10),'%') and k.testItem=i.id  and k.equipmentId=t.equipmentId ";
		if(!StringUtils.isNullOrEmpty(inspector)) {
			sql+=" and k.create_id ='"+inspector+"' order by i.orderNum";//检测员
		}
		return this.queryForList(sql,TestItemInfo.class);
	}

	/**
	 * 通过灌装计划信息获取需要修改的检测项集合
	 * @author 刘镝
	 */
	public List<TestItemInfo> QueryTestResultListByPlan(TestResult testResult){
		String sql = "SELECT i.*,t.planId,t.test_num,t.id as testResultId,t.testResult FROM test_result t,test_item_info i WHERE t.testItem=i.id ";
		sql+="AND t.planId='"+testResult.getPlanId()+"' AND t.create_id='"+testResult.getCreate_id()+"' AND t.equipmentId='"+testResult.getEquipmentId()+"' AND t.test_num= "+testResult.getTest_num()+" ";//检测员
		return this.queryForList(sql,TestItemInfo.class);
	}

	/**
	 * DB存在check
	 * @author 刘镝
	 */
	public Boolean checkTestResultEO(TestResult entity) {
		String sql = "";
		TestResult eo = entity.clone();
		if(StringUtils.isNullOrEmpty(entity.getId())){
			sql = "select count(*) from test_result t where 1 = 1 ";
		}else{
			sql = "select count(*) from test_result t where t.id <> '" + entity.getId() +"'";
			eo.setId("");
		}

		JDBCHelper helper = makeSearch(eo, sql);
		if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
			return true;
		}
		return false;
	}

	/**
	 * 根据条件修改信息
	 * @author 刘镝
	 */
	public int insertTestResultInfo(String sql){
		return this.executeSQL(sql);
	}

	/**
	 * 查询已经检测过的设备id
	 * @author 刘镝
	 */
	public List<TestItemInfo> QueryOldTestResultEquipmentIdList(TestResult testResult){
		String sql = "SELECT distinct equipmentId from test_result WHERE 1=1";
		sql+=" AND t.planId='"+testResult.getPlanId()+"' AND t.create_id='"+testResult.getCreate_id()+"' AND t.test_num= "+testResult.getTest_num()+" ";
		return this.queryForList(sql,TestItemInfo.class);
	}
	
	/**
	 * 修改部分检测项结果为正常状态
	 * @author 刘镝
	 */
	public int updateTestResultToNormal(TestResult testResult,List<String> itemIds){
		String sql = "UPDATE test_result SET rState='1' WHERE planId='"+testResult.getPlanId()+"' and equipmentId ='"+testResult.getEquipmentId()+"'"
				+ " and test_num="+testResult.getTest_num()+" and testItem NOT in (";
		for (String string : itemIds) {
			sql+="'"+string+"',";
		}
		sql=sql.substring(0, sql.length()-1)+")";
		return this.executeSQL(sql);
	}
	
	/**
	 * 修改部分检测项结果为与他人不一致状态
	 * @author 刘镝
	 */
	public int updateTestResultToDisaccord(TestResult testResult,List<String> itemIds){
		String sql = "UPDATE test_result SET rState='3' WHERE  planId='"+testResult.getPlanId()+"' and equipmentId ='"+testResult.getEquipmentId()+"' "
				+ " and test_num="+testResult.getTest_num()+"  and testItem in (";
		for (String string : itemIds) {
			sql+="'"+string+"',";
		}
		sql=sql.substring(0, sql.length()-1)+")";
		return this.executeSQL(sql);
	}
	
	/**
	 * 修改全部检测项结果为正常状态
	 * @author 刘镝
	 */
	public int updateTestResultAllToNormal(TestResult testResult){
		String sql = "UPDATE test_result SET rState='1' WHERE planId='"+testResult.getPlanId()+"' and equipmentId ='"+testResult.getEquipmentId()+"'"
				+ " and test_num="+testResult.getTest_num()+"";
		return this.executeSQL(sql);
	}

}
