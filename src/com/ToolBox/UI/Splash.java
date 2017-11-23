package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * 启动欢迎界面
 *
 * @author 杨弘
 */
public class Splash extends JWindow implements Runnable {

    private static final long serialVersionUID = -6853365720969173394L;
    private JProgressBar progress;
    private String waiting = "杨弘正在编写后台。。。 ";

    /**
     * 启动画面布局
     */
    public Splash() {
        Container container = getContentPane();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        progress = new JProgressBar(1, 100);
        progress.setStringPainted(true);
        progress.setFont(new Font("微软雅黑", Font.BOLD, 13));
        progress.setBackground(Color.white);
        container.add(new JLabel(new ImageIcon(new FileResource().splashURL)));
        container.add(progress, BorderLayout.SOUTH);
        Dimension screenSize = getToolkit().getScreenSize();
        pack();
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
    }

    /**
     * 开始线程并置顶
     */
    public void start() {
        toFront();
        Thread splashThread = new Thread(this);
        splashThread.start();
    }

    @Override
    public void run() {
        setVisible(true);
        progress.setString(waiting + 0 + "%");
        JFrame ui = new UserInterFace();
        try {
            for (int i = 0; i < 100; i++) {
                progress.setString(waiting + i + "%");
                Thread.sleep(5);
                progress.setValue(i);
                switch (i) {
                    case 40:
                        waiting = "徐祥亮正在编写前端。。。 ";
                        break;
                    case 70:
                        waiting = "朱可欣正在画最后的背景素材。。。 ";
                        break;
                    default:
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
        ui.setVisible(true);
    }
}