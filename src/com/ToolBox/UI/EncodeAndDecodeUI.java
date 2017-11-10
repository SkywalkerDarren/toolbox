package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * ������������棬����Base64���ܽ��ܣ�Unicode������룬MD5��ϢժҪ���ܡ�
 * �ı����֧�ָ��� ճ�� ȫѡ ���еĳ��ù��ܡ�
 * MD5֧�ֶ��ļ�У�飬�Լ�������ϢУ��
 *
 * @author ��룬�������������
 */
class EncodeAndDecodeUI extends JPanel {
    private static final long serialVersionUID = 8137943111751980202L;

    /**
     * ��ʼ��������빦��
     * ���
     * base64��/����, Unicode���Ļ�ת, MD5У��
     */
    EncodeAndDecodeUI() {

        setOpaque(false);

        // �����ǩҳ
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 800, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // Base64����/���ܱ�ǩҳ
        JPanel panelBase64 = new Base64UI();
        panelBase64.setOpaque(false);
        panelBase64.setLayout(null);
        tabbedPane.addTab("Base64����/����", null, panelBase64, null);

        // Unicode���Ļ�ת
        JPanel panelUnicode = new UnicodeUI();
        panelUnicode.setLayout(null);
        panelBase64.setOpaque(false);
        tabbedPane.addTab("Unicode���Ļ�ת", null, panelUnicode, null);

        // MD5У���ǩҳ
        JPanel panelMD5 = new MD5UI();
        tabbedPane.addTab("MD5���ּ���/�ļ�У��", null, panelMD5, null);
        panelMD5.setOpaque(false);
        panelMD5.setLayout(null);


    }

}
