package com.cbposter.utils.base;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 * @Auther: lc
 * @Date: 2020/1/8 15:20
 */
public class Base64Util {
    public Base64Util() {
    }

    public static String encode(BufferedImage bufferedImage, String imgType) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, imgType, outputStream);
        return encode(outputStream);
    }

    public static String encode(ByteArrayOutputStream outputStream) {
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public static BufferedImage decode2Img(String base64) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(base64.getBytes("utf-8"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return ImageIO.read(inputStream);
    }
}