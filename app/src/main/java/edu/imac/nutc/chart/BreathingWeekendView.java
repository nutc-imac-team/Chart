package edu.imac.nutc.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/4/7.
 */

public class BreathingWeekendView extends View {
    String[] weekend = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    int parentX;
    int parentY;
    Paint chartlinePaint;
    Paint chartPaint;
    Paint circlePaint;
    Paint textPaint;
    Paint weekendPaint;
     int[] lineData = {14, 15, 15, 15, 15, 14, 13};
     int[] minData = {9, 9, 9, 9, 9, 9, 9};
     int[] maxData = {25,24, 30, 25, 25, 25, 25};
    double doubleLineData;
    double doubleMinData;
    double doubleMaxData;
    int rotatMinData;
    int rotatMaxData;
    int maxValue=0;


    public BreathingWeekendView(Context context) {
        super(context);
    }

    public BreathingWeekendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        weekendPaint = new Paint();
        circlePaint = new Paint();
        textPaint = new Paint();
        chartlinePaint = new Paint();
        chartPaint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreathingRateView);
        int chartPaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor, 0xff003A61);
        float chartPaintTextSize = typedArray.getDimension(R.styleable.BreathingRateView_breathingRateTextSize, 50);
        weekendPaint.setColor(chartPaintTextColor);
        weekendPaint.setTextSize(chartPaintTextSize);
        int circlePaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor, 0xff0072C2);
        circlePaint.setColor(circlePaintTextColor);
        int textPaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor, Color.BLACK);
        float textPaintTextSize = typedArray.getDimension(R.styleable.BreathingRateView_breathingRateTextSize, 30);
        textPaint.setColor(textPaintTextColor);
        textPaint.setTextSize(textPaintTextSize);
        int chartLinePaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor, 0xff0072C2);
        chartlinePaint.setColor(chartLinePaintTextColor);
        int chartPaintColor = typedArray.getColor(R.styleable.BreathingRateView_breathingRateColor, 0xff55ffff);
        chartPaint.setColor(chartPaintColor);
        typedArray.recycle();


    }

    public BreathingWeekendView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentX = MeasureSpec.getSize(widthMeasureSpec);
        parentY = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<maxData.length;i++){
            if (maxValue<maxData[i]){
                maxValue=maxData[i];
            }
        }

        weekendPaint.setStrokeWidth(3);
        weekendPaint.setStyle(Paint.Style.FILL);
        int parentXPart = parentX / 8;
        int marginText = parentXPart / 5;


        //星期
        canvas.drawLine(0, parentY - weekendPaint.getTextSize(), parentX, parentY - weekendPaint.getTextSize(), weekendPaint);
        for (int i = 1; i <= weekend.length; i++) {
            canvas.drawLine(parentXPart * i, parentY - weekendPaint.getTextSize() + 10, parentXPart * i, parentY - weekendPaint.getTextSize() - 5, weekendPaint);
            canvas.drawText(weekend[i - 1], parentXPart * i - marginText, parentY, weekendPaint);
        }
        circlePaint.setStyle(Paint.Style.FILL);
        //圖
        Path chartPath = new Path();
        chartPaint.setStyle(Paint.Style.FILL);

        // MIN
        for (int i = 1; i <= minData.length; i++) {
            rotatMinData = maxValue - minData[i - 1];
            doubleMinData = Double.valueOf((rotatMinData * 100 / maxValue));
            if (i == 1) {
                chartPath.moveTo(parentXPart, setH(doubleMinData));
            }
            chartPath.lineTo(parentXPart * i, setH(doubleMinData));

            //MAX
            if ((i + 1) > minData.length) {
                for (int j = 7; j >= 1; j--) {
                    rotatMaxData = maxValue - maxData[j - 1];
                    doubleMaxData = Double.valueOf((rotatMaxData * 100 / maxValue));
                    chartPath.lineTo(parentXPart * j, setH(doubleMaxData));
                    if (j == 1) {
                        chartPath.close();
                    }
                }
            }
        }

        canvas.drawPath(chartPath, chartPaint);

        //圖表
        Path linePath = new Path();
        chartlinePaint.setStyle(Paint.Style.STROKE);
        chartlinePaint.setStrokeWidth(3);

        textPaint.setStyle(Paint.Style.FILL);

        for (int i = 1; i <= lineData.length; i++) {
            rotatMinData = maxValue - lineData[i - 1];
            doubleLineData = Double.valueOf((rotatMinData * 100 / maxValue));
            if (i == 1) {
                linePath.moveTo(parentXPart, setH(doubleLineData));
            }
            linePath.lineTo(parentXPart * i, setH(doubleLineData));
            canvas.drawCircle(parentXPart * i, setH(doubleLineData), 10, circlePaint);
            if ((setH(doubleLineData) + 50) > (parentY - weekendPaint.getTextSize())) {
                canvas.drawText(String.valueOf(lineData[i - 1]), parentXPart * i, setH(doubleLineData) - 50, textPaint);
            } else {
                canvas.drawText(String.valueOf(lineData[i - 1]), parentXPart * i, setH(doubleLineData) + 50, textPaint);
            }
        }
        canvas.drawPath(linePath, chartlinePaint);
    }

    public int setH(double Per) {
        float baseData = parentY - weekendPaint.getTextSize();
        return (int) ((Per > 100.0) ? baseData : ((baseData * Per) / 100));
    }

    public void setMaxData(int[] data) {
        maxData=data;
    }
    public void setMinData(int[] data) {
        minData=data;
    }
    public void setLineData(int[] data) {
        lineData=data;
    }
}
