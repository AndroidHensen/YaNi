package com.handsome.didi.Adapter.Mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.media.JetPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Activity.Home.DetailActivity;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class OrderAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Shop> shopList;
    private List<Order> orderList;
    private LayoutInflater mInflater;
    private Context context;

    public OrderAdapter(Context context, List<Shop> shopList, List<Order> orderList) {
        this.shopList = shopList;
        this.orderList = orderList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_order, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Shop shop = shopList.get(position);
        Order order = orderList.get(position);
        //填充数据
        GlideUtils.displayImage(context, shop.show_urls.get(0), holder.iv_shop);
        holder.tv_name.setText(shop.name);
        holder.tv_postage.setText("快递:" + shop.postage);
        holder.tv_price.setText(shop.price);
        holder.tv_sum_money.setText("￥" + Sum(shop.price, shop.postage));

        holder.tv_store_name.setText(order.store_name);
        switch (order.state) {
            case Order.STATE.STATE_GET:
                holder.tv_state.setText("卖家已发货");
                holder.tv_order.setText("确认收货");
                break;
            case Order.STATE.STATE_PAY:
                holder.tv_state.setText("您还未支付");
                holder.tv_order.setText("马上付款");
                break;
            case Order.STATE.STATE_SEND:
                holder.tv_state.setText("卖家未发货");
                holder.tv_order.setText("提醒发货");
                break;
            case Order.STATE.STATE_WAIT:
                holder.tv_state.setText("好评送金豆");
                holder.tv_order.setText("马上评价");
                break;
        }
        return convertView;
    }

    /**
     * 获得控件管理对象
     *
     * @param view
     * @return
     */
    private ViewHolder getViewHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {

        private TextView tv_name, tv_sum_money, tv_price, tv_postage, tv_state, tv_order, tv_sell_num, tv_store_name;
        private ImageView iv_shop;
        private LinearLayout ly_store;

        ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_sum_money = (TextView) view.findViewById(R.id.tv_sum_money);
            tv_postage = (TextView) view.findViewById(R.id.tv_postage);
            tv_state = (TextView) view.findViewById(R.id.tv_state);
            tv_order = (TextView) view.findViewById(R.id.tv_order);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
            tv_sell_num = (TextView) view.findViewById(R.id.tv_sell_num);
            iv_shop = (ImageView) view.findViewById(R.id.iv_shop);
            ly_store = (LinearLayout) view.findViewById(R.id.ly_store);
        }
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
