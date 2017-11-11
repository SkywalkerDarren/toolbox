package com.ToolBox.UI;

import com.ToolBox.coding.TextConvert;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
    private final static String success = "\tת���ɹ�";
    private final static String txt = "txt";
    private static final String js = "js";
    private static final String log = "log";
    private final static String c = "c";
    private final static String java = "java";
    private final static String cpp = "cpp";
    private final static String xml = "xml";
    private final static String json = "json";
    private final static String yaml = "yaml";
    private final static String h = "h";
    private TextBox leftTextArea;
    private TextBox rightTextArea;
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
    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;
    private String fromCharset;
    private String toCharset;
    private File file;

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
        leftScrollPane = new JScrollPane(leftTextArea);
        rightScrollPane = new JScrollPane(rightTextArea);
    }

    /**
     * ��ʼ������
     */
    protected void initUI() {
        final Color normal = new Color(245, 255, 255);
        final Color button = new Color(240, 255, 250);
        final Font fontPlain = new Font("΢���ź�", Font.PLAIN, 14);
        fromCharset = TextConvert.GBK;
        toCharset = TextConvert.UTF_8;

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
        fromComboBox.addItem(TextConvert.GBK);
        fromComboBox.addItem(TextConvert.GB2312);
        fromComboBox.addItem(TextConvert.BIG5);
        fromComboBox.addItem(TextConvert.JIS);
        fromComboBox.addItem(TextConvert.SJIS);
        fromComboBox.addItem(TextConvert.UTF_8);
        fromComboBox.setSelectedItem(TextConvert.GBK);
        add(fromComboBox);

        //��
        toComboBox.setOpaque(false);
        toComboBox.setFont(fontPlain);
        toComboBox.setBounds(490, 108, 205, 35);
        toComboBox.setBackground(normal);
        toComboBox.addItem(TextConvert.GBK);
        toComboBox.addItem(TextConvert.GB2312);
        toComboBox.addItem(TextConvert.BIG5);
        toComboBox.addItem(TextConvert.JIS);
        toComboBox.addItem(TextConvert.SJIS);
        toComboBox.addItem(TextConvert.UTF_8);
        toComboBox.setSelectedItem(TextConvert.UTF_8);
        add(toComboBox);

        leftScrollPane.setBounds(60, 170, 275, 315);
        leftScrollPane.setViewportView(leftTextArea);
        add(leftScrollPane);

        rightTextArea.setEditable(false);
        rightScrollPane.setBounds(455, 170, 275, 315);
        rightScrollPane.setViewportView(rightTextArea);
        add(rightScrollPane);

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
            textFieldRoute.setForeground(Color.BLACK);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            switch (fileChooser.showOpenDialog(null)) {
                case JFileChooser.APPROVE_OPTION:
                    file = fileChooser.getSelectedFile();
                    textFieldRoute.setText(file.getAbsolutePath());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        });

        fromComboBox.addItemListener(e -> fromCharset = (String) e.getItem());

        toComboBox.addItemListener(e -> toCharset = (String) e.getItem());

        btnExchangeFile.addActionListener(e -> {
            if (file == null) return;
            if (file.isDirectory()) {
                File[] files = file.listFiles((dir, name) -> name.lastIndexOf(".") != -1 && (name.endsWith(txt) ||
                        name.endsWith(c) || name.endsWith(java) || name.endsWith(cpp) || name.endsWith(json) ||
                        name.endsWith(yaml) || name.endsWith(xml) || name.endsWith(h) || name.endsWith(log) ||
                        name.endsWith(js)));
                try {
                    assert files != null;
                    for (File f : files) {
                        TextConvert.convert(f, fromCharset, toCharset);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else if (file.isFile()) {
                try {
                    TextConvert.convert(file, fromCharset, toCharset);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            textFieldRoute.setText(file.getAbsolutePath() + success);
            file = null;
        });

        btnExchange.addActionListener(e -> {
            rightTextArea.setForeground(Color.BLACK);
            try {
                String str = TextConvert.convert(leftTextArea.getText(), fromCharset, toCharset);
                rightTextArea.setText(str);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * �������ֱ���ת�����
     */
    LiteralCodeUI() {
        super();
    }

}
