package cvicse.client.isen.framework.sdk;

import android.util.DisplayMetrics;
import android.webkit.WebView;

/**
 * @author Dongling on Sep 7, 2011
 * 
 */
public interface SdkLevelProcessor {

	/**
	 * Set WebView for any Android OS Version
	 * 
	 * @param web
	 *            WebView
	 * @param metrics
	 */
	public void setWebView(WebView web, DisplayMetrics metrics);

	/**
	 * onLowMemory support
	 * 
	 * @param web
	 *            WebView
	 */
	public abstract void onLowMemory(WebView web);
	
}
