package com.handsome.didi.Activity;

import android.view.View;
import android.widget.LinearLayout;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

public class WalletActivity extends BaseActivity {

    private LinearLayout ly_yecz,ly_yxcz,ly_jfk,ly_yetx;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_wallet);
        ly_yecz = (LinearLayout) findViewById(R.id.ly_yecz);
        ly_yxcz = (LinearLayout) findViewById(R.id.ly_yxcz);
        ly_jfk = (LinearLayout) findViewById(R.id.ly_jfk);
        ly_yetx = (LinearLayout) findViewById(R.id.ly_yetx);
    }

    @Override
    public void initListener() {
        ly_yecz.setOnClickListener(this);
        ly_yxcz.setOnClickListener(this);
        ly_jfk.setOnClickListener(this);
        ly_yetx.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            default:
                ToastUtils.showToast(this,"敬请期待");
                break;
        }
    }
}
