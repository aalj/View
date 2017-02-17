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

public class MyView extends View {

    private Paint paint = new Paint();



    public MyView(Context context) {
        super(context);
        initPaint(paint);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(paint);
    }

    private void initPaint(Paint paint){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);//样式为填充
        paint.setStrokeWidth(10);

    }


    /**
     *
     * @param context
     * @param attrs  在xml中可以引用的布局
     * @param defStyleAttr 设置Style 默认是activity的布局或者是appllication的布局
     */
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawColor(Color.GREEN);
        //绘制一条直线，
        canvas.drawLine(0,0,500,500,paint);
        //绘制一组直线
        canvas.drawLines(new float[]{

                50,50,400,50,
                400,50,400,600,
                400,600,50,600,
                60,600,50,50
        },8,8,paint);
        paint.setStyle(Paint.Style.STROKE);
        //绘制一个圆
        /*
         * 参数一 二、圆心的位置
         * 参数三、 圆的半径
         *
         */
        canvas.drawCircle(500,500,100,paint);

        canvas.drawRect(100,100,600,400,paint);

        RectF rectF = new RectF(100,300,400,600);
        canvas.drawRoundRect(rectF,20,20,paint);



    }
}
