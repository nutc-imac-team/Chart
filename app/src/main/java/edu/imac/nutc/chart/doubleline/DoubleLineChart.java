package edu.imac.nutc.chart.doubleline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;

/**
 * Created by cheng on 2017/4/11.
 */

public class DoubleLineChart extends View{
    private int measureHeight, measureWidth;
    private Paint textPaint,firstLinePaint,secondLinePaint;
    private String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    private int[] firstData = {49, 44, 46, 42, 34, 27, 26};
    private int[] secondData = {29, 24, 29, 30, 24, 17, 9};
    private float maxdata = 0;

    public DoubleLineChart(Context context) {
        this(context,null);
    }

    public DoubleLineChart(Context context, AttributeSet attr) {
        super(context, attr);
        textPaint = new Paint();
        firstLinePaint=new Paint();
        secondLinePaint=new Paint();
        TypedArray a = context.obtainStyledAttributes(attr,
                R.styleable.DoubleLineChart);
        for (int i = 0; i < firstData.length; i++) {
            if (maxdata < firstData[i]) {
                maxdata = firstData[i];
            }
        }
        int textColor = a.getColor(R.styleable.DoubleLineChart_textColor,
                0XFFFFFFFF);
        int firstLineColor = a.getColor(R.styleable.DoubleLineChart_first_lineColor,
                0XFFFFFFFF);
        int secondLineColor = a.getColor(R.styleable.DoubleLineChart_second_lineColor,
                0XFFFFFFFF);
        float textSize = a.getDimension(R.styleable.DoubleLineChart_textSize, 64);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        firstLinePaint.setColor(firstLineColor);

        secondLinePaint.setColor(secondLineColor);

        a.recycle();
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
        Path path=new Path();
        //第一條線較高
        firstLinePaint.setStyle(Paint.Style.FILL);
        path.moveTo(0+textPaint.getTextSize(),measureHeight - setHeight((firstData[0] / maxdata) * 90));
        for (int i = 0; i < firstData.length; i++) {
            if(i>0){

                path.lineTo(i * (measureWidth / 7) + textPaint.getTextSize(),measureHeight - setHeight((firstData[i] / maxdata) * 90));
            }
            canvas.drawCircle(i * (measureWidth / 7) + textPaint.getTextSize(),measureHeight - setHeight((firstData[i] / maxdata) * 90),setWidth(1),firstLinePaint);

        }
        firstLinePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,firstLinePaint);
        path.reset();

        //第二條線較低
        secondLinePaint.setStyle(Paint.Style.FILL);
        path.moveTo(0+textPaint.getTextSize(),measureHeight - setHeight((secondData[0] / maxdata) * 90));
        for (int i = 0; i < secondData.length; i++) {
            if(i>0){
                path.lineTo(i * (measureWidth / 7) + textPaint.getTextSize(),measureHeight - setHeight((secondData[i] / maxdata) * 90));
            }
            canvas.drawCircle(i * (measureWidth / 7) + textPaint.getTextSize(),measureHeight - setHeight((secondData[i] / maxdata) * 90),setWidth(1),secondLinePaint);
            canvas.drawText(secondData[i]+"", i * (measureWidth / 7) + setWidth(2), measureHeight - setHeight((secondData[i] / maxdata) * 100), textPaint);
        }
        secondLinePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path,secondLinePaint);
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
}
