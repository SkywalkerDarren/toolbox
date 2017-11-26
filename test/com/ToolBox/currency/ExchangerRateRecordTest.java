package com.ToolBox.currency;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ExchangerRateRecordTest {
    private ExchangerRateRecord e;

    @Before
    public void setUp() throws Exception {
        e = new ExchangerRateRecord();
    }

    @Test
    public void getDate() throws Exception {
        System.out.println("日期：" + e.getDate());
    }

    @Test
    public void update() throws Exception {
        assertTrue(e.update());
    }

    @Test
    public void getRates() throws Exception {
        e.getRates();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRecord() throws Exception {
        for (Currency i : e.getRates()) {
            System.out.println(e.getRecord(i.name).getName());
            System.out.println(e.getRecord(i.name).getRateToUSD());
        }
        e.getRecord("null");
        fail("未报错");
    }

    @Test
    public void calcRate() throws Exception {
        double m = 1;
        System.out.println("测试值：" + m);
        for (Currency s : e.getRates()) {
            for (Currency t : e.getRates()) {
                System.out.println("源货币：" + s.getName() + " 目标货币：" + t.getName() + " 兑换得：" + e.calcRate(s, m, t));
            }
        }
    }

}