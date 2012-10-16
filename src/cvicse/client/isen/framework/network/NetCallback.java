package cvicse.client.isen.framework.network;


/**
 * <p>
 * This class is used to handle customized logic after network invocation.
 * A NetResponse object will be passed in. You are able to find whether a
 * Exception is occured and a wrapped parser.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 */
public interface NetCallback {
	
	/**
	 * 网络访问后台线程处理
	 * @throws Exception
	 */
	public void doInBackground(AsyncNetCall asyncNetCall, NetResponse netResponse) throws Exception;

	/**
	 * 网络访问完成处理，UI线程�?
	 * @param netResponse
	 * @throws Exception
	 */
	public void onCallback(NetResponse netResponse) throws Exception;
	
	/**
	 * 更新进度
	 * @param progress
	 * @throws Exception
	 */
	public void onProgressUpdate(int progress) throws Exception;
	
}
