package com.handsome.didi.Adapter.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handsome.didi.Bean.Category;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CategoryRightAdapter extends BaseAdapter {

    private List<Category> list;
    private LayoutInflater mInflater;
    private Context context;

    public CategoryRightAdapter(Context context, List<Category> list) {
        this.list = list;
        this.context = context;
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
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.adapter_category_right, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Category category = list.get(position);
        GlideUtils.displayImage(context, category.img_url, holder.iv_category_right);
        holder.tv_categroy_right.setText(category.name);
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
        private TextView tv_categroy_right;
        private ImageView iv_category_right;

        ViewHolder(View view) {
            tv_categroy_right = (TextView) view.findViewById(R.id.tv_categroy_right);
            iv_category_right = (ImageView) view.findViewById(R.id.iv_category_right);
        }
    }

}
