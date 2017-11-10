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
class CalculatorUI extends JPanel {

    private static final long serialVersionUID = -7067036135688239326L;
    private static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    /**
     * ��ʼ����ǩҳ
     * ��ӿ�ѧ��������ǩҳ�����ʼ�������ǩҳ��
     * ����Ա���̱�ǩҳ����λ��������ǩҳ��������������ǩҳ
     * �Լ���ʷ��¼�����
     */
    CalculatorUI() {

        setOpaque(false);

        // �����ǩҳ
        tabbedPane.setBackground(new Color(250, 255, 255));
        tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        tabbedPane.setBounds(-2, -2, 597, 550);
        tabbedPane.setBorder(BorderFactory.createEtchedBorder());
        add(tabbedPane);

        // ��ѧ��������ǩҳ
        JPanel panelCalculator = new ScientificUI();
        panelCalculator.setLayout(null);
        tabbedPane.addTab("��ѧ������", null, panelCalculator, null);

        // ���ʼ�������ǩҳ
        JPanel panelExchangeRate = new ExchangeRateUI();
        panelExchangeRate.setLayout(null);
        tabbedPane.addTab("���ʼ�����", null, panelExchangeRate, null);

        // ����Ա���̱�ǩҳ
        JPanel panelKeyBoard = new ProgramerUI();
        tabbedPane.addTab("����Ա����", null, panelKeyBoard, null);
        panelKeyBoard.setLayout(null);

        // ��λ��������ǩҳ
        JPanel panelConversion = new ConversionUI();
        panelConversion.setLayout(null);
        tabbedPane.addTab("��λ������", null, panelConversion, null);

        // ������������ǩҳ
        JPanel panelCalendar = new CalendarUI();
        panelCalendar.setLayout(null);
        tabbedPane.addTab("����������", null, panelCalendar, null);

        // ��ʷ��¼
        JPanel panelHistory = new HistoryUI();
        panelHistory.setBorder(BorderFactory.createTitledBorder(""));
        panelHistory.setBounds(595, -13, 199, 566);
        panelHistory.setLayout(null);
        add(panelHistory);
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
