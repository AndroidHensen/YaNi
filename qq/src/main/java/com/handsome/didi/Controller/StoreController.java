package com.handsome.didi.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Activity.Home.CommentActivity;
import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Activity.Mine.OrderDetailActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
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

    public static StoreController storeController;

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
    public void query(String S_OID, final OnBmobListener listener) {
        BmobQuery<Store> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereEqualTo("objectId", S_OID);
        query.findObjects(new FindListener<Store>() {
            @Override
            public void done(List<Store> list, BmobException e) {
                if (e != null) {
                    listener.onError("Server Error");
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
     * 查询指定店铺id集合中的所有店铺
     *
     * @param S_OID
     * @param listener
     */
    public void query(List<String> S_OID, final OnBmobListener listener) {
        BmobQuery<Store> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.addWhereContainedIn("objectId", S_OID);
        query.findObjects(new FindListener<Store>() {
            @Override
            public void done(List<Store> list, BmobException e) {
                if (e != null) {
                    listener.onError("Server Error");
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
