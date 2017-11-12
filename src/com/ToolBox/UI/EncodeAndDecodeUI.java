package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * 编码解码主界面，放有Base64加密解密，Unicode编码解码，MD5信息摘要功能。
 * 文本框均支持复制 粘贴 全选 剪切的常用功能。
 * MD5支持对文件校验，以及文字信息校验
 * 文件/文字批量编码可以对文本文件，进行批量编码转换
 * 也可以对少量编码错误文件进行转换
 * 支持文字的编码转换及目录内所有文件编码转换
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class EncodeAndDecodeUI extends TransparentPanelUI {
    private static final long serialVersionUID = 8137943111751980202L;
    private static final String base64 = "Base64编码/解码";
    private static final String unicode = "Unicode中文互转";
    private static final String md5 = "MD5文字加密/文件校验";
    private static final String code = "文件/文字编码批量转换";
    private JTabbedPane tabbedPane;
    private JPanel panelBase64;
    private JPanel panelUnicode;
    private JPanel panelMD5;
    private JPanel panelLiteral;

    /**
     * 初始化组件
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
     * 初始化布局
     */
    @Override
    protected void initUI() {
        // 构造标签页
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 800, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // Base64加密/解密标签页
        panelBase64.setOpaque(false);
        panelBase64.setLayout(null);
        tabbedPane.addTab(base64, null, panelBase64, null);

        // Unicode中文互转
        panelUnicode.setLayout(null);
        panelBase64.setOpaque(false);
        tabbedPane.addTab(unicode, null, panelUnicode, null);

        // MD5校验标签页
        tabbedPane.addTab(md5, null, panelMD5, null);
        panelMD5.setOpaque(false);
        panelMD5.setLayout(null);

        // 文字编码批量转换标签页
        panelLiteral.setOpaque(false);
        panelLiteral.setLayout(null);
        tabbedPane.addTab(code, null, panelLiteral, null);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {

    }

    /**
     * 初始化编码解码功能
     * 添加
     * base64加/解密, Unicode中文互转, MD5校验
     */
    EncodeAndDecodeUI() {
        super();
    }

}