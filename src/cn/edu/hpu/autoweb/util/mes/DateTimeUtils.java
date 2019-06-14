package cn.edu.hpu.autoweb.util.mes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.hpu.autoweb.exception.DateTimeOrNumericalException;


/**
 * 日期时间工具类
 * 
 * @author  范帆
 * @version 1.0 2011-09-07
 * @since   1.0
 */
public class DateTimeUtils {

	//预定义格式字符串
	public static final String FORMAT_ONLY_DATE_EN = "yyyy-MM-dd";
	public static final String FORMAT_ONLY_DATE_CHS = "yyyy年MM月dd日";
	public static final String FORMAT_FULL_DATETIME_EN = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_FULL_DATETIME_CHS = "yyyy年MM月dd日 HH时mm分ss秒";

	public static final String SERIAL_LONG = "yyyyMMddHHmmssSSS";
	
	//预定义控制参数
	public static final int FIRST_DAY_OF_WEEK = 1;                   //星期的第一天
	public static final int FIRST_DAY_OF_MONTH = 2;                  //月份的第一天  
	public static final int FIRST_DAY_OF_QUARTER = 3;                //季度的第一天
	public static final int FIRST_DAY_OF_YEAR = 4;                   //年份的第一天
	public static final int LAST_DAY_OF_WEEK = 5;                    //星期的最后一天
	public static final int LAST_DAY_OF_MONTH = 6;                   //月份的最后一天
	public static final int LAST_DAY_OF_QUARTER = 7;                 //季度的最后一天
	public static final int LAST_DAY_OF_YEAR = 8;                    //年份的最后一天
	public static final int DAY_OF_NEXT_WEEK = 9;                    //下周今日
	public static final int DAY_OF_NEXT_MONTH = 10;                  //下月今日
	public static final int DAY_OF_NEXT_YEAR = 11;                   //明年今日
	
	public static final int YEAR = 21;                                //年
	public static final int MONTH = 22;                               //月
	public static final int QUARTER = 23;                             //季度
	public static final int DAY_OF_YEAE = 24;                         //年中天数
	public static final int DAY_OF_WEEK_CHS = 25;                     //周中天数 周一为第一天
	public static final int DAY_OF_WEEK_EN = 26;                      //周中天数 西方习惯 周日为第一天
	
	//根据指定模式格式化日期时间
	public static String dateTimeFormatConvert(String format,Date date)
	{
		String dateFormatted = null;
		if(format == null || date == null)
			throw new DateTimeOrNumericalException("日期格式化失败：参数为空");
			
		DateFormat df = new SimpleDateFormat(format);
		try{
			dateFormatted = df.format(date);
		}catch(IllegalArgumentException e)
		{
			logger.error("DateTimeUtils::dateTimeFormatConvert catch exception:", e);
			throw new DateTimeOrNumericalException("日期格式化失败：模式字符串非法");
		}
		
		return dateFormatted;
	}
	
	//格式化日期时间为中文完整格式
	public static String dateTimeFormattedCHS(Date date)
	{
		return dateTimeFormatConvert(FORMAT_FULL_DATETIME_CHS,date);
	}
	
	//格式化日期为英文完整格式
	public static String dateTimeFormattedEN(Date date)
	{
		return dateTimeFormatConvert(FORMAT_FULL_DATETIME_EN,date);
	}

	//当天日期简单格式
	public static String getCurrentSingleDate()
	{
		return dateTimeFormatConvert(FORMAT_ONLY_DATE_EN,new Date());
	}

	// 当前工作日取得
	public static String getCurrentWorkSingleDate()
	{
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour < 8)
		{
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		return DateTimeUtils.dateTimeFormatConvert(DateTimeUtils.FORMAT_ONLY_DATE_EN, calendar.getTime());
	}
	// 当前班次的开始时间
	public static String getCurrentWorkBbDateTime()
	{
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour < 8)
		{
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		String dateTime = DateTimeUtils.dateTimeFormatConvert(DateTimeUtils.FORMAT_ONLY_DATE_EN, calendar.getTime());
		if(hour >= 8 && hour < 20){
			dateTime += " 08:00:00";
		}
		else{
			dateTime += " 20:00:00";
		}
		return dateTime;
	}
	
	//传入日期所处星期、月份、季度、年份的第一天/最后一天
	
	//获取当前日期所在的年份、月份、季度数等要素
	public static int getCurrentDateElement(int console)
	{
		int result = -1;
		Calendar c = Calendar.getInstance();
		switch(console)
		{
			case YEAR      			:  result = c.get(Calendar.YEAR); break;                     //当前日期所在年份
			case MONTH     			:  result = c.get(Calendar.MONTH) + 1; break;                //当前日期所在月份
			case QUARTER   			:  result = getCurrentDateQuarter(); break;                  //当前日期所在季度
			case DAY_OF_YEAE    	:  result = c.get(Calendar.DAY_OF_YEAR); break;              //当前日期所在年中为第N天
			case DAY_OF_WEEK_EN 	:  result = c.get(Calendar.DAY_OF_WEEK); break;              //当前日期所在周中为第N天 周日为第一天
			case DAY_OF_WEEK_CHS 	:  c = Calendar.getInstance(Locale.CHINA);			                          
									   c.setFirstDayOfWeek(Calendar.MONDAY);
			                           result = c.get(Calendar.DAY_OF_WEEK); break;              //当前日期所在周中为第N天 周一为第一天
		}
		
		if(result == -1)
			throw new DateTimeOrNumericalException("getCurrentDateElement()执行失败，控制参数非法");
		
		return result;
		
	}
	
	//获取当前日期所在的本月第一天，本月最后一天
	public static String getCurrentDate(int console)
	{
		String result = "";
		Calendar c = Calendar.getInstance();
		switch(console)
		{
		case FIRST_DAY_OF_MONTH		:	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));result = dateTimeFormatConvert(FORMAT_ONLY_DATE_EN, c.getTime());break;
		case LAST_DAY_OF_MONTH		:	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));result = dateTimeFormatConvert(FORMAT_ONLY_DATE_EN, c.getTime());break;
		}
		
		if(result.equals(""))
			throw new DateTimeOrNumericalException("getCurrentDateElement()执行失败，控制参数非法");
		
		return result;
	}
	
	//当前日期所在季度
	private static int getCurrentDateQuarter()
	{
		int quarter = -1;
		Calendar c = Calendar.getInstance();
		
		switch(c.get(Calendar.MONTH))
		{
			case 0	:
			case 1	:
			case 2 	:  quarter = 1; break;
			case 3  :
			case 4  :
			case 5  :  quarter = 2; break;
			case 6  :
			case 7  :
			case 8  :  quarter = 3; break;
			case 9  :
			case 10 :
			case 11 :  quarter = 4; break;
		}
		if(quarter == -1)
			throw new DateTimeOrNumericalException("getCurrentDateQuarter()执行失败，未能正确获取季度");
		
		return quarter;
	}
	
	//两个日期间相差的天数
	public int Date(String sDate,String eDate)
	{
		return 0;
	}
	
	//JDBC日期时间类型转换
	public java.sql.Timestamp JDBCDateTimeConvert(Date date)
	{
		return new java.sql.Timestamp(date.getTime());
	}
	
	//JDBC日期类型转换
	public java.sql.Date JDBCDateConvert(Date date)
	{
		return new java.sql.Date(date.getTime());
	}
	
	public static Date StringToDate(String format,String date)
	{
		Date dateFormatted = null;
		if(format == null || date == null)
			throw new DateTimeOrNumericalException("日期格式化失败：参数为空");
			
		DateFormat df = new SimpleDateFormat(format);
		try{
			dateFormatted = df.parse(date);
		}
		catch (ParseException e)
		{
			logger.error("DateTimeUtils::StringToDate catch exception:", e);
			throw new DateTimeOrNumericalException("日期格式化失败：模式字符串非法");
		}
		
		return dateFormatted;
	}

	// 获得某天最大时间 2017-10-15 23:59:59
	public static Date getEndOfDay(Date date) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	// 获得某天最小时间 2017-10-15 00:00:00
	public static Date getStartOfDay(Date date) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	static private Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);
}
