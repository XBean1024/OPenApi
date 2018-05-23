package com.binny.openapi.util;

/**
 * 作者: binny
 * 时间: 5/23
 * 描述:
 */
public class UtilString {
    public static String getHtml(String content, String color) {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\">  \n" +
                "<head>  \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  \n" +
                "<title>缩进2字符</title>  \n" +
                "<style type=\"text/css\">  \n" +
                "p{ text-indent:2em;}  \n" +
                "</style>  \n" +
                "</head>  \n" +
                "<body  bgcolor=\""+color+"\">  \n" +
                content +
                "</body>  \n" +
                "</html>  ";
        return html;
    }
}
