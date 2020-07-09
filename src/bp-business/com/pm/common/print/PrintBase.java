package com.pm.common.print;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

/**
 * 报表生成及打印基类
 * @author 刘镝
 */
public class PrintBase {
	private HttpServletRequest request = null;
	// 生成路径
	public static final String OUTPUTFILEPATH = File.separator + "report" + File.separator + "result" + File.separator;
	// 模板路径
	public static final String TEMPLANTPATH = File.separator + "report" + File.separator + "temp" + File.separator;
	// 生成文件类型
	public static final String OUTPUTFILETYPE = ".xls";
	// 报名文件名
	public static final String REPORTNAME =  "计划";
	// 单元格显示最大字符串长度
	public static final int STRINGLENGTH = 20;
	// 列宽基数
	public static final int COLWIDTHBASE = 256;
	// 行高基数
	public static final int ROWHEIGHTBASE = 20;
	// 换行符
	public static final String NEWLINE = "\n";
	
	//表格基数集合
	public Map<String,String> tableRowBase = new HashMap<String,String>();
	public Map<String,String> tableColBase = new HashMap<String,String>();

	public PrintBase(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 范围样式copy
	 * 
	 * @param oldSeetName
	 *            旧sheet名
	 * @param outSheetName
	 *            目标sheet名
	 * @param pageStartRow
	 *            开始行
	 * @param pageEndRow
	 *            结束行
	 * @param startCol
	 *            开始行
	 * @param endCol
	 *            结束列
	 * @param readPlace
	 *            位置
	 * @param hssInputExcelFile
	 *            模板
	 */
	public void mdRangeCopy(String oldSeetName, String outSheetName,
			int pageStartRow, int pageEndRow, int startCol, int endCol,
			int readPlace, HSSFWorkbook hssInputExcelFile) {
		// 源行
		HSSFRow oldRow = null;
		// 目标行
		HSSFRow targetRow = null;
		// 源单元格
		HSSFCell oldCell = null;
		// 目标单元格
		HSSFCell targetCell = null;
		// 源sheet
		HSSFSheet oldSheet = null;
		// 目标Sheet
		HSSFSheet targetSheet = null;
		// 范围
		Region range = null;
		// 格式
		int lcType;
		// 行
		int lcRow;
		// sheet开始行
		int sheetStartRow;
		// sheet结束行
		int sheetEndRow;
		if ((pageStartRow == -1) || (pageEndRow == -1)) {
			return;
		}
		oldSheet = hssInputExcelFile.getSheet(oldSeetName);
		targetSheet = hssInputExcelFile.getSheet(outSheetName);
		int num = oldSheet.getNumMergedRegions();
		// 复制结合单元格
		for (lcRow = 0; lcRow < num; lcRow++) {
			range = oldSheet.getMergedRegionAt(lcRow);
			if ((range.getRowFrom() >= pageStartRow)
					&& (range.getRowTo() <= pageEndRow)
					&& (range.getColumnFrom() >= startCol)
					&& (range.getColumnTo() <= endCol)) {
				sheetStartRow = range.getRowFrom() - pageStartRow + readPlace;
				sheetEndRow = range.getRowTo() - pageStartRow + readPlace;
				range.setRowFrom(sheetStartRow);
				range.setRowTo(sheetEndRow);
				targetSheet.addMergedRegion(range);
			}
		}

		// 列幅设置
		if (pageStartRow == 0) {
			for (lcRow = pageStartRow; lcRow <= pageEndRow; lcRow++) {
				oldRow = oldSheet.getRow(lcRow);
				if (oldRow != null) {
					for (short i = (short) startCol; i <= endCol; i++) {
						targetSheet.setColumnWidth(i,
								oldSheet.getColumnWidth(i));
					}
					break;
				}
			}
		}

		// 行copy
		for (lcRow = pageStartRow; lcRow <= pageEndRow; lcRow++) {
			oldRow = oldSheet.getRow(lcRow);
			if (oldRow == null) {
				continue;
			}

			targetRow = targetSheet.getRow(lcRow - pageStartRow + readPlace);
			if (targetRow == null) {
				targetRow = targetSheet.createRow(lcRow - pageStartRow
						+ readPlace);
			}
			targetRow.setHeight(oldRow.getHeight());
			for (short i = (short) (startCol); i <= endCol; i++) {
				oldCell = oldRow.getCell(i);
				if (oldCell == null) {
					continue;
				}
				targetCell = targetRow.createCell(i);
				targetCell.setCellStyle(oldCell.getCellStyle());
				lcType = oldCell.getCellType();
				targetCell.setCellType(lcType);
				switch (lcType) {
				case HSSFCell.CELL_TYPE_BOOLEAN:
					targetCell.setCellValue(oldCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					targetCell.setCellErrorValue(oldCell.getErrorCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					// parseFormula
					targetCell.setCellFormula(this.parseFormula(oldCell
							.getCellFormula()));
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					targetCell.setCellValue(oldCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					targetCell.setCellValue(oldCell.getStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					targetCell.setCellValue(oldCell.getStringCellValue());
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Formula
	 * 
	 * @param pPOIFormula
	 *            pPOIFormula
	 * @return Formula
	 */
	private String parseFormula(String pPOIFormula) {
		final String cstReplaceString = "ATTR(semiVolatile)";
		StringBuffer result = null;
		int index;

		result = new StringBuffer();
		index = pPOIFormula.indexOf(cstReplaceString);
		if (index >= 0) {
			result.append(pPOIFormula.substring(0, index));
			result.append(pPOIFormula.substring(index
					+ cstReplaceString.length()));
		} else {
			result.append(pPOIFormula);
		}

		return result.toString();
	}

	/**
	 * 打印设置 TODO
	 */
	public void printSet(HSSFSheet hssInputExcelSheet,
			HSSFSheet hssOutputExcelSheet) {
		try {
			// sheet set
			HSSFPrintSetup hssFromSheetSet = hssInputExcelSheet.getPrintSetup();
			hssOutputExcelSheet.setSelected(true);
			// hssOutputExcelSheet.setDisplayGridlines(false);
			hssOutputExcelSheet.getPrintSetup().setPaperSize(
					hssFromSheetSet.getPaperSize());
			hssOutputExcelSheet.getPrintSetup().setLandscape(
					hssFromSheetSet.getLandscape());
			hssOutputExcelSheet.getPrintSetup().setHeaderMargin(
					hssFromSheetSet.getHeaderMargin());
			hssOutputExcelSheet.getPrintSetup().setFooterMargin(
					hssFromSheetSet.getFooterMargin());
			hssOutputExcelSheet.setHorizontallyCenter(hssInputExcelSheet
					.getHorizontallyCenter());
			hssOutputExcelSheet.setVerticallyCenter(hssInputExcelSheet
					.getVerticallyCenter(true));

			hssOutputExcelSheet.setAutobreaks(true);
			hssOutputExcelSheet.setHorizontallyCenter(true); // 设置打印页面为水平居中

			// sheet 打印比例.（3/4）=0.75 = 75%
			hssOutputExcelSheet.setZoom(4, 4);
			// set display grid lines or not
			hssOutputExcelSheet.setDisplayGridlines(false);
			// set print grid lines or not
			hssOutputExcelSheet.setPrintGridlines(false);

			hssOutputExcelSheet.setMargin(HSSFSheet.TopMargin, 1d);
			hssOutputExcelSheet.setMargin(HSSFSheet.BottomMargin, 1d);
			hssOutputExcelSheet.setMargin(HSSFSheet.LeftMargin, 0.7d);
			hssOutputExcelSheet.setMargin(HSSFSheet.RightMargin, 0.7d);

		} catch (Exception e) {
			// TODO
		}
	}

	/**
	 * 打印设置 TODO
	 */
	public void printSet2(HSSFSheet hssInputExcelSheet,
			HSSFSheet hssOutputExcelSheet) {
		try {
			// sheet set
			HSSFPrintSetup hssFromSheetSet = hssInputExcelSheet.getPrintSetup();
			hssOutputExcelSheet.setSelected(true);
			// hssOutputExcelSheet.setDisplayGridlines(false);
			hssOutputExcelSheet.getPrintSetup().setPaperSize(
					hssFromSheetSet.getPaperSize());
			hssOutputExcelSheet.getPrintSetup().setLandscape(
					hssFromSheetSet.getLandscape());
			hssOutputExcelSheet.getPrintSetup().setHeaderMargin(
					hssFromSheetSet.getHeaderMargin());
			hssOutputExcelSheet.getPrintSetup().setFooterMargin(
					hssFromSheetSet.getFooterMargin());
			hssOutputExcelSheet.setHorizontallyCenter(hssInputExcelSheet
					.getHorizontallyCenter());
			hssOutputExcelSheet.setVerticallyCenter(hssInputExcelSheet
					.getVerticallyCenter(true));

			hssOutputExcelSheet.setAutobreaks(false);
			hssOutputExcelSheet.setHorizontallyCenter(true); // 设置打印页面为水平居中

			// sheet 打印比例.（3/4）=0.75 = 75%
			hssOutputExcelSheet.setZoom(4, 4);
			// set display grid lines or not
			hssOutputExcelSheet.setDisplayGridlines(false);
			// set print grid lines or not
			hssOutputExcelSheet.setPrintGridlines(false);

			hssOutputExcelSheet.setMargin(HSSFSheet.TopMargin, 0.7d);
			hssOutputExcelSheet.setMargin(HSSFSheet.BottomMargin, 0.3d);
			hssOutputExcelSheet.setMargin(HSSFSheet.LeftMargin, 0.0d);
			hssOutputExcelSheet.setMargin(HSSFSheet.RightMargin, 0.0d);

		} catch (Exception e) {
			// TODO
		}
	}
	
	/**
	 * 图片设置 TODO
	 */
	public void picSet(HSSFWorkbook hssInputExcelFile,
			HSSFSheet hssOutputExcelSheet) {
		try {
			HSSFPatriarch patriarch = (HSSFPatriarch) hssOutputExcelSheet
					.createDrawingPatriarch();
			// SKlogo
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(new File(this.getPicPath()));
			ImageIO.write(bufferImg, "png", byteArrayOut);
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1, 1,
					(short) 0, 1, (short) 2, 2);
			patriarch.createPicture(anchor, hssInputExcelFile.addPicture(
					byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		} catch (Exception e) {
			// TODO
		}
	}
	
	/**
	 * 图片设置 TODO
	 */
	public void picSet2(HSSFWorkbook hssInputExcelFile,
			HSSFSheet hssOutputExcelSheet, int placeN) {
		try {
			HSSFPatriarch patriarch = (HSSFPatriarch) hssOutputExcelSheet
					.createDrawingPatriarch();
			// SKlogo
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(new File(this.getPicPath()));
			ImageIO.write(bufferImg, "png", byteArrayOut);
			//int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2,int row2
			//dx1 - the x coordinate within the first cell.
			//dy1 - the y coordinate within the first cell.
			//dx2:第二个单元格的开始X坐标
			//dy2:第二个单元格的开始y坐标
			//col1 图片的左上角放在第几个列cell
			//row1 图片的左上角放在第几个行cell， 
			//col2 图片的右下角放在第几个列cell， 
			//row2 图片的右下角放在第几个行cell，
			// PS图片从1开始
//			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 2,
//					(short) 1, 0, (short) 3, 2);
//			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 2,
//					(short) 1, 40, (short) 3, 42);
//			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 2,
//					(short) 1, 80, (short) 3, 82);
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 2,
					(short) 1, placeN, (short) 3, placeN + 2);
			patriarch.createPicture(anchor, hssInputExcelFile.addPicture(
					byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		} catch (Exception e) {
			// TODO
		}
	}
	
	/**
	 * 图片设置 TODO
	 */
	public void picSet3(HSSFWorkbook hssInputExcelFile,
			HSSFSheet hssOutputExcelSheet) {
		try {
			HSSFPatriarch patriarch = (HSSFPatriarch) hssOutputExcelSheet
					.createDrawingPatriarch();
			// SKlogo
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(new File(this.getPicPath()));
			ImageIO.write(bufferImg, "png", byteArrayOut);
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 2, 2,
					(short) 0, 0, (short) 2, 2);
			patriarch.createPicture(anchor, hssInputExcelFile.addPicture(
					byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		} catch (Exception e) {
			// TODO
		}
	}

	/*
	 * getFilePath
	 */
	public String getFilePath(String outSheetName) {
		return  OUTPUTFILEPATH + outSheetName + new Date().getTime() + OUTPUTFILETYPE;
	}
	
	/*
	 * getRealPath
	 */
	public String getRealPath() {
		return this.request.getSession().getServletContext().getRealPath("/");
	}

	/*
	 * getTemPlatPath
	 */
	public String getTemPlatPath() {
		return this.getRealPath() + TEMPLANTPATH + "temp.xls";
	}
	
	/*
	 * getTemPlatPath
	 */
	public String getTemPlatPath2() {
		return this.getRealPath() + TEMPLANTPATH + "weixiuTemp.xls";
	}
	
	/*
	 * getTemPlatPath
	 */
	public String getTemPlatPath3() {
		return this.getRealPath() + TEMPLANTPATH + "weixiufeiyonginfo.xls";
	}
	
	/*
	 * getTemPlatPath
	 */
	public String getTemPlatPathForProposal() {
		return this.getRealPath() + TEMPLANTPATH + "tianshuTemp.xls";
	}
	
	/*
	 * getPicPath
	 */
	public String getPicPath() {
		return this.getRealPath() + TEMPLANTPATH + "logo.png";
	}
	
	/**
	 * 四舍五入 todo
	 */
	public Double tRound(String poiFormat) {
		try {
			// double f = Double.valueOf(poiFormat);
			// BigDecimal b = new BigDecimal(f);
			// return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return Double.valueOf(poiFormat);

		} catch (Exception e) {
			return Double.valueOf(0);
		}
	}

	/*
	 * 删除文件
	 */
    public boolean deleteFile(String sPath) {
    	try{
            File file = new File(sPath);
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
    	}
    	catch(Exception e){
    		return false;
    	}
		return true;
    }

	/** 
	* 获取字符串长度：中文字符长度计2，其它计1 
	* 设置单元格行列高宽
	* rowNum 行指针
	* colNum 列指针
	* 
	* @param value 
	* @return 
	*/ 
	public String getStringHandle(String value,HSSFSheet hssOutputExcelSheet,int rowNum,int colNum){ 
		String lcStringHandle = "";
		   int length = 0; 
		   int baseCount = STRINGLENGTH;
		   //行高基数
		   int rowHeigthCount = 1;
		   //列宽基数
		   int colWidthCount = 1;
		   
		   //遍历单元格内容根据全角半角加入换行符
		   //计算行高基数
		    for (int i = 0; i < value.length(); i++) {
		        String temp = value.substring(i, i + 1); 

		        //全角
		        if (temp.matches("[\u0391-\uFFE5]")) { 
		            length += 2; 
		        } 
		        //半角
		        else { 
		            length += 1; 
		        } 
		        
		        //正好长度等于换行条件 先加字符后换行(不是最后一个字符的场合)
		        if(length == baseCount && i != value.length() -1){
		        	lcStringHandle = lcStringHandle + temp + NEWLINE;
		        	baseCount = baseCount + STRINGLENGTH;
		        	rowHeigthCount++;
		        }
		        //长度大于换行条件先换行后加字符
		        else if(length > baseCount){
		        	lcStringHandle = lcStringHandle + NEWLINE + temp;
		        	baseCount = baseCount + STRINGLENGTH;
		        	rowHeigthCount++;
		        }else{
		        	lcStringHandle = lcStringHandle + temp;
		        }
		        
		    } 
		    
		    //无换行的场合列宽等于内容长度
		    if(rowHeigthCount == 1){
		    	colWidthCount = length;
		    }
		    //换行的场合列宽等于允许最大长度
		    else{
		    	colWidthCount = STRINGLENGTH;
		    }
		    
		    
		    HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(rowNum);
		    //设置当前行高
		    String maxRowHeight = this.tableRowBase.get(rowNum+"");
		    if(null != maxRowHeight && !maxRowHeight.isEmpty()){
		    	//重新设置行高和当前行高最大值
		    	if(Double.valueOf(maxRowHeight).compareTo(Double.valueOf(rowHeigthCount+"")) < 0){
		    		this.tableRowBase.put(rowNum+"",rowHeigthCount+"");
		    		hssDetalSet.setHeight((short)(20*ROWHEIGHTBASE*rowHeigthCount));
		    	}
		    }else{
		    	this.tableRowBase.put(rowNum+"",rowHeigthCount+"");
		    	hssDetalSet.setHeight((short)(20*ROWHEIGHTBASE*rowHeigthCount));
		    }
		    
		    //设置当前列宽
		    String maxColWidth = this.tableColBase.get(colNum+"");
		    if(null != maxColWidth && !maxColWidth.isEmpty()){
		    	//重新设置列宽和当前列宽最大值
		    	if(Double.valueOf(maxColWidth).compareTo(Double.valueOf(colWidthCount+"")) < 0){
		    		this.tableColBase.put(colNum+"",colWidthCount+"");
		    		hssOutputExcelSheet.setColumnWidth(colNum,colWidthCount*COLWIDTHBASE + 2*COLWIDTHBASE);
		    	}
		    }else{
		    	this.tableColBase.put(colNum+"",colWidthCount+"");
		    	hssOutputExcelSheet.setColumnWidth(colNum,colWidthCount*COLWIDTHBASE + 2*COLWIDTHBASE);
		    } 
		    
		    //赋值
		    hssDetalSet = hssOutputExcelSheet.getRow(rowNum);
		    hssDetalSet.getCell(colNum).setCellValue(lcStringHandle);
		    return lcStringHandle; 
	} 
	
	/**
	 * 加序号
	 * 
	 * @param 数据源 printList
	 * @param 序号flag 1：首行序号，次行排序开始。 2：首行排序开始
	 * @return new objecj
	 */
	public List<String[]> listNoAdd(List<String[]> printList,int flag){
		List<String[]> resultList = new ArrayList<String[]>();
		
		for(int i = 0; i < printList.size(); i++){
			String[] row = printList.get(i);
			if(flag == 1){
				if(i == 0){
					resultList.add(concat(new String[]{"序号"}, row));
				}else{
					resultList.add(concat(new String[]{(i) + ""}, row));
				}
			}else{
				resultList.add(concat(new String[]{(i + 1) + ""}, row));
			}
		}
		return resultList;
	}
	
	/**
	 * 数组merge
	 * 
	 * @return result
	 */
	public static <T> T[] concat(T[] first, T[] second) {
	  T[] result = Arrays.copyOf(first, first.length + second.length);
	  System.arraycopy(second, 0, result, first.length, second.length);
	  return result;
	}
	
	//时间处理
	public String timeManage(String time){
		try{
			return time.replace("-", ".");
		}catch(Exception e){
			return time;
		}
	}

    /**
     * 两个Double数相减
     * @param v1
     * @param v2
     * @return Double
     */
    public Double sub(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }
    
   /**********************excle无模板打印*************************/
}
