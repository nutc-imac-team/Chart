package edu.imac.nutc.chart.br;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;

/**
 * Created by user on 2017/4/7.
 */

public class BreathingStripView extends View {
    private Paint textPaint,graphicPaint;
    int parentX, parentY;
    public BreathingStripView(Context context) {
        super(context);
    }
    public BreathingStripView(Context context, AttributeSet attrs) {
        super(context, attrs);
        graphicPaint=new Paint();
        textPaint = new Paint();
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.BreathingRateView);
        int circlePaintColor=typedArray.getColor(R.styleable.BreathingRateView_graphicColor,0xff039BE5);
        graphicPaint.setColor(circlePaintColor);
        int chartpaintColor=typedArray.getColor(R.styleable.BreathingRateView_textColor,0xff0072C2);
        textPaint.setColor(chartpaintColor);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,30, parentY, textPaint);
        canvas.drawCircle(15, parentY /2,15, graphicPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentX= MeasureSpec.getSize(widthMeasureSpec);
        parentY = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentX |MeasureSpec.EXACTLY, parentY |MeasureSpec.EXACTLY);
    }

}
