package edu.imac.nutc.chart.sleep;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;

/**
 * Created by user on 2017/3/30.
 */

public class SleepStripView extends View{
    Paint chartPaint;
    int data;
    int parentY, parentX;

    public SleepStripView(Context context) {
        super(context);
    }
    
    public SleepStripView(Context context, AttributeSet attrs) {
        super(context, attrs);
        chartPaint = new Paint();
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.SleepClassesView);
        int textColor=typedArray.getColor(R.styleable.SleepClassesView_textColor,0xff9B4FE9);
        float textSize=typedArray.getDimension(R.styleable.SleepClassesView_textSize,30);
        chartPaint.setColor(textColor);
        chartPaint.setTextSize(textSize);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentX = MeasureSpec.getSize(widthMeasureSpec);
        parentY = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentX |MeasureSpec.EXACTLY, parentY |MeasureSpec.EXACTLY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Double doubleData;
        data=500;
        doubleData= Double.valueOf((data*100/ parentX));
        canvas.drawRect(0,0,setW(doubleData),parentY, chartPaint);
    }

    public int setW(double Per) {
        return (int) ((Per > 100.0) ? parentX : ((parentX * Per) / 100));
    }


}
