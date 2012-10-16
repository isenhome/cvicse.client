package cvicse.client.isen.framework;

import android.view.KeyEvent;

import java.util.HashMap;
import java.util.Locale;

/**
 * <p>
 * System configuration is defined here
 * </p>
 * <p>
 * It is just for the first running of app. After that, the configuration will
 * be loaded into preferences instead of definition here.
 * </p>
 *
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Jul 8, 2011
 */
public class Conf {

    /**
     * 日志级别，默认INFO
     * 注意：不要修改默认�?，如�?EBUG，请通过Secret Menu设置
     */
    public static final String LOG_LEVEL = "VERBOSE";

    /**
     * 系统环境，默认RELEASE
     * 注意：不要修改默认�?，如�?EBUG，请通过Secret Menu设置
     */
    public static volatile SysEnv SYS_ENV = SysEnv.MOCK; 

    /**
     * Secret Menu是否打开
     */
    public static final boolean SECRET_MENU_ENABLE = true;

    /**
     * Default locale.
     */
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    /**
	 * HTTP connection timeout
	 */
	public static final long CONNECTION_TIMEOUT = 10000;

    /**
	 * Tab effects time
	 */
	public static final long TAB_EFFECTS_TIME = 600;
	
	/**
	 * Mock loading time
	 */
	public static final long MOCK_LOADING_TIME = 1000;

	/**
	 * Default encoding
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
     * Server list, they can be dynamically set in secret menu
     */
    public static final String[][] SERVER_MAP = new String[][] {
    	{"DEV",          "http://192.168.132.5:8300/mobile"},
    	{"RELEASE",      "http://192.168.132.5:8300/mobile"}
    };

    /**
     * Server地址
     * 注意：不要修改默认�?，请通过Secret Menu设置
     */
    public static volatile String SERVER_ADDR = SERVER_MAP[0][1];

    /**
     * Proxy for CMWAP
     */
    public static final String PROXY_HOST = "10.0.0.172";
    public static final int PROXY_PORT = 80;

    /**
     * Secret keys
     */
    public static final int[] SECRET_KEYS = new int[]{
            KeyEvent.KEYCODE_MENU,
            KeyEvent.KEYCODE_MENU,
            KeyEvent.KEYCODE_MENU,
            KeyEvent.KEYCODE_MENU,
            KeyEvent.KEYCODE_MENU
    };

    /**
     * Activity entry list.
     */
    public static final HashMap<String, String> ENTRY_MAP = new HashMap<String, String>();

    static {
        ENTRY_MAP.put("Login Manual", "com.cvicse.sws.app.LoginManualActivity");
        ENTRY_MAP.put("Flow Leave Request", "com.cvicse.sws.app.flow.LeaveRequestActivity");
    }

}
