package com.ToolBox.coding;

import org.junit.Test;

import java.io.File;

public class TextConvertTest {
    @Test
    public void convertRoot() throws Exception {
        TextConvert.convertRoot(new File("./testResource/test"), TextConvert.UTF_8, TextConvert.GBK);
        TextConvert.convertRoot(new File("./testResource/test"), TextConvert.GBK, TextConvert.UTF_8);
        TextConvert.convertRoot(new File("./testResource/test.txt"), TextConvert.GBK, TextConvert.UTF_8);
    }

    @Test
    public void convert() throws Exception {
        TextConvert.convert("test", TextConvert.UTF_8, TextConvert.GBK);
        TextConvert.convert("test", TextConvert.UTF_8, TextConvert.GB2312);
    }

}