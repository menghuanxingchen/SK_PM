package com.repast.core.codelist;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.repast.core.cache.CacheKeyTool;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;


@Service("codeListService")
public class CodeListService {
	
	@Resource
	private SysCodelistDAO sysCodelistDAO;
    
    /**
     * @description 根据kindcode获取代码表信息
     * @author qijianjun
     * @updateAuthor qijianjun
     * @param SchoolNatureCode
     * @return
     * @created 2014年10月30日 下午1:54:35
     */
	public List<SysCodelist> getCodeList(String kind){
		return sysCodelistDAO.getCodeList(kind);
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
		return sysCodelistDAO.getCodeListByKindParentCode(kindCode, parentCode);
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
    	return sysCodelistDAO.getCodeNameByKindCodeAndCodeValue(kindCode,codeValue);
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
    public String getCodeValueByKindCodeAndCodeName(String kindCode,String codeName)
    {
    	return sysCodelistDAO.getCodeValueByKindCodeAndCodeName(kindCode, codeName);
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
    	return sysCodelistDAO.getCodeListBySql(sql, stringList);
    }

	public Pagination<SysCodelist> querySysCodeList(SysCodelist sysCodelist,int pageIndex, int pageSize) {
		return sysCodelistDAO.querySysCodeList( sysCodelist, pageIndex,  pageSize) ;
	}
	
	public Pagination<SysCodelist> querySysCodeListByCodeKind(SysCodelist sysCodelist,int pageIndex, int pageSize) {
		return sysCodelistDAO.querySysCodeListByCodeKind( sysCodelist, pageIndex,  pageSize) ;
	}
	
	public int checkSysCodeListByCodeExists(SysCodelist sysCodelist){
		List<SysCodelist> list = sysCodelistDAO.checkSysCodeListByCodeExists(sysCodelist);
		return list.size();
	}

	/**
	 * 保存codelist数据
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	public int saveSysCodeListByCode(SysCodelist sysCodelist,Processor processor) {
		if(sysCodelist.getCodelistid()==null)
			sysCodelist.setCodelistid(sysCodelistDAO.getUUId());
		
		int flag = sysCodelistDAO.saveSysCodelist(sysCodelist);
		
	//	CacheKeyTool.getInstance().flushCache(new String[]{"SYS_CODELIST"});
		
		/*Log log = new Log(LogConstant.SysCodeListServiceModelName,LogConstant.OP_INSERT,"设置"+sysCodelist.getKindname()+"代码数据！",processor);
		log.putOldAndNewObj(null, sysCodelist);
		log.saveLog();*/
		
		return flag;
	}

	/**
	 * 读取指定id的codelist对象数据
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	public SysCodelist loadSysCodeListById(SysCodelist sysCodelist,Processor processor) {
		return sysCodelistDAO.getSysCodelistById(sysCodelist.getCodelistid());
	}

	
	public int deleteSysCodeListById(SysCodelist sysCodelist,Processor processor) {
		sysCodelist = sysCodelistDAO.getSysCodelistById(sysCodelist.getCodelistid());
		int falg = sysCodelistDAO.deleteSysCodelist(sysCodelist);
		
		CacheKeyTool.getInstance().flushCache(new String[]{"SYS_CODELIST"});
		
		/*Log log = new Log(LogConstant.SysCodeListServiceModelName,LogConstant.OP_DELETE,"删除"+sysCodelist.getKindname()+"中 "+sysCodelist.getCodename()+" 代码数据！",processor);
		log.putOldAndNewObj(null, sysCodelist);
		log.saveLog();*/
		
		return falg;
	}

	public SysCodelist saveUpdateSysCodeListById(SysCodelist sysCodelist,Processor processor) {
		sysCodelistDAO.updateSysCodelist(sysCodelist);
		
		CacheKeyTool.getInstance().flushCache(new String[]{"SYS_CODELIST"});
		
		/*Log log = new Log(LogConstant.SysCodeListServiceModelName,LogConstant.OP_UPDATE,"修改"+sysCodelist.getKindname()+"中 "+sysCodelist.getCodename()+" 代码数据！",processor);
		log.putOldAndNewObj(null, sysCodelist);
		log.saveLog();*/
		
		return sysCodelist;
	}
    
}
