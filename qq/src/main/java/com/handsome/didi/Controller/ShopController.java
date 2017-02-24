package com.handsome.didi.Controller;

import android.content.Context;
import android.util.Log;

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
public class ShopController extends CommonController {

    public ShopController(Context context) {
        super(context);
    }

    public interface OnQueryListener {
        void onQuery(List<Shop> list);
    }

    /**
     * 查询所有商品
     *
     * @param listener
     */
    public void query(final OnQueryListener listener) {
        BmobQuery<Shop> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.order("id");
        query.findObjects(new FindListener<Shop>() {
            @Override
            public void done(List<Shop> list, BmobException e) {
                if (listener != null) {
                    listener.onQuery(list);
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
    public void queryByBQL(List<String> oid, final OnQueryListener listener) {
        try {

            //拼装SQL语句
            String str = "";
            for (int i = 0; i < oid.size(); i++) {
                if (i == oid.size() - 1) {
                    str += "'" + oid.get(i) + "'";
                } else {
                    str += "'" + oid.get(i) + "',";
                }
            }
            String bql = "select * from Shop where objectId in (" + str + ")";
            //查询
            BmobQuery<Shop> query = new BmobQuery<>();
            query.doSQLQuery(bql, new SQLQueryListener<Shop>() {
                @Override
                public void done(BmobQueryResult<Shop> result, BmobException e) {
                    if (listener != null) {
                        listener.onQuery(result.getResults());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
