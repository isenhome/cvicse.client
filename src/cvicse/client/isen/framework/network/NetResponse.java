package cvicse.client.isen.framework.network;

import org.apache.http.HttpEntity;


/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 */
public class NetResponse {
	private Exception exception;
	private Object result;
	private HttpEntity entity;

	public NetResponse() {
	}

	public NetResponse(Exception exception, Object result) {
		this.exception = exception;
		this.result = result;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object parser) {
		this.result = parser;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

}
