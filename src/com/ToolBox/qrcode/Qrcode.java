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
 * ��ά����Ĵ���
 * ��ά����������빦��
 *
 * @author ���
 */
public class Qrcode {

    public static final int LARGE = 350;
    public static final int MEDIUM = 250;
    public static final int SMALL = 150;

    private static BufferedImage image;
    private static int width = MEDIUM;
    private static int height = MEDIUM;
    private static HashMap<EncodeHintType, Object> hints = new HashMap<>();

    //Ĭ��ʹ��utf-8����
    static {
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    }

    /**
     * ��ά�����
     *
     * @param bufferedImage ��ά��ͼƬ
     * @return �������������򷵻�ʶ�����
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
            retStr = "ʶ�����";
        }
        return retStr;
    }

    /**
     * ����ȼ�����
     *
     * @param level ����ȼ� ��ErrorCorrectionLevel����
     */
    public static void setCorrection(ErrorCorrectionLevel level) {
        hints.put(EncodeHintType.ERROR_CORRECTION, level);
    }

    public static void setSize(int size) {
        Qrcode.width = size;
        Qrcode.height = size;
    }

    /**
     * �����ά���ļ�
     *
     * @param content ��ά��ԭʼ��Ϣ
     * @param path    ����·��
     */
    public static void generateFile(String content, Path path) throws IOException {
        generateImage(content);
        String FORMAT = "jpg";
        File qrCodeFile = new File(path.toString() + "\\qrcode" + DateTime.now().toString(" yyyy-MM-dd") + "." + FORMAT);
        //����ά��ͼƬ��Ϊ�ļ����
        ImageIO.write(image, FORMAT, qrCodeFile);
    }

    /**
     * ���ɶ�ά��ͼƬ
     *
     * @param content ��ά��ԭʼ��Ϣ
     * @return �����ͼƬ���
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
