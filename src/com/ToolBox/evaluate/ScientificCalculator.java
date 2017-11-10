package com.ToolBox.evaluate;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;

//support + - * / log ^ ! mod cos sin tan yroot abs

/**
 * ��ѧ���������㷽��
 *
 * @author ���
 */
public class ScientificCalculator extends Calculator {


    /**
     * �ж��Ƿ��ǺϷ������ַ�
     *
     * @param c һ��������
     * @return true ����Ϸ�
     */
    private static boolean isLegalFirstOperator(char c) {
        // ( - + mod cos sin tan log ln sqrt
        return (c == '(' || c == '-' || c == '+' || c == 'm' || c == 'c' || c == 's' || c == 't' || c == 'l' || c == 'q'
                || c == '!' || c == 'a');
    }

    /**
     * s = sin
     * c = cos
     * t = tan
     * ! = fact
     * q = root
     * l = log
     * a = abs
     * m = mod
     * ^ = power
     *
     * @param c һ��������
     * @return ���ȼ�
     */
    protected int getLevel(char c) {
        switch (c) {
            // abs
            case 'a':
                // mod
            case 'm':
                // fact
            case '!':
                // cos
            case 'c':
                // sin
            case 's':
                // tan
            case 't':
                // yroot
            case 'q':
                // log10
            case 'l':
                // power
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

    protected boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    protected void calcExp(Stack<BigDecimal> val, Stack<Character> flag) {
        BigDecimal b;
        BigDecimal a;
        switch (flag.pop()) {
            case '+':
                b = val.pop();
                a = val.pop();
                val.push(a.add(b));
                break;
            case '-':
                b = val.pop();
                a = val.pop();
                val.push(a.subtract(b));
                break;
            case '*':
                b = val.pop();
                a = val.pop();
                val.push(a.multiply(b));
                break;
            case '/':
                b = val.pop();
                a = val.pop();
                val.push(a.divide(b, 32, BigDecimal.ROUND_HALF_UP));
                break;
            // abs
            case 'a':
                b = val.pop();
                val.push(b.abs());
                break;
            // yroot
            case 'q':
                b = val.pop();
                a = val.pop();
                val.push(BigDecimalMath.root(b, a, mc));
                break;
            // sin
            case 's':
                b = val.pop();
                val.push(BigDecimalMath.sin(b, mc));
                break;
            // cos
            case 'c':
                b = val.pop();
                val.push(BigDecimalMath.cos(b, mc));
                break;
            // tan
            case 't':
                b = val.pop();
                val.push(BigDecimalMath.tan(b, mc));
                break;
            // mod
            case 'm':
                b = val.pop();
                a = val.pop();
                val.push(a.divideAndRemainder(b)[1]);
                break;
            // log10
            case 'l':
                b = val.pop();
                val.push(BigDecimalMath.log10(b, mc));
                break;
            // fact
            case '!':
                b = val.pop();
                try {
                    val.push(BigDecimalMath.factorial(b.intValueExact()));
                } catch (Exception e) {
                    throw new IllegalArgumentException("�׳�ӦΪ����");
                }
                break;
            case '^':
                b = val.pop();
                a = val.pop();
                val.push(BigDecimalMath.pow(a, b, mc));
                break;
            default:
                throw new IllegalArgumentException("�޴˲�����");
        }
    }

    protected String[] explain(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("���ʽΪ��");
        }
        ArrayList<String> expression = new ArrayList<>();
        StringBuilder digit = new StringBuilder();
        int dot = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || s.charAt(i - 1) == '(') {
                // check the first char ". - + ("
                if (!isLegalFirstOperator(s.charAt(i)) && !isDigit(s.charAt(i))) {
                    throw new IllegalArgumentException("\"" + s.charAt(i) + "\" ��Ӧ�ڱ��ʽ�Ŀ�ͷ");
                } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                    try {
                        if (isDigit(s.charAt(i + 1))) {
                            digit.append(s.charAt(i));
                            continue;
                        } else {
                            throw new IllegalArgumentException("�ж����������");
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("�����Ų�Ӧ��ĩβ");
                    }
                } else if (!isDigit(s.charAt(i))) {
                    expression.add("" + s.charAt(i));
                    continue;
                }
            }
            // check other char
            if (s.charAt(i) == '.' || isDigit(s.charAt(i))) {
                if (s.charAt(i) == '.') {
                    dot++;
                }
                if (dot > 1) {
                    throw new IllegalArgumentException("�ж����С����");
                } else {
                    digit.append(s.charAt(i));
                    if (i + 1 >= s.length() || !isDigit(s.charAt(i + 1)) && s.charAt(i + 1) != '.') {
                        expression.add(digit.toString());
                        dot = 0;
                        digit = new StringBuilder();
                    }
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

}
