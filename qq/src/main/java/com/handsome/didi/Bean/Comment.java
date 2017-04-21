package com.handsome.didi.Bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/6.
 */
public class Comment extends BmobObject {

    //评论内容
    private String content;
    //评论用户名
    private String username;
    //评论图片
    private List<String> img_urls;
    //商品id
    private String S_OID;
    //商家回复
    private String reply;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getS_OID() {
        return S_OID;
    }

    public void setS_OID(String s_OID) {
        S_OID = s_OID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
