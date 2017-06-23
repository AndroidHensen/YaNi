package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/16.
 */
public class Recharge extends BmobObject {
    //充值金额
    public double money;
    //充值打折金额
    public double discount_money;
    //充值类型,0-话费充值  1-游戏充值
    public int type;

    public interface TYPE {
        int TYPE_PHONE = 0x00;
        int TYPE_GAME = 0x01;
    }
}
