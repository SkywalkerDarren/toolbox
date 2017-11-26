package com.ToolBox.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
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
    private Period period;

    /**
     * 日期计算初始化
     *
     * @param startYear 起始年份
     * @param startMonth 起始月份
     * @param startDay 起始天数
     * @param endYear 结束年份
     * @param endMonth 结束月份
     * @param endDay 结束天数
     */
    public DateCalculate(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        startDate = new DateTime(startYear, startMonth, startDay, 0, 0);
        endDate = new DateTime(endYear, endMonth, endDay, 0, 0);
        period = getIntervalDate();
    }

    /**
     * 日期计算初始化
     *
     * @param startYear  起始年份
     * @param startMonth 起始月份
     * @param startDay   起始天数
     * @param year       年
     * @param month      月
     * @param day        日
     * @param isAdd      是否增加日期
     */
    public DateCalculate(int startYear, int startMonth, int startDay, int year, int month, int day, Boolean isAdd) {
        startDate = new DateTime(startYear, startMonth, startDay, 0, 0);
        period = new Period(year, month, 0, day, 0, 0, 0, 0);
        if (isAdd) {
            endDate = startDate.plus(period);
        } else {
            endDate = startDate.minus(period);
        }
    }

    /**
     * @return 获得日期区间
     */
    private Period getIntervalDate() {
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

    /**
     * 获取日期间距
     *
     * @return 这个月的第几天
     */
    public String getIntervalDay() {
        return period.getDays() + period.getWeeks() * 7 + "";
    }

    /**
     * 获取日期间距
     *
     * @return 这个年的第几月
     */
    public String getIntervalMonth() {
        return period.getMonths() + "";
    }

    /**
     * 获取日期间距
     *
     * @return 间距几年
     */
    public String getIntervalYear() {
        return period.getYears() + "";
    }

    /**
     * 获取日期间距
     *
     * @return 日期间距总天数
     */
    public String getTotalDay() {
        return Math.abs(Days.daysBetween(startDate, endDate).getDays()) + "";
    }

    /**
     * 获取结果日期
     *
     * @return 结果日期的日数
     */
    public String getEndDay() {
        return endDate.getDayOfMonth() + "";
    }

    /**
     * 获取结果日期
     *
     * @return 结果日期的月份
     */
    public String getEndMonth() {
        return endDate.getMonthOfYear() + "";
    }

    /**
     * 获取结果日期
     *
     * @return 结果日期的年份
     */
    public String getEndYear() {
        return endDate.getYear() + "";
    }

    /**
     * 判断一个年份是否是闰年
     *
     * @param year 需判断的年份
     * @return true 如果是闰年
     */
    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    /**
     * 根据是否闰年，年月设定日期
     * @see DateCalculate#isLeapYear(int)
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    public static int setDay(int year, int month) {
        int day;
        switch (month) {
            case 1:
                day = 31;
                break;
            case 2:
                if (isLeapYear(year)) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            case 3:
                day = 31;
                break;
            case 4:
                day = 30;
                break;
            case 5:
                day = 31;
                break;
            case 6:
                day = 30;
                break;
            case 7:
                day = 31;
                break;
            case 8:
                day = 31;
                break;
            case 9:
                day = 30;
                break;
            case 10:
                day = 31;
                break;
            case 11:
                day = 30;
                break;
            case 12:
                day = 31;
                break;
            default:
                throw new IllegalArgumentException("日期非法");
        }
        return day;
    }
}