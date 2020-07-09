package com.pm.common.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
 
import javax.imageio.ImageIO;
 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.font.FontDesignMetrics;
import sun.misc.BASE64Encoder;
 
/** 
 * 二维码 生成、解析器 帮助类 
 * @author gaowenchao
 * 
 */
public class QrCodeCreate {
	
	/**
     * 颜色
     */
    private static final int QRCOLOR = 0xFF000000;
    /**
     * 背景颜色
     */
    private static final int BGWHITE = 0xFFFFFFFF;
    /**
     * 存放路径
     */
    private static final String CODEPATH = "E:\\PMpicture\\";


    public static String getQRCode(String data, String belowText,File file,int width,int height) {
        try {
        	QrCodeCreate zp = new QrCodeCreate();
            BufferedImage bim = zp.getQR_CODEBufferedImage(data, BarcodeFormat.QR_CODE, width, height, zp.getDecodeHintType());
            //字节数组
            return zp.addText_QRCode(bim, belowText,file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param bim       图片
     * @param belowText 二维码下方显示文字
     * @return
     */
    public String addText_QRCode(BufferedImage bim, String belowText,File file) {
        try {
            BufferedImage image = bim;
            if (belowText != null && !belowText.equals("")) {
                BufferedImage outImage = new BufferedImage(230, 225, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("宋体", Font.PLAIN, 18));
                int strWidth = outg.getFontMetrics().stringWidth(belowText);
                outg.drawString(belowText, 100 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 5);
                outg.dispose();
                outImage.flush();
                image = outImage;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "jpg", baos);

            BufferedImage newBufferedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(image, 0, 0,
                    Color.WHITE, null);
            File filePath = new File(CODEPATH);
            QrCodeCreate.judeDirExists(filePath);
            ImageIO.write(newBufferedImage, "jpg", file);
            //图片写到字节数组中
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            
            String imageBase64QRCode = encoder.encode(baos.toByteArray());
            baos.close();
            image.flush();
            return imageBase64QRCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绘制二维码
     *
     * @param content       扫描内容
     * @param barcodeFormat 格式
     * @param width
     * @param height
     * @param hints         二维码属性设置
     * @return 二维码图片
     */
    public BufferedImage getQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * 设置二维码属性
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param file
     */
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
            } else {
            }
        } else {
            file.mkdir();
        }

    }
	    
	    
}