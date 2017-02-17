package com.myview.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liang on 2016/11/6.
 */

public class OperatingCanvas extends View {

    private Paint mPaint;
    private Paint textPaint;
    private int mWight;
    private int mHeight;

    public OperatingCanvas(Context context) {
        super(context);
        init();

    }


    public OperatingCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OperatingCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xff666666);
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStrokeWidth(5);


        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(0xffff0000);
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWight = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWight / 2, mHeight / 2);
        canvas.save();
        //=======================体验canvas的位移（translate）
//        canvas.drawCircle(100,100,50,mPaint);
//        canvas.translate(100,100);
//
//        mPaint.setColor(0xff888888);
//        canvas.drawCircle(100,100,50,mPaint);
//        for(int i = 0 ; i < 10 ; i++){
//            canvas.translate(100,100);
//            canvas.drawCircle(100,100,50,mPaint);
//        }

        //===========================体验canvas的缩放（scale）
        //canvas移动到页面中心，

//        int r= 200;
//        RectF rectF = new RectF(-r,-r,r,r);
//        canvas.drawRoundRect(rectF,0,0,mPaint);

        //如果使用两个参数的缩放方法，则是没有指定缩放点，坐标原定为缩放点。
//        canvas.scale(0.5f,0.5f);
        //使用三个参数的所方法方法，则是指定以设置额这个点作为缩放点。
//        canvas.scale(0.5f,5f,r,r);
//        canvas.drawRoundRect(rectF,0,0,mPaint);
//        for (int i = 0;i<20 ; i++){
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRoundRect(rectF,0,0,mPaint);
//        }




        //canvas 的旋转（画一个表盘）
//        mPaint.setStrokeWidth(3);
//        canvas.drawPoint(0, 0, mPaint);
//        mPaint.setStrokeWidth(1);
        int r = 200;
        int r2 = 220;
        canvas.drawCircle(0, 0, r, mPaint);
        canvas.drawCircle(0, 0, r2, mPaint);


        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.save();
                //获取文字的大小使用Rect
                Rect rect = new Rect();
                int number = i == 0?12:(i/5);
                textPaint.getTextBounds(number+"",0,(number+"").length(),rect);
                canvas.translate(0,-r+20+((rect.bottom-rect.top)/2));
                canvas.rotate(-6*i);
                canvas.drawText(number+"",0,(rect.bottom-rect.top)/2,textPaint);
                canvas.restore();
                canvas.drawLine(0, -r + 10, 0, -r2, mPaint);
            } else {
                canvas.drawLine(0, -r2, 0, -r, mPaint);
            }


            canvas.rotate(6);
        }

        canvas.restore();

    }
}
