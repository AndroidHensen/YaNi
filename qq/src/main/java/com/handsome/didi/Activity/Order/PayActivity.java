package com.handsome.didi.Activity.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.Fragment.Main.CartFragment;
import com.handsome.didi.R;
import com.handsome.didi.Utils.CalculateUtils;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends BaseActivity {

    private OrderController orderController;
    private UserController userController;

    private TextView tv_sum_money;
    private ArrayList<Order> orderList;
    private LinearLayout ly_wechat, ly_zhifubao, ly_qq;

    private double sum_money;

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
        userController = UserController.getInstance();


        orderList = getIntent().getParcelableArrayListExtra("orderList");

        List<String> S_OID = new ArrayList<>();
        for (Order order : orderList) {
            S_OID.addAll(order.S_OID);
        }
        deleteUserCart(S_OID);

        sum_money = 0;
        for (Order order : orderList) {
            Log.e("TAG", order.sum_money + "元");
            sum_money = CalculateUtils.Sum(order.sum_money, sum_money);
        }
        tv_sum_money.setText(sum_money + "元");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_wechat:
                payOrder("微信支付", orderList);
                break;
            case R.id.ly_zhifubao:
                payOrder("支付宝支付", orderList);
                break;
            case R.id.ly_qq:
                payOrder("QQ支付", orderList);
                break;
        }
    }

    /**
     * 支付订单
     */
    public void payOrder(String payWay, List<Order> orderList) {
        for (Order order : orderList) {
            //待发货
            order.state = Order.STATE.STATE_SEND;
            order.pay_way = payWay;
            orderController.update(order, new BaseController.OnBmobCommonListener() {
                @Override
                public void onSuccess(String success) {
                    showToast(success);
                    finish();
                }

                @Override
                public void onError(String error) {
                    showToast(error);
                }
            });
        }
    }

    /**
     * 删除用户购物车
     */
    private void deleteUserCart(List<String> s_oid_list) {
        userController.deleteUserCart(s_oid_list, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                //更新UI
                onChangeDataInUI(CartFragment.class.getName());
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onLoading(String loading) {

            }
        });
    }
}
