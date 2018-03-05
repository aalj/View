package com.myview.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.myview.R;

import java.util.TimerTask;


/**
 * TODO: document your custom view class.
 */
public class JumpView extends View {

    private final String TAG = JumpView.class.getSimpleName();
    //倒计时时间
    private int mTextTime = 5 * 1000;
    private float yuanjiao = 360 / (mTextTime * 1f);
    //文字的颜色
    private int mJumpTextColor = Color.RED;
    private float mTextRadio = 300;
    private float mTextSize = mTextRadio / 2;


    private TextPaint mTextPaint;

    //背景默认颜色
    private int backgroupColor = 0xAA448982;
    //边框默认颜色
    private int frameColor = 0xAA2632FF;


    //两种颜色 一个文字
    private Paint backGPaint;
    private Paint framePaint;

    //控件距四周的边距
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;


    //控件的宽高
    private int mWidth;
    private int mHeight;

    private int frameSize = 10;

    private boolean isShow = true;

    public JumpView(Context context) {
        super(context);
        init(null, 0);
    }

    public JumpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public JumpView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.JumpView, defStyle, 0);

        mTextTime = a.getInt(
                R.styleable.JumpView_jumpTime, mTextTime);
        mJumpTextColor = a.getColor(
                R.styleable.JumpView_jumpTextColor,
                mJumpTextColor);

        mTextSize = a.getDimension(
                R.styleable.JumpView_jumpTextSize,
                mTextSize);
        mTextRadio = a.getDimension(
                R.styleable.JumpView_jumpBackRadio,
                mTextRadio);

        backgroupColor = a.getColor(R.styleable.JumpView_jumpBackGroupColor, backgroupColor);
        frameColor = a.getColor(R.styleable.JumpView_jumpFrameColor, frameColor);
        a.recycle();


        initPaint();
        mTextSize = mTextSize <= mTextRadio / 2 ? mTextSize : mTextRadio / 2;
        yuanjiao = 360 / (mTextTime * 1f);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension((int) mTextRadio * 2, (int) mTextRadio * 2);

        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();


        Log.e(TAG, String.format("paddingLeft---->  %s", paddingLeft));
        Log.e(TAG, String.format("paddingTop---->  %s", paddingTop));
        Log.e(TAG, String.format("paddingRight---->  %s", paddingRight));
        Log.e(TAG, String.format("paddingBottom---->  %s", paddingBottom));


        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.e(TAG, String.format("mWidth---->  %s", mWidth));
        Log.e(TAG, String.format("mHeight---->  %s", mHeight));

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {


        super.onLayout(changed, 100, 100, 100, 100);


    }

    private void initPaint() {
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        //绘制背景需要的笔
        backGPaint = new Paint();
        //全部填充
        backGPaint.setStyle(Paint.Style.FILL);
        backGPaint.setAntiAlias(true);
        backGPaint.setColor(backgroupColor);

        //绘制边框需要的笔
        framePaint = new Paint();
        //只需要边框
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setAntiAlias(true);
        framePaint.setColor(frameColor);
        framePaint.setStrokeWidth(frameSize);


    }


    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mJumpTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        setBackgroundColor(0xffffffff);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        //转移画布的原点,用于更加方便的计算
        canvas.translate(width / 2, height / 2);
        canvas.save();
        //画背景圆
        canvas.drawCircle(0, 0, mTextRadio, backGPaint);
        // Draw the text.
        canvas.drawText(String.valueOf(mTextTime),
                0,
                0 + mTextSize / 2,
                mTextPaint);
        canvas.restore();

        int i = frameSize / 2;
        canvas.save();
        //绘制圆弧
        RectF oval = new RectF(
                -mTextRadio + i,
                -mTextRadio + i,
                mTextRadio - i,
                mTextRadio - i); // 用于定义的圆弧的形状和大小的界限

        float sweepAngle = 360 - yuanjiao * mTextTime;
        Log.e(TAG, String.format("圆弧旋转的角度   %s", sweepAngle));
        canvas.drawArc(oval, -90, sweepAngle, false, framePaint); // 根据进度画圆弧
        canvas.restore();


        if (isShow) {
            Message message = handler.obtainMessage(1);     // Message
            handler.sendMessageDelayed(message, 1);
            isShow = false;
        }
    }

    final Handler handler = new Handler() {

        public void handleMessage(Message msg) {         // handle message
            switch (msg.what) {
                case 1:
                    Log.e(TAG, String.format("开始倒计时   %s", mTextTime));
                    if (mTextTime > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1);      // send message
                        invalidate();
                    }
                    mTextTime--;
                    break;
                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public int getExampleString() {
        return mTextTime;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(int exampleString) {
        mTextTime = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mJumpTextColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mJumpTextColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }


}
