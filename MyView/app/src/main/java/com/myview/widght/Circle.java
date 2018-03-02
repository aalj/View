package com.myview.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.myview.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class Circle extends View {


    private long wight = 0;
    private long height = 0;


    //内圆画笔
    private Paint insidePaint = null;
    //外圆画笔
    private Paint outsidePaint = null;

    //画表盘文字的笔
    private Paint textPaint = null;

    private Paint mGradientCirclePaint = null;

    //屏幕密度
    private float density = 0.0f;
    //表盘的半径
    private float radiaus = 0;
    private float inOutSpace = 60;

    //第一行文字的颜色
    private int oneRowColor = 0xffffff;
    //第一行文字的大小
    private float oneRowTextSize = 60;

    //第二行文字的颜色
    private int twoRowColor = 0x000000;
    //第二行文字的大小
    private float twoRowTextSize = 30;
    //内圆颜色
    private int insideColor = 0;
    //外圆颜色
    private int outsideColor = 0;

    //是否是手动设置显示扫描控件
    private boolean isHandScan = false;

    //扫描是否能够使用(显示的总开关)
    private boolean isShowScan = false;

    //控件是否可以点击
    private boolean isClickable = true;

    private OnClickListener mOnClickListener;

    Matrix matrix;//矩阵
    private float degrees = 0;

    Handler mHandler = new Handler();

    private boolean isScan = true;


    /**
     * 该控件需要的参数
     * 1. 内圆的颜色（带透明度）
     * 2. 外圆的颜色（带透明度）
     * 3. 第一行文字的大小
     * 4. 第一行文字的颜色
     * 5. 第二行文字的大小
     * 6. 第二行文字的颜色
     */

    public Circle(Context context) {
        this(context, null);
    }

    public Circle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public Circle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Clock);
        radiaus = array.getDimension(R.styleable.Clock_clockRadius, 20);

        insideColor = array.getColor(R.styleable.Clock_insideColor, 0x69bf40);
        outsideColor = array.getColor(R.styleable.Clock_outsideColor, 0x69bf40);

        inOutSpace = array.getDimension(R.styleable.Clock_inOutSpace, 10);

        oneRowColor = array.getColor(R.styleable.Clock_oneTextColor, 0xff000000);
        oneRowTextSize = array.getDimension(R.styleable.Clock_oneTextTextSize, 60);

        twoRowColor = array.getColor(R.styleable.Clock_twoTextColor, 0xff000000);
        twoRowTextSize = array.getDimension(R.styleable.Clock_twoTextTextSize, 30);
        isClickable = array.getBoolean(R.styleable.Clock_clickable, true);


        initPaint();
        init(attrs, defStyle);

    }

    //子线程实现旋转效果
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (isScan) {
                //旋转
                matrix.postRotate(++degrees, 0, 0);

            }
            Circle.this.invalidate();
            mHandler.postDelayed(runnable, 10);

        }
    };


    private void initPaint() {
        matrix = new Matrix();
        density = getResources().getDisplayMetrics().density;

        insidePaint = new Paint();
        insidePaint.setAntiAlias(true);
        insidePaint.setFilterBitmap(true);
        insidePaint.setStyle(Paint.Style.FILL);
        insidePaint.setColor(insideColor);
        insidePaint.setStrokeWidth(1 * density);

        outsidePaint = new Paint();
        outsidePaint.setAntiAlias(true);
        outsidePaint.setFilterBitmap(true);
        outsidePaint.setStyle(Paint.Style.FILL);
        outsidePaint.setColor(outsideColor);
        outsidePaint.setStrokeWidth(1 * density);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setFilterBitmap(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        //着色器
        Shader mShader = new SweepGradient(0, 0, Color.TRANSPARENT,
                Color.parseColor("#AAAAAAAA"));
        mGradientCirclePaint = new Paint();
        mGradientCirclePaint.setColor(Color.WHITE);
        mGradientCirclePaint.setStyle(Paint.Style.FILL);// 实心
        mGradientCirclePaint.setAntiAlias(true);
        mGradientCirclePaint.setShader(mShader);
        mHandler.post(runnable);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wight = w;
        height = h;
    }

    /**
     * 确定控件的大小在onMeasure中进行设置和和判断
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        //屏幕宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //屏幕高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
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
        //左右间距和
        int LR = paddingLeft + paddingRight;
        //上下间距和
        int TB = paddingBottom + paddingTop;
        if (radiaus == 0) {
            radiaus = wight / 3;
        }
        int padding = LR > TB ? LR : TB;

        int width;
        int height;

        if (widthModel == MeasureSpec.EXACTLY) {//这个是确定值
            width = widthSize - LR;
        } else {
            width = (int) radiaus * 2 - LR;
        }
        if (heightModel == MeasureSpec.EXACTLY) {//确定值
            height = heightSize - TB;
        } else {
            height = (int) radiaus * 2 - TB;
        }
        int length = width > height ? height : width;
        if (length < radiaus * 2) {
            length = (int) radiaus * 2 + padding;
        }

        setMeasuredDimension(length + (int) inOutSpace * 2, length + (int) inOutSpace * 2);

    }

    private void init(AttributeSet attrs, int defStyle) {

        //控制不然字体的大小超过圆的面积
        oneRowTextSize = oneRowTextSize < radiaus / 2 ? oneRowTextSize : radiaus / 2;
        twoRowTextSize = twoRowTextSize < radiaus / 2 ? twoRowTextSize : radiaus / 2;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(wight / 2, height / 2);


        canvas.save();

        canvas.drawCircle(0, 0, radiaus + inOutSpace, outsidePaint);

        canvas.drawCircle(0, 0, radiaus, insidePaint);

        drawText(canvas);

        canvas.restore();
        //判断上是否要自动显示扫描还是通过点击以后在显示扫描
        if (isShowScan && isScan) {

            canvas.save();
            canvas.concat(matrix);
            canvas.drawCircle(0, 0, radiaus, mGradientCirclePaint);
            matrix.reset();
            canvas.restore();
        }

    }


    private void drawText(Canvas canvas) {
        textPaint.setColor(oneRowColor);
        textPaint.setTextSize(oneRowTextSize);
        canvas.save();
        //画刻度的文字
        Rect rect = new Rect();
        //这里就是显示表盘的刻度
        //得到文字的大小
        String text = "上班打卡";
        textPaint.getTextBounds(text, 0, text.length(), rect);
        //移动画布中心点对以便于绘制文字（画布的中心就是对应的文字中心）
        canvas.translate(0, -(rect.bottom - rect.top));
        canvas.drawText(text, 0, (rect.bottom - rect.top) / 2, textPaint);
        int paddingTop = rect.bottom - rect.top;
        canvas.restore();
        canvas.translate(0, 0);

        textPaint.setColor(twoRowColor);
        textPaint.setTextSize(twoRowTextSize);
        canvas.save();
        //画刻度的文字
        rect = new Rect();
        //这里就是显示表盘的刻度
        //得到文字的大小
        String format = new SimpleDateFormat("HH:mm:ss").format(new Date());
        textPaint.getTextBounds(format, 0, format.length(), rect);
        //移动画布中心点对以便于绘制文字（画布的中心就是对应的文字中心）
        canvas.translate(0, (rect.bottom - rect.top));
        canvas.drawText(format, 0, (rect.bottom - rect.top) / 2, textPaint);

        canvas.restore();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!isHandScan && isClickable) {
                    isScan = true;
                    isHandScan = false;
                    degrees = 0;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (isClickable && mOnClickListener != null) {
                    mOnClickListener.onClick(Circle.this);
                }
                isScan = false;
                isHandScan = true;
                break;
        }
        return true;
    }

    public interface OnClickListener {
        void onClick(View view);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        if (l != null) {
            mOnClickListener = l;

        }
    }

    public void isScanView(boolean isScan) {
        isHandScan = false;
        this.isScan = isScan;
        degrees = 0;
    }

    public void isNotSScanView() {
        this.isScan = false;
        isHandScan = false;
    }

    public void setShowScaView(boolean isShowScan) {
        this.isShowScan = isShowScan;
    }


    public boolean isClickable() {
        return isClickable;
    }


    public void setClickable(boolean clickable) {
        isClickable = clickable;
    }
}