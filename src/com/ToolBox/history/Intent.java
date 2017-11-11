package com.ToolBox.history;

import java.util.HashMap;

/**
 * ��Ϣ���ݹ���
 *
 * @author ���
 */
public class Intent {
    private static HashMap<String, Object> intent = new HashMap<>();

    /**
     * �趨��ֵ
     *
     * @param serialVersionUID ��uid
     * @param key              ��ȡ��
     * @param obj              �������
     */
    public static void setObj(long serialVersionUID, String key, Object obj) {
        intent.put(serialVersionUID + key, obj);
    }

    /**
     * ��ȡ��ֵ
     * @param serialVersionUID ��uid
     * @param key ��ȡ��
     * @return ��ȡ����
     */
    public static Object getObj(long serialVersionUID, String key) {
        return intent.get(serialVersionUID + key);
    }

}
