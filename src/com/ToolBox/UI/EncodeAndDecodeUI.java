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
class EncodeAndDecodeUI extends TransparentPanelUI {
    private static final long serialVersionUID = 8137943111751980202L;
    private static final String base64 = "Base64����/����";
    private static final String unicode = "Unicode���Ļ�ת";
    private static final String md5 = "MD5���ּ���/�ļ�У��";
    private static final String code = "�ļ�/���ֱ�������ת��";
    private JTabbedPane tabbedPane;
    private JPanel panelBase64;
    private JPanel panelUnicode;
    private JPanel panelMD5;
    private JPanel panelLiteral;

    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panelBase64 = new Base64UI();
        panelUnicode = new UnicodeUI();
        panelMD5 = new MD5UI();
        panelLiteral = new LiteralCodeUI();
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {
        // �����ǩҳ
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 800, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // Base64����/���ܱ�ǩҳ
        panelBase64.setOpaque(false);
        panelBase64.setLayout(null);
        tabbedPane.addTab(base64, null, panelBase64, null);

        // Unicode���Ļ�ת
        panelUnicode.setLayout(null);
        panelBase64.setOpaque(false);
        tabbedPane.addTab(unicode, null, panelUnicode, null);

        // MD5У���ǩҳ
        tabbedPane.addTab(md5, null, panelMD5, null);
        panelMD5.setOpaque(false);
        panelMD5.setLayout(null);

        // ���ֱ�������ת����ǩҳ
        panelLiteral.setOpaque(false);
        panelLiteral.setLayout(null);
        tabbedPane.addTab(code, null, panelLiteral, null);
    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {

    }

    /**
     * ��ʼ��������빦��
     * ���
     * base64��/����, Unicode���Ļ�ת, MD5У��
     */
    EncodeAndDecodeUI() {
        super();
    }

}
