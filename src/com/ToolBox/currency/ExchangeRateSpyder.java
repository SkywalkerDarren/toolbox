package com.ToolBox.currency;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取汇率
 *
 * @author 杨弘
 */
class ExchangeRateSpyder {

    /**
     * 汇率数据爬虫
     *
     * @return json元素
     * @throws Exception 更新失败
     */
    JsonElement spider() throws Exception {
        // 设置网址
        String url_str = "https://v3.exchangerate-api.com/bulk/e92cde05e8f9d2a9a7847c75/USD";

        // 拉取请求
        URL url;
        url = new URL(url_str);

        HttpURLConnection request;
        request = (HttpURLConnection) url.openConnection();
        request.connect();

        // 解析json
        JsonParser jp = new JsonParser();
        InputStreamReader isr = new InputStreamReader((InputStream) request.getContent(), "utf-8");
        JsonElement root = jp.parse(isr);

        return root;
    }
}