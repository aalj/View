package com.myview.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.myview.R;


/**
 * TODO: document your custom view class.
 */
public class JumpView extends View {

    private final String TAG = JumpView.class.getSimpleName();


    private String mTextString;
    private int mJumpTextColor = Color.RED;
    private Drawable mExampleDrawable;
    private float mTextSize = 10;


    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

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

        mTextString = a.getString(
                R.styleable.JumpView_jumpString);
        mJumpTextColor = a.getColor(
                R.styleable.JumpView_jumpTextColor,
                mJumpTextColor);

        mTextSize = a.getDimension(
                R.styleable.JumpView_jumpTextSize,
                mTextSize);

        backgroupColor = a.getColor(R.styleable.JumpView_jumpBackGroupColor, backgroupColor);
        frameColor = a.getColor(R.styleable.JumpView_jumpFrameColor, frameColor);
        a.recycle();


        initPaint();

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) mTextWidth, (int) (mTextHeight+mTextSize));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) mTextWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, (int) (mTextHeight+mTextSize));
        }

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

    }


    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mJumpTextColor);
        mTextWidth = mTextPaint.measureText(mTextString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.


        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, String.format("width---->  %s", width));
        Log.e(TAG, String.format("height---->  %s", height));


        int contentWidth = width - paddingLeft - paddingRight;
        int contentHeight = height - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mTextString,
                0 / 2,
                0 / 2,
                mTextPaint);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mTextString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mTextString = exampleString;
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


    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}
