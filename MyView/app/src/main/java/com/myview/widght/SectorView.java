package com.myview.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.myview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liang on 2016/11/2.
 */

public class SectorView extends View {
    //画一个扇形图
    //实现步骤，先画一个圆
    //定义一组模拟数据
    //定义一组颜色

    Paint paint = new Paint();
    Context mContext;
    List<String > sectorcColor = new ArrayList<>();
    List<Float> sectorcData = new ArrayList<>();
    String TAG = SectorView.class.getSimpleName();


    int mWidth =0;
    int mHeight = 0;


    public SectorView(Context context) {
        super(context);
        initPaint(context);
    }



    public SectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public SectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth =w;
        mHeight=h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPaint(Context context) {
        similation();
        mContext = context;
        paint.setColor(ContextCompat.getColor(mContext,R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);

    }
    
    public void similation(){
        // TODO: 2016/11/2 添加模拟数据
        sectorcData.add(1f);
        sectorcData.add(2f);
        sectorcData.add(3f);
        sectorcData.add(4f);

        sectorcColor.add("#121011");
        sectorcColor.add("#FF4081");
        sectorcColor.add("#303F9F");
        sectorcColor.add("#3F51B5");

    }
    

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int r = 150;
        canvas.translate(mWidth / 2, mHeight / 2);


        //1. 扇形图的外形边框
        canvas.drawCircle(0, 0,r,paint);
        //2. 对每一个扇形进行绘制
            //1. 根据数值计算每一块所占的比例
            //2. 然后绘制每一块的颜色
        int countData = 0;
        for(Float i: sectorcData){
            countData+=i;
        }
        Log.e(TAG, "draw: "+countData );
        paint.setStyle(Paint.Style.FILL);

        RectF rectF  = new RectF(-r,-r,r,r);
        int countAngle = 0;
        for(int i = 0; i < sectorcData.size(); i++){
            //角度
            Log.e(TAG, "draw: sectorcData.get(i) "+sectorcData.get(i) );
            int angle = (int)((sectorcData.get(i)/countData)*360);
            Log.e(TAG, "draw: angle"+angle );
            //颜色
            paint.setColor(Color.parseColor(sectorcColor.get(i)));

            canvas.drawArc(rectF,countAngle,angle,true,paint);
            countAngle+=angle;
            Log.e(TAG, "draw: countAngle--a"+countAngle );
        }

    }


}
