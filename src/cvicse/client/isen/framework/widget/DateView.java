package cvicse.client.isen.framework.widget;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import cvicse.client.isen.framework.logging.BysLogger;
import cvicse.client.isen.framework.util.DateUtil;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 16, 2011
 */
public class DateView extends TextView {
	private static BysLogger log = BysLogger.getLogger();

	public DateView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DateView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DateView(Context context) {
		super(context);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		setText(DateUtil.currentDateString());
		setOnClickListener(clickListener);
	}
	
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			try {
				String dateStr = getText().toString();
				new DatePickerDialog(getContext(), dateSetListener, DateUtil.getYear(dateStr), DateUtil.getMonth(dateStr), DateUtil.getDay(dateStr)).show();
			} catch (Exception ex) {
				throw new IllegalStateException("DateView exception.", ex);
			}
		}
	};
	
	private OnDateSetListener dateSetListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			try {
				log.debug("year=" + year + ", month=" + month + ", day=" + day);
				setText(DateUtil.parseDateString(year, month + 1, day));
			} catch (Exception ex) {
				throw new IllegalStateException("DateView exception.", ex);
			}
		}
	};

}
