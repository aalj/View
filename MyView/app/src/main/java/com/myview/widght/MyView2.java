package com.myview.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liang on 2016/10/10.
 *
 * 对于绘制View
 * 1. 初始化画笔
 *      1. 设置画笔的颜色
 *      2. 设置画笔的模式
 *      3. 设置画笔的粗细
 *
 */

public class MyView2 extends View {

    private Paint paint = new Paint();



    public MyView2(Context context) {
        super(context);
        initPaint(paint);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(paint);
    }

    private void initPaint(Paint paint){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);//样式为填充
        paint.setStrokeWidth(10);

    }


    /**
     *
     * @param context
     * @param attrs  在xml中可以引用的布局
     * @param defStyleAttr 设置Style 默认是activity的布局或者是appllication的布局
     */
    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(paint);
    }

    //测量view的大小，但是如果要更改视图的大小需要调用的是setMeasuredDimension(width,height);

    /**
     * 参数并不是宽高，这两个值是有View各个方向上的距离和测量模式合成的有个值
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //通过MeasureSpec.getSize可以的到View的宽高，
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //得到的是测量模式
        MeasureSpec.getMode(widthMeasureSpec);

    }


    //确定View的大小

    /**
     * 在View的大小发生变化的时候调用
     * @param w 最终的宽
     * @param h 最终的高
     * @param oldw 改变前的宽
     * @param oldh 改变前的高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }

    //确定子View布局
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //实际绘制View的方法
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        RectF rectF = new RectF(50,0,650,300);
//        paint.setColor(Color.LTGRAY);
//        canvas.drawRect(rectF,paint);
//
//        paint.setColor(Color.RED);
//        canvas.drawRoundRect(rectF,300,150,paint);


//        RectF rectF1 = new RectF(50,400,650,700);
//        paint.setColor(Color.LTGRAY);
//        canvas.drawRect(rectF1,paint);
//        paint.setColor(Color.RED);
//        canvas.drawArc(rectF1,0,90,true,paint);


        RectF rectF2 = new RectF(50,100,650,700);
        paint.setColor(Color.LTGRAY);
//        canvas.drawRect(rectF2,paint);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF2,0,30,true,paint);

    }
}
