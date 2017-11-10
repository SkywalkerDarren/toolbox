package com.ToolBox.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.joda.time.DateTime;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * 二维码核心代码
 * 二维码生成与解码功能
 *
 * @author 杨弘
 */
public class Qrcode {

    public static final int LARGE = 350;
    public static final int MEDIUM = 250;
    public static final int SMALL = 150;

    private static BufferedImage image;
    private static int width = MEDIUM;
    private static int height = MEDIUM;
    private static HashMap<EncodeHintType, Object> hints = new HashMap<>();

    //默认使用utf-8编码
    static {
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    }

    /**
     * 二维码解码
     *
     * @param bufferedImage 二维码图片
     * @return 解码结果，错误则返回识别错误
     */
    public static String decodeQr(BufferedImage bufferedImage) {
        String retStr;
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
        hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        try {
            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
            retStr = result.getText();
        } catch (Exception e) {
            retStr = "识别错误";
        }
        return retStr;
    }

    /**
     * 纠错等级设置
     *
     * @param level 纠错等级 在ErrorCorrectionLevel类中
     */
    public static void setCorrection(ErrorCorrectionLevel level) {
        hints.put(EncodeHintType.ERROR_CORRECTION, level);
    }

    public static void setSize(int size) {
        Qrcode.width = size;
        Qrcode.height = size;
    }

    /**
     * 保存二维码文件
     *
     * @param content 二维码原始信息
     * @param path    保存路径
     */
    public static void generateFile(String content, Path path) throws IOException {
        generateImage(content);
        String FORMAT = "jpg";
        File qrCodeFile = new File(path.toString() + "\\qrcode" + DateTime.now().toString(" yyyy-MM-dd") + "." + FORMAT);
        //将二维码图片作为文件输出
        ImageIO.write(image, FORMAT, qrCodeFile);
    }

    /**
     * 生成二维码图片
     *
     * @param content 二维码原始信息
     * @return 编码后图片结果
     */
    public static BufferedImage generateImage(String content) {
        BitMatrix bitMatrix = getMatrix(content);
        image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }

    private static BitMatrix getMatrix(String content) {

        try {
            return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
