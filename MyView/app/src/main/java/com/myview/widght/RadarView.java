package com.myview.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by junliang on 2016/12/6.
 */

public class RadarView extends View {

    /**
     * 主要画一个真六边形的雷达图
     * 1、确定雷达的环数
     * 2、确定雷达的角度
     * 3、计算每边的顶点坐标
     * 4、
     *
     */
    //雷达的边数
    int count = 6;
    //雷达每一个区块的角度
    float anglen = (float) (Math.PI*2/6);


    private int weight;
    private int height;




    public RadarView(Context context) {
        super(context,null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        weight = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //坐标移动到中心点
        canvas.translate(weight/2,height/2);


    }
}
