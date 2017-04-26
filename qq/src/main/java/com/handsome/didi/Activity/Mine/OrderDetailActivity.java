package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;

public class OrderDetailActivity extends BaseActivity {

    private Order order;
    private Shop shop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("订单详情");
        setTitleCanBack();

        order = getIntent().getParcelableExtra("order");
        shop = getIntent().getParcelableExtra("shop");

        Log.e("ss",order.toString()+shop.toString());
    }

    @Override
    public void processClick(View v) {

    }

}
