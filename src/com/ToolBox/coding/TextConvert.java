package com.ToolBox.coding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class TextConvert {

    public final static String UTF_8 = "UTF-8"; // ͨ��
    public final static String BIG5 = "BIG5"; // ����
    public final static String GBK = "GBK"; //����
    public final static String GB2312 = "GB2312"; //����
    public final static String SJIS = "SJIS"; // ����
    public final static String JIS = "JIS"; // ����

    /**
     * ��ָ���ļ���Ŀ¼ת����ָ���ı���
     *
     * @param file            Ҫת�����ļ���Ŀ¼
     * @param fromCharsetName Դ�ļ��ı���
     * @param toCharsetName   Ҫת���ı���
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
     * ��ָ�����뷽ʽ��ȡ�ļ��������ļ�����
     *
     * @param file            Ҫת�����ļ�
     * @param fromCharsetName Դ�ļ��ı���
     * @return �ļ������ַ���
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
     * ��ָ�����뷽ʽд�ı��ļ������ڻḲ��
     *
     * @param file          Ҫд����ļ�
     * @param toCharsetName Ҫת���ı���
     * @param content       �ļ�����
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
