package com.handsome.didi.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Adapter.Cart.CartAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ShopController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class CartFragment extends BaseFragment {

    ShopController shopController;
    UserController userController;
    //中
    private LinearLayout ly_cart_bg;
    private ListView lv_cart;
    private CartAdapter adapter;
    //底部
    private TextView tv_buy, tv_delete, tv_sum_money;


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
        shopController = new ShopController(getActivity());
        userController = new UserController(getActivity());
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
        if (cartOid.isEmpty()) {
            ly_cart_bg.setVisibility(View.VISIBLE);
            lv_cart.setVisibility(View.GONE);
            return;
        }
        //查询
        shopController.queryCartOrLove(cartOid, new ShopController.OnQueryListener() {
            @Override
            public void onQuery(List<Shop> list) {
                ly_cart_bg.setVisibility(View.GONE);
                lv_cart.setVisibility(View.VISIBLE);
                adapter = new CartAdapter(getActivity(), list);
                adapter.setTextView(tv_sum_money);
                lv_cart.setAdapter(adapter);
            }
        });
    }

    /**
     * 删除购物车商品
     */
    private void deleteUserCart() {
        if (adapter != null) {
            userController.deleteUserCart(adapter.getSelected_objectId(), new UserController.onCompleteListener() {
                @Override
                public void onComplete() {
                    //更新UI
                    initCartViews();
                }
            });
        }
    }

}
