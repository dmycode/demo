package com.example.demo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 
    * @ClassName: ImageUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author dy
    * @date 2020年3月31日
 */
public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    // 图片默认缩放比率
    public static final double DEFAULT_SCALE = 0.8d;
    // 缩略图大小 宽
    public static final int iwidth = 50;
    // 缩略图大小 高
    public static final int iheight = 50;
    // 图片格式
    public static final String[] IMAGE_TYPE = { "JPEG", "JPG", "PNG", "jpeg",
            "jpg", "png", "BMP", "bmp" };

    /**
     * 
        * @Title: compressImageForceSize
        * @Description: 压缩一个图片,指定图片尺寸（默认宽50高50），不保持图片比例
        * @param @param filePath
        * @param @param thumbnailPath
        * @param @throws IOException    参数
        * @return void    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static void compressImageForceSize(String filePath,
            String thumbnailPath) throws IOException {
        Thumbnails.of(filePath).forceSize(ImageUtil.iwidth, ImageUtil.iheight)
                .toFile(thumbnailPath);
    }

    /**
     * 
        * @Title: compressImageForceSize
        * @Description: 压缩一个图片,指定图片尺寸，不保持图片比例
        * @param @param filePath
        * @param @param thumbnailPath
        * @param @param width
        * @param @param height
        * @param @throws IOException    参数
        * @return void    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static void compressImageForceSize(String filePath,
            String thumbnailPath, int width, int height) throws IOException {
        Thumbnails.of(filePath).forceSize(width, height).toFile(thumbnailPath);
    }

    /**
     * 
        * @Title: compressImageProportion
        * @Description: 压缩一个图片,指定宽度，计算比例压缩
        * @param @param filePath
        * @param @param thumbnailPath
        * @param @throws IOException    参数
        * @return void    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static void compressImageProportion(String filePath,
            String thumbnailPath, int width) throws IOException {
        File file = new File(filePath);
        compressImage(file, thumbnailPath, width);
    }

    /**
     * 
        * @Title: compressImageProportion
        * @Description: 压缩一个图片 宽度默认50,按比例压缩
        * @param @param filePath
        * @param @param thumbnailPath
        * @param @throws IOException    参数
        * @return void    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static void compressImageProportion(String filePath,
            String thumbnailPath) throws IOException {
        File file = new File(filePath);
        compressImage(file, thumbnailPath, ImageUtil.iwidth);
    }

    /**
     * 
        * @Title: compressImage
        * @Description: 压缩一个图片,指定宽度按比例压缩
        * @param     参数
        * @return void    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static void compressImage(File file, String thumbnailPath,
            int iwidth) throws IOException {
        double scale = ImageUtil.DEFAULT_SCALE;
        InputStream is = null;
        BufferedImage src = null;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            int width = src.getWidth(null);
            // 图片太小的时候，不压缩
            if (iwidth >= width) {
                scale = 1;
            } else {
                scale = (double) iwidth / (double) width;
            }
            is.close();
        } catch (Exception e) {
            logger.error("压缩图像数据失败:{}", e.toString());
            e.printStackTrace();
        } finally {
            is.close();
        }
        Thumbnails.of(file)
                // 图片缩放率，不能和size()一起使用
                .scale(scale)
                // 缩略图保存目录,该目录需存在，否则报错
                .toFile(thumbnailPath);
    }

    /**
     * 
        * @Title: isImageFile
        * @Description: 根据文件扩展名判断文件是否图片格式
        * @param @param extension
        * @param @return    参数
        * @return boolean    返回类型
    	* @author zhangzhongling
    	* @date 2018年11月13日
        * @throws
     */
    public static boolean isImageFile(String extension) {
        for (String e : ImageUtil.IMAGE_TYPE) {
            if (extension.equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将图片转换成Base64编码
     * 
     * @param imgFile
     *            待处理图片
     * @return
     */
    public static String getUrlImgStr(String urlImg) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        URL url = null;
        String imgStr = null;
        try {
            url = new URL(urlImg);
            DataInputStream dataInputStream = new DataInputStream(
                    url.openStream());
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int ch;
            while ((ch = dataInputStream.read(buffer)) != -1) {
                bytestream.write(buffer, 0, ch);
            }
            bytestream.close();
            dataInputStream.close();
            imgStr = new String(Base64.encodeBase64(bytestream.toByteArray()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgStr;
    }

    /**
     * 将图片转换成Base64编码
     * 
     * @param imgFile
     *            待处理图片
     * @return
     */
    public static String getImgStr(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        String imgStr = "";
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            imgStr = new String(Base64.encodeBase64(data));
        } catch (Exception e) {
            logger.error("获取图像数据失败:{}", e.toString());
            e.printStackTrace();
        }
        return imgStr;
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * 
     * @param imgStr
     *            图片数据
     * @param imgFilePath
     *            保存图片全路径地址
     * @return
     */
    public static boolean generateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) {
            // 图像数据为空
            logger.error("图像数据为空filePath={}", imgFilePath);
            return false;
        }
        try {
            File savefile = new File(imgFilePath);
            if (!savefile.getParentFile().exists()) {
                savefile.getParentFile().mkdirs();
            }
            // Base64解码
            byte[] b = Base64.decodeBase64(imgStr.getBytes());
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            logger.error("图像数据保存失败:{}", e.toString());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 
        * @Title: commpressImageToDesFileSize
        * @Description: 压缩图片到指定大小
        * @param @param filePath
        * @param @param desFileSize
        * @param @throws IOException    参数
        * @return void    返回类型
        * @author zhangzhongling
        * @date 2020年2月5日
        * @throws
     */
    public static void commpressImageToDesFileSize(String filePath,
            long desFileSize) throws IOException {
        File imgFile = new File(filePath);
        long fileSize = imgFile.length();
        // 判断大小,如果小于desFileSize,不压缩,如果大于等于desFileSize,压缩
        if (fileSize <= desFileSize) {
            if (fileSize == 0) {
                logger.error("文件不存在,length为0,filePath={}", filePath);
            }
            return;
        }
        double scale = ImageUtil.DEFAULT_SCALE;
        // 为了图片大小更接近目标值，修正压缩系数
        if (fileSize <= 2 * desFileSize) {
            scale = 0.95;
        }
        // 图片质量
        Thumbnails.of(filePath).scale(scale).toFile(filePath);
        // 如果不满足要求,递归直至满足小于desFileSize的要求
        commpressImageToDesFileSize(filePath, desFileSize);
    }

    /** 设置文字水印
     * @param sourceImg 源图片路径
     * @param targetImg 保存的图片路径
     * @param watermark 水印内容
     * @param color 水印颜色
     * @param font 水印字体
     * @throws IOException 
     */
    public static String claimAddWatermark(String sourceImg)
            throws IOException {
        Font font = new Font("微软雅黑", Font.BOLD, 5);// 字体，粗体
        Color color = new Color(255);// 颜色白色
        String watermark = "";
        return addWatermark(sourceImg, watermark, font, color);

    }

    /**
     * 
        * @Title: addWatermark
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @param base64Img
        * @param watermark
        * @param font
        * @param color
        * @return
        * @throws IOException 
        * String 返回类型
        * @author dy
    	* @date 2020年3月31日
        * @throws
     */
    public static String addWatermark(String base64Img, String watermark,
            Font font, Color color) throws IOException {
        ByteArrayOutputStream output = null;
        ByteArrayInputStream dataInput = null;
        try {
            byte[] bytes = Base64.decodeBase64(base64Img);
            dataInput = new ByteArrayInputStream(bytes);
            Image srcImg = ImageIO.read(dataInput);
            int srcImgWidth = srcImg.getWidth(null);
            int srcImgHeight = srcImg.getHeight(null);
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(color);
            g.setFont(font);
            // 设置水印的坐标，左上角
            g.drawString(watermark, 0, font.getSize()); // 加水印
            g.dispose();
            // 输出图片
            output = new ByteArrayOutputStream();
            ImageIO.write(bufImg, "jpg", output);
            dataInput = new ByteArrayInputStream(output.toByteArray());
            byte[] data = new byte[dataInput.available()];
            dataInput.read(data);
            base64Img = new String(Base64.encodeBase64(data));
        } catch (Exception e) {
            logger.error("添加水印失败case:{}", e.getMessage(), e);
            return null;
        } finally {
            output.flush();
            output.close();
            dataInput.close();
        }
        return base64Img;

    }

}
