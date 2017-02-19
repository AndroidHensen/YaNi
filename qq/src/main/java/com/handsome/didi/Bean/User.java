package com.handsome.didi.Bean;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by handsome on 2016/4/19.
 */
public class User extends BmobObject {

    private long id;
    private String name;
    private String password;
    private Boolean sex;
    private int age;
    private int rate;
    private List<Integer> love_id;
    private List<Integer> cart_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getCart_id() {
        return cart_id;
    }

    public void setCart_id(List<Integer> cart_id) {
        this.cart_id = cart_id;
    }

    public List<Integer> getLove_id() {
        return love_id;
    }

    public void setLove_id(List<Integer> love_id) {
        this.love_id = love_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
