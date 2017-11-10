package com.ToolBox.capture;

import java.util.HashMap;

/**
 * 信息传递工具
 *
 * @author 杨弘
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
