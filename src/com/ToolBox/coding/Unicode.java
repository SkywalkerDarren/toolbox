package com.ToolBox.coding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unicode±àÂë½âÂëºËÐÄ
 *
 * @author Ñîºë
 */
public class Unicode {

    /**
     * ½âÂëUnicode×Ö·û´®
     *
     * @param str Î´½âÂëµÄ×Ö·û´®
     * @return ½âÂëºóµÄ×Ö·û´®
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
     * ÓÃunicode±àÂë×Ö·û´®£¬/uXXXXµÄÐÎÊ½
     *
     * @param s Òª±àÂëµ½×Ö·û´®
     * @return ±àÂëºóµÄ×Ö·û´®
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
