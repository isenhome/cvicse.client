package cvicse.client.isen.framework.exception;

/**
 * <p>
 * Application logical exception.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 6, 2011
 */
public class NetworkException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NetworkException(String exceptionMsg) {
		super(exceptionMsg);
    }

	public NetworkException(String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
    }


}