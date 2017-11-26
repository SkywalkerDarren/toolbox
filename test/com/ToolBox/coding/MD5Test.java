package com.ToolBox.coding;

import org.junit.Test;

public class MD5Test {
    @Test
    public void converToMD5() throws Exception {
        System.out.println();
        System.out.println(MD5.converToMD5("test"));
    }

    @Test
    public void checkFileMD5() throws Exception {
        System.out.println();
        System.out.println(MD5.checkFileMD5("./testResource/test.txt"));
    }

}