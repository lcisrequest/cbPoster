package com.cbposter.utils.base;

/**
 * @Auther: lc
 * @Date: 2020/1/8 16:00
 */

public class NumUtil {
    public NumUtil() {
    }

    public static Integer decode2int(String str, Integer defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Long.decode(str).intValue();
            } catch (Exception var3) {
                return defaultValue;
            }
        }
    }

    public static String toHex(int hex) {
        String str = Integer.toHexString(hex);
        return str.length() == 1 ? "0" + str : str;
    }
}
