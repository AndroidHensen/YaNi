package com.handsome.didi.Controller;

import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Category;

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
public class CategoryController extends BaseController {

    public static volatile CategoryController categoryController;

    public static CategoryController getInstance() {
        if (categoryController == null) {
            synchronized (CategoryController.class) {
                if (categoryController == null) {
                    categoryController = new CategoryController();
                }
            }
        }
        return categoryController;
    }

    /**
     * 查询分类
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Category> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id");
        query.findObjects(new FindListener<Category>() {
            @Override
            public void done(List<Category> list, BmobException e) {
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
