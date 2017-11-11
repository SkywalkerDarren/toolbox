package com.ToolBox.UI;

import javax.swing.*;

/**
 * 通用透明panel
 *
 * @author 杨弘
 */
public abstract class TransparentPanelUI extends JPanel {
    private static final long serialVersionUID = -8103263234844270632L;

    /**
     * 初始化组件
     */
    protected abstract void initCompoment();

    /**
     * 初始化布局
     */
    protected abstract void initUI();

    /**
     * 建立监听事件
     */
    protected abstract void createAction();

    /**
     * 构建文字编码转换框架
     */
    protected TransparentPanelUI() {
        initCompoment();
        initUI();
        createAction();

        setOpaque(false);
    }
}
