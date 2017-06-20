package com.handsome.didi.Activity.Mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handsome.didi.Adapter.Mine.CollectionAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class CollectionActivity extends BaseActivity {

    private StoreController storeController;
    private UserController userController;

    private CollectionAdapter collectionAdapter;
    private RecyclerView rv_collection;
    private List<String> S_OID;
    private List<Store> storeList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initViews() {
        rv_collection = findView(R.id.rv_collection);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        setTitle("我的收藏");
        setTitleCanBack();

        storeController = StoreController.getInstance();
        userController = UserController.getInstance();

        initCollecionViews();
    }

    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化收藏列表
     */
    private void initCollecionViews() {
        S_OID = userController.getCollectionOid();
        storeController.query(S_OID, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                storeList = (List<Store>) list;

                collectionAdapter = new CollectionAdapter(CollectionActivity.this, storeList);
                rv_collection.setLayoutManager(new LinearLayoutManager(CollectionActivity.this));
                rv_collection.setAdapter(collectionAdapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

}
