package cvicse.client.isen.framework.secret;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cvicse.client.isen.framework.Conf;
import cvicse.client.isen.framework.Const;
import cvicse.client.isen.framework.SysEnv;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.app.R;


/**
 * <p>
 * 修改LOG级别
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 22, 2011
 */
public class SysEnvActivity extends Activity {
	private static BysLogger log = BysLogger.getLogger();
	
	private SharedPreferences sharePreferences;
	
	private RadioGroup radioGroup;
	private RadioButton radioRelease;
	private RadioButton radioDebug;
	private RadioButton radioMock;
	private Button btnYes;
	
	private String sysEnvStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_sys_env);
        initView();
    }

    private void initView() {
    	radioGroup = (RadioGroup)findViewById(R.id.radioGroup);  
    	radioRelease = (RadioButton)findViewById(R.id.radioRelease);  
    	radioDebug = (RadioButton)findViewById(R.id.radioDebug);  
    	radioMock = (RadioButton)findViewById(R.id.radioMock);  
    	btnYes = (Button)findViewById(R.id.btnYes);
    	
    	sharePreferences = getSharedPreferences(Const.SHARED_PREF_MAIN, Context.MODE_PRIVATE);
    	sysEnvStr = sharePreferences.getString(Const.KEY_SYS_ENV, Conf.SYS_ENV.toString());

    	SysEnv sysEnv = SysEnv.valueOf(sysEnvStr);
    	switch(sysEnv) {
    	case RELEASE:
    		radioRelease.setChecked(true);
    		break;
    	case DEBUG:
    		radioDebug.setChecked(true);
    		break;
    	case MOCK:
    		radioMock.setChecked(true);
    		break;
    	}
    	
    	radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            	switch (checkedId) {
				case R.id.radioRelease:
					sysEnvStr = SysEnv.RELEASE.toString();
					break;
				case R.id.radioDebug:
					sysEnvStr = SysEnv.DEBUG.toString();
					break;
				case R.id.radioMock:
					sysEnvStr = SysEnv.MOCK.toString();
					break;
				}
            }
        }); 

    	btnYes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
        		// 执久化
        		SharedPreferences.Editor edit = sharePreferences.edit();
        		edit.putString(Const.KEY_SYS_ENV, sysEnvStr);
        		edit.commit();
        		
        		log.warn("Sys env has been set to: " + sysEnvStr);

        		Conf.SYS_ENV = SysEnv.valueOf(sysEnvStr);
        		finish();
			}
		});

    }

}
