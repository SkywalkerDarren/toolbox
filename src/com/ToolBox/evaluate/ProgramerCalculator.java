package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 程序员计算器运算方式
 *
 * @author 杨弘
 */
public class ProgramerCalculator extends Calculator {
    @Override
    protected int getLevel(char c) {
        switch (c) {
            case ';':
            case ':':
                return 5;
            case '<':
            case '>':
                return 4;
            case '%':
            case '&':
            case '|':
            case '~':
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            case '(':
                return 0;
        }
        return -1;
    }

    @Override
    protected void calcExp(Stack<BigDecimal> val, Stack<Character> flag) {
        BigDecimal b;
        BigDecimal a;
        switch (flag.pop()) {
            case '+':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() + b.longValue()));
                break;
            case '-':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() - b.longValue()));
                break;
            case '*':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() * b.longValue()));
                break;
            case '/':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() / b.longValue()));
                break;
            case '&':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() & b.longValue()));
                break;
            case '|':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() | b.longValue()));
                break;
            case '^':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() ^ b.longValue()));
                break;
            case '%':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() % b.longValue()));
                break;
            case '~':
                b = val.pop();
                val.push(new BigDecimal(~b.longValue()));
                break;
            case '<':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() << b.longValue()));
                break;
            case '>':
                b = val.pop();
                a = val.pop();
                val.push(new BigDecimal(a.longValue() >> b.longValue()));
                break;
            case ';':
                b = val.pop();
                a = val.pop();
                long x = a.longValue();
                long y = b.longValue();
                val.push(new BigDecimal((x << y) | (x >>> 64 - y)));
                break;
            case ':':
                b = val.pop();
                a = val.pop();
                long i = a.longValue();
                long j = b.longValue();
                val.push(new BigDecimal((i >>> j) | (i << 64 - j)));
                break;
            default:
                throw new IllegalArgumentException("无此操作符");
        }
    }

    @Override
    protected String[] explain(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("表达式为空");
        }
        ArrayList<String> expression = new ArrayList<>();
        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i - 1) == '(') {
                // check the first char "- + ("
                if (!(s.charAt(i) == '-' || s.charAt(i) == '~' ||
                        s.charAt(i) == '+' || s.charAt(i) == '(')
                        && !isDigit(s.charAt(i))) {
                    throw new IllegalArgumentException("\"" + s.charAt(i) + "\" 不应在表达式的开头");
                } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                    try {
                        if (isDigit(s.charAt(i + 1))) {
                            digit.append(s.charAt(i));
                            continue;
                        } else {
                            throw new IllegalArgumentException("有多余的正负号");
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("正负号不应在末尾");
                    }
                } else if (!isDigit(s.charAt(i))) {
                    expression.add("" + s.charAt(i));
                    continue;
                }
            }
            // check other char
            if (isDigit(s.charAt(i))) {
                digit.append(s.charAt(i));
                if (i + 1 >= s.length() || !isDigit(s.charAt(i + 1)) && s.charAt(i + 1) != '.') {
                    expression.add(digit.toString());
                    digit = new StringBuilder();
                }
            } else {
                expression.add("" + s.charAt(i));
            }

        }
        // move to string[]
        String[] exp = new String[expression.size() + 2];
        exp[0] = "1";
        exp[1] = "*";
        for (int i = 0; i < expression.size(); i++) {
            exp[i + 2] = expression.get(i);
        }
        return exp;
    }

    @Override
    protected boolean isDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
}
