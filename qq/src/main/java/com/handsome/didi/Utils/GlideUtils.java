package com.handsome.didi.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

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
        Glide.with(context).load(url).thumbnail(0.1f).skipMemoryCache(true).into(imageView);
    }

}
