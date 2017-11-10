package com.ToolBox.UI;

import com.ToolBox.coding.MD5;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * MD5У�飬֧���ļ�У�飬��ϢУ��
 * �Դ�д32λ����ʽ���ؽ��
 *
 * @author ��룬�������������
 */
class MD5UI extends JPanel {

    private static final long serialVersionUID = -4036614769898898847L;
    private final static String upTip = "�����Ҫ����/У�������ճ��������";
    private final static String middleTip = "��ѡ��·������ļ�";
    private final static String downTip = "����/У���Ľ��";
    private final JTextArea textAreaUp = new TextBox(upTip);
    private final TextBox textFieldMiddle = new TextBox(middleTip);
    private final TextBox textFieldDown = new TextBox(downTip);

    /**
     * ����MD5���
     */
    MD5UI() {

        // �Ϸ��ı���
        Color normal = new Color(245, 255, 255);

        add(textAreaUp);

        JScrollPane scroll = new JScrollPane(textAreaUp);
        add(scroll, BorderLayout.CENTER);
        scroll.setViewportView(textAreaUp);
        scroll.setBounds(70, 75, 450, 110);
        add(scroll);

        // �м��ı���
        textFieldMiddle.setBounds(70, 255, 450, 45);

        add(textFieldMiddle);

        // �·��ı���
        textFieldDown.setBounds(70, 375, 450, 45);
        textFieldDown.setEditable(false);

        add(textFieldDown);

        JButton buttonUnicodeToCHN = new JButton(" MD5 ���� ");
        buttonUnicodeToCHN.setVisible(true);
        buttonUnicodeToCHN.setBackground(normal);
        buttonUnicodeToCHN.setBounds(558, 112, 140, 35);
        buttonUnicodeToCHN.addActionListener(e -> {
            String src = textAreaUp.getText();
            textFieldDown.setForeground(Color.BLACK);
            try {
                String tar = MD5.converToMD5(src);
                textFieldDown.setText(tar);
            } catch (Exception e1) {
                textFieldDown.setText("ת��ʧ��");
                e1.printStackTrace();
            }
        });
        add(buttonUnicodeToCHN);


        JButton buttonRoute = new JButton(" ����ļ� ");
        buttonRoute.setVisible(true);
        buttonRoute.setBackground(normal);
        buttonRoute.setBounds(558, 262, 140, 35);
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
        add(buttonRoute);

        JButton buttonCHNToUnicode = new JButton(" MD5 У�� ");
        buttonCHNToUnicode.setVisible(true);
        buttonCHNToUnicode.setBackground(normal);
        buttonCHNToUnicode.setBounds(558, 382, 140, 35);
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
                textFieldDown.setText("û������ļ�");
                e1.printStackTrace();
            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();
            }
        });
        add(buttonCHNToUnicode);

    }
}
