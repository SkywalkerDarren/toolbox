package com.ToolBox.UI;

import com.ToolBox.currency.Currency;
import com.ToolBox.currency.ExchangerRateRecord;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ת����Ĭ��ʹ�����û��ʣ����Դ����ϻ�ȡ���»���
 * ֧�ֽ����ֻ��ҵĻ���
 * ֧�ֽ������������ȡ
 *
 * @author ��룬�������������
 */
class ExchangeRateUI extends JPanel implements MouseListener {

    private static final long serialVersionUID = -4791068421614363948L;
    private static JTextField textFieldSource = new JTextField();
    private JTextField textFieldTarget;
    private ExchangerRateRecord exchange = new ExchangerRateRecord();

    /**
     * ������ʶһ�����
     */
    public ExchangeRateUI() {

        setOpaque(false);

        JComboBox<String> comboBoxSource = new JComboBox<>();
        comboBoxSource.setToolTipText("��ֵ");
        Color color = new Color(240, 255, 255);
        comboBoxSource.setBackground(color);
        Font font = new Font("΢���ź�", Font.PLAIN, 15);
        comboBoxSource.setFont(font);
        comboBoxSource.setLocation(86, 106);
        Currency[] cur = exchange.getRates();
        for (Currency aCur : cur) {
            comboBoxSource.addItem(aCur.getName());
        }
        comboBoxSource.setSelectedItem("CNY");
        Dimension dimension = new Dimension(175, 35);
        comboBoxSource.setPreferredSize(dimension);
        comboBoxSource.setSize(dimension);
        comboBoxSource.setEditable(false);
        add(comboBoxSource);

        JComboBox<String> comboBoxTarget = new JComboBox<>();
        comboBoxTarget.setBackground(color);
        comboBoxTarget.setLocation(86, 333);
        comboBoxTarget.setFont(font);
        comboBoxTarget.setPreferredSize(dimension);
        comboBoxTarget.setSize(dimension);
        for (Currency aCur : cur) {
            comboBoxTarget.addItem(aCur.getName());
        }
        add(comboBoxTarget);

        Date today = new Date();
        today.getTime();
        DateFormat format = new SimpleDateFormat("yyyy��MM��dd�� ���³ɹ�");
        format.format(today);

        textFieldSource.setBackground(color);
        textFieldSource.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        textFieldSource.setBounds(315, 106, 175, 35);
        textFieldSource.setColumns(10);
        textFieldSource.addMouseListener(this);
        add(textFieldSource);

        textFieldTarget = new JTextField();
        textFieldTarget.setBackground(color);
        textFieldTarget.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        textFieldTarget.setEditable(false);
        textFieldTarget.setBounds(315, 333, 175, 35);
        textFieldTarget.setColumns(10);
        textFieldTarget.addMouseListener(this);
        add(textFieldTarget);

        JLabel lbSelectCurrency = new JLabel("ѡ�����");
        lbSelectCurrency.setBounds(86, 58, 175, 35);
        add(lbSelectCurrency);

        JLabel lbDate = new JLabel();
        lbDate.setBounds(86, 253, 175, 35);
        add(lbDate);

        JLabel lbMuch = new JLabel("������");
        lbMuch.setBounds(315, 58, 175, 35);
        add(lbMuch);

        JLabel lbRate = new JLabel();
        lbRate.setBounds(315, 283, 175, 35);
        add(lbRate);

        JButton btnUpdate = new JButton("���»���");
        btnUpdate.setBackground(new Color(224, 255, 255));
        btnUpdate.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        btnUpdate.setBounds(86, 217, 174, 35);
        add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            if (exchange.update()) {
                lbDate.setText(format.format(today));
            } else {
                lbDate.setText("����ʧ��");
            }
        });

        JButton btnExchange = new JButton("�һ�");
        btnExchange.setBackground(new Color(224, 255, 255));
        btnExchange.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        btnExchange.setBounds(315, 217, 174, 35);
        btnExchange.addActionListener(e -> {
            Currency source = exchange.getRecord((String) comboBoxSource.getSelectedItem());
            Currency target = exchange.getRecord((String) comboBoxTarget.getSelectedItem());
            double much = Double.parseDouble(textFieldSource.getText());
            BigDecimal money = exchange.calcRate(source, much, target);
            textFieldTarget.setText(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            HistoryUI.updateRecord(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            lbRate.setText("��ǰ����Ϊ: " + target.getRateToUSD() / source.getRateToUSD());
        });
        add(btnExchange);
    }

    /**
     * �趨����ʷ��¼����ȡ�Ľ������ʾ��UI�ϲ����ص����ʽ��
     *
     * @param s ��ȡ�Ľ��
     */
    static void setTempResult(String s) {
        textFieldSource.setText(s);
    }

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
}
