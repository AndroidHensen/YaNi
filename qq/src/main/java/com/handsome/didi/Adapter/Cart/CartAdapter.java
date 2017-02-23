package com.handsome.didi.Adapter.Cart;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsome.didi.Bean.Shop;
import com.handsome.didi.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CartAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Shop> list;
    private BitmapUtils bitmapUtils;
    private LayoutInflater mInflater;
    //选中集合
    private List<Integer> selected_Id;
    //计算价格
    private double sum_money = 0;

    public double getSum_money() {
        return sum_money;
    }

    public List<Integer> getSelected_Id() {
        return selected_Id;
    }

    public CartAdapter(Context context, List<Shop> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
        selected_Id = new ArrayList<>();
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
        bitmapUtils.display(holder.iv_shop, shop.getImage_url());
        holder.tv_name.setText(shop.getName());
        holder.tv_price.setText(shop.getPrice() + "");
        holder.tv_postage.setText("快递:" + shop.getPostage());
        holder.tv_price_discount.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_price_discount.setText(shop.getPrice_discount() + "");
        holder.tv_sell_num.setText("月售" + shop.getSell_num() + "笔");
        holder.iv_check.setTag(position);
        holder.iv_check.setOnClickListener(this);
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
            case R.id.iv_check:
                int position = (int) v.getTag();
                if (selected_Id.contains((Integer) position)) {
                    selected_Id.remove((Integer) position);
                    v.setBackgroundResource(R.drawable.cart_mid_ic_check_off);
                } else {
                    selected_Id.add((Integer) position);
                    v.setBackgroundResource(R.drawable.cart_mid_ic_check_on);
                }
                break;
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_name, tv_price, tv_price_discount, tv_sell_num, tv_postage;
        private ImageView iv_shop, iv_check;

        ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_price_discount = (TextView) view.findViewById(R.id.tv_price_discount);
            tv_sell_num = (TextView) view.findViewById(R.id.tv_sell_num);
            tv_postage = (TextView) view.findViewById(R.id.tv_postage);
            iv_shop = (ImageView) view.findViewById(R.id.iv_shop);
            iv_check = (ImageView) view.findViewById(R.id.iv_check);
        }
    }

    /**
     * 选择商品，计算总价格
     *
     * @param position
     * @return
     */
//    public void selectSingle(int position) {
//        Shop shop = list.get(position);
//        //创建BigDecimal对象
//        BigDecimal bj1 = new BigDecimal(Double.toString(sum_money));
//        BigDecimal bj2 = new BigDecimal(shop.getPrice());
//        if (selected_Id.contains(shop.getId())) {
//            sum_money = bj1.subtract(bj2).doubleValue();
//            selected_Id.remove((Long) shop.getId());
//        } else {
//            sum_money = bj1.add(bj2).doubleValue();
//            selected_Id.add((int) shop.getId());
//        }
//        notifyDataSetChanged();
//    }

}
