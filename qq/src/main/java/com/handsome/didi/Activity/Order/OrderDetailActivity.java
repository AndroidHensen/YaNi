package com.handsome.didi.Activity.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.math.BigDecimal;

public class OrderDetailActivity extends BaseActivity {

    private ShopController shopController;
    private ActivityController activityController;

    private Order order;
    private Shop shop;

    private TextView tv_order_number, tv_order_state, tv_realname, tv_phone, tv_address, tv_name,
            tv_price, tv_postage, tv_sell_num, tv_pay_way, tv_express_type, tv_express_date, tv_bill_type,
            tv_bill_title, tv_bill_message, tv_money, tv_postage_money, tv_real_sum_money, tv_order_date;
    private ImageView iv_shop;
    private LinearLayout ly_shop_detail, ly_order_edit;
    //需要根据不同状态显示的控件
    private TextView tv_down_bill;
    private LinearLayout ly_order_express;


    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initViews() {
        tv_order_number = findView(R.id.tv_order_number);
        tv_order_state = findView(R.id.tv_order_state);
        tv_realname = findView(R.id.tv_realname);
        tv_phone = findView(R.id.tv_phone);
        tv_address = findView(R.id.tv_address);
        tv_name = findView(R.id.tv_name);
        tv_price = findView(R.id.tv_price);
        tv_postage = findView(R.id.tv_postage);
        tv_sell_num = findView(R.id.tv_sell_num);
        tv_pay_way = findView(R.id.tv_pay_way);
        tv_express_type = findView(R.id.tv_express_type);
        tv_express_date = findView(R.id.tv_express_date);
        tv_bill_type = findView(R.id.tv_bill_type);
        tv_bill_title = findView(R.id.tv_bill_title);
        tv_bill_message = findView(R.id.tv_bill_message);
        tv_money = findView(R.id.tv_money);
        tv_postage_money = findView(R.id.tv_postage_money);
        tv_real_sum_money = findView(R.id.tv_real_sum_money);
        tv_order_date = findView(R.id.tv_order_date);
        tv_down_bill = findView(R.id.tv_down_bill);
        iv_shop = findView(R.id.iv_shop);
        ly_order_express = findView(R.id.ly_order_express);
        ly_shop_detail = findView(R.id.ly_shop_detail);
        ly_order_edit = findView(R.id.ly_order_edit);
    }

    @Override
    public void initListener() {
        setOnClick(ly_shop_detail);
    }

    @Override
    public void initData() {
        setTitle("订单详情");
        setTitleCanBack();

        shopController = ShopController.getInstance();
        activityController = ActivityController.getInstance();

        initOrderDetailViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_shop_detail:
                activityController.startDetailActivityWithShop(this, shop);
                break;
        }
    }

    /**
     * 初始化菜单详情页面
     */
    private void initOrderDetailViews() {
        order = getIntent().getParcelableExtra("order");
        shop = getIntent().getParcelableExtra("shop");

        tv_order_number.setText(order.order_number);
        tv_realname.setText(order.realname);
        tv_phone.setText(order.phone);
        tv_address.setText(order.address);
        tv_pay_way.setText(order.pay_way);
        tv_express_type.setText(order.express_type);
        tv_express_date.setText("配送日期：" + order.express_date);
        tv_bill_type.setText(order.bill_type);
        tv_real_sum_money.setText("￥" + order.sum_money);
        tv_bill_title.setText("发票抬头：" + order.bill_title);
        tv_bill_message.setText("发票内容：" + order.bill_message);
        tv_order_date.setText(order.getCreatedAt());

        tv_name.setText(shop.name);
        tv_price.setText("￥" + shop.price);
        tv_postage.setText("快递：" + shop.postage);
        tv_sell_num.setText("月售" + shop.sell_num + "笔");
        tv_money.setText("￥" + shop.price);
        tv_postage_money.setText("￥" + shop.postage);
        GlideUtils.displayImage(this, shop.show_urls.get(0), iv_shop);

        switch (order.state) {
            case Order.STATE.STATE_COMPLETE:
                tv_order_state.setText("已完成");
                tv_down_bill.setVisibility(View.VISIBLE);
                ly_order_express.setVisibility(View.VISIBLE);
                ly_order_edit.setVisibility(View.VISIBLE);
                break;
            case Order.STATE.STATE_GET:
                tv_order_state.setText("待收货");
                tv_down_bill.setVisibility(View.VISIBLE);
                ly_order_express.setVisibility(View.VISIBLE);
                ly_order_edit.setVisibility(View.GONE);
                break;
            case Order.STATE.STATE_PAY:
                tv_down_bill.setVisibility(View.GONE);
                ly_order_express.setVisibility(View.GONE);
                ly_order_edit.setVisibility(View.GONE);
                tv_order_state.setText("待付款");
                break;
            case Order.STATE.STATE_SEND:
                tv_order_state.setText("待发货");
                tv_down_bill.setVisibility(View.VISIBLE);
                ly_order_express.setVisibility(View.GONE);
                ly_order_edit.setVisibility(View.GONE);
                break;
            case Order.STATE.STATE_WAIT:
                tv_order_state.setText("待评价");
                tv_down_bill.setVisibility(View.VISIBLE);
                ly_order_express.setVisibility(View.VISIBLE);
                ly_order_edit.setVisibility(View.VISIBLE);
                break;
        }
    }

}
