package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class AddressActivity extends BaseActivity {

    private TextView tv_add_address;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initViews() {
        tv_add_address = findView(R.id.tv_add_address);
    }

    @Override
    public void initListener() {
        setOnClick(tv_add_address);
    }

    @Override
    public void initData() {
        setTitle("管理地址");
        setTitleCanBack();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_address:
                startActivity(AddAddressActivity.class);
                break;
        }
    }
}
