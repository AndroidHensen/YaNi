package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by handsome on 2016/4/14.
 */
public class Find extends BmobObject {

    //店铺ID
    public String S_OID;
    //用户发表内容
    public String user_post;
    //用户名
    public String user_name;
    //用户发表主题
    public String user_theme;
    //发表展示图
    public String user_pic_url_1;
    public String user_pic_url_2;
    public String user_pic_url_3;
    //用户发表时间
    public String date;
    //用户浏览
    public int user_scan;
    //发现类型，0-用户发现 1-编辑推荐
    public int type;
    //编辑推荐大标题
    public String tv_big_title_1;
    public String tv_big_title_2;
    public String tv_big_title_3;
    //编辑推荐小标题
    public String tv_small_title_1;
    public String tv_small_title_2;
    public String tv_small_title_3;
}
