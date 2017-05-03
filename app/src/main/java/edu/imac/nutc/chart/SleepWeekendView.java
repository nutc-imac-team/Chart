package edu.imac.nutc.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/3/29.
 */

public class SleepWeekendView extends View {
    String[] weekend = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    int parentWidth, parentHeight;
    Paint chartPaint, dataPaint;
    public int[] chartData = {100, 200, 300, 250, 240, 350, 100};

    public SleepWeekendView(Context context) {
        super(context);
    }

    public SleepWeekendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        chartPaint = new Paint();
        dataPaint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SleepClassesView);
        int chartPaintTextColor = typedArray.getColor(R.styleable.SleepClassesView_sleepClassesTextColor, 0xff003A61);
        float chartPaintTextSize = typedArray.getDimension(R.styleable.SleepClassesView_sleepClassesTextSize, 50);
        chartPaint.setColor(chartPaintTextColor);
        chartPaint.setTextSize(chartPaintTextSize);
        int dataPaintTextColor = typedArray.getColor(R.styleable.SleepClassesView_sleepClassesTextColor, 0xff9B4FE9);
        dataPaint.setColor(dataPaintTextColor);
        typedArray.recycle();
    }

    public SleepWeekendView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        chartPaint.setColor(0xff003A61);
        chartPaint.setStrokeWidth(3);
        int parentXPart = parentWidth / 8;
        int marginText = parentXPart / 5;
        chartPaint.setTextSize(50);

        //星期
        canvas.drawLine(0, parentHeight - chartPaint.getTextSize(), parentWidth, parentHeight - chartPaint.getTextSize(), chartPaint);//橫

        for (int i = 1; i <= weekend.length; i++) {
            canvas.drawLine(parentXPart * i, parentHeight - chartPaint.getTextSize() + 10, parentXPart * i, parentHeight - chartPaint.getTextSize() - 5, chartPaint);
            canvas.drawText(weekend[i - 1], parentXPart * i - marginText, parentHeight, chartPaint);
        }

        Path dataPath = new Path();
        //資料
        for (int i = 1; i <= chartData.length; i++) {
            if (i == 1) {
                dataPath.moveTo(parentXPart * i, parentHeight - chartPaint.getTextSize());
                dataPath.lineTo(parentXPart * i, chartData[i - 1]);
            } else if (i + 1 > chartData.length) {
                dataPath.lineTo(parentXPart * i, chartData[i - 1]);
                dataPath.lineTo(parentXPart * i, parentHeight - chartPaint.getTextSize());
                dataPath.close();
            } else {
                dataPath.lineTo(parentXPart * (i), chartData[i - 1]);
            }
        }

        canvas.drawPath(dataPath, dataPaint);

    }
}
