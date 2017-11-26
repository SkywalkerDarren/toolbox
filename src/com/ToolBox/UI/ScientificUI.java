package com.ToolBox.UI;

import com.ToolBox.evaluate.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 科学计算器，支持结果保存即结果获取
 * 支持带优先级的四则运算，以及高级运算符
 * 支持高精度计算
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class ScientificUI extends TransparentPanelUI implements ActionListener, KeyListener {

    private static final long serialVersionUID = 688567022889591814L;
    private static JTextArea expTextArea = new JTextArea();
    private static StringBuilder number = new StringBuilder();// 用户显示
    private boolean wasAnswer = false;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnLog, btnAbs, btnSin, btnCos, btnTan, btnMod;
    private JButton btnLeftParenthesis, btnRightParenthesis;
    private JButton btnCE, btnC, buttonBackSpace, btnDivide, btnMultiply, btnMinus;
    private JButton buttonPlus, buttonEqual, buttonFact, btnPower, btnRoot, buttonDot;

    /**
     * 初始化组件
     */
    @Override
    protected void initComponent() {
        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnLog = new JButton("log");
        btnAbs = new JButton("abs");
        btnSin = new JButton("sin");
        btnCos = new JButton("cos");
        btnTan = new JButton("tan");
        btnMod = new JButton("mod");
        btnLeftParenthesis = new JButton("(");
        btnRightParenthesis = new JButton(")");
        btnCE = new JButton("CE");
        btnC = new JButton("C");
        buttonBackSpace = new JButton("←");
        btnDivide = new JButton("/");
        btnMultiply = new JButton("*");
        btnMinus = new JButton("-");
        buttonPlus = new JButton("+");
        buttonEqual = new JButton("=");
        buttonFact = new JButton("fact");
        btnPower = new JButton("x^y");
        btnRoot = new JButton("y√x");
        buttonDot = new JButton(".");
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {
        Color btnBasicColor = new Color(240, 255, 250);
        Color btnNumberColor = new Color(225, 255, 250);
        Font font = new Font("微软雅黑", Font.BOLD, 13);
        Font fontBasic = new Font("微软雅黑", Font.BOLD, 14);

        // 按钮组
        btn0.setBackground(btnNumberColor);
        btn0.setFont(font);
        btn0.setBounds(295, 421, 75, 35);
        add(btn0);

        btn1.setBackground(btnNumberColor);
        btn1.setFont(font);
        btn1.setBounds(221, 387, 75, 35);
        add(btn1);

        btn2.setBackground(btnNumberColor);
        btn2.setFont(font);
        btn2.setBounds(295, 387, 75, 35);
        add(btn2);

        btn3.setBackground(btnNumberColor);
        btn3.setFont(font);
        btn3.setBounds(369, 387, 75, 35);
        add(btn3);

        btn4.setBackground(btnNumberColor);
        btn4.setFont(font);
        btn4.setBounds(221, 353, 75, 35);
        add(btn4);

        btn5.setBackground(btnNumberColor);
        btn5.setFont(font);
        btn5.setBounds(295, 353, 75, 35);
        add(btn5);

        btn6.setBackground(btnNumberColor);
        btn6.setFont(font);
        btn6.setBounds(369, 353, 75, 35);
        add(btn6);

        btn7.setBackground(btnNumberColor);
        btn7.setFont(font);
        btn7.setBounds(221, 319, 75, 35);
        add(btn7);

        btn8.setBackground(btnNumberColor);
        btn8.setFont(font);
        btn8.setBounds(295, 319, 75, 35);
        add(btn8);

        btn9.setBackground(btnNumberColor);
        btn9.setFont(font);
        btn9.setBounds(369, 319, 75, 35);
        add(btn9);

        Color btnAdvanceColor = new Color(235, 255, 250);
        btnLog.setBackground(btnAdvanceColor);
        btnLog.setFont(font);
        btnLog.setBounds(147, 319, 75, 35);
        add(btnLog);

        btnAbs.setBackground(btnAdvanceColor);
        btnAbs.setFont(font);
        btnAbs.setBounds(147, 353, 75, 35);
        add(btnAbs);

        btnSin.setBackground(btnAdvanceColor);
        btnSin.setFont(font);
        btnSin.setBounds(73, 319, 75, 35);
        add(btnSin);

        btnCos.setBackground(btnAdvanceColor);
        btnCos.setFont(font);
        btnCos.setBounds(73, 353, 75, 35);
        add(btnCos);

        btnTan.setBackground(btnAdvanceColor);
        btnTan.setFont(font);
        btnTan.setBounds(73, 387, 75, 35);
        add(btnTan);

        btnMod.setBackground(btnAdvanceColor);
        btnMod.setFont(font);
        btnMod.setBounds(73, 421, 75, 35);
        add(btnMod);

        btnLeftParenthesis.setBackground(btnBasicColor);
        btnLeftParenthesis.setFont(font);
        btnLeftParenthesis.setBounds(73, 285, 75, 35);
        add(btnLeftParenthesis);

        btnRightParenthesis.setBackground(btnBasicColor);
        btnRightParenthesis.setFont(font);
        btnRightParenthesis.setBounds(147, 285, 75, 35);
        add(btnRightParenthesis);

        btnCE.setBackground(btnBasicColor);
        btnCE.setFont(font);
        btnCE.setBounds(221, 285, 75, 35);
        add(btnCE);

        btnC.setBackground(btnBasicColor);
        btnC.setFont(font);
        btnC.setBounds(295, 285, 75, 35);
        add(btnC);

        buttonBackSpace.setBackground(btnBasicColor);
        buttonBackSpace.setFont(font);
        buttonBackSpace.setBounds(369, 285, 75, 35);
        add(buttonBackSpace);

        btnDivide.setBackground(btnBasicColor);
        btnDivide.setFont(fontBasic);
        btnDivide.setBounds(443, 285, 75, 35);
        add(btnDivide);

        btnMultiply.setBackground(btnBasicColor);
        btnMultiply.setFont(fontBasic);
        btnMultiply.setBounds(443, 319, 75, 35);
        add(btnMultiply);

        btnMinus.setBackground(btnBasicColor);
        btnMinus.setFont(fontBasic);
        btnMinus.setBounds(443, 353, 75, 35);
        add(btnMinus);

        buttonPlus.setBackground(btnBasicColor);
        buttonPlus.setFont(fontBasic);
        buttonPlus.setBounds(443, 387, 75, 35);
        add(buttonPlus);

        buttonEqual.setBackground(btnBasicColor);
        buttonEqual.setFont(fontBasic);
        buttonEqual.setBounds(443, 421, 75, 35);
        add(buttonEqual);

        buttonFact.setBackground(btnAdvanceColor);
        buttonFact.setFont(font);
        buttonFact.setBounds(147, 387, 75, 35);
        add(buttonFact);

        btnPower.setBackground(btnAdvanceColor);
        btnPower.setFont(font);
        btnPower.setBounds(147, 421, 75, 35);
        add(btnPower);

        btnRoot.setBackground(btnAdvanceColor);
        btnRoot.setFont(font);
        btnRoot.setBounds(221, 421, 75, 35);
        add(btnRoot);

        buttonDot.setBackground(btnBasicColor);
        buttonDot.setFont(fontBasic);
        buttonDot.setBounds(369, 421, 75, 35);
        add(buttonDot);

        // 文本框
        expTextArea.setBackground(btnBasicColor);
        expTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        expTextArea.setEditable(false);
        expTextArea.setLineWrap(true);
        expTextArea.setColumns(10000);
        expTextArea.setFocusable(true);
        expTextArea.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(expTextArea);
        jsp.setBounds(70, 55, 450, 158);
        add(jsp);

    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        btn0.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnLog.addActionListener(this);
        btnAbs.addActionListener(this);
        btnSin.addActionListener(this);
        btnCos.addActionListener(this);
        btnTan.addActionListener(this);
        btnMod.addActionListener(this);
        btnLeftParenthesis.addActionListener(this);
        btnRightParenthesis.addActionListener(this);
        btnCE.addActionListener(this);
        btnC.addActionListener(this);
        buttonBackSpace.addActionListener(this);
        btnDivide.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnMinus.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonEqual.addActionListener(this);
        buttonFact.addActionListener(this);
        btnPower.addActionListener(this);
        btnRoot.addActionListener(this);
        buttonDot.addActionListener(this);
        expTextArea.addKeyListener(this);
        expTextArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    RightClickMenu menu = new RightClickMenu(expTextArea);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * 构造计算器布局
     */
    ScientificUI() {
        super();
    }

    /**
     * 设定存储结果
     *
     * @param tempResult 字符串数值
     */
    static void setTempResult(String tempResult) {
        number.append(tempResult);
        expTextArea.setText(number.toString());
    }

    /**
     * 各个可触发事件
     * @see ScientificUI#actionPerformed(ActionEvent)
     * @see ScientificUI#keyPressed(KeyEvent)
     */
    private void action(String btnName) {
        switch (btnName) {
            // 数字
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
                    number.replace(0, number.length(), "");
                    wasAnswer = false;
                }
                number.append(btnName);
                expTextArea.setText(number.toString());
                break;
            // 特殊
            case ".":
                wasAnswer = false;
                number.append(".");
                expTextArea.setText(number.toString());
                break;
            // 双目
            case "/":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("÷ ");
                expTextArea.setText(number.toString());
                break;
            case "*":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("× ");
                expTextArea.setText(number.toString());
                break;
            case "-":
            case "+":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                try {
                    if (number.charAt(number.length() - 2) == '(') number.append(btnName);
                    else number.append(btnName).append(" ");
                } catch (StringIndexOutOfBoundsException e) {
                    number.append(btnName).append(" ");
                }
                expTextArea.setText(number.toString());
                break;
            case "(":
            case ")":
            case "mod":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append(btnName).append(" ");
                expTextArea.setText(number.toString());
                break;
            case "^":
            case "x^y":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("^ ");
                expTextArea.setText(number.toString());
                break;
            case "y√x":
                wasAnswer = false;
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append("√ ");
                expTextArea.setText(number.toString());
                break;
            // 单目
            case "sin":
            case "cos":
            case "tan":
            case "abs":
            case "fact":
            case "log":
                if (wasAnswer) {
                    number.replace(0, number.length(), "");
                    wasAnswer = false;
                }
                if (!(number.length() > 0 && prevIsSpace())) {
                    number.append(" ");
                }
                number.append(btnName).append(" ( ");
                expTextArea.setText(number.toString());
                break;
            // 控制符
            case "\n":
            case "=":
                String r;
                try {
                    r = new Calculator().getResult(number.toString());
                    HistoryUI.updateRecord(r);
                } catch (Exception error) {
                    r = "表达式无效";
                    error.printStackTrace();
                }
                number.append(" =");
                number.append('\n');
                number.append('\n');
                number.append(r);
                expTextArea.setText(number.toString());
                number.replace(0, number.length(), "");
                number.append(r);
                wasAnswer = true;
                break;
            case "CE":
                wasAnswer = false;
                while (number.length() > 0 && isDigit(number.charAt(number.length() - 1))) {
                    number.deleteCharAt(number.length() - 1);
                    expTextArea.setText(number.toString());
                }

                break;
            case "C":
                wasAnswer = false;
                number.replace(0, number.length(), "");
                expTextArea.setText("0");
                break;
            case "\b":
            case "←":
                wasAnswer = false;
                if (number.length() == 0) {
                    return;
                }
                if (isDigit(number.charAt(number.length() - 1))) {
                    number.replace(number.length() - 1, number.length(), "");
                } else {
                    number.replace(number.length() - 1, number.length(), "");
                    while (number.length() > 0 && !isDigit(number.charAt(number.length() - 1))
                            && number.charAt(number.length() - 1) != ' ') {
                        number.replace(number.length() - 1, number.length(), "");
                    }
                    number.replace(number.length() - 1, number.length(), "");

                }
                expTextArea.setText(number.toString());
                break;
            default:
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
        return (c == '+' || c == '-' || c == '.' || c >= '0' && c <= '9');
    }

    /**
     * 判断前一字符是否为空格
     *
     * @return true 如果前一字符是空格
     */
    private boolean prevIsSpace() {
        return (number.charAt(number.length() - 1) == ' ');
    }

    /**
     * 通过按钮键入表达式
     *
     * @param e 按钮事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = e.getActionCommand();
        action(btnName);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 通过按键键入表达式
     *
     * @param e 键盘事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        String btnName = e.getKeyChar() + "";
        action(btnName);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}