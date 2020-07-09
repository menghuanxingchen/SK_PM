package com.repast.core.codelist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name:易学籍
 * Application describing:实体类
 * Copyright:沈阳东慧网络科技有限公司
 * Company:www.exueji.com
 * @author xxx
 */
@Component("SysCodelistDAO")
public class SysCodelistDAO extends BaseDao
{
	/**
     * 
     * 保存实体对象entity
     * @author 
     */
    public int saveSysCodelist(SysCodelist entity)
    {
        return this.saveNew(entity);
    }
    /**
     * 
     * 修改实体对象entity
     * @author 
     */
    public int updateSysCodelist(SysCodelist entity)
    {
        return this.saveUpdate(entity);
    }
    /**
     * 
     * 删除实体对象entity
     * @author 
     */    
    public int deleteSysCodelist(SysCodelist entity)
    {
        return this.saveRemove(entity);
    }
    /**
     * 
     * 根据实体对象Id返回entity
     * @author 
     */    
    public SysCodelist getSysCodelistById(String id)
    {
        return this.getEntityById(id, SysCodelist.class);
    }
    /**
     * @description 根据不同的种类值(kind)获取不同类型的代码表
     * @author lingyong
     * @updateAuthor lingyong
     * @param kind
     * @return
     * @created 2014年9月18日 上午8:47:39
     */
    public List<SysCodelist> getCodeList(String kind){
		String sql = "select * from sys_codelist where 1=1 ";
		if(kind!=null && !kind.equals("")){
			sql = sql + "and kindcode= ? ";
		}
		sql = sql + "order by ordernum asc " ;
		List<SysCodelist> sysCodelist = this.queryForList(sql, new Object[]{kind}, SysCodelist.class);
		return sysCodelist;
	}
    /**
     * @description 根据代码表中的种类代码和代码名称获取代码值
     * @author qijianjun
     * @updateAuthor qijianjun
     * @param kindCode
     * @param codeValue
     * @return
     * @created 2014年11月19日 下午4:55:48
     */
    public String getCodeValueByKindCodeAndCodeName(String kindCode,String codeName)
    {
    	String codeValue = "";
    	String sql = "select * from sys_codelist where kindcode = ? and codeName = ? ";
    	SysCodelist entity = this.queryObject(sql, new Object[]{kindCode,codeName}, SysCodelist.class);
    	if(entity!=null)
    	{
    		codeValue = entity.getCodevalue();
    	}
		return codeValue;
    }
    /**
     * @description 
     * @author qijianjun
     * @updateAuthor qijianjun
     * @param kindCode
     * @param codeValue
     * @return
     * @created 2014年11月19日 下午4:55:48
     */
    public String getCodeNameByKindCodeAndCodeValue(String kindCode,String codeValue)
    {
    	String codeName = "";
    	String sql = "select * from sys_codelist where kindcode = ? and codeValue = ? ";
    	SysCodelist entity = this.queryObject(sql, new Object[]{kindCode,codeValue}, SysCodelist.class);
    	if(entity!=null)
    	{
    		codeName = entity.getCodename();
    	}
		return codeName;
    }

    /**
	 * @description 根据kindCode、parentCode获取SysCodelist
	 * @author wuchao@exueji.com
	 * @updateAuthor wuchao@exueji.com
	 * @param kindCode
	 * @param parentCode
	 * @return
	 * @created 2014年9月19日 下午2:59:03
	 */
    public List<SysCodelist> getCodeListByKindParentCode(String kindCode,String parentCode){
    	String sql = "";
    	List<String> stringArray = new ArrayList<String>();
    	if(parentCode != null && !"".equals(parentCode)){
    		sql = "select * from sys_codelist where kindcode = ? and parentcodevalue = ? ";
    		stringArray.add(kindCode);
    		stringArray.add(parentCode);
    	}else{
    		sql = "select * from sys_codelist where kindcode = ? and parentcodevalue is null ";
    		stringArray.add(kindCode);
    	}
    	sql = sql + "order by orderNum asc " ;
		List<SysCodelist> sysCodelist = this.queryForList(sql, stringArray.toArray(), SysCodelist.class);
		return sysCodelist;
	}
    
    /**
     * @description 
     * @author wuchao
     * @updateAuthor wuchao
     * @param kindCode
     * @param codeValue
     * @return
     * @created 2014年11月19日 下午4:55:48
     */
    public List<SysCodelist> getCodeListBySql(String sql,List<String> stringList)
    {
    	return this.queryForList(sql, stringList.toArray(), SysCodelist.class);
    }
    
    
    private final static String QueryTableSql = "SELECT T.* FROM SYS_CODELIST T WHERE 1=1 ";
    
    private final static String queryDistCodeKind = "Select T.kindCode,T.kindName,count(*) kindCnt FROM SYS_CODELIST T where 1=1 ";
	
    private JDBCHelper makeSearch(SysCodelist sysCodelist, String sql)
    {
        JDBCHelper jdbcHelper = new JDBCHelper(sql);
        if(sysCodelist!=null)
        {
        		jdbcHelper.putParam(JDBCHelper.AND, "T.kindCode", JDBCHelper.EQ, sysCodelist.getKindcode());
        		jdbcHelper.putParam(JDBCHelper.AND, "T.kindName", JDBCHelper.LIKE, sysCodelist.getKindname());
        		
        	//	jdbcHelper.putOrder(sysCodelist);
        	//	jdbcHelper.putOrder("T.kindCode asc,T.codeValue asc");
        }
        return jdbcHelper;
    }
    
	public Pagination<SysCodelist> querySysCodeList(SysCodelist sysCodelist,int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(sysCodelist,queryDistCodeKind);
		return getPage(helper.getSql()+" group by T.kindCode,T.kindName order by T.kindCode asc,T.codeValue asc", helper.getParam(), SysCodelist.class, pageIndex, pageSize);
	}
	
	public Pagination<SysCodelist> querySysCodeListByCodeKind(SysCodelist sysCodelist,int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(sysCodelist,QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), SysCodelist.class, pageIndex, pageSize);
	}
	
	
    private JDBCHelper makeCheckExists(SysCodelist sysCodelist, String sql)
    {
        JDBCHelper jdbcHelper = new JDBCHelper(sql);
        if(sysCodelist!=null)
        {
        		jdbcHelper.putParam(JDBCHelper.AND, "T.kindCode", JDBCHelper.EQ, sysCodelist.getKindcode());
        		jdbcHelper.putParam(JDBCHelper.AND, "T.kindName", JDBCHelper.EQ, sysCodelist.getKindname());
        		
        		jdbcHelper.putParam(JDBCHelper.AND, "T.CODEValue", JDBCHelper.EQ, sysCodelist.getCodevalue());
        		jdbcHelper.putParam(JDBCHelper.AND, "T.CODENAME", JDBCHelper.EQ, sysCodelist.getCodename());
        		
        		jdbcHelper.putOrder("T.kindCode asc,T.codeValue asc");
        }
        return jdbcHelper;
    }
	public List<SysCodelist> checkSysCodeListByCodeExists(SysCodelist sysCodelist) {
		JDBCHelper helper = makeCheckExists(sysCodelist,QueryTableSql);
		return this.queryForList(helper.getSql(), helper.getParam(), SysCodelist.class);
	} 
	
	public SysCodelist getSysCodeListByKindValue(String kindcode,String codevalue){
		SysCodelist sysCodelist = new SysCodelist();
		sysCodelist.setKindcode(kindcode);
		sysCodelist.setCodevalue(codevalue);;
		JDBCHelper helper = makeCheckExists(sysCodelist,QueryTableSql);
		return this.queryObject(helper.getSql(), helper.getParam(), SysCodelist.class);
	}
	 
	 
	
}
