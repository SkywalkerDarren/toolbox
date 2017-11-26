package com.ToolBox.currency;

/**
 * 货币存储
 *
 * @author 杨弘
 */
public class Currency {
    /**
     * 货币名称
     */
    String name;
    /**
     * 货币兑美元汇率
     */
    double rateToUSD;

    /**
     * 获取货币的名称
     *
     * @return 货币名称
     */
    public String getName() {
        return name;
    }

    /**
     * 以美元为1，获取货币的汇率
     *
     * @return 货币兑美元汇率
     */
    public double getRateToUSD() {
        return rateToUSD;
    }
}