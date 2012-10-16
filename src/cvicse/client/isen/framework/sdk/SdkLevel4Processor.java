package cvicse.client.isen.framework.sdk;

import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cvicse.client.isen.framework.logging.BysLogger;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 12, 2011
 */
public class SdkLevel4Processor implements SdkLevelProcessor {
	private static BysLogger log = BysLogger.getLogger();

	@Override
	public void setWebView(WebView web, DisplayMetrics metrics) {
		log.debug("setWebView()...");
		WebSettings settings = web.getSettings();
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE); // If set this,
															// webview's back
															// operation will
															// bad.
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(false);
		settings.setSaveFormData(true);
		settings.setSavePassword(true);
		settings.setBuiltInZoomControls(true);
		// Some site check UA, if use custom, site will show bad, so comment
		// this
		// If you want replace default Usage-Agent field, use this.
		// settings.setUserAgentString("Gorilla/Android HWAndroid/0.1");
		settings.setAllowFileAccess(true);
		settings.setNeedInitialFocus(false);
		settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
		web.setHorizontalScrollBarEnabled(false);
		float density = metrics.density;
		int ll = (int) (density * 10);
		web.setInitialScale(ll);

	}

	@Override
	public void onLowMemory(WebView web) {
		log.debug("onLowMemory()...");
	}

}
