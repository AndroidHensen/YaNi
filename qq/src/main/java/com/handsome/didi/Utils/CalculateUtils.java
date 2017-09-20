package com.handsome.didi.Utils;

import java.math.BigDecimal;

/**
 * @author 许英俊 2017/6/26
 */
public class CalculateUtils {

    /**
     * 计算价格和邮费的总计
     *
     * @param price
     * @param postage
     * @return
     */
    public static double Sum(String price, String postage) {
        BigDecimal bj2 = new BigDecimal(price);
        BigDecimal bj3 = new BigDecimal(postage);
        return bj2.add(bj3).doubleValue();
    }

    /**
     * 计算价格和邮费的总计
     *
     * @param price
     * @param postage
     * @return
     */
    public static double Sum(double price, double postage) {
        BigDecimal bj2 = new BigDecimal(price);
        BigDecimal bj3 = new BigDecimal(postage);
        return bj2.add(bj3).doubleValue();
    }

    /**
     * 计算价格和邮费的总计
     *
     * @param price
     * @param postage
     * @return
     */
    public static double Sum(String price, double postage) {
        BigDecimal bj2 = new BigDecimal(price);
        BigDecimal bj3 = new BigDecimal(postage);
        return bj2.add(bj3).doubleValue();
    }

    /**
     * 计算价格和邮费的总计
     *
     * @param price
     * @param postage
     * @return
     */
    public static double Sum(double price, String postage) {
        BigDecimal bj2 = new BigDecimal(price);
        BigDecimal bj3 = new BigDecimal(postage);
        return bj2.add(bj3).doubleValue();
    }
}
