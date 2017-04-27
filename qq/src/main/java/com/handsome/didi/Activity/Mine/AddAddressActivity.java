package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

public class AddAddressActivity extends BaseActivity {

    private UserController userController;
    private AddressController addressController;

    private EditText et_realname, et_phone, et_street, et_area, et_address;
    private CheckBox cb_isdefault;
    private Button bt_save;

    private String username;
    private String realname, phone, street, area, address;
    private boolean isdeafault;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initViews() {
        et_realname = findView(R.id.et_realname);
        et_phone = findView(R.id.et_phone);
        et_street = findView(R.id.et_street);
        et_area = findView(R.id.et_area);
        et_address = findView(R.id.et_address);
        cb_isdefault = findView(R.id.cb_isdefault);
        bt_save = findView(R.id.bt_save);
    }

    @Override
    public void initListener() {
        setOnClick(bt_save);
    }

    @Override
    public void initData() {
        setTitle("添加新地址");
        setTitleCanBack();

        userController = UserController.getInstance();
        addressController = AddressController.getInstance();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                saveAddress();
                break;
        }
    }

    /**
     * 保存到本地数据库
     */
    public void saveAddress() {
        username = userController.getUsername();
        isdeafault = cb_isdefault.isChecked();
        realname = et_realname.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        street = et_street.getText().toString().trim();
        area = et_area.getText().toString().trim();
        address = et_address.getText().toString().trim();
        if (phone.length() < 11) {
            showToast("电话填写不符合规格");
            return;
        }
        if (TextUtils.isEmpty(realname) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(street) || TextUtils.isEmpty(area) || TextUtils.isEmpty(address)) {
            showToast("地址信息填写不全");
            return;
        }
        if (isdeafault) {
            //将已经设置为默认地址的选项取消掉
            addressController.updateAddressWithoutDefault(username);
        }

        Address userAddress = new Address(null, username, realname, phone, street, area, address, isdeafault);
        //添加到本地数据库
        long id = addressController.insert(userAddress);
        if (id != -1) {
            showToast("保存成功");
            finish();
        } else {
            showToast("保存失败");
        }
    }
}
