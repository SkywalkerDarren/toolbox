package com.ToolBox.currency;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ��ȡ����
 *
 * @author ���
 */
class ExchangeRateSpyder {

    /**
     * ������������
     *
     * @return jsonԪ��
     */
    JsonObject spider() throws JsonIOException, JsonSyntaxException, IOException {
        // ������ַ
        String url_str = "https://v3.exchangerate-api.com/bulk/e92cde05e8f9d2a9a7847c75/USD";

        // ��ȡ����
        URL url;
        url = new URL(url_str);

        HttpURLConnection request;
        request = (HttpURLConnection) url.openConnection();
        request.connect();

        // ����json
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent(), "utf-8"));

        return root.getAsJsonObject().get("rates").getAsJsonObject();
    }
}
