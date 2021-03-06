package com.ToolBox.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 支持右键菜单，带提示的文本框
 *
 * @author 杨弘，徐祥亮
 */
public class TextBox extends JTextArea implements MouseListener, FocusListener {


    private static final long serialVersionUID = -3130307316850299368L;
    private String hint;

    /**
     * 初始化带提示的文本框
     *
     * @param hint 提示文字
     */
    TextBox(String hint) {
        this.hint = hint;
        setText(hint);
        Color normal = new Color(245, 255, 255);
        setBackground(normal);
        setLineWrap(true);
        setFocusable(true);
        setWrapStyleWord(true);
        setVisible(true);
        Font fontPlain = new Font("微软雅黑", Font.PLAIN, 14);
        setFont(fontPlain);
        setForeground(Color.gray);
        addMouseListener(this);
        addFocusListener(this);
    }

    /**
     * 设定右键菜单
     *
     * @param e 鼠标事件
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            RightClickMenu menu = new RightClickMenu(this);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * 按下鼠标提示消失
     *
     * @param e 鼠标事件
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (getText().equals(hint)) {
            setText(null);
            setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    /**
     * 失去焦点且无文字则设定提示
     *
     * @param e 鼠标事件
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (getText().length() == 0) {
            setForeground(Color.gray);
            setText(hint);
        }
    }
}