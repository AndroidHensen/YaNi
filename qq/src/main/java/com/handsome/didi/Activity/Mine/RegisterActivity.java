package com.handsome.didi.Activity.Mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.Fragment.Main.HomeFragment;
import com.handsome.didi.Fragment.Main.MineFragment;
import com.handsome.didi.R;

public class RegisterActivity extends BaseActivity {

    private UserController userController;

    private EditText et_password, et_username, et_password_again;
    private Button bt_register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initViews() {
        et_password = findView(R.id.et_password);
        et_username = findView(R.id.et_username);
        et_password_again = findView(R.id.et_password_again);
        bt_register = findView(R.id.bt_register);
    }

    @Override
    public void initListener() {
        bt_register.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("免费注册");
        setTitleCanBack();

        userController = UserController.getInstance();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String password_again = et_password_again.getText().toString().trim();
                userController.register(username, password, password_again, new BaseController.onBmobUserListener() {
                    @Override
                    public void onSuccess(String success) {
                        onChangeDataInUI(MineFragment.class.getName());
                        showToast(success);
                        finish();
                        //跳转到用户详情页面
                        startActivity(UserDetailActivity.class);
                    }

                    @Override
                    public void onError(String error) {
                        showToast(error);
                    }

                    @Override
                    public void onLoading(String loading) {
                        showToast(loading);
                    }
                });
                break;
        }
    }

}
