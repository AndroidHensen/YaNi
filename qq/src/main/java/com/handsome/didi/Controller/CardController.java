package com.handsome.didi.Controller;

import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Card;
import com.handsome.didi.Bean.Shop;

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
public class CardController extends BaseController {

    public static volatile CardController bannerController;

    public static CardController getInstance() {
        if (bannerController == null) {
            synchronized (CardController.class) {
                if (bannerController == null) {
                    bannerController = new CardController();
                }
            }
        }
        return bannerController;
    }

    /**
     * 查询卡券
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Card> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.findObjects(new FindListener<Card>() {
            @Override
            public void done(List<Card> list, BmobException e) {
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

    /**
     * 查询指定卡券id集合中的所有卡券（我的卡券）
     *
     * @param C_OID    卡券ObjectId集合
     * @param listener
     */
    public void query(final List<String> C_OID, final OnBmobListener listener) {
        BmobQuery<Card> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereContainedIn("objectId", C_OID);
        query.findObjects(new FindListener<Card>() {
            @Override
            public void done(List<Card> list, BmobException e) {
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
                            query(C_OID, listener);
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
