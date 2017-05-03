package com.handsome.didi.Activity.Common;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.math.BigDecimal;
import java.util.List;

public class ConfirmOrderActivity extends BaseActivity {

    private AddressController addressController;
    private UserController userController;
    private StoreController storeController;
    private OrderController orderController;
    private ActivityController activityController;

    private TextView tv_realname, tv_phone, tv_address, tv_name, tv_buy,
            tv_price, tv_postage, tv_sell_num, tv_pay_way, tv_express_type, tv_express_date, tv_bill_title,
            tv_bill_message, tv_bill_type, tv_money, tv_postage_money, tv_real_sum_money;
    private ImageView iv_shop;

    private String username;
    private Address address;
    private Shop shop;
    private Order order;
    private String store_name;
    private List<Store> storeList;

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
        tv_buy = findView(R.id.tv_buy);
        tv_sell_num = findView(R.id.tv_sell_num);
        tv_pay_way = findView(R.id.tv_pay_way);
        tv_express_type = findView(R.id.tv_express_type);
        tv_express_date = findView(R.id.tv_express_date);
        tv_bill_title = findView(R.id.tv_bill_title);
        tv_bill_message = findView(R.id.tv_bill_message);
        tv_bill_type = findView(R.id.tv_bill_type);
        tv_money = findView(R.id.tv_money);
        tv_postage_money = findView(R.id.tv_postage_money);
        tv_real_sum_money = findView(R.id.tv_real_sum_money);
        iv_shop = findView(R.id.iv_shop);
    }

    @Override
    public void initListener() {
        setOnClick(tv_buy);
    }

    @Override
    public void initData() {
        setTitle("确认订单");
        setTitleCanBack();

        addressController = AddressController.getInstance();
        userController = UserController.getInstance();
        storeController = StoreController.getInstance();
        orderController = OrderController.getInstance();
        activityController = ActivityController.getInstance();

        initConfirmOrderViews();
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                //打开支付页面
                startPayActivity();
                break;
        }
    }

    /**
     * 初始化确认订单页面
     */
    private void initConfirmOrderViews() {
        //获取商品数据
        shop = getIntent().getParcelableExtra("shop");
        //获取店铺数据
        initStoreDate();
        username = userController.getUsername();
        address = addressController.queryDefaultAddress(username);
        if (address != null) {
            tv_realname.setText(address.realname);
            tv_phone.setText(address.phone);
            tv_address.setText(address.area + address.street + address.address);
        }
        GlideUtils.displayImage(this, shop.show_urls.get(0), iv_shop);
        tv_name.setText(shop.name);
        tv_price.setText("￥" + shop.price);
        tv_money.setText("￥" + shop.price);
        tv_postage.setText("快递：" + shop.postage);
        tv_postage_money.setText("￥" + shop.postage);
        tv_sell_num.setText("月售" + shop.sell_num + "笔");
        tv_real_sum_money.setText("￥" + Sum(shop.price, shop.postage));
        //TODO:测试阶段
        tv_pay_way.setText("在线支付");
        tv_express_type.setText("雅妮快递");
        tv_express_date.setText("工作日、双休日与假日均可送货");
        tv_bill_title.setText("个人");
        tv_bill_type.setText("-" + "电子发票");
        tv_bill_message.setText("-" + "明细");
    }

    /**
     * 获取店铺数据
     */
    private void initStoreDate() {
        storeController.query(shop.S_OID, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                storeList = (List<Store>) list;
                store_name = storeList.get(0).name;
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 打开支付页面
     */
    public void startPayActivity() {
        order = new Order();
        order.S_OID = shop.getObjectId();
        order.state = Order.STATE.STATE_PAY;
        order.store_name = store_name;
        //去掉横杆
        order.bill_message = tv_bill_message.getText().toString().substring(1);
        order.bill_type = tv_bill_type.getText().toString().substring(1);
        order.postage = shop.postage;
        order.sum_money = "" + Sum(shop.price, shop.postage);
        order.bill_title = tv_bill_title.getText().toString();
        order.express_number = orderController.getOrderNumber();
        order.express_type = tv_express_type.getText().toString();
        order.express_date = tv_express_date.getText().toString();
        order.realname = tv_realname.getText().toString();
        order.address = tv_address.getText().toString();
        order.phone = tv_phone.getText().toString();
        order.pay_way = tv_pay_way.getText().toString();
        order.order_number = orderController.getOrderNumber();
        order.U_OID = userController.getUserOid();
        insertOrder();
    }

    /**
     * 插入到后台数据库
     */
    public void insertOrder() {
        orderController.insert(order, new BaseController.OnBmobCommonListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
                //跳转页面
                activityController.startPayActivityWithOrder(ConfirmOrderActivity.this, order);
                finish();
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 计算价格和邮费的总计
     *
     * @param price
     * @param postage
     * @return
     */
    public double Sum(String price, String postage) {
        BigDecimal bj2 = new BigDecimal(price);
        BigDecimal bj3 = new BigDecimal(postage);
        return bj2.add(bj3).doubleValue();
    }
}
