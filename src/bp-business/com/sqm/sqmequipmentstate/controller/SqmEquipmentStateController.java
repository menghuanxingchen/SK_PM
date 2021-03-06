package com.sqm.sqmequipmentstate.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.UIDataConvert;
import com.sqm.sqmequipmentstate.entity.SqmEquipmentState;
import com.sqm.sqmequipmentstate.service.SqmEquipmentStateService;

/**
 * 表sqm_equipment_state的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/SqmEquipmentStateController")
public class SqmEquipmentStateController {

    @Resource
    private SqmEquipmentStateService sqmEquipmentStateService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/sqm_equipment_state_list";
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
     * @param sqmEquipmentState
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SqmEquipmentState sqmEquipmentState,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<SqmEquipmentState> dataList = sqmEquipmentStateService.querySqmEquipmentStateList(sqmEquipmentState, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<SqmEquipmentState> convert = new UIDataConvert<SqmEquipmentState>();
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
     * @param sqmEquipmentState
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel SqmEquipmentState sqmEquipmentState,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            SqmEquipmentState dataInfo = sqmEquipmentStateService.getSqmEquipmentStateById(sqmEquipmentState.getId());
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
     * @param sqmEquipmentState
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel SqmEquipmentState sqmEquipmentState,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmEquipmentStateService.insertSqmEquipmentState(sqmEquipmentState);
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
     * @param sqmEquipmentState
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel SqmEquipmentState sqmEquipmentState,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmEquipmentStateService.updSqmEquipmentState(sqmEquipmentState);
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
     * @param sqmEquipmentState
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel SqmEquipmentState sqmEquipmentState,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            sqmEquipmentStateService.cancelSqmEquipmentState(sqmEquipmentState);
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

