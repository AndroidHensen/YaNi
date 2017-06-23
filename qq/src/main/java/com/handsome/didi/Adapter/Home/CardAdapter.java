package com.handsome.didi.Adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Card;
import com.handsome.didi.Controller.UserController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;
import com.handsome.didi.Utils.ToastUtils;

import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class CardAdapter extends BaseAdapter implements View.OnClickListener {

    private UserController userController;

    private List<Card> list;
    private LayoutInflater mInflater;
    private Context context;

    public CardAdapter(Context context, List<Card> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        userController = UserController.getInstance();
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
            convertView = mInflater.inflate(R.layout.adapter_card, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Card card = list.get(position);
        holder.tv_card_money.setText(card.card_money + "");
        holder.tv_get_card.setTag(position);
        holder.tv_get_card.setOnClickListener(this);
        GlideUtils.displayImage(context, card.img_url, holder.iv_card);

        if (userController.getCardOid().contains(card.getObjectId())) {
            holder.tv_get_card.setText("已经领取");
            holder.tv_get_card.setBackgroundResource(R.drawable.common_bg_red_white_50_12x6);
        } else {
            holder.tv_get_card.setText("立即领取");
            holder.tv_get_card.setBackgroundResource(R.drawable.common_bg_red_white_12x6);
        }

        switch (card.type) {
            case Card.TYPE.TYPE_COMMON:
                holder.tv_card_name.setText(card.store_name + "专场");
                break;
            case Card.TYPE.TYPE_SPECIA:
                holder.tv_card_name.setText(card.store_name + "特卖专场");
                break;
            case Card.TYPE.TYPE_NEW:
                holder.tv_card_name.setText(card.store_name + "新专场");
                break;
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
        private TextView tv_card_name, tv_card_money, tv_get_card;
        private ImageView iv_card;

        ViewHolder(View view) {
            tv_card_name = (TextView) view.findViewById(R.id.tv_card_name);
            tv_card_money = (TextView) view.findViewById(R.id.tv_card_money);
            tv_get_card = (TextView) view.findViewById(R.id.tv_get_card);
            iv_card = (ImageView) view.findViewById(R.id.iv_card);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_card:
                int position = (int) v.getTag();
                String S_OID = list.get(position).getObjectId();
                addUserCard(S_OID);
                break;
        }
    }

    /**
     * 添加用户卡券
     *
     * @param S_OID
     */
    private void addUserCard(String S_OID) {
        userController.addUserCard(S_OID, new BaseController.onBmobUserListener() {
            @Override
            public void onSuccess(String success) {
                ToastUtils.showToast(context, success);
                notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                ToastUtils.showToast(context, error);
            }

            @Override
            public void onLoading(String loading) {

            }
        });
    }
}
