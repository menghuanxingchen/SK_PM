package com.tslms.persistence.dao;

import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.tslms.persistence.entity.TsLmsPutware;
/**
 * 
 * @author skcc
 *
 */
@Component("TsLmsPutwareDAO")
public class TsLmsPutwareDAO extends BaseDao {
	public final static String QueryTableSql = "SELECT T.* FROM ts_lms_putware T WHERE 1=1 ";
	
	private JDBCHelper makeSearch(TsLmsPutware entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
				jdbcHelper.putParam(JDBCHelper.AND, "T. putid", JDBCHelper.EQ, entiy.getPutid());
				jdbcHelper.putParam(JDBCHelper.AND, "T. putno", JDBCHelper.EQ, entiy.getPutno());
				jdbcHelper.putParam(JDBCHelper.AND, "T. warename", JDBCHelper.EQ, entiy.getWarename());
				jdbcHelper.putParam(JDBCHelper.AND, "T. customer", JDBCHelper.EQ, entiy.getCustomer());
				jdbcHelper.putParam(JDBCHelper.AND, "T. part", JDBCHelper.EQ, entiy.getPart());
				jdbcHelper.putParam(JDBCHelper.AND, "T. materialtype", JDBCHelper.EQ, entiy.getMaterialtype());
				jdbcHelper.putParam(JDBCHelper.AND, "T. materialname", JDBCHelper.EQ, entiy.getMaterialname());
				jdbcHelper.putParam(JDBCHelper.AND, "T. matercount", JDBCHelper.EQ, entiy.getMatercount());
				jdbcHelper.putParam(JDBCHelper.AND, "T. puttime", JDBCHelper.EQ, entiy.getPuttime());
				jdbcHelper.putParam(JDBCHelper.AND, "T. putcount", JDBCHelper.EQ, entiy.getPutcount());
		}
		return jdbcHelper;
	}
	
	/**
	 * 分页查询entity List
	 * @author 齐建军
	 */
	public Pagination<TsLmsPutware> queryTsLmsPutwareList(TsLmsPutware entity, int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
		return getPage(helper.getSql(), helper.getParam(), TsLmsPutware.class, pageIndex, pageSize);
	}
	
	/**
	 * 保存实体对象entity
	 * @author 齐建军
	 */
	public int saveTsLmsPutware(TsLmsPutware entity) {
   			 if(entity.getPutid()==null)
				entity.setPutid(this.getUUId());
		
		int flag =  this.saveNew(entity);
		return flag;
	}

	/**
	 * 修改实体对象entity
	 * @author 齐建军
	 */
	public int updateTsLmsPutware(TsLmsPutware entity) {
		int flag =  this.saveUpdate(entity);
		return flag;
	}

	/**
	 * 删除实体对象entity
	 * @author 齐建军
	 */
	public int deleteTsLmsPutware(TsLmsPutware entity) {
		return this.saveRemove(entity);
	}

	/**
	 * 根据实体对象Id返回entity
	 * @author 齐建军
	 */
	public TsLmsPutware getTsLmsPutwareById(String id) {
		return this.getEntityById(id, TsLmsPutware.class);
	}
	
}
