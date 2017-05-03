package edu.imac.nutc.chart.hrv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;
import edu.imac.nutc.chart.model.SetWeek;

/**
 * Created by cheng on 2017/5/2.
 */

public class HrvChart extends View{
    private int measureHeight, measureWidth;
    private Paint textPaint,linePaint,graphicPaint;
    private String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    private float[] lineData = {49, 44, 46, 42, 34, 27, 26};
    private float[] graphicData = {29, 24, 29, 30, 24, 17, 9};
    private float maxdata = 0;
    private int percent=60;
    private int textOffest=100;
    private boolean download=false;
    public HrvChart(Context context) {
        this(context,null);
    }

    public HrvChart(Context context, AttributeSet attr) {
        super(context, attr);
        textPaint = new Paint();
        linePaint=new Paint();
        graphicPaint=new Paint();
        week= SetWeek.getWeek();
        TypedArray a = context.obtainStyledAttributes(attr,
                R.styleable.HrvChart);
        for (int i = 0; i < lineData.length; i++) {
            if (maxdata < lineData[i]) {
                maxdata = lineData[i];
            }
        }
        int lineColor = a.getColor(R.styleable.HrvChart_lineColor,
                0XFFB60B99);
        int graphicColor = a.getColor(R.styleable.HrvChart_graphicColor,
                0XA0740A62);
        float textSize = a.getDimension(R.styleable.HrvChart_textSize, 64);
        int textColor = a.getColor(R.styleable.HrvChart_textColor,
                0XFFFFFFFF);
        linePaint.setColor(lineColor);
        graphicPaint.setColor(graphicColor);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(download) {
            //星期
            for (int i = 0; i < week.length; i++) {
                canvas.drawText(week[i], i * (measureWidth / 7) + setWidth(2), measureHeight, textPaint);
            }
            //底線
            canvas.drawLine(0, measureHeight - textPaint.getTextSize(), measureWidth
                    , measureHeight - textPaint.getTextSize(), textPaint);
            //圖形
            Path path = new Path();
            for (int index = 0; index < lineData.length; index++) {
                float nowdotX = index * (measureWidth / 7) + textPaint.getTextSize();
                float nowdotY = measureHeight - setHeight((lineData[index] / maxdata) * percent) - textPaint.getTextSize();
                if (index == 0) path.moveTo(nowdotX, nowdotY);
                else path.lineTo(nowdotX, nowdotY);
            }
            for (int index = graphicData.length - 1; index >= 0; index--) {
                float nowdotX = (index) * (measureWidth / 7) + textPaint.getTextSize();
                float nowdotY = measureHeight - setHeight((graphicData[index] / maxdata) * percent) - textPaint.getTextSize();
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

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
    public void setLineData(float[] lineData){
        this.lineData=lineData;
        for (int i = 0; i < lineData.length; i++) {
            if (maxdata < lineData[i]) {
                maxdata = lineData[i];
            }
        }
    }
    public void setGraphicData(float[] graphicData){
        this.graphicData=graphicData;
    }
    public void setDownload(boolean download){
        this.download=download;
    }
}