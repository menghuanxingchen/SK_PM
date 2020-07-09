package com.sqm.TestResultUpdate.service;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.sqm.TestResultUpdate.dao.TestResultUpdateDAO;
import com.sqm.TestResultUpdate.entity.TestResultUpdate;
/**
 * 表test_result_update的SERVICE类
 * @author 刘镝
 */
@Service
public class TestResultUpdateService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private TestResultUpdateDAO testResultUpdateDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<TestResultUpdate> queryTestResultUpdateList(TestResultUpdate testResultUpdate, int pageIndex, int pageSize) {
        return testResultUpdateDAO.queryDataList(testResultUpdate, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public TestResultUpdate getTestResultUpdateById(String testResultUpdate_id) {
        return testResultUpdateDAO.getTestResultUpdateById(testResultUpdate_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     */
    public int insertTestResultUpdate(TestResultUpdate testResultUpdate){
        return testResultUpdateDAO.saveTestResultUpdate(testResultUpdate);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updTestResultUpdate(TestResultUpdate testResultUpdate){
        return testResultUpdateDAO.updateTestResultUpdate(testResultUpdate);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelTestResultUpdate(TestResultUpdate testResultUpdate) {
        return testResultUpdateDAO.deleteTestResultUpdate(testResultUpdate);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(TestResultUpdate testResultUpdate){
        return testResultUpdateDAO.checkTestResultUpdateEO(testResultUpdate);
    }
}

