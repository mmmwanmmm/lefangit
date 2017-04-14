package com.lefanfs.base.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间工具类
 * 
 * @author daniel
 * 
 */
public class DateTimeUtil {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_PATTERN_SHORT = "yyyyMMdd";

	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_PATTERN_SHORT = "yyyyMMddHHmmss";
	public static final String DATETIME_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATETIME_PATTERN_LONG2 = "yyyyMMddHHmmssSSS";
	public static final String DATETIME_PATTERN_H = "yyyy-MM-dd HH";
	public static final String DATETIME_PATTERN_M = "yyyy-MM-dd HH:mm";
	public static final String TIMEZONE_SHANGHAI = "Asia/Shanghai";
	public static final String DATETIME_PATTERN_SOLR = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DATETIME_PATTERN_ZONE = "yyyy-MM-dd HH:mm:ss.SSS Z";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(DateTimeUtil.getDateByDayOfWeek(new Date(),
		// Calendar.WEDNESDAY));

		// System.out.println(getTimeZoneDate("Japan", DATETIME_PATTERN_LONG));
		// System.out.println(getTimeZoneDate(null, DATETIME_PATTERN_LONG));
		// // System.out.println(getFormatedDateString("Japan"));
		// // System.out.println(getFormatedDateString("Europe/Madrid"));
		// System.out.println(getTimeZoneDate("GMT+8:00",
		// DATETIME_PATTERN_LONG));
		// System.out.println("====" + getBeforDateFormate("2015-04-01 12:22"));
		// System.out.println("====" + getBeforDayFormate("2015-05-04 19:40:29.865"));
		System.out.println(parseDateTime("2016-08-08 14:24:44", "yyyy-MM-dd HH:mm:ss"));
		// System.out.println("===" +
		// getWeekCH(String.valueOf(getWeekOfDate(parseDateTime("2015-03-31
		// 10:58",
		// DATETIME_PATTERN_M)))));

	}

	/**
	 * 获取今天的日期字符串（默认格式）
	 * 
	 * @return
	 * @author daniel
	 */
	public static String getToday() {
		return formatDate(new Date());
	}

	/**
	 * 获取今天的日期字符串（自定义格式）
	 * 
	 * @param pattern
	 * @return
	 * @author daniel
	 */
	public static String getToday(String pattern) {
		return formatDateTime(new Date(), pattern);
	}

	/**
	 * 获取现在的日期字符串（默认格式）
	 * 
	 * @return
	 * @author daniel
	 */
	public static String getNow() {
		return formatDateTime(new Date());
	}

	/**
	 * 获取现在的日期字符串（自定义格式）
	 * 
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static String getNow(String pattern) {
		return formatDateTime(new Date(), pattern);
	}

	/**
	 * 将日期对象格式化成日期字符串（默认格式）
	 * 
	 * @param d
	 *            日期对象
	 * @return
	 * @author daniel
	 */
	public static String formatDate(Date d) {
		return formatDateTime(d, DATE_PATTERN);
	}

	/**
	 * 将日期对象格式化成日期时间字符串（默认格式）
	 * 
	 * @param d
	 *            日期对象
	 * @return
	 * @author daniel
	 */
	public static String formatDateTime(Date d) {
		return formatDateTime(d, DATETIME_PATTERN);
	}

	/**
	 * 针对时间查询条件solr查询
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTimeForSolr(Date d) {
		return formatDateTime(d, DATETIME_PATTERN_SOLR);
	}

	/**
	 * 针对时间查询条件solr查询
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDateTimeForSolr(String d) {
		return formatDateTime(parseDate(d), DATETIME_PATTERN_SOLR);
	}

	/**
	 * 将日期对象格式化成日期时间字符串（自定义格式）
	 * 
	 * @param d
	 *            日期对象
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static String formatDateTime(Date d, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

	/**
	 * 将日期字符串转换成日期对象（默认格式）
	 * 
	 * @param str
	 *            日期字符串
	 * @return
	 * @author daniel
	 */
	public static Date parseDate(String str) {
		return parseDateTime(str, DATE_PATTERN);
	}

	/**
	 * 将日期时间字符串转换成日期对象（默认格式）
	 * 
	 * @param str
	 *            日期时间字符串
	 * @return
	 * @author daniel
	 */
	public static Date parseDateTime(String str) {
		return parseDateTime(str, DATETIME_PATTERN);
	}

	/**
	 * 将日期时间字符串转换成日期对象（自定义格式）
	 * 
	 * @param str
	 *            日期时间字符串
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static Date parseDateTime(String str, String pattern) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 比较两个日期
	 * 
	 * @param src
	 *            第一个日期对象
	 * @param dest
	 *            第二个日期对象
	 * @return -1表示小于，0表示等于，1表示大于
	 * @author daniel
	 */
	public static int compareTo(Date src, Date dest) {
		Date d1 = parseDateTime(formatDateTime(src, DATETIME_PATTERN), DATETIME_PATTERN);
		Date d2 = parseDateTime(formatDateTime(dest, DATETIME_PATTERN), DATETIME_PATTERN);
		int i = d1.compareTo(d2);
		return i;
	}

	/**
	 * 在给定的时间上向后追加多少分钟
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateAfter(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		} else {
			calendar.setTime(new Date());
		}
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 
	 * 输入一个日期，得到这个日期所在周的dayOfWeek的日期
	 * 
	 * @param date
	 * @param dayOfWeek
	 * @return
	 * @author Lynch
	 */
	public static String getDateByDayOfWeek(Date date, int dayOfWeek) {
		SimpleDateFormat format = new SimpleDateFormat(DateTimeUtil.DATE_PATTERN);
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		// 关于DAY_OF_WEEK的说明
		// Field number for get and set indicating
		// the day of the week. This field takes values
		// SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
		// and SATURDAY
		// 如果是星期天，则加上7天
		if (dayOfWeek == Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			return format.format(new Date(cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)));
		} else {
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		}
		return format.format(cal.getTime());
	}

	/**
	 * 
	 * 获取时间在当前月是第几天
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getCurrentMonthOfDay(Date date) {
		// 获取当前月的第一天
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		Long day = DateTimeUtil.getDay(date, firstDate);
		return day.intValue();
	}

	/**
	 * 
	 * 获取当前月的第n天
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 * @author Lynch
	 */
	public static String getDayOfMonth(Date date, int n) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, n);
		return DateTimeUtil.formatDateTime(ca.getTime(), DATETIME_PATTERN);
	}

	/**
	 * 
	 * 获取当前月的第n天
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 * @author Lynch
	 */
	public static String getDayOfMonth(Date date, int n, String format) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, n);
		return DateTimeUtil.formatDateTime(ca.getTime(), format);
	}

	/**
	 * 
	 * 日期增加或减去某个时间单位数
	 * 
	 * @param millis
	 * @return
	 * @author Lynch
	 */
	public static Date getAddDate(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.getTime();
	}

	/**
	 * 
	 * 返回两个日期天数差
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @author Lynch
	 */
	public static long getDay(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long ms = DateTimeUtil.getMilliSecond(d1, d2);
		return ms / 1000 / 60 / 60 / 24;
	}

	/**
	 * 
	 * 返回两个日期天数差
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @author Lynch
	 */
	public static long getDiffDays(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		d1 = c1.getTime();

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		d2 = c2.getTime();

		long ms = DateTimeUtil.getMilliSecond(d1, d2);
		return ms / 1000 / 60 / 60 / 24;
	}

	/**
	 * 返回两个日期毫秒差
	 */
	public static long getMilliSecond(Date d1, Date d2) {
		long d1MS = d1.getTime();
		long d2MS = d2.getTime();
		return Math.abs(d1MS - d2MS);
	}

	/**
	 * 
	 * 获取时间所在的月份
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getMonthOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * 获取时间所在天数
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getDayOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 
	 * 获取当前时间所在的年
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getYearOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getWeekOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static String getWeekCH(String weekdayNumbers) {
		if (weekdayNumbers != null) {
			return weekdayNumbers.replaceAll("2", "星期一").replaceAll("3", "星期二").replaceAll("4", "星期三").replaceAll("5", "星期四").replaceAll("6", "星期五").replaceAll("7", "星期六").replaceAll("1", "星期日");
		}
		return "";
	}

	/**
	 * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8
	 * 
	 * @param timeZoneOffset
	 * @return
	 */
	public String formatedDateTimeZone(int timeZoneOffset, String pattern) {
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}

	public static String formatedDateTimeZone(String _timeZone, String pattern) {
		TimeZone timeZone = null;
		if (StringUtils.isEmpty(_timeZone)) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = TimeZone.getTimeZone(_timeZone);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}

	public static Date getTimeZoneDate(String _timeZone, String pattern) {
		TimeZone timeZone = null;
		if (StringUtils.isEmpty(_timeZone)) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = TimeZone.getTimeZone(_timeZone);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(timeZone);
		return parseDateTime(sdf.format(new Date()), pattern);
	}

	public static String getBeforDayFormate(Date inDate) {
		// long day = 1;
		if (!StringUtils.isEmpty(inDate)) {
			long day = getDiffDays(inDate, new Date());
			if (day == 0) {
				return "今天";
			} else if (day == 1) {
				return "昨天";
			} else {
				return day + " 天前";
			}
			// long now = new Date().getTime();
			// long ago = inDate.getTime();
			// long val = (now - ago) / (1000 * 3600 * 24);
			// if (val == 0) {
			// return "今天";
			// } else if (val >= day) {
			// return (val / day) + " 天前";
			// }
		}
		return "";
	}

	public static String getBeforDayFormate(String inDate) {
		if (!StringUtils.isEmpty(inDate)) {
			return getBeforDayFormate(parseDateTime(inDate, DATETIME_PATTERN_M));
		}
		return "";
	}

	public static String getBeforDateFormate(Date inDate) {
		long minute = 1;
		long hour = 60;
		long yesterday = 60 * 24;
		long towDay = 60 * 48;
		long treeDay = 60 * 24 * 3;
		long sevenDay = 60 * 24 * 7;
		if (!StringUtils.isEmpty(inDate)) {
			long now = new Date().getTime();
			long ago = inDate.getTime();
			long val = (now - ago) / (1000 * 60);
			if (val == 0) {
				return "刚刚";
			} else if (val >= minute && val < hour) {
				return val + " 分钟前";
			} else if (val >= hour && val < yesterday) {
				return (val / hour) + " 小时前";
			} else if (val >= yesterday && val < towDay) {
				return "昨天  " + formatDateTime(inDate, "HH:mm");
			} else if (val >= towDay && val < treeDay) {
				return "前天  " + formatDateTime(inDate, "HH:mm");
			} else if (val >= treeDay && val <= sevenDay) {
				return getWeekCH(String.valueOf(getWeekOfDate(inDate))) + " " + formatDateTime(inDate, "HH:mm");
			} else if (val > sevenDay) {
				return formatDateTime(inDate, "yyyy年MM月dd日  HH:mm");
			}
		}

		return "";
	}

	public static String getBeforDateFormate(String inDate) {
		if (!StringUtils.isEmpty(inDate)) {
			return getBeforDateFormate(parseDateTime(inDate, DATETIME_PATTERN_M));
		}
		return "";
	}

	public static String dateToString_yyyyMMddHHmmssSSS(Date date){
		if (date==null){
			return null;
		}
		return formatDateTime(date,DATETIME_PATTERN_LONG2);
	}
}
