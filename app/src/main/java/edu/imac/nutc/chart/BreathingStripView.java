package edu.imac.nutc.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/4/7.
 */

public class BreathingStripView extends View {
    Paint chartpaint;
    Paint circlePaint;
    int parentX, parentY;
    public BreathingStripView(Context context) {
        super(context);
    }
    public BreathingStripView(Context context, AttributeSet attrs) {
        super(context, attrs);
        chartpaint = new Paint();
        circlePaint=new Paint();
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.BreathingRateView);
        int chartpaintColor=typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor,0xff0072C2);
        chartpaint.setColor(chartpaintColor);
        int circlePaintColor=typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor,0xff039BE5);
        circlePaint.setColor(circlePaintColor);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,30, parentY, chartpaint);
        canvas.drawCircle(15, parentY /2,15, circlePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentX= MeasureSpec.getSize(widthMeasureSpec);
        parentY = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentX |MeasureSpec.EXACTLY, parentY |MeasureSpec.EXACTLY);
    }

}
