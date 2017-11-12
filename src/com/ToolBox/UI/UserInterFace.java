package com.ToolBox.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 整体框架布局的生成
 *
 * @author 杨弘，徐祥亮，朱可欣
 * @version 1.0
 */
class UserInterFace extends JFrame implements ActionListener {

    private static final long serialVersionUID = -5503828920612138533L;
    private final static String CALCTOOLS = " 计算换算工具 ";
    private final static String ENCODETOOLS = " 编码解码工具 ";
    private final static String QRCODETOOLS = " 二维码生成 ";
    private JPanel cardPanel = new JPanel() {
        private static final long serialVersionUID = -1572461108315996809L;
        private BufferedImage img;

        @Override
        protected void paintComponent(Graphics g) {
            try {
                img = ImageIO.read(new FileResource().backgroundURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this);
        }
    };

    /**
     * 初始化程序
     */
    UserInterFace() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeFrame();
        initializeMenuBar();
        initializeJPanel();

    }

    /**
     * 框架初始化
     * 加载程序主框架，框架类型，设置焦点类型，设置字体，背景，窗口属性
     */
    private void initializeFrame() {

        Dimension screenSize = getToolkit().getScreenSize();

        setVisible(false);
        setTitle(" ToolBox     v1.0 ");
        setAlwaysOnTop(false);
        getContentPane().setFont(new Font("Consolas", Font.BOLD, 17));
        // 设置坐标：x起点，y起点，组件长度，组件宽度
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
        setLocationRelativeTo(null);
        // 可否调整窗口大小
        setResizable(false);
        // 用户单机窗口的关闭按钮时程序执行的操作
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFocusable(true);
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new FileResource().toolboxURL));

    }

    /**
     * 初始化菜单栏，添加各类选单
     */
    private void initializeMenuBar() {
        // 添加菜单工具栏
        final Font font = new Font("微软雅黑", Font.PLAIN, 14);
        final Color color = new Color(240, 255, 255);
        final Dimension dimensionStart = new Dimension(90, 25);
        final Dimension dimensionFunction = new Dimension(135, 25);
        final Dimension dimensionAbout = new Dimension(90, 25);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // 文件菜单
        JMenu mnMenuStart = new JMenu(" 文 件 ");
        mnMenuStart.setFont(font);
        menuBar.add(mnMenuStart);

        // 选项菜单
        JMenu mnMenuFunction = new JMenu(" 功 能 ");
        mnMenuFunction.setFont(font);
        menuBar.add(mnMenuFunction);

        // 帮助菜单
        JMenu mnMenuHelp = new JMenu(" 帮 助 ");
        mnMenuHelp.setFont(font);
        menuBar.add(mnMenuHelp);

        // 截图菜单
        JMenuItem mnMenuOpen = new JMenuItem(" 截 图 ");
        mnMenuOpen.setAlignmentX(JMenu.CENTER_ALIGNMENT);
        mnMenuStart.add(mnMenuOpen);
        mnMenuOpen.setFont(font);
        mnMenuOpen.setBackground(color);
        mnMenuOpen.setPreferredSize(dimensionStart);
        mnMenuOpen.setOpaque(true);
        mnMenuOpen.addActionListener(e -> {
            setVisible(false);
            SnapShot shot = new SnapShot();
            shot.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    setVisible(true);
                }
            });
        });

        // 退出菜单
        JMenuItem menuItemExit = new JMenuItem(" 退 出 ");
        menuItemExit.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuStart.add(menuItemExit);
        menuItemExit.setOpaque(true);
        menuItemExit.setFont(font);
        menuItemExit.setBackground(color);
        menuItemExit.setPreferredSize(dimensionStart);
        menuItemExit.addActionListener(e -> System.exit(0));

        // 计算工具菜单
        JMenuItem menuItemCalculateTool = new JMenuItem(CALCTOOLS);
        menuItemCalculateTool.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemCalculateTool);
        menuItemCalculateTool.setOpaque(true);
        menuItemCalculateTool.setFont(font);
        menuItemCalculateTool.setPreferredSize(dimensionFunction);
        menuItemCalculateTool.setBackground(color);
        menuItemCalculateTool.addActionListener(this);

        // 编码解码工具菜单
        JMenuItem menuItemEncodeAndDecode = new JMenuItem(ENCODETOOLS);
        menuItemEncodeAndDecode.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemEncodeAndDecode);
        menuItemEncodeAndDecode.setOpaque(true);
        menuItemEncodeAndDecode.setFont(font);
        menuItemEncodeAndDecode.setPreferredSize(dimensionFunction);
        menuItemEncodeAndDecode.setBackground(color);
        menuItemEncodeAndDecode.addActionListener(this);

        // 二维码菜单
        JMenuItem menuItemQRCode = new JMenuItem(QRCODETOOLS);
        menuItemQRCode.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemQRCode);
        menuItemQRCode.setOpaque(true);
        menuItemQRCode.setFont(font);
        menuItemQRCode.setPreferredSize(dimensionFunction);
        menuItemQRCode.setBackground(color);
        menuItemQRCode.addActionListener(this);

        // 关于菜单
        JMenuItem menuItemAbout = new JMenuItem(" 关 于 ");
        mnMenuHelp.add(menuItemAbout);
        menuItemAbout.setOpaque(true);
        menuItemAbout.setFont(font);
        menuItemAbout.setPreferredSize(dimensionAbout);
        menuItemAbout.setBackground(color);
        menuItemAbout.addActionListener(e -> {
            // 弹出关于窗口
            new AboutWindow();
        });
    }

    /**
     * 初始化主面板
     */
    private void initializeJPanel() {
        // 使用卡片布局
        cardPanel.setLayout(new CardLayout());
        getContentPane().add(cardPanel);

        // 构造计算器工具面板
        JPanel calculateTools = new CalculatorUI();
        calculateTools.setLayout(null);
        cardPanel.add(calculateTools, CALCTOOLS);

        // 构造编码解码工具面板
        JPanel encodeAndDecodeTools = new EncodeAndDecodeUI();
        encodeAndDecodeTools.setLayout(null);
        cardPanel.add(encodeAndDecodeTools, ENCODETOOLS);

        // QR构造二维码工具面板
        JPanel qrCodeTools = new QRCodeUI();
        qrCodeTools.setLayout(null);
        cardPanel.add(qrCodeTools, QRCODETOOLS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, e.getActionCommand());
    }
}