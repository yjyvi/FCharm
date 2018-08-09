package com.whmnrc.feimei.utils;


/**
 * 显示字符串占位工具类
 *
 * @author yjyvi
 * @data 2018/3/3.
 */

public class PlaceholderUtils {

    private static void initCurrency() {
//        String currency = SPUtils.getString(MyApplication.applicationContext, CommonConstant.Common.CURRENT_CURRENCY);
//        if (!TextUtils.isEmpty(currency)) {
//            sCurrencyPrice = Double.parseDouble(currency);
//        }
//        sCode = SPUtils.getString(MyApplication.applicationContext, CommonConstant.Common.CURRENT_CURRENCY_CODE);
//        if (TextUtils.isEmpty(sCode)) {
//            sCode = "$";
//        }
    }

    private static double sCurrencyPrice = 1.0;
    private static String sCode;

    /**
     * 商品价格占位
     *
     * @param money
     * @return
     */
    public static String pricePlaceholder(double money) {

        initCurrency();

        if (money <= 0.0) {
            money = 0.0;
        }
        return String.format("%s%2.2f", sCode, money * sCurrencyPrice);
    }
}
