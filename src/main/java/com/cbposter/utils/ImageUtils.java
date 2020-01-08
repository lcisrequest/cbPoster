package com.cbposter.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther: lc
 * @Date: 2020/1/8 14:11
 */
public class ImageUtils {

    public static void getImage() throws IOException {

        //读取文件
        BufferedImage bi_0 = ImageIO.read(new File("D:\\home\\1.jpg"));
        BufferedImage bi_1 = ImageIO.read(new File("D:\\home\\2.jpg"));

        //假设图片0 和图片1 宽度相同，上下合成
        //new 一个新的图像
        int w = bi_0.getWidth();
        int h_0 = bi_0.getHeight();
        int h_1 = bi_1.getHeight();
        int h = h_0 + h_1;
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);

        //像素一个一个复制过来
        for (int y = 0; y < h_0; y++) {
            for (int x = 0; x < w; x++) {
                bi.setRGB(x, y, bi_0.getRGB(x, y));
            }
        }
        for (int y = 0; y < h_1; y++) {
            for (int x = 0; x < w; x++) {
                bi.setRGB(x,  y, bi_1.getRGB(x, y));
            }
        }
        //输出文件
        try {
            ImageIO.write(bi, "JPEG", new File("D:/1+2.jpg"));
        } catch (IOException ex) {
        }
    }
}
