package com.ToolBox.evaluate;

import org.junit.Before;
import org.junit.Test;

import static com.ToolBox.evaluate.Calculator.*;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator s;
    Calculator p;

    @Before
    public void setUp() throws Exception {
        s = new Calculator();
        p = new Calculator();
    }

    @Test(expected = Exception.class)
    public void getResult() throws Exception {
        assertEquals(s.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )"), "705");
        assertEquals(s.getResult("3 × 3"), "9");
        assertEquals(s.getResult("5 ÷ 2"), "2.5");
        assertEquals(s.getResult(" 1 + 1"), "2");
        assertEquals(s.getResult("( 1 + 1 )"), "2");
        assertEquals(s.getResult("( - 1 + 1 )"), "0");
        assertEquals(s.getResult("sin ( 3.1415926535"), "0.0000000000897932");
        assertEquals(s.getResult("cos ( - 1 + 1 )"), "1");
        assertEquals(s.getResult("tan ( 3.1415926535"), "-0.0000000000897932");
        assertEquals(s.getResult("sin ( cos ( tan ( 10"), "0.7153149717720886");
        assertEquals(s.getResult("fact ( 20"), "2432902008176640000");
        assertEquals(s.getResult("log ( 100"), "2");
        assertEquals(s.getResult("abs ( - 100"), "100");
        assertEquals(s.getResult("9 mod 7"), "2");
        assertEquals(s.getResult("3.5 mod 0.2"), "0.1");
        assertEquals(s.getResult("3 ^ 2"), "9");
        assertTrue(s.getResult("3 ^ 0.5").startsWith("1.73"));
        assertTrue(s.getResult("3 ^ 0").startsWith("1"));
        assertEquals(s.getResult("2 √ 9"), "3");
        assertEquals(s.getResult("2 √ 0"), "0");
        assertEquals(s.getResult("0.5 √ 2"), "4");
        assertEquals(s.getResult("- 1"), "-1");
        assertEquals(s.getResult("( ( ( ( ( 0 ) ) )"), "0");
        assertEquals(s.getResult("( ( ( ( ("), "0");
        assertEquals(s.getResult("( - 1"), "-1");
        assertEquals(s.getResult("3 + 3 × 3 - 3 ÷ 3"), "11");
        s.getResult("fact ( 0.1");
        fail("未报错");
    }

    @Test
    public void getResult1() throws Exception {
        assertEquals(p.getResult("7 Mod ( 3", DEC), "1");
        assertEquals(p.getResult("3 × 3", DEC), "9");
        assertEquals(p.getResult("4 ÷ 3", DEC), "1");
        assertEquals(p.getResult("Not ( 0", DEC), "-1");
        assertEquals(p.getResult("1 + 1", BIN), "2");
        assertEquals(p.getResult("1 + 7", OCT), "8");
        assertEquals(p.getResult("1 + 9", DEC), "10");
        assertEquals(p.getResult("1 + F", HEX), "16");
        assertEquals(p.getResult("1 + f", HEX), "16");
        assertEquals(p.getResult("( 1 Or 2 + 4 ) And 8 Xor 7", DEC), "7");
        assertEquals(p.getResult("Not ( - 1", DEC), "0");
        assertEquals(p.getResult("2 And ( - 3", DEC), "0");
        assertEquals(p.getResult("1 Lsh 1", DEC), "2");
        assertEquals(p.getResult("1 Lsh 1 + ( - 1 + 2 )", DEC), "3");
        assertEquals(p.getResult("4 Rsh 1", DEC), "2");
        assertEquals(p.getResult("1 RoL 1", DEC), "2");
        assertEquals(p.getResult("1 RoR 1", DEC), Long.MIN_VALUE + "");
        assertEquals(p.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )", DEC), "705");
    }

}