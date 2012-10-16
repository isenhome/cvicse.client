package cvicse.client.isen.framework.sdk;

import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cvicse.client.isen.framework.logging.BysLogger;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 12, 2011
 */
public class SdkLevel7Processor implements SdkLevelProcessor {
	private static BysLogger log = BysLogger.getLogger();

	@Override
	public void setWebView(WebView web, DisplayMetrics metrics) {
		log.debug("setWebView()...");
		if (web != null) {
			WebSettings settings = web.getSettings();
			settings.setJavaScriptEnabled(true);
			settings.setJavaScriptCanOpenWindowsAutomatically(false);
			settings.setSaveFormData(true);
			settings.setSavePassword(true);
			settings.setBuiltInZoomControls(true);
			// Some site check UA, if use custom, site will show bad, so comment this
			// If you want replace default Usage-Agent field, use this.
			// settings.setUserAgentString("Gorilla/Android HWAndroid/0.1");
			settings.setAllowFileAccess(true);
			settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
			settings.setNeedInitialFocus(false);
			settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
			web.setHorizontalScrollBarEnabled(false);
			if(log.isDebug()) {
				web.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
			}
			log.debug("WebView default scale: " + web.getScale());
		}

	}

	@Override
	public void onLowMemory(WebView web) {
		log.debug("onLowMemory()...");
		if (web != null) {
			web.freeMemory();
		}
	}
	
	/*
	 * Added by Roy on Oct 31, 2011 for WebView调试
	 */
	final class InJavaScriptLocalObj {
	    public void showSource(String html) {
	        log.debug("Source code of loaded web page is:\n" + html);
	    }
	
	}


}
