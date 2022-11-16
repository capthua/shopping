package com.shopping.common.business;

public class OrderUtils {

    public static String genOrderNo() {
        return Consts.APP_ABBR + System.currentTimeMillis();
    }

}
