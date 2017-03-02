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
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

public class StoreActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private StoreController storeController;
    private UserController userController;
    private ShopController shopController;

    private String OID;
    private String S_OID;
    private Store store;
    private List<Shop> shopList;
    private BitmapUtils bitmapUtils;

    private TextView tv_store_name, tv_store_fans;
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
        tv_store_fans =findView(R.id.tv_store_fans);
        iv_store_icon = findView(R.id.iv_store_icon);
        gv_shops = findView(R.id.gv_shops);
        ly_store_rate = findView(R.id.ly_store_rate);
    }

    @Override
    public void initListener() {
        gv_shops.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        storeController = new StoreController(this);
        userController = new UserController(this);
        shopController = new ShopController(this);

        storeController.setTitle(this, "欢迎进入");
        bitmapUtils = new BitmapUtils(this);
        initStoreViews();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化店铺信息
     */
    private void initStoreViews() {
        S_OID = getIntent().getStringExtra("S_OID");
        storeController.query(S_OID, new StoreController.OnQueryListener() {
            @Override
            public void onQuery(List<Store> list) {
                store = list.get(0);
                OID = store.getObjectId();
                mHandler.sendEmptyMessage(MSG_STORE);

                initShopViews();
            }

            private void initShopViews() {
                shopController.query(OID, new ShopController.OnQueryListener() {
                    @Override
                    public void onQuery(List<Shop> list) {
                        shopList = list;
                        mHandler.sendEmptyMessage(MSG_SHOP);
                    }
                });
            }
        });
    }


    /**
     * 设置店铺信息
     */
    private void setStoreViews() {
        tv_store_fans.setText(store.getLove_num() + "");
        tv_store_name.setText(store.getName());
        bitmapUtils.display(iv_store_icon, store.getImg_url());
        userController.setUserRate(store.getRate(), ly_store_rate);
    }

    /**
     * 设置商品信息
     */
    private void setShopViews() {
        adapter = new StoreAdapter(this, shopList);
        gv_shops.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        shopController.startDetailActivityWithShop(this, shopList.get(position));
    }
}
