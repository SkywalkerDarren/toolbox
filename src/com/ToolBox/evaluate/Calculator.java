package com.ToolBox.evaluate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
 * �����������㷨
 *
 * @author ���
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
     * @param exp ���ʽ���ɵ��ַ���
     * @return ���ʽ�Ľ��
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
     * ���ʽ����ĺ��ķ���
     *
     * @param expression: һ���ɵ�����ֵ����Ź��ɵı��ʽ����(e.g., s[] = {"3.56","+","4"})
     * @return ��ֵ���
     */
    private BigDecimal evaluate(String[] expression) {
        if (expression.length < 1) {
            throw new IllegalArgumentException("���ʽΪ��");
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
                        throw new IllegalArgumentException("�����Ŷ���");
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
                    throw new IllegalArgumentException("��ֵ����");
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
     * �ж��Ƿ������ֵ�һ���֣�����С����
     *
     * @param c �жϵ��ַ�
     * @return true ��������ֵ�һ����
     */
    protected abstract boolean isDigit(char c);

    /**
     * ���һ�������������ȼ�
     *
     * @param c һ��������
     * @return ���������ȼ�
     */
    protected abstract int getLevel(char c);

    /**
     * ��˫ջ������ʽ���
     *
     * @param val   �洢��ֵ��ջ
     * @param flag: �洢��������ջ
     */
    protected abstract void calcExp(Stack<BigDecimal> val, Stack<Character> flag);

    /**
     * �������ʽ�ַ������ַ�������
     *
     * @param exp ���ʽ�ַ���
     * @return �ַ�������
     */
    protected abstract String[] explain(String exp);
}
