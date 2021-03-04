package com.baiyang.oms.modular.business.util;

//import javafx.scene.input.DataFormat;


import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 该类为时间处理工具类，主要完成时间的秒数转换、字符串转换
 */
@Slf4j
public class TimeUtils implements Serializable {
	private static String dateFormat="yyyy-MM-dd";
	
	/**
	 * 获得当天0点0分的标准格林威治时间（秒）
	 * 
	 * @return
	 */
	public static int getCurrentDaySecond() {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.set(Calendar.HOUR, 0);
		gCalendar.set(Calendar.MINUTE, 0);
		gCalendar.set(Calendar.SECOND, 0);
		gCalendar.set(Calendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}

	/**
	 * 从一个指定的秒数获得所在天的起始时间点(秒)
	 * 
	 * @param second
	 * @return
	 */
	public static int getDayStartSecondFromInt(int second) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis((long) second * 1000);
		gCalendar.set(Calendar.HOUR, 0);
		gCalendar.set(Calendar.MINUTE, 0);
		gCalendar.set(Calendar.SECOND, 0);
		gCalendar.set(Calendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}

	/**
	 * 从一个指定的秒数获得所在天的下一天起始时间点(秒)
	 * 
	 * @param second
	 * @return
	 */
	public static int getNextDayStartSecondFromInt(int second) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis((long) second * 1000);
		gCalendar.add(Calendar.DATE, 1);
		gCalendar.set(Calendar.HOUR, 0);
		gCalendar.set(Calendar.MINUTE, 0);
		gCalendar.set(Calendar.SECOND, 0);
		gCalendar.set(Calendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}

	/**
	 * 从一个指定的秒数获得所在天的前一天起始时间点(秒)
	 * 
	 * @param second
	 * @return
	 */
	public static int getLastDayStartSecondFromInt(int second) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis((long) second * 1000);
		gCalendar.add(Calendar.DATE, -1);
		gCalendar.set(Calendar.HOUR, 0);
		gCalendar.set(Calendar.MINUTE, 0);
		gCalendar.set(Calendar.SECOND, 0);
		gCalendar.set(Calendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}

	/**
	 * 从一个指定的秒数获得所在天的前一天起始时间点(毫秒)
	 * 
	 * @param milli
	 * @return
	 */
	public static long getLastDayStartMilliFromInt(long milli) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(milli);
		gCalendar.add(Calendar.DATE, -1);
		gCalendar.set(Calendar.HOUR, 0);
		gCalendar.set(Calendar.MINUTE, 0);
		gCalendar.set(Calendar.SECOND, 0);
		gCalendar.set(Calendar.MILLISECOND, 0);
		return gCalendar.getTimeInMillis();
	}

	// 获得当前日期的字符串表示
	public static String getCurrentDateString() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		return sFormat.format(gCalendar.getTime());
	}

	// 获得当前日期的完整字符串表示
	public static String getCurrentDateStringAll() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gCalendar = new GregorianCalendar();
		return sFormat.format(gCalendar.getTime());
	}

	// 获得当前时间的字符串表示
	public static String getCurrentTime() {
		SimpleDateFormat sFormat = new SimpleDateFormat("kk:mm:ss");
		GregorianCalendar gCalendar = new GregorianCalendar();
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定时间的字符串表示
	public static String getSpecificTime(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
		return sFormat.format(date);
	}


	// 获得当前日期前一天的字符串表示
	public static String getYesterdayTimeString() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.add(Calendar.DATE, -1);
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定日期字符串的毫秒数
	public static long getMilliFromString(String dateStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gCalendar = new GregorianCalendar();
		try {
			Date date = sFormat.parse(dateStr);
			gCalendar.setTime(date);
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		return gCalendar.getTimeInMillis();
	}

	// 获得指定数值的日期字符串表示……毫秒
	public static String getDateStringFromMilliSeconds(long minSeconds) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(minSeconds);
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定数值的日期字符串全表示……毫秒
	public static String getDateStringAllFromMilliSeconds(long minSeconds) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(minSeconds);
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定数值的日期字符串表示……秒
	public static String getDateStringFromSeconds(long seconds) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(seconds * 1000);
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定数值的日期字符串全表示……秒
	public static String getDateStringAllFromSeconds(long seconds) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(seconds * 1000);
		return sFormat.format(gCalendar.getTime());
	}

	// 获得指定日期字符串的日期(当天起始点0时0分0秒)……秒
	public static int getStartSecondFromStr(String dateStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		int result = 0;
		try {
			Date date = sFormat.parse(dateStr);
			gCalendar.setTime(date);
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gCalendar.set(GregorianCalendar.MINUTE, 0);
			gCalendar.set(GregorianCalendar.SECOND, 0);
			result = (int) (gCalendar.getTimeInMillis() / 1000);
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		return result;
	}

	// 获得指定日期字符串的日期(当天起始点0时0分0秒)……毫秒
	public static long getStartMillisFromStr(String dateStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		long result = 0;
		try {
			Date date = sFormat.parse(dateStr);
			gCalendar.setTime(date);
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			gCalendar.set(GregorianCalendar.MINUTE, 0);
			gCalendar.set(GregorianCalendar.SECOND, 0);
			result = gCalendar.getTimeInMillis();
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		return result;
	}

	// 获得指定日期字符串的日期(当天中午12点0分0秒)……秒
	public static int getMiddleSecondFromStr(String dateStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		int result = 0;
		try {
			Date date = sFormat.parse(dateStr);
			gCalendar.setTime(date);
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
			gCalendar.set(GregorianCalendar.MINUTE, 0);
			gCalendar.set(GregorianCalendar.SECOND, 0);
			result = (int) (gCalendar.getTimeInMillis() / 1000);
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		return result;
	}

	// 获得指定日期字符串的日期(当天最后点23时59分59秒)……秒
	public static int getEndSecondFromStr(String dateStr) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gCalendar = new GregorianCalendar();
		int result = 0;
		try {
			Date date = sFormat.parse(dateStr);
			gCalendar.setTime(date);
			gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 23);
			gCalendar.set(GregorianCalendar.MINUTE, 59);
			gCalendar.set(GregorianCalendar.SECOND, 59);
			result = (int) (gCalendar.getTimeInMillis() / 1000);
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		return result;
	}

	// 获得指定日期（秒）所在月份第一天中午12点的秒数
	public static int getStartDayOfMonthFromSecond(long seconds) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(seconds * 1000);
		gCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
		gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
		gCalendar.set(GregorianCalendar.MINUTE, 0);
		gCalendar.set(GregorianCalendar.SECOND, 0);
		gCalendar.set(GregorianCalendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}

	// 获得指定日期（秒）所在年份第一天中午12点的秒数
	public static int getStartDayOfYearFromSecond(long seconds) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTimeInMillis(seconds * 1000);
		gCalendar.set(GregorianCalendar.DAY_OF_YEAR, 1);
		gCalendar.set(GregorianCalendar.HOUR_OF_DAY, 12);
		gCalendar.set(GregorianCalendar.MINUTE, 0);
		gCalendar.set(GregorianCalendar.SECOND, 0);
		gCalendar.set(GregorianCalendar.MILLISECOND, 0);
		return (int) (gCalendar.getTimeInMillis() / 1000);
	}
	/**
	 * 获取月末日期
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		return formatter.format(cal.getTime());
	}

	public static String getbeforeLastDayOfMonth(int year, int month) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);// 年
		cal.set(Calendar.MONTH, month - 1);// 月，因为Calendar里的月是从0开始，所以要减1
		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, 0);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		return formatter.format(cal.getTime());
	}

	/**
	 * 获取当月月末日期
	 * @param offset,月份偏移量，0为当月，-1为上月，1为下月
	 * @return
	 */
	public static String getLastDayOfMonth(int offset) {
		Calendar cal = new GregorianCalendar();

		cal.set(Calendar.DATE, 1);// 日，设为一号
		cal.add(Calendar.MONTH, offset + 1);// 月份加一，得到下个月的一号
		cal.add(Calendar.DATE, -1);// 下一个月减一为本月最后一天

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		return formatter.format(cal.getTime());
	}

	/**
	 * 获取之前或之后日期日期
	 * @param offset,月份偏移量
	 * @return
	 */
	public static String getLastDayOfDay(int offset) {
		Calendar cal = new GregorianCalendar();

		cal.add(Calendar.DAY_OF_WEEK, 0 - offset);// 

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		return formatter.format(cal.getTime());
	}
	/**
	 * 两个日期相减获取天数
	 * @return
	 */
	public static long datemindate(Date fisrt,Date second){		
		 return (fisrt.getTime()-second.getTime())/(24*60*60*1000);
	}
    /**
     * 两个日期相减或得月份数
     * @return
     */
    public static int datemindateMonth(Date date1, Date date2) {  Calendar cal1 = new GregorianCalendar();  cal1.setTime(date1);  Calendar cal2 = new GregorianCalendar();  cal2.setTime(date2);  int c =   (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)    - cal2.get(Calendar.MONTH);  return c; }
    /**
	 * 当前日期减当月第一天
	 * @return
	 */
	public static long dateminfirst(){		
		// 日历类
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);// 日，设为一号
		return datemindate(new Date(),cal.getTime());
		
	}
	/**
	 * 获取传入日期，前N天或后N天日期。
	 * @return
	 */
	public static Date getDateByNum(Date indate,int daynum){		
		// 日历类
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(indate);
		calendar.add(Calendar.DAY_OF_MONTH, daynum);
		return calendar.getTime();
	}

    /**
     * 根据传入日期与格式，返回日期类型
     * @return
     */
    public static Date getDateByForm(String indate,String dfromat){
        SimpleDateFormat sdf = new SimpleDateFormat(dfromat);
        Date date = null;
        try {
            date = sdf.parse(indate);
        } catch (ParseException e) {
            e.fillInStackTrace();
        }
        return date;

    }

	/**
	 * 根据传入日期与格式，返回字符串类型
	 * @return
	 */
	public static String getDateStringByForm(Date indate,String dfromat){
		if(indate==null){
			return "";
		}
		// 日历类
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(indate);
		SimpleDateFormat formatter = new SimpleDateFormat(dfromat);
		return formatter.format(calendar.getTime());
	}
	/**
	 * 根据传入格式日期与自定义格式，返回字符串类型
	 * @return
	 */
	public static String getDateStringByForm(String dfromatin,String indate,String dfromat){	
		SimpleDateFormat sFormat = new SimpleDateFormat(dfromatin);
		GregorianCalendar gCalendar = new GregorianCalendar();
		try {
			Date date = sFormat.parse(indate);
			gCalendar.setTime(date);
		} catch (ParseException e) {
            e.fillInStackTrace();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(dfromat);
		return formatter.format(gCalendar.getTime());
	}

    public static String getDateFormat(Date date){
        SimpleDateFormat dfromat = new SimpleDateFormat(dateFormat);
        return  dfromat.format(date);
    }
    public static String getDateFormat(Date date, String dateFormat){
        SimpleDateFormat dfromat = new SimpleDateFormat(dateFormat);
        return  dfromat.format(date);
    }

	/*public static void main(String[] args) {
		int timeInt = 1199142900;
		System.out.println(SysDateFormat.getDateStringAllFromSeconds(timeInt));
		int time = getDayStartSecondFromInt(timeInt);
		String str = SysDateFormat.getDateStringAllFromSeconds(time);
		System.out.println(str);
		int time2 = getNextDayStartSecondFromInt(timeInt);
		String str2 = SysDateFormat.getDateStringAllFromSeconds(time2);
		System.out.println(str2);
	}*/

	/**
	 * 获取传入日期与当前时间的相隔分钟。
	 * @return
	 */
	public static long getDateDiff(Date date){
		SimpleDateFormat dfromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println("服务器时间："+now.getTime());
		long minute = 0;
		try {
			long diff = date.getTime() - now.getTime();
			minute  = diff / (1000 * 60);
		}catch (Exception e){
			e.fillInStackTrace();
		}
		return minute;
	}
	/**
	 * 获取当前时间与传入时间的相隔分钟。
	 * @return
	 */
	public static long getDateDiffMethod(Date date){
		SimpleDateFormat dfromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		System.out.println("服务器时间："+now.getTime());
		long minute = 0;
		try {
			long diff = now.getTime() - date.getTime();
			minute  = diff / (1000 * 60);
		}catch (Exception e){
			e.fillInStackTrace();
		}
		return minute;
	}

	// 获得当前日期的字符串表示
	public static String getCurrentDateString(String dateFormat) {
		SimpleDateFormat sFormat = new SimpleDateFormat(dateFormat);
		GregorianCalendar gCalendar = new GregorianCalendar();
		return sFormat.format(gCalendar.getTime());
	}
	public static void main(String[] args){
		System.out.println(getCurrentDateString("HH:mm"));;
	}
}
