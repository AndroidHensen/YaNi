package com.handsome.didi.Base;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
