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
class ExchangeRateUI extends TransparentPanelUI implements MouseListener {

    private static final long serialVersionUID = -4791068421614363948L;
    private static final String value = "��ֵ";
    private static final String CNY = "CNY";
    private static final String select = "ѡ�����";
    private static final String input = "������";
    private static final String update = "���»���";
    private static final String exchanger = "�һ�";
    private static final String pattern = "yyyy��MM��dd�� ���³ɹ�";
    private static final String failed = "����ʧ��";
    private static final String current = "��ǰ����Ϊ: ";
    private static JTextField textFieldSource = new JTextField();
    private ExchangerRateRecord exchange;
    private JTextField textFieldTarget;
    private JComboBox<String> comboBoxSource;
    private JComboBox<String> comboBoxTarget;
    private JLabel lbSelectCurrency;
    private JLabel lbDate;
    private JLabel lbMuch;
    private JLabel lbRate;
    private JButton btnUpdate;
    private JButton btnExchange;
    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        exchange = new ExchangerRateRecord();
        comboBoxSource = new JComboBox<>();
        comboBoxTarget = new JComboBox<>();
        lbSelectCurrency = new JLabel(select);
        lbDate = new JLabel();
        lbMuch = new JLabel(input);
        lbRate = new JLabel();
        btnUpdate = new JButton(update);
        btnExchange = new JButton(exchanger);
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {
        Color color = new Color(240, 255, 255);
        Font font = new Font("΢���ź�", Font.PLAIN, 15);
        Currency[] cur = exchange.getRates();
        Dimension dimension = new Dimension(175, 35);

        comboBoxSource.setToolTipText(value);
        comboBoxSource.setBackground(color);
        comboBoxSource.setFont(font);
        comboBoxSource.setLocation(86, 106);
        for (Currency aCur : cur) {
            comboBoxSource.addItem(aCur.getName());
        }
        comboBoxSource.setSelectedItem(CNY);
        comboBoxSource.setPreferredSize(dimension);
        comboBoxSource.setSize(dimension);
        comboBoxSource.setEditable(false);
        add(comboBoxSource);

        comboBoxTarget.setBackground(color);
        comboBoxTarget.setLocation(86, 333);
        comboBoxTarget.setFont(font);
        comboBoxTarget.setPreferredSize(dimension);
        comboBoxTarget.setSize(dimension);
        for (Currency aCur : cur) {
            comboBoxTarget.addItem(aCur.getName());
        }
        add(comboBoxTarget);

        textFieldSource.setBackground(color);
        textFieldSource.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        textFieldSource.setBounds(315, 106, 175, 35);
        textFieldSource.setColumns(10);
        add(textFieldSource);

        textFieldTarget = new JTextField();
        textFieldTarget.setBackground(color);
        textFieldTarget.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        textFieldTarget.setEditable(false);
        textFieldTarget.setBounds(315, 333, 175, 35);
        textFieldTarget.setColumns(10);
        add(textFieldTarget);

        lbSelectCurrency.setBounds(86, 58, 175, 35);
        add(lbSelectCurrency);

        lbDate.setBounds(86, 253, 175, 35);
        add(lbDate);

        lbMuch.setBounds(315, 58, 175, 35);
        add(lbMuch);

        lbRate.setBounds(315, 283, 175, 35);
        add(lbRate);

        btnUpdate.setBackground(new Color(224, 255, 255));
        btnUpdate.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        btnUpdate.setBounds(86, 217, 174, 35);
        add(btnUpdate);

        btnExchange.setBackground(new Color(224, 255, 255));
        btnExchange.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        btnExchange.setBounds(315, 217, 174, 35);
        add(btnExchange);
    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {
        Date today = new Date();
        DateFormat format = new SimpleDateFormat(pattern);
        today.getTime();
        format.format(today);

        textFieldSource.addMouseListener(this);
        textFieldTarget.addMouseListener(this);
        btnUpdate.addActionListener(e -> {
            if (exchange.update()) {
                lbDate.setText(format.format(today));
            } else {
                lbDate.setText(failed);
            }
        });

        btnExchange.addActionListener(e -> {
            Currency source = exchange.getRecord((String) comboBoxSource.getSelectedItem());
            Currency target = exchange.getRecord((String) comboBoxTarget.getSelectedItem());
            double much = Double.parseDouble(textFieldSource.getText());
            BigDecimal money = exchange.calcRate(source, much, target);
            textFieldTarget.setText(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            HistoryUI.updateRecord(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            lbRate.setText(current + target.getRateToUSD() / source.getRateToUSD());
        });
    }

    /**
     * ������ʶһ�����
     */
    public ExchangeRateUI() {
        super();
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
