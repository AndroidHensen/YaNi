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
import com.handsome.didi.Activity.Mine.ScanRecordActivity;
import com.handsome.didi.Activity.Mine.ServiceActivity;
import com.handsome.didi.Activity.Mine.UserActivity;
import com.handsome.didi.Activity.Order.OrderActivity;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.library.FastMenuBar;

/**
 * Created by handsome on 2016/4/7.
 */
public class MineFragment extends BaseFragment implements FastMenuBar.onMenuBarClickListener {

    private UserController userController;

    private FastMenuBar fmb_order, fmb_love, fmb_message, fmb_history, fmb_favorite, fmb_service, fmb_return;
    private RelativeLayout ly_login;
    private TextView tv_username;
    private LinearLayout ly_user_rate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
        fmb_order = findView(R.id.fmb_order);
        fmb_love = findView(R.id.fmb_love);
        fmb_message = findView(R.id.fmb_message);
        fmb_history = findView(R.id.fmb_history);
        fmb_favorite = findView(R.id.fmb_favorite);
        fmb_service = findView(R.id.fmb_service);
        fmb_return = findView(R.id.fmb_return);
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
        fmb_order.setOnMenuBarClickListener(this);
        fmb_love.setOnMenuBarClickListener(this);
        fmb_message.setOnMenuBarClickListener(this);
        fmb_history.setOnMenuBarClickListener(this);
        fmb_favorite.setOnMenuBarClickListener(this);
        fmb_service.setOnMenuBarClickListener(this);
        fmb_return.setOnMenuBarClickListener(this);
        setOnClick(ly_login);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_login:
                if (userController.isLogin()) {
                    //开启详情界面
                    startActivity(UserActivity.class);
                } else {
                    //开启登陆界面
                    startActivity(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void onMenuBarClick(FastMenuBar fastMenuBar) {
        switch (fastMenuBar.getId()) {
            case R.id.fmb_order:
                //开启订单页面
                startActivity(OrderActivity.class);
                break;
            case R.id.fmb_love:
                //开启我的关注
                startActivity(LoveActivity.class);
                break;
            case R.id.fmb_return:
                //开启回馈帮助页面
                startActivity(ReturnActivity.class);
                break;
            case R.id.fmb_service:
                //开启在线客服页面
                startActivity(ServiceActivity.class);
                break;
            case R.id.fmb_favorite:
                //开启我的收藏页面
                startActivity(CollectionActivity.class);
                break;
            case R.id.fmb_message:
                //开启我的卡券页面
                startActivity(MyCardActivity.class);
                break;
            case R.id.fmb_history:
                //开启浏览记录页面
                startActivity(ScanRecordActivity.class);
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
