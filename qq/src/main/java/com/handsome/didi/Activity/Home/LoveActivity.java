package com.handsome.didi.Activity.Home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import java.util.List;

public class LoveActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    //中
    private LinearLayout ly_love_bg;
    private ListView lv_love;
    private List<Shop> loveList;
    private CartAdapter adapter;
    //底
    private TextView tv_delete;

    @Override
    public int getLayoutId() {
        return R.layout.activity_love;
    }

    @Override
    public void initViews() {
        ly_love_bg = findView(R.id.ly_love_bg);
        lv_love = findView(R.id.lv_love);
        tv_delete = findView(R.id.tv_delete);
    }

    @Override
    public void initListener() {
        setOnClick(tv_delete);
        lv_love.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        //初始化关注数据
        initLoveData();
    }


    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化关注数据
     */
    private void initLoveData() {
        if (loveList.size() > 0) {
            ly_love_bg.setVisibility(View.GONE);
            lv_love.setVisibility(View.VISIBLE);
            adapter = new CartAdapter(this, loveList);
            lv_love.setAdapter(adapter);
        } else {
            ly_love_bg.setVisibility(View.VISIBLE);
            lv_love.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //进入详情页
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
