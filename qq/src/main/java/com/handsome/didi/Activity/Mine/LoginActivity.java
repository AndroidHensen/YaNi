package com.handsome.didi.Activity.Mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

public class LoginActivity extends BaseActivity {

    private UserController userController;
    //界面
    private EditText et_username, et_password;
    private Button bt_login, bt_register;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);
    }

    @Override
    public void initListener() {
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void initData() {
        userController = new UserController(this);
        userController.setTitle(this, "账户登录");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                //登陆
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                userController.login(username, password);
                break;
            case R.id.bt_register:
                //注册
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
