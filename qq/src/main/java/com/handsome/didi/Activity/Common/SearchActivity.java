package com.handsome.didi.Activity.Common;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Common.CommonShopGridAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.View.MyGridView;
import com.handsome.didi.zxing.activity.CaptureActivity;

import java.util.List;

public class SearchActivity extends BaseActivity {

    //头
    private ImageView iv_zxing;
    private EditText et_search;
    private TextView tv_sure;
    private String searchText;
    //中部
    private MyGridView gv_shops;
    private CommonShopGridAdapter adapter;
    private List<Shop> shopList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initViews() {
        iv_zxing = findView(R.id.iv_zxing);
        et_search = findView(R.id.et_search);
        tv_sure = findView(R.id.tv_sure);
        gv_shops = findView(R.id.gv_shops);
    }

    @Override
    public void initListener() {
        setOnClick(iv_zxing);
        setOnClick(tv_sure);
    }

    @Override
    public void initData() {

    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zxing:
                //开启扫描界面
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sure:
                searchText = et_search.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    showToast("正在搜索...");
                    initSearchResult(searchText);
                } else {
                    showToast( "搜索不能为空");
                }
                break;
        }
    }

    /**
     * @param searchText
     */
    private void initSearchResult(String searchText) {

    }
}
