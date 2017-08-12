package com.handsome.didi.Activity.Mine;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.Fragment.Main.CartFragment;
import com.handsome.didi.Fragment.Main.HomeFragment;
import com.handsome.didi.Fragment.Main.MineFragment;
import com.handsome.didi.R;

public class UserActivity extends BaseActivity {

    private UserController userController;
    //界面
    private TextView tv_user_name, tv_user_age, tv_user_sex;
    private LinearLayout ly_user_message, ly_user_address;
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
        ly_user_message = findView(R.id.ly_user_message);
        ly_user_address = findView(R.id.ly_user_address);
        bt_login_out = findView(R.id.bt_login_out);
    }

    @Override
    public void initListener() {
        bt_login_out.setOnClickListener(this);
        ly_user_message.setOnClickListener(this);
        ly_user_address.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("用户信息");
        setTitleCanBack();

        userController = UserController.getInstance();
        initUserViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_user_message:
                //修改用户信息
                startActivity(UserDetailActivity.class);
                break;
            case R.id.ly_user_address:
                //管理地址信息
                startActivity(AddressActivity.class);
                break;
            case R.id.bt_login_out:
                //退出登录
                loginOut();
                break;
        }
    }

    /**
     * 初始化用户信息界面
     */
    private void initUserViews() {
        User user = userController.getCurrentUser();
        if (user != null) {
            tv_user_name.setText(user.getUsername());
            tv_user_age.setText(user.age + "");
            tv_user_sex.setText(user.sex ? "男" : "女");
        }
    }

    /**
     * 退出登录
     */
    private void loginOut() {
        userController.loginOut(new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                //更新UI
                onChangeDataInUI(MineFragment.class.getName());
                onChangeDataInUI(CartFragment.class.getName());
                showToast(success);
                finish();
            }

            @Override
            public void onError(String error) {
            }

            @Override
            public void onLoading(String loading) {
            }
        });
    }

}
