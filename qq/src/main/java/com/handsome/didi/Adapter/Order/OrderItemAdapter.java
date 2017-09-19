package com.handsome.didi.Adapter.Order;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class OrderItemAdapter extends BaseAdapter implements View.OnClickListener {

    private ActivityController activityController;

    private List<Shop> list;
    private LayoutInflater mInflater;
    private Context context;
    //是否点击去到订单详情页面
    private Boolean isItemClick;

    public void setItemClick(Boolean itemClick) {
        isItemClick = itemClick;
    }

    public OrderItemAdapter(Context context, List<Shop> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        isItemClick = true;
        activityController = ActivityController.getInstance();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_order_item, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Shop shop = list.get(position);
        GlideUtils.displayImage(context, shop.show_urls.get(0), holder.iv_shop);
        holder.tv_name.setText(shop.name);
        holder.tv_price.setText("￥" + shop.price);
        holder.tv_postage.setText("快递：" + shop.postage);
        holder.tv_sell_num.setText("月售" + shop.sell_num + "笔");
        if (isItemClick) {
            holder.ly_shop.setTag(position);
            holder.ly_shop.setOnClickListener(this);
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
        int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.ly_shop:
                activityController.startDetailActivityWithShop(context, list.get(position));
                break;
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_name, tv_price, tv_sell_num, tv_postage;
        private ImageView iv_shop;
        private LinearLayout ly_shop;

        ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_sell_num = (TextView) view.findViewById(R.id.tv_sell_num);
            tv_postage = (TextView) view.findViewById(R.id.tv_postage);
            iv_shop = (ImageView) view.findViewById(R.id.iv_shop);
            ly_shop = (LinearLayout) view.findViewById(R.id.ly_shop);
        }
    }

}
