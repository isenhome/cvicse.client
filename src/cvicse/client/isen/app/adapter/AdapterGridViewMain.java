package cvicse.client.isen.app.adapter;

import java.util.ArrayList;

import cvicse.client.isen.app.model.MainMenuItem;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AdapterGridViewMain extends BaseAdapter {

	ArrayList<MainMenuItem> mainMenuItems = new ArrayList<MainMenuItem>();
	
	//声明首页菜单编号
	public static final int ID_MMI_Create = 0;
	public static final int ID_MMI_Finish = 1;
	public static final int ID_MMI_Undo = 2;
	public static final int ID_MMI_Done = 3;
	
	private Context context;
	
	public AdapterGridViewMain(Context context){
		this.context = context;
		//mainMenuItems.add(new MainMenuItem(ID_MMI_Create,context.getString(R.string),R.drawable.));
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mainMenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mainMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
