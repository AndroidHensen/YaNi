package com.handsome.didi.Adapter.Find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Find;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class FindAdapter extends BaseAdapter {

    private List<Find> list;
    private Context context;
    private LayoutInflater mInflater;

    public FindAdapter(Context context, List<Find> list) {
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_find, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Find find = list.get(position);
        if (find.type == 0) {
            holder.ly_find_edit.setVisibility(View.GONE);
            holder.ly_find_post.setVisibility(View.VISIBLE);
            holder.tv_user_theme.setText(find.user_theme);
            holder.tv_user_name.setText(find.user_name);
            holder.tv_user_scan.setText(find.user_scan + "人浏览");
            holder.tv_user_post.setText(find.user_post);
            GlideUtils.displayImage(context, find.user_pic_url_1, holder.iv_find_1);
            GlideUtils.displayImage(context, find.user_pic_url_2, holder.iv_find_2);
            GlideUtils.displayImage(context, find.user_pic_url_3, holder.iv_find_3);
        } else if (find.type == 1) {
            holder.ly_find_edit.setVisibility(View.VISIBLE);
            holder.ly_find_post.setVisibility(View.GONE);
            holder.tv_big_title_1.setText(find.tv_big_title_1);
            holder.tv_big_title_2.setText(find.tv_big_title_2);
            holder.tv_big_title_3.setText(find.tv_big_title_3);
            holder.tv_small_title_1.setText(find.tv_small_title_1);
            holder.tv_small_title_2.setText(find.tv_small_title_2);
            holder.tv_small_title_3.setText(find.tv_small_title_3);
            GlideUtils.displayImage(context, find.user_pic_url_1, holder.iv_find_4);
            GlideUtils.displayImage(context, find.user_pic_url_2, holder.iv_find_5);
            GlideUtils.displayImage(context, find.user_pic_url_3, holder.iv_find_6);
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
        private TextView tv_user_name, tv_user_theme, tv_user_post, tv_user_scan;
        private ImageView iv_find_1, iv_find_2, iv_find_3, iv_find_4, iv_find_5, iv_find_6;
        private LinearLayout ly_find_post, ly_find_edit;
        //编辑推荐
        private TextView tv_big_title_1, tv_big_title_2, tv_big_title_3;
        private TextView tv_small_title_1, tv_small_title_2, tv_small_title_3;

        ViewHolder(View view) {
            tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            tv_user_theme = (TextView) view.findViewById(R.id.tv_user_theme);
            tv_user_scan = (TextView) view.findViewById(R.id.tv_user_scan);
            tv_user_post = (TextView) view.findViewById(R.id.tv_user_post);
            iv_find_1 = (ImageView) view.findViewById(R.id.iv_find_1);
            iv_find_2 = (ImageView) view.findViewById(R.id.iv_find_2);
            iv_find_3 = (ImageView) view.findViewById(R.id.iv_find_3);
            iv_find_4 = (ImageView) view.findViewById(R.id.iv_find_4);
            iv_find_5 = (ImageView) view.findViewById(R.id.iv_find_5);
            iv_find_6 = (ImageView) view.findViewById(R.id.iv_find_6);
            ly_find_post = (LinearLayout) view.findViewById(R.id.ly_find_post);
            ly_find_edit = (LinearLayout) view.findViewById(R.id.ly_find_edit);

            tv_big_title_1 = (TextView) view.findViewById(R.id.tv_big_title_1);
            tv_big_title_2 = (TextView) view.findViewById(R.id.tv_big_title_2);
            tv_big_title_3 = (TextView) view.findViewById(R.id.tv_big_title_3);
            tv_small_title_1 = (TextView) view.findViewById(R.id.tv_small_title_1);
            tv_small_title_2 = (TextView) view.findViewById(R.id.tv_small_title_2);
            tv_small_title_3 = (TextView) view.findViewById(R.id.tv_small_title_3);
        }
    }
}
