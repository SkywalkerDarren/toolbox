package com.ToolBox.UI;

import com.ToolBox.date.DateCalculate;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;

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
class CalendarUI extends JPanel {

    private static final long serialVersionUID = -4791068421614363948L;
    private final Font fontPlain = new Font("微软雅黑", Font.PLAIN, 14);
    private final Font fontBold = new Font("微软雅黑", Font.BOLD, 14);
    private final Color normal = new Color(245, 255, 255);

    /**
     * 构建日期计算的UI基本框架
     */
    CalendarUI() {


        setOpaque(false);
        JComboBox<String> comboBoxYear = new JComboBox<>();
        add(comboBoxYear);
        comboBoxYear.setBackground(normal);
        comboBoxYear.setBounds(97, 72, 160, 40);
        comboBoxYear.setFont(new Font("微软雅黑", Font.BOLD, 13));
        comboBoxYear.addItem("日期之间的相隔时间");
        comboBoxYear.addItem("添加或减去天数");
        comboBoxYear.setVisible(true);


        //日期增减
        JPanel panelPlusOrMinus = new PlusOrMinusPanel();
        panelPlusOrMinus.setOpaque(false);
        panelPlusOrMinus.setLayout(null);
        panelPlusOrMinus.setVisible(true);
        panelPlusOrMinus.setBounds(0, 0, 590, 509);
        add(panelPlusOrMinus);


        //日期间隔
        JPanel panelInterval = new IntervalPanel();
        panelInterval.setOpaque(false);
        panelInterval.setLayout(null);
        panelPlusOrMinus.setVisible(false);
        panelInterval.setBounds(0, 0, 600, 509);
        add(panelInterval);

        comboBoxYear.addActionListener(e -> {
            if (e.getSource() == comboBoxYear) {

                int index = comboBoxYear.getSelectedIndex();
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
     * 根据年月设定日期
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    private int setDay(int year, int month) {
        int day;
        switch (month) {
            case 1:
                day = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            case 3:
                day = 31;
                break;
            case 4:
                day = 30;
                break;
            case 5:
                day = 31;
                break;
            case 6:
                day = 30;
                break;
            case 7:
                day = 31;
                break;
            case 8:
                day = 31;
                break;
            case 9:
                day = 30;
                break;
            case 10:
                day = 31;
                break;
            case 11:
                day = 30;
                break;
            case 12:
                day = 31;
                break;
            default:
                throw new IllegalArgumentException("日期非法");
        }
        return day;
    }

    /**
     * 判断一个年份是否是闰年
     *
     * @param year 需判断的年份
     * @return true 如果是闰年
     */
    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    /**
     * 日期距离类
     * 获得两个日期的间隔日期及总天数
     */
    private class IntervalPanel extends JPanel {


        private static final long serialVersionUID = 1851775115929217134L;

        /**
         * 日期距离的UI初始化
         */
        IntervalPanel() {
            JComboBox<Integer> comboBoxStartYear = new JComboBox<>();
            comboBoxStartYear.setBounds(97, 157, 65, 35);
            comboBoxStartYear.setBackground(normal);
            add(comboBoxStartYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxStartYear.addItem(i);
            }
            comboBoxStartYear.setSelectedItem(DateTime.now().getYear());

            JComboBox<Integer> comboBoxStartMonth = new JComboBox<>();
            comboBoxStartMonth.setBounds(187, 157, 65, 35);
            comboBoxStartMonth.setBackground(normal);
            add(comboBoxStartMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxStartMonth.addItem(i);
            }
            comboBoxStartMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            JComboBox<Integer> comboBoxStartDay = new JComboBox<>();
            comboBoxStartDay.setBounds(280, 157, 65, 35);
            comboBoxStartDay.setBackground(normal);
            add(comboBoxStartDay);
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
                    int day = setDay((int) comboBoxStartYear.getSelectedItem(),
                            (int) comboBoxStartMonth.getSelectedItem());
                    comboBoxStartDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxStartDay.addItem(i);
                    }
                }
            });
            int day = setDay((int) comboBoxStartYear.getSelectedItem(), (int) comboBoxStartMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxStartDay.addItem(i);
            }
            comboBoxStartDay.setSelectedItem(DateTime.now().getDayOfMonth());

            JComboBox<Integer> comboBoxEndYear = new JComboBox<>();
            comboBoxEndYear.setBounds(97, 244, 65, 35);
            comboBoxEndYear.setBackground(normal);
            add(comboBoxEndYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxEndYear.addItem(i);
            }
            comboBoxEndYear.setSelectedItem(DateTime.now().getYear());


            JComboBox<Integer> comboBoxEndMonth = new JComboBox<>();
            comboBoxEndMonth.setBounds(187, 244, 65, 35);
            comboBoxEndMonth.setBackground(normal);
            add(comboBoxEndMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxEndMonth.addItem(i);
            }
            comboBoxEndMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            JComboBox<Integer> comboBoxEndDay = new JComboBox<>();
            comboBoxEndDay.setBounds(280, 244, 65, 35);
            comboBoxEndDay.setBackground(normal);
            add(comboBoxEndDay);
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
                    int day = setDay((int) comboBoxEndYear.getSelectedItem(), (int) comboBoxEndMonth.getSelectedItem());
                    comboBoxEndDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxEndDay.addItem(i);
                    }
                }
            });
            day = setDay((int) comboBoxEndYear.getSelectedItem(), (int) comboBoxEndMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxEndDay.addItem(i);
            }
            comboBoxEndDay.setSelectedItem(DateTime.now().getDayOfMonth());

            JLabel labelBetweenFrom = new JLabel("自");
            labelBetweenFrom.setFont(fontPlain);

            labelBetweenFrom.setBounds(97, 122, 42, 25);
            add(labelBetweenFrom);

            JLabel labelStartYear = new JLabel("年，");
            labelStartYear.setFont(fontBold);
            labelStartYear.setBounds(162, 166, 54, 15);
            add(labelStartYear);

            JLabel labelStartMonth = new JLabel("月，");
            labelStartMonth.setFont(fontBold);
            labelStartMonth.setBounds(252, 166, 54, 15);
            add(labelStartMonth);

            JLabel labelStartDay = new JLabel("日");
            labelStartDay.setFont(fontBold);
            labelStartDay.setBounds(345, 166, 54, 15);
            add(labelStartDay);

            JLabel labelTo = new JLabel("至");
            labelTo.setFont(fontPlain);
            labelTo.setBounds(97, 212, 54, 15);
            add(labelTo);

            JLabel labelEndYear = new JLabel("年，");
            labelEndYear.setFont(fontBold);
            labelEndYear.setBounds(162, 253, 54, 15);
            add(labelEndYear);

            JLabel labelEndMonth = new JLabel("月，");
            labelEndMonth.setFont(fontBold);
            labelEndMonth.setBounds(252, 253, 54, 15);
            add(labelEndMonth);

            JLabel labelEndDay = new JLabel("日");
            labelEndDay.setFont(fontBold);
            labelEndDay.setBounds(345, 253, 54, 15);
            add(labelEndDay);

            JLabel labelInterval = new JLabel("间隔天数");
            labelInterval.setFont(fontPlain);
            labelInterval.setBounds(97, 301, 83, 25);
            add(labelInterval);

            JLabel labelIntervalYear = new JLabel("", JLabel.CENTER);
            labelIntervalYear.setBounds(97, 336, 45, 35);
            add(labelIntervalYear);

            JLabel labelIntervalMonth = new JLabel("", JLabel.CENTER);
            labelIntervalMonth.setBounds(175, 336, 45, 35);
            add(labelIntervalMonth);

            JLabel labelIntervalDay = new JLabel("", JLabel.CENTER);
            labelIntervalDay.setBounds(250, 336, 45, 35);
            add(labelIntervalDay);

            // 这是最下面那四个
            JLabel labelBetweenYear = new JLabel("年，");
            labelBetweenYear.setFont(fontBold);
            labelBetweenYear.setBounds(152, 346, 54, 15);
            add(labelBetweenYear);

            // 这是最下面那四个
            JLabel labelBetweenMonth = new JLabel("月，");
            labelBetweenMonth.setFont(fontBold);
            labelBetweenMonth.setBounds(223, 346, 54, 15);
            add(labelBetweenMonth);

            // 这是最下面那四个
            JLabel labelBetweenDay = new JLabel("日");
            labelBetweenDay.setFont(fontBold);
            labelBetweenDay.setBounds(303, 346, 54, 15);
            add(labelBetweenDay);

            // 这是最下面那四个
            JLabel textFieldTotalDay = new JLabel("", JLabel.CENTER);
            textFieldTotalDay.setBounds(99, 398, 45, 35);
            add(textFieldTotalDay);

            JLabel labelTotalDay = new JLabel("天");
            labelTotalDay.setFont(fontPlain);
            labelTotalDay.setBounds(152, 406, 54, 15);
            add(labelTotalDay);

            JButton buttonClac = new JButton("计算");
            buttonClac.setFont(fontPlain);
            buttonClac.setBackground(normal);
            buttonClac.setBounds(223, 396, 75, 35);
            add(buttonClac);
            buttonClac.addActionListener(e -> {
                DateTime start = new DateTime((int) comboBoxStartYear.getSelectedItem(),
                        (int) comboBoxStartMonth.getSelectedItem(), (int) comboBoxStartDay.getSelectedItem(), 0, 0);
                DateTime end = new DateTime((int) comboBoxEndYear.getSelectedItem(),
                        (int) comboBoxEndMonth.getSelectedItem(), (int) comboBoxEndDay.getSelectedItem(), 0, 0);

                Period period = new DateCalculate(start, end).getIntervalDate();
                labelIntervalDay.setText(period.getDays() + period.getWeeks() * 7 + "");
                labelIntervalMonth.setText(period.getMonths() + "");
                labelIntervalYear.setText(period.getYears() + "");

                int day1 = Days.daysBetween(start, end).getDays();
                textFieldTotalDay.setText(Math.abs(day1) + "");
            });

        }
    }

    /**
     * 日期增减类
     * 从一个已知日期增减一定的日期，来获得目标日期
     */
    private class PlusOrMinusPanel extends JPanel {


        private static final long serialVersionUID = -2918669599707881570L;
        private boolean isAdd = true;

        /**
         * 日期增减页面的UI构造
         */
        PlusOrMinusPanel() {
            JLabel labelFrom = new JLabel("自");
            labelFrom.setFont(fontPlain);
            labelFrom.setBounds(97, 122, 42, 25);
            add(labelFrom);

            JLabel labelYear = new JLabel("年，");
            labelYear.setFont(fontBold);
            labelYear.setBounds(162, 274, 54, 15);
            add(labelYear);

            JLabel labelMonth = new JLabel("月，");
            labelMonth.setFont(fontBold);
            labelMonth.setBounds(251, 274, 54, 15);
            add(labelMonth);

            JLabel labelDay = new JLabel("日");
            labelDay.setFont(fontBold);
            labelDay.setBounds(345, 274, 54, 15);
            add(labelDay);

            JLabel labelDate = new JLabel("日期");
            labelDate.setFont(fontPlain);
            labelDate.setBounds(97, 336, 54, 15);
            add(labelDate);

            JRadioButton radioButtonAdd = new JRadioButton("添加", true);
            radioButtonAdd.setSelected(true);
            radioButtonAdd.setFont(fontPlain);
            radioButtonAdd.setBounds(162, 219, 87, 23);
            add(radioButtonAdd);
            radioButtonAdd.addActionListener(e -> isAdd = true);

            JRadioButton radioButtonMinus = new JRadioButton("减去");
            radioButtonMinus.setFont(fontPlain);
            radioButtonMinus.setBounds(251, 219, 121, 23);
            add(radioButtonMinus);
            radioButtonMinus.addActionListener(e -> isAdd = false);

            ButtonGroup group = new ButtonGroup();
            group.add(radioButtonAdd);
            group.add(radioButtonMinus);

            JLabel labelFromYear = new JLabel("年，");
            labelFromYear.setFont(fontBold);
            labelFromYear.setBounds(162, 166, 54, 15);
            add(labelFromYear);

            JLabel labelFromMonth = new JLabel("月，");
            labelFromMonth.setFont(fontBold);
            labelFromMonth.setBounds(252, 166, 54, 15);
            add(labelFromMonth);

            JLabel labelFromDay = new JLabel("日");
            labelFromDay.setFont(fontBold);
            labelFromDay.setBounds(345, 166, 54, 15);
            add(labelFromDay);

            JComboBox<Integer> comboBoxAddMonth = new JComboBox<>();
            comboBoxAddMonth.setBounds(187, 265, 65, 35);
            comboBoxAddMonth.setBackground(normal);
            add(comboBoxAddMonth);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddMonth.addItem(i);
            }

            JComboBox<Integer> comboBoxAddDay = new JComboBox<>();
            comboBoxAddDay.setBounds(280, 265, 65, 35);
            comboBoxAddDay.setBackground(normal);
            add(comboBoxAddDay);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddDay.addItem(i);
            }

            JComboBox<Integer> comboBoxAddYear = new JComboBox<>();
            comboBoxAddYear.setBounds(97, 265, 65, 35);
            comboBoxAddYear.setBackground(normal);
            add(comboBoxAddYear);
            for (int i = 0; i < 1000; i++) {
                comboBoxAddYear.addItem(i);
            }

            JComboBox<Integer> comboBoxFromYear = new JComboBox<>();
            comboBoxFromYear.setBounds(97, 157, 65, 35);
            comboBoxFromYear.setBackground(normal);
            add(comboBoxFromYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxFromYear.addItem(i);
            }
            comboBoxFromYear.setSelectedItem(DateTime.now().getYear());

            JComboBox<Integer> comboBoxFromMonth = new JComboBox<>();
            comboBoxFromMonth.setBounds(187, 157, 65, 35);
            comboBoxFromMonth.setBackground(normal);
            add(comboBoxFromMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxFromMonth.addItem(i);
            }
            comboBoxFromMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            JComboBox<Integer> comboBoxFromDay = new JComboBox<>();
            comboBoxFromDay.setBounds(280, 157, 65, 35);
            comboBoxFromDay.setBackground(normal);
            add(comboBoxFromDay);
            comboBoxFromDay.addMouseListener(new MouseListener() {

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
                    int day = setDay((int) comboBoxAddYear.getSelectedItem(), (int) comboBoxFromMonth.getSelectedItem());
                    comboBoxFromDay.removeAllItems();
                    for (int i = 1; i <= day; i++) {
                        comboBoxFromDay.addItem(i);
                    }
                }
            });
            int day = setDay((int) comboBoxFromYear.getSelectedItem(), (int) comboBoxFromMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxFromDay.addItem(i);
            }
            comboBoxFromDay.setSelectedItem(DateTime.now().getDayOfMonth());

            // 这个是最下面那四个或三个
            JLabel plusOrMinusYear = new JLabel("", JLabel.CENTER);
            plusOrMinusYear.setBackground(new Color(224, 255, 255));
            plusOrMinusYear.setBounds(97, 368, 65, 35);
            add(plusOrMinusYear);

            // 这个是最下面那四个或三个
            JLabel plusOrMinusMonth = new JLabel("", JLabel.CENTER);
            plusOrMinusMonth.setBackground(new Color(224, 255, 255));
            plusOrMinusMonth.setBounds(187, 368, 65, 35);
            add(plusOrMinusMonth);

            // 这个是最下面那四个或三个
            JLabel plusOrMinusDay = new JLabel("", JLabel.CENTER);
            plusOrMinusDay.setBackground(new Color(224, 255, 255));
            plusOrMinusDay.setBounds(280, 368, 65, 35);
            add(plusOrMinusDay);

            JLabel labelyear = new JLabel("年，");
            labelyear.setFont(fontBold);
            labelyear.setBounds(162, 377, 54, 15);
            add(labelyear);

            JLabel labelmonth = new JLabel("月，");
            labelmonth.setFont(fontBold);
            labelmonth.setBounds(251, 377, 54, 15);
            add(labelmonth);

            JLabel labelday = new JLabel("日");
            labelday.setFont(fontBold);
            labelday.setBounds(345, 377, 54, 15);
            add(labelday);

            JButton buttonCalc = new JButton("计算");
            buttonCalc.setFont(fontPlain);
            buttonCalc.setBackground(normal);
            buttonCalc.setBounds(197, 326, 75, 35);
            add(buttonCalc);
            buttonCalc.addActionListener(e -> {
                DateTime start = new DateTime((int) comboBoxFromYear.getSelectedItem(),
                        (int) comboBoxFromMonth.getSelectedItem(), (int) comboBoxFromDay.getSelectedItem(), 0, 0);
                Period period = new Period((int) comboBoxAddYear.getSelectedItem(),
                        (int) comboBoxAddMonth.getSelectedItem(), 0, (int) comboBoxAddDay.getSelectedItem(), 0, 0,
                        0, 0);
                DateTime target;
                if (isAdd) {
                    target = start.plus(period);
                } else {
                    target = start.minus(period);
                }
                plusOrMinusDay.setText(target.getDayOfMonth() + "");
                plusOrMinusMonth.setText(target.getMonthOfYear() + "");
                plusOrMinusYear.setText(target.getYear() + "");
            });


        }
    }

}
