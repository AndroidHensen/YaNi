package com.handsome.didi.Activity.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.handsome.didi.Activity.Main.MainActivity;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;


public class WelcomeActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startMain();
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mHandler.sendEmptyMessageDelayed(0, 1500);
    }

    @Override
    public void processClick(View v) {

    }

    /**
     * 开启主界面
     */
    private void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
