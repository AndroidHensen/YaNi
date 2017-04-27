package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Mine.AddressAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class AddressActivity extends BaseActivity {

    private AddressController addressController;
    private UserController userController;

    private AddressAdapter adapter;
    private ListView lv_address;
    private TextView tv_add_address;
    private String username;
    private List<Address> addressList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initViews() {
        tv_add_address = findView(R.id.tv_add_address);
        lv_address = findView(R.id.lv_address);
    }

    @Override
    public void initListener() {
        setOnClick(tv_add_address);
    }

    @Override
    public void initData() {
        setTitle("管理地址");
        setTitleCanBack();

        addressController = AddressController.getInstance();
        userController = UserController.getInstance();
        //初始化我的地址
        initAddressViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_address:
                startActivity(AddAddressActivity.class);
                break;
        }
    }


    /**
     * 初始化我的地址
     */
    private void initAddressViews() {
        username = userController.getUsername();
        addressList = addressController.query(username);
        adapter = new AddressAdapter(this,addressList);
        lv_address.setAdapter(adapter);
    }

}
