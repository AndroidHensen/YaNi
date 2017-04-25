package com.handsome.didi.Fragment.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Adapter.Find.FindAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Find;
import com.handsome.didi.Controller.FindController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class FindFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    private StoreController storeController;
    private FindController findController;
    private Intent intent;
    //整页
    private PullToRefreshListView lv_find;
    private int currentPage = 0;
    private final static int DATE_CHANGE = 1;
    //发现数据
    private FindAdapter adapter;
    private List<Find> findList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DATE_CHANGE:
                    lv_find.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initViews() {
        lv_find = findView(R.id.lv_find);
    }

    @Override
    public void initData() {
        findController = FindController.getInstance();
        storeController = StoreController.getInstance();
        //初始化发现数据
        initFindData();
    }

    @Override
    public void initListener() {
        lv_find.setOnRefreshListener(this);
        lv_find.setOnItemClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }


    /**
     * 初始化发现数据
     */
    private void initFindData() {
        currentPage = 0;
        findList = new ArrayList<>();
        findController.query(currentPage, new FindController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                findList = (List<Find>) list;
                adapter = new FindAdapter(getActivity(), findList);
                lv_find.setAdapter(adapter);
                mHandler.sendEmptyMessageDelayed(DATE_CHANGE, 200);
            }

            @Override
            public void onError(String error) {
                showToast(error);
                mHandler.sendEmptyMessageDelayed(DATE_CHANGE, 200);
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initFindData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currentPage++;
        findController.query(currentPage, new FindController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                findList.addAll((List<Find>) list);
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(DATE_CHANGE, 200);
            }

            @Override
            public void onError(String error) {
                showToast(error);
                mHandler.sendEmptyMessageDelayed(DATE_CHANGE, 200);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到商店页面
        storeController.startStoreActivityWithStoreId(getActivity(), findList.get(position - 1).S_OID);
    }
}
