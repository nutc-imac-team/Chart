package edu.imac.nutc.chart.sleep;

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
 * Created by user on 2017/3/29.
 */

public class SleepWeekendView extends View {
    String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    int parentWidth, parentHeight;
    private Paint textPaint,graphicPaint;
    public int[] chartData = {100, 200, 300, 250, 240, 350, 100};

    public SleepWeekendView(Context context) {
        super(context);
    }

    public SleepWeekendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        week= SetWeek.getWeek();
        textPaint = new Paint();
        graphicPaint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SleepClassesView);
        int chartPaintTextColor = typedArray.getColor(R.styleable.SleepClassesView_textColor, 0xff003A61);
        float chartPaintTextSize = typedArray.getDimension(R.styleable.SleepClassesView_textSize, 50);
        textPaint.setColor(chartPaintTextColor);
        textPaint.setTextSize(chartPaintTextSize);
        int dataPaintTextColor = typedArray.getColor(R.styleable.SleepClassesView_textColor, 0xff9B4FE9);
        graphicPaint.setColor(dataPaintTextColor);
        typedArray.recycle();
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

        textPaint.setColor(0xff003A61);
        textPaint.setStrokeWidth(3);
        int parentXPart = parentWidth / 8;
        int marginText = parentXPart / 5;
        textPaint.setTextSize(50);

        //星期
        canvas.drawLine(0, parentHeight - textPaint.getTextSize(), parentWidth, parentHeight - textPaint.getTextSize(), textPaint);//橫

        for (int i = 1; i <= week.length; i++) {
            canvas.drawLine(parentXPart * i, parentHeight - textPaint.getTextSize() + 10, parentXPart * i, parentHeight - textPaint.getTextSize() - 5, textPaint);
            canvas.drawText(week[i - 1], parentXPart * i - marginText, parentHeight, textPaint);
        }

        Path dataPath = new Path();
        //資料
        for (int i = 1; i <= chartData.length; i++) {
            if (i == 1) {
                dataPath.moveTo(parentXPart * i, parentHeight - graphicPaint.getTextSize());
                dataPath.lineTo(parentXPart * i, chartData[i - 1]);
            } else if (i + 1 > chartData.length) {
                dataPath.lineTo(parentXPart * i, chartData[i - 1]);
                dataPath.lineTo(parentXPart * i, parentHeight - graphicPaint.getTextSize());
                dataPath.close();
            } else {
                dataPath.lineTo(parentXPart * (i), chartData[i - 1]);
            }
        }

        canvas.drawPath(dataPath, graphicPaint);

    }
}
