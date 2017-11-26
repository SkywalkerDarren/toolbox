package com.ToolBox.UI;

import com.ToolBox.coding.Base64;

import javax.swing.*;
import java.awt.*;

/**
 * base64解码的主面板，可以对编码进行base64编码与解码
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class Base64UI extends TransparentPanelUI {

    private static final long serialVersionUID = -4621043278294486022L;
    private final static String leftTips = "输入要转换的字符串";
    private final static String rightTips = "输入要解码的字符串";
    private final static String encoding = " BASE64编码 > ";
    private final static String decoding = " < BASE64解码 ";

    private TextBox textAreaLeft;
    private TextBox textAreaRight;
    private JScrollPane jspTextLeft;
    private JScrollPane jspTextRight;
    private JButton buttonRecode;
    private JButton buttonDecode;

    /**
     * 初始化组件
     */
    @Override
    protected void initComponent() {
        textAreaLeft = new TextBox(leftTips);
        textAreaRight = new TextBox(rightTips);
        jspTextLeft = new JScrollPane();
        jspTextRight = new JScrollPane();
        buttonRecode = new JButton(encoding);
        buttonDecode = new JButton(decoding);
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        Color normal = new Color(245, 255, 255);

        jspTextLeft.setBounds(35, 60, 250, 350);
        jspTextLeft.add(textAreaLeft);
        jspTextLeft.setViewportView(textAreaLeft);
        add(jspTextLeft);

        jspTextRight.setBounds(510, 60, 250, 350);
        jspTextRight.add(textAreaRight);
        jspTextRight.setViewportView(textAreaRight);
        add(jspTextRight);

        buttonRecode.setVisible(true);
        buttonRecode.setBackground(normal);
        buttonRecode.setBounds(328, 150, 140, 30);
        add(buttonRecode);

        buttonDecode.setVisible(true);
        buttonDecode.setBackground(normal);
        buttonDecode.setBounds(328, 225, 140, 30);
        add(buttonDecode);

    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        buttonRecode.addActionListener(e -> {
            String src = textAreaLeft.getText();
            String tar = Base64.stringToBase64(src);
            textAreaRight.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });

        buttonDecode.addActionListener(e -> {
            String src = textAreaRight.getText();
            String tar = Base64.base64ToString(src);
            textAreaLeft.setText(tar);
            textAreaLeft.setForeground(Color.BLACK);
            textAreaRight.setForeground(Color.BLACK);
        });
    }

    /**
     * 构造编码器框架
     */
    Base64UI() {
        super();
    }
}