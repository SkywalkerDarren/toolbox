package com.ToolBox.UI;

import com.ToolBox.capture.ScreenWindow;
import com.ToolBox.history.Intent;
import com.ToolBox.qrcode.Qrcode;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
class QRCodeUI extends TransparentPanelUI {

    private final static String topTip = "��ʾ����";
    private final static String levelTip = "����ȼ�";
    private final static String sizeTip = "ѡ���С";
    private final static String createQR = "���ɶ�ά��";
    private final static String screenShot = "��ͼ";
    private final static String saveRoute = "���浽";
    private final static String chooseFileString = "ѡ���ļ�";
    private final static String identityString = "ʶ���ά��";
    private final static String title = "��ά�빤��";
    private final static String L = "��";
    private final static String M = "��";
    private final static String Q = "��";
    private final static String H = "����";
    private final static String S = "С";
    private final static String B = "��";
    private final static JFileChooser imageFileChooser = new JFileChooser();
    private final static JFileChooser directoryChooser = new JFileChooser();
    private static final long serialVersionUID = 4091338005524008141L;
    private BufferedImage image;
    private Path path;
    private TextBox textAreaTop;
    private JComboBox<String> comboBoxLevel;
    private JComboBox<String> comboBoxSize;
    private JButton btnCreate;
    private JButton btnSave;
    private JButton btnScreenShot;
    private JButton btnChooseFile;
    private JButton btnIdentity;
    private JPanel display;
    private JLabel lblLevel;
    private JLabel lblSize;
    private JLabel imageLabel;


    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        textAreaTop = new TextBox(topTip);
        comboBoxLevel = new JComboBox<>();
        comboBoxSize = new JComboBox<>();
        btnCreate = new JButton(createQR);
        btnSave = new JButton(saveRoute);
        btnScreenShot = new JButton(screenShot);
        btnChooseFile = new JButton(chooseFileString);
        display = new JPanel();
        lblLevel = new JLabel(levelTip);
        lblSize = new JLabel(sizeTip);
        imageLabel = new JLabel("");
        btnIdentity = new JButton(identityString);
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {
        Color normal = new Color(245, 255, 255);

        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        textAreaTop.setBounds(55, 75, 330, 150);
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
        comboBoxLevel.addItem(L);
        comboBoxLevel.addItem(M);
        comboBoxLevel.addItem(Q);
        comboBoxLevel.addItem(H);
        comboBoxLevel.setSelectedItem(M);
        Qrcode.setCorrection(ErrorCorrectionLevel.M);
        add(comboBoxLevel);

        comboBoxSize.setVisible(true);
        comboBoxSize.setBounds(235, 250, 150, 30);
        comboBoxSize.setBackground(normal);
        comboBoxSize.setForeground(normal);
        comboBoxSize.addItem(S);
        comboBoxSize.addItem(M);
        comboBoxSize.addItem(B);
        comboBoxSize.setSelectedItem(M);
        Qrcode.setSize(Qrcode.MEDIUM);
        add(comboBoxSize);

        btnCreate.setVisible(true);
        btnCreate.setBounds(55, 305, 150, 30);
        Color dark = new Color(225, 255, 250);
        btnCreate.setBackground(dark);
        add(btnCreate);

        btnSave.setVisible(true);
        btnSave.setBounds(235, 305, 150, 30);
        btnSave.setBackground(dark);
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
        add(btnScreenShot);

        btnChooseFile.setVisible(true);
        btnChooseFile.setBounds(535, 435, 100, 30);
        btnChooseFile.setBackground(dark);
        add(btnChooseFile);

        btnIdentity.setVisible(true);
        btnIdentity.setBounds(635, 435, 100, 30);
        btnIdentity.setBackground(dark);
        add(btnIdentity);

    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {
        comboBoxLevel.addActionListener(l -> {
            if (comboBoxLevel.getSelectedItem() != null) {
                switch ((String) comboBoxLevel.getSelectedItem()) {
                    case L:
                        Qrcode.setCorrection(ErrorCorrectionLevel.L);
                        break;
                    case M:
                        Qrcode.setCorrection(ErrorCorrectionLevel.M);
                        break;
                    case Q:
                        Qrcode.setCorrection(ErrorCorrectionLevel.Q);
                        break;
                    case H:
                        Qrcode.setCorrection(ErrorCorrectionLevel.H);
                        break;
                    default:
                        break;
                }
            }
        });

        comboBoxSize.addActionListener(l -> {
            if (comboBoxSize.getSelectedItem() != null) {
                switch ((String) comboBoxSize.getSelectedItem()) {
                    case S:
                        Qrcode.setSize(Qrcode.SMALL);
                        break;
                    case M:
                        Qrcode.setSize(Qrcode.MEDIUM);
                        break;
                    case B:
                        Qrcode.setSize(Qrcode.LARGE);
                        break;
                    default:
                        break;
                }
            }
        });

        btnCreate.addActionListener(l -> {
            String src = textAreaTop.getText();
            image = Qrcode.generateImage(src);
            imageLabel.setIcon(new ImageIcon(image));
        });

        btnSave.addActionListener(l -> {

            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int state = directoryChooser.showSaveDialog(null);
            switch (state) {
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    path = directoryChooser.getSelectedFile().toPath();
                    break;
            }

            try {
                Qrcode.generateFile(textAreaTop.getText(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

        btnChooseFile.addActionListener(l -> {
            imageFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter imageFilter = new FileNameExtensionFilter("ͼƬ�ļ�", "png", "jpg");
            imageFileChooser.setFileFilter(imageFilter);
            int state = imageFileChooser.showSaveDialog(null);
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

        btnIdentity.addActionListener(e -> {
            textAreaTop.setForeground(Color.BLACK);
            String result = Qrcode.decodeQr(image);
            textAreaTop.setText(result);
        });
    }

    /**
     * ���ض�ά�����
     */
    QRCodeUI() {
        super();
        setBorder(BorderFactory.createTitledBorder(title));
    }
}
