package com.handsome.didi.Fragment.Order;

import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Mine.OrderAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.R;

/**
 * Created by handsome on 2016/4/7.
 */
public class OrderAllFragment extends BaseFragment {

    private ListView lv_all;
    private OrderAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public void initViews() {
        lv_all = findView(R.id.lv_all);
    }

    @Override
    public void initData() {

    }


    @Override
    public void initListener() {
    }

    @Override
    public void processClick(View v) {
    }
}
