package com.repast.core.codelist;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.repast.core.entity.AdvaQueryModel;

/**
 * @company www.exueji.com
 * @description 
 * @author lingyong
 * @updateAuthor lingyong
 * @version 1.0
 * @created 2014年9月16日 上午11:18:45
 */
@Service
public class CodelistManager implements ApplicationContextAware{
	
	private CodeListService codeListService;
	
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
		
	}
	
	public void setCodeListService(CodeListService codeListService) {
		this.codeListService = codeListService;
	}

	private static CodelistManager codelistManager = new CodelistManager();  
	
    private CodelistManager(){}
    
    public static CodelistManager getInstance(){
        return codelistManager;  
    } 
    
    public void init(){
    	
    }
    /**
     * @description 
     * @author lingyong
     * @updateAuthor lingyong
     * @param kindLists
     * @return
     * @created 2014年9月16日 上午11:23:37
     */
    private Map<String, Map<String, String>> getCodeListByKinds(List<String> kindLists)
    {
    	//setCodeListService(new CodeListService());
    	this.codeListService = (CodeListService) applicationContext.getBean("codeListService");
    	Map<String, Map<String, String>> kindCodeMap = new HashMap<String, Map<String,String>>();
    	for (String kind : kindLists) {
    		List<SysCodelist> codeLists = codeListService.getCodeList(kind);
    		Map<String, String> codeMap = new LinkedHashMap<String, String>();
    		for (SysCodelist codeList : codeLists) {
    			String codeName = codeList.getCodename();
    			String codeValue = codeList.getCodevalue();
    			codeMap.put(codeValue, codeName);
    		}
    		kindCodeMap.put(kind, codeMap);
		}
    	return kindCodeMap;
    }
    
    public Map<String, Map<String, String>> getCodeList(List<String> kindList){
    	return getCodeListByKinds(kindList);
    }
    
    
    public Map<String, Map<String, String>> getCodeList(String ...kinds){
    	List<String> kindLists = Arrays.asList(kinds);
    	return getCodeListByKinds(kindLists);
    }
    /**
     * @description
     * @author lingyong
     * @updateAuthor lingyong
     * @param property 该维度代表的属性
     * @param fClassify 该维度的属性名称
     * @param cClassifyList 生成该维度每个粒度的数据列表
     * @param kindName 显示的值在粒度数据中的属性
     * @param kindValue 实际的值在粒度数据中的属性
     * @param optionality 维度可以多选     0为不能多选1为能多选
     * @param keepstill 维度持位置不动     0为不可以动（消失）1为可以动
     * @param defaultval 维度默认值  一般与keepstill一起使用
     * @return
     * @created 2014年9月16日 上午11:23:49
     */
    public AdvaQueryModel setAdvaQueryModel(String property,String fClassify,List<?> cClassifyList,
    		String kindName,String kindValue,String optionality,String keepstill,String defaultval){
    	AdvaQueryModel advaQueryModel = new AdvaQueryModel();
    	advaQueryModel.setProperty(property);
    	advaQueryModel.setfClassify(fClassify);
    	if(optionality!=null){
    		if(optionality.equals("0")){
    			advaQueryModel.setOptionality("0");	
    		}else{
    			advaQueryModel.setOptionality("1");	
    		}
    	}else{
    		advaQueryModel.setOptionality("1");	
    	}
    	if(keepstill!=null){
    		if(keepstill.equals("0")){
    			advaQueryModel.setKeepstill("0");
        		advaQueryModel.setDefaultval(defaultval);
    		}else{
    			advaQueryModel.setKeepstill("1");
        		advaQueryModel.setDefaultval(null);
    		}
    	}else{
    		advaQueryModel.setKeepstill("0");
    		advaQueryModel.setDefaultval(null);
    	}
    	Map<String, String> cClassify = new HashMap<String, String>();
    	for (Object object : cClassifyList) {
    		try {
    			Class clazz = object.getClass();
    			//根据反射获得实体中kindName的值
				PropertyDescriptor kindNamePD = new PropertyDescriptor(kindName, clazz);
				Method getKindNameMethod = kindNamePD.getReadMethod();
				Object kindNameStr = null;
				if(kindNamePD != null){
					kindNameStr = getKindNameMethod.invoke(object);
				}
				//根据反射获得实体中kindValue的值
				PropertyDescriptor kindValuePD = new PropertyDescriptor(kindValue, clazz);
				Method getKindValueMethod = kindValuePD.getReadMethod();
				Object kindValueStr = null;
				if(kindValuePD != null){
					kindValueStr = getKindValueMethod.invoke(object);
				}
				if(kindNameStr != null && kindValueStr != null){
					cClassify.put(kindNameStr.toString(), kindValueStr.toString());
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
    	advaQueryModel.setcClassify(cClassify);
    	return advaQueryModel;
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
		this.codeListService = (CodeListService) applicationContext.getBean("codeListService");
		return codeListService.getCodeListByKindParentCode(kindCode, parentCode);
	}

	/**
	 * @description 根据kindCode获取list 
	 * @author chao
	 * @updateAuthor chao
	 * @param kindCode
	 * @return
	 * @created 2014年11月12日 下午12:48:11
	 */
	public List<SysCodelist> getCodeListByKind(String kindCode){
		this.codeListService = (CodeListService) applicationContext.getBean("codeListService");
		return codeListService.getCodeList(kindCode);
	}
}
