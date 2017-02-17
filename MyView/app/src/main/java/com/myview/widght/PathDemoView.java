package com.myview.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by junliang on 2016/11/24.
 */

public class PathDemoView extends View {

    private int wight;
    private int height;
    private Paint paint;

    public PathDemoView(Context context) {
        this(context, null);
    }

    public PathDemoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        init();

    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setStrokeWidth(5);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wight = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(wight / 2, height / 2);
        Path path = new Path();

        /*-----------------------path的基本操作----------------------------*/


        //moveTo、 setLastPoint、 lineTo 和 close
        /*
            moveTo;         --->移动到下一次起点的位置
                                    1.moveTo的移动只是点的移动。对之前的操作过的path没有影响。
            setLastPoint;   --->重置当前path中最后一个点的位置，绘制前调用等同于moveTo，
                                    2.setLastPoint同样是移动一个点但是会影响上一次的操作。
            lineTo;         --->添加上一个点到当前点的path
            close;          --->连接第一个点到最后一个点，形成闭合区域
                                    1.让最后一个点和第一个点形成闭合区域同时把最后一个点移动到起点位置
         */

//        path.lineTo(200, 200);
//        path.lineTo(200, 400);
////        path.moveTo(0,100);
//        path.setLastPoint(100, 500);
//        path.close();
//        path.lineTo(200, 0);
//        canvas.drawPath(path, paint);


        /*---------------------path绘制基本图形--------------------------*/
        /*
            圆             addCircle(一种方法实现)
            椭圆           addOval(一种方法实现)
            矩形           addRect(两种方法实现)
            圆角矩形        addRountRect (两种方法实现)
         */

        /*
            Path.Direction.CW 表示顺时针画图
            Path.Direction.CCW 表示逆时针画图
         */

//        RectF rectF = new RectF(-100,-100,100,100);
        path.addRect(-200,-200,200,200, Path.Direction.CW);

        path.setLastPoint(-300,300);
//        path.addRect(rectF, Path.Direction.CW);
//        path.setLastPoint(-200,200);
        canvas.drawPath(path,paint);




    }
}
