package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.util.HashMap;

public class Operators {
    static HashMap<String, Operator> operatorHashMap = new HashMap<>();

    static {
        operatorHashMap.put("@", new Operator() {
            @Override
            public int getAry() {
                return 0;
            }

            @Override
            public int getLevel() {
                return -1;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });
        operatorHashMap.put("+", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 1;
            }

            @Override
            public String getName() {
                return "+";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.add(b);
            }

            @Override
            public Long calc(Long a, Long b) {
                return a + b;
            }
        });


        operatorHashMap.put("-", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 1;
            }

            @Override
            public String getName() {
                return "-";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.subtract(b);
            }

            @Override
            public Long calc(Long a, Long b) {
                return a - b;
            }
        });

        operatorHashMap.put("*", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 2;
            }

            @Override
            public String getName() {
                return "*";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.multiply(b);
            }

            @Override
            public Long calc(Long a, Long b) {
                return a * b;
            }
        });

        operatorHashMap.put("/", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 2;
            }

            @Override
            public String getName() {
                return "/";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.divide(b, 32, BigDecimal.ROUND_HALF_UP);
            }

            @Override
            public Long calc(Long a, Long b) {
                return a / b;
            }
        });

        operatorHashMap.put("(", new Operator() {
            @Override
            public int getAry() {
                return 0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public String getName() {
                return "(";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put(")", new Operator() {
            @Override
            public int getAry() {
                return 0;
            }

            @Override
            public int getLevel() {
                return -1;
            }

            @Override
            public String getName() {
                return ")";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });
    }
}

abstract class Operator {
    public abstract int getAry();

    public abstract int getLevel();

    public abstract String getName();

    public abstract BigDecimal calc(BigDecimal a, BigDecimal b);

    public abstract Long calc(Long a, Long b);
}
