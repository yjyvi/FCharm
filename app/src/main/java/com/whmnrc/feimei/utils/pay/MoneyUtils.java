package com.whmnrc.feimei.utils.pay;

import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @author yjyvi
 * @data 2018/8/23.
 */

public class MoneyUtils {

    public static void showRMB(TextView textView, double money) {
        textView.setText(String.format("%s", new DecimalFormat("###.##").format(money)));
    }

    public static void showRMB(boolean isShowRmb, TextView textView, double money) {
        String show;
        if (isShowRmb) {
            show = "￥";
        } else {
            show = "";
        }

        textView.setText(String.format("%s%s", show, new DecimalFormat("###.##").format(money)));
    }
}
