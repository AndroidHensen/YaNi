package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class Store extends BmobObject {
    //关注人数
    public int love_num;
    //全部宝贝
    public int all_shop;
    //店名
    public String name;
    //店标
    public String img_url;
    //店等级、1-15、一个爱心到五个皇冠
    public int rate;
    //宝贝评分
    public double shop_grade;
    //卖家服务
    public double store_grade;
    //物流服务
    public double delivery_grade;

}
