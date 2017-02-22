package com.handsome.didi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by handsome on 2016/4/8.
 */
public class Shop extends BmobObject implements Parcelable {

    private String name;
    //打折价
    private String price;
    //打折价+删除线
    private String price_discount;
    //邮费
    private String postage;
    //月销
    private int sell_num;
    //猜你喜欢展示图
    private String image_url;
    //商品地址
    private String address;
    //商品详细图
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    //商品服务保障
    private String service;
    //店铺id
    private String S_OID;

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

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl1() {
        return this.url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return this.url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return this.url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return this.url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
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
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeString(this.price_discount);
        dest.writeString(this.postage);
        dest.writeInt(this.sell_num);
        dest.writeString(this.image_url);
        dest.writeString(this.address);
        dest.writeString(this.url1);
        dest.writeString(this.url2);
        dest.writeString(this.url3);
        dest.writeString(this.url4);
        dest.writeString(this.service);
        dest.writeString(this.S_OID);
        dest.writeString(this.getObjectId());
    }

    public Shop() {
    }

    protected Shop(Parcel in) {
        this.name = in.readString();
        this.price = in.readString();
        this.price_discount = in.readString();
        this.postage = in.readString();
        this.sell_num = in.readInt();
        this.image_url = in.readString();
        this.address = in.readString();
        this.url1 = in.readString();
        this.url2 = in.readString();
        this.url3 = in.readString();
        this.url4 = in.readString();
        this.service = in.readString();
        this.S_OID = in.readString();
        setObjectId(in.readString());
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

    @Override
    public String toString() {
        return "Shop{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", price_discount='" + price_discount + '\'' +
                ", postage='" + postage + '\'' +
                ", sell_num=" + sell_num +
                ", image_url='" + image_url + '\'' +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", url4='" + url4 + '\'' +
                ", service='" + service + '\'' +
                ", S_OID='" + S_OID + '\'' +
                '}';
    }
}
