package com.handsome.didi.Adapter.Home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Recharge;
import com.handsome.didi.R;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class RechargeAdapter extends BaseAdapter {

    private List<Recharge> list;
    private LayoutInflater mInflater;
    private int selectPosition = -1;

    public RechargeAdapter(Context context, List<Recharge> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.adapter_recharge, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Recharge recharge = list.get(position);
        holder.tv_money.setText(recharge.money + "元");
        holder.tv_discount_money.setText("售价" + recharge.discount_money + "元");
        if (selectPosition == position) {
            holder.tv_money.setTextColor(Color.parseColor("#323232"));
            holder.tv_discount_money.setTextColor(Color.parseColor("#323232"));
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

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_money, tv_discount_money;

        ViewHolder(View view) {
            tv_money = (TextView) view.findViewById(R.id.tv_money);
            tv_discount_money = (TextView) view.findViewById(R.id.tv_discount_money);
        }
    }

    /**
     * 设置选中位置
     *
     * @param selectPosition
     */
    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }
}
