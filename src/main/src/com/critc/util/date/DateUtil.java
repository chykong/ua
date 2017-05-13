package com.critc.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String formatStr) {
        String strTime = "";
        try {
            DateFormat df = new SimpleDateFormat(formatStr);
            strTime = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 判断日期是null或空
     *
     * @param date
     * @return
     */
    public static boolean isDateNotNullOrEmpty(Date date) {
        String str = DateUtil.dateToString(date, "yyyy-MM-dd HH:mm:ss");
        if (str != null && !str.equals(""))
            return true;
        else
            return false;
    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr   需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date stringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getSystemTime() {
        String strTime = "";
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strTime = df.format(new Date());
        return strTime;
    }

    /**
     * 取得系统日期
     *
     * @return
     */
    public static String getSystemDate() {
        String strDate = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        strDate = df.format(new Date());
        return strDate;
    }

    /**
     * 系统时间加减
     *
     * @param date
     * @param dayNum
     * @return
     */
    public static String getOpeDate(String date, int dayNum) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dt);
        gc.add(5, dayNum);
        // gc.set(gc.YEAR,gc.get(gc.MONTH),gc.get(gc.DATE));
        return String.valueOf(df.format(gc.getTime()));
    }

    /**
     * 系统月份加减
     *
     * @param date
     * @param monthNum
     * @return
     */
    public static String getOpeMonth(String date, int monthNum) {

        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dt);
        gc.add(2, monthNum);
        // gc.set(gc.YEAR,gc.get(gc.MONTH),gc.get(gc.DATE));
        return String.valueOf(df.format(gc.getTime()));
    }

    /**
     * 取得两个日期的时间差
     */
    public static long getDateDifference(String date1, String date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos1 = new ParsePosition(0);
        java.util.Date dt1 = formatter.parse(date1, pos);
        java.util.Date dt2 = formatter.parse(date2, pos1);
        long l = (dt2.getTime() - dt1.getTime()) / (3600 * 24 * 1000);
        return l;
    }

    /**
     * 取得两个日期的时间差
     */
    public static long getHourDifference(Date date1, Date date2) {
        long l = (date1.getTime() - date2.getTime()) / (3600 * 1000);
        return l;
    }

    /**
     * 取得月份差
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getMonthDifference(String date1, String date2) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return result == 0 ? 1 : Math.abs(result);

    }

    /* 取得当月最后一天 */
    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
        String df = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return df;// 获得月末是几号
    }

    /* 取得当月第一天 */
    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        String df = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return df;// 获得月初是几号
    }

    /* 取得上个月的第一天 */
    public static String getLastMonthDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, -1);// 月份减一，得到上个月的一号
        String df = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return df;// 获得月初是几号
    }

    /* 取得下个月的最后一天 */
    public static String getNextMonthEndDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 2);// 月份加一，得到下下个月的一号
        cal.add(Calendar.DATE, -1);// 下下一个月减一为下个月最后一天
        String df = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return df;// 获得月末是几号
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getShortSystemTime() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        strTime = df.format(new java.util.Date());
        return strTime;
    }

    /**
     * 取得系统时间2
     *
     * @return
     */
    public static String getShortSystemTime2() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        strTime = df.format(new java.util.Date());
        return strTime;
    }

    /**
     * 取得系统短日期，yymmdd
     *
     * @return
     */
    public static String getShortSystemDate() {
        String strTime = "";
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        strTime = df.format(new java.util.Date());
        return strTime;
    }

    /**
     * 取得上个月的今天，当前日期-30
     *
     * @return
     */
    public static String getLastMoneyDay() {
        return DateUtil.getOpeDate(DateUtil.getSystemDate(), -30);
    }

    /* 取得当月最后一天 */
    public static String getLastDayOfMonth(String date) {
        Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));// 年
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));// 月，因为Calendar里的月是从0开始，所以要减1
        cal.set(Calendar.DATE, 1);// 日，设为一号
        cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
        cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天
        return df.format(cal.getTime());// 获得月末是几号
    }

    /**
     * 获取某个时间段的所有天数集合(包含起始日期与终止日期)
     *
     * @param starDate
     * @param endDate
     * @return
     */
    public static List<String> getDayList(String starDate, String endDate) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        List<String> dayList = new ArrayList<String>();
        if (starDate.equals(endDate)) {
            dayList.add(starDate);
        } else if (starDate.compareTo(endDate) < 0) {
            while (starDate.compareTo(endDate) <= 0) {
                dayList.add(starDate);
                try {
                    long l = stringToDate(starDate, "yyyy-MM-dd").getTime();
                    starDate = format.format(l + 3600 * 24 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            dayList.add(endDate);
        }
        return dayList;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getLastDayOfMonth("2015-10-11"));
    }

}
