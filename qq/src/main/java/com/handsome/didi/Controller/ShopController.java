package com.handsome.didi.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
    public void query(int currentPage, final OnQueryListener listener) {
        try {
            BmobQuery<Shop> query = new BmobQuery<>();
            query.order("id");
            query.setCachePolicy(mPolicy);
            query.setLimit(pageCount);
            query.setSkip(currentPage * pageCount);
            query.findObjects(new FindListener<Shop>() {
                @Override
                public void done(List<Shop> list, BmobException e) {
                    if (e != null) {
                        showToast("error code:"+e.getErrorCode());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有商品
     *
     * @param listener
     */
    public void query(String S_OID, final OnQueryListener listener) {
        try {
            BmobQuery<Shop> query = new BmobQuery<>();
            query.setCachePolicy(mPolicy);
            query.order("id");
            query.addWhereEqualTo("S_OID", S_OID);
            query.findObjects(new FindListener<Shop>() {
                @Override
                public void done(List<Shop> list, BmobException e) {
                    if (e != null) {
                        showToast("error code:"+e.getErrorCode());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(list);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询指定商品（关注、购物车）
     *
     * @param oid      商品ObjectId集合
     * @param listener
     */
    public void queryCartOrLove(List<String> oid, final OnQueryListener listener) {
        try {
            //拼装SQL语句
            String str = "";
            if (oid.isEmpty()) {
                str = "'empty'";
            } else {
                for (int i = 0; i < oid.size(); i++) {
                    if (i == oid.size() - 1) {
                        str += "'" + oid.get(i) + "'";
                    } else {
                        str += "'" + oid.get(i) + "',";
                    }
                }
            }
            String bql = "select * from Shop where objectId in (" + str + ")";
            //查询
            BmobQuery<Shop> query = new BmobQuery<>();
            query.doSQLQuery(bql, new SQLQueryListener<Shop>() {
                @Override
                public void done(BmobQueryResult<Shop> result, BmobException e) {
                    if (e != null) {
                        showToast("error code:"+e.getErrorCode());
                        return;
                    }
                    if (listener != null) {
                        listener.onQuery(result.getResults());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 开启商品详情
     *
     * @param activity
     * @param shop
     */
    public void startDetailActivityWithShop(Activity activity, Shop shop) {
        Intent intent = new Intent(activity, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", shop);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
}
