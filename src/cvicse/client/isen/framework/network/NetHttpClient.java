package cvicse.client.isen.framework.network;

import org.apache.http.impl.client.DefaultHttpClient;

public class NetHttpClient {
	
	private static DefaultHttpClient httpClient;
	
	public static DefaultHttpClient getHttpClient(){
		if(httpClient == null){
			httpClient = new DefaultHttpClient();
		}
		return httpClient;
	}
}
//HttpClientParams.setCookiePolicy(client.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
//
//if (null != cookie)
//
//     postMethod.setHeader("cookie", cookie);

