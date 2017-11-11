package com.ToolBox.UI;

import com.ToolBox.coding.Unicode;

import javax.swing.*;
import java.awt.*;

/**
 * �������Unicode��֧������Unicode����ת��
 *
 * @author ��룬�������������
 */
class UnicodeUI extends TransparentPanelUI {

    private static final long serialVersionUID = 7188547339103719919L;
    private static final String leftTip = "���Ľ��";
    private static final String rightTip = "Unicode���";
    private static final String encode = " ���� ת Unicode > ";
    private static final String decode = " < Unicode ת ���� ";
    private TextBox textAreaLeft;
    private TextBox textAreaRight;
    private JScrollPane jspTextLeft;
    private JScrollPane jspTextRight;
    private JButton buttonUnicodeToCHN;
    private JButton buttonCHNToUnicode;

    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        textAreaLeft = new TextBox(leftTip);
        textAreaRight = new TextBox(rightTip);
        jspTextLeft = new JScrollPane();
        jspTextRight = new JScrollPane();
        buttonUnicodeToCHN = new JButton(encode);
        buttonCHNToUnicode = new JButton(decode);
    }

    /**
     * ��ʼ������
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
     * ���������¼�
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
     * ����Unicode���Ļ�ת���
     */
    UnicodeUI() {
        super();
    }
}
