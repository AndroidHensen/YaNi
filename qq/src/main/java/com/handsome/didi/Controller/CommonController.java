package com.handsome.didi.Controller;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.handsome.didi.R;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/16.
 */
public class CommonController {

    /**
     * 设置标题栏
     * @param activity
     * @param title
     */
    public void setTitle(Activity activity, String title) {
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }
}
