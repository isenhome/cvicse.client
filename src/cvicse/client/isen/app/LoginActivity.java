package cvicse.client.isen.app;

import org.json.JSONObject;

import cvicse.client.isen.framework.exception.AppException;
import cvicse.client.isen.framework.encryption.EncryptionMd5;
import cvicse.client.isen.framework.network.AsyncNetCall;
import cvicse.client.isen.framework.network.NetApi;
import cvicse.client.isen.framework.network.NetCallback;
import cvicse.client.isen.framework.network.NetCaller;
import cvicse.client.isen.framework.network.NetRequest;
import cvicse.client.isen.framework.network.NetResponse;
import cvicse.client.isen.framework.validation.Validator;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    private EditText userName;
    private EditText password;
    private Button btnLogin;
    
    private void initActivity(){
    	userName = (EditText)findViewById(R.id.editUsername);
    	password = (EditText)findViewById(R.id.editPasswd);
    	btnLogin = (Button)findViewById(R.id.btnLogin);
    	btnLogin.setOnClickListener(this);
    }
    
    @Override
	protected void onResume() {
		Log.i("message", "onResume()...");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i("message", "onPause()...");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i("message", "onStop()...");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.i("message", "onDestroy()...");
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){
		case R.id.btnLogin:
			doLogin();
			break;
		}
		
	}
	
	private void doLogin(){
		
		Validator.validateUsername(userName.getText().toString());
		Validator.validateUsername(password.getText().toString());
		
		NetRequest request = new NetRequest();
		request.addHttpParam("loginName",userName.getText().toString());
		request.addHttpParam("password", EncryptionMd5.Md5Twice(password.getText().toString()));
		NetCaller.doPost(exceptionHandler, NetApi.postLogin, request, new NetCallback(){

			@Override
			public void doInBackground(AsyncNetCall asyncNetCall,
					NetResponse netResponse) throws Exception {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCallback(NetResponse netResponse) throws Exception {
				// TODO Auto-generated method stub
				JSONObject result = (JSONObject) netResponse.getResult();
				int result_code = result.getInt("result_code");
				if(result_code == 200){
					boolean isAllow = result.getBoolean("isAllow");
					String strUserName = result.getString("userName");
					if(isAllow){
						Intent intent = new Intent(LoginActivity.this,MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				else if(result_code == 400){
					String mes = result.getString("mes");
					String desc = result.getString("desc");
					throw new AppException(mes+"\n"+desc);
				}
				else{
					throw new AppException("服务器异常");
				}
			}

			@Override
			public void onProgressUpdate(int progress) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

    
}
