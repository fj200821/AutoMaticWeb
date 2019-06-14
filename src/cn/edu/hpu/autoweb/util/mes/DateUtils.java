package cn.edu.hpu.autoweb.util.mes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.hpu.autoweb.exception.DateTimeOrNumericalException;


public class DateUtils {
    public static final String PATTERN_DATE_DEFAULT = "yyyy-MM-dd";
    public static final String PATTERN_DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_DATE_YYYY_MM_DD_Week = "yyyy-MM-dd(E)";
    public static final String PATTERN_DATE_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DATETIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATETIME_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String PATTERN_DATETIME_YYYY_MM_DD_HHMM = "yyyy-MM-dd HHmm";
    public static final String PATTERN_DATETIME_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HHmmss";
    public static final String PATTERN_DATETIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PATTERN_DATETIME_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String PATTERN_TIME_DEFAULT = "HH:mm:ss";
    public static final String PATTERN_TIME_HH_MM = "HH:mm";
    public static final String PATTERN_TIME_HH_MM_SS = "HH:mm:ss";
    public static final String PATTERN_TIME_HHMM = "HHmm";
    public static final String PATTERN_TIME_HHMMSS = "HHmmss";

    public static Date getCurrentDate() {
        return new Date();
    }

    public static String formatTime(Date date) {
        return formatDate(date, PATTERN_TIME_DEFAULT);
    }

    public static String formatDate(Date date) {
        return formatDate(date, PATTERN_DATE_DEFAULT);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, PATTERN_DATETIME_DEFAULT);
    }

    public static String formatDate(Date date, String formatPattern) {
        if (date == null)
            return "";
        else
            return getDateFormat(formatPattern).format(date);
    }

    public static Date fromString(String dateStr) throws Exception {
        return new SimpleDateFormat(PATTERN_DATE_DEFAULT).parse(dateStr);
    }

    public static Date fromDateTimeString(String dateStr) throws Exception {
        return new SimpleDateFormat(PATTERN_DATETIME_DEFAULT).parse(dateStr);
    }

    public static SimpleDateFormat getDateFormat(String formatPattern) {
        return new SimpleDateFormat(formatPattern);
    }

    public static int compareDateToField(Date date1, Date date2,
                                         int calendarField) {
        if (date1 == null || date2 == null)
            throw new IllegalArgumentException("Neither date could be null");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        int ret = 0;
        switch (calendarField) {
            case 1:
                ret = c1.get(1) - c2.get(1);
                break;

            case 2:
                ret = compareDateToField(date1, date2, 1);
                ret = ret == 0 ? c1.get(2) - c2.get(2) : ret;
                break;

            case 5:
                ret = compareDateToField(date1, date2, 2);
                ret = ret == 0 ? c1.get(5) - c2.get(5) : ret;
                break;
            default:
                ret = (int) (c1.getTimeInMillis() - c2.getTimeInMillis());
                break;
        }
        return ret;
    }

    public static String sunday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            int week = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, -week + 1);
        } catch (Exception e) {
            logger.error("DateUtils::sunday catch exception:", e);
        }
        return sdf.format(c.getTime());
    }

    public static String saturday(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(date));
            int week = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, -week + 7);
        } catch (Exception e) {
            logger.error("DateUtils::saturday catch exception:", e);
        }
        return sdf.format(c.getTime());
    }

    // 当前工作日取得
    public static String getCurrentWorkSingleDate() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 8) {
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        return formatDate(calendar.getTime(), DateUtils.PATTERN_DATE_DEFAULT);
    }

    public static Date StringToDate(String format, String date) {
        Date dateFormatted = null;
        if (format == null || date == null)
            throw new DateTimeOrNumericalException("日期格式化失败：参数为空");

        DateFormat df = new SimpleDateFormat(format);
        try {
            dateFormatted = df.parse(date);
        } catch (ParseException e) {
            throw new DateTimeOrNumericalException("日期格式化失败：模式字符串非法");
        }

        return dateFormatted;
    }

    public static int daysOfTwo(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    public static long daysOfTwo_2(Date fDate, Date oDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        long days = (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);
        return days;
    }

    public static long milliOfTwo_2(Date fDate, Date oDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long milli = (oDate.getTime() - fDate.getTime());
        return milli;
    }

    static private Logger logger = LoggerFactory.getLogger(DateUtils.class);
}
