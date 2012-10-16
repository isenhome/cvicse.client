package cvicse.client.isen.framework.widget;

import android.view.View;
import android.widget.ZoomButtonsController;

/**
 * 扩展WebView，移除Zoom按钮
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Oct 13, 2011
 */
public class MyZoomButtonsController extends ZoomButtonsController {

	public MyZoomButtonsController(View ownerView) {
		super(ownerView);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

}
