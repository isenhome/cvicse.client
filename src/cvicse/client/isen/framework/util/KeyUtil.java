package cvicse.client.isen.framework.util;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import cvicse.client.isen.framework.Conf;
import cvicse.client.isen.framework.logging.BysLogger;
//import cvicse.client.isen.framework.secret.SecretMenuActivity;

import static cvicse.client.isen.framework.Conf.SECRET_KEYS;


/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 1, 2011
 */
public class KeyUtil {
    private static BysLogger log = BysLogger.getLogger();

    // For secret menu
    private static long now = 0;
    private static long lastKeyDownTime = 0;
    private static long KEY_DOWN_TIMEOUT = 1000;
    private static long KEY_DOWN_MIN_INTERVAL = 100;
    private static int currentKeyIndex = 0;

    /**
     * Process secret menu
     * @param context
     * @param keyCode
     * @param event
     * @return
     */
    public static boolean doSecretMenu(Context context, int keyCode, KeyEvent event) {
    	if(!Conf.SECRET_MENU_ENABLE) {
    		log.debug("Secret Menu is disabled.");
    		return false;
    	}

        String msg = "(onKeyDown)action=" + event.getAction()
                + ", keyCode=" + event.getKeyCode()
                + ", repeatCount=" + event.getRepeatCount();
        log.debug(msg);

        // Calculate continous key down
        now = System.currentTimeMillis();
        long interval = now - lastKeyDownTime;
        if (interval > KEY_DOWN_TIMEOUT || interval < KEY_DOWN_MIN_INTERVAL) {
            currentKeyIndex = 0;
            lastKeyDownTime = 0;
        }

        boolean bValidTimeInterval = (lastKeyDownTime == 0) || (interval >= KEY_DOWN_MIN_INTERVAL && interval <= KEY_DOWN_TIMEOUT);

        boolean isHandled = true;
        if (lastKeyDownTime == 0) {
            isHandled = false;
        }
        log.debug("currentKeyIndex: " + currentKeyIndex + ", interval: " + interval);

        if (currentKeyIndex < SECRET_KEYS.length - 1 && bValidTimeInterval && keyCode == KeyEvent.KEYCODE_MENU) {
            currentKeyIndex++;
            lastKeyDownTime = System.currentTimeMillis();
        } else if (currentKeyIndex == SECRET_KEYS.length - 1 && bValidTimeInterval && keyCode == KeyEvent.KEYCODE_MENU) {
            // Start secret menu activity
            //Intent intent = new Intent(context, SecretMenuActivity.class);
            //context.startActivity(intent);
            //currentKeyIndex = 0;
        }

        return isHandled;
    }
    
	/**
	 * Close keyboard
	 * @param context
	 * @param view
	 */
	public static void closeKeyboard(final Context context,final View view) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				InputMethodManager m = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				m.hideSoftInputFromWindow(
						view.getWindowToken(), 0);
			}
		}, 500);
	}

}
