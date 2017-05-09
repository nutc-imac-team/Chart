package edu.imac.nutc.chart.br;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;
import edu.imac.nutc.chart.model.SetWeek;

/**
 * Created by user on 2017/4/7.
 */

public class BreathingWeekendView extends View {
    String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    private int measureHeight, measureWidth;
    private Paint textPaint, linePaint, graphicPaint;
    private int[] lineData = {0, 0, 0, 0, 0, 0, 0};
    private int[] maxGraphicData = {0, 0, 0, 0, 0, 0, 0};
    private int[] minGraphicData = {0, 0, 0, 0, 0, 0, 0};
    private float maxdata = 0;
    private int percent = 60;
    private int textOffest = 100;

    public BreathingWeekendView(Context context) {
        super(context);
    }

    public BreathingWeekendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new Paint();
        linePaint = new Paint();
        graphicPaint = new Paint();
        week = SetWeek.getWeek();
        for (int i = 0; i < maxGraphicData.length; i++) {
            if (maxdata < maxGraphicData[i]) {
                maxdata = maxGraphicData[i];
            }
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BreathingRateView);
        int textPaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_textColor, 0XFFFFFFFF);
        float textPaintTextSize = typedArray.getDimension(R.styleable.BreathingRateView_textSize, 64);
        textPaint.setColor(textPaintTextColor);
        textPaint.setTextSize(textPaintTextSize);

        int circlePaintTextColor = typedArray.getColor(R.styleable.BreathingRateView_textColor, 0xff039BE5);
        linePaint.setColor(circlePaintTextColor);
        int chartPaintColor = typedArray.getColor(R.styleable.BreathingRateView_textColor, 0xff0072C2);
        graphicPaint.setColor(chartPaintColor);
        typedArray.recycle();


    }

    public BreathingWeekendView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //星期
        for (int i = 0; i < week.length; i++) {
            canvas.drawText(week[i], i * (measureWidth / 7) + setWidth(2), measureHeight, textPaint);
        }
        //底線
        canvas.drawLine(0, measureHeight - textPaint.getTextSize(), measureWidth
                , measureHeight - textPaint.getTextSize(), textPaint);
        //圖形
        Path path = new Path();
        for (int index = 0; index < maxGraphicData.length; index++) {
            float nowdotX = index * (measureWidth / 7) + textPaint.getTextSize();
            float nowdotY = measureHeight - setHeight((maxGraphicData[index] / maxdata) * percent) - textPaint.getTextSize();
            if (index == 0) path.moveTo(nowdotX, nowdotY);
            else path.lineTo(nowdotX, nowdotY);
        }
        for (int index = minGraphicData.length - 1; index >= 0; index--) {
            float nowdotX = (index) * (measureWidth / 7) + textPaint.getTextSize();
            float nowdotY = measureHeight - setHeight((minGraphicData[index] / maxdata) * percent) - textPaint.getTextSize();
            path.lineTo(nowdotX, nowdotY);
        }
        path.close();
        canvas.drawPath(path, graphicPaint);
        //線
        path.reset();
        linePaint.setStyle(Paint.Style.FILL);
        path.moveTo(0 + textPaint.getTextSize(), measureHeight - setHeight((lineData[0] / maxdata) * percent) - textPaint.getTextSize());
        for (int i = 0; i < lineData.length; i++) {
            if (i > 0) {

                path.lineTo(i * (measureWidth / 7) + textPaint.getTextSize(), measureHeight - setHeight((lineData[i] / maxdata) * percent) - textPaint.getTextSize());
            }
            canvas.drawCircle(i * (measureWidth / 7) + textPaint.getTextSize(), measureHeight - setHeight((lineData[i] / maxdata) * percent) - textPaint.getTextSize(), setWidth(1), linePaint);
            canvas.drawText(lineData[i] + "", i * (measureWidth / 7) + setWidth(2), measureHeight - setHeight((lineData[i] / maxdata) * percent) - textOffest, textPaint);
        }
        linePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, linePaint);
    }


//        for (int i = 0; i < maxData.length; i++) {
//            if (maxValue < maxData[i]) {
//                maxValue = maxData[i];
//            }
//        }
//
//        textPaint.setStrokeWidth(3);
//        textPaint.setStyle(Paint.Style.FILL);
//        int parentXPart = parentX / 8;
//        int marginText = parentXPart / 5;
//
//
//        //星期
//        canvas.drawLine(0, parentY - textPaint.getTextSize(), parentX, parentY - textPaint.getTextSize(), textPaint);
//        for (int i = 1; i <= weekend.length; i++) {
//            canvas.drawLine(parentXPart * i, parentY - textPaint.getTextSize() + 10, parentXPart * i, parentY - textPaint.getTextSize() - 5, textPaint);
//            canvas.drawText(weekend[i - 1], parentXPart * i - marginText, parentY, textPaint);
//        }
//        //圖
//        Path chartPath = new Path();
//        graphicPaint.setStyle(Paint.Style.FILL);
//
//        // MIN
//        for (int i = 1; i <= minData.length; i++) {
//            rotatMinData = maxValue - minData[i - 1];
//            doubleMinData = Double.valueOf((rotatMinData * 100 / maxValue));
//            if (i == 1) {
//                chartPath.moveTo(parentXPart, setH(doubleMinData));
//            }
//            chartPath.lineTo(parentXPart * i, setH(doubleMinData));
//
//            //MAX
//            if ((i + 1) > minData.length) {
//                for (int j = 7; j >= 1; j--) {
//                    rotatMaxData = maxValue - maxData[j - 1];
//                    doubleMaxData = Double.valueOf((rotatMaxData * 100 / maxValue));
//                    chartPath.lineTo(parentXPart * j, setH(doubleMaxData));
//                    if (j == 1) {
//                        chartPath.close();
//                    }
//                }
//            }
//        }
//
//        canvas.drawPath(chartPath, graphicPaint);
//
//        //圖表
//        Path linePath = new Path();
//        graphicPaint.setStyle(Paint.Style.STROKE);
//        graphicPaint.setStrokeWidth(3);
//
//        textPaint.setStyle(Paint.Style.FILL);
//
//        for (int i = 1; i <= lineData.length; i++) {
//            rotatMinData = maxValue - lineData[i - 1];
//            doubleLineData = Double.valueOf((rotatMinData * 100 / maxValue));
//            if (i == 1) {
//                linePath.moveTo(parentXPart, setH(doubleLineData));
//            }
//            linePath.lineTo(parentXPart * i, setH(doubleLineData));
//            canvas.drawCircle(parentXPart * i, setH(doubleLineData), 10, graphicPaint);
//            if ((setH(doubleLineData) + 50) > (parentY - textPaint.getTextSize())) {
//                canvas.drawText(String.valueOf(lineData[i - 1]), parentXPart * i, setH(doubleLineData) - 50, textPaint);
//            } else {
//                canvas.drawText(String.valueOf(lineData[i - 1]), parentXPart * i, setH(doubleLineData) + 50, textPaint);
//            }
//        }
//        canvas.drawPath(linePath, graphicPaint);
//    }

    private int setWidth(double Per) {
        if (Per == -1)
            return -1;
        else if (Per == -2)
            return -2;
        return (int) ((Per > 100.0) ? measureWidth : ((measureWidth * Per) / 100));
    }

    private int setHeight(double Per) {
        if (Per == -1)
            return -1;
        else if (Per == -2)
            return -2;
        return (int) ((Per > 100.0) ? measureHeight : ((measureHeight * Per) / 100));
    }

    public void setMaxData(int[] data) {

        for (int i = 0; i < data.length; i++) {
            if (maxdata < data[i]) {
                maxdata = data[i];
            }
        }
        maxGraphicData = data;
    }

    public void setMinData(int[] data) {
        minGraphicData = data;
    }

    public void setLineData(int[] data) {
        lineData = data;
    }
}
