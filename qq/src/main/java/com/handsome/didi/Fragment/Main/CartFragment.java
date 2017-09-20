package com.handsome.didi.Fragment.Main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by handsome on 2016/4/7.
 */
public class CartFragment extends BaseFragment {

    private ShopController shopController;
    private UserController userController;
    private ActivityController activityController;
    //购物车数据
    private LinearLayout ly_cart_bg;
    private ListView lv_cart;
    private CartAdapter adapter;
    private List<Shop> shopList;
    //底部按钮
    private TextView tv_buy, tv_delete, tv_sum_money;

    //订单信息
    private ShopsOrder shopsOrder;
    private ArrayList<ShopsOrder> shopsOrderList;
    //拆分订单信息
    private HashMap<String, List<Shop>> orders;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initViews() {
        ly_cart_bg = findView(R.id.ly_cart_bg);
        lv_cart = findView(R.id.lv_cart);
        tv_buy = findView(R.id.tv_buy);
        tv_delete = findView(R.id.tv_delete);
        tv_sum_money = findView(R.id.tv_sum_money);
    }

    @Override
    public void initData() {
        userController = UserController.getInstance();
        shopController = ShopController.getInstance();
        activityController = ActivityController.getInstance();
        //初始化购物车数据
        initCartViews();
    }

    @Override
    public void initListener() {
        setOnClick(tv_buy);
        setOnClick(tv_delete);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                deleteUserCart();
                break;
            case R.id.tv_buy:
                buy();
                break;
        }
    }

    /**
     * 初始化购物车数据
     */
    private void initCartViews() {
        //初始化价钱
        tv_sum_money.setText(0.0f + "");
        //获取购物车oid
        List<String> cartOid = userController.getCartOid();
        //查询
        shopController.query(cartOid, new ShopController.OnBmobListener() {
            @Override
            public void onSuccess(List<?> list) {
                shopList = (List<Shop>) list;
                ly_cart_bg.setVisibility(View.GONE);
                lv_cart.setVisibility(View.VISIBLE);
                adapter = new CartAdapter(getActivity(), shopList);
                adapter.setTextView(tv_sum_money);
                adapter.setEdit(true);
                lv_cart.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                ly_cart_bg.setVisibility(View.VISIBLE);
                lv_cart.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 删除购物车商品
     */
    private void deleteUserCart() {
        if (adapter != null) {
            userController.deleteUserCart(adapter.getSelected_objectId(), new UserController.onBmobUserListener() {
                @Override
                public void onError(String error) {
                    showToast(error);
                }

                @Override
                public void onSuccess(String success) {
                    showToast(success);
                    //更新UI
                    initCartViews();
                }

                @Override
                public void onLoading(String loading) {

                }
            });
        }
    }

    /**
     * 立即购买，拆分订单
     */
    private void buy() {
        if (adapter.getSelected_objectId().isEmpty()) {
            showToast("请选择要购买的物品");
        } else {

            orders = new HashMap<>();
            shopsOrderList = new ArrayList<>();

            //拆分订单
            List<Integer> positions = adapter.getSelected_position();
            for (Integer position : positions) {
                Shop shop = shopList.get(position);
                String S_OID = shop.S_OID;

                if (orders.containsKey(S_OID)) {
                    List<Shop> shops = orders.get(S_OID);
                    shops.add(shop);
                } else {
                    List<Shop> shops = new ArrayList<>();
                    shops.add(shop);
                    orders.put(S_OID, shops);
                }
            }

            //取出所有订单
            Iterator iter = orders.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                List<Shop> val = (List<Shop>) entry.getValue();
                //增加订单
                shopsOrder = new ShopsOrder();
                shopsOrder.order = null;
                shopsOrder.shopList = val;
                shopsOrderList.add(shopsOrder);
            }

            activityController.startConfirmOrderActivityWithShop(getActivity(), shopsOrderList);
        }
    }
}
