package com.handsome.didi.Controller;

import android.os.CountDownTimer;

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

    public static BannerController getInstance() {
        if (bannerController == null) {
            synchronized (BannerController.class) {
                if (bannerController == null) {
                    bannerController = new BannerController();
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
    public void query(final OnBmobListener listener) {
        BmobQuery<Banner> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id");
        query.findObjects(new FindListener<Banner>() {
            @Override
            public void done(List<Banner> list, BmobException e) {
                if (e != null) {
                    listener.onError("服务器异常，正在重连");
                    //重连机制
                    new CountDownTimer(connect_time, interval_time) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            query(listener);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("空空如也");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }


}
