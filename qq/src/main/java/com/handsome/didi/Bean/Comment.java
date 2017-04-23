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
    public String content;
    //评论用户名
    public String username;
    //评论图片
    public List<String> img_urls;
    //商品id
    public String S_OID;
    //商家回复
    public String reply;
}
