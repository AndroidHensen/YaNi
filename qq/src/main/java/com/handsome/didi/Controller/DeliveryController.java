package com.handsome.didi.Controller;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Delivery;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class DeliveryController extends BaseController {

    public static volatile DeliveryController deliveryController;

    public static DeliveryController getInstance() {
        if (deliveryController == null) {
            synchronized (DeliveryController.class) {
                if (deliveryController == null) {
                    deliveryController = new DeliveryController();
                }
            }
        }
        return deliveryController;
    }


    /**
     * 快递接口
     */
    public interface onDeliveryListener {
        void onSuccess(Delivery delivery);

        void onError(String error);
    }
}
