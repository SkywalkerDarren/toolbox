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
     * 有限状态机检查表达式是否合法
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
        Pattern patternFloat = Pattern.compile("[-|+]?\\d+\\.\\d+");
        Pattern patternInteger = Pattern.compile("[-|+]?\\d+");
        Pattern patternHex = Pattern.compile("[-|+]?[\\d[a-f][A-F]]+");
        int l = 0, r = 0;
        //      neg,    num,    double, single, left,   right,  """ "
        boolean FSM[][] = new boolean[][]{
                {false, true, false, false, false, false}, //neg
                {false, false, true, false, false, true},  //num
                {false, true, false, true, true, false}, //double
                {false, false, false, false, true, false},  //single
                {true, true, false, true, true, false},  //left
                {false, false, true, false, false, false},  //right
                {}
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
                patternHex.matcher(start).matches()) {
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
                    patternHex.matcher(expression.get(k)).matches()) {
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
        String f = flag.pop();
//        System.out.println(Operators.operatorHashMap.get(f).getName());
        Operator op = Operators.operatorHashMap.get(f);
        switch (op.getAry()) {
            case 2:
                b = val.pop();
                try {
//                    if ((op.getName().equals("+") || op.getName().equals("-")) &&
//                            (flag.peek().equals("(") || flag.peek().equals("@")))
//                        a = BigDecimal.ZERO;
//                    else
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
        Calculator s = new Calculator();
        Calculator p = new Calculator();
        System.out.println(s.getResult(" 1 + 1"));
        if (!p.getResult("Not ( 0", DEC).equals("-1")) throw new AssertionError();
//        assert c.checkExpression("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )".split(" "));
        // 科学计算器
//        assert c.getResult("").equals("0");
        assert s.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )").equals("705");
        assert s.getResult("1 + 1").equals("2");
        assert s.getResult("( 1 + 1 )").equals("2");
        assert s.getResult("( - 1 + 1 )").equals("0");
        assert s.getResult("sin ( 3.1415926535").equals("0.0000000000897932");
        assert s.getResult("cos ( - 1 + 1 )").equals("1");
        assert s.getResult("tan ( 3.1415926535").equals("-0.0000000000897932");
        assert s.getResult("sin ( cos ( tan ( 10").equals("0.7153149717720886");
        assert s.getResult("fact ( 20").equals("2432902008176640000");
        assert s.getResult("log ( 100").equals("2");
        assert s.getResult("abs ( - 100").equals("100");
        assert s.getResult("9 mod 7").equals("2");
        assert s.getResult("3.5 mod 0.2").equals("0.1");
        assert s.getResult("3 ^ 2").equals("9");
        assert s.getResult("3 ^ 0.5").startsWith("1.73");
        assert s.getResult("3 ^ 0").startsWith("1");
        assert s.getResult("2 √ 9").equals("3");
        assert s.getResult("2 √ 0").equals("0");
        assert s.getResult("0.5 √ 2").equals("4");
        assert s.getResult("- 1").equals("-1");
        assert s.getResult("( ( ( ( ( 0 )").equals("0");
        assert s.getResult("( ( ( ( (").equals("0");
        assert s.getResult("( - 1").equals("-1");
        assert s.getResult("3 + 3 × 3 - 3 ÷ 3").equals("11");
        System.out.println("科学计算器成功");

        // 程序员计算器
//        if (!c.getResult("", BIN).equals("0")) throw new AssertionError();
        if (!p.getResult("1 + 1", BIN).equals("2")) throw new AssertionError();
        if (!p.getResult("1 + 7", OCT).equals("8")) throw new AssertionError();
        if (!p.getResult("1 + 9", DEC).equals("10")) throw new AssertionError();
        if (!p.getResult("1 + F", HEX).equals("16")) throw new AssertionError();
        if (!p.getResult("1 + f", HEX).equals("16")) throw new AssertionError();
        if (!p.getResult("( 1 Or 2 + 4 ) And 8 Xor 7", DEC).equals("7")) throw new AssertionError();
        if (!p.getResult("Not ( - 1", DEC).equals("0")) throw new AssertionError();
        if (!p.getResult("2 And ( - 3", DEC).equals("0")) throw new AssertionError();
        if (!p.getResult("1 Lsh 1", DEC).equals("2")) throw new AssertionError();
        if (!p.getResult("1 Lsh 1 + ( - 1 + 2 )", DEC).equals("3")) throw new AssertionError();
        if (!p.getResult("4 Rsh 1", DEC).equals("2")) throw new AssertionError();
        if (!p.getResult("1 RoL 1", DEC).equals("2")) throw new AssertionError();
        if (!p.getResult("1 RoR 1", DEC).equals(Long.MIN_VALUE + "")) throw new AssertionError();
        if (!p.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )", DEC).equals("705"))
            throw new AssertionError();
        System.out.println("程序员计算器成功");
    }
}