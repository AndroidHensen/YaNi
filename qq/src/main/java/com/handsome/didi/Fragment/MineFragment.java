package com.handsome.didi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Mine.LoginActivity;
import com.handsome.didi.Activity.LoveActivity;
import com.handsome.didi.Activity.OrderActivity;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

/**
 * Created by handsome on 2016/4/7.
 */
public class MineFragment extends BaseFragment {

    UserController userController;
    Intent intent;

    private LinearLayout ly_order, ly_love, ly_message, ly_history, ly_discuss, ly_service, ly_return;
    private RelativeLayout ly_login;
    private TextView tv_username;
    private LinearLayout ly_user_rate;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ly_order = (LinearLayout) view.findViewById(R.id.ly_order);
        ly_love = (LinearLayout) view.findViewById(R.id.ly_love);
        ly_message = (LinearLayout) view.findViewById(R.id.ly_message);
        ly_history = (LinearLayout) view.findViewById(R.id.ly_history);
        ly_discuss = (LinearLayout) view.findViewById(R.id.ly_discuss);
        ly_service = (LinearLayout) view.findViewById(R.id.ly_service);
        ly_return = (LinearLayout) view.findViewById(R.id.ly_return);
        ly_login = (RelativeLayout) view.findViewById(R.id.ly_login);
        ly_user_rate = (LinearLayout) view.findViewById(R.id.ly_user_rate);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        return view;
    }

    @Override
    public void initData() {
        userController = new UserController(getActivity());
        userController.setTitle(getActivity(), "我的");

        initUserViews();
    }


    @Override
    public void initListener() {
        ly_order.setOnClickListener(this);
        ly_love.setOnClickListener(this);
        ly_message.setOnClickListener(this);
        ly_history.setOnClickListener(this);
        ly_discuss.setOnClickListener(this);
        ly_service.setOnClickListener(this);
        ly_return.setOnClickListener(this);
        ly_login.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_order:
                //开启订单页面
                intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ly_love:
                //开启我的关注
                intent = new Intent(getActivity(), LoveActivity.class);
                startActivity(intent);
                break;
            case R.id.ly_login:
                //开启登陆界面
                if (!userController.isLogin()) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


    /**
     * 初始化用户信息界面
     */
    private void initUserViews() {
        String username = userController.getUserName();
        int rate = userController.getUserRate();
        tv_username.setText(username);
        userController.setUserRate(rate, ly_user_rate);
    }

}
