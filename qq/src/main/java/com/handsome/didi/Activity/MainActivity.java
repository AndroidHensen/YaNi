package com.handsome.didi.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handsome.didi.Adapter.Main.MainAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Fragment.CartFragment;
import com.handsome.didi.Fragment.CategoryFragment;
import com.handsome.didi.Fragment.FindFragment;
import com.handsome.didi.Fragment.HomeFragment;
import com.handsome.didi.Fragment.MineFragment;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private int isTouchBack = 0;
    private ViewPager vp_main;
    private MainAdapter adapter;
    private List<Fragment> list;
    private ImageView iv_bottom_find, iv_bottom_cart, iv_bottom_category, iv_bottom_mine, iv_bottom_home;
    private LinearLayout ly_bottom_home, ly_bottom_find, ly_bottom_cart, ly_bottom_category, ly_bottom_mine;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        iv_bottom_find = (ImageView) findViewById(R.id.iv_bottom_find);
        iv_bottom_cart = (ImageView) findViewById(R.id.iv_bottom_cart);
        iv_bottom_category = (ImageView) findViewById(R.id.iv_bottom_category);
        iv_bottom_mine = (ImageView) findViewById(R.id.iv_bottom_mine);
        iv_bottom_home = (ImageView) findViewById(R.id.iv_bottom_home);
        ly_bottom_home = (LinearLayout) findViewById(R.id.ly_bottom_home);
        ly_bottom_find = (LinearLayout) findViewById(R.id.ly_bottom_find);
        ly_bottom_cart = (LinearLayout) findViewById(R.id.ly_bottom_cart);
        ly_bottom_category = (LinearLayout) findViewById(R.id.ly_bottom_category);
        ly_bottom_mine = (LinearLayout) findViewById(R.id.ly_bottom_mine);
    }

    @Override
    public void initListener() {
        vp_main.setOnPageChangeListener(this);
        ly_bottom_home.setOnClickListener(this);
        ly_bottom_find.setOnClickListener(this);
        ly_bottom_cart.setOnClickListener(this);
        ly_bottom_category.setOnClickListener(this);
        ly_bottom_mine.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //初始化碎片
        initFragment();
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
    private void initFragment() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CategoryFragment());
        list.add(new FindFragment());
        list.add(new CartFragment());
        list.add(new MineFragment());
        adapter = new MainAdapter(getSupportFragmentManager(), list);
        vp_main.setAdapter(adapter);
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
        iv_bottom_home.setBackgroundResource(position == 0 ? R.drawable.main_bot_tab_home_off : R.drawable.main_bot_tab_home_on);
        iv_bottom_category.setBackgroundResource(position == 1 ? R.drawable.main_bot_tab_category_on : R.drawable.main_bot_tab_category_off);
        iv_bottom_find.setBackgroundResource(position == 2 ? R.drawable.main_bot_tab_find_off : R.drawable.main_bot_tab_find_on);
        iv_bottom_cart.setBackgroundResource(position == 3 ? R.drawable.main_bot_tab_cart_on : R.drawable.main_bot_tab_cart_off);
        iv_bottom_mine.setBackgroundResource(position == 4 ? R.drawable.main_bot_tab_mine_on : R.drawable.main_bot_tab_mine_off);
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
            ToastUtils.showToast(this, "再按一次返回键退出");
        } else if (isTouchBack == 2) {
            super.finish();
        }
    }
}
