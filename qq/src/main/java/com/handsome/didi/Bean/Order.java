package com.handsome.didi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobACL;
import cn.bmob.v3.BmobObject;

/**
 * @author 许英俊 2017/4/22
 */
public class Order extends BmobObject implements Parcelable {

    //用户id
    public String U_OID;
    //商品id
    public String S_OID;
    //订单状态
    public int state;
    //店铺名
    public String store_name;
    //订单号
    public String order_number;

    //地址相关
    public String realname;
    public String address;
    public String phone;
    //快递号
    public String express_number;
    //快递类型
    public String express_type;
    //快递日期
    public String express_date;
    //支付方式
    public String pay_way;

    //发票类型
    public String bill_type;
    //抬头
    public String bill_title;
    //发票内容
    public String bill_message;

    //邮费
    public String postage;
    //总价
    public String sum_money;


    public interface STATE {
        //全部
        int STATE_ALL = -0x01;
        //待付款
        int STATE_PAY = 0x00;
        //待发货
        int STATE_SEND = 0x01;
        //待收货
        int STATE_GET = 0x02;
        //待评价
        int STATE_WAIT = 0x03;
        //已完成
        int STATE_COMPLETE = 0x04;
    }


    public Order() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.U_OID);
        dest.writeString(this.S_OID);
        dest.writeInt(this.state);
        dest.writeString(this.store_name);
        dest.writeString(this.order_number);
        dest.writeString(this.realname);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.express_number);
        dest.writeString(this.express_type);
        dest.writeString(this.express_date);
        dest.writeString(this.pay_way);
        dest.writeString(this.bill_type);
        dest.writeString(this.bill_title);
        dest.writeString(this.bill_message);
        dest.writeString(this.postage);
        dest.writeString(this.sum_money);
        dest.writeString(this.getObjectId());
        dest.writeString(this.getCreatedAt());
    }

    protected Order(Parcel in) {
        this.U_OID = in.readString();
        this.S_OID = in.readString();
        this.state = in.readInt();
        this.store_name = in.readString();
        this.order_number = in.readString();
        this.realname = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.express_number = in.readString();
        this.express_type = in.readString();
        this.express_date = in.readString();
        this.pay_way = in.readString();
        this.bill_type = in.readString();
        this.bill_title = in.readString();
        this.bill_message = in.readString();
        this.postage = in.readString();
        this.sum_money = in.readString();
        this.setObjectId(in.readString());
        this.setCreatedAt(in.readString());
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
