package com.binny.openapi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者: binny
 * 时间: 5/23
 * 描述:
 */
public class UtilsRegular {
    /**
     * @param s1 原始字符串
     * @param s2 被替换者
     * @param s3 替换者
     */
    public static String replace(String s1, String s2, String s3) {
        Pattern r = Pattern.compile(s2);//<p>
        Matcher m = r.matcher(s1);//<p>我曾经坐在台北市议会的议
        return m.replaceAll(s3);//<p>\u3000\u3000 \u3000\u3000
    }
}
