package com.ToolBox.currency;

/**
 * ���Ҵ洢
 *
 * @author ���
 */
public class Currency {
    String name;
    double rateToUSD;

    /**
     * ��ȡ���ҵ�����
     *
     * @return ��������
     */
    public String getName() {
        return name;
    }

    /**
     * ����ԪΪ1����ȡ���ҵĻ���
     *
     * @return ���Ҷ���Ԫ����
     */
    public double getRateToUSD() {
        return rateToUSD;
    }
}