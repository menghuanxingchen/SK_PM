package com.sqm.testoveryn.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.sqm.testoveryn.dao.TestOverYnDAO;
import com.sqm.testoveryn.entity.TestOverYn;
/**
 * 表test_over_yn的SERVICE类
 * @author 刘镝
 */
@Service
public class TestOverYnService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private TestOverYnDAO testOverYnDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<TestOverYn> queryTestOverYnList(TestOverYn testOverYn, int pageIndex, int pageSize) {
        return testOverYnDAO.queryDataList(testOverYn, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public TestOverYn getTestOverYnById(String testOverYn_id) {
        return testOverYnDAO.getTestOverYnById(testOverYn_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     * @throws Exception 
     */
    public int insertTestOverYn(TestOverYn testOverYn) throws Exception{
        return testOverYnDAO.saveTestOverYn(testOverYn);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updTestOverYn(TestOverYn testOverYn){
        return testOverYnDAO.updateTestOverYn(testOverYn);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelTestOverYn(TestOverYn testOverYn) {
        return testOverYnDAO.deleteTestOverYn(testOverYn);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(TestOverYn testOverYn){
        return testOverYnDAO.checkTestOverYnEO(testOverYn);
    }
    
	/**
	 * 查询第几检测
	 * @author 刘镝
	 */
	public TestOverYn queryTestOverYn(String planId,String createId){
		 return testOverYnDAO.queryTestOverYn(planId, createId);
	}
	/**
	 * 查询最近谁在检测
	 * @author 刘镝
	 */
	public TestOverYn queryTestOverYnLately(String planId){
		 return testOverYnDAO.queryTestOverYnLately(planId);
	}
	
	/**
	 * 查询已检测完的数量
	 * @author 刘镝
	 */
	public int queryTestOverYesNum(String planId){
		 return testOverYnDAO.queryTestOverYesNum(planId);
	}
	
}

