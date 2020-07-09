package com.sqm.sqmpushinfo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.UIDataConvert;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
import com.sqm.sqmpushinfo.service.SqmPushInfoService;

/**
 * 表sqm_push_info的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/SqmPushInfoController")
public class SqmPushInfoController {

    @Resource
    private SqmPushInfoService sqmPushInfoService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/sqm_push_info_list";
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
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<SqmPushInfo> dataList = sqmPushInfoService.querySqmPushInfoList(sqmPushInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<SqmPushInfo> convert = new UIDataConvert<SqmPushInfo>();
            //IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
            //convert.addConvert(position_nm);
            //convert.format(dataList);

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
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            SqmPushInfo dataInfo = sqmPushInfoService.getSqmPushInfoById(sqmPushInfo.getPush_id());
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
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor,@FormModel FillingPlanInfo planInfo) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmPushInfoService.insertSqmPushInfo(sqmPushInfo,planInfo);
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
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmPushInfoService.updSqmPushInfo(sqmPushInfo);
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
     * @param sqmPushInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel SqmPushInfo sqmPushInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmPushInfoService.cancelSqmPushInfo(sqmPushInfo);
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

