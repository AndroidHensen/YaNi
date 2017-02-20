package com.handsome.didi.Controller;

import android.content.Context;

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
public class ShopController extends CommonController{

    public ShopController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<Shop> list);
    }

    /**
     * 查询商品
     *
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
                }
            }
        });
    }

}
