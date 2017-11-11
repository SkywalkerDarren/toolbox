package com.ToolBox.capture;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 截图工具
 *
 * @author 杨弘
 */
public class GraphicsUtils {
    /**
     * 截图屏幕中制定区域的图片
     *
     * @param x 左上角横坐标
     * @param y 左上角纵坐标
     * @param w 宽度
     * @param h 高度
     * @return 被截部分的BufferedImage对象
     * @throws Exception 截图异常
     */
    static BufferedImage getScreenImage(int x, int y, int w, int h) throws Exception {
        Robot robot = new Robot();
        return robot.createScreenCapture(new Rectangle(x, y, w, h));
    }

    /**
     * 将指定图片写入系统剪贴板
     *
     * @param image 图片
     */
    public static void setClipboardImage(final Image image) {
        Transferable trans = new Transferable() {
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.imageFlavor};
            }

            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                if (isDataFlavorSupported(flavor))
                    return image;
                throw new UnsupportedFlavorException(flavor);
            }

        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
    }
}