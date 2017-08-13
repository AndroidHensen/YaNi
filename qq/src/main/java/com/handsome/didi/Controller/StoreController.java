package com.handsome.didi.Controller;

import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Store;

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
public class StoreController extends BaseController {

    public static volatile StoreController storeController;

    public static StoreController getInstance() {
        if (storeController == null) {
            synchronized (StoreController.class) {
                if (storeController == null) {
                    storeController = new StoreController();
                }
            }
        }
        return storeController;
    }

    /**
     * 查询商店
     */
    public void query(final String S_OID, final OnBmobListener listener) {
        BmobQuery<Store> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereEqualTo("objectId", S_OID);
        query.findObjects(new FindListener<Store>() {
            @Override
            public void done(List<Store> list, BmobException e) {
                if (e != null) {

                    if (e.getErrorCode() == 9016) {
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
                            query(S_OID, listener);
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

    /**
     * 查询指定店铺id集合中的所有店铺
     *
     * @param S_OID
     * @param listener
     */
    public void query(final List<String> S_OID, final OnBmobListener listener) {
        BmobQuery<Store> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereContainedIn("objectId", S_OID);
        query.findObjects(new FindListener<Store>() {
            @Override
            public void done(List<Store> list, BmobException e) {
                if (e != null) {

                    if (e.getErrorCode() == 9016) {
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
                            query(S_OID, listener);
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
