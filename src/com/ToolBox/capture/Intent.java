package com.ToolBox.capture;

import java.util.HashMap;

/**
 * ��Ϣ���ݹ���
 *
 * @author ���
 */
public class Intent {
    private static HashMap<String, Object> intent = new HashMap<>();

    static void setObj(long serialVersionUID, String key, Object obj) {
        intent.put(serialVersionUID + key, obj);
    }

    public static Object getObj(long serialVersionUID, String key) {
        return intent.get(serialVersionUID + key);
    }

}
