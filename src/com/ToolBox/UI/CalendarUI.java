package com.ToolBox.UI;

import com.ToolBox.date.DateCalculate;
import org.joda.time.DateTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 日期计算器，支持日期间隔计算及日期增减天数计算
 * 显示总天数，周期时间
 * 不支持历史记录
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class CalendarUI extends TransparentPanelUI {

    private static final long serialVersionUID = -4791068421614363948L;
    private static final String interval = "日期之间的相隔时间";
    private static final String addAndMinusDay = "添加或减去天数";
    private static final Font fontPlain = new Font("微软雅黑", Font.PLAIN, 14);
    private static final Font fontBold = new Font("微软雅黑", Font.BOLD, 14);
    private static final Color normal = new Color(245, 255, 255);
    private JComboBox<String> comboBoxCalcMethod;
    private JPanel panelPlusOrMinus;
    private JPanel panelInterval;


    /**
     * 初始化组件
     */
    @Override
    protected void initCompoment() {
        comboBoxCalcMethod = new JComboBox<>();
        panelPlusOrMinus = new PlusOrMinusPanel();
        panelInterval = new IntervalPanel();
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        comboBoxCalcMethod.setBackground(normal);
        comboBoxCalcMethod.setBounds(97, 72, 160, 40);
        comboBoxCalcMethod.setFont(new Font("微软雅黑", Font.BOLD, 13));
        comboBoxCalcMethod.addItem(interval);
        comboBoxCalcMethod.addItem(addAndMinusDay);
        comboBoxCalcMethod.setVisible(true);
        add(comboBoxCalcMethod);

        //日期增减
        panelPlusOrMinus.setOpaque(false);
        panelPlusOrMinus.setLayout(null);
        panelPlusOrMinus.setVisible(true);
        panelPlusOrMinus.setBounds(0, 0, 590, 509);
        add(panelPlusOrMinus);

        //日期间隔
        panelInterval.setOpaque(false);
        panelInterval.setLayout(null);
        panelPlusOrMinus.setVisible(false);
        panelInterval.setBounds(0, 0, 600, 509);
        add(panelInterval);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        comboBoxCalcMethod.addActionListener(e -> {
            if (e.getSource() == comboBoxCalcMethod) {

                int index = comboBoxCalcMethod.getSelectedIndex();
                switch (index) {
                    case 0:
                        panelPlusOrMinus.setVisible(false);
                        panelInterval.setVisible(true);
                        break;
                    case 1:
                        panelPlusOrMinus.setVisible(true);
                        panelInterval.setVisible(false);
                        break;
                }

            }
        });
    }

    /**
     * 构建日期计算的UI基本框架
     */
    CalendarUI() {
        super();
    }

    /**
     * 日期距离类
     * 获得两个日期的间隔日期及总天数
     */
    private class IntervalPanel extends TransparentPanelUI {


        private static final long serialVersionUID = 1851775115929217134L;
        private static final String from = "自";
        private static final String year = "年，";
        private static final String month = "月，";
        private static final String day = "日";
        private static final String days = "天";
        private static final String to = "至";
        private static final String interval = "间隔天数";
        private JComboBox<Integer> comboBoxStartYear;
        private JComboBox<Integer> comboBoxStartMonth;
        private JComboBox<Integer> comboBoxStartDay;
        private JComboBox<Integer> comboBoxEndYear;
        private JComboBox<Integer> comboBoxEndMonth;
        private JComboBox<Integer> comboBoxEndDay;
        private JLabel labelBetweenFrom;
        private JLabel labelStartYear;
        private JLabel labelStartMonth;
        private JLabel labelStartDay;
        private JLabel labelTo;
        private JLabel labelEndYear;
        private JLabel labelEndMonth;
        private JLabel labelEndDay;
        private JLabel labelInterval;
        private JLabel labelIntervalYear;
        private JLabel labelIntervalMonth;
        private JLabel labelIntervalDay;
        private JLabel labelBetweenYear;
        private JLabel labelBetweenMonth;
        private JLabel labelBetweenDay;
        private JLabel textFieldTotalDay;
        private JLabel labelTotalDay;
        private JButton buttonCalc;

        /**
         * 初始化组件
         */
        @Override
        protected void initCompoment() {
            comboBoxStartYear = new JComboBox<>();
            comboBoxStartMonth = new JComboBox<>();
            comboBoxStartDay = new JComboBox<>();
            comboBoxEndYear = new JComboBox<>();
            comboBoxEndMonth = new JComboBox<>();
            comboBoxEndDay = new JComboBox<>();
            labelBetweenFrom = new JLabel(from);
            labelStartYear = new JLabel(year);
            labelStartMonth = new JLabel(month);
            labelStartDay = new JLabel(day);
            labelTo = new JLabel(to);
            labelEndYear = new JLabel(year);
            labelEndMonth = new JLabel(month);
            labelEndDay = new JLabel(day);
            labelInterval = new JLabel(interval);
            labelIntervalYear = new JLabel("", JLabel.CENTER);
            labelIntervalMonth = new JLabel("", JLabel.CENTER);
            labelIntervalDay = new JLabel("", JLabel.CENTER);
            labelBetweenYear = new JLabel(year);
            labelBetweenMonth = new JLabel(month);
            labelBetweenDay = new JLabel(day);
            textFieldTotalDay = new JLabel("", JLabel.CENTER);
            labelTotalDay = new JLabel(days);
            buttonCalc = new JButton("计算");
        }

        /**
         * 初始化布局
         */
        @Override
        protected void initUI() {
            int day;

            // 开始年份
            comboBoxStartYear.setBounds(97, 157, 65, 35);
            comboBoxStartYear.setBackground(normal);
            add(comboBoxStartYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxStartYear.addItem(i);
            }
            comboBoxStartYear.setSelectedItem(DateTime.now().getYear());

            // 开始月份
            comboBoxStartMonth.setBounds(187, 157, 65, 35);
            comboBoxStartMonth.setBackground(normal);
            add(comboBoxStartMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxStartMonth.addItem(i);
            }
            comboBoxStartMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            // 开始天数
            comboBoxStartDay.setBounds(280, 157, 65, 35);
            comboBoxStartDay.setBackground(normal);
            add(comboBoxStartDay);
            day = DateCalculate.setDay((int) comboBoxStartYear.getSelectedItem(), (int) comboBoxStartMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxStartDay.addItem(i);
            }
            comboBoxStartDay.setSelectedItem(DateTime.now().getDayOfMonth());

            // 结束年份
            comboBoxEndYear.setBounds(97, 244, 65, 35);
            comboBoxEndYear.setBackground(normal);
            add(comboBoxEndYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxEndYear.addItem(i);
            }
            comboBoxEndYear.setSelectedItem(DateTime.now().getYear());

            // 结束月份
            comboBoxEndMonth.setBounds(187, 244, 65, 35);
            comboBoxEndMonth.setBackground(normal);
            add(comboBoxEndMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxEndMonth.addItem(i);
            }
            comboBoxEndMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            // 结束天数
            comboBoxEndDay.setBounds(280, 244, 65, 35);
            comboBoxEndDay.setBackground(normal);
            add(comboBoxEndDay);
            day = DateCalculate.setDay((int) comboBoxEndYear.getSelectedItem(), (int) comboBoxEndMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxEndDay.addItem(i);
            }
            comboBoxEndDay.setSelectedItem(DateTime.now().getDayOfMonth());

            // 标签组********************************
            labelBetweenFrom.setFont(fontPlain);
            labelBetweenFrom.setBounds(97, 122, 42, 25);
            add(labelBetweenFrom);

            labelStartYear.setFont(fontBold);
            labelStartYear.setBounds(162, 166, 54, 15);
            add(labelStartYear);

            labelStartMonth.setFont(fontBold);
            labelStartMonth.setBounds(252, 166, 54, 15);
            add(labelStartMonth);

            labelStartDay.setFont(fontBold);
            labelStartDay.setBounds(345, 166, 54, 15);
            add(labelStartDay);

            labelTo.setFont(fontPlain);
            labelTo.setBounds(97, 212, 54, 15);
            add(labelTo);

            labelEndYear.setFont(fontBold);
            labelEndYear.setBounds(162, 253, 54, 15);
            add(labelEndYear);

            labelEndMonth.setFont(fontBold);
            labelEndMonth.setBounds(252, 253, 54, 15);
            add(labelEndMonth);

            labelEndDay.setFont(fontBold);
            labelEndDay.setBounds(345, 253, 54, 15);
            add(labelEndDay);

            labelInterval.setFont(fontPlain);
            labelInterval.setBounds(97, 301, 83, 25);
            add(labelInterval);

            labelIntervalYear.setBounds(97, 336, 45, 35);
            add(labelIntervalYear);


            labelIntervalMonth.setBounds(175, 336, 45, 35);
            add(labelIntervalMonth);

            labelIntervalDay.setBounds(250, 336, 45, 35);
            add(labelIntervalDay);

            // 这是最下面那四个
            labelBetweenYear.setFont(fontBold);
            labelBetweenYear.setBounds(152, 346, 54, 15);
            add(labelBetweenYear);

            // 这是最下面那四个
            labelBetweenMonth.setFont(fontBold);
            labelBetweenMonth.setBounds(223, 346, 54, 15);
            add(labelBetweenMonth);

            // 这是最下面那四个
            labelBetweenDay.setFont(fontBold);
            labelBetweenDay.setBounds(303, 346, 54, 15);
            add(labelBetweenDay);

            // 这是最下面那四个
            textFieldTotalDay.setBounds(99, 398, 45, 35);
            add(textFieldTotalDay);

            labelTotalDay.setFont(fontPlain);
            labelTotalDay.setBounds(152, 406, 54, 15);
            add(labelTotalDay);

            // 计算按钮
            buttonCalc.setFont(fontPlain);
            buttonCalc.setBackground(normal);
            buttonCalc.setBounds(223, 396, 75, 35);
            add(buttonCalc);
        }

        /**
         * 建立监听事件
         */
        @Override
        protected void createAction() {

            comboBoxStartDay.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    int day = DateCalculate.setDay((int) comboBoxStartYear.getSelectedItem(),
                            (int) comboBoxStartMonth.getSelectedItem());
                    comboBoxStartDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxStartDay.addItem(i);
                    }
                }
            });

            comboBoxEndDay.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    int day = DateCalculate.setDay((int) comboBoxEndYear.getSelectedItem(), (int) comboBoxEndMonth.getSelectedItem());
                    comboBoxEndDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxEndDay.addItem(i);
                    }
                }
            });

            buttonCalc.addActionListener(e -> {
                int startYear = (int) comboBoxStartYear.getSelectedItem();
                int startMonth = (int) comboBoxStartMonth.getSelectedItem();
                int startDay = (int) comboBoxStartDay.getSelectedItem();

                int endYear = (int) comboBoxEndYear.getSelectedItem();
                int endMonth = (int) comboBoxEndMonth.getSelectedItem();
                int endDay = (int) comboBoxEndDay.getSelectedItem();

                DateCalculate dateCalculate = new DateCalculate(startYear, startMonth, startDay, endYear, endMonth, endDay);
                labelIntervalDay.setText(dateCalculate.getIntervalDay());
                labelIntervalMonth.setText(dateCalculate.getIntervalMonth());
                labelIntervalYear.setText(dateCalculate.getIntervalYear());
                textFieldTotalDay.setText(dateCalculate.getTotalDay());
            });

        }

        /**
         * 日期距离的UI初始化
         */
        IntervalPanel() {
            super();
        }
    }

    /**
     * 日期增减类
     * 从一个已知日期增减一定的日期，来获得目标日期
     */
    private class PlusOrMinusPanel extends TransparentPanelUI {


        private static final long serialVersionUID = -2918669599707881570L;
        private static final String from = "自";
        private static final String year = "年，";
        private static final String month = "月，";
        private static final String day = "日";
        private static final String date = "日期";
        private static final String calc = "计算";
        private static final String add = "添加";
        private static final String minus = "减去";
        private boolean isAdd = true;
        private JLabel labelFrom;
        private JLabel labelYear;
        private JLabel labelMonth;
        private JLabel labelDay;
        private JLabel labelDate;
        private JRadioButton radioButtonAdd;
        private JRadioButton radioButtonMinus;
        private ButtonGroup group;
        private JLabel labelFromYear;
        private JLabel labelFromMonth;
        private JLabel labelFromDay;
        private JComboBox<Integer> comboBoxAddMonth;
        private JComboBox<Integer> comboBoxAddDay;
        private JComboBox<Integer> comboBoxAddYear;
        private JComboBox<Integer> comboBoxStartYear;
        private JComboBox<Integer> comboBoxStartMonth;
        private JComboBox<Integer> comboBoxStartDay;
        private JLabel plusOrMinusYear;
        private JLabel plusOrMinusMonth;
        private JLabel plusOrMinusDay;
        private JLabel labelyear;
        private JLabel labelmonth;
        private JLabel labelday;
        private JButton buttonCalc;

        /**
         * 初始化组件
         */
        @Override
        protected void initCompoment() {
            labelFrom = new JLabel(from);
            labelYear = new JLabel(year);
            labelMonth = new JLabel(month);
            labelDay = new JLabel(day);
            labelDate = new JLabel(date);
            radioButtonAdd = new JRadioButton(add, true);
            radioButtonMinus = new JRadioButton(minus);
            group = new ButtonGroup();
            group.add(radioButtonAdd);
            group.add(radioButtonMinus);
            labelFromYear = new JLabel(year);
            labelFromMonth = new JLabel(month);
            labelFromDay = new JLabel(day);
            comboBoxAddMonth = new JComboBox<>();
            comboBoxAddDay = new JComboBox<>();
            comboBoxAddYear = new JComboBox<>();
            comboBoxStartYear = new JComboBox<>();
            comboBoxStartMonth = new JComboBox<>();
            comboBoxStartDay = new JComboBox<>();
            plusOrMinusYear = new JLabel("", JLabel.CENTER);
            plusOrMinusMonth = new JLabel("", JLabel.CENTER);
            plusOrMinusDay = new JLabel("", JLabel.CENTER);
            labelyear = new JLabel(year);
            labelmonth = new JLabel(month);
            labelday = new JLabel(day);
            buttonCalc = new JButton(calc);
        }

        /**
         * 初始化布局
         */
        @Override
        protected void initUI() {
            labelFrom.setFont(fontPlain);
            labelFrom.setBounds(97, 122, 42, 25);
            add(labelFrom);

            labelYear.setFont(fontBold);
            labelYear.setBounds(162, 274, 54, 15);
            add(labelYear);

            labelMonth.setFont(fontBold);
            labelMonth.setBounds(251, 274, 54, 15);
            add(labelMonth);

            labelDay.setFont(fontBold);
            labelDay.setBounds(345, 274, 54, 15);
            add(labelDay);

            labelDate.setFont(fontPlain);
            labelDate.setBounds(97, 336, 54, 15);
            add(labelDate);

            radioButtonAdd.setSelected(true);
            radioButtonAdd.setFont(fontPlain);
            radioButtonAdd.setBounds(162, 219, 87, 23);
            add(radioButtonAdd);

            radioButtonMinus.setFont(fontPlain);
            radioButtonMinus.setBounds(251, 219, 121, 23);
            add(radioButtonMinus);

            labelFromYear.setFont(fontBold);
            labelFromYear.setBounds(162, 166, 54, 15);
            add(labelFromYear);

            labelFromMonth.setFont(fontBold);
            labelFromMonth.setBounds(252, 166, 54, 15);
            add(labelFromMonth);

            labelFromDay.setFont(fontBold);
            labelFromDay.setBounds(345, 166, 54, 15);
            add(labelFromDay);

            comboBoxAddMonth.setBounds(187, 265, 65, 35);
            comboBoxAddMonth.setBackground(normal);
            add(comboBoxAddMonth);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddMonth.addItem(i);
            }

            comboBoxAddDay.setBounds(280, 265, 65, 35);
            comboBoxAddDay.setBackground(normal);
            add(comboBoxAddDay);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddDay.addItem(i);
            }

            comboBoxAddYear.setBounds(97, 265, 65, 35);
            comboBoxAddYear.setBackground(normal);
            add(comboBoxAddYear);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddYear.addItem(i);
            }

            comboBoxStartYear.setBounds(97, 157, 65, 35);
            comboBoxStartYear.setBackground(normal);
            add(comboBoxStartYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxStartYear.addItem(i);
            }
            comboBoxStartYear.setSelectedItem(DateTime.now().getYear());

            comboBoxStartMonth.setBounds(187, 157, 65, 35);
            comboBoxStartMonth.setBackground(normal);
            add(comboBoxStartMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxStartMonth.addItem(i);
            }
            comboBoxStartMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            comboBoxStartDay.setBounds(280, 157, 65, 35);
            comboBoxStartDay.setBackground(normal);
            add(comboBoxStartDay);
            int day = DateCalculate.setDay((int) comboBoxStartYear.getSelectedItem(), (int) comboBoxStartMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxStartDay.addItem(i);
            }
            comboBoxStartDay.setSelectedItem(DateTime.now().getDayOfMonth());

            // 这个是最下面那四个或三个
            plusOrMinusYear.setBackground(new Color(224, 255, 255));
            plusOrMinusYear.setBounds(97, 368, 65, 35);
            add(plusOrMinusYear);

            // 这个是最下面那四个或三个
            plusOrMinusMonth.setBackground(new Color(224, 255, 255));
            plusOrMinusMonth.setBounds(187, 368, 65, 35);
            add(plusOrMinusMonth);

            // 这个是最下面那四个或三个
            plusOrMinusDay.setBackground(new Color(224, 255, 255));
            plusOrMinusDay.setBounds(280, 368, 65, 35);
            add(plusOrMinusDay);

            labelyear.setFont(fontBold);
            labelyear.setBounds(162, 377, 54, 15);
            add(labelyear);

            labelmonth.setFont(fontBold);
            labelmonth.setBounds(251, 377, 54, 15);
            add(labelmonth);

            labelday.setFont(fontBold);
            labelday.setBounds(345, 377, 54, 15);
            add(labelday);

            buttonCalc.setFont(fontPlain);
            buttonCalc.setBackground(normal);
            buttonCalc.setBounds(197, 326, 75, 35);
            add(buttonCalc);
        }

        /**
         * 建立监听事件
         */
        @Override
        protected void createAction() {
            radioButtonAdd.addActionListener(e -> isAdd = true);
            radioButtonMinus.addActionListener(e -> isAdd = false);

            comboBoxStartDay.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    int day = DateCalculate.setDay((int) comboBoxStartYear.getSelectedItem(), (int) comboBoxStartMonth.getSelectedItem());
                    comboBoxStartDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxStartDay.addItem(i);
                    }
                }
            });

            buttonCalc.addActionListener(e -> {
                int startYear = (int) comboBoxStartYear.getSelectedItem();
                int startMonth = (int) comboBoxStartMonth.getSelectedItem();
                int startDay = (int) comboBoxStartDay.getSelectedItem();

                int year = (int) comboBoxAddYear.getSelectedItem();
                int month = (int) comboBoxAddMonth.getSelectedItem();
                int days = (int) comboBoxAddDay.getSelectedItem();

                DateCalculate dateCalculate = new DateCalculate(startYear, startMonth, startDay, year, month, days, isAdd);
                plusOrMinusDay.setText(dateCalculate.getEndDay());
                plusOrMinusMonth.setText(dateCalculate.getEndMonth());
                plusOrMinusYear.setText(dateCalculate.getEndYear());
            });
        }

        /**
         * 日期增减页面的UI构造
         */
        PlusOrMinusPanel() {
            super();
        }
    }

}