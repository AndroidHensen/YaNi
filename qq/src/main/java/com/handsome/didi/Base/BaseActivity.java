package com.handsome.didi.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.umeng.analytics.MobclickAgent;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        initListener();
        initData();
    }

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        processClick(v);
    }

    public void onResume() {
        super.onResume();
        //友盟統計
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        //友盟統計
        MobclickAgent.onPause(this);
    }
}
