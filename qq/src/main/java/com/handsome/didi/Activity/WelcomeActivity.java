package com.handsome.didi.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;
import com.handsome.didi.Utils.PermissionUtils;
import com.handsome.didi.Utils.PrefUtils;
import com.thinkland.sdk.android.JuheSDKInitializer;


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
