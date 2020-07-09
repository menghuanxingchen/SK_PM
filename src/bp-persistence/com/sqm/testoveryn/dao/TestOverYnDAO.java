package com.sqm.testoveryn.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
import com.sqm.testoveryn.entity.TestOverYn;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_over_yn 的DAO类
 * @author 刘镝
 */
@Component("TestOverYnDAO")
public class TestOverYnDAO extends BaseDao {
	public final static String QueryTableSql = "select t.* from test_over_yn t where 1=1 ";

	/**
	 * 检索绑定
	 * @author 刘镝
	 */
	private JDBCHelper makeSearch(TestOverYn entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. plan_id", JDBCHelper.EQ, entiy.getPlan_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
			jdbcHelper.putParam(JDBCHelper.AND,"T. over_yn", JDBCHelper.EQ, entiy.getOver_yn());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
		}
		return jdbcHelper;
	}

	/**
	 * 更新绑定
	 * @author 刘镝
	 */
	private JDBCHelper makeUpd(TestOverYn entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. plan_id", JDBCHelper.EQ, entiy.getPlan_id());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. test_num", JDBCHelper.EQ, entiy.getTest_num());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. over_yn", JDBCHelper.EQ, entiy.getOver_yn());
			jdbcHelper.putParam(JDBCHelper.COMMA,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());

		}
		return jdbcHelper;
	}

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<TestOverYn> queryTestOverYnList(TestOverYn entity, int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), TestOverYn.class, pageIndex, pageSize);
	}

	/**
	 * 查询entity List
	 * @author 刘镝
	 */
	public List<TestOverYn> queryTestOverYnList(TestOverYn entity) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), TestOverYn.class, 1, -1).getRecords();
	}

	/**
	 * 查询返回entity(首行)
	 * @author 刘镝
	 */
	public TestOverYn queryTestOverYnEO(TestOverYn entity) {
		List<TestOverYn> list = queryTestOverYnList(entity);
		if(list.size() > 0){
			return list.get(0);
		}else{
			return new TestOverYn();
		}
	}

	/**
	 * 保存实体对象entity
	 * @author 刘镝
	 * @throws Exception 
	 */
	public int saveTestOverYn(TestOverYn entity) throws Exception {
		entity.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
		if(entity.getId()==null)
			entity.setId(this.getUUId());
		int flag =  this.saveNew(entity);
		return flag;
	}

	/**
	 * 修改实体对象entity
	 * @author 刘镝
	 */
	public int updateTestOverYn(TestOverYn entity) {
		int flag =  this.saveUpdate(entity);
		return flag;
	}

	/**
	 * 根据条件修改信息
	 * @author 刘镝
	 */
	public int specialUpdTestOverYn(TestOverYn entity){
		JDBCHelper helper = makeUpd(entity, "");
		String sql = "update test_over_yn t set "+ helper.getUpdSql() +" where t.id ='" + entity.getId() +"'";
		return this.executeSQL(sql,helper.getParam());
	}
	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	public int deleteTestOverYn(TestOverYn entity) {
		int flag =  this.saveRemove(entity);
		return flag;
	}

	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	public TestOverYn getTestOverYnById(String id) {
		return this.getEntityById(id, TestOverYn.class);
	}

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<TestOverYn> queryDataList(TestOverYn entity, int pageIndex, int pageSize) {
		String sql = "select t.* from test_over_yn t where 1=1 ";
		//if(!StringUtils.isNullOrEmpty(entity.getId())){
		//  sql = sql + " and id like '%" + entity.getId() + "%'";
		//}
		//sql = sql + " order by id desc";
		return getPage(sql, null, TestOverYn.class, pageIndex, pageSize);
	}

	/**
	 * 查询第几检测
	 * @author 刘镝
	 */
	public TestOverYn queryTestOverYn(String planId,String createId){
		String sql = "SELECT * from test_over_yn WHERE  plan_id='"+planId+"' and create_id='"+createId+"'  ORDER BY test_num DESC LIMIT 0,1 ";
		List<TestOverYn> list = this.queryForList(sql,TestOverYn.class);
		return  list.size()==0?new TestOverYn():list.get(0);
	}
	
	/**
	 * 查询最近谁在检测
	 * @author 刘镝
	 */
	public TestOverYn queryTestOverYnLately(String planId){
		String sql = "SELECT * from test_over_yn WHERE  plan_id='"+planId+"'   ORDER BY create_time DESC LIMIT 0,1 ";
		List<TestOverYn> list = this.queryForList(sql,TestOverYn.class);
		return  list.size()==0?new TestOverYn():list.get(0);
	}
	
	/**
	 * 查询检测员及线长已检测完的次数
	 * @author 刘镝
	 */
	public int queryTestOverYesNum(String planId){
		String sql = "SELECT t.* from  test_over_yn t LEFT JOIN sys_user_info s on t.create_id=s.user_num WHERE t.over_yn='y' AND s.sqm_role_code in ('1','2') and t.plan_id='"+planId+"'";
		List<TestOverYn> list = this.queryForList(sql,TestOverYn.class);
		return  list.size();
	}	

	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	public List<TestOverYn> queryDataList(TestOverYn entity){
		String sql = "select * from test_over_yn where 1=1 ";
		return this.queryForList(sql,TestOverYn.class);
	}
	
	/**
	 * 根据planId查询List
	 * @author 刘镝
	 */
	public List<TestOverYn> queryDataListByPlanId(String planId){
		String sql = "select * from test_over_yn where 1=1 and plan_id='"+planId+"'";
		return this.queryForList(sql,TestOverYn.class);
	}

	/**
	 * DB存在check
	 * @author 刘镝
	 */
	public Boolean checkTestOverYnEO(TestOverYn entity) {
		String sql = "";
		TestOverYn eo = entity.clone();
		if(StringUtils.isNullOrEmpty(entity.getId())){
			sql = "select count(*) from test_over_yn t where 1 = 1 ";
		}else{
			sql = "select count(*) from test_over_yn t where t.id <> '" + entity.getId() +"'";
			eo.setId("");
		}

		JDBCHelper helper = makeSearch(eo, sql);
		if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据罐装计划修改信息
	 * @author 刘镝
	 */
	public int specialUpdTestOverYnByPlanId(String over_yn,String plan_id){
		String sql = "update test_over_yn t set over_yn ='"+ over_yn +"' where t.plan_id ='" + plan_id +"'";
		return this.executeSQL(sql);
	}

}
