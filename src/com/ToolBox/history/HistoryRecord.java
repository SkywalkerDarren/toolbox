package com.ToolBox.history;

import java.util.ArrayList;

/**
 * ��ʷ��¼
 * ��ȡ����
 *
 * @author ���
 */
public class HistoryRecord {
    private static ArrayList<String> result = new ArrayList<>();

    /**
     * �����ʷ��¼���
     *
     * @return ��ʷ��¼������б�
     */
    public static ArrayList<String> getRecord() {
        return result;
    }

    /**
     * Ϊ��ʷ��¼����һ�����
     *
     * @param s �½��
     */
    public static void addResult(String s) {
        result.add(s);
    }

    /**
     * ������ʷ��¼����������ʷ��¼����
     *
     * @return ����ʷ��¼����
     */
    public static int getSize() {
        return result.size();
    }

    /**
     * �����ʷ��¼
     */
    public static void clear() {
        result.clear();
    }
}
