package cvicse.client.isen.framework.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-7-21
 */
public class TimeUtil {
    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int mMinuts = mCalendar.get(Calendar.MINUTE);
        StringBuffer sb = new StringBuffer();
        if (mHour < 10) {
            sb.append('0');
        }
        sb.append(mHour).append(':');
        if (mMinuts < 10) {
            sb.append('0');
        }
        sb.append(mMinuts);
        return sb.toString();
    }

    /**
     * 获取当前日期，使用GMT+08:00 使用北京时间
     *
     * @return String 当前日期
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        StringBuffer sb = new StringBuffer();
        sb.append(calendar.get(Calendar.YEAR)).append("年")
                .append(calendar.get(Calendar.MONTH) + 1).append("月")
                .append(calendar.get(Calendar.DAY_OF_MONTH)).append("日");
        return sb.toString();
    }
}
