package com.cbposter.utils.base;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: lc
 * @Date: 2020/1/8 15:21
 */
public class BasicFileUtil {
    public BasicFileUtil() {
    }

    public static boolean isAbsFile(String fileName) {
        if (!OSUtil.isWinOS()) {
            return fileName.startsWith("/");
        } else {
            return fileName.contains(":") || fileName.startsWith("\\");
        }
    }

    public static String parseHomeDir2AbsDir(String path) {
        String homeDir = System.getProperties().getProperty("user.home");
        return StringUtils.replace(path, "~", homeDir);
    }
}