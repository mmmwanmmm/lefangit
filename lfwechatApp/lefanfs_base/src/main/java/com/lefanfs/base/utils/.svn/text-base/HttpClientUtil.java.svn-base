package com.lefanfs.base.utils;

import com.lefanfs.base.interfaces.HttpRequestExceptionListener;
import com.lefanfs.base.interfaces.ResponseListener;
import com.lefanfs.base.web.SuperDispatcherServlet;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * HttpClient工具类
 * 
 * @author daniel
 */
public class HttpClientUtil {
	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	public static enum HttpMethod {
		get, post, put, delete, head, options, trace
	}

	public HttpClientUtil() {

	}

	public HttpClientUtil(ResponseListener responseListener, HttpRequestExceptionListener exceptionListener, Map<String, String> httpSetting) {
		this.httpSetting = httpSetting;
		this.responseListener = responseListener;
		this.exceptionListener = exceptionListener;
	}

	private ResponseListener responseListener;
	private HttpRequestExceptionListener exceptionListener;
	private Map<String, String> httpSetting;
	private int timeOut = 15000;

	public void setResponseListener(ResponseListener responseListener) {
		this.responseListener = responseListener;
	}

	public void setExceptionListener(HttpRequestExceptionListener exceptionListener) {
		this.exceptionListener = exceptionListener;
	}

	public void setHttpSetting(Map<String, String> httpSetting) {
		this.httpSetting = httpSetting;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * HTTP GET请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public String doHttpGet(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpGet(url);
	}

	/**
	 * HTTP POST请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public String doHttpPost(String url, String body) {
		StringEntity stringEntity = null;
		try {
			stringEntity = new StringEntity(body, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",doHttpPost.UnsupportedEncodingException=", e);
		}
		return httpPost(url, stringEntity);
	}

	/**
	 * HTTP POST请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public String doHttpPost(String url, Map<String, Object> paramMap) {
		StringEntity stringEntity = null;
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if (paramMap != null) {
			this._putLogId(paramMap);
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				NameValuePair kv = new BasicNameValuePair(key, value == null ? "" : value.toString());
				paramList.add(kv);
			}
		}
		try {
			stringEntity = new UrlEncodedFormEntity(paramList, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",doHttpPost.UnsupportedEncodingException=", e);
		}
		return httpPost(url, stringEntity);
	}

	private void _putLogId(Map<String, Object> paramMap) {
		if (paramMap.containsKey("doNotPostLogId") && "true".equals(paramMap.get("doNotPostLogId"))) {
			paramMap.remove("doNotPostLogId");
		} else {
			long threadId = Thread.currentThread().getId();
			paramMap.put("logId", SuperDispatcherServlet.getLogIdByThreadId(threadId));
		}
	}

	/**
	 * HttpPut 请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public String doHttpPut(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpPut(url);
	}

	/**
	 * Http Head 请求
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public String doHttpHead(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpHead(url);
	}

	/**
	 * Http Delete
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public String doHttpDelete(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpDelete(url);
	}

	/**
	 * Http Options
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public String doHttpOptions(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpOptions(url);
	}

	/**
	 * Http Trace
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public String doHttpTrace(String url, Map<String, Object> paramMap) {
		url = setRequestParam(url, paramMap);
		return httpTrace(url);
	}

	/**
	 * HTTP POST请求（适用于带附件的情况）
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @author daniel
	 */
	public String doHttpPostForMultipart(String url, Map<String, Object> paramMap) {
		MultipartEntity multipartEntity = new MultipartEntity();
		this._putLogId(paramMap);
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof InputStreamBody) {
				multipartEntity.addPart(key, (InputStreamBody) value);
			} else if (value instanceof StringBody) {
				multipartEntity.addPart(key, (StringBody) value);
			} else if (value instanceof ByteArrayBody) {
				multipartEntity.addPart(key, (ByteArrayBody) value);
			} else if (value instanceof FileBody) {
				multipartEntity.addPart(key, (FileBody) value);
			}
		}
		return httpPost(url, multipartEntity);
	}

	/**
	 * 设置请求参数
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 */
	private String setRequestParam(String url, Map<String, Object> paramMap) {
		if (paramMap != null && paramMap.size() > 0) {
			this._putLogId(paramMap);
			if (url.indexOf("?") == -1 && url.indexOf("&") == -1) {
				url += "?";
			} else {
				url += "&";
			}
			StringBuilder param = new StringBuilder();
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				param.append(key);
				param.append("=");
				param.append(value);
				param.append("&");
			}
			int last = param.lastIndexOf("&");
			if (last > 0 && last == param.length() - 1) {
				param.deleteCharAt(last);
			}
			url += param.toString();
		}
		return url;
	}

	private String httpGet(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpGet httpGet = new HttpGet(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpGet.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpGet);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
					// entity.consumeContent();
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",url:=" + url + "httpGet.Exception=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		long threadId = Thread.currentThread().getId();
		log.info("logID:" + SuperDispatcherServlet.getLogIdByThreadId(threadId) + ",doHttpGet code=" + httpStatusCode + " handl time:" + end + ", url:=" + url);
		return retStr;
	}

	/**
	 * 解压gzip
	 * 
	 * @param httpEntity
	 * @return
	 * @throws java.io.IOException
	 * @throws java.io.UnsupportedEncodingException
	 */
	private String makeResponseContent(HttpEntity httpEntity) throws IOException, UnsupportedEncodingException {
		String retStr;
		Header header = httpEntity.getContentEncoding();
		if (header != null && header.getValue().toLowerCase().indexOf("gzip") > -1) {
			InputStream is = httpEntity.getContent();
			GZIPInputStream gzin = new GZIPInputStream(is);
			InputStreamReader isr = new InputStreamReader(gzin, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
				sb.append("\r\n");
			}
			isr.close();
			gzin.close();
			retStr = sb.toString();
		} else {
			retStr = EntityUtils.toString(httpEntity);
		}
		return retStr;
	}

	private String httpPost(String url, HttpEntity entity) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpPost httpPost = new HttpPost(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpPost.setHeader(key, value);
			}
		}
		setHttpHeader(httpPost);
		httpPost.setEntity(entity);
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpPost);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
					// entity.consumeContent();
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",url:=" + url + "httpPost.Exception=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		long threadId = Thread.currentThread().getId();
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(threadId) + ",httpPost code=" + httpStatusCode + " handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private String httpDelete(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpDelete httpDelete = new HttpDelete(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpDelete.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpDelete);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpDelete.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpDelete handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private String httpPut(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpPut httpPut = new HttpPut(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpPut.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpPut);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpPut.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpPut handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private String httpHead(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpHead httpHead = new HttpHead(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpHead.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpHead);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpHead.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpHead handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private String httpOptions(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpOptions httpOptions = new HttpOptions(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpOptions.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpOptions);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpOptions.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpOptions handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private String httpTrace(String url) {
		long start = System.currentTimeMillis();
		String retStr = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeOut);
		HttpConnectionParams.setSoTimeout(params, timeOut);
		HttpTrace httpTrace = new HttpTrace(url);
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpTrace.setHeader(key, value);
			}
		}
		HttpResponse httpResponse = null;
		int httpStatusCode = 0;
		try {
			httpResponse = httpClient.execute(httpTrace);
			makeResponsListenerContent(httpResponse);
			httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpStatusCode) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					retStr = makeResponseContent(httpEntity);
				}
			}
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(httpStatusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpTrace.IOException=", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		long end = System.currentTimeMillis() - start;
		log.info("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",httpTrace handl time:" + end + ", url:=" + url);
		return retStr;
	}

	private void setHttpHeader(HttpPost httpPost) {
		if (this.httpSetting != null) {
			for (Map.Entry<String, String> entry : httpSetting.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				httpPost.setHeader(key, value);
			}
		}
	}

	private void makeResponsListenerContent(HttpResponse httpResponse) {
		if (this.responseListener != null) {
			Map<String, String> data = new HashMap<String, String>();
			int code = httpResponse.getStatusLine().getStatusCode();
			String reason = httpResponse.getStatusLine().getReasonPhrase();
			data.put("code", String.valueOf(code));
			data.put("reason", reason);
			responseListener.responseData(data);
		}
	}

	private void makeRequestExceptionListener(int statusCode, boolean timeout, String exception) {
		if (this.exceptionListener != null) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("code", String.valueOf(statusCode));
			data.put("timeout", String.valueOf(timeout));
			data.put("exception", exception);
			this.exceptionListener.exceptionData(data);
		}
	}

	public String doHttpStream(String url, String data) {
		long start = System.currentTimeMillis();
		String result = "";
		BufferedReader in = null;
		OutputStreamWriter out = null;
		int statusCode = 0;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setConnectTimeout(timeOut);
			httpConn.setReadTimeout(timeOut);
			httpConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
			httpConn.setRequestMethod("POST");
			httpConn.connect();
			if (data != null) {
				out = new OutputStreamWriter(httpConn.getOutputStream(), "UTF-8");
				out.write(data);
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String tempIn = "";
			while ((tempIn = in.readLine()) != null) {
				sb.append(tempIn);
			}
			result = sb.toString();
			statusCode = httpConn.getResponseCode();
		} catch (Exception e) {
			long end = System.currentTimeMillis() - start;
			this.makeRequestExceptionListener(statusCode, (end - this.timeOut) > 0, e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
			log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",_doHttpStream.Exception=", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				log.error("logId:" + SuperDispatcherServlet.getLogIdByThreadId(Thread.currentThread().getId()) + ",_doHttpStream.IOException=", ex);
			}
		}
		System.out.print(statusCode);
		return result;
	}

	public static void main(String[] args) throws Exception {

		HttpClientUtil HttpClientUtil = new com.lefanfs.base.utils.HttpClientUtil();
		HttpClientUtil.setResponseListener(new ResponseListener() {
			@Override
			public void responseData(Map<String, String> data) {
				System.out.println(data.toString());
			}
		});
		HttpClientUtil.setExceptionListener(new HttpRequestExceptionListener() {
			@Override
			public void exceptionData(Map<String, String> data) {
				System.out.println(data.toString());
			}
		});
		Map<String, String> httpSetting = new HashMap<String, String>();
		httpSetting.put("Accept", "application/json");
		// httpSetting.put("X-Client-Event", "123443657567657657ddsds");
		httpSetting.put("X-Hash-ip", "edeeeeeeeeeeeeeeeeeeeeee");
		HttpClientUtil.setHttpSetting(httpSetting);
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("a", new String("dddd"));
		// paramMap.put("b", new String("dddd"));
		String retJson = HttpClientUtil.doHttpStream("http://192.168.1.100/api/v2/categories/game/leafs/", null);

		// String retJson =
		// HttpClientUtil.setRequestParam("http://android.ccplay.com.cn/api/v2/categories/game/leafs/",
		// paramMap);
		System.out.print(retJson);
	}
}
