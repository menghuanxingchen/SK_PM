package com.repast.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysUserInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sys_user_info 的DAO类
 * @author 刘镝
 */
@Component("SysUserInfoDAO")
public class SysUserInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* ,i.code_nm as sub_dept_nm"
  		+ " FROM sys_user_info T LEFT JOIN (select code_value ,sub_code_group_value from sys_code_info where code_group_value = 'depart_group' )  as c on T.dept_code = c.code_value "
  		+ " LEFT JOIN sys_code_info i on c.sub_code_group_value = i.code_group_value and T.sub_dept_code = i.code_value "
  		+ " LEFT JOIN (select count(id) cnt,create_id from proposal_info where  left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P on t.user_num = p.create_id WHERE 1=1 ";
  
  public final static String QueryTableSqlByReport = "SELECT  T.* ,i.code_nm as sub_dept_nm,IFNULL(p.cnt,0) as reportCount,IFNULL(p1.cnt,0) as tongYiCount,IFNULL(p2.cnt,0) as boHuiCount,IFNULL(p3.cnt,0) as weiShenCount"+
	" FROM sys_user_info T "+
    " LEFT JOIN (select code_value ,sub_code_group_value from sys_code_info where code_group_value = 'depart_group' )  as c on T.dept_code = c.code_value "+
	" LEFT JOIN sys_code_info i on c.sub_code_group_value = i.code_group_value and T.sub_dept_code = i.code_value "+
    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where  left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P on t.user_num = p.create_id"+
    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_state<>1 and approval_state<>6 and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P1 on t.user_num = p1.create_id"+
    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_step=2 and approval_state=6  and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P2 on t.user_num = p2.create_id"+
    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_step=2  and approval_state=1  and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P3 on t.user_num = p3.create_id"+
    " WHERE 1=1 and t.email<>'' and t.user_yn<>'0'";
  
  public final static String QueryTableSqlByAll = "SELECT  T.* ,i.code_nm as sub_dept_nm,IFNULL(p.cnt,0) as reportCount,"
  		    + "                                               IFNULL(p1.cnt,0) as tongYiCount,IFNULL(p2.cnt,0) as boHuiCount,IFNULL(p3.cnt,0) as weiShenCount"+
			" FROM sys_user_info T "+
		    " LEFT JOIN (select code_value ,sub_code_group_value from sys_code_info where code_group_value = 'depart_group' )  as c on T.dept_code = c.code_value "+
			" LEFT JOIN sys_code_info i on c.sub_code_group_value = i.code_group_value and T.sub_dept_code = i.code_value "+
		    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where   "
		    + "                  left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P on t.user_num = p.create_id"+
		    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_state<>1 and approval_state<>6"
		    + "                 and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P1 on t.user_num = p1.create_id"+
		    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_step=2 and approval_state=6  "
		    + "                 and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P2 on t.user_num = p2.create_id"+
		    " LEFT JOIN (select count(id) cnt,create_id from proposal_info where approval_step=2  and approval_state=1  "
		    + "                 and left(create_time,4) = DATE_FORMAT(NOW(), '%Y')  GROUP BY create_id  ) as P3 on t.user_num = p3.create_id"+
		    " WHERE 1=1 and t.user_yn<>'0'";
		  
  private JDBCHelper makeSearch(SysUserInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_id", JDBCHelper.EQ, entiy.getUser_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_num", JDBCHelper.LIKE, entiy.getUser_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pwd", JDBCHelper.EQ, entiy.getPwd());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_nm", JDBCHelper.LIKE, entiy.getUser_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. position_id", JDBCHelper.EQ, entiy.getPosition_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
              jdbcHelper.putParam(JDBCHelper.AND,"T. email", JDBCHelper.EQ, entiy.getEmail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. mobile", JDBCHelper.EQ, entiy.getMobile());
              jdbcHelper.putParam(JDBCHelper.AND,"T. Telephone", JDBCHelper.EQ, entiy.getTelephone());
              jdbcHelper.putParam(JDBCHelper.AND,"T. auth_code", JDBCHelper.EQ, entiy.getAuth_code());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"IFNULL(p.cnt,0)", JDBCHelper.GTQ, entiy.getTongYiCount());
      }
     if(!StringUtils.isNullOrEmpty(entiy.getOrderType())){
    	 jdbcHelper.putOrder(entiy.getOrderType());
     }
      return jdbcHelper;
  }
  private JDBCHelper makeSearch2(SysUserInfo entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. user_id", JDBCHelper.EQ, entiy.getUser_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. user_num", JDBCHelper.LIKE, entiy.getUser_num());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. pwd", JDBCHelper.EQ, entiy.getPwd());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. user_nm", JDBCHelper.LIKE, entiy.getUser_nm());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. position_id", JDBCHelper.EQ, entiy.getPosition_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. email", JDBCHelper.EQ, entiy.getEmail());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. mobile", JDBCHelper.EQ, entiy.getMobile());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. Telephone", JDBCHelper.EQ, entiy.getTelephone());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. auth_code", JDBCHelper.EQ, entiy.getAuth_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
	              jdbcHelper.putOrder("T.position_id,T.dept_code,T.sub_dept_code");
	      }
	      return jdbcHelper;
	  }
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
  public Pagination<SysUserInfo> querySysUserInfoList(SysUserInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), SysUserInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveSysUserInfo(SysUserInfo entity) {
	  if(entity.getUser_id()==null)
			entity.setUser_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateSysUserInfo(SysUserInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteSysUserInfo(SysUserInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public SysUserInfo getSysUserInfoById(String id) {
	  String sql = "select a.*,b.code_nm as dept_code_nm from sys_user_info a "
	  		+ "left join sys_code_info b on a.dept_code=b.code_value and b.code_group_value='depart_group' "
	  		+ "where 1 = 1 and a.user_num ='"+id+"'";
      return this.queryObject(sql, SysUserInfo.class);
  }
  
  /**
   * 根据实体对象Id返回entity
   * @author 刘镝
   */
    public SysUserInfo getSysUserInfoByIdOld(String id) {
    	return this.getEntityById(id, SysUserInfo.class);
    }
  /**
   * 登录信息
   * @author fish
   */
    public SysUserInfo getLoginInfo(String userNum ,String pwd) {
      String sql = "select * from sys_user_info where 1 = 1 and user_num ='"+userNum+"' and pwd = '"+pwd+"'";
      return this.queryObject(sql, SysUserInfo.class);
    }
    
    public List<SysUserInfo> querySysUserInfoList(SysUserInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSql);
        return queryForList(helper.getSql(), helper.getParam(), SysUserInfo.class);
      }
    
    public List<SysUserInfo> querySysUserInfoListForDealUser(SysUserInfo entity) {
        JDBCHelper helper = makeSearch2(entity, QueryTableSql);
        return queryForList(helper.getSql(), helper.getParam(), SysUserInfo.class);
      }
    
    public List<SysUserInfo> querySysUserInfoListByReport(SysUserInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSqlByReport);
        return queryForList(helper.getSql(), helper.getParam(), SysUserInfo.class);
      }
    public List<SysUserInfo> querySysUserInfoListByAll(SysUserInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSqlByAll);
        return queryForList(helper.getSql(), helper.getParam(), SysUserInfo.class);
      }
    
    /**
     * sqm评价人
     * @author gao
     */
    public List<SysUserInfo> getEvaluationList() {
        String sql="select * from sys_user_info where 1=1 and user_num in('lg00302','lg00158','lg00157','lg00064')";
        return queryForList(sql, null,SysUserInfo.class);
    }
    
    /**
     * 搬入搬出
     * @author gao
     */
    public List<SysUserInfo> querySysUserInfoListFormoving() {
        String sql="select * from sys_user_info where 1=1 and position_id not in('1','2','')";
        return queryForList(sql, null,SysUserInfo.class);
    }
    
    /**
     * 查询所有用户推送id
     * @author gao
     */
    public List<SysUserInfo> queryRegistrationIDs() {
        String sql="SELECT  GROUP_CONCAT(distinct registrationID SEPARATOR ',') registrationID FROM  sys_user_info where registrationID is not null and registrationID !=''";
        return queryForList(sql, null,SysUserInfo.class);
    }
    
    /**
     * 查询所有用户推送id-搬入搬出
     * @author gao
     */
    public List<SysUserInfo> queryPmregistrationIDs() {
        String sql="SELECT  GROUP_CONCAT(distinct pmregistrationID SEPARATOR ',') pmregistrationID FROM  sys_user_info where pmregistrationID is not null and pmregistrationID !=''";
        return queryForList(sql, null,SysUserInfo.class);
    }
}
