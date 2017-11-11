package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * 编码解码主界面，放有Base64加密解密，Unicode编码解码，MD5信息摘要功能。
 * 文本框均支持复制 粘贴 全选 剪切的常用功能。
 * MD5支持对文件校验，以及文字信息校验
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class EncodeAndDecodeUI extends JPanel {
    private static final long serialVersionUID = 8137943111751980202L;
    private static final String base64 = "Base64加密/解密";
    private static final String unicode = "Unicode中文互转";
    private static final String md5 = "MD5文字加密/文件校验";
    private static final String code = "文件/文字编码批量转换";

    /**
     * 初始化编码解码功能
     * 添加
     * base64加/解密, Unicode中文互转, MD5校验
     */
    EncodeAndDecodeUI() {

        setOpaque(false);

        // 构造标签页
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 800, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // Base64加密/解密标签页
        JPanel panelBase64 = new Base64UI();
        panelBase64.setOpaque(false);
        panelBase64.setLayout(null);
        tabbedPane.addTab(base64, null, panelBase64, null);

        // Unicode中文互转
        JPanel panelUnicode = new UnicodeUI();
        panelUnicode.setLayout(null);
        panelBase64.setOpaque(false);
        tabbedPane.addTab(unicode, null, panelUnicode, null);

        // MD5校验标签页
        JPanel panelMD5 = new MD5UI();
        tabbedPane.addTab(md5, null, panelMD5, null);
        panelMD5.setOpaque(false);
        panelMD5.setLayout(null);

        // 文字编码批量转换标签页
        JPanel panelLiteral = new LiteralCodeUI();
        panelLiteral.setOpaque(false);
        panelLiteral.setLayout(null);
        tabbedPane.addTab(code, null, panelLiteral, null);
    }

}
