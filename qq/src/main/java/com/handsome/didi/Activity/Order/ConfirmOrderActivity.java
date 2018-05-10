package com.handsome.didi.Activity.Order;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
    private ArrayList<ShopsOrder> shopsOrderList;

    private String username;
    private Address address;

    private Order order;

    //商店信息
    private List<Store> storeList;
    private List<String> store_oid;

    //所有订单
    private ArrayList<Order> orderList;
    //订单金额
    private double maxPostage;
    private double sum_money;
    private double real_money;
    //计数器
    private static volatile int count;

    private static String[] EXPRESS_TYPE = {"雅妮快递", "京东快递", "淘宝快递"};
    private static String[] EXPRESS_DATA = {"工作日、双休日与假日", "工作日", "双休日与假日"};

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
                showSingleDialog(EXPRESS_TYPE, tv_express_type);
                break;
            case R.id.ly_express_date:
                //打开快递日期页面
                showSingleDialog(EXPRESS_DATA, tv_express_date);
                break;
            case R.id.ly_bill:
                //打开发票信息页面
                showEditTextDialog(tv_bill_title, tv_bill_type, tv_bill_message);
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
        //归零
        count = 0;
        //获取商品数据
        shopsOrderList = getIntent().getParcelableArrayListExtra("shopsOrderList");

        shopList = new ArrayList<>();
        store_oid = new ArrayList<>();
        for (ShopsOrder shopsOrder : shopsOrderList) {
            //添加商品条目
            shopList.addAll(shopsOrder.shopList);
            //添加商品店铺信息
            store_oid.add(shopsOrder.shopList.get(0).S_OID);
        }
        itemAdapter = new OrderItemAdapter(this, shopList);
        lv_order_item.setAdapter(itemAdapter);

        //获取店铺数据
        initStoreDate(store_oid);
        username = userController.getUsername();

        setAddressView();

        for (ShopsOrder shopsOrder : shopsOrderList) {
            //计算邮费，每一个订单中商品的最高价邮费的总和
            maxPostage += calculatePostage(shopsOrder.shopList);
            //计算商品的总价格
            sum_money += calculateSumMoney(shopsOrder.shopList);
        }
        //计算实付价格=总价格+邮费
        real_money = CalculateUtils.Sum(maxPostage + "", sum_money + "");

        tv_postage_money.setText("￥" + maxPostage);
        tv_money.setText("￥" + sum_money);
        tv_real_sum_money.setText("￥" + real_money);

        tv_express_type.setText("雅妮快递");
        tv_express_date.setText("工作日、双休日与假日");
        tv_bill_title.setText("个人");
        tv_bill_type.setText("电子发票");
        tv_bill_message.setText("明细");
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
     * 计算订单中同一个店铺所有商品的邮费
     *
     * @param shopList
     * @return
     */
    private double calculatePostage(List<Shop> shopList) {
        double postage = 0;
        for (Shop shop : shopList) {
            double shopPostage = Double.parseDouble(shop.postage);
            if (postage < shopPostage) {
                postage = shopPostage;
            }
        }
        return postage;
    }

    /**
     * 计算订单中同一个店铺所有商品的总价格
     *
     * @param shopList
     * @return
     */
    private double calculateSumMoney(List<Shop> shopList) {
        double sum_money = 0;
        for (Shop shop : shopList) {
            sum_money = CalculateUtils.Sum(shop.price, sum_money);
        }
        return sum_money;
    }

    /**
     * 获取店铺数据
     */
    private void initStoreDate(List<String> store_oid) {
        storeController.query(store_oid, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                storeList = (List<Store>) list;
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

        if (storeList == null) {
            showToast("请稍等，正在初始化");
            return;
        }

        //存储每个订单的信息
        orderList = new ArrayList<>();

        for (ShopsOrder shopsOrder : shopsOrderList) {
            order = new Order();
            order.state = Order.STATE.STATE_PAY;
            //添加商品ID

            List<String> S_OID = new ArrayList<>();
            for (Shop shop : shopsOrder.shopList) {
                S_OID.add(shop.getObjectId());
            }
            order.S_OID = S_OID;

            order.postage = calculatePostage(shopsOrder.shopList) + "";
            //价格=总价钱+邮费
            order.sum_money = CalculateUtils.Sum(calculateSumMoney(shopsOrder.shopList), order.postage) + "";

            //区分不同订单的店铺名字
            for (Store store : storeList) {
                if (store.getObjectId().equals(shopsOrder.shopList.get(0).S_OID)) {
                    order.store_name = store.name;
                }
            }

            //去掉个人-电子发票-明细的横杆
            order.bill_title = tv_bill_title.getText().toString();
            order.bill_type = tv_bill_type.getText().toString();
            order.bill_message = tv_bill_message.getText().toString();
            //其他信息
            order.express_number = orderController.getOrderNumber();
            order.express_type = tv_express_type.getText().toString();
            order.express_date = tv_express_date.getText().toString();
            order.realname = tv_realname.getText().toString();
            order.address = tv_address.getText().toString();
            order.phone = tv_phone.getText().toString();
            order.order_number = orderController.getOrderNumber();
            order.U_OID = userController.getUserOid();

            orderList.add(order);
        }

        //一条一条添加缓存订单，由于有连接失败的情况需要重连，所以需要缓存
        for (Order order_cache : orderList) {
            insertOrder(order_cache, orderList);
        }
    }


    /**
     * 插入一条订单数据到后台数据库
     */
    public void insertOrder(final Order order, final ArrayList<Order> orderList) {
        orderController.insert(order, new BaseController.OnBmobCommonListener() {
            @Override
            public void onSuccess(String success) {
                showToast(success);
                //计数器
                count++;

                if (count == orderList.size()) {
                    //跳转页面
                    activityController.startPayActivityWithOrder(ConfirmOrderActivity.this, orderList);
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 单选选择框
     */
    private void showSingleDialog(final String[] items, final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //items[which]被选中
                textView.setText(items[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 电子发票信息输入框对话框
     */
    public void showEditTextDialog(final TextView tv_bill_title, final TextView tv_bill_type, final TextView tv_bill_content) {
        LayoutInflater factory = LayoutInflater.from(this);
        View view = factory.inflate(R.layout.view_dialog_edittext, null);

        final EditText et_bill_title = (EditText) view.findViewById(R.id.et_bill_title);
        final EditText et_bill_type = (EditText) view.findViewById(R.id.et_bill_type);
        final EditText et_bill_content = (EditText) view.findViewById(R.id.et_bill_content);

        et_bill_title.setText(tv_bill_title.getText());
        et_bill_type.setText(tv_bill_type.getText());
        et_bill_content.setText(tv_bill_content.getText());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请填写");
        builder.setView(view);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                if (et_bill_title.getText().toString().trim().isEmpty() ||
                        et_bill_type.getText().toString().trim().isEmpty() ||
                        et_bill_content.getText().toString().trim().isEmpty()) {
                    showToast("输入的内容不能空");
                    return;
                }

                tv_bill_title.setText(et_bill_title.getText());
                tv_bill_type.setText(et_bill_type.getText());
                tv_bill_content.setText(et_bill_content.getText());
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
