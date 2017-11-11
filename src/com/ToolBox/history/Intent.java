package com.ToolBox.history;

import java.util.HashMap;

/**
 * 信息传递工具
 *
 * @author 杨弘
 */
public class Intent {
    private static HashMap<String, Object> intent = new HashMap<>();

    /**
     * 设定传值
     *
     * @param serialVersionUID 类uid
     * @param key              获取键
     * @param obj              保存对象
     */
    public static void setObj(long serialVersionUID, String key, Object obj) {
        intent.put(serialVersionUID + key, obj);
    }

    /**
     * 获取传值
     * @param serialVersionUID 类uid
     * @param key 获取键
     * @return 获取对象
     */
    public static Object getObj(long serialVersionUID, String key) {
        return intent.get(serialVersionUID + key);
    }

}