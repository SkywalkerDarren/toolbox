package com.ToolBox.history;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HistoryRecordTest {
    @After
    public void tearDown() throws Exception {
        HistoryRecord.clear();
    }

    @Before
    public void setUp() throws Exception {
        HistoryRecord.addResult("123");
        HistoryRecord.addResult("12.3");
        HistoryRecord.addResult("123123123123123123123123123123");
    }


    @Test
    public void getRecord() throws Exception {
        for (String r : HistoryRecord.getRecord()) {
            System.out.println(r);
        }
    }

    @Test
    public void addResult() throws Exception {
        HistoryRecord.addResult("123123123");
        System.out.println(HistoryRecord.getSize());
    }

    @Test
    public void getSize() throws Exception {
        System.out.println(HistoryRecord.getSize());
    }

    @Test
    public void clear() throws Exception {
        HistoryRecord.clear();
        System.out.println(HistoryRecord.getSize());
    }

}