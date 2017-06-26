package com.handsome.didi.Fragment.Order;

import android.view.View;
import android.widget.ListView;

import com.handsome.didi.Adapter.Order.OrderAdapter;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.OrderController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class OrderPayFragment extends BaseFragment {


    private OrderController orderController;
    private ShopController shopController;
    private UserController userController;

    private ListView lv_order_all;
    private OrderAdapter adapter;
    private List<Order> orderList;
    private List<Shop> shopList;
    private List<String> s_oidList;
    private String U_OID;


    @Override
    public int getLayoutId() {
        return  R.layout.fragment_order_pay;
    }

    @Override
    public void initViews() {
        lv_order_all = findView(R.id.lv_order_pay);
    }

    @Override
    public void initData() {
        orderController = OrderController.getInstance();
        shopController = ShopController.getInstance();
        userController = UserController.getInstance();

        initOrderAll();
    }

    @Override
    public void initListener() {
    }

    @Override
    public void processClick(View v) {
    }

    private void initOrderAll() {
        U_OID = userController.getUserOid();
        if (U_OID == null) {
            return;
        }

        orderController.query(U_OID, Order.STATE.STATE_PAY, new BaseController.OnBmobListener() {
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


    private void initShop(final List<Order> orderList) {
        s_oidList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            s_oidList.add(orderList.get(i).S_OID);
        }
        shopController.query(s_oidList, new BaseController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;
                adapter = new OrderAdapter(getActivity(), shopList, orderList);
                lv_order_all.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                showToast(error);
            }
        });
    }
}
