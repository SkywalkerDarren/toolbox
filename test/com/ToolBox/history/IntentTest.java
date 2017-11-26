package com.ToolBox.history;

import org.junit.BeforeClass;
import org.junit.Test;

public class IntentTest {
    private static Long a = 1L;
    private static Long b = 2L;
    private static Long c = 3L;
    private static Long d = 4L;

    @BeforeClass
    public static void init() throws Exception {
        Intent.setObj(a, a.getClass().getName(), a.intValue());
        Intent.setObj(b, b.getClass().getName(), b.intValue());
        Intent.setObj(c, c.getClass().getName(), c.intValue());
        Intent.setObj(d, d.getClass().getName(), d.intValue());
    }

    @Test
    public void setObj() throws Exception {
        Intent.setObj(a, a.getClass().getName(), a.intValue());
        Intent.setObj(b, b.getClass().getName(), b.intValue());
        Intent.setObj(c, c.getClass().getName(), c.intValue());
        Intent.setObj(d, d.getClass().getName(), d.intValue());
    }

    @Test
    public void getObj() throws Exception {
        System.out.println(Intent.getObj(a, a.getClass().getName()));
        System.out.println(Intent.getObj(b, b.getClass().getName()));
        System.out.println(Intent.getObj(c, c.getClass().getName()));
        System.out.println(Intent.getObj(d, d.getClass().getName()));
    }

}