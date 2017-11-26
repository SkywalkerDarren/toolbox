package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * 关于窗口，显示当前版本号
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class AboutWindow extends JDialog {


    private static final long serialVersionUID = -2467559809056956188L;

    /**
     * 初始化关于窗口
     */
    AboutWindow() {
        FileResource resource = new FileResource();

        setTitle("About Universal ToolBox");
        setResizable(false);
        setFocusable(true);
        getContentPane().setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(resource.toolboxURL));
        setSize(300, 220);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 304, 201);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblCopyright = new JLabel("Copyright 2017 - Universal ToolBox.");
        setIconImage(new ImageIcon(resource.toolboxURL).getImage());
        lblCopyright.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblCopyright.setBounds(6, 88, 294, 54);
        panel.add(lblCopyright);

        JLabel lblRights = new JLabel("All Rights Reserved.");
        lblRights.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblRights.setBounds(70, 152, 188, 39);
        panel.add(lblRights);

        JLabel lblVersion = new JLabel("Universal ToolBox v1.0");
        lblVersion.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblVersion.setBounds(60, 40, 183, 32);
        panel.add(lblVersion);

        ImageIcon ic = new ImageIcon(resource.toolboxURL);
        ic.setImage(ic.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(ic);
        lblIcon.setBounds(36, 40, 25, 32);
        panel.add(lblIcon);

        JLabel lblBackGround = new JLabel();
        lblBackGround.setIcon(new ImageIcon(resource.aboutURL));
        lblBackGround.setBounds(0, 0, 304, 201);
        panel.add(lblBackGround);
    }
}