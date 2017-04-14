package com.lefanfs.apicenter.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 2015/6/3.
 */
public class DateUtils {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        String strDate = "2015-06-08";
        Date str1 = DateUtils.getFirstDayOfWeek(sdf.parse(strDate));
        System.out.println(sdf.format(str1));
        Date str2 = DateUtils.getLastDayOfWeek(sdf.parse(strDate));
        System.out.println(sdf.format(str2));
    }
        /**
         * @param
         * @throws java.text.ParseException
         * @描述 —— 获取某年某月中周一的总数【返回-1表示传入参数有误！！！】
         */
    private static int getMondayNumByYearMonth(int year, int month)
            throws ParseException {
        if (month >= 13 || month <= 0) {
            System.out.println("月份不存在！");
        } else {
            Date date = getLastDayOfYearMonth(year, month);
            // 当前月份的总天数
            int jiequ_tians = Integer.parseInt(new SimpleDateFormat("dd")
                    .format(date));
            int tongji = 0;
            date = getFirstDayOfYearMonth(year, month);
            for (int i = 0; i < jiequ_tians; i++) {
                Date temp = getDateFromSourceDate(date, i);
                if (getWeekNumByDate(temp) == 1) {
                    do {
                        tongji++;
                        i += 7;
                    } while (i < jiequ_tians);
                }
            }
            return tongji;
        }
        return -1;
    }

    /**
     * @param year
     * @param month
     * @param week
     * @return
     * @描述 —— 获取指定年月周的周一日期【返回null表示出入参数有误！！！】
     */
    public static Date getFirstDateByYearMonthWeek(int year, int month, int week) {
        if (month >= 13 || month <= 0) {
            System.out.println("月份不存在！");
        } else {
            Date date = getLastDayOfYearMonth(year, month);
            // 当前月份的总天数
            int jiequ_tians = Integer.parseInt(new SimpleDateFormat("dd")
                    .format(date));
            int tongji = 0;
            date = getFirstDayOfYearMonth(year, month);
            for (int i = 0; i < jiequ_tians; i++) {
                // 从当月1号开始到当月最后一天遍历数据
                Date temp = getDateFromSourceDate(date, i);
                if (getWeekNumByDate(temp) == 1) {
                    // 一旦发现有周一直接加上7
                    do {
                        tongji++;
                        if (tongji == week) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month - 1);
                            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
                            return calendar.getTime();
                        }
                        i += 7;
                    } while (i < jiequ_tians);
                }
            }
        }
        return null;
    }

    /**
     * @param date
     * @return
     * @描述 —— 获取日期所在周的第一天
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
      /*  if(dayOfWeek==1){
            dayOfWeek=7;
        }else{
            dayOfWeek=dayOfWeek-1;
        }*/
        System.out.println("==================:"+dayOfWeek);
        Calendar calFirstDayInThisWeek = (Calendar) cal.clone();
        calFirstDayInThisWeek.add(Calendar.DATE, cal
                .getActualMinimum(Calendar.DAY_OF_WEEK)
                - dayOfWeek);
        Date dateTemp = calFirstDayInThisWeek.getTime();

        return getDateFromSourceDate(dateTemp, 1);
    }

    /**
     * @param date
     * @return
     * @描述 —— 获取日期所在周的最后一天
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        int dayOfWeek= cal.get(Calendar.DAY_OF_WEEK);
       /* if(dayOfWeek==1){
            dayOfWeek=7;
        }else{
            dayOfWeek=dayOfWeek-1;
        }*/
        System.out.println("*******************:"+dayOfWeek);
        Calendar calLastDayInThisWeek = (Calendar) cal.clone();
        calLastDayInThisWeek.add(Calendar.DATE, cal
                .getActualMaximum(Calendar.DAY_OF_WEEK)
                - dayOfWeek);

        Date dateTemp = calLastDayInThisWeek.getTime();
        return getDateFromSourceDate(dateTemp, 1);
    }


    public static Date getFirstDayOfYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        // 当前月的第一天
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }


    public static Date getLastDayOfYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return cal.getTime();
    }


    public static int getWeekNumByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return weekDay == 1 ? 7 : weekDay - 1;
    }


    public static Date getDateFromSourceDate(Date currentDate, int num) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(GregorianCalendar.DATE, num);
        return cal.getTime();
    }
}
