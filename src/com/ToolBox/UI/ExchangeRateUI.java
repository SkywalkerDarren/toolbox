package com.ToolBox.UI;

import com.ToolBox.currency.Currency;
import com.ToolBox.currency.ExchangerRateRecord;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

/**
 * 汇率转换，默认使用内置汇率，可以从网上获取最新汇率
 * 支持近百种货币的换算
 * 支持结果保存与结果读取
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class ExchangeRateUI extends TransparentPanelUI implements MouseListener {

    private static final long serialVersionUID = -4791068421614363948L;
    private static final String value = "币值";
    private static final String CNY = "CNY";
    private static final String select = "选择货币";
    private static final String input = "输入金额";
    private static final String update = "更新汇率";
    private static final String exchanger = "兑换";
    private static final String failed = "更新失败";
    private static final String current = "当前汇率为: ";
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
     * 初始化组件
     */
    @Override
    protected void initComponent() {
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
     * 初始化布局
     */
    @Override
    protected void initUI() {
        Color color = new Color(240, 255, 255);
        Font font = new Font("微软雅黑", Font.PLAIN, 15);
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
        textFieldSource.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textFieldSource.setBounds(315, 106, 175, 35);
        textFieldSource.setColumns(10);
        add(textFieldSource);

        textFieldTarget = new JTextField();
        textFieldTarget.setBackground(color);
        textFieldTarget.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textFieldTarget.setEditable(false);
        textFieldTarget.setBounds(315, 333, 175, 35);
        textFieldTarget.setColumns(10);
        add(textFieldTarget);

        lbSelectCurrency.setBounds(86, 58, 175, 35);
        add(lbSelectCurrency);

        lbDate.setBounds(70, 253, 205, 35);
        lbDate.setText("当前汇率日期 " + exchange.getDate());
        add(lbDate);

        lbMuch.setBounds(315, 58, 175, 35);
        add(lbMuch);

        lbRate.setBounds(315, 283, 175, 35);
        add(lbRate);

        btnUpdate.setBackground(new Color(224, 255, 255));
        btnUpdate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        btnUpdate.setBounds(86, 217, 174, 35);
        add(btnUpdate);

        btnExchange.setBackground(new Color(224, 255, 255));
        btnExchange.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        btnExchange.setBounds(315, 217, 174, 35);
        add(btnExchange);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {

        textFieldSource.addMouseListener(this);
        textFieldTarget.addMouseListener(this);
        btnUpdate.addActionListener(e -> {
            if (exchange.update()) {
                lbDate.setText(exchange.getDate() + " 已获取最新汇率");
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
     * 构造汇率兑换布局
     */
    public ExchangeRateUI() {
        super();
    }

    /**
     * 设定从历史记录所获取的结果，显示到UI上并加载到表达式中
     *
     * @param s 获取的结果
     */
    static void setTempResult(String s) {
        textFieldSource.setText(s);
    }

    /**
     * 右键调出菜单
     *
     * @param e 鼠标事件
     */
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