package com.ToolBox.coding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unicode编码解码核心
 *
 * @author 杨弘
 */
public class Unicode {

    /**
     * 解码Unicode字符串
     *
     * @param str 未解码的字符串
     * @return 解码后的字符串
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * 用unicode编码字符串，/uXXXX的形式
     *
     * @param s 要编码到字符串
     * @return 编码后的字符串
     */
    public static String stringToUnicode(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        for (char c : s.toCharArray()) {
            sb.append("\\u");
            sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
            sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
            sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
            sb.append(Character.forDigit((c) & 0xf, 16));

        }
        return sb.toString();
    }
}
