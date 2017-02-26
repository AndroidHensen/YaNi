package com.handsome.didi.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;

import com.umeng.analytics.MobclickAgent;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private SparseArray<View> mViews;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        processClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
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

    public <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }
}
