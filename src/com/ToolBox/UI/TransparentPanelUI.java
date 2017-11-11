package com.ToolBox.UI;

import javax.swing.*;

/**
 * ͨ��͸��panel
 *
 * @author ���
 */
public abstract class TransparentPanelUI extends JPanel {
    private static final long serialVersionUID = -8103263234844270632L;

    /**
     * ��ʼ�����
     */
    protected abstract void initCompoment();

    /**
     * ��ʼ������
     */
    protected abstract void initUI();

    /**
     * ���������¼�
     */
    protected abstract void createAction();

    /**
     * �������ֱ���ת�����
     */
    protected TransparentPanelUI() {
        initCompoment();
        initUI();
        createAction();

        setOpaque(false);
    }
}
