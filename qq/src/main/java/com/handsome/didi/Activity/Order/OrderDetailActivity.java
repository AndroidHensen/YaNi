package com.handsome.didi.Activity.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Adapter.Order.OrderItemAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.CalculateUtils;
import com.handsome.didi.Utils.GlideUtils;
import com.handsome.didi.View.MyListView;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailActivity extends BaseActivity {

    private ShopController shopController;
    private ActivityController activityController;

    private Order order;

    private TextView tv_order_number, tv_order_state, tv_realname, tv_phone, tv_address,
            tv_pay_way, tv_express_type, tv_express_date, tv_bill_type,
            tv_bill_title, tv_bill_message, tv_money, tv_postage_money, tv_real_sum_money, tv_order_date,
            tv_evaluate;
    private ImageView iv_shop;
    private LinearLayout ly_order_edit;
    //需要根据不同状态显示的控件
    private TextView tv_down_bill;
    private LinearLayout ly_order_express;

    private MyListView lv_order_item;
    private OrderItemAdapter itemAdapter;
    private List<Shop> shopList;
    private ShopsOrder shopsOrder;


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
        ly_order_edit = findView(R.id.ly_order_edit);
        tv_evaluate = findView(R.id.tv_evaluate);
        lv_order_item = findView(R.id.lv_order_item);
    }

    @Override
    public void initListener() {
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
            case R.id.tv_evaluate:
                activityController.startEvaluateActivityWithShop(this, shopList.get(0));
                break;
        }
    }

    /**
     * 初始化菜单详情页面
     */
    private void initOrderDetailViews() {
        shopsOrder = getIntent().getParcelableExtra("shopsOrder");
        shopList = shopsOrder.shopList;
        order = shopsOrder.order;
        itemAdapter = new OrderItemAdapter(this, shopList);
        lv_order_item.setAdapter(itemAdapter);

        //计算商品的总价格
        double sum_money = 0;
        for (Shop shop : shopList) {
            sum_money = CalculateUtils.Sum(shop.price, sum_money + "");
        }
        tv_money.setText("￥" + sum_money);

        tv_postage_money.setText("￥" + order.postage);
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

        switch (order.state) {
            case Order.STATE.STATE_COMPLETE:
                tv_order_state.setText("已完成");
                tv_down_bill.setVisibility(View.VISIBLE);
                ly_order_express.setVisibility(View.VISIBLE);
                ly_order_edit.setVisibility(View.VISIBLE);
                tv_evaluate.setOnClickListener(this);
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
                tv_evaluate.setOnClickListener(this);
                break;
        }
    }

}
