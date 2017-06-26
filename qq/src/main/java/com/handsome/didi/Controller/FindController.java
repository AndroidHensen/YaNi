package com.handsome.didi.Controller;

import android.content.Context;
import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Find;

import java.util.Collections;
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
public class FindController extends BaseController {

    public static FindController findController;

    public static FindController getInstance() {
        if (findController == null) {
            synchronized (FindController.class) {
                if (findController == null) {
                    findController = new FindController();
                }
            }
        }
        return findController;
    }

    /**
     * 查询发现
     *
     * @param listener
     */
    public void query(final int currentPage, final OnBmobListener listener) {
        BmobQuery<Find> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.setLimit(pageCount);
        query.setSkip(currentPage * pageCount);
        query.findObjects(new FindListener<Find>() {
            @Override
            public void done(List<Find> list, BmobException e) {
                if (e != null) {

                    if(e.getErrorCode() == 9016){
                        listener.onError("无网络连接，请检查您的手机网络");
                        return;
                    }

                    listener.onError("服务器异常，正在重连");
                    //重连机制
                    new CountDownTimer(connect_time, interval_time) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            query(currentPage, listener);
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
