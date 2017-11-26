package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 计算器核心算法
 *
 * @author 杨弘
 */
public class Calculator {
    /**
     * 十六进制，不支持浮点数计算
     */
    public final static int HEX = 16;
    /**
     * 十进制，不支持浮点数计算
     */
    public final static int DEC = 10;
    /**
     * 八进制，不支持浮点数计算
     */
    public final static int OCT = 8;
    /**
     * 二进制，不支持浮点数计算
     */
    public final static int BIN = 2;
    /**
     * 支持浮点数的十进制模式
     */
    private final static int NORMAL = 0;
    private int radix = NORMAL;

    /**
     * 通过表达式获取计算结果
     *
     * @param exp 表达式构成的字符串
     * @return 表达式的结果
     */
    public String getResult(String exp) {
        String[] expression = exp.split(" ");
        if (!checkExpression(expression)) {
            throw new IllegalArgumentException("表达式不合法");
        }

        BigDecimal answer = evaluate(expression);
        String result = answer.setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        if (result.length() < 32) {
            return result;
        } else {
            return answer.setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toEngineeringString();
        }
    }

    /**
     * 通过表达式获取计算结果
     *
     * @param exp   表达式构成的字符串
     * @param radix 当前的进制
     * @return 表达式的结果
     */
    public String getResult(String exp, int radix) {
        this.radix = radix;
        return getResult(exp);
    }

    /**
     * 有限状态机检查表达式是否合法
     * 状态转移表：
     * 正负号, 数值, 双目运算符, 单目运算符, 左括号, 右括号
     * **0 1 2 3 4 5
     * 0   T
     * 1     T     T
     * 2   T   T T
     * 3         T
     * 4 T T   T T
     * 5     T     T
     *
     * @param exp 表达式数组
     */
    private boolean checkExpression(String[] exp) {
        ArrayList<String> expression = new ArrayList<>();
        for (String s : exp) {
            if (!s.matches(" *"))
                expression.add(s);
        }
        // [-|+]?\d\.{0,1}\d* 判断是否是数字
        Pattern patternFloat = Pattern.compile("[-+]?\\d+\\.\\d+");
        Pattern patternExp = Pattern.compile("[-+]?\\d+\\.\\d+[Ee]+\\++\\d+");
        Pattern patternInteger = Pattern.compile("[-+]?\\d+");
        Pattern patternHex = Pattern.compile("[-+]?[\\d[a-f][A-F]]+");
        int l = 0, r = 0;
        //      neg,    num,    double, single, left,   right
        boolean FSM[][] = new boolean[][]{
                {false, true, false, false, false, false}, //neg
                {false, false, true, false, false, true},  //num
                {false, true, false, true, true, false}, //double
                {false, false, false, false, true, false},  //single
                {true, true, false, true, true, false},  //left
                {false, false, true, false, false, true},  //right
        };

        // 初始化一个无效入口
        int i = 0, j = 0;
        boolean single = false;
        String start = expression.get(0);
        try {
            single = Operators.operatorHashMap.get(start).getAry() == 1;
        } catch (NullPointerException ignored) {

        }

        // 设定状态机有效入口
        if (patternFloat.matcher(start).matches() ||
                patternInteger.matcher(start).matches() ||
                patternHex.matcher(start).matches() ||
                patternExp.matcher(start).matches()) {
            j = 1;
        } else if (start.equals("-") || start.equals("+")) {
            j = 0;
        } else if (start.equals("(")) {
            l = 1;
            j = 4;
        } else if (single) {
            j = 3;
        }

        for (int k = 1; k < expression.size(); k++) {
            i = j;
            if (patternFloat.matcher(expression.get(k)).matches() ||
                    patternInteger.matcher(expression.get(k)).matches() ||
                    patternHex.matcher(expression.get(k)).matches() ||
                    patternExp.matcher(expression.get(k)).matches()) {
                j = 1;
            } else if (i == 4 && (expression.get(k).equals("-") || expression.get(k).equals("+"))) {
                j = 0;
            } else if (expression.get(k).equals("(")) {
                l++;
                j = 4;
            } else if (expression.get(k).equals(")")) {
                r++;
                j = 5;
            } else if (Operators.operatorHashMap.get(expression.get(k)).getAry() == 2) {
                j = 2;
            } else if (Operators.operatorHashMap.get(expression.get(k)).getAry() == 1) {
                j = 3;
            }

            if (!FSM[i][j]) return false;

        }

        return FSM[i][j] && r <= l;
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
                    case "":
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
        BigDecimal c;
        String f = flag.pop();
        Operator op = Operators.operatorHashMap.get(f);
        switch (op.getAry()) {
            case 2:
                b = val.pop();
                try {
                    a = val.pop();
                } catch (EmptyStackException e) {
                    a = BigDecimal.ZERO;
                }
                if (radix > 0) {
                    c = BigDecimal.valueOf(op.calc(a.longValueExact(), b.longValueExact()));
                } else {
                    c = op.calc(a, b);
                }
                val.push(c);
                break;
            case 1:
                a = val.pop();
                if (radix > 0) {
                    c = BigDecimal.valueOf(op.calc(a.longValueExact()));
                } else {
                    c = op.calc(a);
                }
                val.push(c);
                break;
            default:
                break;
        }
    }
}