package com.handsome.didi.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/3/4.
 */
public class GlideUtils {

    /**
     * 加载网络资源
     *
     * @param url
     * @param imageView
     */
    public static void setImageView(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
