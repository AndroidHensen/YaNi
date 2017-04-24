package com.handsome.didi.Bean;

import cn.bmob.v3.BmobObject;

/**
 * @author 许英俊 2017/4/22
 */
public class Order extends BmobObject {

    //用户id
    public String U_OID;
    //商品id
    public String S_OID;
    //订单状态
    public int state;
    //店铺名
    public String store_name;

    public interface STATE {
        //全部
        int STATE_ALL = -0x01;
        //待付款
        int STATE_PAY = 0x00;
        //待发货
        int STATE_SEND = 0x01;
        //待收货
        int STATE_GET = 0x02;
        //待评价
        int STATE_WAIT = 0x03;
        //已完成
        int STATE_COMPLETE = 0x04;
    }


}
