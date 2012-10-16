package cvicse.client.isen.framework.exception;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.util.DialogUtil;
import cvicse.client.isen.framework.util.DialogUtil.DialogCallback;

import cvicse.client.isen.framework.resource.Res;
import cvicse.client.isen.app.R;

import static cvicse.client.isen.framework.Const.MSG_TYPE_EXCEPTION;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 16, 2011
 */
public class ExceptionHandler extends Handler {
	private static BysLogger log = BysLogger.getLogger();
	private Context context;
	private ExceptionCallback callback;
	private DialogCallback dialogCallback;

	public ExceptionHandler(Context context) {
		this.context = context;
	}
	
	public ExceptionHandler(Context context, ExceptionCallback callback) {
		this.context = context;
		this.callback = callback;
	}

	public ExceptionHandler(Context context, ExceptionCallback callback, DialogCallback dialogCallback) {
		this.context = context;
		this.callback = callback;
		this.dialogCallback = dialogCallback;
	}

	@Override
	public void handleMessage(Message msg) {
		if(callback != null) {
			try {
				callback.cleanup();
			} catch (Exception ex) {
				log.error("ExceptionCallback cleanup error.", ex);
				DialogUtil.showInfo(context, Res.resources.getString(R.string.error), Res.resources.getString(R.string.msg_system_error), dialogCallback);
			}
		}

		switch (msg.what) {
		case MSG_TYPE_EXCEPTION:
			if(msg.obj != null && msg.obj instanceof Exception) {
				Exception ex = (Exception)msg.obj;
				log.error("Error", ex);
				
				if(ex instanceof AppException
						|| ex instanceof SystemException
						|| ex instanceof ValidationException) {
					//Show dialog
					DialogUtil.showInfo(context, Res.resources.getString(R.string.error), ex.getMessage(), dialogCallback);
				} else if(ex instanceof NetworkException) {
					//Show dialog
					DialogUtil.showInfo(context, Res.resources.getString(R.string.error), Res.resources.getString(R.string.msg_network_error), dialogCallback);
				} else {
					//Show dialog
					DialogUtil.showInfo(context, Res.resources.getString(R.string.error), Res.resources.getString(R.string.msg_system_error), dialogCallback);
				}
			} else {
				log.error("Error: " + msg.obj);
				//Show dialog
				DialogUtil.showInfo(context, Res.resources.getString(R.string.error), Res.resources.getString(R.string.msg_system_error), dialogCallback);
			}
			break;

		default:
			break;

		}
	}

}
