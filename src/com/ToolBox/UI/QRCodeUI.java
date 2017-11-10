package com.ToolBox.UI;

import com.ToolBox.capture.Intent;
import com.ToolBox.capture.ScreenWindow;
import com.ToolBox.qrcode.Qrcode;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * ��ά������壬֧�ֶ�ά��������༭
 * ��ѡ��ͼƬ��С �� �� С
 * ��ѡ����ȼ� �� �� �� ����
 * ֧�ֶ�ά��ͼƬ����
 *
 * @author ��룬�������������
 */
class QRCodeUI extends JPanel {

    private final static String topTip = "��ʾ����";
    private final static String levelTip = "����ȼ�";
    private final static String sizeTip = "ѡ���С";
    private final static String createQR = "���ɶ�ά��";
    private final static String screenShot = "��ͼ";
    private final static String saveRoute = "���浽";
    private final static String chooseFileString = "ѡ���ļ�";
    private final static String identityString = "ʶ���ά��";
    private final static JFileChooser imageFileChooser = new JFileChooser();
    private final static JFileChooser directoryChooser = new JFileChooser();
    private static final long serialVersionUID = 4091338005524008141L;
    private BufferedImage image;
    private Path path;


    /**
     * ���ض�ά�����
     */
    QRCodeUI() {

        setOpaque(false);

        final JTextArea textAreaTop = new JTextArea(topTip);
        final JComboBox<String> comboBoxLevel = new JComboBox<>();
        final JComboBox<String> comboBoxSize = new JComboBox<>();
        final JButton btnCreate = new JButton(createQR);
        final JButton btnSave = new JButton(saveRoute);
        final JButton btnScreenShot = new JButton(screenShot);
        final JButton btnChooseFile = new JButton(chooseFileString);
        final JButton btnIdentity = new JButton(identityString);
        final JPanel display = new JPanel();
        final JLabel lblLevel = new JLabel(levelTip);
        final JLabel lblSize = new JLabel(sizeTip);
        JLabel imageLabel = new JLabel("");

        setBorder(BorderFactory.createTitledBorder("��ά�빤��"));

        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);


        // �Ϸ��ı���
        textAreaTop.setColumns(10000);
        textAreaTop.setFocusable(true);
        textAreaTop.setVisible(true);
        Font fontPlain = new Font("΢���ź�", Font.PLAIN, 14);
        textAreaTop.setFont(fontPlain);
        textAreaTop.setLineWrap(true);
        Color normal = new Color(245, 255, 255);
        textAreaTop.setBackground(normal);
        textAreaTop.setOpaque(false);
        textAreaTop.setForeground(Color.gray);
        textAreaTop.setBounds(55, 75, 330, 150);
        textAreaTop.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    RightClickMenu menu = new RightClickMenu(textAreaTop);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (textAreaTop.getText().equals(topTip)) {
                    textAreaTop.setForeground(Color.BLACK);
                    textAreaTop.setText(null);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

        });

        textAreaTop.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (textAreaTop.getText().length() == 0) {
                    textAreaTop.setForeground(Color.gray);
                    textAreaTop.setText(topTip);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        add(textAreaTop);

        lblLevel.setVisible(true);
        lblLevel.setBounds(60, 225, 150, 30);
        add(lblLevel);

        lblSize.setVisible(true);
        lblSize.setBounds(240, 225, 150, 30);
        add(lblSize);

        comboBoxLevel.setVisible(true);
        comboBoxLevel.setBounds(55, 250, 150, 30);
        comboBoxLevel.setBackground(normal);
        comboBoxLevel.setForeground(normal);
        comboBoxLevel.addItem("��");
        comboBoxLevel.addItem("��");
        comboBoxLevel.addItem("��");
        comboBoxLevel.addItem("����");
        comboBoxLevel.setSelectedItem("��");
        Qrcode.setCorrection(ErrorCorrectionLevel.M);
        comboBoxLevel.addActionListener(l -> {
            if (comboBoxLevel.getSelectedItem() != null) {
                switch ((String) comboBoxLevel.getSelectedItem()) {
                    case "��":
                        Qrcode.setCorrection(ErrorCorrectionLevel.L);
                        break;
                    case "��":
                        Qrcode.setCorrection(ErrorCorrectionLevel.M);
                        break;
                    case "��":
                        Qrcode.setCorrection(ErrorCorrectionLevel.Q);
                        break;
                    case "����":
                        Qrcode.setCorrection(ErrorCorrectionLevel.H);
                        break;
                    default:
                        break;
                }
            }
        });
        add(comboBoxLevel);

        comboBoxSize.setVisible(true);
        comboBoxSize.setBounds(235, 250, 150, 30);
        comboBoxSize.setBackground(normal);
        comboBoxSize.setForeground(normal);
        comboBoxSize.addItem("С");
        comboBoxSize.addItem("��");
        comboBoxSize.addItem("��");
        comboBoxSize.setSelectedItem("��");
        Qrcode.setSize(Qrcode.MEDIUM);
        comboBoxSize.addActionListener(l -> {
            if (comboBoxSize.getSelectedItem() != null) {
                switch ((String) comboBoxSize.getSelectedItem()) {
                    case "С":
                        Qrcode.setSize(Qrcode.SMALL);
                        break;
                    case "��":
                        Qrcode.setSize(Qrcode.MEDIUM);
                        break;
                    case "��":
                        Qrcode.setSize(Qrcode.LARGE);
                        break;
                    default:
                        break;
                }
            }
        });
        add(comboBoxSize);

        btnCreate.setVisible(true);
        btnCreate.setBounds(55, 305, 150, 30);
        Color dark = new Color(225, 255, 250);
        btnCreate.setBackground(dark);
        btnCreate.addActionListener(l -> {
            String src = textAreaTop.getText();
            image = Qrcode.generateImage(src);
            imageLabel.setIcon(new ImageIcon(image));
        });
        add(btnCreate);

        btnSave.setVisible(true);
        btnSave.setBounds(235, 305, 150, 30);
        btnSave.setBackground(dark);
        btnSave.addActionListener(l -> {

            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int state = directoryChooser.showOpenDialog(null);
            switch (state) {
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    path = directoryChooser.getSelectedFile().toPath();
                    break;
            }

            try {
                Qrcode.generateFile(textAreaTop.getText(), path);
                System.out.println("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add(btnSave);

        display.setLayout(new BorderLayout());
        display.setBorder(BorderFactory.createEtchedBorder());
        display.setVisible(true);
        display.setBounds(435, 78, 300, 325);
        display.setBackground(Color.WHITE);
        display.add(imageLabel, BorderLayout.CENTER);
        add(display);

        btnScreenShot.setVisible(true);
        btnScreenShot.setBounds(435, 435, 100, 30);
        btnScreenShot.setBackground(dark);
        btnScreenShot.addActionListener(l -> {
            try {
                JFrame screenShot = new ScreenWindow();
                screenShot.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        image = (BufferedImage) Intent.getObj(ScreenWindow.serialVersionUID, ScreenWindow.imageIntent);
                        imageLabel.setIcon(new ImageIcon(image));
                    }
                });
            } catch (Exception ignored) {
            }
        });
        add(btnScreenShot);

        btnChooseFile.setVisible(true);
        btnChooseFile.setBounds(535, 435, 100, 30);
        btnChooseFile.setBackground(dark);
        btnChooseFile.addActionListener(l -> {
            imageFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter imageFilter = new FileNameExtensionFilter("ͼƬ�ļ�", "png", "jpg");
            imageFileChooser.setFileFilter(imageFilter);
            int state = imageFileChooser.showOpenDialog(null);
            switch (state) {
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    try {
                        image = ImageIO.read(imageFileChooser.getSelectedFile());
                        imageLabel.setIcon(new ImageIcon(image));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    display.revalidate();
                    display.repaint();
                    break;
            }
        });
        add(btnChooseFile);

        btnIdentity.setVisible(true);
        btnIdentity.setBounds(635, 435, 100, 30);
        btnIdentity.setBackground(dark);
        btnIdentity.addActionListener(e -> {
            textAreaTop.setForeground(Color.BLACK);
            String result = Qrcode.decodeQr(image);
            textAreaTop.setText(result);
        });
        add(btnIdentity);
    }
}
