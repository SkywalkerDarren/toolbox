package com.ToolBox.coding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * md5加密核心
 *
 * @author 杨弘
 */
public class MD5 {
    /**
     * 将文本转换至MD5摘要
     *
     * @param content 要转换的文本
     * @return 32位大写的MD5摘要
     * @throws Exception 编码错误
     */
    public static String converToMD5(String content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] src = content.getBytes("utf-8");
        md.update(src);
        byte[] buff = md.digest();
        return toHex(buff);


    }

    /**
     * 检查文件MD5摘要
     *
     * @param filePath 要摘要的文件
     * @return 32位大写的MD5摘要
     * @throws IOException 读写错误
     * @throws GeneralSecurityException 生成错误
     */
    public static String checkFileMD5(String filePath) throws IOException, GeneralSecurityException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);
        md.update(data);
        byte[] buff = md.digest();
        return toHex(buff);
    }

    /**
     * 128位二进制转换为32位大写字符串
     *
     * @param buff 二进制流
     * @return 32位大写字符串
     */
    private static String toHex(byte[] buff) {
        StringBuilder sb = new StringBuilder("");
        int digit;
        for (byte b : buff) {
            digit = b;
            if (digit < 0) {
                digit &= 0xff;
            }
            if (digit < 0x10) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(digit));
        }
        return sb.toString().toUpperCase();
    }
}
