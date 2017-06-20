package com.handsome.didi.Adapter.Mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Find;
import com.handsome.didi.Bean.Store;
import com.handsome.didi.Controller.ActivityController;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> implements View.OnClickListener {

    private UserController userController;
    private ActivityController activityController;

    private List<Store> list;
    private Context context;
    private LayoutInflater mInflater;

    public CollectionAdapter(Context context, List<Store> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        userController = UserController.getInstance();
        activityController = ActivityController.getInstance();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_collection, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //获取数据
        Store store = list.get(position);
        //标注位置
        holder.itemView.setTag(position);
        //填充数据
        userController.setUserRate(context, store.rate, holder.ly_store_rate);
        holder.tv_store_name.setText(store.name);
        GlideUtils.displayImage(context, store.img_url, holder.iv_store_icon);
    }

    @Override
    public void onClick(View v) {
        //进入店铺页面
        int position = (int) v.getTag();
        String S_OID = list.get(position).getObjectId();
        activityController.startStoreActivityWithStoreId(context, S_OID);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_store_name;
        private LinearLayout ly_store_rate;
        private ImageView iv_store_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_store_name = (TextView) itemView.findViewById(R.id.tv_store_name);
            ly_store_rate = (LinearLayout) itemView.findViewById(R.id.ly_store_rate);
            iv_store_icon = (ImageView) itemView.findViewById(R.id.iv_store_icon);
        }
    }
}
