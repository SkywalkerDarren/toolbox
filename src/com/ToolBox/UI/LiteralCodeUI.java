package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

class LiteralCodeUI extends JPanel {

    private static final long serialVersionUID = 4233801683473119453L;
    private final static String exchangeTip = "ת��";
    private final static String from = "��";
    private final static String to = "ת����";
    private final static String code = "����";
    private final static String route = "�ļ�·��";
    private final static String chooseFile = "ѡ���ļ�";
    private final static String input = "�������ı�����";
    private final static String output = "ת�����ı�����";
    private final static String exchange = "  ת  ��  > ";
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
         * �������ֱ���ת�����
         */

        final Color normal = new Color(245, 255, 255);
        final Color button = new Color(240, 255, 250);
        final Font fontPlain = new Font("΢���ź�", Font.PLAIN, 14);

        //ѡ���ļ���ť
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

        //���Ϸ�ת����ť
        btnExchangeFile = new JButton();
        btnExchangeFile.setOpaque(false);
        btnExchangeFile.setText(exchangeTip);
        btnExchangeFile.setFont(fontPlain);
        btnExchangeFile.setBounds(596, 50, 105, 35);
        btnExchangeFile.setBackground(button);
        super.add(btnExchangeFile);

        //�ļ�·���ı���
        textFieldRoute = new JTextField();
        textFieldRoute.setBackground(normal);
        textFieldRoute.setEditable(false);
        textFieldRoute.setText(route);
        textFieldRoute.setBounds(213, 50, 375, 35);
        textFieldRoute.setFont(fontPlain);
        textFieldRoute.setForeground(Color.GRAY);
        super.add(textFieldRoute);

        //��
        fromComboBox = new JComboBox<>();
        fromComboBox.setOpaque(false);
        fromComboBox.setFont(fontPlain);
        fromComboBox.setBounds(92, 108, 205, 35);
        fromComboBox.setBackground(normal);
        fromComboBox.addActionListener(e -> {
            // TODO Auto-generated method stub

        });
        super.add(fromComboBox);

        //��
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

        //ת����ť
        btnExchange = new JButton();
        btnExchange.setOpaque(false);
        btnExchange.setText(exchange);
        btnExchange.setFont(fontPlain);
        btnExchange.setBounds(342, 305, 105, 35);
        btnExchange.setBackground(button);
        super.add(btnExchange);
        btnExchange.addActionListener(e -> {//����ת����ťʵ�ֱ���ת������������Ϊ��ɫ
            // TODO Auto-generated method stub
            rightTextArea.setForeground(Color.BLACK);
        });

        //��ǩ��
        lblFrom = new JLabel();
        lblFrom.setText(from);
        lblFrom.setFont(fontPlain);
        lblFrom.setBounds(70, 116, 20, 20);
        super.add(lblFrom);

        //��ǩת����
        lblTo = new JLabel();
        lblTo.setText(to);
        lblTo.setFont(fontPlain);
        lblTo.setBounds(440, 116, 60, 20);
        super.add(lblTo);

        //�����ǩ
        lblLeftCode = new JLabel();
        lblLeftCode.setText(code);
        lblLeftCode.setFont(fontPlain);
        lblLeftCode.setBounds(302, 114, 60, 20);
        super.add(lblLeftCode);

        //�����ǩ
        lblRightCode = new JLabel();
        lblRightCode.setText(code);
        lblRightCode.setFont(fontPlain);
        lblRightCode.setBounds(702, 114, 60, 20);
        super.add(lblRightCode);

    }

}
