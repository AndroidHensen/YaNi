package com.handsome.didi.Activity.Mine;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class UserActivity extends BaseActivity {

    private UserController userController;
    //界面
    private TextView tv_user_name, tv_user_age, tv_user_sex;
    private LinearLayout ly_user_update;
    private Button bt_login_out;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public void initViews() {
        tv_user_name = findView(R.id.tv_user_name);
        tv_user_age = findView(R.id.tv_user_age);
        tv_user_sex = findView(R.id.tv_user_sex);
        ly_user_update = findView(R.id.ly_user_update);
        bt_login_out = findView(R.id.bt_login_out);
    }

    @Override
    public void initListener() {
        bt_login_out.setOnClickListener(this);
        ly_user_update.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("用户信息");
        userController = new UserController(this);

        initUserViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_user_update:
                //修改用户信息

                break;
            case R.id.bt_login_out:
                //退出登录
                userController.loginOut();
                break;
        }
    }

    /**
     * 初始化用户信息界面
     */
    private void initUserViews() {
        User user = userController.getCurrentUser();
        tv_user_name.setText(user.getUsername());
        tv_user_age.setText(user.getAge() + "");
        tv_user_sex.setText(user.getSex() ? "男" : "女");
    }

}
