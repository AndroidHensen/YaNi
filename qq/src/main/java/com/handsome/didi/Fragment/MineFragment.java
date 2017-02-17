package com.handsome.didi.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.LoginActivity;
import com.handsome.didi.Activity.LoveActivity;
import com.handsome.didi.Activity.OrderActivity;
import com.handsome.didi.Activity.WalletActivity;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import cn.bmob.v3.BmobUser;

/**
 * Created by handsome on 2016/4/7.
 */
public class MineFragment extends BaseFragment {

    private LinearLayout ly_order, ly_wallet, ly_love, ly_message, ly_appoint, ly_history, ly_discuss, ly_service, ly_return;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ly_order = (LinearLayout) view.findViewById(R.id.ly_order);
        ly_wallet = (LinearLayout) view.findViewById(R.id.ly_wallet);
        ly_love = (LinearLayout) view.findViewById(R.id.ly_love);
        ly_message = (LinearLayout) view.findViewById(R.id.ly_message);
        ly_appoint = (LinearLayout) view.findViewById(R.id.ly_appoint);
        ly_history = (LinearLayout) view.findViewById(R.id.ly_history);
        ly_discuss = (LinearLayout) view.findViewById(R.id.ly_discuss);
        ly_service = (LinearLayout) view.findViewById(R.id.ly_service);
        ly_return = (LinearLayout) view.findViewById(R.id.ly_return);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        ly_order.setOnClickListener(this);
        ly_wallet.setOnClickListener(this);
        ly_love.setOnClickListener(this);
        ly_message.setOnClickListener(this);
        ly_appoint.setOnClickListener(this);
        ly_history.setOnClickListener(this);
        ly_discuss.setOnClickListener(this);
        ly_service.setOnClickListener(this);
        ly_return.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_order:
                //开启订单页面
                Intent intent3 = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent3);
                break;
            case R.id.ly_wallet:
                //开启钱包页面
                Intent intent4 = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent4);
                break;
            case R.id.ly_love:
                //开启我的关注
                Intent intent = new Intent(getActivity(), LoveActivity.class);
                startActivity(intent);
                break;
            default:
                ToastUtils.showToast(getActivity(), "敬请期待");
                break;
        }
    }

}
