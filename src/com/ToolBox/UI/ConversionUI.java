package com.ToolBox.UI;

import com.ToolBox.measurement.Measurement;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map.Entry;

/**
 * ������������λ���л��㲢������
 * ֧����ʷ��¼����
 *
 * @author ��룬�������������
 */
class ConversionUI extends JPanel {

    private static final long serialVersionUID = -1342718738933302379L;
    private static JTextField tfSource = new JTextField();

    /**
     * ��ʼ������������Ļ�������
     */
    public ConversionUI() {

        setOpaque(false);

        Measurement m = new Measurement();
        //��һ��
        JComboBox<String> comboBoxType = new JComboBox<>();
        Color color = new Color(240, 255, 255);
        comboBoxType.setBackground(color);
        comboBoxType.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxType.setBounds(103, 95, 150, 30);
        add(comboBoxType);
        for (String type : m.measureType) {
            comboBoxType.addItem(type);
        }

        //�ڶ���
        JComboBox<String> comboBoxSource = new JComboBox<>();
        comboBoxSource.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxSource.setBackground(color);
        comboBoxSource.setBounds(103, 207, 150, 30);
        add(comboBoxSource);

        //������
        JComboBox<String> comboBoxTarget = new JComboBox<>();
        comboBoxTarget.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        comboBoxTarget.setBackground(color);
        comboBoxTarget.setBounds(103, 326, 150, 30);
        add(comboBoxTarget);

        for (Entry<String, BigDecimal> entry : m.length.entrySet()) {
            comboBoxSource.addItem(entry.getKey());
            comboBoxTarget.addItem(entry.getKey());
        }

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

        //��һ��

        tfSource.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        tfSource.setBackground(color);
        tfSource.setBounds(297, 208, 151, 31);
        add(tfSource);
        tfSource.setColumns(10);

        //�ڶ���
        JTextField tfTarget = new JTextField();
        tfTarget.setEditable(false);
        tfTarget.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        tfTarget.setColumns(10);
        tfTarget.setBackground(color);
        tfTarget.setBounds(297, 326, 151, 31);
        add(tfTarget);

        //��������
        JLabel lblType = new JLabel("\u8BF7\u9009\u62E9\u6362\u7B97\u7C7B\u578B");
        lblType.setFont(new Font("����", Font.PLAIN, 15));
        lblType.setBounds(103, 40, 150, 30);
        add(lblType);

        //���㵥λ
        JLabel lblUnit = new JLabel("\u8BF7\u9009\u62E9\u6362\u7B97\u5355\u4F4D");
        lblUnit.setFont(new Font("����", Font.PLAIN, 14));
        lblUnit.setBounds(103, 150, 150, 30);
        add(lblUnit);

        //���㰴ť
        JButton btnConvert = new JButton("\u6362\u7B97");
        btnConvert.setBackground(new Color(245, 255, 250));
        btnConvert.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        btnConvert.setBounds(338, 268, 70, 35);
        add(btnConvert);

        //add
        btnConvert.addActionListener(e -> {
            BigDecimal r = m.conver((String) comboBoxSource.getSelectedItem(), (String) comboBoxTarget.getSelectedItem(),
                    new BigDecimal(tfSource.getText()), (String) comboBoxType.getSelectedItem());
            String result = r.setScale(6, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
            tfTarget.setText(result);
            HistoryUI.updateRecord(result);
        });


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
