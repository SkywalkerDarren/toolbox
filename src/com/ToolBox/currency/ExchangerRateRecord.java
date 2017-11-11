package com.ToolBox.currency;

import com.ToolBox.UI.FileResource;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 记录汇率
 * 初始为内置汇率
 * 从网上获取最新汇率
 *
 * @author 杨弘
 */
public class ExchangerRateRecord {
    private Currency rates[];
    private int size;

    /**
     * 初始化原始json数据
     */
    public ExchangerRateRecord() {
        FileResource resource = new FileResource();
        try {
            InputStream in = new FileInputStream(new File(resource.exchangeRateURL.toURI()));
            JsonElement root = new JsonParser().parse(new InputStreamReader(in));
            setRates(root.getAsJsonObject().get("rates").getAsJsonObject());
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * 用于更新汇率信息，需要联网
     *
     * @return true 如果更新成功
     */
    public boolean update() {
        try {
            setRates(new ExchangeRateSpyder().spider());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断汇率信息是否为空
     *
     * @return true 如果汇率为空
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取汇率信息数组，以Currency数组的形式返回一组汇率，如果信息为空，则自动从网上爬取信息
     *
     * @return 返回一组汇率
     */
    public Currency[] getRates() {
        if (isEmpty()) {
            update();
        }
        return rates.clone();
    }

    /**
     * 解析json数据到汇率数组
     *
     * @param jsonRates json数据
     */
    private void setRates(JsonObject jsonRates) {
        size = jsonRates.size();
        rates = new Currency[size];
        int i = 0;
        for (Map.Entry<String, JsonElement> entry : jsonRates.entrySet()) {
            rates[i] = new Currency();
            rates[i].name = entry.getKey();
            rates[i].rateToUSD = entry.getValue().getAsDouble();
            i++;
        }
    }

    /**
     * 给定一种货币名称，返回该货币类Currency
     *
     * @param name 货币名称
     * @return 货币类
     */
    public Currency getRecord(String name) {
        if (isEmpty()) {
            update();
        }
        name = name.toUpperCase();
        int i;
        for (i = 0; i < size; i++) {
            if (rates[i].getName().equals(name)) {
                break;
            }
        }
        if (i == size) {
            throw new IllegalArgumentException("没有这种货币");
        }
        return rates[i];
    }

    /**
     * 换算出兑换后应得的金额
     *
     * @param source 初始货币
     * @param much   金额
     * @param target 目标货币
     * @return 兑换后的金额
     */
    public BigDecimal calcRate(Currency source, double much, Currency target) {
        BigDecimal m = new BigDecimal(much);
        BigDecimal t = new BigDecimal(target.getRateToUSD());
        BigDecimal s = new BigDecimal(source.getRateToUSD());
        return m.multiply(t.divide(s, MathContext.DECIMAL128));
    }
}