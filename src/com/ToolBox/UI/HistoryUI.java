package com.ToolBox.UI;

import com.ToolBox.history.HistoryRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ��ʷ��¼����������ڼ�¼���ֽ������������
 * ����ͨ������ķ�ʽ�������ʱ����Ľ�����������
 * ֧�ָ��ƽ������ս��
 *
 * @author ��룬�������������
 */
class HistoryUI extends TransparentPanelUI {

    private static final long serialVersionUID = 4338709380282933851L;
    private static final String history = "��ʷ��¼";
    private static final String copy = "����";
    private static final String clear = "���";
    private static JList<String> jList = new JList<>();
    private static DefaultListModel<String> dlm = new DefaultListModel<>();
    private JLabel title;
    private JButton btnCopy;
    private JButton btnClear;
    private JScrollPane jsp;
    /**
     * ��ʼ�����
     */
    @Override
    protected void initCompoment() {
        title = new JLabel(history);
        btnCopy = new JButton(copy);
        btnClear = new JButton(clear);
        jsp = new JScrollPane();
    }

    /**
     * ��ʼ������
     */
    @Override
    protected void initUI() {
        // ����
        title.setBounds(22, 20, 75, 35);
        title.setFont(new Font("΢���ź�", Font.BOLD, 13));
        add(title);
        // ��Ӹ��ư�ť
        btnCopy.setBackground(new Color(224, 255, 255));
        btnCopy.setBounds(22, 470, 75, 35);
        add(btnCopy);
        // �����հ�ť
        btnClear.setBackground(new Color(224, 255, 255));
        btnClear.setBounds(105, 470, 75, 35);
        add(btnClear);
        // �����б�
        jList.setBackground(new Color(245, 255, 255));
        jsp.setViewportView(HistoryUI.jList);
        jsp.setBounds(16, 49, 170, 420);
        add(jsp, BorderLayout.CENTER);
    }

    /**
     * ���������¼�
     */
    @Override
    protected void createAction() {
        jList.addMouseListener(new MouseListener() {

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
                String s = jList.getSelectedValue();
                if (s == null) {
                    return;
                }
                String source = CalculatorUI.getSelectedComponent();
                if (source.equals(ScientificUI.class.getName())) {
                    ScientificUI.setTempResult(s);
                } else if (source.equals(ExchangeRateUI.class.getName())) {
                    ExchangeRateUI.setTempResult(s);
                } else if (source.equals(ProgramerUI.class.getName())) {
                    ProgramerUI.setTempResult(s);
                } else if (source.equals(ConversionUI.class.getName())) {
                    ConversionUI.setTempResult(s);
                }
            }
        });

        btnCopy.addActionListener(e -> {
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            String s = jList.getSelectedValue();
            Transferable tText = new StringSelection(s);
            clip.setContents(tText, null);
        });

        btnClear.addActionListener(e -> {
            HistoryRecord.clear();
            dlm.clear();
        });
    }

    /**
     * ������ʷ��¼UI������ܣ��������¼�
     */
    HistoryUI() {
        super();
    }

    /**
     * ������������ʷ��¼
     *
     * @param r ������ʮ������ֵ���
     */
    static void updateRecord(String r) {
        HistoryRecord.addResult(r);
        dlm.add(0, HistoryRecord.getRecord().get(HistoryRecord.getSize() - 1));
        jList.setModel(dlm);
    }
}
