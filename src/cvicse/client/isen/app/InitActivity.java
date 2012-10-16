package cvicse.client.isen.app;

import org.json.JSONObject;

import cvicse.client.isen.framework.network.AsyncNetCall;
import cvicse.client.isen.framework.network.NetApi;
import cvicse.client.isen.framework.network.NetCallback;
import cvicse.client.isen.framework.network.NetCaller;
import cvicse.client.isen.framework.network.NetRequest;
import cvicse.client.isen.framework.network.NetResponse;
import cvicse.client.isen.framework.resource.Res;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class InitActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.activity_init);
        Res.SetResources(this.getResources());
        initMobo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_init, menu);
        return true;
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
    
    private void initMobo(){
    	NetRequest request = new NetRequest();
		NetCaller.doGet(exceptionHandler, NetApi.initMobo, request, new NetCallback(){

			@Override
			public void doInBackground(AsyncNetCall asyncNetCall,
					NetResponse netResponse) throws Exception {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCallback(NetResponse netResponse) throws Exception {
				// TODO Auto-generated method stub
				JSONObject result = (JSONObject)netResponse.getResult();
				int result_code = result.getInt("result_code");
				boolean success = result.getBoolean("success");
				if(result_code == 200){
					if(success){
						Intent intent = new Intent(InitActivity.this,LoginActivity.class);
						startActivity(intent);
						finish();
					}
					else{
						
					}
				}
				else{
					
				}
				
			}

			@Override
			public void onProgressUpdate(int progress) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
		});
    }

    
}
