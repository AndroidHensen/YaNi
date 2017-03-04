package com.handsome.didi.Base;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.handsome.didi.R;

import cn.bmob.v3.BmobQuery;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/16.
 */
public class BaseController {

    public Context mContext;
    public BmobQuery.CachePolicy mPolicy;
    public int pageCount = 10;

    public BaseController(Context context) {
        this.mContext = context;
        this.mPolicy = BmobQuery.CachePolicy.CACHE_ELSE_NETWORK;
    }

    /**
     * 设置标题栏
     *
     * @param activity
     * @param title
     */
    public void setTitle(Activity activity, String title) {
        TextView tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }



}
