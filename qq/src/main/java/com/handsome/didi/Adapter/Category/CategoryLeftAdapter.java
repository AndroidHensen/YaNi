package com.handsome.didi.Adapter.Category;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.R;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CategoryLeftAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater mInflater;
    public int clickPosition = 0;

    public CategoryLeftAdapter(Context context, List<String> list) {
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
            convertView = mInflater.inflate(R.layout.adapter_category_left, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        if (clickPosition == position) {
            holder.tv_categroy_left.setTextColor(Color.RED);
            holder.ly_categroy_left.setBackgroundResource(R.color.colorWhite);
        } else {
            holder.tv_categroy_left.setTextColor(Color.BLACK);
            holder.ly_categroy_left.setBackgroundResource(R.color.colorPrimaryBg);
        }
        holder.tv_categroy_left.setText(list.get(position));
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
        private TextView tv_categroy_left;
        private LinearLayout ly_categroy_left;

        ViewHolder(View view) {
            tv_categroy_left = (TextView) view.findViewById(R.id.tv_categroy_left);
            ly_categroy_left = (LinearLayout) view.findViewById(R.id.ly_category_left);
        }
    }

    /**
     * 设置点击位置
     *
     * @param clickPosition
     */
    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
    }

    /**
     * 获取点击位置
     *
     * @return
     */
    public int getClickPosition() {
        return clickPosition;
    }
}
