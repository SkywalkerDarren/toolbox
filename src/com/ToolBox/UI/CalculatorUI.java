package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;

/**
 * һ��ȫ�ܵĿ�ѧ��������������ʷ��¼���ܣ�֧�ֽ���ı��棬���ü����ƣ��ɽ��и��ӵĴ����ȼ��ĸ߾��ȼ��㣬
 * ����Ա�����ṩ�����ƣ��˽��ƣ�ʮ���ƣ�ʮ�����ƵĻ��㣬�Լ�����λ���㣬
 * ������ͨ�����ؼ���ֱ�Ӳ��ݶ�������ֵ��֧�ִ����ȼ��ļ��㣬֧��ʮ���Ƽ��������棬���ü����ơ�
 * ���ʼ������ɴ�������ȡ���»��ʣ�Ĭ��ʹ�����õĻ��ʣ�����������ʾ��ǰ���Ҷһ����ʣ���֧�ֽ���ı��棬���ü����ơ�
 * ����������ɸ������Ͷ�һ����λ���㵽��һ����λ��֧�ֽ�����棬���ü����ơ�
 * ���ڼ���������������ɣ�һ�������ڼ����㣬��һ�����ڵ���һ�����ڵ��������Լ����ڣ���һ�����Ƕ����ڵ�������
 * ѡ��һ�����ڣ���ѡ������������������������������ڡ�
 *
 * @author ��룬�������������
 */
class CalculatorUI extends TransparentPanelUI {

    private static final long serialVersionUID = -7067036135688239326L;
    private static final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private static final String scientifc = "��ѧ������";
    private static final String exchange = "���ʼ�����";
    private static final String programer = "����Ա����";
    private static final String convertion = "��λ������";
    private static final String calendar = "����������";
    private JPanel panelCalculator;
    private JPanel panelExchangeRate;
    private JPanel panelKeyBoard;
    private JPanel panelConversion;
    private JPanel panelCalendar;
    private JPanel panelHistory;

    /**
     * ��ʼ�����
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
     * ��ʼ������
     */
    @Override
    protected void initUI() {

        // �����ǩҳ
        tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 597, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // ��ѧ��������ǩҳ
        panelCalculator.setLayout(null);
        tabbedPane.addTab(scientifc, null, panelCalculator, null);

        // ���ʼ�������ǩҳ
        panelExchangeRate.setLayout(null);
        tabbedPane.addTab(exchange, null, panelExchangeRate, null);

        // ����Ա���̱�ǩҳ
        tabbedPane.addTab(programer, null, panelKeyBoard, null);
        panelKeyBoard.setLayout(null);

        // ��λ��������ǩҳ
        panelConversion.setLayout(null);
        tabbedPane.addTab(convertion, null, panelConversion, null);

        // ������������ǩҳ
        panelCalendar.setLayout(null);
        tabbedPane.addTab(calendar, null, panelCalendar, null);

        // ��ʷ��¼
        panelHistory.setBorder(BorderFactory.createTitledBorder(""));
        panelHistory.setBounds(595, -13, 199, 566);
        panelHistory.setLayout(null);
        add(panelHistory);
    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {

    }

    /**
     * ��ʼ����ǩҳ
     * ��ӿ�ѧ��������ǩҳ�����ʼ�������ǩҳ��
     * ����Ա���̱�ǩҳ����λ��������ǩҳ��������������ǩҳ
     * �Լ���ʷ��¼�����
     */
    CalculatorUI() {
        super();
    }

    /**
     * ��õ�ǰ��ѡ��ǩҳ����
     *
     * @return ������ѡ��ǩҳ����
     */
    static String getSelectedComponent() {
        return tabbedPane.getSelectedComponent().getClass().getName();
    }

}
