package com.ToolBox.UI;

import com.ToolBox.coding.Unicode;

import javax.swing.*;
import java.awt.*;

/**
 * 编码解码Unicode，支持中文Unicode互相转换
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class UnicodeUI extends JPanel {

    private static final long serialVersionUID = 7188547339103719919L;
    private static final String leftTip = "中文结果";
    private static final String rightTip = "Unicode结果";
    private final TextBox textAreaLeft = new TextBox(leftTip);
    private final TextBox textAreaRight = new TextBox(rightTip);

    /**
     * 构造Unicode中文互转框架
     */
    UnicodeUI() {
        setOpaque(false);
        Color normal = new Color(240, 255, 255);

        JScrollPane jspTextLeft = new JScrollPane();
        jspTextLeft.setBounds(35, 60, 250, 350);
        jspTextLeft.setViewportView(textAreaLeft);
        jspTextLeft.add(textAreaLeft);
        jspTextLeft.setViewportView(textAreaLeft);
        add(jspTextLeft);

        JScrollPane jspTextRight = new JScrollPane();
        jspTextRight.setBounds(510, 60, 250, 350);
        jspTextRight.setViewportView(textAreaRight);
        jspTextRight.add(textAreaRight);
        jspTextRight.setViewportView(textAreaRight);
        add(jspTextRight);

        JButton buttonUnicodeToCHN = new JButton(" 中文 转 Unicode > ");
        buttonUnicodeToCHN.setVisible(true);
        buttonUnicodeToCHN.setBackground(normal);
        buttonUnicodeToCHN.setBounds(328, 150, 140, 30);
        buttonUnicodeToCHN.addActionListener(e -> {
            String src = textAreaLeft.getText();
            String tar = Unicode.stringToUnicode(src);
            textAreaRight.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
        add(buttonUnicodeToCHN);

        JButton buttonCHNToUnicode = new JButton(" < Unicode 转 中文 ");
        buttonCHNToUnicode.setVisible(true);
        buttonCHNToUnicode.setBackground(normal);
        buttonCHNToUnicode.setBounds(328, 225, 140, 30);
        buttonCHNToUnicode.addActionListener(e -> {
            String src = textAreaRight.getText();
            String tar = Unicode.unicodeToString(src);
            textAreaLeft.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
        add(buttonCHNToUnicode);
    }
}
