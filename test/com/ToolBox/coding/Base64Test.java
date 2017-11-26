package com.ToolBox.coding;

import org.junit.Test;

public class Base64Test {
    @Test
    public void stringToBase64() throws Exception {
        System.out.println(Base64.stringToBase64("test"));
    }

    @Test
    public void base64ToString() throws Exception {
        System.out.println(Base64.base64ToString("dGVzdA=="));
    }

}