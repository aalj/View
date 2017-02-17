package com.myview.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.myview.R;
import com.socks.library.KLog;
import com.socks.library.KLogUtil;

import java.util.Calendar;

/**
 * 画一个真实的钟
 */
public class Clock extends View {

    private long wight = 0;
    private long height = 0;
    private int screemWidth ;
    private int screemHeight ;

    //画的边框的笔
    private Paint clockPaint = null;
    //画表盘文字的笔
    private Paint textPaint = null;
    //话表指针的笔
    private Paint pointerPaint = null;
    private Paint neiPaint = null;
    //屏幕密度
    private float density = 0.0f;
    //表盘的半径
    private float radiaus = 0;
    private float hoursPointer = 0;
    private float minutePointer = 0;
    private float secondPointer = 0;

    public Clock(Context context) {
        this(context,null);
        initPaint();
        init(null, 0);
    }

    public Clock(Context context, AttributeSet attrs) {
        this(context, attrs,0);

        initPaint();
        init(attrs, 0);
    }

    public Clock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Clock);
        radiaus = array.getInteger(R.styleable.Clock_clockRadius,200);
        KLog.e(radiaus);
        initPaint();
        init(attrs, defStyle);
    }

    private void initPaint() {
        density = getResources().getDisplayMetrics().density;

        clockPaint = new Paint();
        clockPaint.setAntiAlias(true);
        clockPaint.setFilterBitmap(true);
        clockPaint.setStyle(Paint.Style.STROKE);
        clockPaint.setColor(Color.BLACK);
        clockPaint.setStrokeWidth(1 * density);

        neiPaint = new Paint();
        neiPaint.setAntiAlias(true);
        neiPaint.setFilterBitmap(true);
        neiPaint.setStyle(Paint.Style.FILL);
        neiPaint.setColor(Color.BLACK);
        neiPaint.setStrokeWidth(1 * density);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(0xff000000);
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        pointerPaint = new Paint();
        pointerPaint.setAntiAlias(true);
        pointerPaint.setFilterBitmap(true);
        pointerPaint.setStyle(Paint.Style.FILL);
        pointerPaint.setStrokeWidth(5);
        pointerPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        KLog.e("onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        wight = w;
        height = h;
    }

    /**
     * 确定控件的大小在onMeasure中进行设置和和判断
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        KLog.e("onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthModel=MeasureSpec.getMode(widthMeasureSpec);
        int heightModel=MeasureSpec.getMode(heightMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        /**
         * 确定考控件大小。
         * 1、 需要的到相关的控件设置大小的类型。
         * 2、根据相应的大小类型得到相关的宽高
         * 3、得到相应的padding
         * 4、 根据属性设置的半径设置控件的最终大小（需要判断最大的宽高然后社会成正方向，然后让控件显示在正中心）
         *      1、如果控件的大小大于表盘的半径，则使用控件设置的大小
         *      2、如果设置的控件大小小于表盘的大小，则使用控件的半径设置控件的大小
         *      3、
         */

        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int LR =paddingLeft+paddingRight;
        int TB = paddingBottom+paddingTop;
        if(radiaus==0){
            radiaus=wight/3;
        }
        int padding = LR>TB?LR:TB;

        int width;
        int height;

        if(widthModel==MeasureSpec.EXACTLY){
            width=widthSize+LR;
        }else{
            width=(int)radiaus*2+LR;
        }
        if(heightModel==MeasureSpec.EXACTLY){
            height=heightSize+TB;
        }else{
            height=(int)radiaus*2+TB;
        }
        int length = width>height?width:height;
        if(length<radiaus*2){
            length = (int)radiaus*2+padding;
        }

        setMeasuredDimension(length,length);
    }

    private void init(AttributeSet attrs, int defStyle) {


        Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        screemWidth=display.getWidth();
        screemHeight=display.getHeight();


    
        secondPointer = radiaus-10*density;
        minutePointer = secondPointer*2/3;
        hoursPointer = secondPointer/3;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(wight / 2, height / 2);
        //画表盘的
        canvas.drawCircle(0, 0, radiaus, clockPaint);

        canvas.drawCircle(0,0,10f,neiPaint);
        //画刻度
        //画数字
        drawKeDu(canvas);
        //画指针
        drawPointer(canvas);
        canvas.restore();
        postInvalidateDelayed(100);
    }
    
    private void drawPointer(Canvas canvas) {
        Calendar mCalendar=Calendar.getInstance();
        float tempHour=mCalendar.get(Calendar.HOUR);
        float tempMinute=mCalendar.get(Calendar.MINUTE);
        float tempSecond=mCalendar.get(Calendar.SECOND);
        
        float hourRotate =  (tempHour/12)*360;
        float minRotate = (tempMinute/60)*360;
        float secondRotate = (tempSecond/60)*360;

        canvas.save();
        canvas.rotate(hourRotate);
        canvas.drawLine(0,0,0,-hoursPointer,pointerPaint);
        canvas.restore();
    
        canvas.save();
        canvas.rotate(minRotate);
        canvas.drawLine(0,0,0,-minutePointer,pointerPaint);
        canvas.restore();
    
        canvas.save();
        canvas.rotate(secondRotate);
        canvas.drawLine(0,0,0,-secondPointer,pointerPaint);
        canvas.restore();
        
    }
    
    private void drawKeDu(Canvas canvas) {
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                canvas.save();
                //画刻度的文字
                Rect rect = new Rect();
                //这里就是显示表盘的刻度
                int number = i == 0 ? 12 : (i / 5);
                //得到文字的大小
                textPaint.getTextBounds(number + "", 0, (number + "").length(), rect);
                //移动画布中心点对以便于绘制文字（画布的中心就是对应的文字中心）
                canvas.translate(0, -radiaus + 40);
                canvas.rotate(-6 * i);
                canvas.drawText(number + "", 0, (rect.bottom - rect.top) / 2, textPaint);

                canvas.restore();
                //画长线
                canvas.drawLine(0, -radiaus + 20, 0, -radiaus, clockPaint);
            } else {
                //画短线
                canvas.drawLine(0, -radiaus + 10, 0, -radiaus, clockPaint);
            }
            canvas.rotate(6);
        }
    }
}
