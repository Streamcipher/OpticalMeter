package ch.uzh.michaelspring.cameraapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by melchior on 07.08.15.
 */
public class FaceBorder extends View {

    public FaceBorder(Context context) {
        super(context);
    }

    public FaceBorder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i(Constants.TAG, "onDraw in faceborder is run");

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawText("Test Text", 10, 10, paint);


    }
}
