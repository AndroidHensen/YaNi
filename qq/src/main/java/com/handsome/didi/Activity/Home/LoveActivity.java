package com.handsome.didi.Activity.Home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Home.LoveAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

public class LoveActivity extends BaseActivity {

    private UserController userController;
    private ShopController shopController;
    //中
    private LinearLayout ly_love_bg;
    private ListView lv_love;
    private List<String> loveList;
    private LoveAdapter adapter;
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
    }

    @Override
    public void initData() {
        setTitle("我的关注");
        setTitleCanBack();

        userController = UserController.getInstance();
        shopController = ShopController.getInstance();
        //初始化关注数据
        initLoveData();
    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                deleteUserLove();
                break;
        }
    }


    /**
     * 初始化关注数据
     */
    private void initLoveData() {
        loveList = userController.getLoveOid();
        shopController.queryCartOrLove(loveList, new ShopController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                tv_delete.setVisibility(View.VISIBLE);
                ly_love_bg.setVisibility(View.GONE);
                adapter = new LoveAdapter(LoveActivity.this, (List<Shop>) list);
                lv_love.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                tv_delete.setVisibility(View.GONE);
                ly_love_bg.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 删除关注
     */
    private void deleteUserLove() {
        userController.deleteUserLove(adapter.getSelected_objectId(), new UserController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                initLoveData();
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onLoading(String loading) {

            }
        });
    }

}
