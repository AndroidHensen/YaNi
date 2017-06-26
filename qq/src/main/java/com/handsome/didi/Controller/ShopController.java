package com.handsome.didi.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Shop;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class ShopController extends BaseController {

    public static ShopController shopController;

    public static ShopController getInstance() {
        if (shopController == null) {
            synchronized (ShopController.class) {
                if (shopController == null) {
                    shopController = new ShopController();
                }
            }
        }
        return shopController;
    }

    /**
     * 查询规定页数的所有商品
     *
     * @param listener
     */
    public void query(final int currentPage, final OnBmobListener listener) {
        final BmobQuery<Shop> query = new BmobQuery<>();
        query.order("id");
        query.setCachePolicy(mPolicy);
        query.setLimit(pageCount);
        query.setSkip(currentPage * pageCount);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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

    /**
     * 查询指定店铺id的所有商品
     *
     * @param listener
     */
    public void query(final String S_OID, final OnBmobListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id");
        query.addWhereEqualTo("S_OID", S_OID);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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
     * 查询指定商品id集合中的所有商品（订单、关注、购物车）
     *
     * @param S_OID    商品ObjectId集合
     * @param listener
     */
    public void query(final List<String> S_OID, final OnBmobListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereContainedIn("objectId", S_OID);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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
