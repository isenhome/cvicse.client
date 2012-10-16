package cvicse.client.isen.framework.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>
 * IO utility class. 
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 11, 2011
 */
public class IOUtil {

	public static void close(Closeable obj) throws IOException {
		if(obj != null) {
			obj.close();
		}
	}

}
