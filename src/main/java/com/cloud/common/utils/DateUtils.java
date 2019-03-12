package com.cloud.common.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 *
 * @author Pan Jianneng
 */
public final class DateUtils {
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_BREIF = "yyyyMMdd";
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_YEAR_WEEK = "yyyyww";
    public static final String PATTERN_DATE_TIME_BREIF = "yyyyMMddHHmmss";
    public static final String PATTERN_YEAR = "yyyy";

    /**
     * 默认的 {@code JSON} 日期/时间字段的格式化模式。
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    private DateUtils() {
    }

    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, PATTERN_DATE);
    }

    public static String formatDateTime(Date dateTime) {
        return DateFormatUtils.format(dateTime, PATTERN_DATE_TIME);
    }

    public static String formatDateTimeBreif(Date dateTime) {
        return DateFormatUtils.format(dateTime, PATTERN_DATE_TIME_BREIF);
    }

    public static Date parseDate(String date) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, PATTERN_DATE);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format: " + date + " for " + PATTERN_DATE);
        }
    }

    public static Date parseDate2Breif(String date) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, PATTERN_DATE_BREIF);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format: " + date + " for " + PATTERN_DATE);
        }
    }

    public static Date parseDateTime(String dateTime) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateTime, PATTERN_DATE_TIME);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal datetime format: " + dateTime + " for " + PATTERN_DATE_TIME);
        }
    }

    public static Date parseYearMonth(String yearMonth) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(yearMonth, PATTERN_MONTH);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal datetime format: " + yearMonth + " for " + PATTERN_MONTH);
        }
    }

    public static Date parseYearWeek(String yearWeek) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(yearWeek, PATTERN_YEAR_WEEK);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal datetime format: " + yearWeek + " for " + PATTERN_YEAR_WEEK);
        }
    }

    /**
     * Adds a number of days to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date   the date, not null
     * @param amount the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
    }

    /**
     * <p>Truncate this date, leaving the field specified as the most
     * significant field.</p>
     *
     * <p>For example, if you had the datetime of 28 Mar 2002
     * 13:45:01.231, if you passed with HOUR, it would return 28 Mar
     * 2002 13:00:00.000.  If this was passed with MONTH, it would
     * return 1 Mar 2002 0:00:00.000.</p>
     *
     * @param date  the date to work with, not null
     * @param field the field from {@code Calendar} or <code>SEMI_MONTH</code>
     * @return the different truncated date, not null
     * @throws IllegalArgumentException if the date is <code>null</code>
     * @throws ArithmeticException      if the year is over 280 million
     */
    public static Date truncate(Date date, int field) {
        return org.apache.commons.lang3.time.DateUtils.truncate(date, field);
    }

    /**
     * 查询两个日期相差的自然周数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 相差的自然周数
     */
    public static int naturalWeeksBetween(Date start, Date end) {
        Preconditions.checkNotNull(start);
        Preconditions.checkNotNull(end);
        Preconditions.checkArgument(end.getTime() >= start.getTime(), "结束时间必须大于等于开始时间");
        return Days.daysBetween(new DateTime(start).withDayOfWeek(1), new DateTime(end)
                .plusWeeks(1).withDayOfWeek(1)).getDays() / 7;
    }

    /**
     * 求两个日期间的月差值
     *
     * @param min 小的日期值
     * @param max 大的日期值
     * @return
     */
    public static int getMonthDiff(DateTime min, DateTime max) {
        return (max.getYear() - min.getYear()) * 12 + max.getMonthOfYear() - min.getMonthOfYear();
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, PATTERN_DATE);
    }

    /**
     * localDateTime转Date
     *
     * @param localDateTime 时间
     * @return Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当前年份
     */
    public static int currentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }


    /**
     * 获取当前日期时分秒毫秒作为批次ID
     *
     * @return
     */
    public static String getBatchId() {
        return new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getBatchId());
    }


}
