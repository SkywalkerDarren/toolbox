package com.ToolBox.coding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class TextConvert {

    public final static String UTF_8 = "UTF-8"; // 通用
    public final static String BIG5 = "BIG5"; // 繁体
    public final static String GBK = "GBK"; //简体
    public final static String GB2312 = "GB2312"; //简体
    public final static String SJIS = "SJIS"; // 日语
    public final static String JIS = "JIS"; // 日语

    /**
     * 把指定文件或目录转换成指定的编码
     *
     * @param file            要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName   要转换的编码
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName) throws Exception {
        String fileContent = getContentFromCharset(file, fromCharsetName);
        saveFile2Charset(file, toCharsetName, fileContent);
    }

    public static String convert(String string, String fromCharsetName, String toCharsetName) throws Exception {
        if (!Charset.isSupported(fromCharsetName) || !Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        byte[] b = string.getBytes(fromCharsetName);
        return new String(b, toCharsetName);
    }

    /**
     * 以指定编码方式读取文件，返回文件内容
     *
     * @param file            要转换的文件
     * @param fromCharsetName 源文件的编码
     * @return 文件内容字符串
     */
    public static String getContentFromCharset(File file, String fromCharsetName) throws Exception {
        if (!Charset.isSupported(fromCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream,
                fromCharsetName);
        char[] chs = new char[(int) file.length()];
        reader.read(chs);
        String str = new String(chs).trim();
        reader.close();
        return str;
    }

    /**
     * 以指定编码方式写文本文件，存在会覆盖
     *
     * @param file          要写入的文件
     * @param toCharsetName 要转换的编码
     * @param content       文件内容
     */
    public static void saveFile2Charset(File file, String toCharsetName, String content) throws Exception {
        if (!Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(toCharsetName);
        }
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outWrite = new OutputStreamWriter(outputStream,
                toCharsetName);
        outWrite.write(content);
        outWrite.close();
    }
}
