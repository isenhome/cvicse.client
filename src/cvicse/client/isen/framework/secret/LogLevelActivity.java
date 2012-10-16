package cvicse.client.isen.framework.secret;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cvicse.client.isen.framework.Conf;
import cvicse.client.isen.framework.Const;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.app.InitActivity;
import cvicse.client.isen.app.R;


/**
 * <p>
 * 修改LOG级别
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 22, 2011
 */
public class LogLevelActivity extends Activity {
	private static BysLogger log = BysLogger.getLogger();
	
	private SharedPreferences sharePreferences;
	
	private RadioGroup radioGroup;
	private RadioButton radioVerbose;
	private RadioButton radioDebug;
	private RadioButton radioInfo;
	private RadioButton radioWarn;
	private RadioButton radioError;
	private Button btnYes;
	
	private String logLevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_log_level);
        initView();
    }

    private void initView() {
    	radioGroup = (RadioGroup)findViewById(R.id.radioGroup);  
    	radioVerbose = (RadioButton)findViewById(R.id.radioVerbose);  
    	radioDebug = (RadioButton)findViewById(R.id.radioDebug);  
    	radioInfo = (RadioButton)findViewById(R.id.radioInfo);  
    	radioWarn = (RadioButton)findViewById(R.id.radioWarn);
    	radioError = (RadioButton)findViewById(R.id.radioError);
    	btnYes = (Button)findViewById(R.id.btnYes);
    	
    	sharePreferences = getSharedPreferences(Const.SHARED_PREF_MAIN, Context.MODE_PRIVATE);

    	logLevel = sharePreferences.getString(Const.KEY_LOG_LEVEL, Conf.LOG_LEVEL);

    	if(radioVerbose.getText().toString().equalsIgnoreCase(logLevel)) {
    		radioVerbose.setChecked(true);
    	} else if(radioDebug.getText().toString().equalsIgnoreCase(logLevel)) {
    		radioDebug.setChecked(true);
    	} else if(radioInfo.getText().toString().equalsIgnoreCase(logLevel)) {
    		radioInfo.setChecked(true);
    	} else if(radioWarn.getText().toString().equalsIgnoreCase(logLevel)) {
    		radioWarn.setChecked(true);
    	} else if(radioError.getText().toString().equalsIgnoreCase(logLevel)) {
    		radioError.setChecked(true);
    	}
    	
    	radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	if(checkedId == radioVerbose.getId()) {
            		logLevel = radioVerbose.getText().toString();
            	} else if(checkedId == radioDebug.getId()) {
            		logLevel = radioDebug.getText().toString();
            	} else if(checkedId == radioInfo.getId()) {
            		logLevel = radioInfo.getText().toString();
            	} else if(checkedId == radioWarn.getId()) {
            		logLevel = radioWarn.getText().toString();
            	} else if(checkedId == radioError.getId()) {
            		logLevel = radioError.getText().toString();
            	} 
            }
        }); 

    	btnYes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
        		// 执久化
        		SharedPreferences.Editor edit = sharePreferences.edit();
        		edit.putString(Const.KEY_LOG_LEVEL, logLevel);
        		edit.commit();
        		
        		log.warn("Log level has been set to: " + logLevel);

        		log.setLevel(logLevel);
        		Intent intent = new Intent(LogLevelActivity.this,InitActivity.class);
				startActivity(intent);
        		finish();
			}
		});
    	
    }

}
