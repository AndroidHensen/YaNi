package com.handsome.didi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/9/19.
 */

public class ShopsOrder implements Parcelable {

    public List<Shop> shopList;
    public Order order;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.shopList);
        dest.writeParcelable(this.order, flags);
    }

    public ShopsOrder() {
    }

    protected ShopsOrder(Parcel in) {
        this.shopList = in.createTypedArrayList(Shop.CREATOR);
        this.order = in.readParcelable(Order.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShopsOrder> CREATOR = new Parcelable.Creator<ShopsOrder>() {
        @Override
        public ShopsOrder createFromParcel(Parcel source) {
            return new ShopsOrder(source);
        }

        @Override
        public ShopsOrder[] newArray(int size) {
            return new ShopsOrder[size];
        }
    };

    @Override
    public String toString() {
        return "ShopsOrder{" +
                "shopList=" + shopList +
                ", order=" + order +
                '}';
    }
}
