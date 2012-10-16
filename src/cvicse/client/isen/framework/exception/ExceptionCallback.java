package cvicse.client.isen.framework.exception;

/**
 * <p>
 * Exception callback, customized cleanup when exception occurred
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 18, 2011
 */
public interface ExceptionCallback {

	/**
	 * 异常发生后的清理工作，由各Activity自己定义，构建ExceptionHandler时传�?
	 * @throws Exception
	 */
	public void cleanup() throws Exception;
	
}
