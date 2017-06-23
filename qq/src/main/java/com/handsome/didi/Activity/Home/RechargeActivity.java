package com.handsome.didi.Activity.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Home.RechargeAdapter;
import com.handsome.didi.Adapter.Main.MainAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Recharge;
import com.handsome.didi.Controller.RechargeController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.Fragment.Home.RechargeGameFragment;
import com.handsome.didi.Fragment.Home.RechargePhoneFragment;
import com.handsome.didi.Fragment.Mine.OrderAllFragment;
import com.handsome.didi.Fragment.Mine.OrderGetFragment;
import com.handsome.didi.Fragment.Mine.OrderPayFragment;
import com.handsome.didi.Fragment.Mine.OrderSendFragment;
import com.handsome.didi.Fragment.Mine.OrderWaitFragment;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends BaseActivity {

    private TabLayout tl_recharge;
    private ViewPager vp_recharge;
    private List<Fragment> fragments;
    private List<String> strings;
    private MainAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initViews() {
        tl_recharge = findView(R.id.tl_recharge);
        vp_recharge = findView(R.id.vp_recharge);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("充值中心");
        setTitleCanBack();

        initFragments();
    }

    @Override
    public void processClick(View v) {

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new RechargePhoneFragment());
        fragments.add(new RechargeGameFragment());

        strings = new ArrayList<>();
        strings.add("话费充值");
        strings.add("点卡点券");
        //添加Tab
        for (String str : strings) {
            tl_recharge.addTab(tl_recharge.newTab().setText(str));
        }
        //绑定ViewPager
        adapter = new MainAdapter(getSupportFragmentManager(), fragments, strings);
        vp_recharge.setAdapter(adapter);
        vp_recharge.setOffscreenPageLimit(2);
        tl_recharge.setupWithViewPager(vp_recharge);
    }

}
