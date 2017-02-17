package com.handsome.didi.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
public class FindFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2 {

    FindController findController;
    //数据
    private PullToRefreshListView lv_find;
    private FindAdapter adapter;
    private List<Find> findList;
    private final static int DATE_CHAGE = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DATE_CHAGE:
                    lv_find.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        lv_find = (PullToRefreshListView) view.findViewById(R.id.lv_find);
        return view;
    }

    @Override
    public void initData() {
        findController = new FindController();
        findController.setTitle(getActivity(),"发现");
        //初始化发现数据
        initFindData();
    }

    @Override
    public void initListener() {
        lv_find.setOnRefreshListener(this);
    }

    @Override
    public void processClick(View v) {

    }


    /**
     * 初始化发现数据
     */
    private void initFindData() {
        findList = new ArrayList<>();
        findController.query(new FindController.OnQueryListener() {
            @Override
            public void onQuery(List<Find> list) {
                findList = list;
                adapter = new FindAdapter(getActivity(), findList);
                lv_find.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mHandler.sendEmptyMessageDelayed(DATE_CHAGE, 2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mHandler.sendEmptyMessageDelayed(DATE_CHAGE, 2000);
    }
}
