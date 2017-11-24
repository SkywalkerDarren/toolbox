package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.Stack;

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
    private final static int NORMAL = 0;
    private int radix = NORMAL;

    /**
     * @param exp 表达式构成的字符串
     * @return 表达式的结果
     */
    public String getResult(String exp) {
        String[] expression = exp.split(" ");
        BigDecimal answer = evaluate(expression);
        String result = answer.setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        if (result.length() < 32) {
            return result;
        } else {
            return answer.setScale(16, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toEngineeringString();
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
        Operator op = Operators.operatorHashMap.get(flag.pop());
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

    public static void main(String[] args) {
        Calculator c = new Calculator();
        // 科学计算器
        assert c.getResult("").equals("0");
        assert c.getResult("1 + 1").equals("2");
        assert c.getResult("( 1 + 1 )").equals("2");
        assert c.getResult("( - 1 + 1 )").equals("0");
        assert c.getResult("sin ( 3.1415926535").equals("0.0000000000897932");
        assert c.getResult("cos ( - 1 + 1 )").equals("1");
        assert c.getResult("tan ( 3.1415926535").equals("-0.0000000000897932");
        assert c.getResult("sin ( cos ( tan ( 10").equals("0.7153149717720886");
        assert c.getResult("fact ( 20").equals("2432902008176640000");
        assert c.getResult("log ( 100").equals("2");
        assert c.getResult("abs ( - 100").equals("100");
        assert c.getResult("9 mod 7").equals("2");
        assert c.getResult("3.5 mod 0.2").equals("0.1");
        assert c.getResult("3 ^ 2").equals("9");
        assert c.getResult("3 ^ 0.5").startsWith("1.73");
        assert c.getResult("3 ^ 0").startsWith("1");
        assert c.getResult("2 √ 9").equals("3");
        assert c.getResult("2 √ 0").equals("0");
        assert c.getResult("0.5 √ 2").equals("4");
        assert c.getResult("- 1").equals("-1");
        assert c.getResult("( ( ( ( ( 0 )").equals("0");
        assert c.getResult("( ( ( ( (").equals("0");
        assert c.getResult("( - 1").equals("-1");
        assert c.getResult("3 + 3 × 3 - 3 ÷ 3").equals("11");
        assert c.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )").equals("705");
        System.out.println("科学计算器成功");

        // 程序员计算器
        if (!c.getResult("", BIN).equals("0")) throw new AssertionError();
        if (!c.getResult("1 + 1", BIN).equals("2")) throw new AssertionError();
        if (!c.getResult("1 + 7", OCT).equals("8")) throw new AssertionError();
        if (!c.getResult("1 + 9", DEC).equals("10")) throw new AssertionError();
        if (!c.getResult("1 + F", HEX).equals("16")) throw new AssertionError();
        if (!c.getResult("1 + f", HEX).equals("16")) throw new AssertionError();
        if (!c.getResult("( 1 Or 2 + 4 ) And 8 Xor 7", DEC).equals("7")) throw new AssertionError();
        if (!c.getResult("Not 0", DEC).equals("-1")) throw new AssertionError();
        if (!c.getResult("Not ( - 1", DEC).equals("0")) throw new AssertionError();
        if (!c.getResult("2 And ( - 3", DEC).equals("0")) throw new AssertionError();
        if (!c.getResult("1 Lsh 1", DEC).equals("2")) throw new AssertionError();
        if (!c.getResult("1 Lsh 1 + ( - 1 + 2 )", DEC).equals("3")) throw new AssertionError();
        if (!c.getResult("4 Rsh 1", DEC).equals("2")) throw new AssertionError();
        if (!c.getResult("1 RoL 1", DEC).equals("2")) throw new AssertionError();
        if (!c.getResult("1 RoR 1", DEC).equals(Long.MIN_VALUE + "")) throw new AssertionError();
        if (!c.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )", DEC).equals("705"))
            throw new AssertionError();
        System.out.println("程序员计算器成功");
    }
}