package com.handsome.didi.Activity.Order;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Mine.AddressActivity;
import com.handsome.didi.Adapter.Order.OrderItemAdapter;
import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.Fragment.Main.CartFragment;
import com.handsome.didi.R;
import com.handsome.didi.Utils.CalculateUtils;
import com.handsome.didi.Utils.GlideUtils;
import com.handsome.didi.View.MyListView;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

public class ConfirmOrderActivity extends BaseActivity {

    private AddressController addressController;
    private UserController userController;
    private StoreController storeController;
    private OrderController orderController;
    private ActivityController activityController;

    private TextView tv_realname, tv_phone, tv_address, tv_buy, tv_postage_money,
            tv_express_type, tv_express_date, tv_bill_title, tv_money, tv_real_sum_money,
            tv_bill_message, tv_bill_type;
    private LinearLayout ly_express_type, ly_express_date, ly_bill, ly_address;

    //订单商品
    private MyListView lv_order_item;
    private OrderItemAdapter itemAdapter;
    private List<Shop> shopList;

    private String username;
    private Address address;
    private ShopsOrder shopsOrder;
    private Order order;
    private String store_name;
    private List<Store> storeList;
    private List<String> S_OID;

    //订单金额
    private double maxPostage;
    private double sum_money;
    private double real_money;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initViews() {
        tv_realname = findView(R.id.tv_realname);
        tv_money = findView(R.id.tv_money);
        tv_real_sum_money = findView(R.id.tv_real_sum_money);
        tv_postage_money = findView(R.id.tv_postage_money);
        tv_phone = findView(R.id.tv_phone);
        tv_address = findView(R.id.tv_address);
        tv_buy = findView(R.id.tv_buy);
        tv_express_type = findView(R.id.tv_express_type);
        tv_express_date = findView(R.id.tv_express_date);
        tv_bill_title = findView(R.id.tv_bill_title);
        tv_bill_message = findView(R.id.tv_bill_message);
        tv_bill_type = findView(R.id.tv_bill_type);
        ly_express_type = findView(R.id.ly_express_type);
        ly_express_date = findView(R.id.ly_express_date);
        ly_bill = findView(R.id.ly_bill);
        ly_address = findView(R.id.ly_address);
        lv_order_item = findView(R.id.lv_order_item);
    }

    @Override
    public void initListener() {
        setOnClick(tv_buy);
        setOnClick(ly_express_type);
        setOnClick(ly_express_date);
        setOnClick(ly_bill);
        setOnClick(ly_address);
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
            case R.id.ly_express_type:
                //打开快递方式页面
                break;
            case R.id.ly_express_date:
                //打开快递日期页面
                break;
            case R.id.ly_bill:
                //打开发票信息页面
                break;
            case R.id.ly_address:
                showToast("请勾选默认地址");
                startActivity(AddressActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAddressView();
    }

    /**
     * 初始化确认订单页面
     */
    private void initConfirmOrderViews() {
        //获取商品数据
        shopsOrder = getIntent().getParcelableExtra("shopsOrder");
        shopList = shopsOrder.shopList;
        itemAdapter = new OrderItemAdapter(this, shopList);
        lv_order_item.setAdapter(itemAdapter);
        //获取店铺数据
        initStoreDate();
        username = userController.getUsername();

        setAddressView();

        //邮费为所有商品中的最高价
        maxPostage = 0;
        for (Shop shop : shopList) {
            double shopPostage = Double.parseDouble(shop.postage);
            if (maxPostage < shopPostage) {
                maxPostage = shopPostage;
            }
        }
        //计算商品的总价格
        sum_money = 0;
        for (Shop shop : shopList) {
            sum_money = CalculateUtils.Sum(shop.price, sum_money + "");
        }
        //实付价格=总价格+邮费
        real_money = CalculateUtils.Sum(maxPostage + "", sum_money + "");

        tv_postage_money.setText("￥" + maxPostage);
        tv_money.setText("￥" + sum_money);
        tv_real_sum_money.setText("￥" + real_money);

        //TODO:测试阶段的订单选项
        tv_express_type.setText("雅妮快递");
        tv_express_date.setText("工作日、双休日与假日均可送货");
        tv_bill_title.setText("个人");
        tv_bill_type.setText("-" + "电子发票");
        tv_bill_message.setText("-" + "明细");
    }

    /**
     * 设置地址栏目
     */
    private void setAddressView() {
        address = addressController.queryDefaultAddress(username);
        if (address != null) {
            tv_realname.setText(address.realname);
            tv_phone.setText(address.phone);
            tv_address.setText(address.area + address.street + address.address);
        }
    }

    /**
     * 获取店铺数据
     */
    private void initStoreDate() {
        storeController.query(shopList.get(0).S_OID, new BaseController.OnBmobListener() {
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

        if (address == null) {
            showToast("请选择送货地址");
            return;
        }

        //添加商品ID
        S_OID = new ArrayList<>();
        for (Shop shop : shopList) {
            S_OID.add(shop.getObjectId());
        }

        order = new Order();
        order.S_OID = S_OID;
        order.state = Order.STATE.STATE_PAY;
        order.store_name = store_name;
        //去掉个人-电子发票-明细的横杆
        order.bill_title = tv_bill_title.getText().toString();
        order.bill_type = tv_bill_type.getText().toString().substring(1);
        order.bill_message = tv_bill_message.getText().toString().substring(1);

        order.postage = maxPostage + "";
        order.sum_money = real_money + "";
        order.express_number = orderController.getOrderNumber();
        order.express_type = tv_express_type.getText().toString();
        order.express_date = tv_express_date.getText().toString();
        order.realname = tv_realname.getText().toString();
        order.address = tv_address.getText().toString();
        order.phone = tv_phone.getText().toString();
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
                //删除用户购物车
                deleteUserCart();
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
     * 删除用户购物车
     */
    private void deleteUserCart() {
        userController.deleteUserCart(S_OID, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                //更新UI
                onChangeDataInUI(CartFragment.class.getName());
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onLoading(String loading) {

            }
        });
    }

}
