package com.handsome.didi.Adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Comment;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Bean.User;
import com.handsome.didi.Controller.CommentController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CommentAdapter extends BaseAdapter {

    private Context context;
    private List<Comment> commentList;
    private LayoutInflater mInflater;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        mInflater = LayoutInflater.from(context);
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
        holder.tv_comment_content.setText(comment.getContent());
        holder.tv_comment_date.setText(comment.getCreatedAt());
        holder.tv_user_name.setText(comment.getUsername());
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
        private TextView tv_user_name, tv_comment_content, tv_comment_date;

        ViewHolder(View view) {
            tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            tv_comment_content = (TextView) view.findViewById(R.id.tv_comment_content);
            tv_comment_date = (TextView) view.findViewById(R.id.tv_comment_date);
        }
    }

}
