package com.ToolBox.UI;

import com.ToolBox.coding.TextConvert;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 批量文字转换工具
 * 支持简体，繁体，日语，utf-8互相转换编码
 * 支持文件编码转换
 * 支持批量转换
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class LiteralCodeUI extends TransparentPanelUI {

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
     * 初始化组件
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
     * 初始化布局
     */
    protected void initUI() {
        final Color normal = new Color(245, 255, 255);
        final Color button = new Color(240, 255, 250);
        final Font fontPlain = new Font("微软雅黑", Font.PLAIN, 14);
        fromCharset = TextConvert.GBK;
        toCharset = TextConvert.UTF_8;

        //选择文件按钮
        btnChooseFile.setOpaque(false);
        btnChooseFile.setText(chooseFile);
        btnChooseFile.setFont(fontPlain);
        btnChooseFile.setBounds(84, 50, 105, 35);
        btnChooseFile.setBackground(button);
        add(btnChooseFile);

        //最上方转换按钮
        btnExchangeFile.setOpaque(false);
        btnExchangeFile.setText(exchangeTip);
        btnExchangeFile.setFont(fontPlain);
        btnExchangeFile.setBounds(596, 50, 105, 35);
        btnExchangeFile.setBackground(button);
        add(btnExchangeFile);

        //文件路径文本框
        textFieldRoute.setBackground(normal);
        textFieldRoute.setEditable(false);
        textFieldRoute.setText(route);
        textFieldRoute.setBounds(213, 50, 375, 35);
        textFieldRoute.setFont(fontPlain);
        textFieldRoute.setForeground(Color.GRAY);
        add(textFieldRoute);

        //左
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

        //右
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

        //转换按钮
        btnExchange.setOpaque(false);
        btnExchange.setText(exchange);
        btnExchange.setFont(fontPlain);
        btnExchange.setBounds(342, 305, 105, 35);
        btnExchange.setBackground(button);
        add(btnExchange);

        //标签从
        lblFrom.setText(from);
        lblFrom.setFont(fontPlain);
        lblFrom.setBounds(70, 116, 20, 20);
        add(lblFrom);

        //标签转换到
        lblTo.setText(to);
        lblTo.setFont(fontPlain);
        lblTo.setBounds(440, 116, 60, 20);
        add(lblTo);

        //编码标签
        lblLeftCode.setText(code);
        lblLeftCode.setFont(fontPlain);
        lblLeftCode.setBounds(302, 114, 60, 20);
        add(lblLeftCode);

        //编码标签
        lblRightCode.setText(code);
        lblRightCode.setFont(fontPlain);
        lblRightCode.setBounds(702, 114, 60, 20);
        add(lblRightCode);
    }

    /**
     * 建立监听事件
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
            TextConvert.convertRoot(file, fromCharset, toCharset);
            new SuccessDialog();
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
     * 成功修改的消息框
     *
     * @author 杨弘
     */
    class SuccessDialog extends JDialog {

        private static final long serialVersionUID = 4237521911102115879L;
        private static final String success = "已成功修改文件编码。";
        private static final String OK = "确定";
        private final FileResource resource = new FileResource();

        /**
         * 初始化窗口
         */
        SuccessDialog() {
            setResizable(false);
            setFocusable(true);
            getContentPane().setLayout(null);
            setIconImage(Toolkit.getDefaultToolkit().getImage(resource.toolboxURL));
            setSize(300, 220);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JLabel lblSuccess = new JLabel(success);
            lblSuccess.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            lblSuccess.setBounds(75, 60, 150, 30);
            add(lblSuccess);

            JButton confirm = new JButton(OK);
            confirm.setFont(new Font("微软雅黑", Font.BOLD, 15));
            confirm.setBounds(100, 140, 100, 30);
            confirm.addActionListener(l -> dispose());
            add(confirm);

            JLabel lblBackGround = new JLabel();
            lblBackGround.setIcon(new ImageIcon(resource.aboutURL));
            lblBackGround.setBounds(0, 0, 300, 200);
            add(lblBackGround);

        }
    }
    /**
     * 构建文字编码转换框架
     */
    LiteralCodeUI() {
        super();
    }

}