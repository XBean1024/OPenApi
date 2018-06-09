package com.binny.openapi.util;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 作者: binny
 * 时间: 5/23
 * 描述: 对字符串的一些处理
 */
public class UtilString {
    public static String getHtml(String content, String color) {
        return "<html xmlns=\"http://www.w3.org/1999/xhtml\">  \n" +
                "<head>  \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  \n" +
                "<title>缩进2字符</title>  \n" +
                "<style type=\"text/css\">  \n" +
                "p{text-indent:2em;font-size:14px}  \n" +
                "</style>  \n" +
                "</head>  \n" +
                "<body  bgcolor=\""+color+"\">  \n" +
                content +
                "</body>  \n" +
                "</html>  ";
    }


    public static String ReadStreamOfJson(String JsonUri){
        InputStreamReader isr = null;
        String result = "";
        StringBuffer html = new StringBuffer();
        InputStream is = null;

        try
        {
            URL url = new URL(JsonUri); //根据Strng表现形式创建URL对象

            URLConnection urlConnection = url.openConnection();//返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接

            //urlConnection.setConnectTimeout(4000); //设置链接超时

            is = urlConnection.getInputStream();//返回从打开的连接中读取到的输入流对象

            isr = new InputStreamReader(is, "utf-8");

            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while((line = br.readLine()) != null)
            {
                html.append(line+"\r\n");
            }
//            Log.i("html", "ReadStreamOfJson: "+html);
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        writeLog(html.toString()); //写文件到sd卡
        return html.toString();
    }

    /************************************************************
     *@Author; 龙之游 @ xu 596928539@qq.com
     * 时间:2016/12/20 13:25
     * 注释:  写文件
     ************************************************************/
    public static File writeLog(String str){

        File file = null;
        Log.i("file", "__________________________________________________________________写入成功: "+str);
        try
        {
            String path= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + "crash_lzhy_moneyhll.txt";
            file=new File(path);
            if(!file.exists()){
                file.createNewFile();
            }

            FileOutputStream out=new FileOutputStream(file,false); //如果追加方式用true,此处覆盖
            StringBuffer sb=new StringBuffer();
            byte[] bytes = str.getBytes();
            out.write(bytes);
            //out.write(sb.toString().getBytes("utf-8"));//注意需要转换对应的字符集
            out.close();
            if (file.exists()) {
                return file;
            }else {
                Log.i("file", "__________________________________写入失败: "+file);
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }
}
