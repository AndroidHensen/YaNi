package com.handsome.didi.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/6.
 */
public class Comment extends BmobObject implements Parcelable {

    //评论内容
    public String content;
    //评论用户名
    public String username;
    //评论图片
    public List<String> img_urls;
    //商品id
    public String S_OID;
    //商家回复
    public String reply;

    public Comment() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.username);
        dest.writeStringList(this.img_urls);
        dest.writeString(this.S_OID);
        dest.writeString(this.reply);
        dest.writeString(this.getCreatedAt());
    }

    protected Comment(Parcel in) {
        this.content = in.readString();
        this.username = in.readString();
        this.img_urls = in.createStringArrayList();
        this.S_OID = in.readString();
        this.reply = in.readString();
        this.setCreatedAt(in.readString());
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
