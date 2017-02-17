package com.handsome.didi.Activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

public class RegisterActivity extends BaseActivity implements TextWatcher {

    private EditText et_password, et_username, et_password_again;
    private String password, username, password_again;
    private Button bt_register;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_register);
        et_password = (EditText) findViewById(R.id.et_password);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        bt_register = (Button) findViewById(R.id.bt_register);
    }

    @Override
    public void initListener() {
        bt_register.setOnClickListener(this);
        et_password_again.addTextChangedListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                password_again = et_password_again.getText().toString().trim();
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if (username.length() >= 6 && username.length() <= 12) {
                    if (password_again.equals(password)) {

                    } else {
                        ToastUtils.showToast(this, "两次密码必须一致");
                        et_password_again.setBackgroundResource(R.drawable.login_center_red_border_bg);
                    }
                } else {
                    ToastUtils.showToast(this, "注册账号必须在6-12位之间");
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        et_password_again.setBackgroundResource(R.drawable.common_bg_gray_10x10);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
