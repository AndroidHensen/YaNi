package com.handsome.didi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Adapter.Common.CommonShopListAdapter;
import com.handsome.didi.Base.BaseFragment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.Utils.ToastUtils;

import java.util.List;

/**
 * Created by handsome on 2016/4/7.
 */
public class CartFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    //中
    private LinearLayout ly_cart_bg;
    private ListView lv_cart;
    private List<Shop> cartList;
    private CommonShopListAdapter adapter;
    //头
    private TextView tv_header_edit;
    private boolean isEdit;
    //底部
    private RelativeLayout ly_bottom_cart;
    private TextView tv_buy, tv_delete, tv_sum_money;

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, null);
        ly_cart_bg = (LinearLayout) view.findViewById(R.id.ly_cart_bg);
        lv_cart = (ListView) view.findViewById(R.id.lv_cart);
        tv_header_edit = (TextView) view.findViewById(R.id.tv_header_edit);
        ly_bottom_cart = (RelativeLayout) view.findViewById(R.id.ly_bottom_cart);
        tv_buy = (TextView) view.findViewById(R.id.tv_buy);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_sum_money = (TextView) view.findViewById(R.id.tv_sum_money);
        return view;
    }

    @Override
    public void initData() {
        //初始化购物车数据
//        initCartData();
    }

    @Override
    public void initListener() {
        lv_cart.setOnItemClickListener(this);
        tv_header_edit.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_header_edit:
                //编辑
                if (isEdit) {
                    closeEditMenu();
                } else {
                    showEditMenu();
                }
                break;
            case R.id.tv_delete:
                //删除
                deleteCart();
                break;
            case R.id.tv_buy:
                //结算
                ToastUtils.showToast(getActivity(),"该功能暂未开启");
                break;
        }
    }

    /**
     * 初始化购物车数据
     */
    private void initCartData() {
//        cartList = CartDao.queryCart(getActivity().getContentResolver());
        if (cartList.size() > 0) {
            ly_cart_bg.setVisibility(View.GONE);
            lv_cart.setVisibility(View.VISIBLE);
            tv_header_edit.setVisibility(View.VISIBLE);
            adapter = new CommonShopListAdapter(getActivity(), cartList);
            lv_cart.setAdapter(adapter);
        } else {
            ly_cart_bg.setVisibility(View.VISIBLE);
            lv_cart.setVisibility(View.GONE);
            tv_header_edit.setVisibility(View.GONE);
        }
    }

    /**
     * 关闭编辑菜单
     */
    private void closeEditMenu() {
        //关闭结算板
        tv_header_edit.setText("编辑");
        ly_bottom_cart.setVisibility(View.GONE);
        //关闭编辑模式
        adapter.closeEditMode();
        isEdit = false;
    }

    /**
     * 打开编辑菜单
     */
    private void showEditMenu() {
        //显示结算板
        tv_header_edit.setText("取消");
        ly_bottom_cart.setVisibility(View.VISIBLE);
        //打开编辑模式
        adapter.openEditMode();
        isEdit = true;
        //显示结算价格
        tv_sum_money.setText("0");
    }

    /**
     * 删除购物车数据
     */
    private void deleteCart() {
        List<Integer> list = adapter.getSelected_Id();
        if (list.size() > 0) {
            for (Integer _id : list) {
//                CartDao.deleteCart(getActivity().getContentResolver(), _id);
            }
            //初始化购物车数据
            initCartData();
            //关闭菜单
            closeEditMenu();
        } else {
            ToastUtils.showToast(getActivity(), "请选择商品");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isEdit) {
            //选择条目
            adapter.selectSingle(position);
            //获得计算后的价格
            tv_sum_money.setText(adapter.getSum_money()+"");
        } else {
            //进入详情页
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            startActivity(intent);
        }
    }

}
