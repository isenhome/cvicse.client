package cvicse.client.isen.framework;

//import com.cvicse.sws.app.model.UserModel;

import cvicse.client.isen.framework.sdk.SdkLevelProcessor;


/**
 * <p>
 * 定义全局变量
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 16, 2011
 */
public class GlobalVars {

	/**
	 * Processor for particular SDK level
	 */
	public static volatile SdkLevelProcessor sdkLevelProcessor;

	/**
	 * If APN is cmwap
	 */
	public static volatile boolean isCmWap = false;
	
	/**
	 * Version name got from remote server
	 */
	public static volatile String versionNameRemote = "";
	
	/**
	 * Upgrade type
	 */
	public static volatile int upgradeType = 0;
	
	/**
	 * Download URL for upgrade APK file
	 */
	public static volatile String clientDownloadUrl = "";
	
	/**
	 * Current font size
	 */
	//public static final UserModel userModel = new UserModel();
	
}
