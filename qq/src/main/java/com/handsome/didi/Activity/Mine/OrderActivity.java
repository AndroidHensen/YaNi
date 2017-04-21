package com.handsome.didi.Activity.Mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.handsome.didi.Adapter.Main.MainAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Fragment.Order.OrderAllFragment;
import com.handsome.didi.Fragment.Order.OrderWaitFragment;
import com.handsome.didi.Fragment.Order.OrderGetFragment;
import com.handsome.didi.Fragment.Order.OrderPayFragment;
import com.handsome.didi.Fragment.Order.OrderSendFragment;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActivity {

    private TabLayout tl_order;
    private ViewPager vp_order;
    private List<Fragment> fragments;
    private List<String> strings;
    private MainAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initViews() {
        tl_order = findView(R.id.tl_order);
        vp_order = findView(R.id.vp_order);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("订单中心");
        setTitleCanBack();

        initFragments();
    }

    @Override
    public void processClick(View v) {

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new OrderAllFragment());
        fragments.add(new OrderPayFragment());
        fragments.add(new OrderSendFragment());
        fragments.add(new OrderGetFragment());
        fragments.add(new OrderWaitFragment());

        strings = new ArrayList<>();
        strings.add("全部");
        strings.add("待付款");
        strings.add("待发货");
        strings.add("待收货");
        strings.add("待评价");
        //添加Tab
        for (String str : strings) {
            tl_order.addTab(tl_order.newTab().setText(str));
        }
        //绑定ViewPager
        adapter = new MainAdapter(getSupportFragmentManager(), fragments, strings);
        vp_order.setAdapter(adapter);
        tl_order.setupWithViewPager(vp_order);
    }
}
