package com.handsome.didi.Bean;

/**
 * Created by handsome on 2016/4/14.
 */
public class Find {

    private long id;
    //用户发表内容
    private String user_post;
    //用户名
    private String user_name;
    //用户发表主题
    private String user_theme;
    //是否已入手
    private boolean isBuy;
    //发表展示图
    private String user_pic_url_1;
    private String user_pic_url_2;
    private String user_pic_url_3;
    //用户发表时间
    private String date;
    //用户浏览
    private int user_scan;
    //发现类型，0-用户发现 1-编辑推荐
    private int type;
    //编辑推荐大标题
    private String tv_big_title_1;
    private String tv_big_title_2;
    private String tv_big_title_3;
    //编辑推荐小标题
    private String tv_small_title_1;
    private String tv_small_title_2;
    private String tv_small_title_3;

    public String getTv_big_title_1() {
        return tv_big_title_1;
    }

    public void setTv_big_title_1(String tv_big_title_1) {
        this.tv_big_title_1 = tv_big_title_1;
    }

    public String getTv_big_title_2() {
        return tv_big_title_2;
    }

    public void setTv_big_title_2(String tv_big_title_2) {
        this.tv_big_title_2 = tv_big_title_2;
    }

    public String getTv_big_title_3() {
        return tv_big_title_3;
    }

    public void setTv_big_title_3(String tv_big_title_3) {
        this.tv_big_title_3 = tv_big_title_3;
    }

    public String getTv_small_title_1() {
        return tv_small_title_1;
    }

    public void setTv_small_title_1(String tv_small_title_1) {
        this.tv_small_title_1 = tv_small_title_1;
    }

    public String getTv_small_title_2() {
        return tv_small_title_2;
    }

    public void setTv_small_title_2(String tv_small_title_2) {
        this.tv_small_title_2 = tv_small_title_2;
    }

    public String getTv_small_title_3() {
        return tv_small_title_3;
    }

    public void setTv_small_title_3(String tv_small_title_3) {
        this.tv_small_title_3 = tv_small_title_3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pic_url_1() {
        return user_pic_url_1;
    }

    public void setUser_pic_url_1(String user_pic_url_1) {
        this.user_pic_url_1 = user_pic_url_1;
    }

    public String getUser_pic_url_2() {
        return user_pic_url_2;
    }

    public void setUser_pic_url_2(String user_pic_url_2) {
        this.user_pic_url_2 = user_pic_url_2;
    }

    public String getUser_pic_url_3() {
        return user_pic_url_3;
    }

    public void setUser_pic_url_3(String user_pic_url_3) {
        this.user_pic_url_3 = user_pic_url_3;
    }

    public String getUser_post() {
        return user_post;
    }

    public void setUser_post(String user_post) {
        this.user_post = user_post;
    }

    public int getUser_scan() {
        return user_scan;
    }

    public void setUser_scan(int user_scan) {
        this.user_scan = user_scan;
    }

    public String getUser_theme() {
        return user_theme;
    }

    public void setUser_theme(String user_theme) {
        this.user_theme = user_theme;
    }

}
