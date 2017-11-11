package com.ToolBox.UI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * 通用右键菜单
 *
 * @author 杨弘
 */
class RightClickMenu extends JPopupMenu {


    private static final long serialVersionUID = 4254117594980050625L;

    /**
     * 添加右键菜单组件逻辑
     *
     * @param jTextComponent 文本框
     */
    RightClickMenu(JTextComponent jTextComponent) {
        JMenuItem selectAll = new JMenuItem("全选");
        selectAll.addActionListener(l -> jTextComponent.selectAll());
        add(selectAll);
        JMenuItem cut = new JMenuItem("剪切");
        cut.addActionListener(l -> jTextComponent.cut());
        add(cut);
        JMenuItem copy = new JMenuItem("复制");
        copy.addActionListener(l -> jTextComponent.copy());
        add(copy);
        JMenuItem paste = new JMenuItem("粘贴");
        paste.addActionListener(l -> jTextComponent.paste());
        add(paste);
        JMenuItem clean = new JMenuItem("清空");
        clean.addActionListener(l -> jTextComponent.setText(""));
        add(clean);
    }
}