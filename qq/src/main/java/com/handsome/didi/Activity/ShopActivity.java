package com.handsome.didi.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Adapter.Common.CommonShopGridAdapter;
import com.handsome.didi.Adapter.Common.CommonShopListAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    ShopController shopController;
    //头
    private TextView tv_title_shop;
    private String title;
    private ImageView iv_shop_header_icon;

    //列表
    private ListView lv_shop;
    private List<Shop> shopList;
    private CommonShopListAdapter shop_list_adapter;
    private boolean isList = true;

    //表格
    private GridView gv_shop;
    private CommonShopGridAdapter shop_grid_adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_shop);
        lv_shop = (ListView) findViewById(R.id.lv_shop);
        gv_shop = (GridView) findViewById(R.id.gv_shop);
        tv_title_shop = (TextView) findViewById(R.id.tv_title_shop);
        iv_shop_header_icon = (ImageView) findViewById(R.id.iv_shop_header_icon);
    }

    @Override
    public void initListener() {
        iv_shop_header_icon.setOnClickListener(this);
        lv_shop.setOnItemClickListener(this);
        gv_shop.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        shopController = new ShopController();
        //初始化头标题
        title = getIntent().getStringExtra("title");
        tv_title_shop.setText(title);
        //初始化列表商品
        initListShop();
        //初始化表格商品
        initGridView();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.iv_shop_header_icon:
                //列表和表格的切换
                if (isList) {
                    lv_shop.setVisibility(View.GONE);
                    gv_shop.setVisibility(View.VISIBLE);
                    iv_shop_header_icon.setBackgroundResource(R.drawable.shop_header_list_icon);
                    isList = false;
                } else {
                    lv_shop.setVisibility(View.VISIBLE);
                    gv_shop.setVisibility(View.GONE);
                    iv_shop_header_icon.setBackgroundResource(R.drawable.shop_header_grid_icon);
                    isList = true;
                }
                break;
        }
    }

    /**
     * 初始化列表商品
     */

    private void initListShop() {
        shopList = new ArrayList<Shop>();
        shopController.query(new ShopController.OnQueryListener() {
            @Override
            public void onQuery(List<Shop> list) {
                shopList = list;
                shop_list_adapter = new CommonShopListAdapter(ShopActivity.this, shopList);
                lv_shop.setAdapter(shop_list_adapter);
            }
        });
    }

    /**
     * 初始化表格商品
     */
    private void initGridView() {
        shop_grid_adapter = new CommonShopGridAdapter(this, shopList);
        gv_shop.setAdapter(shop_grid_adapter);
        gv_shop.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //进入详情页
        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);
    }
}
