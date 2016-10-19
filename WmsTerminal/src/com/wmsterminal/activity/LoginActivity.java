package com.wmsterminal.activity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wmsterminal.R;
import com.wmsterminal.activity.MainActivity;


import com.wmsterminal.base.BaseApplication;
import com.wmsterminal.base.activity.BaseResourcesActivity;
import com.wmsterminal.common.thread.HttpRequester;
import com.wmsterminal.common.thread.NetWorkPost;
import com.wmsterminal.common.thread.TaskExecutor;
import com.wmsterminal.model.Login;
import com.wmsterminal.model.User;
import com.wmsterminal.model.LoginDTO;
import com.wmsterminal.model.UserResponse;
import com.wmsterminal.util.IntentUtil;
import com.wmsterminal.util.ObjectUtil;
import com.wmsterminal.util.RequestUtil;
import com.wmsterminal.util.SharedUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by shuhaiyang on 2015/11/25.
 */
@SuppressLint("HandlerLeak")
public class LoginActivity extends BaseResourcesActivity implements View.OnClickListener {

    private boolean isHidden = true;
    @ViewInject(R.id.input_login_account)
    private EditText mEtAccount;

    @ViewInject(R.id.input_login_password)
    private EditText mEtPassword;

    @ViewInject(R.id.img_input_show)
    private ImageView mIvShow;

    @ViewInject(R.id.btn_login)
    private Button mBtnLogin;

    @ViewInject(R.id.tv_to_reg)
    private TextView toReg;

    public static boolean isForeground = false;
    
    private static Boolean isQuit = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initView();
        initSet();
        ViewUtils.inject(this);
        mEtAccount.setText(SharedUtil.getUser(this, "name"));
        mEtPassword.setText(SharedUtil.getUser(this, "pass"));
    }

    private void initSet() {
    }

    @Override
   /* protected void onResume() {
        super.onResume();
        //JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //JPushInterface.onPause(this);
    }*/

    @OnClick({R.id.tv_to_reg, R.id.img_input_show, R.id.btn_login})
    
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_reg:
                //IntentUtil.startActivity(LoginActivity.this, TextActivity.class);
                break;
          
            case R.id.img_input_show:
                if (isHidden) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                isHidden = !isHidden;
                break;
            case R.id.btn_login:
                 String username = mEtAccount.getText().toString().trim();
                 String password = mEtPassword.getText().toString().trim();
             	if (isFastDoubleClick()) {
                    return;
                }     
            
                if (checkData(username, password)) {
                    showProgressDialog("Logining...");

                    Map<String,String> userMap = new HashMap<>();
                    userMap.put("password", password);
                    userMap.put("email", username);
					
                    Login login = new Login();
                    login.setEmail(username);
                    login.setPassword(password);
                    Map<String, String> map = RequestUtil.requByJson("reqJson", userMap);
                    //Loge("getUser.wms", map.get("reqJson"));
                    String url = "http://106.185.35.33:9001/wms/user-authentication/login";
                    TaskExecutor.Execute(new NetWorkPost(this, "wms/user-authentication/login", handler).setMapOfData(map));
                   
                }
                break;
            default:
                break;
        }
    }

    public static JSONObject executeHttpPut(Map<String,String> jsonParam){  
        JSONObject resultObj = null;  
        HttpClient httpClient = new DefaultHttpClient();   
        HttpPost httpPut = new HttpPost("http://106.185.35.33:9001/wms/user-authentication/login");  
   
        try {  
            if (jsonParam != null) {  
                StringEntity entity = new StringEntity(jsonParam.get("reqJson"),HTTP.UTF_8);  
                httpPut.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPut);  
            resultObj = new JSONObject(EntityUtils.toString(response.getEntity()));  
        } catch (IOException | ParseException | JSONException e) {  
            e.printStackTrace();  
        }  
        return resultObj;  
    }  
    /**
     * ��������Ƿ�Ϊ��
     *
     * @param account
     * @param psw
     * @return
     */
    private boolean checkData(String account, String psw) {
        if (ObjectUtil.isEmpty(account)) {
            mEtAccount.setError("�˺Ų���Ϊ��");
            return false;
        } else if (ObjectUtil.isEmpty(psw)) {
            mEtPassword.setError("���벻��Ϊ��");
            return false;
        } else {
            return true;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hideProgressDialog();
            String json = msg.obj.toString();
            UserResponse userRes = null;
            try {
                userRes = JSON.parseObject(json, UserResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (userRes == null) {
                Toast.makeText(LoginActivity.this, "abnormal server", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userRes.getStatus().equals("success")) {
            	
                User user = new User();
                user.setUsername(mEtAccount.getText().toString().trim());
                user.setPassword(mEtPassword.getText().toString().trim());
                user.setToken(userRes.getToken());
                SharedUtil.saveUser(LoginActivity.this, user);
                IntentUtil.startActivity(LoginActivity.this, MainActivity.class);
                finish();
            } else {
                showError(userRes.getReason());
            }
        }
    };
    
    Handler backHandler = new Handler(){
    	
    	public void HandleMessage(Message msg){
    		super.handleMessage(msg);
    		isQuit = false;
    	}
    };
    
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK){
    		if(!isQuit){
    			isQuit = true;
    			Toast.makeText(this, "�ٰ�һ�η��ؼ��˳�����", Toast.LENGTH_SHORT).show();
    			backHandler.sendEmptyMessageDelayed(0, 2000);
    		}
    	}else {
    		finish();
    		System.exit(0);
    	}
		return false;
    }*/
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!isQuit) {
				isQuit = true;
				Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",Toast.LENGTH_SHORT).show();
				// ����handler�ӳٷ��͸���״̬��Ϣ
				backHandler.sendEmptyMessageDelayed(0, 2000);
			} else {
				//finish();
				//finishAndRemove();
				 ((BaseApplication) getApplication()).finish();
					System.exit(0);
				
			}
		}
		return false;
	}
   
}
