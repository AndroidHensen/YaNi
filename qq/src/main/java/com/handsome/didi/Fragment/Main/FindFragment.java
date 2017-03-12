package com.handsome.didi.Fragment.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handsome.didi.Activity.Common.StoreActivity;
import com.handsome.didi.Adapter.Find.FindAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Find;
import com.handsome.didi.Controller.FindController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class FindFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {

    FindController findController;
    private Intent intent;
    //数据
    private PullToRefreshListView lv_find;
    private FindAdapter adapter;
    private List<Find> findList;
    private int currentPage = 0;
    private final static int DATE_CHANGE = 1;

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
        findController = new FindController(getActivity());
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
        findList = new ArrayList<>();
        findController.query(currentPage, new FindController.OnQueryListener() {
            @Override
            public void onQuery(List<Find> list) {
                findList = list;
                adapter = new FindAdapter(getActivity(), findList);
                lv_find.setAdapter(adapter);
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
        findController.query(currentPage, new FindController.OnQueryListener() {
            @Override
            public void onQuery(List<Find> list) {
                findList.addAll(list);
                adapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(DATE_CHANGE, 200);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getActivity(), StoreActivity.class);
        intent.putExtra("S_OID", findList.get(position).getS_OID());
        startActivity(intent);
    }
}
