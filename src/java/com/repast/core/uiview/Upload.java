package com.repast.core.uiview;

public class Upload {

	/**
	 * 文件名
	 * */
	private String filename;
	/**
	 * 相对路径
	 * */
	private String filepath;
	/**
	 * 上传日期
	 * */
	private String uploaddate;
	/**
	 * 服务器端文件名字
	 * */
	private String servername;
	/**
	 * 真实路径
	 * */
	private String fileRealpath;
	
	/**
	 * 文件大小（KB）
	 */
	private String filesize;
	

	public String getFileRealpath() {
		return fileRealpath;
	}

	public void setFileRealpath(String fileRealpath) {
		this.fileRealpath = fileRealpath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
	
}
