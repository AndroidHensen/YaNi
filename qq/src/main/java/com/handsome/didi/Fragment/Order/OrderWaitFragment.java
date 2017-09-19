package com.handsome.didi.Fragment.Order;

import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Order.OrderAdapter;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class OrderWaitFragment extends BaseFragment {

    private OrderController orderController;
    private ShopController shopController;
    private UserController userController;

    private ListView lv_order_all;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private List<String> s_oidList;
    private List<ShopsOrder> shopsOrderList;
    private ShopsOrder shopsOrder;
    private List<Shop> shopList;
    private List<Shop> shopsOrderList_shopList;
    private String U_OID;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_all;
    }

    @Override
    public void initViews() {
        lv_order_all = findView(R.id.lv_order_all);
    }

    @Override
    public void initData() {
        orderController = OrderController.getInstance();
        shopController = ShopController.getInstance();
        userController = UserController.getInstance();

        initOrderViews();
    }

    @Override
    public void initListener() {
    }

    @Override
    public void processClick(View v) {
    }

    private void initOrderViews() {
        U_OID = userController.getUserOid();
        if (U_OID == null) {
            return;
        }

        initOrder();
    }

    /**
     * 查询订单
     */
    public void initOrder() {
        orderController.query(U_OID, Order.STATE.STATE_WAIT, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                orderList = (List<Order>) list;
                initShop(orderList);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 查询订单中的商品
     *
     * @param orderList
     */
    private void initShop(final List<Order> orderList) {
        s_oidList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            for (String s_oid : orderList.get(i).S_OID) {
                s_oidList.add(s_oid);
            }
        }
        shopController.query(s_oidList, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;

                orderShops(orderList, shopList);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }

    /**
     * 排序所有订单
     *
     * @param orderList
     * @param shopList
     */
    private void orderShops(List<Order> orderList, List<Shop> shopList) {
        shopsOrderList = new ArrayList<>();
        for (Order order : orderList) {
            List<String> s_oid = order.S_OID;

            shopsOrderList_shopList = new ArrayList<>();
            shopsOrder = new ShopsOrder();
            shopsOrder.order = order;

            for (Shop shop : shopList) {
                if (s_oid.contains(shop.getObjectId())) {
                    shopsOrderList_shopList.add(shop);
                }
            }

            shopsOrder.shopList = shopsOrderList_shopList;
            shopsOrderList.add(shopsOrder);
        }

        adapter = new OrderAdapter(getActivity(), shopsOrderList);
        lv_order_all.setAdapter(adapter);
    }

}
