package com.ToolBox.UI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * ͨ���Ҽ��˵�
 *
 * @author ���
 */
class RightClickMenu extends JPopupMenu {


    private static final long serialVersionUID = 4254117594980050625L;

    /**
     * ����Ҽ��˵�����߼�
     *
     * @param jTextComponent �ı���
     */
    RightClickMenu(JTextComponent jTextComponent) {
        JMenuItem selectAll = new JMenuItem("ȫѡ");
        selectAll.addActionListener(l -> jTextComponent.selectAll());
        add(selectAll);
        JMenuItem cut = new JMenuItem("����");
        cut.addActionListener(l -> jTextComponent.cut());
        add(cut);
        JMenuItem copy = new JMenuItem("����");
        copy.addActionListener(l -> jTextComponent.copy());
        add(copy);
        JMenuItem paste = new JMenuItem("ճ��");
        paste.addActionListener(l -> jTextComponent.paste());
        add(paste);
        JMenuItem clean = new JMenuItem("���");
        clean.addActionListener(l -> jTextComponent.setText(""));
        add(clean);
    }
}