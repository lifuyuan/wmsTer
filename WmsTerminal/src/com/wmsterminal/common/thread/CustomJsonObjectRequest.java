package com.wmsterminal.common.thread;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;    

import org.json.JSONException;
import org.json.JSONObject;    

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.wmsterminal.activity.wms.QueryOutBoundActivity;
import com.wmsterminal.util.SharedUtil;

public class CustomJsonObjectRequest extends Request<JSONObject> {
private Listener<JSONObject> listener;
private Map<String, String> params;
private String mToken;

public CustomJsonObjectRequest(int method, String url,String token,
        Listener<JSONObject> reponseListener, ErrorListener errorListener) {
    super(Method.PATCH, url, errorListener);
    this.listener = reponseListener;
    this.params = params;
    this.mToken = token;
}

@Override
protected Map<String, String> getParams()
        throws com.android.volley.AuthFailureError {

    return params;
};

@Override
protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
    try {

        String jsonString = new String(response.data,
                HttpHeaderParser.parseCharset(response.headers));

        return Response.success(new JSONObject(jsonString),
                HttpHeaderParser.parseCacheHeaders(response));
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return Response.error(new ParseError(e));

    } catch (JSONException je) {
        je.printStackTrace();
        return Response.error(new ParseError(je));
    }
}

@Override
public byte[] getBody() throws AuthFailureError {

     Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }

        return null;
}


 private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }

            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

@Override
public Map<String, String> getHeaders() throws AuthFailureError {
    // TODO Auto-generated method stub
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("authorization", mToken); 		             
    return headers;
}

@Override
protected void deliverResponse(JSONObject response) {
    // TODO Auto-generated method stub
    Log.d("check deliverResponse:", ""+response);
    }
}
