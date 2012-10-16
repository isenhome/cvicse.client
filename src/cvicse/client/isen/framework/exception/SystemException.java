package cvicse.client.isen.framework.exception;

/**
 * <p>
 * System level exception.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 2, 2011
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SystemException(String exceptionMsg) {
		super(exceptionMsg);
    }

	public SystemException(String exceptionMsg, Throwable throwable) {
		super(exceptionMsg, throwable);
    }


}