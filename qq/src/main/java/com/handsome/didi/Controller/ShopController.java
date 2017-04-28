package com.handsome.didi.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    public void query(int currentPage, final OnBmobListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.order("id");
        query.setCachePolicy(mPolicy);
        query.setLimit(pageCount);
        query.setSkip(currentPage * pageCount);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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

    /**
     * 查询指定店铺id的所有商品
     *
     * @param listener
     */
    public void query(String S_OID, final OnBmobListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.order("id");
        query.addWhereEqualTo("S_OID", S_OID);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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

    /**
     * 查询指定集合商品id中的所有商品
     *
     * @param listener
     */
    public void query(List<String> S_OID, final OnBmobListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereContainedIn("objectId", S_OID);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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

    /**
     * 查询指定商品（关注、购物车）
     *
     * @param oid      商品ObjectId集合
     * @param listener
     */
    public void queryCartOrLove(List<String> oid, final OnBmobListener listener) {
        //查询
        BmobQuery<Shop> query = new BmobQuery<>();
        query.addWhereContainedIn("objectId", oid);
        query.setLimit(limit_page);
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
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

    /**
     * 开启商品详情
     *
     * @param context
     * @param shop
     */
    public void startDetailActivityWithShop(Context context, Shop shop) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", shop);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
