package com.handsome.didi.Activity.Order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class EvaluateActivity extends BaseActivity {

    private RelativeLayout ly_add_img;

    @Override
    public int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initViews() {
        ly_add_img = findView(R.id.ly_add_img);
    }

    @Override
    public void initListener() {
        setOnClick(ly_add_img);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.ly_add_img:
                
                break;
        }
    }
}
