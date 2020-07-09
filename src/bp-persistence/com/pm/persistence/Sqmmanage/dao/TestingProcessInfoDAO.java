package com.pm.persistence.Sqmmanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysUserInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表 的DAO类
 * @author 刘镝
 */
@Component("TestingProcessInfoDAO")
public class TestingProcessInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select T.* from testing_process_info T  WHERE 1=1   ";

/**
 * 妫�绱㈢粦瀹�
 * @author 鍒橀暆
 */
  private JDBCHelper makeSearch(TestingProcessInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. lineName", JDBCHelper.LIKE, entiy.getLineName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. productCode", JDBCHelper.LIKE, entiy.getProductCode());
              jdbcHelper.putParam(JDBCHelper.AND,"T. productName", JDBCHelper.LIKE, entiy.getProductName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentName", JDBCHelper.LIKE, entiy.getEquipmentName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. useYn", JDBCHelper.EQ, entiy.getUseYn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. orderNum", JDBCHelper.EQ, entiy.getOrderNum());
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
  public Pagination<TestingProcessInfo> queryTestingProcessInfoList(TestingProcessInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    helper.setDefaultOrderBy("T.lineId,T.productCode,T.orderNum");
    return getPage(helper.getSql(), helper.getParam(), TestingProcessInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveTestingProcessInfo(TestingProcessInfo entity) {
	entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateTestingProcessInfo(TestingProcessInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteTestingProcessInfo(TestingProcessInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public TestingProcessInfo getTestingProcessInfoById(String id) {
	  String sql = "select * from testing_process_info where 1 = 1 and id ='"+id+"'";
      return this.queryObject(sql, TestingProcessInfo.class);
  }
  
  
  
    public List<TestingProcessInfo> queryTestingProcessInfoList(TestingProcessInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSql);
        return queryForList(helper.getSql(), helper.getParam(), TestingProcessInfo.class);
      }
    
    public List<TestingProcessInfo> queryTestingProcessTempInfoList(TestingProcessInfo entity) {
        JDBCHelper helper = makeSearch(entity, "select T.* from testing_process_info_temp T where 1=1");
        return queryForList(helper.getSql(), helper.getParam(), TestingProcessInfo.class);
      }
    /**
	 * 判断是否添加生产线
	 * @author 刘镝
	 */
	public Boolean checkTestingProcessInfo(TestingProcessInfo testingProcessInfo) {
		  String sql = "select count(*) from testing_process_info where 1 = 1 and lineId ='"+testingProcessInfo.getLineId()+"'"
		  		+ " and productCode='"+testingProcessInfo.getProductCode()+"'";
		  
	  if(this.queryForInt(sql, null) > 0){
	        return true;
	    }
	    return false;
	}
	
	/**
	 * 查询产品本人检测流程
	 * @author 刘镝
	 */
	  public List<TestingProcessInfo> queryTestingProcessInfoListNopage(TestingProcessInfo entity,Processor processor) {
		  String sql = "select t.*,s.eState as testState,s.eStateName as testStateName from testing_process_info t,sqm_equipment_every_state s where  t.lineId=s.lineId AND t.equipmentId=s.equipmentId  ";
		  if(!StringUtils.isNullOrEmpty(entity.getProductCode())) {//产品code
			  sql=sql+ " and t.productCode='"+entity.getProductCode()+"'";
		  }
		  if(!StringUtils.isNullOrEmpty(entity.getLineId())) {//生产线id
			  sql=sql+ " and t.lineId='"+entity.getLineId()+"'";
		  }
		  if(!StringUtils.isNullOrEmpty(entity.getEquipmentId())) {//设备id
			  sql=sql+ " and t.equipmentId='"+entity.getEquipmentId()+"'";
		  }
		  sql=sql+" and s.inspector='"+processor.getCurrentUserId()+"' ";
		  sql=sql+" order by t.orderNum ";
	      return this.queryForList(sql,TestingProcessInfo.class);
	  }
	  
		/**
		 * 查询产品监测流程
		 * @author 刘镝
		 */
		  public List<TestingProcessInfo> queryTestingProcessInfoListMonitor(TestingProcessInfo entity) {
			  String sql = "select t.*,s.eState as testState,s.eStateName as testStateName from testing_process_info t,sqm_equipment_state s where  t.lineId=s.lineId AND t.equipmentId=s.equipmentId  ";
			  if(!StringUtils.isNullOrEmpty(entity.getProductCode())) {//产品code
				  sql=sql+ " and t.productCode='"+entity.getProductCode()+"'";
			  }
			  if(!StringUtils.isNullOrEmpty(entity.getLineId())) {//生产线id
				  sql=sql+ " and t.lineId='"+entity.getLineId()+"'";
			  }
			  if(!StringUtils.isNullOrEmpty(entity.getEquipmentId())) {//设备id
				  sql=sql+ " and t.equipmentId='"+entity.getEquipmentId()+"'";
			  }
			  sql=sql+" order by t.orderNum ";
		      return this.queryForList(sql,TestingProcessInfo.class);
		  }
	  
	 /**
	  * 根据实体对象productCode、lineId返回entity
	  * @author 刘镝
	  */
	   public List<TestingProcessInfo> getTestingProcessInfoByProductCode(String productCode,String lineId) {
	    String sql = "select * from testing_process_info where  productCode ='"+productCode+"' and lineId='"+lineId+"' ORDER BY orderNum";
	       return this.queryForList(sql, TestingProcessInfo.class);
	   }
	   
	/**
	* 根据实体对象lineId返回entity
	* @author 刘镝
	*/
	public List<TestingProcessInfo> getTestingProcessInfoTempByLineId(String lineId) {
	     String sql = "select * from testing_process_info_temp where  lineId='"+lineId+"' ORDER BY orderNum";
	      return this.queryForList(sql, TestingProcessInfo.class);
    }
	
	/**
	* 根据实体对象lineId返回entity
	* @author 刘镝
	*/
	public List<TestingProcessInfo> getTestingProcessInfoInLineId(String lineIds) {
	     String sql = "SELECT lineId,lineName,productCode,productName from  testing_process_info WHERE lineId in ("+lineIds+")   GROUP BY productCode,lineId ORDER BY productCode ";
	      return this.queryForList(sql, TestingProcessInfo.class);
    }
}
