package com.handsome.didi.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.handsome.didi.Activity.Common.WebActivity;
import com.handsome.didi.Activity.Order.ConfirmOrderActivity;
import com.handsome.didi.Activity.Order.EvaluateActivity;
import com.handsome.didi.Activity.Order.PayActivity;
import com.handsome.didi.Activity.Common.PhotoViewActivity;
import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Activity.Home.CommentActivity;
import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Activity.Mine.AddAddressActivity;
import com.handsome.didi.Activity.Order.OrderDetailActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许英俊 2017/4/28
 */
public class ActivityController extends BaseController {

    public static volatile ActivityController activityController;

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
     * @param shopsOrder
     * @param shopsOrder
     */
    public void startOrderDetailActivityWithStoreAndOrder(Context context, ShopsOrder shopsOrder) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shopsOrder", shopsOrder);
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
     * 打开确认订单页面，一条shop数据
     *
     * @param context
     * @param shop
     */
    public void startConfirmOrderActivityWithShop(Context context, Shop shop) {
        //封装数据
        ShopsOrder shopsOrder = new ShopsOrder();
        List<Shop> shopList = new ArrayList<>();
        shopList.add(shop);
        shopsOrder.shopList = shopList;

        ArrayList<ShopsOrder> shopsOrderArrayList =  new ArrayList<>();
        shopsOrderArrayList.add(shopsOrder);

        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("shopsOrderList", shopsOrderArrayList);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /**
     * 打开确认订单页面，多条order数据
     *
     * @param context
     * @param shopsOrderList
     */
    public void startConfirmOrderActivityWithShop(Context context, ArrayList<ShopsOrder> shopsOrderList) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("shopsOrderList", shopsOrderList);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 打开支付页面，多个商品
     *
     * @param context
     * @param orderList
     */
    public void startPayActivityWithOrder(Context context, ArrayList<Order> orderList) {
        Intent intent = new Intent(context, PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("orderList", orderList);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 打开支付页面，单个商品
     *
     * @param context
     * @param order
     */
    public void startPayActivityWithOrder(Context context, Order order) {
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);

        Intent intent = new Intent(context, PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("orderList", orderList);
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

    /**
     * 打开编辑地址页面
     *
     * @param context
     * @param address
     */
    public void startEditAddressActivityWithAddress(Context context, Address address) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("address", address);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /**
     * 开启浏览器页面
     *
     * @param context
     * @param url
     */
    public void startWebActivityWithUrl(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    /**
     * 开启评价页面
     *
     * @param context
     * @param shop
     */
    public void startEvaluateActivityWithShop(Context context, Shop shop) {
        Intent intent = new Intent(context, EvaluateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", shop);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
