package com.handsome.didi.Activity.Common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

public class ConfirmOrderActivity extends BaseActivity {

    private AddressController addressController;
    private UserController userController;

    private TextView tv_realname, tv_phone, tv_address, tv_name,
            tv_price, tv_postage, tv_sell_num, tv_pay_way, tv_express_type, tv_express_date, tv_bill,
            tv_money, tv_postage_money, tv_real_sum_money;
    private ImageView iv_shop;

    private String username;
    private Address address;
    private Shop shop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initViews() {
        tv_realname = findView(R.id.tv_realname);
        tv_phone = findView(R.id.tv_phone);
        tv_address = findView(R.id.tv_address);
        tv_name = findView(R.id.tv_name);
        tv_price = findView(R.id.tv_price);
        tv_postage = findView(R.id.tv_postage);
        tv_sell_num = findView(R.id.tv_sell_num);
        tv_pay_way = findView(R.id.tv_pay_way);
        tv_express_type = findView(R.id.tv_express_type);
        tv_express_date = findView(R.id.tv_express_date);
        tv_bill = findView(R.id.tv_bill);
        tv_money = findView(R.id.tv_money);
        tv_postage_money = findView(R.id.tv_postage_money);
        tv_real_sum_money = findView(R.id.tv_real_sum_money);
        iv_shop = findView(R.id.iv_shop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setTitle("确认订单");
        setTitleCanBack();

        addressController = AddressController.getInstance();
        userController = UserController.getInstance();

        initConfirmOrderViews();
    }

    @Override
    public void processClick(View v) {

    }


    /**
     * 初始化确认订单页面
     */
    private void initConfirmOrderViews() {
        shop = getIntent().getParcelableExtra("shop");
        username = userController.getUsername();
        address = addressController.queryDefaultAddress(username);
        if (address != null) {
            tv_realname.setText(address.realname);
            tv_phone.setText(address.phone);
            tv_address.setText(address.area + address.street + address.address);
        }
        GlideUtils.displayImage(this, shop.show_urls.get(0), iv_shop);
        tv_name.setText(shop.name);
        tv_price.setText(shop.price);
        tv_postage.setText("快递：" + shop.postage);
        tv_sell_num.setText("月售" + shop.sell_num + "笔");
    }
}
