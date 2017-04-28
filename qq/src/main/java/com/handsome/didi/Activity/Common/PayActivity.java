package com.handsome.didi.Activity.Common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.R;

public class PayActivity extends BaseActivity {

    private TextView tv_sum_money;
    private Order order;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initViews() {
        tv_sum_money = findView(R.id.tv_sum_money);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("雅妮收銀台");
        setTitleCanBack();

        order = getIntent().getParcelableExtra("order");
        tv_sum_money.setText(order.sum_money + "元");
    }

    @Override
    public void processClick(View v) {

    }
}
