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
 * ���ڼ�������֧�����ڼ�����㼰����������������
 * ��ʾ������������ʱ��
 * ��֧����ʷ��¼
 *
 * @author ��룬�������������
 */
class CalendarUI extends TransparentPanelUI {

    private static final long serialVersionUID = -4791068421614363948L;
    private static final String interval = "����֮������ʱ��";
    private static final String addAndMinusDay = "��ӻ��ȥ����";
    private static final Font fontPlain = new Font("΢���ź�", Font.PLAIN, 14);
    private static final Font fontBold = new Font("΢���ź�", Font.BOLD, 14);
    private static final Color normal = new Color(245, 255, 255);
    private JComboBox<String> comboBoxYear;
    private JPanel panelPlusOrMinus;
    private JPanel panelInterval;


    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        comboBoxYear = new JComboBox<>();
        panelPlusOrMinus = new PlusOrMinusPanel();
        panelInterval = new IntervalPanel();
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {
        comboBoxYear.setBackground(normal);
        comboBoxYear.setBounds(97, 72, 160, 40);
        comboBoxYear.setFont(new Font("΢���ź�", Font.BOLD, 13));
        comboBoxYear.addItem(interval);
        comboBoxYear.addItem(addAndMinusDay);
        comboBoxYear.setVisible(true);
        add(comboBoxYear);

        //��������
        panelPlusOrMinus.setOpaque(false);
        panelPlusOrMinus.setLayout(null);
        panelPlusOrMinus.setVisible(true);
        panelPlusOrMinus.setBounds(0, 0, 590, 509);
        add(panelPlusOrMinus);

        //���ڼ��
        panelInterval.setOpaque(false);
        panelInterval.setLayout(null);
        panelPlusOrMinus.setVisible(false);
        panelInterval.setBounds(0, 0, 600, 509);
        add(panelInterval);
    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {
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
     * �������ڼ����UI�������
     */
    CalendarUI() {
        super();
    }


    /**
     * ���������趨����
     *
     * @param year  ���
     * @param month �·�
     * @return ����
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
                throw new IllegalArgumentException("���ڷǷ�");
        }
        return day;
    }

    /**
     * �ж�һ������Ƿ�������
     *
     * @param year ���жϵ����
     * @return true ���������
     */
    private boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    /**
     * ���ھ�����
     * ����������ڵļ�����ڼ�������
     */
    private class IntervalPanel extends TransparentPanelUI {


        private static final long serialVersionUID = 1851775115929217134L;
        private static final String from = "��";
        private static final String year = "�꣬";
        private static final String month = "�£�";
        private static final String day = "��";
        private static final String days = "��";
        private static final String to = "��";
        private static final String interval = "�������";
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
        private JButton buttonClac;

        /**
         * ��ʼ�����
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
            buttonClac = new JButton("����");
        }

        /**
         * ��ʼ������
         */
        @Override
        protected void initUI() {
            // ��ʼ���
            comboBoxStartYear.setBounds(97, 157, 65, 35);
            comboBoxStartYear.setBackground(normal);
            add(comboBoxStartYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxStartYear.addItem(i);
            }
            comboBoxStartYear.setSelectedItem(DateTime.now().getYear());

            // ��ʼ�·�
            comboBoxStartMonth.setBounds(187, 157, 65, 35);
            comboBoxStartMonth.setBackground(normal);
            add(comboBoxStartMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxStartMonth.addItem(i);
            }
            comboBoxStartMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            // ��ʼ����
            comboBoxStartDay.setBounds(280, 157, 65, 35);
            comboBoxStartDay.setBackground(normal);
            add(comboBoxStartDay);

            // �������
            comboBoxEndYear.setBounds(97, 244, 65, 35);
            comboBoxEndYear.setBackground(normal);
            add(comboBoxEndYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxEndYear.addItem(i);
            }
            comboBoxEndYear.setSelectedItem(DateTime.now().getYear());

            // �����·�
            comboBoxEndMonth.setBounds(187, 244, 65, 35);
            comboBoxEndMonth.setBackground(normal);
            add(comboBoxEndMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxEndMonth.addItem(i);
            }
            comboBoxEndMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            // ��������
            comboBoxEndDay.setBounds(280, 244, 65, 35);
            comboBoxEndDay.setBackground(normal);
            add(comboBoxEndDay);

            // ��ǩ��********************************
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

            // �������������ĸ�
            labelBetweenYear.setFont(fontBold);
            labelBetweenYear.setBounds(152, 346, 54, 15);
            add(labelBetweenYear);

            // �������������ĸ�
            labelBetweenMonth.setFont(fontBold);
            labelBetweenMonth.setBounds(223, 346, 54, 15);
            add(labelBetweenMonth);

            // �������������ĸ�
            labelBetweenDay.setFont(fontBold);
            labelBetweenDay.setBounds(303, 346, 54, 15);
            add(labelBetweenDay);

            // �������������ĸ�
            textFieldTotalDay.setBounds(99, 398, 45, 35);
            add(textFieldTotalDay);

            labelTotalDay.setFont(fontPlain);
            labelTotalDay.setBounds(152, 406, 54, 15);
            add(labelTotalDay);

            // ���㰴ť
            buttonClac.setFont(fontPlain);
            buttonClac.setBackground(normal);
            buttonClac.setBounds(223, 396, 75, 35);
            add(buttonClac);
        }

        /**
         * ���������¼�
         */
        @Override
        protected void createAction() {
            int day;

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
            day = setDay((int) comboBoxStartYear.getSelectedItem(), (int) comboBoxStartMonth.getSelectedItem());
            for (int i = 1; i <= day; i++) {
                comboBoxStartDay.addItem(i);
            }
            comboBoxStartDay.setSelectedItem(DateTime.now().getDayOfMonth());

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

        /**
         * ���ھ����UI��ʼ��
         */
        IntervalPanel() {
            super();
        }
    }

    /**
     * ����������
     * ��һ����֪��������һ�������ڣ������Ŀ������
     */
    private class PlusOrMinusPanel extends TransparentPanelUI {


        private static final long serialVersionUID = -2918669599707881570L;
        private static final String from = "��";
        private static final String year = "�꣬";
        private static final String month = "�£�";
        private static final String day = "��";
        private static final String date = "����";
        private static final String calc = "����";
        private static final String add = "���";
        private static final String minus = "��ȥ";
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
        private JComboBox<Integer> comboBoxFromYear;
        private JComboBox<Integer> comboBoxFromMonth;
        private JComboBox<Integer> comboBoxFromDay;
        private JLabel plusOrMinusYear;
        private JLabel plusOrMinusMonth;
        private JLabel plusOrMinusDay;
        private JLabel labelyear;
        private JLabel labelmonth;
        private JLabel labelday;
        private JButton buttonCalc;

        /**
         * ��ʼ�����
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
            comboBoxFromYear = new JComboBox<>();
            comboBoxFromMonth = new JComboBox<>();
            comboBoxFromDay = new JComboBox<>();
            plusOrMinusYear = new JLabel("", JLabel.CENTER);
            plusOrMinusMonth = new JLabel("", JLabel.CENTER);
            plusOrMinusDay = new JLabel("", JLabel.CENTER);
            labelyear = new JLabel(year);
            labelmonth = new JLabel(month);
            labelday = new JLabel(day);
            buttonCalc = new JButton(calc);
        }

        /**
         * ��ʼ������
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

            comboBoxFromYear.setBounds(97, 157, 65, 35);
            comboBoxFromYear.setBackground(normal);
            add(comboBoxFromYear);
            for (int i = 1970; i < 3000; i++) {
                comboBoxFromYear.addItem(i);
            }
            comboBoxFromYear.setSelectedItem(DateTime.now().getYear());

            comboBoxFromMonth.setBounds(187, 157, 65, 35);
            comboBoxFromMonth.setBackground(normal);
            add(comboBoxFromMonth);
            for (int i = 1; i <= 12; i++) {
                comboBoxFromMonth.addItem(i);
            }
            comboBoxFromMonth.setSelectedItem(DateTime.now().getMonthOfYear());

            comboBoxFromDay.setBounds(280, 157, 65, 35);
            comboBoxFromDay.setBackground(normal);
            add(comboBoxFromDay);

            // ��������������ĸ�������
            plusOrMinusYear.setBackground(new Color(224, 255, 255));
            plusOrMinusYear.setBounds(97, 368, 65, 35);
            add(plusOrMinusYear);

            // ��������������ĸ�������
            plusOrMinusMonth.setBackground(new Color(224, 255, 255));
            plusOrMinusMonth.setBounds(187, 368, 65, 35);
            add(plusOrMinusMonth);

            // ��������������ĸ�������
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
         * ���������¼�
         */
        @Override
        protected void createAction() {
            radioButtonAdd.addActionListener(e -> isAdd = true);
            radioButtonMinus.addActionListener(e -> isAdd = false);

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

        /**
         * ��������ҳ���UI����
         */
        PlusOrMinusPanel() {
            super();
        }
    }

}
