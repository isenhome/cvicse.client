package cvicse.client.isen.framework.util;

import android.content.Context;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 29, 2011
 */
public class DimenUtil {

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}

}
