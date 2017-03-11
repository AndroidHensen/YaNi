package com.handsome.didi.Base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handsome.didi.R;
import com.iflytek.thridparty.E;
import com.umeng.analytics.MobclickAgent;


public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    private SparseArray<View> mViews;
    private Intent intent;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        if (v.getId() == R.id.iv_finish) {
            finish();
        }
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

    public <E extends View> void setOnClick(E view) {
        view.setOnClickListener(this);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class cls) {
        intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void requestPermissions(String permissions) {
        if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions)) {

            }
            ActivityCompat.requestPermissions(this, new String[]{permissions}, 0);
        }
    }

    public void setTitle(String title) {
        TextView tv_title = findView(R.id.tv_title);
        tv_title.setText(title);
    }


    public void setTitleCanBack() {
        ImageView iv_finish = findView(R.id.iv_finish);
        iv_finish.setVisibility(View.VISIBLE);
        setOnClick(iv_finish);
    }
}
