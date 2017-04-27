package com.handsome.didi.Adapter.Mine;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.Order;
import com.handsome.didi.Bean.Shop;
import com.handsome.didi.Controller.AddressController;
import com.handsome.didi.Controller.StoreController;
import com.handsome.didi.R;
import com.handsome.didi.Utils.GlideUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by handsome on 2016/4/8.
 */
public class AddressAdapter extends BaseAdapter implements View.OnClickListener {

    private AddressController addressController;

    private List<Address> addressList;
    private LayoutInflater mInflater;
    private Context context;

    public AddressAdapter(Context context, List<Address> addressList) {
        this.addressList = addressList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        addressController = AddressController.getInstance();
    }

    @Override
    public int getCount() {
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_address, parent, false);
        }
        ViewHolder holder = getViewHolder(convertView);
        Address address = addressList.get(position);
        holder.tv_realname.setText(address.realname);
        holder.tv_address.setText(address.area + address.street + address.address);
        holder.tv_phone.setText(address.phone);
        holder.cb_isdefault.setOnClickListener(this);
        holder.cb_isdefault.setTag(position);
        boolean isdefault = address.isdefault;
        if (isdefault) {
            holder.cb_isdefault.setChecked(true);
            holder.tv_isdefault.setTextColor(Color.parseColor("#FF6622"));
            holder.tv_isdefault.setText("默认地址");
        } else {
            holder.cb_isdefault.setChecked(false);
            holder.tv_isdefault.setTextColor(Color.parseColor("#333333"));
            holder.tv_isdefault.setText("设为默认");
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
        switch (v.getId()) {
            case R.id.cb_isdefault:
                int position = (int) v.getTag();
                selectDefaultAddress(position);
                break;
        }
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {
        private TextView tv_realname, tv_phone, tv_address, tv_isdefault;
        private CheckBox cb_isdefault;

        ViewHolder(View view) {
            tv_realname = (TextView) view.findViewById(R.id.tv_realname);
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_isdefault = (TextView) view.findViewById(R.id.tv_isdefault);
            cb_isdefault = (CheckBox) view.findViewById(R.id.cb_isdefault);
        }
    }


    /**
     * 设置默认地址
     *
     * @param position
     */
    private void selectDefaultAddress(int position) {
        for (int i = 0; i < addressList.size(); i++) {
            Address address = addressList.get(i);
            address.isdefault = false;
            if (i == position) {
                address = addressList.get(i);
                address.isdefault = true;
            }
            //升级数据库
            addressController.update(address);
        }
        notifyDataSetChanged();
    }
}
