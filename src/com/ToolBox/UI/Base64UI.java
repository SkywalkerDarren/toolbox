package com.ToolBox.UI;

import com.ToolBox.coding.Base64;

import javax.swing.*;
import java.awt.*;

/**
 * base64���������壬���ԶԱ������base64���������
 *
 * @author ��룬�������������
 */
class Base64UI extends JPanel {

    private static final long serialVersionUID = -4621043278294486022L;
    private final static String leftTips = "����Ҫת�����ַ���";
    private final static String rightTips = "����Ҫ������ַ���";
    private final TextBox textAreaLeft = new TextBox(leftTips);
    private final TextBox textAreaRight = new TextBox(rightTips);

    /**
     * ������������
     */
    Base64UI() {
        setOpaque(false);

        Color normal = new Color(245, 255, 255);

        JScrollPane jspTextLeft = new JScrollPane();
        jspTextLeft.setBounds(35, 60, 250, 350);
        jspTextLeft.add(textAreaLeft);
        jspTextLeft.setViewportView(textAreaLeft);
        add(jspTextLeft);

        JScrollPane jspTextRight = new JScrollPane();
        jspTextRight.setBounds(510, 60, 250, 350);
        jspTextRight.add(textAreaRight);
        jspTextRight.setViewportView(textAreaRight);
        add(jspTextRight);

        JButton buttonRecode = new JButton(" BASE64���� > ");
        buttonRecode.setVisible(true);
        buttonRecode.setBackground(normal);
        buttonRecode.setBounds(328, 150, 140, 30);
        buttonRecode.addActionListener(e -> {
            String src = textAreaLeft.getText();
            String tar = Base64.stringToBase64(src);
            textAreaRight.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
        add(buttonRecode);

        JButton buttonDecode = new JButton(" < BASE64���� ");
        buttonDecode.setVisible(true);
        buttonDecode.setBackground(normal);
        buttonDecode.setBounds(328, 225, 140, 30);
        buttonDecode.addActionListener(e -> {
            String src = textAreaRight.getText();
            String tar = Base64.base64ToString(src);
            textAreaLeft.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
        add(buttonDecode);
    }
}
