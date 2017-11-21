package com.ToolBox.evaluate;

//support + - * / log ^ ! mod cos sin tan yroot abs

/**
 * 科学计算器运算方法
 *
 * @author 杨弘
 */
public class ScientificCalculator extends Calculator {

    /**
     * 判断是否是合法的首字符
     *
     * @param c 一个操作符
     * @return true 如果合法
     */
    private static boolean isLegalFirstOperator(char c) {
        // ( - + mod cos sin tan log ln sqrt
        return (c == '(' || c == '-' || c == '+' || c == 'm' || c == 'c' || c == 's' || c == 't' || c == 'l' || c == 'q'
                || c == '!' || c == 'a');
    }

//    protected void calcExp(Stack<BigDecimal> val, Stack<String> flag) {
//
//        switch (op.getName()) {
//            case "+":
//                b = val.pop();
//                a = val.pop();
//                val.push(a.add(b));
//                break;
//            case "-":
//                b = val.pop();
//                a = val.pop();
//                val.push(a.subtract(b));
//                break;
//            case "*":
//                b = val.pop();
//                a = val.pop();
//                val.push(a.multiply(b));
//                break;
//            case "/":
//                b = val.pop();
//                a = val.pop();
//                val.push(a.divide(b, 32, BigDecimal.ROUND_HALF_UP));
//                break;
//            // abs
//            case "abs":
//                b = val.pop();
//                val.push(b.abs());
//                break;
//            // yroot
//            case "yroot":
//                b = val.pop();
//                a = val.pop();
//                val.push(BigDecimalMath.root(b, a, mc));
//                break;
//            // sin
//            case "sin":
//                b = val.pop();
//                val.push(BigDecimalMath.sin(b, mc));
//                break;
//            // cos
//            case "cos":
//                b = val.pop();
//                val.push(BigDecimalMath.cos(b, mc));
//                break;
//            // tan
//            case "tan":
//                b = val.pop();
//                val.push(BigDecimalMath.tan(b, mc));
//                break;
//            // mod
//            case "mod":
//                b = val.pop();
//                a = val.pop();
//                val.push(a.divideAndRemainder(b)[1]);
//                break;
//            // log10
//            case "log10":
//                b = val.pop();
//                val.push(BigDecimalMath.log10(b, mc));
//                break;
//            // fact
//            case "!":
//                b = val.pop();
//                try {
//                    val.push(BigDecimalMath.factorial(b.intValueExact()));
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("阶乘应为整数");
//                }
//                break;
//            case "^":
//                b = val.pop();
//                a = val.pop();
//                val.push(BigDecimalMath.pow(a, b, mc));
//                break;
//            default:
//                throw new IllegalArgumentException("无此操作符");
//        }
//    }

//    /**
//     * 解析表达式字符串到字符串数组
//     *
//     * @param exp 表达式字符串
//     * @return 字符串数组
//     */
//    @Override
//    protected String[] explain(String s) {
//        if (s.isEmpty()) {
//            throw new IllegalArgumentException("表达式为空");
//        }
//        ArrayList<String> expression = new ArrayList<>();
//        StringBuilder digit = new StringBuilder();
//        int dot = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (i == 0 || s.charAt(i - 1) == '(') {
//                // check the first char ". - + ("
//                if (!isLegalFirstOperator(s.charAt(i)) && !isDigit(s.charAt(i))) {
//                    throw new IllegalArgumentException("\"" + s.charAt(i) + "\" 不应在表达式的开头");
//                } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
//                    try {
//                        if (isDigit(s.charAt(i + 1))) {
//                            digit.append(s.charAt(i));
//                            continue;
//                        } else {
//                            throw new IllegalArgumentException("有多余的正负号");
//                        }
//                    } catch (Exception e) {
//                        throw new IllegalArgumentException("正负号不应在末尾");
//                    }
//                } else if (!isDigit(s.charAt(i))) {
//                    expression.add("" + s.charAt(i));
//                    continue;
//                }
//            }
//            // check other char
//            if (s.charAt(i) == '.' || isDigit(s.charAt(i))) {
//                if (s.charAt(i) == '.') {
//                    dot++;
//                }
//                if (dot > 1) {
//                    throw new IllegalArgumentException("有多余的小数点");
//                } else {
//                    digit.append(s.charAt(i));
//                    if (i + 1 >= s.length() || !isDigit(s.charAt(i + 1)) && s.charAt(i + 1) != '.') {
//                        expression.add(digit.toString());
//                        dot = 0;
//                        digit = new StringBuilder();
//                    }
//                }
//            } else {
//                expression.add("" + s.charAt(i));
//            }
//
//        }
//        // move to string[]
//        String[] exp = new String[expression.size() + 2];
//        exp[0] = "1";
//        exp[1] = "*";
//        for (int i = 0; i < expression.size(); i++) {
//            exp[i + 2] = expression.get(i);
//        }
//        return exp;
//    }

}