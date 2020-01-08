package com.cbposter.utils.base;


/**
 * @Auther: lc
 * @Date: 2020/1/8 15:22
 */
public class DomUtil {
    public DomUtil() {
    }

    public static String toDomSrc(String base64str, MediaType mediaType) {
        return mediaType.getPrefix() + base64str;
    }
}
