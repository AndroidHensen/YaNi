package com.handsome.didi.Bean;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by handsome on 2016/4/19.
 */
public class User extends BmobUser {
    //性别
    public Boolean sex;
    //年龄
    public int age;
    //等级
    public int rate;
    //我的关注商品id
    public List<String> love_oid;
    //购物车商品id
    public List<String> cart_oid;
    //收藏店铺id
    public List<String> collection_oid;
    //我的卡券id
    public List<String> card_oid;
}
