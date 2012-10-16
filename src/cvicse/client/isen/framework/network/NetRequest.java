package cvicse.client.isen.framework.network;

import java.util.HashMap;
import java.util.Map;


/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 */
public class NetRequest {
	private Object data;
	private Map<String, String> httpParams = new HashMap<String, String>();

	/**
	 * Constructor
	 */
	public NetRequest() {
	}
	
	/**
	 * Constructor with attributes
	 * @param data
	 * @param httpParams
	 */
	public NetRequest(Object data, Map<String, String> httpParams) {
		this.data = data;
		this.httpParams = httpParams;
	}

	/**
	 * Get request object
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Set request object
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Get HttpParams
	 * @return
	 */
	public Map<String, String> getHttpParams() {
		return httpParams;
	}

	/**
	 * Set HttpParams
	 * @param httpParams
	 */
	public void setHttpParams(Map<String, String> httpParams) {
		this.httpParams = httpParams;
	}
	
	/**
	 * Add a new HttpParam
	 * @param name
	 * @param value
	 */
	public void addHttpParam(String name, String value) {
		httpParams.put(name, value);
	}

}
