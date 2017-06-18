package com.handsome.didi.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.handsome.didi.Activity.Common.ConfirmOrderActivity;
import com.handsome.didi.Activity.Common.PayActivity;
import com.handsome.didi.Activity.Common.PhotoViewActivity;
import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Activity.Home.CommentActivity;
import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Activity.Mine.OrderDetailActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许英俊 2017/4/28
 */
public class ActivityController extends BaseController {

    public static ActivityController activityController;

    public static ActivityController getInstance() {
        if (activityController == null) {
            synchronized (ActivityController.class) {
                if (activityController == null) {
                    activityController = new ActivityController();
                }
            }
        }
        return activityController;
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


    /**
     * 开启商店详情页面
     *
     * @param context
     * @param S_OID
     */
    public void startStoreActivityWithStoreId(Context context, String S_OID) {
        Intent intent = new Intent(context, StoreActivity.class);
        intent.putExtra("S_OID", S_OID);
        context.startActivity(intent);
    }


    /**
     * 开启订单详情页面
     *
     * @param context
     * @param shop
     * @param order
     */
    public void startOrderDetailActivityWithStoreAndOrder(Context context, Shop shop, Order order) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", shop);
        bundle.putParcelable("order", order);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 打开全部评论页面
     *
     * @param context
     * @param S_OID
     */
    public void startCommentActivityWithShopId(Context context, String S_OID) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("OID", S_OID);
        context.startActivity(intent);
    }


    /**
     * 打开确认订单页面
     *
     * @param context
     * @param shop
     */
    public void startConfirmOrderActivityWithShop(Context context, Shop shop) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", shop);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 打开支付页面
     *
     * @param context
     * @param order
     */
    public void startPayActivityWithOrder(Context context, Order order) {
        Intent intent = new Intent(context, PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("order", order);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 打开图片预览页面
     *
     * @param context
     * @param comment
     */
    public void startPhotoViewActivityWithImage(Context context, Comment comment) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("comment", comment);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
