package com.handsome.didi.Adapter.Order;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.ShopsOrder;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.R;
import com.handsome.didi.View.MyListView;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class OrderAdapter extends BaseAdapter implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ActivityController activityController;

    private List<ShopsOrder> shopsOrderList;
    private LayoutInflater mInflater;
    private Context context;

    public OrderAdapter(Context context, List<ShopsOrder> shopsOrderList) {
        this.shopsOrderList = shopsOrderList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        activityController = ActivityController.getInstance();
    }

    @Override
    public int getCount() {
        return shopsOrderList.size();
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
        ShopsOrder shopsOrder = shopsOrderList.get(position);
        //填充数据
        Order order = shopsOrder.order;

        List<Shop> shopList = shopsOrder.shopList;
        OrderItemAdapter itemAdapter = new OrderItemAdapter(context, shopList);
        itemAdapter.setItemClick(false);
        holder.lv_order_item.setAdapter(itemAdapter);
        holder.lv_order_item.setTag(position);
        holder.lv_order_item.setOnItemClickListener(this);

        holder.tv_store_name.setText(order.store_name);
        holder.tv_sum_money.setText("￥" + order.sum_money);
        holder.ly_store.setOnClickListener(this);
        holder.ly_store.setTag(shopList.get(0).S_OID);
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
            case R.id.tv_order:
                int position = (int) v.getTag();
                ShopsOrder shopsOrder = shopsOrderList.get(position);
                int state = shopsOrder.order.state;

                if (state == Order.STATE.STATE_PAY) {
                    //代付款-付款页面
                    activityController.startPayActivityWithOrder(context, shopsOrder.order);
                } else if (state == Order.STATE.STATE_SEND) {
                    //提醒发货-未发货

                } else if (state == Order.STATE.STATE_WAIT) {
                    //待评价-评价页面
                    activityController.startEvaluateActivityWithShop(context, shopsOrder.shopList.get(0));
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int realPosition = (int) parent.getTag();
        ShopsOrder shopsOrder = shopsOrderList.get(realPosition);
        activityController.startOrderDetailActivityWithStoreAndOrder(context, shopsOrder);
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {

        private TextView tv_state, tv_order, tv_store_name, tv_sum_money;
        private LinearLayout ly_store;
        private MyListView lv_order_item;

        ViewHolder(View view) {
            tv_state = (TextView) view.findViewById(R.id.tv_state);
            tv_order = (TextView) view.findViewById(R.id.tv_order);
            tv_store_name = (TextView) view.findViewById(R.id.tv_store_name);
            tv_sum_money = (TextView) view.findViewById(R.id.tv_sum_money);
            ly_store = (LinearLayout) view.findViewById(R.id.ly_store);
            lv_order_item = (MyListView) view.findViewById(R.id.lv_order_item);
        }
    }

}
