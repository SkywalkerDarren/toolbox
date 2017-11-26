package com.ToolBox.date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DateCalculateTest {

    private DateCalculate dInterval;
    private DateCalculate dInterval2;
    private DateCalculate dIncrease;
    private DateCalculate dDecrease;

    @Before
    public void setUp() throws Exception {
        dInterval = new DateCalculate(1970, 1, 1, 3000, 12, 31);
        dInterval2 = new DateCalculate(3000, 12, 31, 1970, 1, 1);
        dIncrease = new DateCalculate(3000, 1, 1, 999, 999, 999, true);
        dDecrease = new DateCalculate(1970, 1, 1, 999, 999, 999, false);
    }

    @Test(expected = Exception.class)
    public void setDay() throws Exception {
        for (int i = 1; i <= 12; i++) {
            System.out.println(DateCalculate.setDay(2000, i));
            System.out.println(DateCalculate.setDay(2001, i));
        }
        System.out.println(DateCalculate.setDay(1000, 2));
        System.out.println(DateCalculate.setDay(1000, 0));
        fail("未报错");
    }

    @Test
    public void getIntervalDay() throws Exception {
        System.out.println(dInterval.getIntervalDay());
        System.out.println(dInterval2.getIntervalDay());
        System.out.println(dIncrease.getIntervalDay());
        System.out.println(dDecrease.getIntervalDay());
        System.out.println();
    }

    @Test
    public void getIntervalMonth() throws Exception {
        System.out.println(dInterval.getIntervalMonth());
        System.out.println(dInterval2.getIntervalMonth());
        System.out.println(dIncrease.getIntervalMonth());
        System.out.println(dDecrease.getIntervalMonth());
        System.out.println();
    }

    @Test
    public void getIntervalYear() throws Exception {
        System.out.println(dInterval.getIntervalYear());
        System.out.println(dInterval2.getIntervalYear());
        System.out.println(dIncrease.getIntervalYear());
        System.out.println(dDecrease.getIntervalYear());
        System.out.println();
    }

    @Test
    public void getTotalDay() throws Exception {
        System.out.println(dInterval.getTotalDay());
        System.out.println(dInterval2.getTotalDay());
        System.out.println(dIncrease.getTotalDay());
        System.out.println(dDecrease.getTotalDay());
        System.out.println();
    }

    @Test
    public void getEndDay() throws Exception {
        System.out.println(dInterval.getEndDay());
        System.out.println(dInterval2.getEndDay());
        System.out.println(dIncrease.getEndDay());
        System.out.println(dDecrease.getEndDay());
        System.out.println();
    }

    @Test
    public void getEndMonth() throws Exception {
        System.out.println(dInterval.getEndMonth());
        System.out.println(dInterval2.getEndMonth());
        System.out.println(dIncrease.getEndMonth());
        System.out.println(dDecrease.getEndMonth());
        System.out.println();
    }

    @Test
    public void getEndYear() throws Exception {
        System.out.println(dInterval.getEndYear());
        System.out.println(dInterval2.getEndYear());
        System.out.println(dIncrease.getEndYear());
        System.out.println(dDecrease.getEndYear());
        System.out.println();
    }

}