package com.ToolBox.capture;

import com.ToolBox.history.Intent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * 全屏显示的窗口, 按右键退出
 *
 * @author 杨弘
 */
public class ScreenWindow extends JFrame implements MouseListener, MouseMotionListener {

    public static final long serialVersionUID = -2169218221244307442L;
    public static final String imageIntent = "imageIntent";
    private JLabel imageLabel;
    private BufferedImage imageFullScreen;

    private int x, y, xEnd, yEnd;

    /**
     * 截图界面
     *
     * @throws AWTException         ui异常
     * @throws InterruptedException 中断异常
     */
    public ScreenWindow() throws AWTException, InterruptedException {

        Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
        imageFullScreen = GraphicsUtils.getScreenImage(0, 0, screenDims.width, screenDims.height);

        imageLabel = new JLabel(new ImageIcon(imageFullScreen));
        imageLabel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        imageLabel.addMouseListener(this);
        imageLabel.addMouseMotionListener(this);

        this.getContentPane().add(imageLabel);

        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
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

        try {
            BufferedImage image = GraphicsUtils.getScreenImage(Math.min(x, xEnd), Math.min(y, yEnd), Math.abs(xEnd - x),
                    Math.abs(yEnd - y));
            Intent.setObj(serialVersionUID, imageIntent, image);
        } catch (AWTException | InterruptedException e1) {
            e1.printStackTrace();
        }
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
        xEnd = e.getX();
        yEnd = e.getY();

        BufferedImage bi = new BufferedImage(imageFullScreen.getWidth(), imageFullScreen.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        g2d.drawImage(imageFullScreen, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(Math.min(x, xEnd) - 1, Math.min(y, yEnd) - 1, Math.abs(xEnd - x) + 1,
                Math.abs(yEnd - y) + 1);
        g2d.dispose();

        Graphics g = imageLabel.getGraphics();
        g.drawImage(bi, 0, 0, null);
        g.dispose();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}