package cvicse.client.isen.framework.effects;

import android.view.View;
import android.view.animation.Animation.AnimationListener;

/**
 * <p>
 * Effect interface. AnimationListener is supported.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 29, 2011
 */
public interface Effect {

	/**
	 * Start animation
	 * @param view
	 * @param index
	 */
	public void startEffect(View view, int index);

	/**
	 * Set AnimationListener
	 * @param listener
	 */
	public void setListener(AnimationListener listener);
	
}
