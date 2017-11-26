package com.ToolBox.evaluate;

import org.junit.Before;
import org.junit.Test;

import static com.ToolBox.evaluate.Calculator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        assert s.getResult("( 50 + 4 * 3 / 2 + 799 - 180 + 9 + 8 + ( 3 / 3 ) + 8 + ( 9 + 3 ) / 3 )").equals("705");
        assert s.getResult(" 1 + 1").equals("2");
        assert s.getResult("( 1 + 1 )").equals("2");
        assert s.getResult("( - 1 + 1 )").equals("0");
        assert s.getResult("sin ( 3.1415926535").equals("0.0000000000897932");
        assert s.getResult("cos ( - 1 + 1 )").equals("1");
        assert s.getResult("tan ( 3.1415926535").equals("-0.0000000000897932");
        assert s.getResult("sin ( cos ( tan ( 10").equals("0.7153149717720886");
        assert s.getResult("fact ( 20").equals("2432902008176640000");
        s.getResult("fact ( 0.1");
        fail("未报错");
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
        assert s.getResult("( ( ( ( ( 0 ) ) )").equals("0");
        assert s.getResult("( ( ( ( (").equals("0");
        assert s.getResult("( - 1").equals("-1");
        assert s.getResult("3 + 3 × 3 - 3 ÷ 3").equals("11");
    }

    @Test
    public void getResult1() throws Exception {
        if (!p.getResult("3 × 3", DEC).equals("9")) throw new AssertionError();
        assertEquals(p.getResult("7 Mod ( 3", DEC), "1");
        if (!p.getResult("Not ( 0", DEC).equals("-1")) throw new AssertionError();
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
    }

}