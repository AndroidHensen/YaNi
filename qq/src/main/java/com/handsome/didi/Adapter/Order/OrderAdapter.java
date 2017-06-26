package com.handsome.didi.Adapter.Order;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class OrderAdapter extends BaseAdapter implements View.OnClickListener {

    private ActivityController activityController;

    private List<Shop> shopList;
    private List<Order> orderList;
    private LayoutInflater mInflater;
    private Context context;

    //选中条目
    private int position;

    public OrderAdapter(Context context, List<Shop> shopList, List<Order> orderList) {
        this.shopList = shopList;
        this.orderList = orderList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        activityController = ActivityController.getInstance();
        //数据库字段排序，让商品和订单对应起来
        Collections.sort(shopList, new Comparator<Shop>() {
            @Override
            public int compare(Shop lhs, Shop rhs) {
                return lhs.getObjectId().compareTo(rhs.getObjectId());
            }
        });
        Collections.sort(orderList, new Comparator<Order>() {
            @Override
            public int compare(Order lhs, Order rhs) {
                return lhs.S_OID.compareTo(rhs.S_OID);
            }
        });
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
            convertView = mInflater.inflate(R.layout.adapter_order, parent, false);
        }
        ViewHolder holder = getViewHolder(convertView);
        Shop shop = shopList.get(position);
        Order order = orderList.get(position);
        //填充数据
        GlideUtils.displayImage(context, shop.show_urls.get(0), holder.iv_shop);
        holder.tv_name.setText(shop.name);
        holder.tv_postage.setText("快递：" + shop.postage);
        holder.tv_price.setText("￥" + shop.price);
        holder.tv_sell_num.setText("月售" + shop.sell_num + "笔");
        holder.tv_store_name.setText(order.store_name);
        holder.tv_sum_money.setText("￥" + order.sum_money);
        holder.ly_store.setOnClickListener(this);
        holder.ly_store.setTag(shop.S_OID);
        holder.ly_order.setOnClickListener(this);
        holder.ly_order.setTag(position);
        holder.tv_order.setOnClickListener(this);
        holder.tv_order.setTag(position);
        switch (order.state) {
            case Order.STATE.STATE_GET:
                holder.tv_state.setText("卖家已发货");
                holder.tv_order.setText("确认收货");
                holder.tv_state.setTextColor(Color.parseColor("#333333"));
                holder.tv_order.setVisibility(View.VISIBLE);
                break;
            case Order.STATE.STATE_PAY:
                holder.tv_state.setText("您还未支付");
                holder.tv_order.setText("马上付款");
                holder.tv_state.setTextColor(Color.parseColor("#333333"));
                holder.tv_order.setVisibility(View.VISIBLE);
                break;
            case Order.STATE.STATE_SEND:
                holder.tv_state.setText("卖家未发货");
                holder.tv_order.setText("提醒发货");
                holder.tv_state.setTextColor(Color.parseColor("#333333"));
                holder.tv_order.setVisibility(View.VISIBLE);
                break;
            case Order.STATE.STATE_WAIT:
                holder.tv_state.setText("买家已收货");
                holder.tv_order.setText("马上评价");
                holder.tv_state.setTextColor(Color.parseColor("#333333"));
                holder.tv_order.setVisibility(View.VISIBLE);
                break;
            case Order.STATE.STATE_COMPLETE:
                holder.tv_state.setText("交易成功");
                holder.tv_state.setTextColor(Color.RED);
                holder.tv_order.setVisibility(View.INVISIBLE);
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
        switch (v.getId()) {
            case R.id.ly_store:
                String S_OID = (String) v.getTag();
                activityController.startStoreActivityWithStoreId(context, S_OID);
                break;
            case R.id.ly_order:
                position = (int) v.getTag();
                activityController.startOrderDetailActivityWithStoreAndOrder(context, shopList.get(position), orderList.get(position));
                break;
            case R.id.tv_order:
                position = (int) v.getTag();
                //代付款则进入付款页面
                if (orderList.get(position).state == Order.STATE.STATE_PAY) {
                    activityController.startPayActivityWithOrder(context, orderList.get(position));
                } else if (orderList.get(position).state == Order.STATE.STATE_WAIT) {
                    //评价页面
                }
                break;
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {

        private TextView tv_name, tv_sum_money, tv_price, tv_postage, tv_state, tv_order, tv_sell_num, tv_store_name;
        private ImageView iv_shop;
        private LinearLayout ly_store, ly_order;

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
            ly_order = (LinearLayout) view.findViewById(R.id.ly_order);
        }
    }

}
