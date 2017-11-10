package com.ToolBox.date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * 日期计算核心
 * 日期区间计算
 *
 * @author 杨弘
 */
public class DateCalculate {
    private DateTime startDate;
    private DateTime endDate;

    /**
     * 日期计算初始化
     *
     * @param s 开始日期
     * @param e 结束日期
     */
    public DateCalculate(DateTime s, DateTime e) {
        this.startDate = s;
        this.endDate = e;
    }

    /**
     * @return 获得日期区间
     */
    public Period getIntervalDate() {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("时间未初始化");
        }
        Interval dt;
        if (startDate.isBefore(endDate)) {
            dt = new Interval(startDate, endDate);
        } else {
            dt = new Interval(endDate, startDate);
        }
        return dt.toPeriod();
    }
}
