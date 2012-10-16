package cvicse.client.isen.framework.network;

import static cvicse.client.isen.framework.Conf.CONNECTION_TIMEOUT;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;

import cvicse.client.isen.app.R;
import cvicse.client.isen.framework.Const;
import cvicse.client.isen.framework.GlobalVars;
import cvicse.client.isen.framework.exception.AppException;
import cvicse.client.isen.framework.exception.ExceptionHandler;
import cvicse.client.isen.framework.exception.NetworkException;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.resource.Res;
import cvicse.client.isen.framework.util.ExceptionUtil;

/**
 * <p>
 * Asynchronous network invocation
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 7, 2011
 * @param <T>
 */
public class AsyncNetCall extends AsyncTask<Object, Integer, NetResponse> {
	private static BysLogger log = BysLogger.getLogger();

	private NetApi netApi;
	private NetMethod netMethod;
	private NetRequest netRequest;
	private NetCallback netCallback;
	private ExceptionHandler exceptionHandler;

	public AsyncNetCall(ExceptionHandler exceptionHandler, NetApi netApi, NetMethod netMethod, NetRequest netRequest, NetCallback netCallback) {
		this.exceptionHandler = exceptionHandler;
		this.netApi = netApi;
		this.netMethod = netMethod;
		this.netRequest = netRequest;
		this.netCallback = netCallback;
	}

	@Override
	protected NetResponse doInBackground(Object... params) {
		publishProgress(0);
		NetResponse response = new NetResponse();
		try {
			HttpUriRequest httpUriRequest = netMethod.getHttpUriRequest(netApi, netRequest);
			
			if(log.isDebug()) {
				log.debug("Server request: \nURL=" + netApi.getPath() 
						+ "\nURI=" + httpUriRequest.getURI().toURL().getFile()
						+ "\nProxy=" + netApi.getProxy());
				
				StringBuilder sb = new StringBuilder();
				for (Entry<String, String> entry: netApi.getHeaders().entrySet()) {
					sb.append("\n" + entry.getKey() + "=" + entry.getValue());
				};
				log.debug("HTTP Headers: " + sb.toString());
				
				sb = new StringBuilder();
				for (Entry<String, String> entry: netRequest.getHttpParams().entrySet()) {
					sb.append("\n" + entry.getKey() + "=" + entry.getValue());
				};
				log.debug("HTTP Parameters: " + sb.toString());
			}

			// publishProgress("0", "do request");
			HttpResponse httpResponse = httpCall(httpUriRequest);
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			String statusMsg = httpResponse.getStatusLine().getReasonPhrase();

			Object result = null;
			if (HttpStatus.SC_OK == statusCode) {
				result = parseResponse(httpResponse);
			} else {
				throw new NetworkErrorException("Network invocation error. Detail is: " + statusCode + " - " + statusMsg);
			}

			response.setResult(result);
			response.setEntity(httpResponse.getEntity());

			try {
				netCallback.doInBackground(this, response);
			} catch (Exception e) {
				NetworkException ex = new NetworkException("NetCallback.doInBackground() error.", e);
				response.setException(ex);
			}
		} catch (ConnectTimeoutException e) {
			AppException ex = new AppException(Res.resources.getString(R.string.msg_network_timeout), e);
			response.setException(ex);
		} catch (SocketTimeoutException e) {
			AppException ex = new AppException(Res.resources.getString(R.string.msg_network_timeout), e);
			response.setException(ex);
		} catch (ConnectException e) {
			AppException ex = new AppException(Res.resources.getString(R.string.msg_network_unreachable), e);
			response.setException(ex);
		} catch (Exception e) {
			log.error("", e);
			NetworkException ex = new NetworkException("Network access error. Error message is: " + e.getMessage(), e);
			response.setException(ex);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(NetResponse response) {
		Exception ex = response.getException();
		if (ex != null) {
			ExceptionUtil.sendExceptionMsg(exceptionHandler, ex);
			
			if (exceptionHandler != null) {
				return;
			} else {
				log.error("Network invocation error, and ExceptionHandler is null.", ex);
			}
		}
		
		try {
			netCallback.onCallback(response);
		} catch (Exception e) {
			ExceptionUtil.sendExceptionMsg(exceptionHandler, e);
			return;
		}
		super.onPostExecute(response);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		log.debug("onProgressUpdate() progress=" + values[0]);
		try {
			netCallback.onProgressUpdate(values[0]);
		} catch (Exception e) {
			ExceptionUtil.sendExceptionMsg(exceptionHandler, e);
		}
	}
	
	public void updateProgress(Integer... values) {
		publishProgress(values);
	}

	/**
	 * @param httpUriRequest
	 * @return
	 * @throws Exception
	 */
	private HttpResponse httpCall(HttpUriRequest httpUriRequest) throws Exception {
		// Timeout setup
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, (int) CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, (int) CONNECTION_TIMEOUT);
		
		DefaultHttpClient httpClient = NetHttpClient.getHttpClient();
		httpClient.setParams(httpParams);
		//DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

		// If cmwap, proxy is needed
		if (GlobalVars.isCmWap) {
			log.debug("GlobalVars.isCmWap (network call): " + GlobalVars.isCmWap);
			HttpParams params = httpClient.getParams();
			params.setParameter(ConnRoutePNames.DEFAULT_PROXY, netApi.getProxy());
			params.setParameter(ConnManagerPNames.TIMEOUT, CONNECTION_TIMEOUT);
		}

		// RedirectHandler
		httpClient.setRedirectHandler(new RedirectHandler() {
			@Override
			public boolean isRedirectRequested(org.apache.http.HttpResponse httpResponse, HttpContext httpContext) {
				return false;
			}

			@Override
			public URI getLocationURI(org.apache.http.HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
				log.debug("getLocationURI()...");
				URI result = null;
				try {
					Header hd = httpResponse.getFirstHeader("Location");
					if (hd != null) {
						result = new URI(hd.getValue());
					}
				} catch (URISyntaxException e) {
					log.warn("Network invocation setting redirect handler error.", e);
				}
				return result;
			}
		});
		
		// Network call
		HttpResponse httpResponse = httpClient.execute(httpUriRequest);
		
		// Process result
		if (httpResponse == null) {
			throw new NetworkErrorException("Network invocation error, HttpResponse is null.");
		}

		return httpResponse;
	}

	/**
	 * @param contentType
	 * @param in
	 * @return
	 * @throws Exception
	 */
	private Object parseResponse(HttpResponse response) throws Exception {
		Object result = null;

		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		
		BufferedInputStream bufIn = new BufferedInputStream(in);
		
		// Header contentTypeHeader = entity.getContentType();
		
		switch (netApi) {
		case custom:
			result = bufIn;
			break;
		case sws:
			int k = -1;
			long skip = 0;
			int f = Const.ASCII_LESS;
			while ((k = in.read()) != f && k != -1) {
				skip++;
			}
			log.debug("Response skip: " + skip);
			bufIn.skip(skip);
			 
			if(log.isVerbose()) {
				BufferedReader readerIn = new BufferedReader(new InputStreamReader(in));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = readerIn.readLine()) != null) {
					sb.append(line);
				}
				String responseStr = "<" + sb.toString();
				log.verbose("Server response: \n" + responseStr);
				bufIn = new BufferedInputStream(new ByteArrayInputStream(responseStr.getBytes()));
			}

			// Response body
			Reader content = new BufferedReader(new InputStreamReader(bufIn));
			result = XmlPullParserFactory.newInstance().newPullParser();
			((XmlPullParser) result).setInput(content);
			break;
		default:
			if(log.isVerbose()||log.isDebug()) {
				BufferedReader readerIn = new BufferedReader(new InputStreamReader(in));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = readerIn.readLine()) != null) {
					sb.append(line);
				}
				result = new JSONObject(sb.toString());
			}

			break;
		}

		return result;
	}
}