package cvicse.client.isen.framework;


/**
 * <p>
 * 定义常量，所有变量必须是static和final
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 16, 2011
 */
public class Const {
	
	/**
	 * Return code
	 */
	 public static final int CODE_SUCESS = 0;

	/**
	 * 各应用的启动类名，不包括package
	 */
	 public static final String APP_LAUNCH_CLASS = ".MainActivity";

    /**
     * Category: server
     */
    public static final String CATEGORY_SECRET = "org.rs.category.SECRET";
    
	/**
	 * Detail node id
	 */
	public static final int NODE_ID_DETAIL = -99;
    
	/**
	 * Message type: exception
	 */
	public static final int MSG_TYPE_EXCEPTION = 0;
	
	/**
	 * ASCII code for < symbol
	 */
	public static final int ASCII_LESS = 60;

	/**
	 * Shared Preferences
	 */
	public static final String SHARED_PREF_MAIN = "prefMain";

	/**
	 * Key
	 */
	public static final String KEY_PHONE_NUMBER = "keyPhoneNumber";
	public static final String KEY_USERNAME = "keyUsername";
	public static final String KEY_PASSWD = "keyPasswd";
	
	public static final String KEY_LOG_LEVEL = "keyLogLevel";
	public static final String KEY_SYS_ENV = "keySysEnv";
	public static final String KEY_SERVER_ADDR = "keyServerAddr";
	
	public static final String KEY_FLOW_ID = "flowId";
	public static final String KEY_FLOW_NAME = "flowName";
	public static final String KEY_FLOW_INSTANCE_ID = "flowInstanceId";
	public static final String KEY_FLOW_CURRENT_NODE_ID = "currentNodeId";
	public static final String KEY_FLOW_CURRENT_NODE_NAME = "currentNodeName";

	/**
	 * Upgrade type
	 */
	public static final int UPGRADE_NO_NEED = 0;
	public static final int UPGRADE_OPTIONAL = 1;
	public static final int UPGRADE_MANDATORY = 2;
	
	/**
	 * Font size
	 */
	public static final int FONT_SIZE_SMALL = 0;
	public static final int FONT_SIZE_MEDIUM = 1;
	public static final int FONT_SIZE_LARGE = 2;
	
	/*
	 * Added by Roy on Oct 27, 2011 for 重构字体大小设置
	 */
	public static final int[][] FONT_SIZE_ARRAY = new int[][] {
		{70, 85, 105}, //LOW
		{80, 105, 135}, //MEDIUM
		{100, 130, 180}  //HIGH
	};

	/**
	 * String keys
	 */
	public static final String KEY_MSG = "msg";

}
