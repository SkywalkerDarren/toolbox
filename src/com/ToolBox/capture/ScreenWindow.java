package com.ToolBox.capture;

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

    // 将截图的图像加载到的目标组件，用于传值
    private int x, y, xEnd, yEnd; // 用于记录鼠标点击开始和结束的坐标

    public ScreenWindow() throws AWTException, InterruptedException {

        // 取得屏幕尺寸
        Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
        // 取得全屏幕截图
        imageFullScreen = GraphicsUtils.getScreenImage(0, 0, screenDims.width, screenDims.height);

        // 用于全屏展示截图
        imageLabel = new JLabel(new ImageIcon(imageFullScreen));
        // 当鼠标在imageLabel上时，展示为 十字形
        imageLabel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        imageLabel.addMouseListener(this);
        imageLabel.addMouseMotionListener(this);

        this.getContentPane().add(imageLabel);

        this.setUndecorated(true); // 去掉窗口装饰
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 窗口最大化
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // 鼠标右键单击事件
            // 退出ScreenWindow
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

        // 鼠标弹起时，取得鼠标起始两点组成的矩形区域的图像
        try {
            // 因为 xEnd 可能比 x 小 （由右网左移动）起始坐标取其中较小值，xEnd - x 取其绝对值， 同样处理y
            BufferedImage image = GraphicsUtils.getScreenImage(Math.min(x, xEnd), Math.min(y, yEnd), Math.abs(xEnd - x),
                    Math.abs(yEnd - y));
            // 用Intent传值
            Intent.setObj(serialVersionUID, imageIntent, image);
        } catch (AWTException | InterruptedException e1) {
            e1.printStackTrace();
        }

        // 退出该ScreenWindow
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
        // 1. 取得鼠标的按下点和移动当前点坐标
        xEnd = e.getX();
        yEnd = e.getY();

        // 2. 创建一个缓冲图形对象（BufferedImage） bi
        BufferedImage bi = new BufferedImage(imageFullScreen.getWidth(), imageFullScreen.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();
        // 3. 将原始图形画到 bi 中
        g2d.drawImage(imageFullScreen, 0, 0, null);
        g2d.setColor(Color.RED); // 设置画笔颜色为红色
        // 4. 根据取得的坐标画一个矩形到 bi 中
        // 以鼠标按下点坐标和鼠标拖动的当前点坐标画矩形，作为截图区域的展示
        // +1 与 -1 是为了防止截图时将矩形框也截进去
        g2d.drawRect(Math.min(x, xEnd) - 1, Math.min(y, yEnd) - 1, Math.abs(xEnd - x) + 1,
                Math.abs(yEnd - y) + 1);
        g2d.dispose();

        // 5. 将 bi 画到屏幕上
        Graphics g = imageLabel.getGraphics();
        g.drawImage(bi, 0, 0, null);
        g.dispose();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

