package edu.imac.nutc.chart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cheng on 2017/3/30.
 */

public class BarChart extends View {
    private int measureHeight, measureWidth;
    private Paint textPaint,graphicPaint;
    private String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    private double[] data = {100.0, 50.2, 80.8, 150.4, 230.5, 70.9, 80.8};
    private float maxdata = 0;

    public BarChart(Context context) {
        this(context,null);
    }

    public BarChart(Context context, AttributeSet attr) {
        super(context, attr);
        textPaint = new Paint();
        graphicPaint=new Paint();
        TypedArray a = context.obtainStyledAttributes(attr,
                R.styleable.Chart);
        for (int i = 0; i < data.length; i++) {
            if (maxdata < data[i]) {
                maxdata = (float) data[i];
            }
        }
        int textColor = a.getColor(R.styleable.Chart_textColor,
                0XFFFFFFFF);
        int graphicColor = a.getColor(R.styleable.Chart_graphicColor,
                0XFFFFFFFF);
        float textSize = a.getDimension(R.styleable.Chart_textSize, 64);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        graphicPaint.setColor(graphicColor);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < week.length; i++) {
            canvas.drawText(week[i], i * (measureWidth / 7) + setWidth(2), measureHeight, textPaint);
        }
        canvas.drawLine(0, measureHeight - textPaint.getTextSize(), measureWidth
                , measureHeight - textPaint.getTextSize(), textPaint);

        for (int i = 0; i < data.length; i++) {
            canvas.drawRect(i * (measureWidth / 7) + textPaint.getTextSize(), measureHeight - setHeight((data[i] / maxdata) * 90)
                    , i * (measureWidth / 7) + textPaint.getTextSize() + setWidth(3), measureHeight - textPaint.getTextSize(), graphicPaint);
            canvas.drawText(data[i]+"", i * (measureWidth / 7) + setWidth(2), measureHeight - setHeight((data[i] / maxdata) * 80), textPaint);
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
}
