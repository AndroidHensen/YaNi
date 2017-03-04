package com.handsome.didi.Activity.Home;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

    private List<Recharge> rechargeList;
    private RechargeAdapter rechargeAdapter;
    private GridView gv_recharge;

    private EditText et_recharge;
    private TextView tv_phone_recharge, tv_game_recharge;
    private View view_phone_recharge, view_game_recharge;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initViews() {
        tv_buy = findView(R.id.tv_buy);
        tv_phone_recharge = findView(R.id.tv_phone_recharge);
        tv_game_recharge = findView(R.id.tv_game_recharge);
        view_phone_recharge = findView(R.id.view_phone_recharge);
        view_game_recharge = findView(R.id.view_game_recharge);
        et_recharge = findView(R.id.et_recharge);
        gv_recharge = findView(R.id.gv_recharge);
    }

    @Override
    public void initListener() {
        setOnClick(tv_buy);
        setOnClick(tv_phone_recharge);
        setOnClick(tv_game_recharge);
        gv_recharge.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        rechargeController = new RechargeController(this);
        rechargeController.setTitle(this, "充值中心");

        initPhoneRecharge();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_phone_recharge:
                view_phone_recharge.setVisibility(View.VISIBLE);
                view_game_recharge.setVisibility(View.INVISIBLE);
                et_recharge.setHint("请输入要充值的手机号码");
                break;
            case R.id.tv_game_recharge:
                view_phone_recharge.setVisibility(View.INVISIBLE);
                view_game_recharge.setVisibility(View.VISIBLE);
                et_recharge.setHint("请输入要充值的QQ号码");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void initPhoneRecharge() {
        rechargeController.query(new RechargeController.OnQueryListener() {
            @Override
            public void onQuery(List<Recharge> list) {
                rechargeList = list;
                rechargeAdapter = new RechargeAdapter(RechargeActivity.this, rechargeList);
                gv_recharge.setAdapter(rechargeAdapter);
            }
        });
    }

}
