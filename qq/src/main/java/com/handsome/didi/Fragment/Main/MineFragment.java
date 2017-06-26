package com.handsome.didi.Fragment.Main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Mine.CollectionActivity;
import com.handsome.didi.Activity.Mine.LoginActivity;
import com.handsome.didi.Activity.Home.LoveActivity;
import com.handsome.didi.Activity.Mine.MyCardActivity;
import com.handsome.didi.Activity.Mine.ReturnActivity;
import com.handsome.didi.Activity.Mine.ServiceActivity;
import com.handsome.didi.Activity.Mine.UserActivity;
import com.handsome.didi.Activity.Order.OrderActivity;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

/**
 * Created by handsome on 2016/4/7.
 */
public class MineFragment extends BaseFragment {

    private UserController userController;

    private LinearLayout ly_order, ly_love, ly_message, ly_history, ly_favorite, ly_service, ly_return;
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
        ly_favorite = findView(R.id.ly_favorite);
        ly_service = findView(R.id.ly_service);
        ly_return = findView(R.id.ly_return);
        ly_login = findView(R.id.ly_login);
        ly_user_rate = findView(R.id.ly_user_rate);
        tv_username = findView(R.id.tv_username);
    }

    @Override
    public void initData() {
        userController = UserController.getInstance();
        initUserViews();
    }


    @Override
    public void initListener() {
        setOnClick(ly_order);
        setOnClick(ly_love);
        setOnClick(ly_message);
        setOnClick(ly_history);
        setOnClick(ly_favorite);
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
                //开启回馈帮助页面
                startActivity(ReturnActivity.class);
                break;
            case R.id.ly_service:
                //开启在线客服页面
                startActivity(ServiceActivity.class);
                break;
            case R.id.ly_favorite:
                //开启我的收藏页面
                startActivity(CollectionActivity.class);
                break;
            case R.id.ly_message:
                //开启我的卡券页面
                startActivity(MyCardActivity.class);
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
            userController.setUserRate(getActivity(), user.rate, ly_user_rate);
        } else {
            tv_username.setText("您还未登陆哦~");
            ly_user_rate.removeAllViews();
        }
    }

}
