package com.ToolBox.UI;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Í¨ÓÃÓÒ¼ü²Ëµ¥
 *
 * @author Ñîºë
 */
class RightClickMenu extends JPopupMenu {


    private static final long serialVersionUID = 4254117594980050625L;

    RightClickMenu(JTextComponent jTextComponent) {
        JMenuItem selectAll = new JMenuItem("È«Ñ¡");
        selectAll.addActionListener(l -> jTextComponent.selectAll());
        add(selectAll);
        JMenuItem cut = new JMenuItem("¼ôÇÐ");
        cut.addActionListener(l -> jTextComponent.cut());
        add(cut);
        JMenuItem copy = new JMenuItem("¸´ÖÆ");
        copy.addActionListener(l -> jTextComponent.copy());
        add(copy);
        JMenuItem paste = new JMenuItem("Õ³Ìù");
        paste.addActionListener(l -> jTextComponent.paste());
        add(paste);
        JMenuItem clean = new JMenuItem("Çå¿Õ");
        clean.addActionListener(l -> jTextComponent.setText(""));
        add(clean);

    }

}