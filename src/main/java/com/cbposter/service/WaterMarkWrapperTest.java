package com.cbposter.service;

import com.cbposter.utils.base.ImageLoadUtil;
import com.cbposter.utils.image.wrapper.wartermark.WaterMarkOptions;
import com.cbposter.utils.image.wrapper.wartermark.WaterMarkWrapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: lc
 * @Date: 2020/1/8 14:28
 */
public class WaterMarkWrapperTest {

    public void testWaterMark() {
        try {
            BufferedImage img = WaterMarkWrapper.of("bg.png")
                    .setInline(true)
                    .setWaterLogo("xcx.jpg")
                    .setWaterLogoHeight(50)
                    .setWaterInfo(" 图文小工具\n By 小灰灰Blog")
                    .setStyle(WaterMarkOptions.WaterStyle.FILL_BG)
                    .setWaterColor(Color.LIGHT_GRAY)
                    .setWaterOpacity(0.8f)
                    .setRotate(45)
                    .setPaddingX(80)
                    .setPaddingX(80)
                    .build()
                    .asImage();

            ImageIO.write(img, "jpg", new File("/tmp/FILL_BG.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void testBgWaterMark() {
        try {
            BufferedImage img= WaterMarkWrapper.of("bg.png")
                    .setInline(true)
                    .setWaterLogo("xcx.jpg")
                    .setWaterLogoHeight(50)
                    .setWaterColor(Color.WHITE)
                    .setWaterInfo(" 图文小工具\n By 小灰灰Blog")
                    .setX(50)
                    .setY(50)
                    .setStyle(WaterMarkOptions.WaterStyle.FILL_BG)
                    .setWaterOpacity(0.5f)
                    .setRotate(45)
                    .setPaddingX(60)
                    .setPaddingY(80)
                    .build()
                    .asImage();

            ImageIO.write(img, "png", new File("/tmp/mark.png"));
            System.out.println(img);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void testRotate() throws IOException {
        BufferedImage bufferedImage = ImageLoadUtil.getImageByPath("bg.png");
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.rotate(Math.toRadians(90), bufferedImage.getWidth() >> 1, bufferedImage.getHeight() >> 1);
        g2d.dispose();


        AffineTransform tx = new AffineTransform();
        tx.rotate(0.5, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(bufferedImage, null);
        System.out.println("--------");
    }
}
