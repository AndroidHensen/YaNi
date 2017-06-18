package com.handsome.didi.Adapter.Home;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.DensityUtils;
import com.handsome.didi.Utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CommentAdapter extends BaseAdapter implements View.OnClickListener {

    private ActivityController activityController;

    private Context context;
    private List<Comment> commentList;
    private LayoutInflater mInflater;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        mInflater = LayoutInflater.from(context);
        activityController = ActivityController.getInstance();
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_comment, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Comment comment = commentList.get(position);
        holder.tv_comment_content.setText(comment.content);
        holder.tv_comment_date.setText(comment.getCreatedAt());
        holder.tv_user_name.setText(comment.username);
        //动态创建评论插图
        if (comment.img_urls != null) {
            holder.ly_comment.setVisibility(View.VISIBLE);
            createImageView(comment.img_urls, holder.ly_comment);
            holder.ly_comment.setTag(comment);
            holder.ly_comment.setOnClickListener(this);
        } else {
            holder.ly_comment.setVisibility(View.GONE);
        }
        //掌柜回复
        if (!TextUtils.isEmpty(comment.reply)) {
            holder.tv_comment_reply.setVisibility(View.VISIBLE);
            holder.tv_comment_reply.setText("掌柜回复：" + comment.reply);
        } else {
            holder.tv_comment_reply.setVisibility(View.GONE);
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
        //大图模式
        Comment comment = (Comment) v.getTag();
        if (comment.img_urls != null) {
            activityController.startPhotoViewActivityWithImage(context, comment);
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_user_name, tv_comment_content, tv_comment_date, tv_comment_reply;
        private LinearLayout ly_comment;

        ViewHolder(View view) {
            tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            tv_comment_content = (TextView) view.findViewById(R.id.tv_comment_content);
            tv_comment_date = (TextView) view.findViewById(R.id.tv_comment_date);
            tv_comment_reply = (TextView) view.findViewById(R.id.tv_comment_reply);
            ly_comment = (LinearLayout) view.findViewById(R.id.ly_comment);
        }
    }

    /**
     * 动态创建评论插图
     *
     * @param image_urls
     * @param ly
     */
    private void createImageView(List<String> image_urls, LinearLayout ly) {
        //清空
        ly.removeAllViews();
        //区分单图和多图的情况
        ViewGroup.LayoutParams params;
        if (image_urls.size() == 1) {
            params = new ViewGroup.LayoutParams(DensityUtils.dip2px(context, 200), DensityUtils.dip2px(context, 200));
        } else if (image_urls.size() == 2) {
            params = new ViewGroup.LayoutParams(DensityUtils.dip2px(context, 150), DensityUtils.dip2px(context, 150));
        } else {
            params = new ViewGroup.LayoutParams(DensityUtils.dip2px(context, 100), DensityUtils.dip2px(context, 100));
        }
        //动态添加
        for (int i = 0; i < image_urls.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.displayImage(context, image_urls.get(i), imageView);
            ly.addView(imageView);
        }
    }

}
