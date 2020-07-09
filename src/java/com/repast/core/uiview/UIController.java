package com.repast.core.uiview;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;

/**
 * 处理前台组件的相关类型及方法
 * @author luandong
 *
 */
@Controller
@RequestMapping("/UIController")
public class UIController {

	@Resource
	private UIService uiService;

	@RequestMapping("selectTable.json")
	public @ResponseBody JSONMap<String,Object> selectTable(@FormModel SelectConfig selectConfig) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<Option> options = uiService.queryParameterListByTable(selectConfig);
			Select<Option> select = new Select<Option>();
			select.setOptions(options);
			model.put("data", select);
			model.put(SysConstant.OP_FLAG, true);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 多个文件上传
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/upload.json")
	public void  Upload( HttpServletRequest request,HttpServletResponse response, HttpSession session)
			throws IOException {
		System.out.println("开始处理文件上传["+System.currentTimeMillis()+"]");
		
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		if(request.getCharacterEncoding()==null){
			request.setCharacterEncoding("UTF-8");
		}
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String uploadPar = request.getParameter("modelForder");
		String path = "upload"+"/"+uploadPar;
		
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/")+"/"+path+"/";
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = "";
		ArrayList<Upload> list = new ArrayList<Upload>();
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mf = entry.getValue();
			fileName = mf.getOriginalFilename();
			if (fileName != null && !fileName.equals("")){// 如果客户没有选择文件直接点击上传防止出现异常
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//String date_time = System.currentTimeMillis() + fileName;// 当前日期+文件名称
				String date_time = System.currentTimeMillis()+"." + StringUtils.getFilenameExtension(fileName);;// 当前日期+文件名称
				File uploadFile = new File(uploadDir + date_time);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);// 上传
				long size = mf.getSize();
				long kbSize = size/1000;
				Upload upload = new Upload();
				upload.setFilename(fileName);//真实文件名
				upload.setServername(date_time);//文件在服务器端名称
				upload.setFilepath(path+"/"+date_time);//文件路径
				upload.setUploaddate(sdf.format(new Date()));//文件上传时间－－毫秒
				upload.setFileRealpath(uploadDir + date_time);//文件真实路径
				upload.setFilesize(kbSize+"");
				list.add(upload);
				model.put(SysConstant.OP_FILEUPLOAD, list);
				
				System.out.println("文件信息[filename="+fileName+"]");
				
				//把要上传的对象ID传递给前台
				String uploadId = request.getParameter("uploadId");
				model.put("uploadId", uploadId);
				
			}else{
				continue;
			}

		}
		
		response.setContentType("text/html;chartset=UTF-8");
		response.getWriter().write(JSON.toJSONString(model));
		
		System.out.println("完成文件上传["+System.currentTimeMillis()+"]");
	}  
	
	/**
	 * 
	 * @author lingy
	 * @date 2015年9月17日 上午9:33:28
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/download.do")
	public ResponseEntity<byte[]> download(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {    
        String ctxPath = request.getSession().getServletContext().getRealPath(   
                "/")+"/upload/foodms/";
        String downLoadPath = ctxPath + fileName;
        try {
        	File file=new File(downLoadPath);  
        	HttpHeaders headers = new HttpHeaders();    
        	String newFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        	headers.setContentDispositionFormData("attachment", newFileName);  
        	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
        			headers, HttpStatus.CREATED);    
		} catch (Exception e) {
			System.out.println("--------------------不存在文件名为："+fileName+"的文件--------------------");
		}
        return null;
    }    
}
