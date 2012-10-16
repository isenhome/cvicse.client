package cvicse.client.isen.framework.nav;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 11, 2011
 */
public class BgIPhoneTab extends View {
	private Paint paint;

	public BgIPhoneTab(Context context) {
		super(context);
	}

	public BgIPhoneTab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BgIPhoneTab(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		//Create a rectangle
		Rect rect = new Rect();
		
		this.getDrawingRect(rect);
		
		canvas.drawColor(0xFF000000);
		paint.setColor(0xFF434343);
		canvas.drawLine(rect.left, rect.top, rect.right, rect.top, paint);

		int color = 46;
		int height = 23;
		for( int i = 0; i < height; i++ ) {
			paint.setARGB( 255, color, color, color );
			canvas.drawRect( 
					rect.left, rect.top + i + 1, 
					rect.right, rect.top + i + 2, paint );
			color--;
		}
	}

}