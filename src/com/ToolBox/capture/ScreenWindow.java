package com.ToolBox.capture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * ȫ����ʾ�Ĵ���, ���Ҽ��˳�
 *
 * @author ���
 */
public class ScreenWindow extends JFrame implements MouseListener, MouseMotionListener {

    public static final long serialVersionUID = -2169218221244307442L;
    public static final String imageIntent = "imageIntent";
    private JLabel imageLabel;
    private BufferedImage imageFullScreen;

    // ����ͼ��ͼ����ص���Ŀ����������ڴ�ֵ
    private int x, y, xEnd, yEnd; // ���ڼ�¼�������ʼ�ͽ���������

    public ScreenWindow() throws AWTException, InterruptedException {

        // ȡ����Ļ�ߴ�
        Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
        // ȡ��ȫ��Ļ��ͼ
        imageFullScreen = GraphicsUtils.getScreenImage(0, 0, screenDims.width, screenDims.height);

        // ����ȫ��չʾ��ͼ
        imageLabel = new JLabel(new ImageIcon(imageFullScreen));
        // �������imageLabel��ʱ��չʾΪ ʮ����
        imageLabel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        imageLabel.addMouseListener(this);
        imageLabel.addMouseMotionListener(this);

        this.getContentPane().add(imageLabel);

        this.setUndecorated(true); // ȥ������װ��
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // �������
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // ����Ҽ������¼�
            // �˳�ScreenWindow
            ScreenWindow.this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        xEnd = e.getX();
        yEnd = e.getY();

        // ��굯��ʱ��ȡ�������ʼ������ɵľ��������ͼ��
        try {
            // ��Ϊ xEnd ���ܱ� x С �����������ƶ�����ʼ����ȡ���н�Сֵ��xEnd - x ȡ�����ֵ�� ͬ������y
            BufferedImage image = GraphicsUtils.getScreenImage(Math.min(x, xEnd), Math.min(y, yEnd), Math.abs(xEnd - x),
                    Math.abs(yEnd - y));
            // ��Intent��ֵ
            Intent.setObj(serialVersionUID, imageIntent, image);
        } catch (AWTException | InterruptedException e1) {
            e1.printStackTrace();
        }

        // �˳���ScreenWindow
        ScreenWindow.this.dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 1. ȡ�����İ��µ���ƶ���ǰ������
        xEnd = e.getX();
        yEnd = e.getY();

        // 2. ����һ������ͼ�ζ���BufferedImage�� bi
        BufferedImage bi = new BufferedImage(imageFullScreen.getWidth(), imageFullScreen.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        // 3. ��ԭʼͼ�λ��� bi ��
        g2d.drawImage(imageFullScreen, 0, 0, null);
        g2d.setColor(Color.RED); // ���û�����ɫΪ��ɫ
        // 4. ����ȡ�õ����껭һ�����ε� bi ��
        // ����갴�µ����������϶��ĵ�ǰ�����껭���Σ���Ϊ��ͼ�����չʾ
        // +1 �� -1 ��Ϊ�˷�ֹ��ͼʱ�����ο�Ҳ�ؽ�ȥ
        g2d.drawRect(Math.min(x, xEnd) - 1, Math.min(y, yEnd) - 1, Math.abs(xEnd - x) + 1,
                Math.abs(yEnd - y) + 1);
        g2d.dispose();

        // 5. �� bi ������Ļ��
        Graphics g = imageLabel.getGraphics();
        g.drawImage(bi, 0, 0, null);
        g.dispose();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

