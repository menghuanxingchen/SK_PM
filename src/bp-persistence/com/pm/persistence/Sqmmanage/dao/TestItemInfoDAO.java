package com.pm.persistence.Sqmmanage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysUserInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表 的DAO类
 * @author 刘镝
 */
@Component("TestItemInfoDAO")
public class TestItemInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from test_item_info t  WHERE 1=1 ";

/**
 * 妫�绱㈢粦瀹�
 * @author 鍒橀暆
 */
  private JDBCHelper makeSearch(TestItemInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
    	 jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
         jdbcHelper.putParam(JDBCHelper.AND,"T. lineId", JDBCHelper.EQ, entiy.getLineId());
         jdbcHelper.putParam(JDBCHelper.AND,"T. lineName", JDBCHelper.EQ, entiy.getLineName());
         jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
         jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentName", JDBCHelper.EQ, entiy.getEquipmentName());
         jdbcHelper.putParam(JDBCHelper.AND,"T. productCode", JDBCHelper.EQ, entiy.getProductCode());
         jdbcHelper.putParam(JDBCHelper.AND,"T. productName", JDBCHelper.EQ, entiy.getProductName());
         jdbcHelper.putParam(JDBCHelper.AND,"T. testItem", JDBCHelper.EQ, entiy.getTestItem());
         jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
         jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
         jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
         jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
         jdbcHelper.putParam(JDBCHelper.AND,"T. userYn", JDBCHelper.EQ, entiy.getUserYn());
      }
      return jdbcHelper;
  }
  
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
  public Pagination<TestItemInfo> queryTestItemInfoList(TestItemInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    helper.setDefaultOrderBy("t.lineId,t.productCode,t.orderNum");
    return getPage(helper.getSql(), helper.getParam(), TestItemInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveTestItemInfo(TestItemInfo entity) {
	entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateTestItemInfo(TestItemInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteTestItemInfo(TestItemInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public TestItemInfo getTestItemInfoById(String id) {
	  String sql = "select * from test_item_info where 1 = 1 and id ='"+id+"'";
      return this.queryObject(sql, TestItemInfo.class);
  }
  /**
   * 根据实体对象Id返回entity
   * @author 刘镝
   */
    public List<TestItemInfo> queryTestItemTemplatList(TestItemInfo testItemInfo) {
  	  String sql = "select * from test_item_template where 1 = 1 and lineId ='"+testItemInfo.getLineId()+"'";
        return this.queryForList(sql, TestItemInfo.class);
    }
    
    /**
     * 查询检测项
     * @author 刘镝
     */
      public List<TestItemInfo> queryTestItemInfoList(String lineId,String equipmentId,String productCode) {
    	  String sql = "select * from test_item_info where 1=1 ";
    	  if(!StringUtils.isNullOrEmpty(lineId)) {
    		  sql +=" and lineId ='"+lineId+"'";
    	  }
    	  if(!StringUtils.isNullOrEmpty(equipmentId)) {
    		  sql +=" and equipmentId ='"+equipmentId+"'";
    	  }
    	  if(!StringUtils.isNullOrEmpty(productCode)) {
    		  sql +=" and productCode ='"+productCode+"'";
    	  }
    	  sql=sql+" and userYn='Y' order by orderNum";
          return this.queryForList(sql, TestItemInfo.class);
      }
      
      /**
       * 查询灌装计划与检测流程、检测项是否匹配成功
       * @author 刘镝
       */
        public boolean queryFillingPlanAndTestingProcessAndTestingItmeMatchingYn(String planId) {
      	  String sql = "select n.id from  filling_plan_info i,testing_process_info t, test_item_info n "
      	  		+ " where t.productCode =n.productCode AND i.lineId=t.lineId AND  t.lineId=n.lineId and t.equipmentId=n.equipmentId  and  t.productCode= CONCAT(i.PKG,i.PROD)  and i.id="+planId+"";
            return this.queryForList(sql, TestItemInfo.class).size()>0?true:false;
        }
        
     /**
      * 联合查询检测结果与表字段对应关系
      * @author 刘镝
      */
       public List<TestItemInfo> queryContrastTestItemInfo(String planId) {
    	   String sql = " SELECT  m.id,m.testResultNoID,m.tableName,t.testResult  from test_result t,test_item_info m  WHERE t.testItem=m.id  and  t.planId ='"+planId+"' AND m.testResultNoID !='' AND  m.testResultNoID is not null GROUP BY t.testItem"; 
            return this.queryForList(sql, TestItemInfo.class);
       }
      
	/**
	 * 判断是否添加生产线
	 * @author 刘镝
	 */
	public Boolean checkLineItemInfo(TestItemInfo testItemInfo) {
		  String sql = "select count(*) from test_item_info where 1 = 1 and lineId ='"+testItemInfo.getLineId()+"'"
		  		+ " and productCode='"+testItemInfo.getProductCode()+"'";
		  
	  if(this.queryForInt(sql, null) > 0){
	        return true;
	    }
	    return false;
	}
	
    /**
     * 查询检测项全部状态的
     * @author 刘镝
     */
      public List<TestItemInfo> queryTestItemInfoListAllState(String lineId,String productCode,String equipmentName,String testItem) {
    	  String sql = "select * from test_item_info where 1=1 ";
    	  if(!StringUtils.isNullOrEmpty(lineId)) {
    		  sql +=" and lineId ='"+lineId+"'";
    	  }
    	  if(!StringUtils.isNullOrEmpty(productCode)) {
    		  sql +=" and productCode ='"+productCode+"'";
    	  }
    	  if(!StringUtils.isNullOrEmpty(equipmentName)) {
    		  sql +=" and equipmentName ='"+equipmentName+"'";
    	  }
    	  if(!StringUtils.isNullOrEmpty(testItem)) {
    		  sql +=" and testItem ='"+testItem+"'";
    	  }
          return this.queryForList(sql, TestItemInfo.class);
      }
      /**
       * 根据检测项名称删除
       * @author 刘镝
       */
        public int deleteTestItemInfo(String testItem) {
      	    String sql = "DELETE FROM test_item_info WHERE testItem='"+testItem+"'";
            return this.executeSQL(sql);
        }
}
