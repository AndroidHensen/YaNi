package com.handsome.didi.Controller;

import android.os.CountDownTimer;
import android.util.Log;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.User;

import java.util.List;
import java.util.UUID;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class OrderController extends BaseController {

    public static volatile OrderController orderController;

    public static OrderController getInstance() {
        if (orderController == null) {
            synchronized (OrderController.class) {
                if (orderController == null) {
                    orderController = new OrderController();
                }
            }
        }
        return orderController;
    }

    /**
     * 查询订单
     */
    public void query(final String U_OID, final int state, final OnBmobListener listener) {
        BmobQuery<Order> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("-createdAt");
        query.addWhereEqualTo("U_OID", U_OID);
        //查询全部的情况
        if (state != Order.STATE.STATE_ALL) {
            query.addWhereEqualTo("state", state);
        }
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
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
                            query(U_OID, state, listener);
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
     * 添加订单-待付款
     */
    public void insert(Order order, final OnBmobCommonListener listener) {
        order.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    listener.onSuccess("添加订单成功");
                } else {
                    listener.onError("服务器异常，添加订单失败");
                }
            }
        });
    }

    /**
     * 更新订单
     */
    public void update(Order order, final OnBmobCommonListener listener) {
        order.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    listener.onSuccess("更新订单成功");
                } else {
                    listener.onError("服务器异常，更新订单失败");
                }
            }
        });
    }


    /**
     * 生成11位唯一订单号
     *
     * @return
     */
    public String getOrderNumber() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%010d", hashCodeV);
    }
}
