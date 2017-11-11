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
 * 历史记录侧边栏，用于记录各种结果，并保存结果
 * 可以通过点击的方式，输出临时保存的结果到输入框中
 * 支持复制结果，清空结果
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class HistoryUI extends TransparentPanelUI {

    private static final long serialVersionUID = 4338709380282933851L;
    private static final String history = "历史记录";
    private static final String copy = "复制";
    private static final String clear = "清空";
    private static JList<String> jList = new JList<>();
    private static DefaultListModel<String> dlm = new DefaultListModel<>();
    private JLabel title;
    private JButton btnCopy;
    private JButton btnClear;
    private JScrollPane jsp;
    /**
     * 初始化组件
     */
    @Override
    protected void initCompoment() {
        title = new JLabel(history);
        btnCopy = new JButton(copy);
        btnClear = new JButton(clear);
        jsp = new JScrollPane();
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        // 标题
        title.setBounds(22, 20, 75, 35);
        title.setFont(new Font("微软雅黑", Font.BOLD, 13));
        add(title);
        // 添加复制按钮
        btnCopy.setBackground(new Color(224, 255, 255));
        btnCopy.setBounds(22, 470, 75, 35);
        add(btnCopy);
        // 添加清空按钮
        btnClear.setBackground(new Color(224, 255, 255));
        btnClear.setBounds(105, 470, 75, 35);
        add(btnClear);
        // 设置列表
        jList.setBackground(new Color(245, 255, 255));
        jsp.setViewportView(HistoryUI.jList);
        jsp.setBounds(16, 49, 170, 420);
        add(jsp, BorderLayout.CENTER);
    }

    /**
     * 建立监听事件
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
     * 构建历史记录UI基本框架，及监听事件
     */
    HistoryUI() {
        super();
    }

    /**
     * 新增并更新历史记录
     *
     * @param r 新增的十进制数值结果
     */
    static void updateRecord(String r) {
        HistoryRecord.addResult(r);
        dlm.add(0, HistoryRecord.getRecord().get(HistoryRecord.getSize() - 1));
        jList.setModel(dlm);
    }
}
