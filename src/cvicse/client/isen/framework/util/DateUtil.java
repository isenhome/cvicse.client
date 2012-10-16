package cvicse.client.isen.framework.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>Description: This class provide a set of utility method for processing java.util.Date.</p>
 *
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Aug 18, 2008
 */
public class DateUtil {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Default format: yyyy-MM-dd
     * @return String
     */
    public static String currentDateString() {
        return currentDateString(DEFAULT_DATE_PATTERN);
    }

    /**
     * Return the date string by specified pattern
     * @param pattern Specified date pattern
     * @return String
     */
    public static String currentDateString(String pattern) {
    	return getDateString(currentDate(), pattern);
    }
    
    /**
     * Get current date time string
     * @return
     */
    public static String currentTimeString() {
        return currentDateString(DEFAULT_TIME_PATTERN);
    }
    
    /**
     * Get the current Date object
     * @return Date
     */
    public static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }
    
    /**
     * Get current year
     * @return
     */
    public static int currentYear() {
    	return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Get current month
     * @return
     */
    public static int currentMonth() {
    	return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * Get current day
     * @return
     */
    public static int currentDay() {
    	return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * Get year of specified date string base on default pattern
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int getYear(String dateStr) throws ParseException {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(parseDate(dateStr));
    	return cal.get(Calendar.YEAR);
    }
    
    /**
     * Get month of specified date string base on default pattern
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int getMonth(String dateStr) throws ParseException {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(parseDate(dateStr));
    	return cal.get(Calendar.MONTH);
    }
    
    /**
     * Get day of specified date string base on default pattern
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static int getDay(String dateStr) throws ParseException {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(parseDate(dateStr));
    	return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * Get date string by specified date object. The default date pattern is
     * yyyy-MM-dd.
     * @param date Date object
     * @return Date string
     */
    public static String getDateString(Date date) {
    	return getDateString(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * Get date string by specified date object and pattern. 
     * @param date Date object
     * @param pattern Date pattern
     * @return Date string
     */
    public static String getDateString(Date date, String pattern) {
    	SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
        String result = simpleFormat.format(date);
        return result;
    }
    
    /**
     * Get date time string by specified date object
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
    	return getDateString(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * Parse specified year, month and day to Date object.
     *
     * @param year int
     * @param month int
     * @param day int
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(int year, int month, int day) throws ParseException {
        validateDate(year, month, day);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }
    
    /**
     * Parse specified dataStr to Date object use specified date pattern.
     * 
	 * @param dateStr Date string
	 * @param pattern Specified date pattern
	 * @return Date
     * @throws ParseException 
	 */
	public static Date parseDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		return new Date(simpleFormat.parse(dateStr).getTime());
	}

    /**
	 * Parse specified dataStr to Date object use default date pattern.
	 * 
	 * @param dateStr Date string
	 * @return Date
     * @throws ParseException 
	 */
	public static Date parseDate(String dateStr) throws ParseException {
		return parseDate(dateStr, DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * Get unified date format string
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateString(int year, int month, int day) throws ParseException {
		return getDateString(parseDate(year, month, day));
	}

    /**
     * Valite date.
     * 
     * @param year
     * @param month
     * @param day
     * @throws ParseException
     */
    private static void validateDate(int year, int month, int day) throws ParseException {
        if(year < 1900 || year > 2400)
             throw new ParseException ("Date parse error: the year is invalid.", 0);
         if(month < 1 || month > 12)
              throw new ParseException ("Date parse error: the month is invalid.", 0);
          if(day < 1 || day > 31)
               throw new ParseException ("Date parse error: the day is invalid.", 0);
    }

}
