package cvicse.client.isen.framework.resource;

import android.content.res.Resources;

/**
 * <p>
 * This class is used to hold Android Resources object
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 2, 2011
 */
public class Res {

	/**
	 * Android resources object
	 */
	public static Resources resources;
	
	public static void SetResources(Resources resources){
		Res.resources = resources;
	}

}
