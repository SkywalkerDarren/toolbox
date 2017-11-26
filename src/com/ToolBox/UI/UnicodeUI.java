package com.ToolBox.UI;

import com.ToolBox.coding.Unicode;

import javax.swing.*;
import java.awt.*;

/**
 * 编码解码Unicode，支持中文Unicode互相转换
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class UnicodeUI extends TransparentPanelUI {

    private static final long serialVersionUID = 7188547339103719919L;
    private static final String leftTip = "中文结果";
    private static final String rightTip = "Unicode结果";
    private static final String encode = " 中文 转 Unicode > ";
    private static final String decode = " < Unicode 转 中文 ";
    private TextBox textAreaLeft;
    private TextBox textAreaRight;
    private JScrollPane jspTextLeft;
    private JScrollPane jspTextRight;
    private JButton buttonUnicodeToCHN;
    private JButton buttonCHNToUnicode;

    /**
     * 初始化组件
     */
    @Override
    protected void initComponent() {
        textAreaLeft = new TextBox(leftTip);
        textAreaRight = new TextBox(rightTip);
        jspTextLeft = new JScrollPane();
        jspTextRight = new JScrollPane();
        buttonUnicodeToCHN = new JButton(encode);
        buttonCHNToUnicode = new JButton(decode);
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        Color normal = new Color(240, 255, 255);

        jspTextLeft.setBounds(35, 60, 250, 350);
        jspTextLeft.setViewportView(textAreaLeft);
        jspTextLeft.add(textAreaLeft);
        jspTextLeft.setViewportView(textAreaLeft);
        add(jspTextLeft);


        jspTextRight.setBounds(510, 60, 250, 350);
        jspTextRight.setViewportView(textAreaRight);
        jspTextRight.add(textAreaRight);
        jspTextRight.setViewportView(textAreaRight);
        add(jspTextRight);

        buttonUnicodeToCHN.setVisible(true);
        buttonUnicodeToCHN.setBackground(normal);
        buttonUnicodeToCHN.setBounds(328, 150, 140, 30);
        add(buttonUnicodeToCHN);

        buttonCHNToUnicode.setVisible(true);
        buttonCHNToUnicode.setBackground(normal);
        buttonCHNToUnicode.setBounds(328, 225, 140, 30);
        add(buttonCHNToUnicode);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        buttonUnicodeToCHN.addActionListener(e -> {
            String src = textAreaLeft.getText();
            String tar = Unicode.stringToUnicode(src);
            textAreaRight.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });

        buttonCHNToUnicode.addActionListener(e -> {
            String src = textAreaRight.getText();
            String tar = Unicode.unicodeToString(src);
            textAreaLeft.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
    }

    /**
     * 构造Unicode中文互转框架
     */
    UnicodeUI() {
        super();
    }
}