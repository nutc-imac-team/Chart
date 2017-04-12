package edu.imac.nutc.chart.hrv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import edu.imac.nutc.chart.R;

/**
 * Created by menglee on 2017/4/11.
 */

public class HrvView extends View {
    protected String TAG = HrvView.class.getName();
    private final String[] week = {"Wed", "Thu", "Fri", "Sat", "Sun", "Mon", "Tue"};
    private int[] hrvMax = {57, 53, 50, 45, 67, 75, 51};
    private int[] hrvMin = {30, 20, 50, 45, 40, 30, 0};
    private double max = 50.5;
    private double min = 25.9;
    private final String morning = "Morning";
    private final String evening = "Eventing";
    private Paint bottomTextPaint;
    private Paint bottomLinePaint;
    private Paint graphicPointPaint;
    private Paint graphicLinePaint;
    private Paint graphicAreaPaint;
    private Paint graphicTextPaint;
    private Paint indexPaint;
    private Paint indexTextPaint;
    private Paint indexCirclePaint;
    private Paint maxValuePaint;
    private Paint minValuePaint;
    private Paint bitmapPaint;
    private Bitmap bitmapIcon;
    private int height;
    private int width;
    private float preHeight;
    private float preWidth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HrvView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public HrvView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HrvView(Context context) {
        this(context, null);
    }

    public HrvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bottomTextPaint = new Paint();
        graphicPointPaint = new Paint();
        graphicLinePaint = new Paint();
        graphicAreaPaint = new Paint();
        bottomLinePaint = new Paint();
        graphicTextPaint = new Paint();
        indexPaint = new Paint();
        indexTextPaint = new Paint();
        indexCirclePaint = new Paint();
        maxValuePaint = new Paint();
        minValuePaint = new Paint();
        bitmapPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Hrv);
        //baweLine
        int baseLineColor = a.getColor(R.styleable.Hrv_hrvBaseLineColor, 0XFF1D3244);
        float baseLineWidth = a.getDimension(R.styleable.Hrv_hrvBaseLineColor, 10);
        //baseLineText
        int baseLineTextColor = a.getColor(R.styleable.Hrv_hrvBaseLineTextColor, 0XFFFFFFFF);
        float baseLineTextSize = a.getDimension(R.styleable.Hrv_hrvBaseLineColor, 64);
        //graphic
        int graphicPointColor = a.getColor(R.styleable.Hrv_hrvGraphicPointColor, 0XFFEB0CC5);
//        int graphicPointWidth = a.getColor(R.styleable.Hrv_hrvGraphicPointWidth, 15);
        int graphicLineColor = a.getColor(R.styleable.Hrv_hrvGraphicLineColor, 0XFFB60B99);
        int graphicLineWidth = a.getColor(R.styleable.Hrv_hrvGraphicLineWidth, 10);
        int graphicAreaColor = a.getColor(R.styleable.Hrv_hrvGraphicAreaColor, 0XA0740A62);
        int graphicTextColor = a.getColor(R.styleable.Hrv_hrvGraphicTextColor, 0XFFFFFFFF);
        float graphicTextWidth = a.getDimension(R.styleable.Hrv_hrvGraphicTextWidth, 64);
        int indexColor = a.getColor(R.styleable.Hrv_hrvIndexColor, 0XA0740A62);
        float indexWidth = a.getDimension(R.styleable.Hrv_hrvIndexWidth, 20);
        int indexTextColor = a.getColor(R.styleable.Hrv_hrvIndexTextColor, 0XFFFFFFFF);
        float indexTextSize = a.getDimension(R.styleable.Hrv_hrvIndexTextSize, 50);
        int indexCircleColor = a.getColor(R.styleable.Hrv_hrvIndexCircleColor, 0XFFB60B99);
        int maxValueColor = a.getColor(R.styleable.Hrv_hrvMaxValueColor, 0XFFFFFFFF);
        float maxTextSize = a.getDimension(R.styleable.Hrv_hrvIndexTextSize, 80);
        int minValueColor = a.getColor(R.styleable.Hrv_hrvMinValueColor, 0XFFFFFFFF);
        float minTextSize = a.getDimension(R.styleable.Hrv_hrvIndexTextSize, 60);

        a.recycle();
        //bottom line
        bottomLinePaint.setColor(baseLineColor);
        bottomLinePaint.setStrokeWidth(baseLineWidth);
        //bottom text
        bottomTextPaint.setTextSize(baseLineTextSize);
        bottomTextPaint.setColor(baseLineTextColor);
        //graphic
        graphicPointPaint.setColor(graphicPointColor);
        graphicLinePaint.setStrokeWidth(graphicLineWidth);
        graphicLinePaint.setColor(graphicLineColor);
        graphicAreaPaint.setColor(graphicAreaColor);
        graphicTextPaint.setColor(graphicTextColor);
        graphicTextPaint.setTextSize(graphicTextWidth);

        //index
        indexPaint.setColor(indexColor);
        indexPaint.setStrokeWidth(indexWidth);
        indexTextPaint.setColor(indexTextColor);
        indexTextPaint.setTextSize(indexTextSize);
        indexCirclePaint.setColor(indexCircleColor);

        //value
        maxValuePaint.setColor(maxValueColor);
        maxValuePaint.setTextSize(maxTextSize);
        minValuePaint.setColor(minValueColor);
        minValuePaint.setTextSize(minTextSize);
        bitmapPaint.setAntiAlias(false);
        bitmapPaint.setFilterBitmap(false);
        bitmapIcon= BitmapFactory.decodeResource(getResources(),R.mipmap.battery);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //baseLine
        canvas.drawLine(0, height - bottomTextPaint.getTextSize(), width, height - bottomTextPaint.getTextSize(), bottomLinePaint);

        //baseLineText
        for (int index = 0; index <= week.length; index++) {
            if (index == week.length) break;
            float dotX = (float) ((index + 1) * (width / (week.length + 1)));
            float dotTop = (float) (height - bottomTextPaint.getTextSize() * 1.5);
            float dotBot = (float) (height - bottomTextPaint.getTextSize() / 1.5);
            float textX = (float) (dotX - bottomTextPaint.getTextSize() * 0.5);

            canvas.drawLine(dotX, dotTop, dotX, dotBot, bottomLinePaint);
            canvas.drawText(week[index], textX, height, bottomTextPaint);

        }

        //graphic
        Path path = new Path();
        for (int index = 0; index < hrvMax.length; index++) {
            float nowdotX = (float) ((index + 1) * (width / (hrvMax.length + 1)));
            float nowdotY = ((100 - hrvMax[index]) * preHeight);
            if (index == 0) path.moveTo(nowdotX, nowdotY);
            else path.lineTo(nowdotX, nowdotY);
        }
        for (int index = hrvMin.length - 1; index >= 0; index--) {
            float nowdotX = (float) ((index + 1) * (width / (hrvMax.length + 1)));
            float nowdotY = ((100 - hrvMin[index]) * preHeight);
            path.lineTo(nowdotX, nowdotY);
        }
        path.close();
        canvas.drawPath(path, graphicAreaPaint);
        for (int index = 0; index < hrvMax.length; index++) {
            float nowdotX = (float) ((index + 1) * (width / (hrvMax.length + 1)));
            float nextdotX = (float) ((index + 2) * (width / (hrvMax.length + 1)));
            float nowdotY = ((100 - hrvMax[index]) * preHeight);
            float textX = (float) (nowdotX - bottomTextPaint.getTextSize() * 0.5);
            float textY = (float) (nowdotY - bottomTextPaint.getTextSize() * 0.5);

            if (index + 1 < hrvMax.length) {
                float nextdotY = ((100 - hrvMax[index + 1]) * preHeight);
                //line
                canvas.drawLine(nowdotX, nowdotY, nextdotX, nextdotY, graphicLinePaint);
            }
            //point and value
            canvas.drawCircle(nowdotX, nowdotY, (float) (preWidth * 1.3), graphicPointPaint);
            canvas.drawText(String.valueOf(hrvMax[index]), textX, textY, graphicTextPaint);
        }

        //morning and evening circle


        canvas.drawText(morning, preWidth * 82, preHeight * 20, indexTextPaint);
        canvas.drawText(evening, preWidth * 82, preHeight * 30, indexTextPaint);
        int textWidth = getTextWidth(indexTextPaint, morning);
        canvas.drawCircle(preWidth * 84 + textWidth, preHeight * 20, (float) (preWidth * 0.5), indexCirclePaint);
        canvas.drawLine(preWidth * 84 + textWidth, preHeight * 18, preWidth * 84 + textWidth, preHeight * 32, indexPaint);

        //max and min
        textWidth = getTextWidth(maxValuePaint, String.valueOf(max));
        canvas.drawText(String.valueOf(max), (width  - textWidth)/2, preHeight * 30 - maxValuePaint.getTextSize(), maxValuePaint);
        textWidth = getTextWidth(minValuePaint, String.valueOf(min));
        canvas.drawText(String.valueOf(min), (width  - textWidth)/2, preHeight * 30 , minValuePaint);
        canvas.drawBitmap(bitmapIcon,preWidth*20,preHeight*20, bitmapPaint);
    }

    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        preHeight = MeasureSpec.getSize(heightMeasureSpec) / 100;
        preWidth = MeasureSpec.getSize(widthMeasureSpec) / 100;
    }
}
