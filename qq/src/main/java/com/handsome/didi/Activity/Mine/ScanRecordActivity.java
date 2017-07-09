package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Mine.ScanRecordAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class ScanRecordActivity extends BaseActivity {

    private ShopController shopController;
    private UserController userController;

    private ListView lv_scan_record;
    private ScanRecordAdapter adapter;
    private List<Shop> shopList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_record;
    }

    @Override
    public void initViews() {
        lv_scan_record = findView(R.id.lv_scan_record);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("浏览记录");
        setTitleCanBack();

        shopController = ShopController.getInstance();
        userController = UserController.getInstance();

        //初始化浏览记录页面
        initScanRecordViews();
    }


    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化浏览记录页面
     */
    private void initScanRecordViews() {
        shopController.query(userController.getScanRecordOid(), new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;
                adapter = new ScanRecordAdapter(ScanRecordActivity.this, shopList);
                lv_scan_record.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
