package com.handsome.didi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by handsome on 2016/4/8.
 */
public class Shop extends BmobObject implements Parcelable {

    //商品名称
    public String name;
    //打折价
    public String price;
    //打折价+删除线
    public String price_discount;
    //邮费
    public String postage;
    //月销
    public int sell_num;
    //商品地址
    public String address;
    //商品详细图
    public List<String> show_urls;
    //商品服务保障
    public String service;
    //店铺id
    public String S_OID;
    //详细信息图片
    public List<String> detail_urls;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getObjectId());
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.price_discount);
        dest.writeString(this.postage);
        dest.writeInt(this.sell_num);
        dest.writeString(this.address);
        dest.writeStringList(this.show_urls);
        dest.writeString(this.service);
        dest.writeString(this.S_OID);
        dest.writeStringList(this.detail_urls);
    }

    public Shop() {
    }

    protected Shop(Parcel in) {
        setObjectId(in.readString());
        this.name = in.readString();
        this.price = in.readString();
        this.price_discount = in.readString();
        this.postage = in.readString();
        this.sell_num = in.readInt();
        this.address = in.readString();
        this.show_urls = in.createStringArrayList();
        this.service = in.readString();
        this.S_OID = in.readString();
        this.detail_urls = in.createStringArrayList();
    }

    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        @Override
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
