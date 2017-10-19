package ua.nure.mykytenko.SummaryTask4.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.exception.AppException;

/**
 * Util methods for work with dates
 * 
 * @author A.Mykytenko
 */
public final class DateUtil {

	private static final Logger LOG = Logger.getLogger(DateUtil.class);

	/**
	 * Extracts a java.sql.Date object from String
	 * 
	 * @return java.sql.Date object
	 */
	public static java.sql.Date extractDate(String date, String formatString) {
		if (date == null || date.isEmpty()) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		java.util.Date parsed;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			throw new AppException(e.getMessage(), e);
		}

		return new java.sql.Date(parsed.getTime());
	}
	
	/**
	 * Extracts a java.util.Date object from java.sql.Date date and time
	 * 
	 * @return java.util.Date object
	 */
	public static java.util.Date extractDate(java.sql.Date date, Time time) {
		java.util.Date t = new java.util.Date(time.getTime());

		Calendar dCal = Calendar.getInstance();
		if (date != null) {
			java.util.Date d = new java.util.Date(date.getTime());
			dCal.setTime(d);
		}

		Calendar tCal = Calendar.getInstance();
		tCal.setTime(t);

		dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));

		return dCal.getTime();
	}
	
	/**
	 * Returns the difference between two dates. It uses to get the duration of the train trip
	 * 
	 * @return String
	 */
	public static String getDuration(Date depDate, Date arrDate) {
		int h = (int) getDateDiff(arrDate, depDate, TimeUnit.HOURS);
		int m = (int) getDateDiff(arrDate, depDate, TimeUnit.MINUTES) - 60 * h;

		LOG.debug(String.format("h=%d, m=%d", h, m));
		return String.format("%d:%d", h, m);
	}

	public static Date today() {
		return Calendar.getInstance().getTime();
	}

	public static Time getTime(Date date) {
		return (date == null) ? null : new Time(date.getTime());
	}

	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
