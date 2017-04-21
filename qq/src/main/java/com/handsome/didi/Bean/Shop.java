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
    private String name;
    //打折价
    private String price;
    //打折价+删除线
    private String price_discount;
    //邮费
    private String postage;
    //月销
    private int sell_num;
    //商品地址
    private String address;
    //商品详细图
    private List<String> show_urls;
    //商品服务保障
    private String service;
    //店铺id
    private String S_OID;
    //详细信息图片
    private List<String> detail_urls;

    public List<String> getShow_urls() {
        return show_urls;
    }

    public void setShow_urls(List<String> show_urls) {
        this.show_urls = show_urls;
    }

    public List<String> getDetail_urls() {
        return detail_urls;
    }

    public void setDetail_urls(List<String> detail_urls) {
        this.detail_urls = detail_urls;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_discount() {
        return this.price_discount;
    }

    public void setPrice_discount(String price_discount) {
        this.price_discount = price_discount;
    }

    public String getPostage() {
        return this.postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public int getSell_num() {
        return this.sell_num;
    }

    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getS_OID() {
        return S_OID;
    }

    public void setS_OID(String s_OID) {
        S_OID = s_OID;
    }

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
