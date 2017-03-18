package com.handsome.didi.Fragment.Order;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.LoveActivity;
import com.handsome.didi.Activity.Mine.LoginActivity;
import com.handsome.didi.Activity.Mine.OrderActivity;
import com.handsome.didi.Activity.Mine.ReturnActivity;
import com.handsome.didi.Activity.Mine.ServiceActivity;
import com.handsome.didi.Activity.Mine.UserActivity;
import com.handsome.didi.Adapter.Mine.OrderAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

/**
 * Created by handsome on 2016/4/7.
 */
public class AllFragment extends BaseFragment {

    private ListView lv_all;
    private OrderAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
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
