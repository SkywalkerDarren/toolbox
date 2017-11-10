package com.ToolBox.capture;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    /**
     * ��ָ��ͼƬд��ϵͳ������
     *
     * @param image
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