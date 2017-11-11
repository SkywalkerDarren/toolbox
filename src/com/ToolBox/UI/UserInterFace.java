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
 * �����ܲ��ֵ�����
 *
 * @author ��룬�������������
 * @version 1.0
 */
class UserInterFace extends JFrame implements ActionListener {

    private static final long serialVersionUID = -5503828920612138533L;
    private final static String CALCTOOLS = " ���㹤�� ";
    private final static String ENCODETOOLS = " ������빤�� ";
    private final static String QRCODETOOLS = " ��ά������ ";
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
     * ��ʼ������
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
     * ��ܳ�ʼ��
     * ���س�������ܣ�������ͣ����ý������ͣ��������壬��������������
     */
    private void initializeFrame() {

        Dimension screenSize = getToolkit().getScreenSize();

        setVisible(false);
        setTitle(" ToolBox     v1.0 ");
        setAlwaysOnTop(false);
        getContentPane().setFont(new Font("Consolas", Font.BOLD, 17));
        // �������꣺x��㣬y��㣬������ȣ�������
        setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2, 800, 600);
        setLocationRelativeTo(null);
        // �ɷ�������ڴ�С
        setResizable(false);
        // �û��������ڵĹرհ�ťʱ����ִ�еĲ���
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFocusable(true);
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(new FileResource().toolboxURL));

    }

    /**
     * ��ʼ���˵�������Ӹ���ѡ��
     */
    private void initializeMenuBar() {
        // ��Ӳ˵�������
        final Font font = new Font("΢���ź�", Font.PLAIN, 14);
        final Color color = new Color(240, 255, 255);
        final Dimension dimensionStart = new Dimension(90, 25);
        final Dimension dimensionFunction = new Dimension(135, 25);
        final Dimension dimensionAbout = new Dimension(90, 25);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // �ļ��˵�
        JMenu mnMenuStart = new JMenu(" �� �� ");
        mnMenuStart.setFont(font);
        menuBar.add(mnMenuStart);

        // ѡ��˵�
        JMenu mnMenuFunction = new JMenu(" �� �� ");
        mnMenuFunction.setFont(font);
        menuBar.add(mnMenuFunction);

        // �����˵�
        JMenu mnMenuHelp = new JMenu(" �� �� ");
        mnMenuHelp.setFont(font);
        menuBar.add(mnMenuHelp);

        // ��ͼ�˵�
        JMenuItem mnMenuOpen = new JMenuItem(" �� ͼ ");
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

        // �˳��˵�
        JMenuItem menuItemExit = new JMenuItem(" �� �� ");
        menuItemExit.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuStart.add(menuItemExit);
        menuItemExit.setOpaque(true);
        menuItemExit.setFont(font);
        menuItemExit.setBackground(color);
        menuItemExit.setPreferredSize(dimensionStart);
        menuItemExit.addActionListener(e -> System.exit(0));

        // ���㹤�߲˵�
        JMenuItem menuItemCalculateTool = new JMenuItem(CALCTOOLS);
        menuItemCalculateTool.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemCalculateTool);
        menuItemCalculateTool.setOpaque(true);
        menuItemCalculateTool.setFont(font);
        menuItemCalculateTool.setPreferredSize(dimensionFunction);
        menuItemCalculateTool.setBackground(color);
        menuItemCalculateTool.addActionListener(this);

        // ������빤�߲˵�
        JMenuItem menuItemEncodeAndDecode = new JMenuItem(ENCODETOOLS);
        menuItemEncodeAndDecode.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemEncodeAndDecode);
        menuItemEncodeAndDecode.setOpaque(true);
        menuItemEncodeAndDecode.setFont(font);
        menuItemEncodeAndDecode.setPreferredSize(dimensionFunction);
        menuItemEncodeAndDecode.setBackground(color);
        menuItemEncodeAndDecode.addActionListener(this);

        // ��ά��˵�
        JMenuItem menuItemQRCode = new JMenuItem(QRCODETOOLS);
        menuItemQRCode.setAlignmentX(JMenuItem.CENTER_ALIGNMENT);
        mnMenuFunction.add(menuItemQRCode);
        menuItemQRCode.setOpaque(true);
        menuItemQRCode.setFont(font);
        menuItemQRCode.setPreferredSize(dimensionFunction);
        menuItemQRCode.setBackground(color);
        menuItemQRCode.addActionListener(this);

        // ���ڲ˵�
        JMenuItem menuItemAbout = new JMenuItem(" �� �� ");
        mnMenuHelp.add(menuItemAbout);
        menuItemAbout.setOpaque(true);
        menuItemAbout.setFont(font);
        menuItemAbout.setPreferredSize(dimensionAbout);
        menuItemAbout.setBackground(color);
        menuItemAbout.addActionListener(e -> {
            // �������ڴ���
            new AboutWindow();
        });
    }

    /**
     * ��ʼ�������
     */
    private void initializeJPanel() {
        // ʹ�ÿ�Ƭ����
        cardPanel.setLayout(new CardLayout());
        getContentPane().add(cardPanel);

        // ����������������
        JPanel calculateTools = new CalculatorUI();
        calculateTools.setLayout(null);
        cardPanel.add(calculateTools, CALCTOOLS);

        // ���������빤�����
        JPanel encodeAndDecodeTools = new EncodeAndDecodeUI();
        encodeAndDecodeTools.setLayout(null);
        cardPanel.add(encodeAndDecodeTools, ENCODETOOLS);

        // QR�����ά�빤�����
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
