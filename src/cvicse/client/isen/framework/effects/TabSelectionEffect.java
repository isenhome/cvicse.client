package cvicse.client.isen.framework.effects;

import cvicse.client.isen.framework.Conf;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.util.DeviceUtil;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;

/**
 * <p>
 * Tab selection effects
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 29, 2011
 */
public class TabSelectionEffect implements Effect {
	private static BysLogger log = BysLogger.getLogger();
	
	private Context context;

	private int currentIndex = 0;  // Current index, it will be updated after new selection
	private int tabSize = 1;  //Tab size, default is 1
	private int tabWidth = 0;

	private TranslateAnimation animation;
	private AnimationListener listener;

	/**
	 * @param context
	 * @param tabSize
	 */
	public TabSelectionEffect(Context context, int tabSize) {
		this.context = context;
		this.tabSize = tabSize;
		
		initTabWidth();
	}

	/* (non-Javadoc)
	 * @see com.cvicse.sws.framework.effects.Effect#setListener(android.view.animation.Animation.AnimationListener)
	 */
	@Override
	public void setListener(AnimationListener listener) {
		this.listener = listener;
	}
	
	private void initTabWidth() {
		int screenWidth = DeviceUtil.getScreenWidth(context);
		tabWidth = (int)Math.round((double)screenWidth/(double)tabSize);
		log.debug("[TabSelectionEffect] screenWidth=" + screenWidth + ", tabSize=" + tabSize + ", tabWidth=" + tabWidth);
	}
	
	/**
	 * Get width of each tab
	 * @return
	 */
	public int getTabWidth() {
		return tabWidth;
	}

	/* (non-Javadoc)
	 * @see com.cvicse.sws.framework.effects.Effect#startEffect(android.view.View, int)
	 */
	@Override
	public void startEffect(View view, int index) {
		animation = new TranslateAnimation(tabWidth * currentIndex, tabWidth * index, 0.0f, 0.0f);
		animation.setDuration(Conf.TAB_EFFECTS_TIME);
		animation.setStartOffset(0);
		animation.setInterpolator(AnimationUtils.loadInterpolator(context, android.R.anim.bounce_interpolator));
		animation.setFillAfter(true);
		if(listener != null) {
			animation.setAnimationListener(listener);
		}
		view.setVisibility(View.VISIBLE);
		view.startAnimation(animation);

		//Post action
		currentIndex = index;
	}

}
