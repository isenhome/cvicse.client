package cvicse.client.isen.framework.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

/**
 * <p>
 * 高亮Adapter，支持设置默认高亮的item，目前用于secret menu中server list的�?中效�?
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Oct 10, 2011
 */
public class HighlightAdapter extends SimpleAdapter {
	public static String LABEL = "label";
	public static String VALUE = "value";
	
	private List<? extends Map<String, ?>> data;
	private String defaultValue;

	public HighlightAdapter(
			Context context, 
			List<? extends Map<String, ?>> data, 
			int resource, 
			String[] from, 
			int[] to,
			String defaultValue) {
		super(context, data, resource, from, to);
		this.data = data;
		this.defaultValue = defaultValue;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		Map<String, ?> itemMap = data.get(position);
		Object valueObj = itemMap.get(VALUE);
		if(defaultValue != null && valueObj !=null && defaultValue.equalsIgnoreCase(valueObj.toString())) {
			view.setBackgroundColor(0x20FFFFFF);
		}

		return view;
	}

}
