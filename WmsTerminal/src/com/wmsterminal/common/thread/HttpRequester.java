package com.wmsterminal.common.thread;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import android.widget.Toast;

public class HttpRequester {
	private static final String TAG = "HttpRequester";

	private void setClientParams(HttpClient client) {
		HttpParams httpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpConnectionParams.setSoTimeout(httpParams, 8000);		
	}

	/* Post Request */
	public String requestByPost(String url, List<NameValuePair> params,String head) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPatch httpPost = new HttpPatch();

		setClientParams(httpClient);
		try {
			httpPost.setURI(new URI(url));
			HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
			httpPost.setEntity(entity);
			httpPost.addHeader("authorization", head);
			HttpResponse response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity());
			} else {
				Log.d(TAG, "response.StatusCode:" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, "ClientProtocolException:" + e.getMessage());
			
		} catch (IOException e) {
			Log.e(TAG, "IOException:" + e.getMessage());
		} catch (URISyntaxException e) {
			Log.e(TAG, "URISyntaxException:" + e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return null;
	}

	private String getParams(List<NameValuePair> params) {
		if (null == params) {
			return null;
		}
		if (0 == params.size()) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (NameValuePair pair : params) {
			if (i == 0)
				sb.append("?");
			else
				sb.append("&");
			sb.append(pair.getName()).append("=").append(pair.getValue());
			i++;
		}

		return sb.toString();
	}

	public static String encodeParameters(List<NameValuePair> params) throws HttpException {
		if (null == params || 0 == params.size()) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		for (int j = 0; j < params.size(); j++) {
			if (j != 0) {
				buffer.append("&");
			}
			try {
				buffer.append(
						URLEncoder.encode(params.get(j).getName(),"UTF-8"))
						.append("=")
						.append(URLEncoder.encode(params.get(j).getValue(),"UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
				throw new HttpException(neverHappen.getMessage(), neverHappen);
			}
		}
		return buffer.toString();
	}

	/* Get Request */
	public String requestByGet(String url, List<NameValuePair> params,String head) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet();

		setClientParams(httpClient);
		try {
			String paramsSting = getParams(params);
			if (null != paramsSting && paramsSting.length() > 0) {
				url = url.concat(paramsSting);
			}

			httpGet.setURI(new URI(url));
			httpGet.addHeader("authorization", head);  
			HttpResponse response = httpClient.execute(httpGet);
            
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(),"utf-8");				
			} else {
				Log.d(TAG, "response.StatusCode:" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, "ClientProtocolException:" + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "IOException:" + e.getMessage());
		} catch (URISyntaxException e) {
			Log.e(TAG, "URISyntaxException:" + e.getMessage());
		}
		return null;
	}

	public String requestByGet(String url,String head) {
		return requestByGet(url, null,head);
	}
}
