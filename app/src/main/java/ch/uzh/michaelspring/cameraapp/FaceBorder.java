package ch.uzh.michaelspring.cameraapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by melchior on 07.08.15.
 */
public class FaceBorder extends View {
    Paint paint;


//    public FaceBorder(Context context) {
//        super(context);
//    }

    public FaceBorder(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(50);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("Test Text", 10, 10, paint);


    }
}
