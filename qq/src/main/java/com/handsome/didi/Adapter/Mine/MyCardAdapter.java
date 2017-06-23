package com.handsome.didi.Adapter.Mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
public class MyCardAdapter extends BaseAdapter {


    private List<Card> list;
    private LayoutInflater mInflater;
    private Context context;

    public MyCardAdapter(Context context, List<Card> list) {
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
            convertView = mInflater.inflate(R.layout.adapter_my_card, null);
        }
        ViewHolder holder = getViewHolder(convertView);
        Card card = list.get(position);
        holder.tv_card_money.setText(card.card_money + "");
        holder.tv_card_date.setText(card.getCreatedAt() + "~" + card.endTime);
        switch (card.type) {
            case Card.TYPE.TYPE_COMMON:
                holder.tv_card_name.setText("专场券 " + card.store_name);
                break;
            case Card.TYPE.TYPE_SPECIA:
                holder.tv_card_name.setText("特卖专场券 " + card.store_name);
                break;
            case Card.TYPE.TYPE_NEW:
                holder.tv_card_name.setText("新专场券 " + card.store_name);
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
        private TextView tv_card_name, tv_card_money, tv_card_date;

        ViewHolder(View view) {
            tv_card_name = (TextView) view.findViewById(R.id.tv_card_name);
            tv_card_money = (TextView) view.findViewById(R.id.tv_card_money);
            tv_card_date = (TextView) view.findViewById(R.id.tv_card_date);
        }
    }

}
