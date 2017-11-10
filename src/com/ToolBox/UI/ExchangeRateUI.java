package com.ToolBox.UI;

import com.ToolBox.currency.Currency;
import com.ToolBox.currency.ExchangerRateRecord;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 汇率转换，默认使用内置汇率，可以从网上获取最新汇率
 * 支持近百种货币的换算
 * 支持结果保存与结果读取
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class ExchangeRateUI extends JPanel {

    private static final long serialVersionUID = -4791068421614363948L;
    private static JTextField textFieldSource = new JTextField();
    private JTextField textFieldTarget;
    private ExchangerRateRecord exchange = new ExchangerRateRecord();

    /**
     * 构造汇率兑换布局
     */
    public ExchangeRateUI() {

        setOpaque(false);

        JComboBox<String> comboBoxSource = new JComboBox<>();
        comboBoxSource.setToolTipText("币值");
        Color color = new Color(240, 255, 255);
        comboBoxSource.setBackground(color);
        Font font = new Font("微软雅黑", Font.PLAIN, 15);
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
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 更新成功");
        format.format(today);

        textFieldSource.setBackground(color);
        textFieldSource.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textFieldSource.setBounds(315, 106, 175, 35);
        add(textFieldSource);
        textFieldSource.setColumns(10);

        textFieldTarget = new JTextField();
        textFieldTarget.setBackground(color);
        textFieldTarget.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        textFieldTarget.setEditable(false);
        textFieldTarget.setBounds(315, 333, 175, 35);
        add(textFieldTarget);
        textFieldTarget.setColumns(10);

        JLabel lbSelectCurrency = new JLabel("选择货币");
        lbSelectCurrency.setBounds(86, 58, 175, 35);
        add(lbSelectCurrency);

        JLabel lbDate = new JLabel();
        lbDate.setBounds(86, 253, 175, 35);
        add(lbDate);

        JLabel lbMuch = new JLabel("输入金额");
        lbMuch.setBounds(315, 58, 175, 35);
        add(lbMuch);

        JLabel lbRate = new JLabel();
        lbRate.setBounds(315, 283, 175, 35);
        add(lbRate);

        JButton btnUpdate = new JButton("更新汇率");
        btnUpdate.setBackground(new Color(224, 255, 255));
        btnUpdate.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        btnUpdate.setBounds(86, 217, 174, 35);
        add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            if (exchange.update()) {
                lbDate.setText(format.format(today));
            } else {
                lbDate.setText("更新失败");
            }
        });

        JButton btnExchange = new JButton("兑换");
        btnExchange.setBackground(new Color(224, 255, 255));
        btnExchange.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        btnExchange.setBounds(315, 217, 174, 35);
        btnExchange.addActionListener(e -> {
            Currency source = exchange.getRecord((String) comboBoxSource.getSelectedItem());
            Currency target = exchange.getRecord((String) comboBoxTarget.getSelectedItem());
            double much = Double.parseDouble(textFieldSource.getText());
            BigDecimal money = exchange.calcRate(source, much, target);
            textFieldTarget.setText(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            HistoryUI.updateRecord(money.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            lbRate.setText("当前汇率为: " + target.getRateToUSD() / source.getRateToUSD());
        });
        add(btnExchange);
    }

    /**
     * 设定从历史记录所获取的结果，显示到UI上并加载到表达式中
     *
     * @param s 获取的结果
     */
    static void setTempResult(String s) {
        textFieldSource.setText(s);
    }
}
