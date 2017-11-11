package com.ToolBox.UI;

import com.ToolBox.coding.MD5;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * MD5校验，支持文件校验，信息校验
 * 以大写32位的形式返回结果
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class MD5UI extends TransparentPanelUI {

    private static final long serialVersionUID = -4036614769898898847L;
    private final static String upTip = "请把需要加密/校验的内容粘贴在这里";
    private final static String middleTip = "请选择路径浏览文件";
    private final static String downTip = "加密/校验后的结果";
    private final static String encode = " MD5 加密 ";
    private final static String browser = " 浏览文件 ";
    private final static String check = " MD5 校验 ";
    private JTextArea textAreaUp;
    private TextBox textFieldMiddle;
    private TextBox textFieldDown;
    private JScrollPane scroll;
    private JButton buttonUnicodeToCHN;
    private JButton buttonRoute;
    private JButton buttonCHNToUnicode;


    /**
     * 初始化组件
     */
    @Override
    protected void initCompoment() {
        textAreaUp = new TextBox(upTip);
        textFieldMiddle = new TextBox(middleTip);
        textFieldDown = new TextBox(downTip);
        scroll = new JScrollPane(textAreaUp);
        buttonUnicodeToCHN = new JButton(encode);
        buttonRoute = new JButton(browser);
        buttonCHNToUnicode = new JButton(check);
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        Color normal = new Color(245, 255, 255);

        // 上方文本框
        scroll.setViewportView(textAreaUp);
        scroll.setBounds(70, 75, 450, 110);
        add(scroll);

        // 中间文本框
        textFieldMiddle.setBounds(70, 255, 450, 45);
        add(textFieldMiddle);

        // 下方文本框
        textFieldDown.setBounds(70, 375, 450, 45);
        textFieldDown.setEditable(false);
        add(textFieldDown);

        buttonUnicodeToCHN.setVisible(true);
        buttonUnicodeToCHN.setBackground(normal);
        buttonUnicodeToCHN.setBounds(558, 112, 140, 35);
        add(buttonUnicodeToCHN);

        buttonRoute.setVisible(true);
        buttonRoute.setBackground(normal);
        buttonRoute.setBounds(558, 262, 140, 35);
        add(buttonRoute);

        buttonCHNToUnicode.setVisible(true);
        buttonCHNToUnicode.setBackground(normal);
        buttonCHNToUnicode.setBounds(558, 382, 140, 35);
        add(buttonCHNToUnicode);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        buttonUnicodeToCHN.addActionListener(e -> {
            String src = textAreaUp.getText();
            textFieldDown.setForeground(Color.BLACK);
            try {
                String tar = MD5.converToMD5(src);
                textFieldDown.setText(tar);
            } catch (Exception e1) {
                textFieldDown.setText("转换失败");
                e1.printStackTrace();
            }
        });

        buttonRoute.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int state = fileChooser.showOpenDialog(null);
            switch (state) {
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    textFieldMiddle.setForeground(Color.BLACK);
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    textFieldMiddle.setText(path);
                    break;
            }
        });

        buttonCHNToUnicode.addActionListener(e -> {
            if (textFieldMiddle.getText().equals(middleTip)) {
                return;
            }

            textFieldDown.setForeground(Color.BLACK);
            String path = textFieldMiddle.getText();
            try {
                String tar = MD5.checkFileMD5(path);
                textFieldDown.setText(tar);
            } catch (IOException e1) {
                textFieldDown.setText("没有这个文件");
                e1.printStackTrace();
            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * 构造MD5框架
     */
    MD5UI() {
        super();
    }
}