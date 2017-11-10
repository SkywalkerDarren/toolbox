package com.ToolBox.UI;

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
class CalculatorUI extends JPanel {

    private static final long serialVersionUID = -7067036135688239326L;
    private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    /**
     * 初始化标签页
     * 添加科学计算器标签页，汇率计算器标签页，
     * 程序员键盘标签页，单位换算器标签页，日期万年历标签页
     * 以及历史记录侧边栏
     */
    CalculatorUI() {

        setOpaque(false);

        // 构造标签页
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 597, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // 科学计算器标签页
        JPanel panelCalculator = new ScientificUI();
        panelCalculator.setLayout(null);
        tabbedPane.addTab("科学计算器", null, panelCalculator, null);

        // 汇率计算器标签页
        JPanel panelExchangeRate = new ExchangeRateUI();
        panelExchangeRate.setLayout(null);
        tabbedPane.addTab("汇率计算器", null, panelExchangeRate, null);

        // 程序员键盘标签页
        JPanel panelKeyBoard = new ProgramerUI();
        tabbedPane.addTab("程序员键盘", null, panelKeyBoard, null);
        panelKeyBoard.setLayout(null);

        // 单位换算器标签页
        JPanel panelConversion = new ConversionUI();
        panelConversion.setLayout(null);
        tabbedPane.addTab("单位换算器", null, panelConversion, null);

        // 日期万年历标签页
        JPanel panelCalendar = new CalendarUI();
        panelCalendar.setLayout(null);
        tabbedPane.addTab("日期万年历", null, panelCalendar, null);

        // 历史记录
        JPanel panelHistory = new HistoryUI();
        panelHistory.setBorder(BorderFactory.createTitledBorder(""));
        panelHistory.setBounds(595, -13, 199, 566);
        panelHistory.setLayout(null);
        add(panelHistory);
    }

    /**
     * 获得当前所选标签页名字
     *
     * @return 返回所选标签页名字
     */
    static String getSelectedComponent() {
        return tabbedPane.getSelectedComponent().getClass().getName();
    }

}
