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

    private Address addressBean;

    //编辑或添加地址
    private int state;

    interface STATE {
        int STATE_ADD = 0x01;
        int STATE_EDIT = 0x02;
    }

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

        //初始化地址页面
        initAddressViews();
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
     * 初始化地址页面
     */
    private void initAddressViews() {
        addressBean = getIntent().getParcelableExtra("address");

        if (addressBean != null) {
            //编辑状态
            state = STATE.STATE_EDIT;
        } else {
            //添加状态
            state = STATE.STATE_ADD;
        }

        if (state == STATE.STATE_EDIT) {
            et_realname.setText(addressBean.realname);
            et_phone.setText(addressBean.phone);
            et_street.setText(addressBean.street);
            et_address.setText(addressBean.address);
            et_area.setText(addressBean.area);
            cb_isdefault.setChecked(addressBean.isdefault);
            bt_save.setText("修改");
        }
    }

    /**
     * 保存按钮点击事件
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

        if (state == STATE.STATE_ADD) {
            //添加到本地数据库
            Address userAddress = new Address(null, username, realname, phone, area, street, address, isdeafault);
            addressController.insert(userAddress);
            onChangeDataInUI(AddressActivity.class.getName());
            showToast("保存成功");
            finish();
        } else if (state == STATE.STATE_EDIT) {
            //修改本地数据库
            Address userAddress = new Address(addressBean.getId(), username, realname, phone, area, street, address, isdeafault);
            addressController.update(userAddress);
            onChangeDataInUI(AddressActivity.class.getName());
            showToast("修改成功");
            finish();
        }
    }
}
