package cvicse.client.isen.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cvicse.client.isen.framework.Const;
import cvicse.client.isen.framework.exception.ExceptionCallback;
import cvicse.client.isen.framework.exception.ExceptionHandler;
import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.util.DialogUtil.DialogCallback;
import cvicse.client.isen.framework.util.ExceptionUtil;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 11, 2011
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
	private static BysLogger log = BysLogger.getLogger();

	protected ExceptionHandler exceptionHandler;
	protected SharedPreferences preferences;
	protected ProgressDialog progressDialog;
	protected String msgInfo;
	
	protected Button btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		log.verbose("onCreate()...");
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		super.onCreate(savedInstanceState);
		
		exceptionHandler = new ExceptionHandler(this, 
				new ExceptionCallback() {
			
					@Override
					public void cleanup() throws Exception {
						cleanupStatus();
					}
				},
				new DialogCallback() {

					@Override
					public void callbackOk() {
						doExceptionInfoOk();
					}

					@Override
					public void callbackCancel() {
						doExceptionInfoOk();
					}
				});
		preferences = getSharedPreferences(Const.SHARED_PREF_MAIN, Context.MODE_PRIVATE);
	}

	@Override
	protected void onResume() {
		log.verbose("onResume()...");
		super.onResume();
	}

	@Override
	protected void onPause() {
		log.verbose("onPause()...");
		super.onPause();
	}

	@Override
	protected void onStop() {
		log.verbose("onStop()...");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		log.verbose("onDestroy()...");
		super.onDestroy();
	}

	protected void init() {
		log.verbose("init()...");
		initTitle();

		//Init progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.setMessage(getString(R.string.msg_loading));

		msgInfo = getString(R.string.label_info);
		
		try {
			Bundle bundle = getIntent().getExtras();
			if(bundle == null) {
				bundle = new Bundle();
			}
			initParams(bundle);
			initView();
		} catch (Exception ex) {
			ExceptionUtil.sendExceptionMsg(exceptionHandler, ex);
		}
	}

	protected void initTitle() {
		log.verbose("initTitle()...");
		TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
		if(txtTitle != null) {
			txtTitle.setText(getTitle());
		}
	}
	
	/**
	 * Initialize parameters passed by Bundle
	 * @param bundle
	 */
	protected void initParams(Bundle bundle) throws Exception {
	}
	
	/**
	 * Initialization process in an Activity
	 */
	protected void initView() throws Exception {
		btnBack = (Button)findViewById(R.id.btnBack);
		
		if(btnBack != null) {
			if(!isSupportBack()) {
				btnBack.setVisibility(View.INVISIBLE);
			} else {
				btnBack.setOnClickListener(this);
			}
		}
	}
	
	/**
	 * Determine if back button is supported
	 * @return
	 */
	protected boolean isSupportBack() {
		return true;
	}
	
	protected void setupStatus() {
		if(progressDialog != null) {
			progressDialog.show();
		}
	}

	protected void cleanupStatus() {
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
	}
	
	protected void doExceptionInfoOk() {
	}
	
	@Override
	public void onClick(View view) {
		log.verbose("onClick()...");
		try {
			switch (view.getId()) {
			case R.id.btnBack:
				onBackPressed();
				break;
			}
		} catch (Exception ex) {
			ExceptionUtil.sendExceptionMsg(exceptionHandler, ex);
		}
	}

}
