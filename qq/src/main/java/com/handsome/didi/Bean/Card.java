package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * @author 许英俊 2017/6/22
 */
public class Card extends BmobObject {

    //对应的商店ID，用于购买物品时识别
    public String S_OID;
    //卡券价格
    public int card_money;
    //商店名字
    public String store_name;
    //商店图标
    public String img_url;
    //截止时间
    public String endTime;
    //专场类型，0-专场 1-特卖专场 2-新专场
    public int type;

    public interface TYPE {
        int TYPE_COMMON = 0x00;
        int TYPE_SPECIA = 0x01;
        int TYPE_NEW = 0x02;
    }

}
