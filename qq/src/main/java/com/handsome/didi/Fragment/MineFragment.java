package com.handsome.didi.Fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Mine.LoginActivity;
import com.handsome.didi.Activity.Home.LoveActivity;
import com.handsome.didi.Activity.Mine.ReturnActivity;
import com.handsome.didi.Activity.Mine.ServiceActivity;
import com.handsome.didi.Activity.Mine.UserActivity;
import com.handsome.didi.Activity.Mine.Order.OrderActivity;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

/**
 * Created by handsome on 2016/4/7.
 */
public class MineFragment extends BaseFragment {

    UserController userController;

    private LinearLayout ly_order, ly_love, ly_message, ly_history, ly_discuss, ly_service, ly_return;
    private RelativeLayout ly_login;
    private TextView tv_username;
    private LinearLayout ly_user_rate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        ly_order = findView(R.id.ly_order);
        ly_love = findView(R.id.ly_love);
        ly_message = findView(R.id.ly_message);
        ly_history = findView(R.id.ly_history);
        ly_discuss = findView(R.id.ly_discuss);
        ly_service = findView(R.id.ly_service);
        ly_return = findView(R.id.ly_return);
        ly_login = findView(R.id.ly_login);
        ly_user_rate = findView(R.id.ly_user_rate);
        tv_username = findView(R.id.tv_username);
    }

    @Override
    public void initData() {
        userController = new UserController(getActivity());

        initUserViews();
    }


    @Override
    public void initListener() {
        setOnClick(ly_order);
        setOnClick(ly_love);
        setOnClick(ly_message);
        setOnClick(ly_history);
        setOnClick(ly_discuss);
        setOnClick(ly_service);
        setOnClick(ly_return);
        setOnClick(ly_login);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_order:
                //开启订单页面
                startActivity(OrderActivity.class);
                break;
            case R.id.ly_love:
                //开启我的关注
                startActivity(LoveActivity.class);
                break;
            case R.id.ly_login:
                if (userController.isLogin()) {
                    //开启详情界面
                    startActivity(UserActivity.class);
                } else {
                    //开启登陆界面
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.ly_return:
                startActivity(ReturnActivity.class);
                break;
            case R.id.ly_service:
                startActivity(ServiceActivity.class);
                break;
        }
    }


    /**
     * 初始化用户信息界面
     */
    private void initUserViews() {
        if (userController.isLogin()) {
            User user = userController.getCurrentUser();
            tv_username.setText(user.getUsername());
            userController.setUserRate(user.getRate(), ly_user_rate);
        }
    }

}
