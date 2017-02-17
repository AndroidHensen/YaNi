package com.handsome.didi.Adapter.Home;

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

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class ServiceAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater mInflater;

    public ServiceAdapter(Context context, List<String> list) {
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
            convertView = mInflater.inflate(R.layout.adapter_servcie, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        holder.tv_service.setText(list.get(position));
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
        private TextView tv_service;

        ViewHolder(View view) {
            tv_service = (TextView) view.findViewById(R.id.tv_service);
        }
    }
}
