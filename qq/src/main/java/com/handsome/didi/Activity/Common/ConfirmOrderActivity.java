package com.handsome.didi.Activity.Common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class ConfirmOrderActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("确认订单");
        setTitleCanBack();
    }

    @Override
    public void processClick(View v) {

    }
}
