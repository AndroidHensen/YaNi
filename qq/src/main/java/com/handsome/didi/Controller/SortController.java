package com.handsome.didi.Controller;

import android.content.Context;
import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Sort;

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
public class SortController extends BaseController {

    public static SortController sortController;

    public static SortController getInstance() {
        if (sortController == null) {
            synchronized (SortController.class) {
                if (sortController == null) {
                    sortController = new SortController();
                }
            }
        }
        return sortController;
    }

    /**
     * 查询超实惠、特色好货
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Sort> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id,sort_type");
        query.findObjects(new FindListener<Sort>() {
            @Override
            public void done(List<Sort> list, BmobException e) {
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
                            query(listener);
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
