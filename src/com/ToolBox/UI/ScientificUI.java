package com.ToolBox.UI;

import com.ToolBox.evaluate.ScientificCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 科学计算器，支持结果保存即结果获取
 * 支持带优先级的四则运算，以及高级运算符
 * 支持高精度计算
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class ScientificUI extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 688567022889591814L;
    private static JTextArea JtextArea = new JTextArea();
    private static StringBuilder number = new StringBuilder();// 用户显示
    private static StringBuilder answer = new StringBuilder();// 后台字符串
    private boolean wasAnswer = false;

    /**
     * 构造布局
     * 按钮，文本框
     */
    ScientificUI() {

        setOpaque(false);
        setBackground(new Color(248, 248, 255));
        Color btnBasicColor = new Color(240, 255, 250);
        Color btnNumberColor = new Color(225, 255, 250);
        Font font = new Font("微软雅黑", Font.BOLD, 13);
        Font fontBasic = new Font("微软雅黑", Font.BOLD, 14);
        setForeground(btnBasicColor);
        setLayout(null);

        // 按钮组
        JButton btn0 = new JButton("0");
        btn0.addActionListener(this);
        btn0.setBackground(btnNumberColor);
        btn0.setFont(font);
        btn0.setBounds(295, 421, 75, 35);
        add(btn0);

        JButton btn1 = new JButton("1");
        btn1.addActionListener(this);
        btn1.setBackground(btnNumberColor);
        btn1.setFont(font);
        btn1.setBounds(221, 387, 75, 35);
        add(btn1);

        JButton btn2 = new JButton("2");
        btn2.addActionListener(this);
        btn2.setBackground(btnNumberColor);
        btn2.setFont(font);
        btn2.setBounds(295, 387, 75, 35);
        add(btn2);

        JButton btn3 = new JButton("3");
        btn3.addActionListener(this);
        btn3.setBackground(btnNumberColor);
        btn3.setFont(font);
        btn3.setBounds(369, 387, 75, 35);
        add(btn3);

        JButton btn4 = new JButton("4");
        btn4.addActionListener(this);
        btn4.setBackground(btnNumberColor);
        btn4.setFont(font);
        btn4.setBounds(221, 353, 75, 35);
        add(btn4);

        JButton btn5 = new JButton("5");
        btn5.addActionListener(this);
        btn5.setBackground(btnNumberColor);
        btn5.setFont(font);
        btn5.setBounds(295, 353, 75, 35);
        add(btn5);

        JButton btn6 = new JButton("6");
        btn6.addActionListener(this);
        btn6.setBackground(btnNumberColor);
        btn6.setFont(font);
        btn6.setBounds(369, 353, 75, 35);
        add(btn6);

        JButton btn7 = new JButton("7");
        btn7.addActionListener(this);
        btn7.setBackground(btnNumberColor);
        btn7.setFont(font);
        btn7.setBounds(221, 319, 75, 35);
        add(btn7);

        JButton btn8 = new JButton("8");
        btn8.addActionListener(this);
        btn8.setBackground(btnNumberColor);
        btn8.setFont(font);
        btn8.setBounds(295, 319, 75, 35);
        add(btn8);

        JButton btn9 = new JButton("9");
        btn9.addActionListener(this);
        btn9.setBackground(btnNumberColor);
        btn9.setFont(font);
        btn9.setBounds(369, 319, 75, 35);
        add(btn9);

        JButton btnLog = new JButton("log");
        btnLog.addActionListener(this);
        Color btnAdvanceColor = new Color(235, 255, 250);
        btnLog.setBackground(btnAdvanceColor);
        btnLog.setFont(font);
        btnLog.setBounds(147, 319, 75, 35);
        add(btnLog);

        JButton btnAbs = new JButton("abs");
        btnAbs.addActionListener(this);
        btnAbs.setBackground(btnAdvanceColor);
        btnAbs.setFont(font);
        btnAbs.setBounds(147, 353, 75, 35);
        add(btnAbs);

        JButton btnSin = new JButton("sin");
        btnSin.addActionListener(this);
        btnSin.setBackground(btnAdvanceColor);
        btnSin.setFont(font);
        btnSin.setBounds(73, 319, 75, 35);
        add(btnSin);

        JButton btnCos = new JButton("cos");
        btnCos.addActionListener(this);
        btnCos.setBackground(btnAdvanceColor);
        btnCos.setFont(font);
        btnCos.setBounds(73, 353, 75, 35);
        add(btnCos);

        JButton btnTan = new JButton("tan");
        btnTan.addActionListener(this);
        btnTan.setBackground(btnAdvanceColor);
        btnTan.setFont(font);
        btnTan.setBounds(73, 387, 75, 35);
        add(btnTan);

        JButton btnMod = new JButton("mod");
        btnMod.addActionListener(this);
        btnMod.setBackground(btnAdvanceColor);
        btnMod.setFont(font);
        btnMod.setBounds(73, 421, 75, 35);
        add(btnMod);

        JButton btnLeftParenthesis = new JButton("(");
        btnLeftParenthesis.addActionListener(this);
        btnLeftParenthesis.setBackground(btnBasicColor);
        btnLeftParenthesis.setFont(font);
        btnLeftParenthesis.setBounds(73, 285, 75, 35);
        add(btnLeftParenthesis);

        JButton btnRightParenthesis = new JButton(")");
        btnRightParenthesis.addActionListener(this);
        btnRightParenthesis.setBackground(btnBasicColor);
        btnRightParenthesis.setFont(font);
        btnRightParenthesis.setBounds(147, 285, 75, 35);
        add(btnRightParenthesis);

        JButton btnCE = new JButton("CE");
        btnCE.addActionListener(this);
        btnCE.setBackground(btnBasicColor);
        btnCE.setFont(font);
        btnCE.setBounds(221, 285, 75, 35);
        add(btnCE);

        JButton btnC = new JButton("C");
        btnC.addActionListener(this);
        btnC.setBackground(btnBasicColor);
        btnC.setFont(font);
        btnC.setBounds(295, 285, 75, 35);
        add(btnC);

        JButton buttonBackSpace = new JButton("←");
        buttonBackSpace.addActionListener(this);
        buttonBackSpace.setBackground(btnBasicColor);
        buttonBackSpace.setFont(font);
        buttonBackSpace.setBounds(369, 285, 75, 35);
        add(buttonBackSpace);

        JButton btnDivide = new JButton("/");
        btnDivide.addActionListener(this);
        btnDivide.setBackground(btnBasicColor);
        btnDivide.setFont(fontBasic);
        btnDivide.setBounds(443, 285, 75, 35);
        add(btnDivide);

        JButton btnMultiply = new JButton("*");
        btnMultiply.addActionListener(this);
        btnMultiply.setBackground(btnBasicColor);
        btnMultiply.setFont(fontBasic);
        btnMultiply.setBounds(443, 319, 75, 35);
        add(btnMultiply);

        JButton btnMinus = new JButton("-");
        btnMinus.addActionListener(this);
        btnMinus.setBackground(btnBasicColor);
        btnMinus.setFont(fontBasic);
        btnMinus.setBounds(443, 353, 75, 35);
        add(btnMinus);

        JButton buttonPlus = new JButton("+");
        buttonPlus.addActionListener(this);
        buttonPlus.setBackground(btnBasicColor);
        buttonPlus.setFont(fontBasic);
        buttonPlus.setBounds(443, 387, 75, 35);
        add(buttonPlus);

        JButton buttonEqual = new JButton("=");
        buttonEqual.addActionListener(this);
        buttonEqual.setBackground(btnBasicColor);
        buttonEqual.setFont(fontBasic);
        buttonEqual.setBounds(443, 421, 75, 35);
        add(buttonEqual);

        JButton buttonFact = new JButton("fact");
        buttonFact.addActionListener(this);
        buttonFact.setBackground(btnAdvanceColor);
        buttonFact.setFont(font);
        buttonFact.setBounds(147, 387, 75, 35);
        add(buttonFact);

        JButton btnPower = new JButton("x^y");
        btnPower.addActionListener(this);
        btnPower.setBackground(btnAdvanceColor);
        btnPower.setFont(font);
        btnPower.setBounds(147, 421, 75, 35);
        add(btnPower);

        JButton btnRoot = new JButton("y√x");
        btnRoot.addActionListener(this);
        btnRoot.setBackground(btnAdvanceColor);
        btnRoot.setFont(font);
        btnRoot.setBounds(221, 421, 75, 35);
        add(btnRoot);

        JButton buttonDot = new JButton(".");
        buttonDot.addActionListener(this);
        buttonDot.setBackground(btnBasicColor);
        buttonDot.setFont(fontBasic);
        buttonDot.setBounds(369, 421, 75, 35);
        add(buttonDot);

        // 文本框
        JtextArea.setBackground(btnBasicColor);
        JtextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        JtextArea.setEditable(false);
        JtextArea.setLineWrap(true);
        JtextArea.setColumns(10000);
        JtextArea.setFocusable(true);
        JtextArea.setWrapStyleWord(true);
        JtextArea.addKeyListener(this);
        JScrollPane jsp = new JScrollPane(JtextArea);
        jsp.setBounds(70, 55, 450, 158);
        add(jsp);

    }

    /**
     * @param tempResult the tempResult to set
     */
    static void setTempResult(String tempResult) {
        answer.append(tempResult);
        number.append(tempResult);
        JtextArea.setText(number.toString());
    }

    /**
     * 各个按钮事件
     */
    private void action(String btnName) {
        switch (btnName) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (wasAnswer) {
                    answer.replace(0, answer.length(), "");
                    number.replace(0, number.length(), "");
                    wasAnswer = false;
                }
                number.append(btnName);
                answer.append(btnName);
                JtextArea.setText(number.toString());
                break;
            case ".":
                wasAnswer = false;
                number.append(".");
                answer.append(".");
                JtextArea.setText(number.toString());
                break;
            case "/":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("÷ ");
                answer.append("/");
                JtextArea.setText(number.toString());
                break;
            case "*":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("× ");
                answer.append("*");
                JtextArea.setText(number.toString());
                break;
            case "-":
            case "+":
            case "(":
            case ")":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append(btnName).append(" ");
                answer.append(btnName);
                JtextArea.setText(number.toString());
                break;
            case "sin":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("sin( ");
                answer.append("s(");
                JtextArea.setText(number.toString());
                break;
            case "cos":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("cos( ");
                answer.append("c(");
                JtextArea.setText(number.toString());
                break;
            case "tan":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("tan( ");
                answer.append("t(");
                JtextArea.setText(number.toString());
                break;
            case "mod":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("mod ");
                answer.append("m");
                JtextArea.setText(number.toString());
                break;
            case "log":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("log( ");
                answer.append("l(");
                JtextArea.setText(number.toString());
                break;
            case "abs":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("abs( ");
                answer.append("a(");
                JtextArea.setText(number.toString());
                break;
            case "fact":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("fact( ");
                answer.append("!(");
                JtextArea.setText(number.toString());
                break;
            case "^":
            case "x^y":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("^ ");
                answer.append("^");
                JtextArea.setText(number.toString());
                break;
            case "y√x":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("√ ");
                answer.append("q");
                JtextArea.setText(number.toString());
                break;
            case "\n":
            case "=":
                number.append(" =");
                String r;
                try {
                    r = new ScientificCalculator().getResult(answer.toString());
                    HistoryUI.updateRecord(r);
                } catch (Exception error) {
                    r = "表达式无效";
                    error.printStackTrace();
                }
                number.append('\n');
                number.append('\n');
                number.append(r);
                JtextArea.setText(number.toString());
                answer.replace(0, answer.length(), "");
                answer.append(r);
                number.replace(0, number.length(), "");
                number.append(r);
                wasAnswer = true;
                break;
            case "CE":
                wasAnswer = false;
                while (number.length() > 0 && isDigit(number.charAt(number.length() - 1))) {
                    // delete
                    number.deleteCharAt(number.length() - 1);
                    answer.deleteCharAt(answer.length() - 1);
                    JtextArea.setText(number.toString());
                }

                break;
            case "C":
                wasAnswer = false;
                answer.replace(0, answer.length(), "");
                number.replace(0, number.length(), "");
                JtextArea.setText("0");
                break;
            case "\b":
            case "←":
                wasAnswer = false;
                if (number.length() == 0) {
                    return;
                }
                if (isDigit(number.charAt(number.length() - 1))) {
                    number.replace(number.length() - 1, number.length(), "");
                    answer.replace(answer.length() - 1, answer.length(), "");
                } else {
                    number.replace(number.length() - 1, number.length(), "");
                    while (number.length() > 0 && !isDigit(number.charAt(number.length() - 1))
                            && number.charAt(number.length() - 1) != ' ') {
                        number.replace(number.length() - 1, number.length(), "");
                    }
                    number.replace(number.length() - 1, number.length(), "");

                    switch (answer.charAt(answer.length() - 1)) {
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                        case '^':
                        case 'q':
                        case ')':
                        case 'm':
                            answer.replace(answer.length() - 1, answer.length(), "");
                            break;
                        case '(':
                            answer.replace(answer.length() - 1, answer.length(), "");
                            if (answer.length() > 0) switch (answer.charAt(answer.length() - 1)) {
                                case 's':
                                case 'c':
                                case 't':
                                case '!':
                                case 'l':
                                case 'a':
                                    answer.replace(answer.length() - 1, answer.length(), "");
                                    break;
                                default:
                                    break;
                            }
                    }
                }
                JtextArea.setText(number.toString());
                break;
        }

    }


    /**
     * 判断是否为数字的一部分
     *
     * @param c 判断的字符
     * @return true 如果是数字的一部分
     */
    private boolean isDigit(char c) {
        return (c == '.' || c >= '0' && c <= '9');
    }

    /**
     * 判断前一字符是否为空格
     *
     * @return true 如果前一字符是空格
     */
    private boolean prevIsSpace() {
        return (number.charAt(number.length() - 1) == ' ');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = e.getActionCommand();
        action(btnName);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String btnName = e.getKeyChar() + "";
        action(btnName);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
