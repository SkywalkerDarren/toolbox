package com.ToolBox.measurement;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MeasurementTest {

    private BigDecimal test;
    private Measurement m;

    @Before
    public void setUp() throws Exception {
        test = new BigDecimal(1);
        m = new Measurement();
        System.out.println("测试值：" + test);
    }

    @Test
    public void conver() throws Exception {
        for (Map.Entry<String, HashMap<String, BigDecimal>> type : m.hashMapType.entrySet()) {
            String ty = type.getKey();
            HashMap<String, BigDecimal> hm = type.getValue();
            for (Map.Entry<String, BigDecimal> source : hm.entrySet()) {
                String s = source.getKey();
                for (Map.Entry<String, BigDecimal> target : hm.entrySet()) {
                    String t = target.getKey();
                    System.out.println("类型：" + ty + " 源单位：" + s + "\t目标单位：" + t + "\t转换值：" + m.conver(s, t, test, ty));
                }
            }
        }

        for (Map.Entry<String, String> source : m.temp.entrySet()) {
            String s = source.getKey();
            for (Map.Entry<String, String> target : m.temp.entrySet()) {
                String t = target.getKey();
                System.out.println("类型：温度 源单位：" + s + "\t目标单位：" + t + "\t转换值：" + m.conver(s, t, test, "温度"));
            }
        }

        for (Map.Entry<String, String> source : m.angle.entrySet()) {
            String s = source.getKey();
            for (Map.Entry<String, String> target : m.angle.entrySet()) {
                String t = target.getKey();
                System.out.println("类型：角度 源单位：" + s + "\t目标单位：" + t + "\t转换值：" + m.conver(s, t, test, "角度"));
            }
        }
    }

}