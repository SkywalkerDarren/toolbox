package com.ToolBox.currency;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.math.MathContext;
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
        JsonElement root = new JsonParser().parse("{\"result\":\"success\"," + "\"timestamp\":1508602227,"
                + "\"from\":\"USD\"," + "\"rates\":{" + "\"USD\":1," + "\"AED\":3.6723," + "\"AMD\":481.43,"
                + "\"ANG\":1.78," + "\"AOA\":165.097," + "\"ARS\":17.409," + "\"AUD\":1.27562838," + "\"BBD\":2.00,"
                + "\"BDT\":82.44," + "\"BGN\":1.65493315," + "\"BHD\":0.3771," + "\"BRL\":3.18034897," + "\"BSD\":1.00,"
                + "\"BWP\":10.3363," + "\"BYN\":1.8758," + "\"CAD\":1.25329664," + "\"CHF\":0.98213328,"
                + "\"CLP\":628.10," + "\"CNY\":6.61887896," + "\"COP\":2931.00," + "\"CZK\":21.7472105,"
                + "\"DKK\":6.30201338," + "\"DOP\":47.23," + "\"EEK\":12.497," + "\"EGP\":17.61," + "\"ETB\":26.9799,"
                + "\"EUR\":0.8470193," + "\"FJD\":2.045," + "\"GBP\":0.75837471," + "\"GHS\":4.391," + "\"GTQ\":7.3435,"
                + "\"HKD\":7.80262099," + "\"HNL\":23.42," + "\"HRK\":6.35175157," + "\"HUF\":260.80792582,"
                + "\"IDR\":13516.25215472," + "\"ILS\":3.48752356," + "\"INR\":65.01721981," + "\"IQD\":1167.00,"
                + "\"IRR\":34299.00," + "\"ISK\":105.25," + "\"JMD\":126.86," + "\"JOD\":0.7075,"
                + "\"JPY\":113.26659445," + "\"KES\":103.25," + "\"KHR\":4027.00," + "\"KRW\":1130.80872756,"
                + "\"KWD\":0.3022," + "\"KZT\":335.62," + "\"LAK\":8315.00," + "\"LBP\":1505.40," + "\"LKR\":153.60,"
                + "\"LTL\":3.0487," + "\"LVL\":0.6205," + "\"MAD\":9.4245," + "\"MKD\":51.97," + "\"MMK\":1359.00,"
                + "\"MUR\":33.86," + "\"MXN\":18.92398577," + "\"MYR\":4.22489527," + "\"NAD\":13.633,"
                + "\"NGN\":357.00," + "\"NOK\":7.97148721," + "\"NZD\":1.430873," + "\"OMR\":0.3842,"
                + "\"PAB\":0.24435581," + "\"PEN\":3.23490491," + "\"PGK\":3.2715," + "\"PHP\":51.49936135,"
                + "\"PKR\":105.20," + "\"PLN\":3.58660077," + "\"PYG\":5619.00," + "\"QAR\":3.7698,"
                + "\"RON\":3.8924779," + "\"RSD\":100.5767," + "\"RUB\":57.44318446," + "\"SAR\":3.75006241,"
                + "\"SCR\":13.551," + "\"SEK\":8.16154372," + "\"SGD\":1.35924229," + "\"THB\":33.1632962,"
                + "\"TJS\":8.8075," + "\"TND\":2.4855," + "\"TRY\":3.66666574," + "\"TTD\":6.6783,"
                + "\"TWD\":30.2370472," + "\"TZS\":2239.00," + "\"UAH\":26.52," + "\"UYU\":29.56," + "\"UZS\":8040.00,"
                + "\"VEF\":9.975," + "\"VND\":22796.20692192," + "\"XAF\":556.10," + "\"XCD\":4.09239298,"
                + "\"XOF\":550.00," + "\"XPF\":100.71," + "\"ZAR\":13.67431059," + "\"ZMW\":9.70}" + "}");
        setRates(root.getAsJsonObject().get("rates").getAsJsonObject());
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
