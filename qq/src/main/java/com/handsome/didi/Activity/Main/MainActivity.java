package com.handsome.didi.Activity.Main;

import android.Manifest;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Adapter.Main.MainAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Fragment.Main.CartFragment;
import com.handsome.didi.Fragment.Main.CategoryFragment;
import com.handsome.didi.Fragment.Main.FindFragment;
import com.handsome.didi.Fragment.Main.HomeFragment;
import com.handsome.didi.Fragment.Main.MineFragment;
import com.handsome.didi.R;
import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private int isTouchBack = 0;
    private ViewPager vp_main;
    private MainAdapter adapter;
    private List<Fragment> list;
    private ImageView iv_bottom_find, iv_bottom_cart, iv_bottom_category, iv_bottom_mine, iv_bottom_home;
    private TextView tv_bottom_find, tv_bottom_cart, tv_bottom_category, tv_bottom_mine, tv_bottom_home;
    private LinearLayout ly_bottom_home, ly_bottom_find, ly_bottom_cart, ly_bottom_category, ly_bottom_mine;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        vp_main = findView(R.id.vp_main);
        iv_bottom_find = findView(R.id.iv_bottom_find);
        iv_bottom_cart = findView(R.id.iv_bottom_cart);
        iv_bottom_category = findView(R.id.iv_bottom_category);
        iv_bottom_mine = findView(R.id.iv_bottom_mine);
        iv_bottom_home = findView(R.id.iv_bottom_home);
        tv_bottom_find = findView(R.id.tv_bottom_find);
        tv_bottom_cart = findView(R.id.tv_bottom_cart);
        tv_bottom_category = findView(R.id.tv_bottom_category);
        tv_bottom_mine = findView(R.id.tv_bottom_mine);
        tv_bottom_home = findView(R.id.tv_bottom_home);
        ly_bottom_home = findView(R.id.ly_bottom_home);
        ly_bottom_find = findView(R.id.ly_bottom_find);
        ly_bottom_cart = findView(R.id.ly_bottom_cart);
        ly_bottom_category = findView(R.id.ly_bottom_category);
        ly_bottom_mine = findView(R.id.ly_bottom_mine);
        //初始化权限
        requestPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);
    }

    @Override
    public void initListener() {
        setOnClick(ly_bottom_home);
        setOnClick(ly_bottom_find);
        setOnClick(ly_bottom_cart);
        setOnClick(ly_bottom_category);
        setOnClick(ly_bottom_mine);
        vp_main.setOnPageChangeListener(this);
    }

    @Override
    public void initData() {
        //初始化碎片
        initFragments();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.ly_bottom_home:
                iconLight(0);
                break;
            case R.id.ly_bottom_category:
                iconLight(1);
                break;
            case R.id.ly_bottom_find:
                iconLight(2);
                break;
            case R.id.ly_bottom_cart:
                iconLight(3);
                break;
            case R.id.ly_bottom_mine:
                iconLight(4);
                break;
        }
    }

    /**
     * 初始化碎片
     */
    private void initFragments() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CategoryFragment());
        list.add(new FindFragment());
        list.add(new CartFragment());
        list.add(new MineFragment());
        adapter = new MainAdapter(getSupportFragmentManager(), list);
        vp_main.setAdapter(adapter);
        vp_main.setOffscreenPageLimit(5);
        //初始化图标
        iconLight(0);
    }

    /**
     * 图标高亮
     *
     * @param position
     */
    private void iconLight(int position) {
        vp_main.setCurrentItem(position, false);
        iv_bottom_home.setBackgroundResource(position == 0 ? R.drawable.main_bot_tab_home_on : R.drawable.main_bot_tab_home_off);
        iv_bottom_category.setBackgroundResource(position == 1 ? R.drawable.main_bot_tab_category_on : R.drawable.main_bot_tab_category_off);
        iv_bottom_find.setBackgroundResource(position == 2 ? R.drawable.main_bot_tab_find_on : R.drawable.main_bot_tab_find_off);
        iv_bottom_cart.setBackgroundResource(position == 3 ? R.drawable.main_bot_tab_cart_on : R.drawable.main_bot_tab_cart_off);
        iv_bottom_mine.setBackgroundResource(position == 4 ? R.drawable.main_bot_tab_mine_on : R.drawable.main_bot_tab_mine_off);
        tv_bottom_home.setTextColor(position == 0 ? Color.parseColor("#F23030") : Color.parseColor("#5D5F6A"));
        tv_bottom_category.setTextColor(position == 1 ? Color.parseColor("#F23030") : Color.parseColor("#5D5F6A"));
        tv_bottom_find.setTextColor(position == 2 ? Color.parseColor("#F23030") : Color.parseColor("#5D5F6A"));
        tv_bottom_cart.setTextColor(position == 3 ? Color.parseColor("#F23030") : Color.parseColor("#5D5F6A"));
        tv_bottom_mine.setTextColor(position == 4 ? Color.parseColor("#F23030") : Color.parseColor("#5D5F6A"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //改变图标高亮
        iconLight(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        isTouchBack = 0;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        //按2次返回键退出
        isTouchBack++;
        if (isTouchBack == 1) {
            showToast("再按一次返回键退出");
        } else if (isTouchBack == 2) {
            super.finish();
        }
    }
}
