package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 计算器核心算法
 *
 * @author 杨弘
 */
public class Calculator {
    public final static int HEX = 16;
    public final static int DEC = 10;
    public final static int OCT = 8;
    public final static int BIN = 2;
    final static MathContext mc = new MathContext(64);
    private final static int NORMAL = 0;
    private int radix = NORMAL;

    /**
     * @param exp 表达式构成的字符串
     * @return 表达式的结果
     */
    public String getResult(String exp) {
        String[] expression = explain(exp);
        AtomicReference<BigDecimal> answer = new AtomicReference<>(evaluate(expression));
        String result = answer.get().setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        if (result.length() < 32) {
            return result;
        } else {
            return answer.get().setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toEngineeringString();
        }
    }

    public String getResult(String exp, int radix) {
        this.radix = radix;
        return getResult(exp);
    }

    /**
     * 表达式处理的核心方法
     *
     * @param expression: 一个由单个数值或符号构成的表达式数组(e.g., s[] = {"3.56","+","4"})
     * @return 数值结果
     */
    private BigDecimal evaluate(String[] expression) {
        if (expression.length < 1) {
            throw new IllegalArgumentException("表达式为空");
        }
        Stack<BigDecimal> val = new Stack<>();
        Stack<String> flag = new Stack<>();
        // 终止符
        flag.push("@");
        for (String s : expression) {
            try {
                if (radix > 0) {
                    s = Long.parseLong(s, radix) + "";
                }
                val.push(new BigDecimal(s));
            } catch (NumberFormatException e) {
                // 运算符
                switch (s) {
                    case ")":
                        while (!flag.peek().equals("(")) {
                            calcExp(val, flag);
                        }
                        flag.pop();
                        break;
                    case "(":
                        flag.push(s);
                        break;
                    default:
                        while (Operators.operatorHashMap.get(s).getLevel() <=
                                Operators.operatorHashMap.get(flag.peek()).getLevel()) {
                            calcExp(val, flag);
                        }
                        flag.push(s);
                        break;
                }
            }
        }
        while (!flag.peek().equals("@")) {
            calcExp(val, flag);
        }
        if (val.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return val.pop();
    }

    /**
     * 用双栈计算表达式结果
     *
     * @param val   存储数值的栈
     * @param flag: 存储操作符的栈
     */
    private void calcExp(Stack<BigDecimal> val, Stack<String> flag) {
        BigDecimal b;
        BigDecimal a;
        Operator op = Operators.operatorHashMap.get(flag.pop());
        if (op.getAry() == 2) {
            b = val.pop();
            a = val.pop();
            val.push(op.calc(a, b));
        } else if (op.getAry() == 1) {
            a = val.pop();
            val.push(op.calc(a, null));
        }
    }

    public static void main(String[] args) {
//        String[] s = {"(","50","+","4","*","3","/","2","+","799","-","180","+","9","+","8","+","(","3","/","3",")","+","8","+","(","9","+","3",")","/","3",")"};
        String s = "( 50.3 + 4 )";
        System.out.println(new Calculator().getResult(s));
    }

    /**
     * 解析表达式字符串到字符串数组
     *
     * @param exp 表达式字符串
     * @return 字符串数组
     */
    private String[] explain(String exp) {
        return exp.split(" ");
    }
}