package com.ToolBox.UI;

import com.ToolBox.evaluate.Calculator;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 程序员计算器，支持二进制键盘，对数值进行进制转换，位运算等
 * 全键盘可以对数值进行快速编辑，但数值一旦编辑，将重置结果
 *
 * @author 杨弘，徐祥亮，朱可欣
 */
class ProgramerUI extends TransparentPanelUI implements ActionListener, MouseListener {

    private static final long serialVersionUID = 8745040693717225813L;
    private static JTextArea textAreaTop = new JTextArea();
    private static JTextField textFieldBinary = new JTextField();
    private static JTextField textFieldOctonary = new JTextField();
    private static JTextField textFieldDecimal = new JTextField();
    private static JTextField textFieldHexadecimal = new JTextField();
    private static int radix = Calculator.DEC;
    private static StringBuilder expression = new StringBuilder();
    private static StringBuilder expUI = new StringBuilder();
    private static final Color color = new Color(240, 255, 255);
    private static final Font font = new Font("微软雅黑", Font.PLAIN, 13);
    private Long result = 0L;
    private JButton[] btnBit;
    private JButton btnRol, btnRor, btnLsh, btnRsh, btnLeft, btnRight;
    private JButton btnCE, btnClean, btnBackSpace;
    private JButton btnOr, btnXor, btnMinus, btnNot, btnAnd, btnMod, btnPlus, btnEqual, btnDivided, btnMultiply;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnA, btnB, btnC, btnD, btnE, btnF;
    private JRadioButton btnBinary, btnOctonary, btnDecimal, btnHexadecimal;
    private JPanel fullKeyPanel, bitKeyPanel;

    /**
     * 初始化组件
     */
    @Override
    protected void initCompoment() {
        fullKeyPanel = new JPanel();
        bitKeyPanel = new JPanel();
        btnBit = new JButton[64];
        btnRol = new JButton("RoL");
        btnRor = new JButton("RoR");
        btnLsh = new JButton("Lsh");
        btnRsh = new JButton("Rsh");
        btnCE = new JButton("CE");
        btnClean = new JButton("c");
        btnBackSpace = new JButton("\u2190");
        btnOr = new JButton("Or");
        btnA = new JButton("A");
        btnB = new JButton("B");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnPlus = new JButton("+");
        btnXor = new JButton("Xor");
        btnC = new JButton("C");
        btnD = new JButton("D");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btnMinus = new JButton("-");
        btnNot = new JButton("Not");
        btnE = new JButton("E");
        btnF = new JButton("F");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btnAnd = new JButton("And");
        btnMod = new JButton("Mod");
        btnLeft = new JButton("(");
        btnRight = new JButton(")");
        btn0 = new JButton("0");
        btnEqual = new JButton("=");
        btnDivided = new JButton("/");
        btnMultiply = new JButton("*");
        btnBinary = new JRadioButton("BIN");
        btnOctonary = new JRadioButton("OCT");
        btnDecimal = new JRadioButton("DEC", true);
        btnHexadecimal = new JRadioButton("HEX");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(btnHexadecimal);
        buttonGroup.add(btnDecimal);
        buttonGroup.add(btnOctonary);
        buttonGroup.add(btnBinary);
    }

    /**
     * 初始化布局
     */
    @Override
    protected void initUI() {

        //最上方显示区域
        textAreaTop.setEditable(false);
        textAreaTop.setBackground(new Color(250, 255, 255));
        textAreaTop.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        textAreaTop.setBounds(13, 12, 570, 60);
        textAreaTop.setColumns(1000);
        textAreaTop.addMouseListener(this);
        add(textAreaTop);

        //二进制
        btnBinary.setBounds(13, 89, 51, 23);
        add(btnBinary);

        //八进制
        btnOctonary.setBounds(13, 140, 51, 23);
        add(btnOctonary);

        //十进制
        btnDecimal.setBounds(13, 191, 51, 23);
        add(btnDecimal);

        //十六进制
        btnHexadecimal.setBounds(13, 244, 51, 23);
        add(btnHexadecimal);

        //二进制文本框
        textFieldBinary.setEditable(false);
        textFieldBinary.setBackground(color);
        textFieldBinary.setFont(font);
        textFieldBinary.setBounds(83, 85, 500, 30);
        textFieldBinary.setColumns(100);
        add(textFieldBinary);

        //八进制文本框
        textFieldOctonary.setEditable(false);
        textFieldOctonary.setBackground(color);
        textFieldOctonary.setFont(font);
        textFieldOctonary.setColumns(100);
        textFieldOctonary.setBounds(83, 136, 500, 30);
        add(textFieldOctonary);

        //十进制文本框
        textFieldDecimal.setEditable(false);
        textFieldDecimal.setBackground(color);
        textFieldDecimal.setFont(font);
        textFieldDecimal.setColumns(100);
        textFieldDecimal.setBounds(83, 187, 500, 30);
        add(textFieldDecimal);


        //十六进制文本框
        textFieldHexadecimal.setEditable(false);
        textFieldHexadecimal.setBackground(color);
        textFieldHexadecimal.setFont(font);
        textFieldHexadecimal.setColumns(100);
        textFieldHexadecimal.setBounds(83, 240, 500, 30);
        add(textFieldHexadecimal);

        //最下方标签页
        JTabbedPane tabbedPaneDisplay = new JTabbedPane(JTabbedPane.TOP);
        tabbedPaneDisplay.setFont(font);
        tabbedPaneDisplay.setBounds(13, 275, 567, 214);
        add(tabbedPaneDisplay);

        fullKeyPanel.setOpaque(false);
        fullKeyPanel.setBorder(BorderFactory.createEtchedBorder());
        fullKeyPanel.setBackground(new Color(245, 255, 255));
        tabbedPaneDisplay.addTab("全键盘", null, fullKeyPanel, null);
        fullKeyPanel.setLayout(null);
        fullKeyboard(fullKeyPanel);

        bitKeyPanel.setOpaque(false);
        bitKeyPanel.setBorder(BorderFactory.createEtchedBorder());
        bitKeyPanel.setBackground(new Color(245, 255, 255));
        tabbedPaneDisplay.addTab("比特键盘", null, bitKeyPanel, null);
        bitKeyPanel.setLayout(null);
        bitKeyboard(bitKeyPanel);
    }

    /**
     * 建立监听事件
     */
    @Override
    protected void createAction() {
        textFieldBinary.addMouseListener(this);
        textFieldOctonary.addMouseListener(this);
        textFieldDecimal.addMouseListener(this);
        textFieldHexadecimal.addMouseListener(this);
    }

    /**
     * 初始化UI窗口，摆放控件位置及设定基本配置
     */
    public ProgramerUI() {
        super();
    }

    /**
     * 设定从历史记录所获取的结果，显示到UI上并加载到表达式中
     *
     * @param tempResult 获取的结果
     */
    static void setTempResult(String tempResult) {
        if (tempResult.contains(".")) {
            StringBuilder sb = new StringBuilder(tempResult);
            sb.delete(tempResult.indexOf("."), sb.length());
            tempResult = sb.toString();
        }
        Long temp = Long.valueOf(tempResult);
        expUI.append(Long.toString(temp, radix));
        expression.append(Long.toString(temp, radix));
        textAreaTop.setText(expUI.toString());
        textFieldBinary.setText(Long.toBinaryString(temp));
        textFieldOctonary.setText(Long.toOctalString(temp));
        textFieldDecimal.setText(Long.toString(temp));
        textFieldHexadecimal.setText(Long.toHexString(temp));
    }

    private void fullKeyboard(JPanel panel) {
        btnRol.setFont(font);
        btnRol.setBackground(color);
        btnRol.setBounds(75, 16, 60, 30);
        panel.add(btnRol);
        btnRol.addActionListener(this);


        btnRor.setBackground(color);
        btnRor.setFont(font);
        btnRor.setBounds(134, 16, 60, 30);
        panel.add(btnRor);
        btnRor.addActionListener(this);

        btnLsh.setBackground(color);
        btnLsh.setFont(font);
        btnLsh.setBounds(193, 16, 60, 30);
        panel.add(btnLsh);
        btnLsh.addActionListener(this);

        btnRsh.setBackground(color);
        btnRsh.setFont(font);
        btnRsh.setBounds(252, 16, 60, 30);
        panel.add(btnRsh);
        btnRsh.addActionListener(this);

        btnCE.setBackground(color);
        btnCE.setFont(font);
        btnCE.setBounds(311, 16, 60, 30);
        panel.add(btnCE);
        btnCE.addActionListener(this);

        btnClean.setBackground(color);
        btnClean.setFont(font);
        btnClean.setBounds(370, 16, 60, 30);
        panel.add(btnClean);
        btnClean.addActionListener(this);

        btnBackSpace.setBackground(color);
        btnBackSpace.setFont(font);
        btnBackSpace.setBounds(429, 16, 60, 30);
        panel.add(btnBackSpace);
        btnBackSpace.addActionListener(this);


        btnOr.setFont(font);
        btnOr.setBackground(color);
        btnOr.setBounds(75, 45, 60, 30);
        panel.add(btnOr);
        btnOr.addActionListener(this);

        btnA.setBackground(color);
        btnA.setFont(font);
        btnA.setBounds(134, 45, 60, 30);
        btnA.setEnabled(false);
        panel.add(btnA);
        btnA.addActionListener(this);

        btnB.setBackground(color);
        btnB.setFont(font);
        btnB.setBounds(193, 45, 60, 30);
        btnB.setEnabled(false);
        panel.add(btnB);
        btnB.addActionListener(this);

        btn7.setBackground(color);
        btn7.setFont(font);
        btn7.setBounds(252, 45, 60, 30);
        panel.add(btn7);
        btn7.addActionListener(this);

        btn8.setBackground(color);
        btn8.setFont(font);
        btn8.setBounds(311, 45, 60, 30);
        panel.add(btn8);
        btn8.addActionListener(this);

        btn9.setBackground(color);
        btn9.setFont(font);
        btn9.setBounds(370, 45, 60, 30);
        panel.add(btn9);
        btn9.addActionListener(this);

        btnPlus.setBackground(color);
        btnPlus.setFont(font);
        btnPlus.setBounds(429, 45, 60, 30);
        panel.add(btnPlus);
        btnPlus.addActionListener(this);

        btnXor.setFont(font);
        btnXor.setBackground(color);
        btnXor.setBounds(75, 74, 60, 30);
        panel.add(btnXor);
        btnXor.addActionListener(this);


        btnC.setBackground(color);
        btnC.setFont(font);
        btnC.setBounds(134, 74, 60, 30);
        btnC.setEnabled(false);
        panel.add(btnC);
        btnC.addActionListener(this);

        btnD.setBackground(color);
        btnD.setFont(font);
        btnD.setBounds(193, 74, 60, 30);
        btnD.setEnabled(false);
        panel.add(btnD);
        btnD.addActionListener(this);

        btn4.setBackground(color);
        btn4.setFont(font);
        btn4.setBounds(252, 74, 60, 30);
        panel.add(btn4);
        btn4.addActionListener(this);

        btn5.setBackground(color);
        btn5.setFont(font);
        btn5.setBounds(311, 74, 60, 30);
        panel.add(btn5);
        btn5.addActionListener(this);

        btn6.setBackground(color);
        btn6.setFont(font);
        btn6.setBounds(370, 74, 60, 30);
        panel.add(btn6);
        btn6.addActionListener(this);

        btnMinus.setBackground(color);
        btnMinus.setFont(font);
        btnMinus.setBounds(429, 74, 60, 30);
        panel.add(btnMinus);
        btnMinus.addActionListener(this);

        btnNot.setFont(font);
        btnNot.setBackground(color);
        btnNot.setBounds(75, 103, 60, 30);
        panel.add(btnNot);
        btnNot.addActionListener(this);

        btnE.setBackground(color);
        btnE.setFont(font);
        btnE.setBounds(134, 103, 60, 30);
        btnE.setEnabled(false);
        panel.add(btnE);
        btnE.addActionListener(this);

        btnF.setBackground(color);
        btnF.setFont(font);
        btnF.setBounds(193, 103, 60, 30);
        btnF.setEnabled(false);
        panel.add(btnF);
        btnF.addActionListener(this);

        btn1.setBackground(color);
        btn1.setFont(font);
        btn1.setBounds(252, 103, 60, 30);
        panel.add(btn1);
        btn1.addActionListener(this);

        btn2.setBackground(color);
        btn2.setFont(font);
        btn2.setBounds(311, 103, 60, 30);
        panel.add(btn2);
        btn2.addActionListener(this);

        btn3.setBackground(color);
        btn3.setFont(font);
        btn3.setBounds(370, 103, 60, 30);
        panel.add(btn3);
        btn3.addActionListener(this);

        btnMultiply.setBackground(color);
        btnMultiply.setFont(font);
        btnMultiply.setBounds(429, 103, 60, 30);
        panel.add(btnMultiply);
        btnMultiply.addActionListener(this);

        btnAnd.setFont(font);
        btnAnd.setBackground(color);
        btnAnd.setBounds(75, 132, 60, 30);
        panel.add(btnAnd);
        btnAnd.addActionListener(this);

        btnMod.setBackground(color);
        btnMod.setFont(font);
        btnMod.setBounds(134, 132, 60, 30);
        panel.add(btnMod);
        btnMod.addActionListener(this);

        btnLeft.setBackground(color);
        btnLeft.setFont(font);
        btnLeft.setBounds(193, 132, 60, 30);
        panel.add(btnLeft);
        btnLeft.addActionListener(this);

        btnRight.setBackground(color);
        btnRight.setFont(font);
        btnRight.setBounds(252, 132, 60, 30);
        panel.add(btnRight);
        btnRight.addActionListener(this);

        btn0.setBackground(color);
        btn0.setFont(font);
        btn0.setBounds(311, 132, 60, 30);
        panel.add(btn0);
        btn0.addActionListener(this);

        btnEqual.setBackground(color);
        btnEqual.setFont(font);
        btnEqual.setBounds(370, 132, 60, 30);
        panel.add(btnEqual);
        btnEqual.addActionListener(this);

        btnDivided.setBackground(color);
        btnDivided.setFont(font);
        btnDivided.setBounds(429, 132, 60, 30);
        panel.add(btnDivided);
        btnDivided.addActionListener(this);

        btnBinary.addActionListener(this);

        btnOctonary.addActionListener(this);

        btnDecimal.addActionListener(this);

        btnHexadecimal.addActionListener(this);

    }

    private void bitKeyboard(JPanel panel) {
        JLabel[] lblIndex = new JLabel[16];
        final Font bitFont = new Font("微软雅黑", Font.BOLD, 10);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    btnBit[index] = new JButton("0");
                    btnBit[index].setBackground(color);
                    btnBit[index].setFont(bitFont);
                    btnBit[index].setBounds(j * 143 + k * 34, i * 44, 34, 30);
                    panel.add(btnBit[index]);
                    btnBit[index].addActionListener(this);
                    index++;
                }
                lblIndex[4 * i + j] = new JLabel((4 * (4 * i + j)) + "", JLabel.CENTER);
                lblIndex[4 * i + j].setBackground(color);
                lblIndex[4 * i + j].setFont(bitFont);
                lblIndex[4 * i + j].setBounds(j * 143, i * 44 + 21, 34, 30);
                panel.add(lblIndex[4 * i + j]);
            }
        }
    }

    private void bitAction(int i) {
        expUI.replace(0, expUI.length(), "");
        expression.replace(0, expression.length(), "");
        if (btnBit[i].getText().equals("0")) {
            btnBit[i].setText("1");
            result += 1L << i;
        } else {
            btnBit[i].setText("0");
            result -= 1L << i;
        }
        textFieldBinary.setText(Long.toBinaryString(result));
        textFieldOctonary.setText(Long.toOctalString(result));
        textFieldDecimal.setText(result + "");
        textFieldHexadecimal.setText(Long.toHexString(result).toUpperCase());
        switch (radix) {
            case Calculator.BIN:
                textAreaTop.setText(textFieldBinary.getText());
                break;
            case Calculator.OCT:
                textAreaTop.setText(textFieldOctonary.getText());
                break;
            case Calculator.DEC:
                textAreaTop.setText(textFieldDecimal.getText());
                break;
            case Calculator.HEX:
                textAreaTop.setText(textFieldHexadecimal.getText());
                break;
            default:
                throw new IllegalArgumentException("无此进制");
        }
        expression.append(Long.toString(result, radix));
        expUI.append(Long.toString(result, radix));
    }

    /**
     * 判断前一字符是否为数字
     *
     * @return true 如果前一字符是数字
     */
    private boolean prevIsDigit() {
        char c = expUI.charAt(expUI.length() - 1);
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private static void setRadix(int radix) {
        ProgramerUI.radix = radix;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < btnBit.length; i++) {
            if (e.getSource().equals(btnBit[i])) {
                bitAction(i);
                return;
            }
        }
        String btnName = e.getActionCommand();
        switch (btnName) {
            //**************进制选择*********************
            case "BIN":
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                btn5.setEnabled(false);
                btn6.setEnabled(false);
                btn7.setEnabled(false);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                setRadix(Calculator.BIN);
                textAreaTop.setText(textFieldBinary.getText());
                expUI.replace(0, expUI.length(), "");
                expression.replace(0, expression.length(), "");
                expUI.append(textFieldBinary.getText());
                expression.append(textFieldBinary.getText());
                break;
            case "OCT":
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                radix = Calculator.OCT;
                textAreaTop.setText(textFieldOctonary.getText());
                expUI.replace(0, expUI.length(), "");
                expression.replace(0, expression.length(), "");
                expUI.append(textFieldOctonary.getText());
                expression.append(textFieldOctonary.getText());
                break;
            case "DEC":
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);
                btnE.setEnabled(false);
                btnF.setEnabled(false);
                radix = Calculator.DEC;
                textAreaTop.setText(textFieldDecimal.getText());
                expUI.replace(0, expUI.length(), "");
                expression.replace(0, expression.length(), "");
                expUI.append(textFieldDecimal.getText());
                expression.append(textFieldDecimal.getText());
                break;
            case "HEX":
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btnA.setEnabled(true);
                btnB.setEnabled(true);
                btnC.setEnabled(true);
                btnD.setEnabled(true);
                btnE.setEnabled(true);
                btnF.setEnabled(true);
                radix = Calculator.HEX;
                textAreaTop.setText(textFieldHexadecimal.getText());
                expUI.replace(0, expUI.length(), "");
                expression.replace(0, expression.length(), "");
                expUI.append(textFieldHexadecimal.getText());
                expression.append(textFieldHexadecimal.getText());
                break;
            //***************************数值输入***********
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
            case "A":
            case "B":
            case "C":
            case "D":
            case "E":
            case "F":
                expression.append(btnName);
                expUI.append(btnName);
                textAreaTop.setText(expUI.toString());
                break;
            //*************************运算符*************
            case "RoL":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append(";");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "RoR":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append(":");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Lsh":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("<");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Rsh":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append(">");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Or":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("|");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Xor":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("^");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Not":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("~");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "And":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("&");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "Mod":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append("%");
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            case "(":
            case ")":
            case "+":
            case "-":
            case "*":
            case "/":
                if (expUI.length() > 0 && prevIsDigit()) {
                    expUI.append(" ");
                }
                expression.append(btnName);
                expUI.append(btnName).append(" ");
                textAreaTop.setText(expUI.toString());
                break;
            //*************************控制符***********
            case "\n":
            case "=":
                String r;
                try {
//                    r = new ProgramerCalculator().getResult(expression.toString(), radix);
//                    result = Long.valueOf(r);
                    textAreaTop.setText(Long.toString(result, radix));
                    textFieldBinary.setText(Long.toBinaryString(result));
                    textFieldOctonary.setText(Long.toOctalString(result));
                    textFieldDecimal.setText(Long.toString(result));
                    textFieldHexadecimal.setText(Long.toHexString(result).toUpperCase());
                    HistoryUI.updateRecord(Long.toString(result));

                    int len = Long.toBinaryString(result).length();
                    for (int i = 0; i < 64; i++) {
                        if (i < len) {
                            btnBit[i].setText(Long.toBinaryString(result).charAt(len - 1 - i) + "");
                        } else {
                            btnBit[i].setText("0");
                        }
                    }
                    expUI.replace(0, expUI.length(), "");
                    expression.replace(0, expression.length(), "");
                    expUI.append(Long.toString(result, radix));
                    expression.append(Long.toString(result, radix));
                } catch (Exception e2) {
                    r = "表达式无效";
                    textAreaTop.setText(r);
                    textFieldBinary.setText("");
                    textFieldOctonary.setText("");
                    textFieldDecimal.setText("");
                    textFieldHexadecimal.setText("");
                    expUI.replace(0, expUI.length(), "");
                    expression.replace(0, expression.length(), "");
                    e2.printStackTrace();
                }
                break;
            case "CE":
                while (expUI.length() > 0 && prevIsDigit()) {
                    expUI.deleteCharAt(expUI.length() - 1);
                    expression.deleteCharAt(expression.length() - 1);
                }
                textAreaTop.setText(expUI.toString());
                break;
            case "c":
                expUI.replace(0, expUI.length(), "");
                expression.replace(0, expression.length(), "");
                textAreaTop.setText(expUI.toString());
                break;
            case "\b":
            case "←":
                if (expUI.length() == 0) {
                    return;
                }

                expression.deleteCharAt(expression.length() - 1);
                char c = 0;
                if (expression.length() > 0) {
                    c = expression.charAt(expression.length() - 1);
                }
                if (expUI.charAt(expUI.length() - 1) == ' ') {
                    do {
                        expUI.deleteCharAt(expUI.length() - 1);
                    } while (expUI.charAt(expUI.length() - 1) != ' ');
                    if ((c >= '0' && c <= '9')
                            || (c >= 'A' && c <= 'F')
                            || (c >= 'a' && c <= 'f')) {
                        expUI.deleteCharAt(expUI.length() - 1);
                    }
                } else {
                    expUI.deleteCharAt(expUI.length() - 1);
                }
                textAreaTop.setText(expUI.toString());
                break;
            default:
                throw new IllegalArgumentException("无此操作符");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            RightClickMenu menu = new RightClickMenu((JTextComponent) e.getComponent());
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
}