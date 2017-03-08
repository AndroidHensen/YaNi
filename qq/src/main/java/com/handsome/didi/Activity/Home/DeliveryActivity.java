package com.handsome.didi.Activity.Home;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class DeliveryActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private EditText et_no;
    private TextView tv_query;
    private ListView lv_delivery;
    private Spinner sp_delivery;

    private String[] company = {"EMS", "圆通", "顺丰", "天天", "申通", "中通", "韵达"};
    private String[] companyNum = {"ems", "yt", "sf", "tt", "sto", "zto", "yd"};
    private String com = companyNum[0];
    private String no;

    @Override
    public int getLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    public void initViews() {
        et_no = findView(R.id.et_no);
        tv_query = findView(R.id.tv_query);
        lv_delivery = findView(R.id.lv_delivery);
        sp_delivery = findView(R.id.sp_delivery);
        //初始化权限
        requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void initListener() {
        setOnClick(tv_query);
        sp_delivery.setOnItemSelectedListener(this);
    }

    @Override
    public void initData() {
        //初始化选项
        initSpring();
    }

    private void initSpring() {
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, company);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_delivery.setAdapter(adapter);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_query:
                no = et_no.getText().toString().trim();
                no = "3989850237220";
                queryDeliveryAndInitData(com, no);
                break;
        }
    }

    /**
     * 查询快递和初始化快递数据
     *
     * @param com
     * @param no
     */
    private void queryDeliveryAndInitData(String com, String no) {
        showToast( "正在查询");
        Parameters parameters = new Parameters();
        parameters.add("com", com);
        parameters.add("no", no);

        Log.e("ss", com);
        Log.e("ss", no);
        JuheData.executeWithAPI(this, 43, "http://v.juhe.cn/exp/index", JuheData.GET, parameters, new DataCallBack() {
            @Override
            public void onSuccess(int i, String s) {

                Log.e("ss", s);
//                String[] result = GsonUtils.parseDelivery(DeliveryActivity.this, s);
//                if (result != null) {
//                    lv_delivery.setAdapter(new ArrayAdapter<String>(DeliveryActivity.this, R.layout.adapter_delivery, R.id.tv_delivery, result));
//                } else {
//                    "未查询到结果"
//                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {
                Log.e("ss", s);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        com = companyNum[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
