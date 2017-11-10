package com.ToolBox.date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * ���ڼ������
 * �����������
 *
 * @author ���
 */
public class DateCalculate {
    private DateTime startDate;
    private DateTime endDate;

    /**
     * ���ڼ����ʼ��
     *
     * @param s ��ʼ����
     * @param e ��������
     */
    public DateCalculate(DateTime s, DateTime e) {
        this.startDate = s;
        this.endDate = e;
    }

    /**
     * @return �����������
     */
    public Period getIntervalDate() {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("ʱ��δ��ʼ��");
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
