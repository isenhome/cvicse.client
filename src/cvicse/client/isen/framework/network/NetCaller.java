package cvicse.client.isen.framework.network;

import cvicse.client.isen.framework.exception.ExceptionHandler;

/**
 * <p>
 * This class is used to do network invocation.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 */
public class NetCaller {
	
	/**
	 * @param exceptionHandler
	 * @param httpApi
	 * @param netRequest
	 * @param netCallback
	 * @return AsyncNetCall
	 */
	public static AsyncNetCall doGet(ExceptionHandler exceptionHandler, NetApi httpApi, NetRequest netRequest, NetCallback netCallback) {
		AsyncNetCall asyncNetCall = new AsyncNetCall(exceptionHandler, httpApi, NetMethod.GET, netRequest, netCallback);
		asyncNetCall.execute();
		return asyncNetCall;
	}

	/**
	 * @param exceptionHandler
	 * @param httpApi
	 * @param netRequest
	 * @param netCallback
	 * @return AsyncNetCall
	 */
	public static AsyncNetCall doPost(ExceptionHandler exceptionHandler, NetApi httpApi, NetRequest netRequest, NetCallback netCallback) {
		AsyncNetCall asyncNetCall = new AsyncNetCall(exceptionHandler, httpApi, NetMethod.POST, netRequest, netCallback);
		asyncNetCall.execute();
		return asyncNetCall;
	}

}
