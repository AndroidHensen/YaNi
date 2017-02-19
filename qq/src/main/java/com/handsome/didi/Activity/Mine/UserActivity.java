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
    //数据
    private User user;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setUserViews(user);
        }
    };

    @Override
    public void initViews() {
        setContentView(R.layout.activity_user);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_age = (TextView) findViewById(R.id.tv_user_age);
        tv_user_sex = (TextView) findViewById(R.id.tv_user_sex);
        ly_user_update = (LinearLayout) findViewById(R.id.ly_user_update);
        bt_login_out = (Button) findViewById(R.id.bt_login_out);
    }

    @Override
    public void initListener() {
        bt_login_out.setOnClickListener(this);
        ly_user_update.setOnClickListener(this);
    }

    @Override
    public void initData() {
        userController = new UserController(this);
        userController.setTitle(this, "用户信息");

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
        String objectId = userController.getUserObjectId();
        userController.query(objectId, new UserController.OnQueryListener() {
            @Override
            public void onQuery(List<User> list) {
                user = list.get(0);
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    private void setUserViews(User user) {
        tv_user_name.setText(user.getName());
        tv_user_age.setText(user.getAge() + "");
        tv_user_sex.setText(user.getSex() ? "男" : "女");
    }

}
