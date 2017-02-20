package com.handsome.didi.Controller;

import android.content.Context;

import com.handsome.didi.Bean.Banner;
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
public class RechargeController extends CommonController{

    public RechargeController(Context context) {
        super(context);
    }

    public interface OnQueryListener {void onQuery(List<Recharge> list);}

    /**
     * 查询轮播图
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        BmobQuery<Recharge> query = new BmobQuery<>();
        query.findObjects(new FindListener<Recharge>() {
            @Override
            public void done(List<Recharge> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

}
