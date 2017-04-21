package com.handsome.didi.Controller;

import android.content.Context;

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

    public static RechargeController rechargeController;

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
     * 查询轮播图
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Recharge> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.findObjects(new FindListener<Recharge>() {
            @Override
            public void done(List<Recharge> list, BmobException e) {
                if (e != null) {
                    listener.onError("error code:" + e.getErrorCode());
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("list is empty");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }

}
