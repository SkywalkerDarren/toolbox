package com.ToolBox.qrcode;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class QrcodeTest {

    private BufferedImage image;

    @BeforeClass
    public static void init() throws Exception {
        Qrcode.generateImage("test");
    }

    @Test
    public void decodeQr() throws Exception {
        System.out.println(Qrcode.decodeQr(image));
        image = ImageIO.read(new File("./testResource/qrcode 2017-11-26.jpg"));
        System.out.println(Qrcode.decodeQr(image));
        image = ImageIO.read(new File("./testResource/test.png"));
        System.out.println(Qrcode.decodeQr(image));

    }

    @Test
    public void setCorrection() throws Exception {
        Qrcode.setCorrection(ErrorCorrectionLevel.H);
    }

    @Test
    public void setSize() throws Exception {
        Qrcode.setSize(Qrcode.MEDIUM);
    }

    @Before
    @Test
    public void generateFile() throws Exception {
        Qrcode.generateFile("测试", new File("./testResource").toPath());
    }

    @Before
    @Test
    public void generateImage() throws Exception {
        image = Qrcode.generateImage("test");
    }

}