package cvicse.client.isen.framework.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import cvicse.client.isen.framework.exception.NetworkException;


/**
 * <p>
 * Define all HTTP methods used in system
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 */
public enum NetMethod {
	
	GET{
		/* (non-Javadoc)
		 * @see com.cvicse.sws.framework.network.NetMethod#getHttpUriRequest(com.cvicse.sws.framework.network.NetApi, com.cvicse.sws.framework.network.NetRequest)
		 */
		@Override
		public HttpUriRequest getHttpUriRequest(
				NetApi netApi, 
				NetRequest netRequest) throws NetworkException {
			try{
				String url = netApi.getPath();
				
				Map<String, String> params = netRequest.getHttpParams();
				StringBuilder buf = new StringBuilder();
				int k = 0;
				for (Map.Entry<String, String> entry: params.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if(k++ > 0) {
						buf.append("&");
					}
					buf.append(key + "=" + value);
				}

				HttpGet httpGet = new HttpGet(url + buf.toString());

				//Cookies
				setupCookie(url, netApi);

				//Headers
				setupHeaders(httpGet, netApi.getHeaders());

				return httpGet;
			}catch(Exception e){
				throw new NetworkException("Get HttpGet method error. ", e);
			}
		}
	},

	POST{
		@Override
		public HttpUriRequest getHttpUriRequest(
				NetApi netApi, 
				NetRequest netRequest) throws NetworkException{
			try{
				String url = netApi.getPath();
				HttpPost httpPost = new HttpPost(url);
				
				//Request parameters
				List<NameValuePair> httpParams = new ArrayList<NameValuePair>();  
				
				Map<String, String> params = netRequest.getHttpParams();
				for (Map.Entry<String, String> entry: params.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					httpParams.add(new BasicNameValuePair(key, value));  
				}
				
				httpPost.setEntity(new UrlEncodedFormEntity(httpParams));

				//Cookies
				setupCookie(url, netApi);
				
				//Headers
				setupHeaders(httpPost, netApi.getHeaders());
				
				return httpPost;
			}catch(Exception e){
				throw new NetworkException("Get HttpPost method error. ", e);
			}
		}
	};

	/**
	 * Obtain HttpUriRequest object.
	 * @param httpApi
	 * @param netRequest
	 * @return
	 */
	public abstract HttpUriRequest getHttpUriRequest(
			NetApi httpApi, 
			NetRequest netRequest) throws NetworkException;

	/**
	 * Setup all headers
	 * @param httpUriRequest
	 * @param headers
	 */
	public void setupHeaders(
			HttpUriRequest httpUriRequest, 
			Map<String, String> headers) {

//		httpUriRequest.setHeader("x-agent", "WirelessCity/Android 1.0 [reqlocal]");
//		httpUriRequest.setHeader("connection", "close");
//		httpUriRequest.setHeader("user-agent", "Dalvik/1.4.0 (Linux; U; Android 2.3; HTC A9188 Build/GRI40)");
//		httpUriRequest.setHeader("Cookie", "JSESSIONID=16DC5F8C63CED77023A9D74DEE97512D;");
//		httpUriRequest.setHeader("accept-encoding", "gzip");
//		httpUriRequest.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//		httpUriRequest.setHeader("accept-charset", "utf-8");

		for (Map.Entry<String, String> entry: headers.entrySet()) {
			httpUriRequest.setHeader(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Setup cookies
	 * @param url
	 * @param netApi
	 */
	public void setupCookie(String url, NetApi netApi) {
//		CookieManager manager = CookieManager.getInstance();
//        Cookie[] cookies = null;
//        net.dratek.browser.http.URL myURL = null;
//        try {
//            myURL = net.dratek.browser.http.URL.parseString(url);
//            cookies = manager.getCookies(myURL);
//        } catch (Exception ex) {
//        	throw new NetworkException("Cookie handling error. ", ex);
//        }
//
//        if (cookies != null && cookies.length > 0) {
//            StringBuffer sb = new StringBuffer();
//            for (Cookie ck : cookies) {
//                sb.append(ck.name + "=" + ck.value + ";");
//            }
//            String ckv = sb.toString();
//            if (ckv.length() > 0) {
//            	netApi.addExtraHeader("Cookie", ckv);
//            }
//        }
	}

}
