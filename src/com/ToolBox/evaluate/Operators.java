package com.ToolBox.evaluate;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.EmptyStackException;
import java.util.HashMap;

/**
 * 运算符类
 * 设定各种运算符，大数或长整数的运算
 *
 * @author 杨弘
 */
class Operators {
    private final static MathContext mc = new MathContext(64, RoundingMode.HALF_UP);
    static HashMap<String, Operator> operatorHashMap = new HashMap<>(32);

    // 运算符初始化
    static {
        // 中止符
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].add(args[1]);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] + args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].subtract(args[1]);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] - args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].multiply(args[1]);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] * args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].divide(args[1], 32, BigDecimal.ROUND_HALF_UP);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] / args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].multiply(args[1]);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] * args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].divide(args[1], 32, BigDecimal.ROUND_HALF_UP);
            }

            @Override
            public Long calc(Long... args) {
                return args[0] / args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] | args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] & args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return ~args[0];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] ^ args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] % args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] << args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return args[0] >> args[1];
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return (args[0] >>> args[1]) | (args[0] << 64 - args[1]);
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return (args[0] << args[1]) | (args[0] >>> 64 - args[1]);
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.sin(args[0], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.cos(args[0], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.tan(args[0], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                try {
                    return BigDecimalMath.factorial(args[0].intValueExact());
                } catch (ArithmeticException e) {
                    throw new IllegalArgumentException("阶乘应为整数");
                }
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.pow(args[0], args[1], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.root(args[1], args[0], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return BigDecimalMath.log10(args[0], mc);
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].divideAndRemainder(args[1])[1];
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return args[0].abs();
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
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
            public BigDecimal calc(BigDecimal... args) {
                return null;
            }

            @Override
            public Long calc(Long... args) {
                return null;
            }
        });
    }
}

/**
 * 运算符抽象类
 * 定义运算符目数，优先级，名称，大数运算方法，整数运算方法
 *
 * @author 杨弘
 */
interface Operator {
    int getAry();

    int getLevel();

    String getName();

    BigDecimal calc(BigDecimal... args) throws EmptyStackException;

    Long calc(Long... args) throws EmptyStackException;
}


