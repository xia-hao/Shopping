package com.shopping.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: han.zhang
 * Date: 2020/6/3 0003
 * Time: 13:54
 * 压缩图片
 */
public class ImageUtil {

    private File file = null; // 文件对象
    private MultipartFile inputFile; // 输入图片流
    private String outputDir; // 输出图路径
    private String outputFileName; // 输出图文件名
    private int outputWidth = 100; // 默认输出图片宽
    private int outputHeight = 100; // 默认输出图片高
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

    public ImageUtil() { // 初始化变量
        inputFile = null;
        outputDir = "";
        outputFileName = "";
        outputWidth = 100;
        outputHeight = 100;
    }

    public void setInputDir(MultipartFile inputFile) {
        this.inputFile = inputFile;
    }
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }
    public void setOutputWidth(int outputWidth) {
        this.outputWidth = outputWidth;
    }
    public void setOutputHeight(int outputHeight) {
        this.outputHeight = outputHeight;
    }
    public void setWidthAndHeight(int width, int height) {
        this.outputWidth = width;
        this.outputHeight = height;
    }

    /*
     * 获得图片大小
     * 传入参数 String path ：图片路径
     */
    public long getPicSize(String path) {
        file = new File(path);
        return file.length();
    }

    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    // 图片处理
    public String compressPic() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 获取文件名
        String oldName = inputFile.getOriginalFilename();
        // 获取文件后缀
        String prefix=oldName.substring(oldName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File file = File.createTempFile(sdf.format(new Date()), prefix);
        // MultipartFile to File
        inputFile.transferTo(file);

        //你的业务逻辑
        try {
            if (!file.exists()) {
                return "";
            }
            Image img = ImageIO.read(file);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return "no";
            } else {
                int newWidth; int newHeight;
                // 判断是否是等比缩放
                if (this.proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getHeight(null)) / ((double) img.getWidth(null));
                    // 根据缩放比率大的进行缩放控制
                    newWidth = 1920;
                    newHeight = (int) (1920 * rate1);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
                 * 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
                // JPEGImageEncoder可适用于其他图片类型的转换
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //程序结束时，删除临时文件
        deleteFile(file);
        return "ok";
    }
    public String compressPic (MultipartFile inputFile, String outputDir, String outputFileName) throws IOException {
        // 输入图路径
        this.inputFile = inputFile;
        // 输出图路径
        this.outputDir = outputDir;
        // 输出图文件名
        this.outputFileName = outputFileName;
        return compressPic();
    }
    public String compressPic(MultipartFile inputFile, String outputDir, String outputFileName, int width, int height, boolean gp) throws IOException {
        // 输入图路径
        this.inputFile = inputFile;
        // 输出图路径
        this.outputDir = outputDir;
        // 输出图文件名
        this.outputFileName = outputFileName;
        // 设置图片长宽
        setWidthAndHeight(width, height);
        // 是否是等比缩放 标记
        this.proportion = gp;
        return compressPic();
    }

    // 压缩图片并返回压缩图片的路径 System.getProperty("user.dir")
    // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
    public static String resFilePath(MultipartFile multipartFile) throws IOException {
//        File pdfFile = new File("C:\\Users\\Administrator\\Pictures\\Camera Roll\\电脑壁纸\\2019-6.jpg");
//        FileInputStream fileInputStream = new FileInputStream(pdfFile);
//        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        ImageUtil mypic = new ImageUtil();
        int count = 0; // 记录全部图片压缩所用时间
        String resName = sdf.format(new Date())+"-" + multipartFile.getOriginalFilename();
        mypic.compressPic(multipartFile, System.getProperty("user.dir")+"\\images\\", resName, 1920, 0, true);
        String resFilePath = System.getProperty("user.dir")+"\\images\\"+ resName;
        return resFilePath;
    }
}
