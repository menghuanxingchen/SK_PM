package com.pm.common.print;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

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
public class QrCodePrint  implements Printable {
	/*// 传入文件和打印机名称
	public static void JPGPrint(File file,String printerName) throws PrintException {
		if (file == null) {
			System.err.println("缺少打印文件");
		}
		InputStream fis = null;
		try {
			// 设置打印格式，如果未确定类型，可选择autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.JPEG;
			// 设置打印参数
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(new Copies(1)); //份数
			aset.add(MediaSize.ISO.); //纸张
			// aset.add(Finishings.STAPLE);//装订
			aset.add(Sides.ONE_SIDED);//单双面
			// 定位打印服务
			PrintService printService = null;
			if (printerName != null) {
				//获得本台电脑连接的所有打印机
				PrintService[] printServices = PrinterJob.lookupPrintServices();
				if(printServices == null || printServices.length == 0) {
					System.out.print("打印失败，未找到可用打印机，请检查。");
					return ;
				}
				//匹配指定打印机
				for (int i = 0;i < printServices.length; i++) {
					System.out.println(printServices[i].getName());
					if (printServices[i].getName().contains(printerName)) {
						printService = printServices[i];
						break;
					}
				}
				if(printService==null){
					System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
					return ;
				}
			}
			fis = new FileInputStream(file); // 构造待打印的文件流
			Doc doc = new SimpleDoc(fis, flavor, null);
			DocPrintJob job = printService.createPrintJob(); // 创建打印作业
			job.print(doc, aset);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
	 // 关闭打印的文件流
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

	

    /** 
     * 打印方法---已经写死了宽度和高度
     */
    public void ePrint()
    {
    	String printerName="ZDesigner ZT410-300dpi ZPL (副本 1)";
        Book book = new Book();
        PageFormat pageFormat = new PageFormat();  
        pageFormat.setOrientation(PageFormat.PORTRAIT); // LANDSCAPE表示竖打;PORTRAIT表示横打;REVERSE_LANDSCAPE表示打印空白  
        Paper paper = pageFormat.getPaper(); 
        //设置宽度和高度   PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英寸的1/72，1英寸为25.4毫米 如果是设置厘米的自己换算一下
        paper.setSize(200, 200); 
        paper.setImageableArea(0, 0, 200, 200);
        pageFormat.setPaper(paper); 
        PrinterJob printerJob = PrinterJob.getPrinterJob();  
        book.append(this, pageFormat,1);
        printerJob.setPageable(book);
        try
        {
        	PrintService printService = null;
			
			//获得本台电脑连接的所有打印机
			PrintService[] printServices = PrinterJob.lookupPrintServices();
			if(printServices == null || printServices.length == 0) {
				System.out.print("打印失败，未找到可用打印机，请检查。");
				return ;
			}
			//匹配指定打印机
			for (int i = 0;i < printServices.length; i++) {
				System.out.println(printServices[i].getName());
				if (printServices[i].getName().contains(printerName)) {
					printService = printServices[i];
					break;
				}
			}
			if(printService==null){
				System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
				return ;
			}
			
            printerJob.setPrintService(printService);
         
            printerJob.print();
        }
        catch (PrinterException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 需要打印的图片路径
     */
    private String imgSRC;
    
    public  QrCodePrint(String imgSrc)
    {
        this.imgSRC = imgSrc;
    }
    
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
        throws PrinterException
    {
        if (pageIndex > 0)
        {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D)graphics;
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        ImageIcon image = new ImageIcon(imgSRC);
        double pageW = pageFormat.getImageableWidth();
        double pageH = pageFormat.getImageableHeight();
        double imageW = image.getIconWidth();
        double imageH = image.getIconHeight();
        double scaleX = pageW / imageW;
        double scaleY = pageH / imageH;
        double scaleFactor = Math.min(scaleX, scaleY);
        g2d.scale(scaleFactor, scaleFactor);
        g2d.drawImage(image.getImage(), 0, 0, null);
        return Printable.PAGE_EXISTS;
    }


}
