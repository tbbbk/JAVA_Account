package util;

import java.lang.invoke.CallSite;
import java.util.Calendar;
import java.util.Date;

/**
 * date tool class
 */
public class DateUtil {
    private static final long millisecondsOfOneDay = 1000 * 60 * 60 * 24;
    private static Calendar c = Calendar.getInstance();

    /**
     * @param   d   Date
     * @return
     */
    public static java.sql.Date util2sql(java.util.Date d) {
        return new java.sql.Date(d.getTime());
    }

    public static Date today() {
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     *
     * @return
     */
    public static Date monthBegin() {
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date monthEnd() {
        return monthEnd(monthBegin());
    }

    /**
     *
     * @param start
     * @return
     */
    public static Date monthEnd(Date start) {
        c.setTime(start);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static int thisMonthTotalDay() {
        c.setTime(new Date());
        monthEnd();
        return c.get(Calendar.DATE);
    }

    public static int thisMonthLeftDay() {
        int TotalDay = thisMonthTotalDay();
        today();
        int today = c.get(Calendar.DATE);
        return TotalDay - today + 1;
    }

    public static int thisYear() {
        today();
        return c.get(Calendar.YEAR);
    }

    public static int thisMonth() {
        today();
        return c.get(Calendar.MONTH) + 1;
    }

    public static void main(String[] args) {
        System.out.println(c);
    }

}

