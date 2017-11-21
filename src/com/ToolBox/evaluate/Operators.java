package com.ToolBox.evaluate;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

class Operators {
    private final static MathContext mc = new MathContext(64, RoundingMode.HALF_UP);
    static HashMap<String, Operator> operatorHashMap = new HashMap<>(32);
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

        operatorHashMap.put("×", new Operator() {
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
                return "×";
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

        operatorHashMap.put("÷", new Operator() {
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
                return "÷";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.divide(b, 32, BigDecimal.ROUND_HALF_UP);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
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
                return null;
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
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a / b;
            }
        });

        operatorHashMap.put("Or", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "Or";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a | b;
            }
        });

        operatorHashMap.put("And", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "And";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a & b;
            }
        });

        operatorHashMap.put("Not", new Operator() {
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
                return "Not";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return ~a;
            }
        });

        operatorHashMap.put("Xor", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "Xor";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a ^ b;
            }
        });

        operatorHashMap.put("Mod", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "Mod";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a % b;
            }
        });

        operatorHashMap.put("Lsh", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 4;
            }

            @Override
            public String getName() {
                return "Lsh";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a << b;
            }
        });

        operatorHashMap.put("Rsh", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 4;
            }

            @Override
            public String getName() {
                return "Rsh";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return a >> b;
            }
        });

        operatorHashMap.put("RoR", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 5;
            }

            @Override
            public String getName() {
                return "RoR";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return (a >>> b) | (a << 64 - b);
            }
        });

        operatorHashMap.put("RoL", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 5;
            }

            @Override
            public String getName() {
                return "RoL";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return null;
            }

            @Override
            public Long calc(Long a, Long b) {
                return (a << b) | (a >>> 64 - b);
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

        operatorHashMap.put("fact", new Operator() {
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
                return "fact";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                try {
                    return BigDecimalMath.factorial(a.intValueExact());
                } catch (Exception e) {
                    throw new IllegalArgumentException("阶乘应为整数");
                }
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("^", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "^";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.pow(a, b, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("√", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "√";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.root(b, a, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("log", new Operator() {
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
                return "log";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return BigDecimalMath.log10(a, mc);
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("mod", new Operator() {
            @Override
            public int getAry() {
                return 2;
            }

            @Override
            public int getLevel() {
                return 3;
            }

            @Override
            public String getName() {
                return "mod";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.divideAndRemainder(b)[1];
            }

            @Override
            public Long calc(Long a, Long b) {
                return null;
            }
        });

        operatorHashMap.put("abs", new Operator() {
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
                return "abs";
            }

            @Override
            public BigDecimal calc(BigDecimal a, BigDecimal b) {
                return a.abs();
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
