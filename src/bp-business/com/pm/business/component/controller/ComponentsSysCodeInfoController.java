package com.pm.business.component.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.mysql.jdbc.StringUtils;
import com.pm.business.component.service.ComponentsSysCodeInfoService;
import com.pm.persistence.component.entity.ComponentsSysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;

/**
 * 表sys_code_info的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/ComponentsSysCodeInfoController")
public class ComponentsSysCodeInfoController {

    @Resource
    private ComponentsSysCodeInfoService componentssysCodeInfoService;
   

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
    	
        return "web/components/sys_code_info_list";
       
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
        	ComponentsSysCodeInfo entity =new ComponentsSysCodeInfo();
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
     * @param sysCodeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel("componentssysCodeInfo") ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        

        	
        	
            //列表查询（分页）
        	//System.out.println(componentssysCodeInfo.getCode_group_value());
        	//System.err.println(componentssysCodeInfo.getCode_nm());
            Pagination<ComponentsSysCodeInfo> dataList = componentssysCodeInfoService.queryComponentsSysCodeInfoList(componentssysCodeInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<ComponentsSysCodeInfo> convert = new UIDataConvert<ComponentsSysCodeInfo>();
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
     * 设备3级部件分页查询
     * @param componentssysCodeInfo
     * @param processor
     * @return
     */
    @RequestMapping("/selDataList.json")
    public @ResponseBody JSONMap<String,Object> selComponentsDataList(@FormModel ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<ComponentsSysCodeInfo> dataList = componentssysCodeInfoService.selComponentsSysCodeInfoList(componentssysCodeInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<ComponentsSysCodeInfo> convert = new UIDataConvert<ComponentsSysCodeInfo>();
          //  IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
           // convert.addConvert(position_nm);
          //  convert.format(dataList);
            //将code_group_value转换为code_nm
            IConvert position_nm = new SqlMapConvertImpl("code_group_value", "code_group_name", "sys_code_group_info", 
            		"code_group_name","code_group_value", " and code_group_value='"+"components_03"+"' ");
            convert.addConvert(position_nm);
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
     * @param sysCodeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	ComponentsSysCodeInfo dataInfo = componentssysCodeInfoService.getComponentsSysCodeInfoById(componentssysCodeInfo.getCode_id());
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
     * @param sysCodeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel("componentssysCodeInfo") ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	componentssysCodeInfo.setCreate_id(processor.getCurrentUserId());
        	System.err.println(componentssysCodeInfo.getCreate_id());
        	componentssysCodeInfoService.insertComponentsSysCodeInfo(componentssysCodeInfo);        	
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
     * @param sysCodeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel("componentssysCodeInfo") ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	//System.out.println(componentssysCodeInfo.getCode_id());
        	componentssysCodeInfo.setUpdate_id(processor.getCurrentUserId());
        	componentssysCodeInfoService.updComponentsSysCodeInfo(componentssysCodeInfo);
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
     * @param sysCodeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	componentssysCodeInfoService.cancelComponentsSysCodeInfo(componentssysCodeInfo);
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
     * 根据实体对象Id返回entity
     * @param componentssysCodeInfo
     * @param processor
     * @return
     */
    @RequestMapping("/queryComponentsEntity.json")
    public @ResponseBody JSONMap<String,Object> selComponentsInfoData(@FormModel("componentssysCodeInfo") ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
     
            ComponentsSysCodeInfo data =componentssysCodeInfoService.getComponentsSysCodeInfoById(componentssysCodeInfo.getCode_id());
           // IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
           // convert.addConvert(position_nm);
           // convert.format(dataList);

            model.put("data", data);
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
    * 根据value验证是否重复
    * @param componentssysCodeInfo
    * @param processor
    * @return
    */
    @RequestMapping("/queryComponentsEntityValue.json")
    public @ResponseBody JSONMap<String,Object> selComponentsInfoValue(@FormModel("componentssysCodeInfo") ComponentsSysCodeInfo componentssysCodeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
     
            List<ComponentsSysCodeInfo> data =componentssysCodeInfoService.selComponentsInfoValue(componentssysCodeInfo);
           // IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
           // convert.addConvert(position_nm);
           // convert.format(dataList);

            model.put("data", data);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
        }
        return model;
    }
   
    
}

