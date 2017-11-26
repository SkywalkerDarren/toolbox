package com.ToolBox.coding;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnicodeTest {

    @Test
    public void stringToUnicode() throws Exception {
        assertEquals(Unicode.stringToUnicode("test"), "\\u0074\\u0065\\u0073\\u0074");
    }

    @Test
    public void unicodeToString() throws Exception {
        assertEquals(Unicode.unicodeToString("\\u0074\\u0065\\u0073\\u0074"), "test");
    }
}