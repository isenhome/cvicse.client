package cvicse.client.isen.framework.util;

import android.os.Message;
import cvicse.client.isen.framework.Const;
import cvicse.client.isen.framework.exception.ExceptionHandler;
import cvicse.client.isen.framework.logging.BysLogger;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 16, 2011
 */
public class ExceptionUtil {
	private static BysLogger log = BysLogger.getLogger();

	/**
	 * 发�?异常异步消息，UI界面将会自动调用ExceptionHandler提示用户
	 * @param exceptionHandler
	 * @param ex
	 */
	public static void sendExceptionMsg(ExceptionHandler exceptionHandler, Exception ex) {
		if(exceptionHandler == null) {
			log.error("", ex);
			return;
		}
		
		Message msg = exceptionHandler.obtainMessage();
		msg.what = Const.MSG_TYPE_EXCEPTION;
		msg.obj = ex;
		exceptionHandler.sendMessage(msg);
	}

}
