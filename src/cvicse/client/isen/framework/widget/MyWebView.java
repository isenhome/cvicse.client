package cvicse.client.isen.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ZoomButtonsController;

import cvicse.client.isen.framework.logging.BysLogger;

/**
 * 扩展WebView，移除Zoom按钮
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Oct 12, 2011
 */
public class MyWebView extends WebView {
	private static BysLogger log = BysLogger.getLogger();
	private ZoomButtonsController mZoomButtonsController;

	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyWebView(Context context) {
		super(context);
	}
	
	public ZoomButtonsController getZoomButtonsController() {
		if (mZoomButtonsController == null) {
			mZoomButtonsController = new MyZoomButtonsController(this);
			// ZoomButtonsController positions the buttons at the bottom, but in
			// the middle. Change their layout parameters so they appear on the
			// right.
			View controls = mZoomButtonsController.getZoomControls();
			ViewGroup.LayoutParams params = controls.getLayoutParams();
			if (params instanceof FrameLayout.LayoutParams) {
				FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) params;
				frameParams.gravity = Gravity.RIGHT;
			}
		}
		log.debug("MyWebView.getZoomButtonsController() mZoomButtonsController=" + mZoomButtonsController);
		log.debug("mZoomButtonsController.isVisible()=" + mZoomButtonsController.isVisible());
		return mZoomButtonsController;
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		log.verbose("ev.getAction(): " + ev.getAction() + ", getScale(): " + getScale() + ", zoomIn(): " + zoomIn());
//		
//		final float density = getContext().getResources().getDisplayMetrics().density;
//		log.verbose("density=" + density);
//		
//		return super.onTouchEvent(ev);
//	}

}
