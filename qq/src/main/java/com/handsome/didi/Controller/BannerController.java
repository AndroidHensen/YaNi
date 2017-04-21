package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Banner;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class BannerController extends BaseController {

    public static BannerController bannerController;

    public BannerController(Context context) {
        super(context);
    }

    public static BannerController getInstance(Context context) {
        if (bannerController == null) {
            synchronized (BannerController.class) {
                if (bannerController == null) {
                    bannerController = new BannerController(context);
                }
            }
        }
        return bannerController;
    }


    /**
     * 查询轮播图
     *
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        BmobQuery<Banner> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(10);
        query.order("id");
        query.findObjects(new FindListener<Banner>() {
            @Override
            public void done(List<Banner> list, BmobException e) {
                if (e != null) {
                    showToast("error code:" + e.getErrorCode());
                    return;
                }
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

    public interface OnQueryListener {
        void onQuery(List<Banner> list);
    }

}
