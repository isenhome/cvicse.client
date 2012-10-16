package cvicse.client.isen.framework.network;

import static cvicse.client.isen.framework.Conf.DEFAULT_ENCODING;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;

import cvicse.client.isen.framework.Conf;

/**
 * <p>
 * All HTTP API methods are defined here.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 6, 2011
 */
public enum NetApi {
	initMobo{
		@Override
		public String getPath() {
			return Conf.SERVER_ADDR + "/initMobo.mobo?iswap=1&cmd=request";
		}
	},
	postLogin{
		@Override
		public String getPath() {
			return Conf.SERVER_ADDR + "/postLogin.mobo?iswap=1&cmd=request";
		}
	},
	login {

		@Override
		public String getPath() {
			return Conf.SERVER_ADDR + "/mobileLoginAction.action";
		}

	},
	queryWorkFlowList {

		@Override
		public String getPath() {
			return Conf.SERVER_ADDR + "/mobileQueryWorkFlowListAction.action";
		}

	},
	custom {
		
	},
	sws{
		//用于中外运xml解析
	}
	;
	

	/**
	 * HTTP headers
	 */
	private Map<String, String> headers = new HashMap<String, String>();

	/**
	 * Default proxy
	 */
	private HttpHost proxy;
	
	private String path;
	
	/**
	 * Constructor
	 */
	private NetApi() {
		//Set common headers
		headers.put("user-agent", getUserAgent());
//		headers.put("accept-encoding", "gzip");
		headers.put("accept-charset", DEFAULT_ENCODING);
		headers.put("x-agent", "Android [RS]");
		headers.put("accept", "text/xml,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("connection", "close");
		//headers.put("MoboSession", "D4A7C4EF27B14098AF4CA7900F57DA64");

		headers.putAll(getDefaultHeaders());
		
		proxy = new HttpHost(Conf.PROXY_HOST, Conf.PROXY_PORT, "http");
	}

	/**
	 * Return URL path, exclude basic IP and port
	 * @return
	 */
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Return proxy which is defined in string resource file
	 * @return
	 */
	public HttpHost getProxy() {
		return proxy;
	}

	/**
	 * Retrieve all defined HTTP headers.
	 * @return
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Define default headers here, it is basic HTTP header.
	 * @return
	 */
	public Map<String, String> getDefaultHeaders() {
		return new HashMap<String, String>();
	}

	/**
	 * Add more extra headers besides default headers defined in HttpApi.
	 * @param name
	 * @param value
	 */
	public void addExtraHeader(String name, String value) {
		headers.put(name, value);
	}
	
	/**
	 * @return http agent
	 */
	private String getUserAgent() {
		return System.getProperty("http.agent");
	}

}
