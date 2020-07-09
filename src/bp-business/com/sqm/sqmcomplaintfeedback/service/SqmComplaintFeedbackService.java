package com.sqm.sqmcomplaintfeedback.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.sqm.sqmcomplaintfeedback.dao.SqmComplaintFeedbackDAO;
import com.sqm.sqmcomplaintfeedback.entity.SqmComplaintFeedback;
/**
 * 表sqm_complaint_feedback的SERVICE类
 * @author 刘镝
 */
@Service
public class SqmComplaintFeedbackService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private SqmComplaintFeedbackDAO sqmComplaintFeedbackDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<SqmComplaintFeedback> querySqmComplaintFeedbackList(SqmComplaintFeedback sqmComplaintFeedback, int pageIndex, int pageSize) {
        return sqmComplaintFeedbackDAO.queryDataList(sqmComplaintFeedback, pageIndex, pageSize);
    }
    

    /**
     * 不分页查询信息
     * @author 刘镝
     */
    public List<SqmComplaintFeedback> querySqmComplaintFeedbackList(SqmComplaintFeedback sqmComplaintFeedback) {
        return sqmComplaintFeedbackDAO.queryDataList(sqmComplaintFeedback);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public SqmComplaintFeedback getSqmComplaintFeedbackById(String sqmComplaintFeedback_id) {
        return sqmComplaintFeedbackDAO.getSqmComplaintFeedbackById(sqmComplaintFeedback_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     */
    public int insertSqmComplaintFeedback(SqmComplaintFeedback sqmComplaintFeedback){
        return sqmComplaintFeedbackDAO.saveSqmComplaintFeedback(sqmComplaintFeedback);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updSqmComplaintFeedback(SqmComplaintFeedback sqmComplaintFeedback){
        return sqmComplaintFeedbackDAO.updateSqmComplaintFeedback(sqmComplaintFeedback);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelSqmComplaintFeedback(SqmComplaintFeedback sqmComplaintFeedback) {
        return sqmComplaintFeedbackDAO.deleteSqmComplaintFeedback(sqmComplaintFeedback);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(SqmComplaintFeedback sqmComplaintFeedback){
        return sqmComplaintFeedbackDAO.checkSqmComplaintFeedbackEO(sqmComplaintFeedback);
    }
}

