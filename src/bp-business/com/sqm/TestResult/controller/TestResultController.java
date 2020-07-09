package com.sqm.TestResult.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.sqm.TestResult.entity.TestResult;
import com.sqm.TestResult.service.TestResultService;

/**
 * 表test_result的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/TestResultController")
public class TestResultController {

    @Resource
    private TestResultService testResultService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/test_result_list";
    }

    /**
     * 下拉列表查询
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/beforeDataList.json")
    public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            SysCodeInfo entity =new SysCodeInfo();
            //entity.setCode_group_value(BusConstant.SELECTID);
            //List<SysCodeInfo> selectList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            //model.put("selectList", selectList);

            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
        }
        return model;
    }

    /**
     * 信息查询
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel TestResult testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<TestResult> dataList = testResultService.queryTestResultList(testResult, processor.getPageIndex(), processor.getPageSize(),"");

            //列表中code转化name
            UIDataConvert<TestResult> convert = new UIDataConvert<TestResult>();
            IConvert equipment = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num", "");
		    convert.addConvert(equipment);
            convert.format(dataList);

            model.put("data", dataList);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
        }
        return model;
    }

    /**
     * 修改、更新前数据取得
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel TestResult testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            TestResult dataInfo = testResultService.getTestResultById(testResult.getId());
            model.put("dataInfo", dataInfo);

            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
        }
        return model;
    }

    /**
     * 保存信息
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel TestResult testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            testResultService.insertTestResult(testResult);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }

    /**
     * 修改信息
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel TestResult testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            testResultService.updTestResult(testResult);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }

    /**
     * 作废信息
     * @param testResult
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel TestResult testResult,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            testResultService.cancelTestResult(testResult);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }
}

