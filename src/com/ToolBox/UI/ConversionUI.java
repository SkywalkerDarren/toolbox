package com.ToolBox.UI;

import com.ToolBox.measurement.Measurement;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Map.Entry;

/**
 * ������������λ���л��㲢������
 * ֧����ʷ��¼����
 *
 * @author ��룬�������������
 */
class ConversionUI extends TransparentPanelUI {

    public static final long serialVersionUID = -1342718738933302379L;
    public static final String strResult = "���";
    private static final String type = "��ѡ��������";
    private static final String unit = "��ѡ���㵥λ";
    private static final String exchange = "����";
    private static JTextField tfSource = new JTextField();
    private JTextField tfTarget;
    private JComboBox<String> comboBoxType;
    private JComboBox<String> comboBoxSource;
    private JComboBox<String> comboBoxTarget;
    private JLabel lblType;
    private JLabel lblUnit;
    private JButton btnConvert;
    private Measurement m;

    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        m = new Measurement();
        tfTarget = new JTextField();
        comboBoxType = new JComboBox<>();
        comboBoxSource = new JComboBox<>();
        comboBoxTarget = new JComboBox<>();
        lblType = new JLabel(type);
        lblUnit = new JLabel(unit);
        btnConvert = new JButton(exchange);
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {

        // ����
        Color color = new Color(240, 255, 255);
        comboBoxType.setBackground(color);
        comboBoxType.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxType.setBounds(103, 95, 150, 30);
        add(comboBoxType);
        for (String type : m.measureType) {
            comboBoxType.addItem(type);
        }

        // Դ��ֵ
        tfSource.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        tfSource.setBackground(color);
        tfSource.setBounds(297, 208, 151, 31);
        tfSource.setColumns(10);
        add(tfSource);

        // Դ��λ
        comboBoxSource.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxSource.setBackground(color);
        comboBoxSource.setBounds(103, 207, 150, 30);
        add(comboBoxSource);

        // Ŀ����ֵ
        tfTarget.setEditable(false);
        tfTarget.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        tfTarget.setColumns(10);
        tfTarget.setBackground(color);
        tfTarget.setBounds(297, 326, 151, 31);
        add(tfTarget);

        // Ŀ�굥λ
        comboBoxTarget.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxTarget.setBackground(color);
        comboBoxTarget.setBounds(103, 326, 150, 30);
        add(comboBoxTarget);

        for (Entry<String, BigDecimal> entry : m.length.entrySet()) {
            comboBoxSource.addItem(entry.getKey());
            comboBoxTarget.addItem(entry.getKey());
        }

        // ��������
        lblType.setFont(new Font("����", Font.PLAIN, 15));
        lblType.setBounds(103, 40, 150, 30);
        add(lblType);

        // ���㵥λ
        lblUnit.setFont(new Font("����", Font.PLAIN, 14));
        lblUnit.setBounds(103, 150, 150, 30);
        add(lblUnit);

        // ���㰴ť
        btnConvert.setBackground(new Color(245, 255, 250));
        btnConvert.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        btnConvert.setBounds(338, 268, 70, 35);
        add(btnConvert);

    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {
        comboBoxType.addActionListener(e -> {
            switch (comboBoxType.getSelectedIndex()) {
                case 0:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, BigDecimal> entry : m.length.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 1:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, BigDecimal> entry : m.area.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 2:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, BigDecimal> entry : m.volume.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 3:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, BigDecimal> entry : m.speed.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 4:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, BigDecimal> entry : m.mass.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 5:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, String> entry : m.temp.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
                case 6:
                    comboBoxSource.removeAllItems();
                    comboBoxTarget.removeAllItems();
                    for (Entry<String, String> entry : m.angle.entrySet()) {
                        comboBoxSource.addItem(entry.getKey());
                        comboBoxTarget.addItem(entry.getKey());
                    }
                    break;
            }
        });
        tfSource.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    RightClickMenu menu = new RightClickMenu((JTextComponent) e.getComponent());
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        tfTarget.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    RightClickMenu menu = new RightClickMenu((JTextComponent) e.getComponent());
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnConvert.addActionListener(e -> {
            BigDecimal r = m.conver((String) comboBoxSource.getSelectedItem(), (String) comboBoxTarget.getSelectedItem(),
                    new BigDecimal(tfSource.getText()), (String) comboBoxType.getSelectedItem());
            String result = r.setScale(6, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
            tfTarget.setText(result);
            HistoryUI.updateRecord(result);
        });
    }

    /**
     * ��ʼ������������Ļ�������
     */
    public ConversionUI() {
        super();
    }

    /**
     * �趨����ʷ��¼����ȡ�Ľ������ʾ��UI�ϲ����ص����ʽ��
     *
     * @param s ��ȡ�Ľ��
     */
    static void setTempResult(String s) {
        tfSource.setText(s);
    }
}
