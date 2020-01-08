package com.cbposter.utils.base;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: lc
 * @Date: 2020/1/8 15:47
 */
public class ImageLoadUtil {
    public ImageLoadUtil() {
    }

    public static BufferedImage getImageByPath(String path) throws IOException {
        if (StringUtils.isBlank(path)) {
            return null;
        } else {
            InputStream stream = FileReadUtil.getStreamByFileName(path);
            return ImageIO.read(stream);
        }
    }
}
