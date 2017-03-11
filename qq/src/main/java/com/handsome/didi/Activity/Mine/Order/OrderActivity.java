package com.handsome.didi.Activity.Mine.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class OrderActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("订单中心");
        setTitleCanBack();
    }

    @Override
    public void processClick(View v) {

    }
}
