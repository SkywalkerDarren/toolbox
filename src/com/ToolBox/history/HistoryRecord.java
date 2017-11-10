package com.ToolBox.history;

import java.util.ArrayList;

/**
 * 历史记录
 * 存取核心
 *
 * @author 杨弘
 */
public class HistoryRecord {
    private static ArrayList<String> result = new ArrayList<>();

    /**
     * 获得历史记录结果
     *
     * @return 历史记录结果的列表
     */
    public static ArrayList<String> getRecord() {
        return result;
    }

    /**
     * 为历史记录增加一个结果
     *
     * @param s 新结果
     */
    public static void addResult(String s) {
        result.add(s);
    }

    /**
     * 返回历史记录所包含的历史记录个数
     *
     * @return 总历史记录个数
     */
    public static int getSize() {
        return result.size();
    }

    /**
     * 清空历史记录
     */
    public static void clear() {
        result.clear();
    }
}
