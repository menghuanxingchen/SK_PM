package com.pm.business.worktimeinfo.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.worktimeinfo.dao.WorktimeInfoDAO;
import com.pm.persistence.worktimeinfo.entity.WorktimeInfo;
import com.repast.core.system.Pagination;
/**
 * 表worktime_info的SERVICE类
 * @author 刘镝
 */
@Service
public class WorktimeInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private WorktimeInfoDAO worktimeInfoDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<WorktimeInfo> queryWorktimeInfoList(WorktimeInfo worktimeInfo, int pageIndex, int pageSize) {
        return worktimeInfoDAO.queryWorktimeInfoList(worktimeInfo, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public WorktimeInfo getWorktimeInfoById(String worktimeInfo_id) {
        return worktimeInfoDAO.getWorktimeInfoById(worktimeInfo_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     */
    public int insertWorktimeInfo(WorktimeInfo worktimeInfo){
        return worktimeInfoDAO.saveWorktimeInfo(worktimeInfo);
    }
    /**
     * 新增List信息
     * @author 刘镝
     */
    public int saveWorktimeInfoList(List<WorktimeInfo> worktimeInfoList) {
    	return worktimeInfoDAO.saveWorktimeInfoList(worktimeInfoList);
    }
    
    public int delWorktimeInfoList(List<WorktimeInfo> worktimeInfoList) {
    	return worktimeInfoDAO.delWorktimeInfoList(worktimeInfoList);
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updWorktimeInfo(WorktimeInfo worktimeInfo){
        return worktimeInfoDAO.updateWorktimeInfo(worktimeInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelWorktimeInfo(WorktimeInfo worktimeInfo) {
        return worktimeInfoDAO.deleteWorktimeInfo(worktimeInfo);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(WorktimeInfo worktimeInfo){
        return worktimeInfoDAO.checkWorktimeInfoEO(worktimeInfo);
    }
}

