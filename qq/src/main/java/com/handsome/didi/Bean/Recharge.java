package com.handsome.didi.Bean;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/16.
 */
public class Recharge {
    private long id;
    private double money;
    private double discount_money;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDiscount_money() {
        return discount_money;
    }

    public void setDiscount_money(double discount_money) {
        this.discount_money = discount_money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
