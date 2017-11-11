package com.ToolBox.UI;

import com.ToolBox.history.Intent;

import javax.swing.*;
import java.awt.*;

/**
 * 一个全能的科学计算器，带有历史记录功能，支持结果的保存，调用及复制，可进行复杂的带优先级的高精度计算，
 * 程序员键盘提供二进制，八进制，十进制，十六进制的换算，以及各种位运算，
 * 还可以通过比特键盘直接操纵二进制数值，支持带优先级的计算，支持十进制计算结果保存，调用及复制。
 * 汇率计算器可从联网获取最新汇率，默认使用内置的汇率，换算结果并显示当前货币兑换汇率，并支持结果的保存，调用及复制。
 * 换算计算器可根据类型对一个单位换算到另一个单位，支持结果保存，调用及复制。
 * 日期计算器有两部分组成，一个是日期间距计算，即一个日期到另一个日期的天数，以及周期，另一部分是对日期的增减，
 * 选择一个日期，再选择增减的周期数，返回增减后的日期。
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class CalculatorUI extends TransparentPanelUI {

    public static final long serialVersionUID = -7067036135688239326L;
    public static final String strTabbed = "标签页";
    private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private static final String scientifc = "科学计算器";
    private static final String exchange = "汇率计算器";
    private static final String programer = "程序员键盘";
    private static final String convertion = "单位换算器";
    private static final String calendar = "日期万年历";
    private JPanel panelCalculator;
    private JPanel panelExchangeRate;
    private JPanel panelKeyBoard;
    private JPanel panelConversion;
    private JPanel panelCalendar;
    private JPanel panelHistory;

    /**
     * 初始化组件
     */
    @Override
    protected void initCompoment() {
        panelCalculator = new ScientificUI();
        panelExchangeRate = new ExchangeRateUI();
        panelKeyBoard = new ProgramerUI();
        panelConversion = new ConversionUI();
        panelCalendar = new CalendarUI();
        panelHistory = new HistoryUI();
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {

        // 构造标签页
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 597, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // 科学计算器标签页
        panelCalculator.setLayout(null);
        tabbedPane.addTab(scientifc, null, panelCalculator, null);

        // 汇率计算器标签页
        panelExchangeRate.setLayout(null);
        tabbedPane.addTab(exchange, null, panelExchangeRate, null);

        // 程序员键盘标签页
        tabbedPane.addTab(programer, null, panelKeyBoard, null);
        panelKeyBoard.setLayout(null);

        // 单位换算器标签页
        panelConversion.setLayout(null);
        tabbedPane.addTab(convertion, null, panelConversion, null);

        // 日期万年历标签页
        panelCalendar.setLayout(null);
        tabbedPane.addTab(calendar, null, panelCalendar, null);

        // 历史记录
        panelHistory.setBorder(BorderFactory.createTitledBorder(""));
        panelHistory.setBounds(595, -13, 199, 566);
        panelHistory.setLayout(null);
        add(panelHistory);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        tabbedPane.addChangeListener(e -> setSelectedComponent(tabbedPane.getSelectedComponent().getClass().getName()));
    }

    /**
     * 初始化标签页
     * 添加科学计算器标签页，汇率计算器标签页，
     * 程序员键盘标签页，单位换算器标签页，日期万年历标签页
     * 以及历史记录侧边栏
     */
    CalculatorUI() {
        super();
        Intent.setObj(serialVersionUID, strTabbed, tabbedPane.getSelectedComponent().getClass().getName());
    }

    /**
     * 设定当前所在组件
     */
    private void setSelectedComponent(String panel) {
        Intent.setObj(serialVersionUID, strTabbed, panel);
    }

}