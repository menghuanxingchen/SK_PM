package com.repast.core.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileuploadController {

	/**
	 * ajax上传文件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadImgbyAjax.json")
	public String uploadImgbyAjax(Model model, MultipartHttpServletRequest request,HttpServletResponse response) throws IOException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		String generationfileName = simpleFormat.format(new Date())+ new Random().nextInt(1000);
		// 保存路径
		String savePath = request.getSession().getServletContext().getRealPath("/")+ "\\" + "upload\\";
		String updateP = request.getParameter("updateP");
		String fileName = null;
		if (updateP != null && !updateP.equals("")) {
			try {
				MultipartFile mf = request.getFile(updateP);
				fileName = mf.getOriginalFilename();
				if (null != mf && !"".equals(mf)) {
					SaveFileFromInputStream(mf.getInputStream(), savePath,generationfileName + fileName);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("fileName", generationfileName + fileName);
					model.addAttribute("fileName", generationfileName + fileName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "jsonView";
	}
	
	/**
	 * 多个文件上传--form表单提交上传
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadImgbyForm.json")
	public String uploadImgbyForm(Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		System.out.println(fileMap);
		String uploadDir = request.getSession().getServletContext().getRealPath("/")+ "\\" + "upload\\";
		System.out.println(uploadDir);
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = "";
		int i = 0;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator(); it.hasNext(); i++) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mf = entry.getValue();
			fileName = mf.getOriginalFilename();
			if (fileName != null && !fileName.equals("")) {// 如果客户没有选择文件直接点击上传防止出现异常
				System.out.println("文件名称" + fileName);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String date_time = sdf.format(new Date()) + fileName;// 当前日期+文件名称
				File uploadFile = new File(uploadDir + date_time);
				System.out.println(uploadFile.getAbsolutePath());
				FileCopyUtils.copy(mf.getBytes(), uploadFile);// 上传
				System.out.println(date_time);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", date_time);
				list.add(map);
				model.addAttribute("fileNames", list);
			} else {
				continue;
			}

		}
		return "jsonView";
	}
	
	//保存文件 1,文件 2，保存路径 3，文件名称
	public void SaveFileFromInputStream(InputStream is, String path, String filename) throws IOException {
			FileOutputStream fos = new FileOutputStream(path + "/" + filename);
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = is.read(buffer)) != -1) {
				bytesum += byteread;
				fos.write(buffer, 0, byteread);
				fos.flush();
			}
			fos.close();
			is.close();
	}
}
