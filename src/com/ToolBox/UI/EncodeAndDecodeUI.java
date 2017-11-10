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
        tabbedPane.addTab("Base64加密/解密", null, panelBase64, null);

        // Unicode中文互转
        JPanel panelUnicode = new UnicodeUI();
        panelUnicode.setLayout(null);
        panelBase64.setOpaque(false);
        tabbedPane.addTab("Unicode中文互转", null, panelUnicode, null);

        // MD5校验标签页
        JPanel panelMD5 = new MD5UI();
        tabbedPane.addTab("MD5文字加密/文件校验", null, panelMD5, null);
        panelMD5.setOpaque(false);
        panelMD5.setLayout(null);


    }

}
