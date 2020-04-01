/**  
 * @Title: DateUtils.java
 * @Package cn.com.libertymutual.claims.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author dy
 * @date 2018年12月21日
 * @version V1.0  
 */

package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: 使用Java8日期新特性简化日期操作
 * @author dy
 * @date 2018年12月21日
 */

public class DateUtils {
	private static String[] optionDateFormats = { "yyyy-MM-dd HH:mm:ss.S a",
	        "yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss",
	        "yyyy-MM-dd HH:mm:ssa" };

	/**
	 * 
	 * @Title: DateToLocalDateTime
	 * @Description: 日期转化为localDateTime
	 * @param date
	 * @return LocalDateTime 返回类型
	 * @author dy
	 * @date 2018年12月20日
	 * @throws
	 */
	public static LocalDateTime DateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	// 02. java.util.Date --> java.time.LocalDate
	public static LocalDate DateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}

	/**
	 * 
	 * @Title: DateToLocalTime
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回类型
	 * @author dy
	 * @date 2018年12月20日
	 * @throws
	 */
	public static LocalTime DateToLocalTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalTime();
	}

	/**
	 * 
	 * @Title: LocalDateTimeTodate
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回类型
	 * @author dy
	 * @date 2018年12月20日
	 * @throws
	 */
	public static Date LocalDateTimeTodate(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * 
	 * @Title: LocalDateToUdate
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回类型
	 * @author dy
	 * @date 2018年12月20日
	 * @throws
	 */
	public static Date LocalDateTodate(LocalDate localDate) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
		Instant instant = zonedDateTime.toInstant();
		return Date.from(instant);
	}

	/**
	 * 
	 * @Title: LocalTimeTodate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return Date 返回类型
	 * @author dy
	 * @date 2018年12月20日
	 * @throws
	 */
	public static Date LocalTimeTodate(LocalTime localTime) {
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	public static Date toDate(String sDate, String format) throws Exception {
		if ((sDate == null) || (sDate.equals(""))) {
			return null;
		}
		return parse(sDate, format);
	}

	private static Date parse(String date, String defaultFormat)
	        throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultFormat);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			for (int i = 0; i < optionDateFormats.length; i++) {
				try {
					SimpleDateFormat format = new SimpleDateFormat(
					        optionDateFormats[i]);
					return format.parse(date);
				} catch (ParseException e2) {
				}
			}

			if (null == date) {
				throw new Exception("Cannot parse date <null>");
			}
			throw new Exception("Cannot parse date " + date);
		}
	}

	public static String date2String(java.util.Date date, String format) {
		if (date == null)
			return null;
		if (format == null)
			format = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
}
