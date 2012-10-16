package cvicse.client.isen.framework.util;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import cvicse.client.isen.framework.Conf;
import cvicse.client.isen.framework.SysEnv;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.sdk.OSType;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 26, 2011
 */
public class DeviceUtil {
	private static BysLogger log = BysLogger.getLogger();

	/**
	 * 取得OS的类型，默认为OSType.ANDROID
	 * @param context
	 * @return
	 */
	public static OSType getOsType(Context context) {
		OSType osType = OSType.ANDROID;
		if(isOphone(context)) {
			osType = OSType.OPHONE;
		}
		
		return osType;
	}
	
	/**
	 * 通过package判断是否OPhone
	 * @param context
	 * @return
	 */
	private static boolean isOphone(Context context) {
		String packageName = "oms.mms";

		PackageManager pm = context.getPackageManager();
		Intent filterIntent = new Intent();
		filterIntent.setPackage(packageName);
		List<ResolveInfo> activities = pm.queryIntentActivities(filterIntent, 0);

		boolean result = activities == null? false : activities.size() > 0;

		return result;
	}
	
	/**
	 * 获取屏幕宽度
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	
	/**
	 * Get device ID
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		String deviceId = null;
		if (Conf.SYS_ENV == SysEnv.DEBUG) {
			deviceId = "1";
		} else {
			TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			deviceId = tm.getDeviceId();
		}
		log.debug("DeviceId found from phone is: " + deviceId);
		return deviceId;
	}

	/**
	 * Get line1 number
	 * @param context
	 * @return
	 */
	public static String getLine1Number(Context context) {
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String line1Number = tm.getLine1Number();
		log.debug("Line1 number found from phone is: " + line1Number);
		return line1Number;
	}
	
}
