package com.handsome.didi.Activity.Home;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Adapter.Home.RechargeAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Recharge;
import com.handsome.didi.Controller.RechargeController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import java.util.List;

public class RechargeActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    RechargeController rechargeController;
    private TextView tv_buy;
    private LinearLayout ly_recharge;

    private List<Recharge> rechargeList;
    private RechargeAdapter rechargeAdapter;
    private GridView gv_recharge;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_recharge);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        gv_recharge = (GridView) findViewById(R.id.gv_recharge);
        ly_recharge = (LinearLayout) findViewById(R.id.ly_recharge);
    }

    @Override
    public void initListener() {
        tv_buy.setOnClickListener(this);
        gv_recharge.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        rechargeController = new RechargeController(this);
        rechargeController.setTitle(this, "充值中心");
        rechargeController.query(new RechargeController.OnQueryListener() {
            @Override
            public void onQuery(List<Recharge> list) {
                rechargeList = list;
                rechargeAdapter = new RechargeAdapter(RechargeActivity.this, rechargeList);
                gv_recharge.setAdapter(rechargeAdapter);
            }
        });
    }

    @Override
    public void processClick(View v) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
