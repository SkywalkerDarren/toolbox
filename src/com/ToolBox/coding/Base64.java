package com.ToolBox.coding;

import java.io.UnsupportedEncodingException;

/**
 * base64加密解密核心
 *
 * @author 杨弘
 */
public class Base64 {
    /**
     * 用Base64编码字符串
     *
     * @param string 要编码的字符串
     * @return 编码后的字符串
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
     * 用Base64解码字符串
     *
     * @param string 未解码的字符串
     * @return 解码后的字符串
     */
    public static String base64ToString(String string) {
        try {
            byte[] src = string.getBytes("utf-8");
            byte[] tar = java.util.Base64.getDecoder().decode(src);
            return new String(tar, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "无法转换";
        }
    }
}
