package com.ToolBox.evaluate;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

public class Operators {
    final static MathContext mc = new MathContext(64, RoundingMode.HALF_UP);
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

        operatorHashMap.put("sin", new Operator() {
            @Override
            public int getAry() {
                return 1;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "sin";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.sin(a, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("cos", new Operator() {
            @Override
            public int getAry() {
                return 1;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "cos";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.cos(a, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("tan", new Operator() {
            @Override
            public int getAry() {
                return 1;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "tan";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.tan(a, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
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
