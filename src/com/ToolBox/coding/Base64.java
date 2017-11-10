package com.ToolBox.coding;

import java.io.UnsupportedEncodingException;

/**
 * base64���ܽ��ܺ���
 *
 * @author ���
 */
public class Base64 {
    /**
     * ��Base64�����ַ���
     *
     * @param string Ҫ������ַ���
     * @return �������ַ���
     */
    public static String stringToBase64(String string) {
        byte[] src = new byte[0];
        try {
            src = string.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return java.util.Base64.getEncoder().encodeToString(src);
    }

    /**
     * ��Base64�����ַ���
     *
     * @param string δ������ַ���
     * @return �������ַ���
     */
    public static String base64ToString(String string) {
        try {
            byte[] src = string.getBytes("utf-8");
            byte[] tar = java.util.Base64.getDecoder().decode(src);
            return new String(tar, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "�޷�ת��";
        }
    }
}
