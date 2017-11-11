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
 * ��¼����
 * ��ʼΪ���û���
 * �����ϻ�ȡ���»���
 *
 * @author ���
 */
public class ExchangerRateRecord {
    private Currency rates[];
    private int size;

    /**
     * ��ʼ��ԭʼjson����
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
     * ���ڸ��»�����Ϣ����Ҫ����
     *
     * @return true ������³ɹ�
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
     * �жϻ�����Ϣ�Ƿ�Ϊ��
     *
     * @return true �������Ϊ��
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * ��ȡ������Ϣ���飬��Currency�������ʽ����һ����ʣ������ϢΪ�գ����Զ���������ȡ��Ϣ
     *
     * @return ����һ�����
     */
    public Currency[] getRates() {
        if (isEmpty()) {
            update();
        }
        return rates.clone();
    }

    /**
     * ����json���ݵ���������
     *
     * @param jsonRates json����
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
     * ����һ�ֻ������ƣ����ظû�����Currency
     *
     * @param name ��������
     * @return ������
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
            throw new IllegalArgumentException("û�����ֻ���");
        }
        return rates[i];
    }

    /**
     * ������һ���Ӧ�õĽ��
     *
     * @param source ��ʼ����
     * @param much   ���
     * @param target Ŀ�����
     * @return �һ���Ľ��
     */
    public BigDecimal calcRate(Currency source, double much, Currency target) {
        BigDecimal m = new BigDecimal(much);
        BigDecimal t = new BigDecimal(target.getRateToUSD());
        BigDecimal s = new BigDecimal(source.getRateToUSD());
        return m.multiply(t.divide(s, MathContext.DECIMAL128));
    }
}
