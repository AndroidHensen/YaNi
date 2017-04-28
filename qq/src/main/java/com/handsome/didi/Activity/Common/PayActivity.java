package com.handsome.didi.Activity.Common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.R;

public class PayActivity extends BaseActivity {

    private OrderController orderController;

    private TextView tv_sum_money;
    private Order order;
    private LinearLayout ly_wechat, ly_zhifubao, ly_qq;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initViews() {
        tv_sum_money = findView(R.id.tv_sum_money);
        ly_wechat = findView(R.id.ly_wechat);
        ly_zhifubao = findView(R.id.ly_zhifubao);
        ly_qq = findView(R.id.ly_qq);
    }

    @Override
    public void initListener() {
        setOnClick(ly_wechat);
        setOnClick(ly_zhifubao);
        setOnClick(ly_qq);
    }

    @Override
    public void initData() {
        setTitle("雅妮收銀台");
        setTitleCanBack();

        orderController = OrderController.getInstance();

        order = getIntent().getParcelableExtra("order");
        tv_sum_money.setText(order.sum_money + "元");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_wechat:
            case R.id.ly_zhifubao:
            case R.id.ly_qq:
                payOrder();
                break;
        }
    }

    /**
     * 支付订单
     */
    public void payOrder() {
        //待发货
        order.state = Order.STATE.STATE_SEND;
        orderController.update(order, new BaseController.OnBmobCommonListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }
}
