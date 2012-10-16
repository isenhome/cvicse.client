package cvicse.client.isen.framework.exception;

/**
 * <p>
 * Application logical exception.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 2, 2011
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AppException(String exceptionMsg) {
		super(exceptionMsg);
    }

	public AppException(String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
    }


}