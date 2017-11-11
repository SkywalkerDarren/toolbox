package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * ������ӭ����
 *
 * @author ���
 */
public class Splash extends JWindow implements Runnable {

    private static final long serialVersionUID = -6853365720969173394L;
    private JProgressBar progress;
    private String waiting = "������ڱ�д��̨������ ";

    /**
     * �������沼��
     */
    public Splash() {
        Container container = getContentPane();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        progress = new JProgressBar(1, 100);
        progress.setStringPainted(true);
        progress.setFont(new Font("΢���ź�", Font.BOLD, 13));
        progress.setBackground(Color.white);
        container.add(new JLabel(new ImageIcon(new FileResource().splashURL)));
        container.add(progress, BorderLayout.SOUTH);
        Dimension screenSize = getToolkit().getScreenSize();
        pack();
        setLocation((screenSize.width - getWidth()) / 2,
                (screenSize.height - getHeight()) / 2);
    }

    /**
     * ��ʼ�̲߳��ö�
     */
    public void start() {
        toFront();
        Thread splashThread = new Thread(this);
        splashThread.start();
    }

    @Override
    public void run() {
        setVisible(true);
        JFrame ui = new UserInterFace();
        try {
            for (int i = 0; i < 100; i++) {
                progress.setString(waiting + i + "%");
                Thread.sleep(5);
                progress.setValue(i);
                switch (i) {
                    case 40:
                        waiting = "���������ڱ�дǰ�ˡ����� ";
                        break;
                    case 70:
                        waiting = "��������ڻ����ı����زġ����� ";
                        break;
                    case 99:
                        waiting = "���ס�ˡ����� ";
                        progress.setString(waiting + i + "%");
                        Thread.sleep(800);
                        break;
                    default:
                        break;
                }
            }
            waiting = "�ǲ����ܵ� ";
            progress.setString(waiting + "100%");
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
        ui.setVisible(true);
    }
}
