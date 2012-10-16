package cvicse.client.isen.framework.sdk;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 12, 2011
 */
public class SdkFactory {

	/**
	 * Get SdkLevelProcessor by current SDK level
	 * @return SdkLevelProcessor
	 */
	public static SdkLevelProcessor getSdkLevelProcessor() {
		SdkLevelProcessor result = null;
		
		int sdkLevel = Integer.parseInt(android.os.Build.VERSION.SDK);
		if(sdkLevel < 5) {
			result = new SdkLevel4Processor();
		} else {
			result = new SdkLevel7Processor();
		}
		
		return result;
	}
	
}
