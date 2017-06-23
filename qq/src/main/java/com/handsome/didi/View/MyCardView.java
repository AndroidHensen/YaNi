package com.handsome.didi.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/8/29.
 */
public class MyCardView extends LinearLayout {
    //圆的半径
    private int radius = 8;
    //圆之间的间距
    private int gap = 8;
    private Paint mPaint;

    public MyCardView(Context context) {
        super(context);
        init();
    }

    public MyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#F0F0F0"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆的个数
        int roundNum = getWidth() / (radius * 2 + gap * 2);
        for (int i = 1; i <= roundNum; i++) {
            canvas.drawCircle((gap + radius) * (2 * i - 1), 0, radius, mPaint);
            canvas.drawCircle((gap + radius) * (2 * i - 1), getHeight(), radius, mPaint);
        }
    }
}
