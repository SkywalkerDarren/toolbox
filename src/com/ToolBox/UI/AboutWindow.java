package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * 关于窗口，显示当前版本号
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class AboutWindow extends JFrame {


    private static final long serialVersionUID = -2467559809056956188L;

    /**
     * 初始化关于窗口
     */
    AboutWindow() {
        FileResource resource = new FileResource();
        Dimension screenSize = getToolkit().getScreenSize();

        setTitle("About ToolBox");
        setResizable(false);
        setFocusable(true);
        getContentPane().setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(resource.toolboxURL));
        setVisible(true);
        setBounds((screenSize.width - 300) / 2, (screenSize.height - 220) / 2,
                300, 220);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 304, 201);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblCopyright = new JLabel("Copyright 2017 - ToolBox. ");
        setIconImage(new ImageIcon(resource.toolboxURL).getImage());
        lblCopyright.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblCopyright.setBounds(36, 88, 229, 54);
        panel.add(lblCopyright);

        JLabel lblRights = new JLabel("All Rights Reserved.");
        lblRights.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblRights.setBounds(60, 152, 188, 39);
        panel.add(lblRights);

        JLabel lblVersion = new JLabel("ToolBox   v1.0");
        lblVersion.setFont(new Font("Consolas", Font.PLAIN, 15));
        lblVersion.setBounds(82, 40, 183, 32);
        panel.add(lblVersion);

        ImageIcon ic = new ImageIcon(resource.toolboxURL);
        ic.setImage(ic.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        JLabel lblIcon = new JLabel("");
        lblIcon.setIcon(ic);
        lblIcon.setBounds(56, 40, 25, 32);
        panel.add(lblIcon);

        JLabel lblBackGround = new JLabel("New label");
        lblBackGround.setIcon(new ImageIcon(resource.aboutURL));
        lblBackGround.setBounds(0, 0, 304, 201);
        panel.add(lblBackGround);
    }
}
