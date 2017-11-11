package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

class LiteralCodeUI extends JPanel {

    private static final long serialVersionUID = 4233801683473119453L;
    private final static String exchangeTip = "转换";
    private final static String from = "从";
    private final static String to = "转换到";
    private final static String code = "编码";
    private final static String route = "文件路径";
    private final static String chooseFile = "选择文件";
    private final static String input = "请输入文本内容";
    private final static String output = "转换后文本内容";
    private final static String exchange = "  转  换  > ";
    private static TextBox leftTextArea;
    private static TextBox rightTextArea;
    private JTextField textFieldRoute;


    LiteralCodeUI() {
        setOpaque(false);

        JComboBox<String> fromComboBox;
        JComboBox<String> toComboBox;
        JButton btnExchange;
        JButton btnChooseFile;
        JButton btnExchangeFile;
        JLabel lblFrom;
        JLabel lblTo;
        JLabel lblLeftCode;
        JLabel lblRightCode;

        /***
         * 构建文字编码转换框架
         */

        final Color normal = new Color(245, 255, 255);
        final Color button = new Color(240, 255, 250);
        final Font fontPlain = new Font("微软雅黑", Font.PLAIN, 14);

        //选择文件按钮
        btnChooseFile = new JButton();
        btnChooseFile.setOpaque(false);
        btnChooseFile.setText(chooseFile);
        btnChooseFile.setFont(fontPlain);
        btnChooseFile.setBounds(84, 50, 105, 35);
        btnChooseFile.setBackground(button);
        super.add(btnChooseFile);
        btnChooseFile.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (!(textFieldRoute.getText().equals(route))) {
                textFieldRoute.setForeground(Color.BLACK);
            }
        });

        //最上方转换按钮
        btnExchangeFile = new JButton();
        btnExchangeFile.setOpaque(false);
        btnExchangeFile.setText(exchangeTip);
        btnExchangeFile.setFont(fontPlain);
        btnExchangeFile.setBounds(596, 50, 105, 35);
        btnExchangeFile.setBackground(button);
        super.add(btnExchangeFile);

        //文件路径文本框
        textFieldRoute = new JTextField();
        textFieldRoute.setBackground(normal);
        textFieldRoute.setEditable(false);
        textFieldRoute.setText(route);
        textFieldRoute.setBounds(213, 50, 375, 35);
        textFieldRoute.setFont(fontPlain);
        textFieldRoute.setForeground(Color.GRAY);
        super.add(textFieldRoute);

        //左
        fromComboBox = new JComboBox<>();
        fromComboBox.setOpaque(false);
        fromComboBox.setFont(fontPlain);
        fromComboBox.setBounds(92, 108, 205, 35);
        fromComboBox.setBackground(normal);
        fromComboBox.addActionListener(e -> {
            // TODO Auto-generated method stub

        });
        super.add(fromComboBox);

        //右
        toComboBox = new JComboBox<>();
        toComboBox.setOpaque(false);
        toComboBox.setFont(fontPlain);
        toComboBox.setBounds(490, 108, 205, 35);
        toComboBox.setBackground(normal);
        toComboBox.addActionListener(e -> {
            // TODO Auto-generated method stub

        });
        super.add(toComboBox);

        leftTextArea = new TextBox(input);
        leftTextArea.setBounds(60, 170, 275, 315);
        super.add(leftTextArea);

        rightTextArea = new TextBox(output);
        rightTextArea.setBounds(455, 170, 275, 315);
        super.add(rightTextArea);

        //转换按钮
        btnExchange = new JButton();
        btnExchange.setOpaque(false);
        btnExchange.setText(exchange);
        btnExchange.setFont(fontPlain);
        btnExchange.setBounds(342, 305, 105, 35);
        btnExchange.setBackground(button);
        super.add(btnExchange);
        btnExchange.addActionListener(e -> {//单击转换按钮实现编码转换，字体设置为黑色
            // TODO Auto-generated method stub
            rightTextArea.setForeground(Color.BLACK);
        });

        //标签从
        lblFrom = new JLabel();
        lblFrom.setText(from);
        lblFrom.setFont(fontPlain);
        lblFrom.setBounds(70, 116, 20, 20);
        super.add(lblFrom);

        //标签转换到
        lblTo = new JLabel();
        lblTo.setText(to);
        lblTo.setFont(fontPlain);
        lblTo.setBounds(440, 116, 60, 20);
        super.add(lblTo);

        //编码标签
        lblLeftCode = new JLabel();
        lblLeftCode.setText(code);
        lblLeftCode.setFont(fontPlain);
        lblLeftCode.setBounds(302, 114, 60, 20);
        super.add(lblLeftCode);

        //编码标签
        lblRightCode = new JLabel();
        lblRightCode.setText(code);
        lblRightCode.setFont(fontPlain);
        lblRightCode.setBounds(702, 114, 60, 20);
        super.add(lblRightCode);

    }

}
