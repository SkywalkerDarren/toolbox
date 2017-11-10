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
public abstract class Calculator {
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
        Stack<Character> flag = new Stack<>();
        flag.push('@');
        boolean check = true;
        for (String s : expression) {
            char c = s.charAt(0);
            if (s.length() < 2 && !isDigit(c)) {
                // operator
                if (c == ')') {
                    check = false;
                    while (flag.peek() != '(') {
                        calcExp(val, flag);
                    }
                    flag.pop();
                } else if (c == '(') {
                    if (!check) {
                        throw new IllegalArgumentException("右括号多余");
                    }
                    flag.push(c);
                } else {
                    check = true;
                    while (getLevel(c) <= getLevel(flag.peek())) {
                        calcExp(val, flag);
                    }
                    flag.push(c);
                }

            } else {
                if (!check) {
                    throw new IllegalArgumentException("数值多余");
                }
                check = false;
                if (radix > 0) {
                    s = Long.parseLong(s, radix) + "";
                }
                val.push(new BigDecimal(s));
            }
        }
        while (flag.peek() != '@') {
            calcExp(val, flag);
        }
        if (val.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return val.pop();
    }

    /**
     * 判断是否是数字的一部分，包括小数点
     *
     * @param c 判断的字符
     * @return true 如果是数字的一部分
     */
    protected abstract boolean isDigit(char c);

    /**
     * 获得一个操作符的优先级
     *
     * @param c 一个操作符
     * @return 操作符优先级
     */
    protected abstract int getLevel(char c);

    /**
     * 用双栈计算表达式结果
     *
     * @param val   存储数值的栈
     * @param flag: 存储操作符的栈
     */
    protected abstract void calcExp(Stack<BigDecimal> val, Stack<Character> flag);

    /**
     * 解析表达式字符串到字符串数组
     *
     * @param exp 表达式字符串
     * @return 字符串数组
     */
    protected abstract String[] explain(String exp);
}
