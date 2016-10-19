package com.wmsterminal.common.thread;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.JsonObject;

public class AnswerService {

	private static final String TAG="AnswerService";
		
	public static String getAnswerListAll(String url,String head){
		
		//String result=new HttpRequester().requestByGet(url,head);
	    List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        formparams.add(new BasicNameValuePair("status", "picked"));
        String result = new HttpRequester().requestByPost(url, formparams, head);
		Log.e("aaaa", ""+result);
			return result;
			
	}

	
}
