package com.handsome.didi.Adapter.Cart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.lidroid.xutils.BitmapUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CartAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Shop> list;
    private BitmapUtils bitmapUtils;
    private LayoutInflater mInflater;
    private Context context;
    //选中集合
    private List<String> selected_objectId;
    //计算价格
    private double sum_money = 0;
    //价格文本
    private TextView tv_sum_money;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv_sum_money.setText(sum_money + "");
        }
    };

    public CartAdapter(Context context, List<Shop> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
        selected_objectId = new ArrayList<>();
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
            convertView = mInflater.inflate(R.layout.adapter_cart, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Shop shop = list.get(position);
        bitmapUtils.display(holder.iv_shop, shop.getUrl1());
        holder.tv_name.setText(shop.getName());
        holder.tv_price.setText(shop.getPrice() + "");
        holder.tv_postage.setText("快递:" + shop.getPostage());
        holder.tv_price_discount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_price_discount.setText(shop.getPrice_discount() + "");
        holder.tv_sell_num.setText("月售" + shop.getSell_num() + "笔");
        holder.iv_check.setTag(position);
        holder.iv_check.setOnClickListener(this);
        holder.ly_shop.setTag(position);
        holder.ly_shop.setOnClickListener(this);
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
            case R.id.iv_check:
                checkAndSum(position, v);
                break;
            case R.id.ly_shop:
                startDetailActivity(position);
                break;
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_name, tv_price, tv_price_discount, tv_sell_num, tv_postage;
        private ImageView iv_shop, iv_check;
        private LinearLayout ly_shop;

        ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_price_discount = (TextView) view.findViewById(R.id.tv_price_discount);
            tv_sell_num = (TextView) view.findViewById(R.id.tv_sell_num);
            tv_postage = (TextView) view.findViewById(R.id.tv_postage);
            iv_shop = (ImageView) view.findViewById(R.id.iv_shop);
            iv_check = (ImageView) view.findViewById(R.id.iv_check);
            ly_shop = (LinearLayout) view.findViewById(R.id.ly_shop);
        }
    }

    /**
     * 选择商品，计算总价格
     *
     * @param position
     * @return
     */
    public void checkAndSum(int position, View v) {
        //获取数据
        Shop shop = list.get(position);
        String objectId = shop.getObjectId();
        //创建BigDecimal对象
        BigDecimal bj1 = new BigDecimal(Double.toString(sum_money));
        BigDecimal bj2 = new BigDecimal(shop.getPrice());
        if (selected_objectId.contains(objectId)) {
            sum_money = bj1.subtract(bj2).doubleValue();
            selected_objectId.remove(objectId);
            v.setBackgroundResource(R.drawable.cart_mid_ic_check_off);
        } else {
            sum_money = bj1.add(bj2).doubleValue();
            selected_objectId.add(objectId);
            v.setBackgroundResource(R.drawable.cart_mid_ic_check_on);
        }
        //更新UI
        mHandler.sendEmptyMessage(0);
    }

    /**
     * 打开详情页面
     *
     * @param position
     */
    public void startDetailActivity(int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("shop", list.get(position));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 获取选中的ObjectId
     *
     * @return
     */
    public List<String> getSelected_objectId() {
        return selected_objectId;
    }

    /**
     * 设置价格文本
     *
     * @param tv_sum_money
     */
    public void setTextView(TextView tv_sum_money) {
        this.tv_sum_money = tv_sum_money;
    }

}
