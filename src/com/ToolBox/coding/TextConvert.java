package com.ToolBox.coding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * 文本转换
 *
 * @author 杨弘
 */
public class TextConvert {

    public final static String UTF_8 = "UTF-8"; // 通用
    public final static String BIG5 = "BIG5"; // 繁体
    public final static String GBK = "GBK"; //简体
    public final static String GB2312 = "GB2312"; //简体
    public final static String SJIS = "SJIS"; // 日语
    public final static String JIS = "JIS"; // 日语

    private final static String txt = "txt";
    private static final String js = "js";
    private static final String log = "log";
    private final static String c = "c";
    private final static String java = "java";
    private final static String cpp = "cpp";
    private final static String xml = "xml";
    private final static String json = "json";
    private final static String yaml = "yaml";
    private final static String h = "h";
    private final static String md = "md";
    private final static String sh = "sh";

    /**
     * 把文件或目录转换成指定的编码
     *
     * @param file        要转换的文件或目录
     * @param fromCharset 源编码
     * @param toCharset   要转换的编码
     */
    public static void convertRoot(File file, String fromCharset, String toCharset) {
        if (file.isDirectory()) {
            File[] files = file.listFiles((dir, name) -> (name.lastIndexOf(".") == -1 ||
                    name.lastIndexOf(".") != -1 && (name.endsWith(txt) || name.endsWith(c) || name.endsWith(java) ||
                            name.endsWith(cpp) || name.endsWith(json) || name.endsWith(yaml) || name.endsWith(xml) ||
                            name.endsWith(h) || name.endsWith(log) || name.endsWith(js) || name.endsWith(md) ||
                            name.endsWith(sh))));
            try {
                assert files != null;
                for (File f : files) {
                    if (f.isDirectory()) {
                        convertRoot(f, fromCharset, toCharset);
                    } else {
                        convert(f, fromCharset, toCharset);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (file.isFile()) {
            try {
                convert(file, fromCharset, toCharset);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 把文件转换成指定的编码
     *
     * @param file            要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName   要转换的编码
     */
    private static void convert(File file, String fromCharsetName, String toCharsetName) {
        String fileContent = getContentFromCharset(file, fromCharsetName);
        saveFile2Charset(file, toCharsetName, fileContent);
    }

    /**
     * 把字符串转换成指定的编码
     *
     * @param string          要转换的字符串
     * @param fromCharsetName 字符串的编码
     * @param toCharsetName   要转换的编码
     * @return 转换后的字符串
     */
    public static String convert(String string, String fromCharsetName, String toCharsetName) {
        try {
            byte[] b = string.getBytes(fromCharsetName);
            return new String(b, toCharsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("编码不支持");
        }
    }

    /**
     * 以指定编码方式读取文件，返回文件内容
     *
     * @param file            要转换的文件
     * @param fromCharsetName 源文件的编码
     * @return 文件内容字符串
     */
    private static String getContentFromCharset(File file, String fromCharsetName) {

        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream, fromCharsetName);
            char[] chs = new char[(int) file.length()];
            reader.read(chs);
            String str = new String(chs).trim();
            reader.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件错误");
        }
    }

    /**
     * 以指定编码方式写文本文件，存在会覆盖
     *
     * @param file          要写入的文件
     * @param toCharsetName 要转换的编码
     * @param content       文件内容
     */
    private static void saveFile2Charset(File file, String toCharsetName, String content) {
        if (!Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(toCharsetName);
        }

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outWrite = new OutputStreamWriter(outputStream,
                    toCharsetName);
            outWrite.write(content);
            outWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}