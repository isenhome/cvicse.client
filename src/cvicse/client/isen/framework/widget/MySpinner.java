package cvicse.client.isen.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import cvicse.client.isen.app.R;
import cvicse.client.isen.framework.logging.BysLogger;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 17, 2011
 */
public class MySpinner extends Spinner {
	private static BysLogger log = BysLogger.getLogger();

	public MySpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MySpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MySpinner(Context context) {
		super(context);
	}
	
	@Override
	protected void onFinishInflate() {
		log.debug("onFinishInflate()...");
		super.onFinishInflate();

		SpinnerAdapter oldAdapter = getAdapter();
		if(oldAdapter != null) {
			ArrayAdapter<Object> spinnerAdapter = new ArrayAdapter<Object>(getContext(), R.layout.widget_spinner);
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			int len = oldAdapter.getCount();
			log.debug("MySpinner is transferring adapter, the number is: " + len);
			for (int i = 0; i < len; i++) {
				spinnerAdapter.add(oldAdapter.getItem(i));
			}
			
			setAdapter(spinnerAdapter);
		}
		
	}

}
