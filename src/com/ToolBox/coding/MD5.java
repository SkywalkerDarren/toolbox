package com.ToolBox.coding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * md5���ܺ���
 *
 * @author ���
 */
public class MD5 {
    /**
     * ���ı�ת����MD5ժҪ
     *
     * @param content Ҫת�����ı�
     * @return 32λ��д��MD5ժҪ
     * @throws Exception �������
     */
    public static String converToMD5(String content) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] src = content.getBytes("utf-8");
        md.update(src);
        byte[] buff = md.digest();
        return toHex(buff);


    }

    /**
     * ����ļ�MD5ժҪ
     *
     * @param filePath ҪժҪ���ļ�
     * @return 32λ��д��MD5ժҪ
     * @throws IOException ��д����
     * @throws GeneralSecurityException ���ɴ���
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
     * 128λ������ת��Ϊ32λ��д�ַ���
     *
     * @param buff ��������
     * @return 32λ��д�ַ���
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
