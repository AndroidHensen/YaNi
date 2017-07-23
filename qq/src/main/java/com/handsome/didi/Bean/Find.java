package com.handsome.didi.Bean;

import java.util.List;

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
    public List<String> user_pic_url;
    //用户浏览
    public int user_scan;
    //发现类型，0-用户发现 1-编辑推荐
    public int type;

    //发现类型
    public interface TYPE {
        int TYPE_USER = 0x00;
        int TYPE_EDIT = 0x01;
    }

    //编辑推荐大标题
    public List<String> big_title;
    //编辑推荐小标题
    public List<String> small_title;
}
