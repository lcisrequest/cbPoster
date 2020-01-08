package com.cbposter.service;


import com.cbposter.utils.base.ImageLoadUtil;
import com.cbposter.utils.image.wrapper.merge.ImgMergeWrapper;
import com.cbposter.utils.image.wrapper.merge.cell.IMergeCell;
import com.cbposter.utils.image.wrapper.merge.template.QrCodeCardTemplate;
import com.cbposter.utils.image.wrapper.merge.template.QrCodeCardTemplateBuilder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: lc
 * @Date: 2020/1/8 14:30
 */
@Service
public class ImgMergeWrapperTest {

    public void testTemplate() throws IOException {
        BufferedImage logo = ImageLoadUtil.getImageByPath("D:\\home\\1.jpg");
        BufferedImage qrCode = ImageLoadUtil.getImageByPath("D:\\home\\2.jpg");
        String name = "小灰灰Blog";
        List<String> desc = Arrays.asList("我是一灰灰，一匹不吃羊的狼", "专注码农技术分享");

        int w = QrCodeCardTemplate.w, h = QrCodeCardTemplate.h;
        List<IMergeCell> list = QrCodeCardTemplateBuilder.build(logo, name, desc, qrCode, "微 信 公 众 号");

        BufferedImage bg = ImgMergeWrapper.merge(list, w, h);

        try {
            ImageIO.write(bg, "jpg", new File("D:\\home\\merge.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
