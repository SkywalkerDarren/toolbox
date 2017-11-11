package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * ��������ת������
 * ֧�ּ��壬���壬���utf-8����ת������
 * ֧���ļ�����ת��
 * ֧������ת��
 *
 * @author ��룬�������������
 */
class LiteralCodeUI extends TransparentPanelUI {

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
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JButton btnExchange;
    private JButton btnChooseFile;
    private JButton btnExchangeFile;
    private JLabel lblFrom;
    private JLabel lblTo;
    private JLabel lblLeftCode;
    private JLabel lblRightCode;

    /**
     * ��ʼ�����
     */
    protected void initCompoment() {
        btnChooseFile = new JButton();
        btnExchangeFile = new JButton();
        textFieldRoute = new JTextField();
        fromComboBox = new JComboBox<>();
        toComboBox = new JComboBox<>();
        leftTextArea = new TextBox(input);
        rightTextArea = new TextBox(output);
        btnExchange = new JButton();
        lblTo = new JLabel();
        lblFrom = new JLabel();
        lblLeftCode = new JLabel();
        lblRightCode = new JLabel();
    }

    /**
     * ��ʼ������
     */
    protected void initUI() {
        final Color normal = new Color(245, 255, 255);
        final Color button = new Color(240, 255, 250);
        final Font fontPlain = new Font("΢���ź�", Font.PLAIN, 14);

        //ѡ���ļ���ť
        btnChooseFile.setOpaque(false);
        btnChooseFile.setText(chooseFile);
        btnChooseFile.setFont(fontPlain);
        btnChooseFile.setBounds(84, 50, 105, 35);
        btnChooseFile.setBackground(button);
        add(btnChooseFile);

        //���Ϸ�ת����ť
        btnExchangeFile.setOpaque(false);
        btnExchangeFile.setText(exchangeTip);
        btnExchangeFile.setFont(fontPlain);
        btnExchangeFile.setBounds(596, 50, 105, 35);
        btnExchangeFile.setBackground(button);
        add(btnExchangeFile);

        //�ļ�·���ı���
        textFieldRoute.setBackground(normal);
        textFieldRoute.setEditable(false);
        textFieldRoute.setText(route);
        textFieldRoute.setBounds(213, 50, 375, 35);
        textFieldRoute.setFont(fontPlain);
        textFieldRoute.setForeground(Color.GRAY);
        add(textFieldRoute);

        //��
        fromComboBox.setOpaque(false);
        fromComboBox.setFont(fontPlain);
        fromComboBox.setBounds(92, 108, 205, 35);
        fromComboBox.setBackground(normal);
        add(fromComboBox);

        //��
        toComboBox.setOpaque(false);
        toComboBox.setFont(fontPlain);
        toComboBox.setBounds(490, 108, 205, 35);
        toComboBox.setBackground(normal);
        add(toComboBox);

        leftTextArea.setBounds(60, 170, 275, 315);
        add(leftTextArea);

        rightTextArea.setBounds(455, 170, 275, 315);
        rightTextArea.setEditable(false);
        add(rightTextArea);

        //ת����ť
        btnExchange.setOpaque(false);
        btnExchange.setText(exchange);
        btnExchange.setFont(fontPlain);
        btnExchange.setBounds(342, 305, 105, 35);
        btnExchange.setBackground(button);
        add(btnExchange);

        //��ǩ��
        lblFrom.setText(from);
        lblFrom.setFont(fontPlain);
        lblFrom.setBounds(70, 116, 20, 20);
        add(lblFrom);

        //��ǩת����
        lblTo.setText(to);
        lblTo.setFont(fontPlain);
        lblTo.setBounds(440, 116, 60, 20);
        add(lblTo);

        //�����ǩ
        lblLeftCode.setText(code);
        lblLeftCode.setFont(fontPlain);
        lblLeftCode.setBounds(302, 114, 60, 20);
        add(lblLeftCode);

        //�����ǩ
        lblRightCode.setText(code);
        lblRightCode.setFont(fontPlain);
        lblRightCode.setBounds(702, 114, 60, 20);
        add(lblRightCode);
    }

    /**
     * ���������¼�
     */
    protected void createAction() {
        btnChooseFile.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (!(textFieldRoute.getText().equals(route))) {
                textFieldRoute.setForeground(Color.BLACK);
            }
        });

        fromComboBox.addActionListener(e -> {
            // TODO Auto-generated method stub

        });

        toComboBox.addActionListener(e -> {
            // TODO Auto-generated method stub

        });


        btnExchange.addActionListener(e -> {//����ת����ťʵ�ֱ���ת������������Ϊ��ɫ
            // TODO Auto-generated method stub
            rightTextArea.setForeground(Color.BLACK);
        });
    }

    /**
     * �������ֱ���ת�����
     */
    LiteralCodeUI() {
        super();
    }

}
