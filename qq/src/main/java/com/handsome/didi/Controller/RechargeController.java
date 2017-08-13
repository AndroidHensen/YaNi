package com.handsome.didi.Controller;

import android.content.Context;
import android.os.CountDownTimer;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Recharge;

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
public class RechargeController extends BaseController {

    public static volatile RechargeController rechargeController;

    public static RechargeController getInstance() {
        if (rechargeController == null) {
            synchronized (RechargeController.class) {
                if (rechargeController == null) {
                    rechargeController = new RechargeController();
                }
            }
        }
        return rechargeController;
    }

    /**
     * 查询充值
     *
     * @param listener
     */
    public void query(final int type, final OnBmobListener listener) {
        BmobQuery<Recharge> query = new BmobQuery<>();
        query.addWhereEqualTo("type", type);
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id");
        query.findObjects(new FindListener<Recharge>() {
            @Override
            public void done(List<Recharge> list, BmobException e) {
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
                            query(type, listener);
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
