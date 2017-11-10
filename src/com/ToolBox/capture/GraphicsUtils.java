package com.ToolBox.capture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ��ͼ����
 *
 * @author ���
 */
class GraphicsUtils {
    /**
     * ��ͼ��Ļ���ƶ������ͼƬ
     *
     * @param x ���ϽǺ�����
     * @param y ���Ͻ�������
     * @param w ���
     * @param h �߶�
     * @return ���ز��ֵ�BufferedImage����
     */
    static BufferedImage getScreenImage(int x, int y, int w, int h) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        return robot.createScreenCapture(new Rectangle(x, y, w, h));
    }
}