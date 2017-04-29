package com.handsome.didi.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author 许英俊 2017/4/29
 */
public class MyScrollView extends ScrollView {


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t + getHeight() >= computeVerticalScrollRange()) {
            if (scrollBottomListener != null) {
                //ScrollView滑动到底部了
                scrollBottomListener.scrollBottom();
            }
        }
    }

    public interface onScrollBottomListener {
        void scrollBottom();
    }

    private onScrollBottomListener scrollBottomListener;

    public void setOnScrollBottomListener(onScrollBottomListener scrollBottomListener) {
        this.scrollBottomListener = scrollBottomListener;
    }
}
