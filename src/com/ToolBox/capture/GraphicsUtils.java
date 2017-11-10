package com.ToolBox.capture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 截图工具
 *
 * @author 杨弘
 */
class GraphicsUtils {
    /**
     * 截图屏幕中制定区域的图片
     *
     * @param x 左上角横坐标
     * @param y 左上角纵坐标
     * @param w 宽度
     * @param h 高度
     * @return 被截部分的BufferedImage对象
     */
    static BufferedImage getScreenImage(int x, int y, int w, int h) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        return robot.createScreenCapture(new Rectangle(x, y, w, h));
    }
}