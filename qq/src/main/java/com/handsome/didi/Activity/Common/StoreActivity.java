package com.handsome.didi.Activity.Common;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Adapter.Home.StoreAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.List;

public class StoreActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private StoreController storeController;
    private UserController userController;
    private ShopController shopController;
    private ActivityController activityController;

    private String S_OID;
    private Store store;
    private List<Shop> shopList;

    private TextView tv_store_name, tv_store_fans, tv_love;
    private ImageView iv_store_icon;
    private GridView gv_shops;
    private LinearLayout ly_store_rate;

    private StoreAdapter adapter;

    private static final int MSG_STORE = 0x01;
    private static final int MSG_SHOP = 0x02;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_STORE:
                    setStoreViews();
                    break;
                case MSG_SHOP:
                    setShopViews();
                    break;
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    public void initViews() {
        tv_store_name = findView(R.id.tv_store_name);
        tv_store_fans = findView(R.id.tv_store_fans);
        tv_love = findView(R.id.tv_love);
        iv_store_icon = findView(R.id.iv_store_icon);
        gv_shops = findView(R.id.gv_shops);
        ly_store_rate = findView(R.id.ly_store_rate);
    }

    @Override
    public void initListener() {
        setOnClick(tv_love);
        gv_shops.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("欢迎光临");
        setTitleCanBack();

        storeController = StoreController.getInstance();
        userController = UserController.getInstance();
        shopController = ShopController.getInstance();
        activityController = ActivityController.getInstance();

        initStoreViews();
        initShopViews();
        initCollectionViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_love:
                addUserCollection();
                break;
        }
    }

    /**
     * 初始化店铺信息
     */
    private void initStoreViews() {
        S_OID = getIntent().getStringExtra("S_OID");
        storeController.query(S_OID, new StoreController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                store = (Store) list.get(0);
                mHandler.sendEmptyMessage(MSG_STORE);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 初始化商品信息
     */
    private void initShopViews() {
        shopController.query(S_OID, new ShopController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;
                mHandler.sendEmptyMessage(MSG_SHOP);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 初始化收藏按钮
     */
    private void initCollectionViews() {
        if (userController.getCollectionOid().contains(S_OID)) {
            tv_love.setText("已收藏");
        } else {
            tv_love.setText("收藏");
        }
    }

    /**
     * 设置店铺信息
     */
    private void setStoreViews() {
        tv_store_fans.setText(store.love_num + "");
        tv_store_name.setText(store.name);
        GlideUtils.displayImage(this, store.img_url, iv_store_icon);
        userController.setUserRate(this, store.rate, ly_store_rate);
    }

    /**
     * 设置商品信息
     */
    private void setShopViews() {
        adapter = new StoreAdapter(this, shopList);
        gv_shops.setAdapter(adapter);
    }

    /**
     * 添加用户收藏
     */
    private void addUserCollection() {
        userController.addUserCollection(S_OID, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
                //更新UI
                initCollectionViews();
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }

            @Override
            public void onLoading(String loading) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        activityController.startDetailActivityWithShop(this, shopList.get(position));
    }
}
