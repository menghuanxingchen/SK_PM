package com.repast.core.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

public class XpUtil {
	/**
	 * @param is	������
	 * @param os	�����
	 * @param newWidth	���
	 * @param newHeight	�߶�
	 * @param format	ͼƬ��ʽ
	 * @throws IOException	
	 */
		public  void resizeImg(InputStream is,OutputStream os,int newWidth,int newHeight,String format) throws IOException{
			BufferedImage bufImg = ImageIO.read(is);
			double width = bufImg.getWidth();
			double height = bufImg.getHeight();
//			double percent = size/width;//��ȡͼƬ�ٷֱ�
//			int newWidth = (int) (width*percent);
//			int newHeight = (int) (height*percent);
			BufferedImage image = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_BGR);
			Graphics graphics = image.createGraphics();	//��ͼ
			graphics.drawImage(bufImg, 0, 0, newWidth, newHeight, null);
			ImageIO.write(image,format, os);
			os.flush();
			is.close();
			os.close();
		}
		
		public static  String readProperties(String key){
			String value ="";
			String propertiesFile = "/application.properties";
			 InputStream is = XpUtil.class.getResourceAsStream(propertiesFile);
			try{
				Properties p = new Properties();
				p.load(is);
			value = p.getProperty(key);
			}catch(Exception e){
				e.printStackTrace();
			}
			return value;
		}
		
}
