package com.handsome.didi.Bean;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class Store {
    //店铺id
    private long id;
    //关注人数
    private int love_num;
    //全部宝贝
    private int all_shop;
    //店名
    private String name;
    //店标
    private String img_url;
    //店等级、1-10、一个爱心到五个砖石
    private int rate;
    //宝贝评分
    private double shop_grade;
    //卖家服务
    private double store_grade;
    //物流服务
    private double delivery_grade;

    public int getAll_shop() {
        return all_shop;
    }

    public void setAll_shop(int all_shop) {
        this.all_shop = all_shop;
    }

    public double getDelivery_grade() {
        return delivery_grade;
    }

    public void setDelivery_grade(double delivery_grade) {
        this.delivery_grade = delivery_grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLove_num() {
        return love_num;
    }

    public void setLove_num(int love_num) {
        this.love_num = love_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(double shop_grade) {
        this.shop_grade = shop_grade;
    }

    public double getStore_grade() {
        return store_grade;
    }

    public void setStore_grade(double store_grade) {
        this.store_grade = store_grade;
    }
}
