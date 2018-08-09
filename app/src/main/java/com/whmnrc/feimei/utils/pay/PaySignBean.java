package com.whmnrc.feimei.utils.pay;

/**
 * Created by yong hao zeng on 2018/1/6.
 */

public class PaySignBean {

    /**
     * type : 1
     * code : 0
     * message : 成功
     * resultdata : {"Sign":"app_id=2017112400140180&method=alipay.trade.app.pay&version=1.0&charset=utf-8&notify_url=http%3a%2f%2fwww.lkyyzj.com%2fswagger%2fui%2findex%2fPay%2fPay_ZfbNotiryRecharge&sign_type=RSA2&timestamp=2018-01-06+15%3a58%3a11&biz_content=%7b%22out_trade_no%22%3a%22E1295090129644%22%2c%22total_amount%22%3a0.01%2c%22product_code%22%3a%22QUICK_MSECURITY_PAY%22%2c%22body%22%3a%22%e4%bd%99%e9%a2%9d%e5%85%85%e5%80%bc%22%2c%22subject%22%3a%22%e9%b1%bc%e5%8f%8b%e4%b9%8b%e5%ae%b6%22%7d&sign=P8bJZlq%2buRSc1KqJF51KZ27zExSK60SYubrPKaTstlaBi44OPUmv%2b1dHzM%2f7ZYi%2b2GFK87U%2fRO%2fjgGQeBl0qtOYGjyM33ZBHQbrkMhtJfXIcFEe%2bmAjntXLQssFSIgmg2DM9PHAtiDkux0Xh6haFZ6i73A45v1ZhOu8HyTD9%2b13%2fflBTos6W%2fdPlht18OCbXaF8BuPf0nbnaU7YtOxt0dg00%2fpLA55TjYxQNeJIWyyJRuvayvvyMWIgPFQiJhqf6Jb69KJ14Mt%2fjIXJCFoY17HNU0ELvstn1wAL%2fFxkv6jU%2bwrWqSJuWAkXbBPwmmnhLmwBSbhcUnH%2fdw1jVvir1%2bA%3d%3d"}
     */

    private int type;
    private int code;
    private String message;
    private ResultdataBean resultdata;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultdataBean getResultdata() {
        return resultdata;
    }

    public void setResultdata(ResultdataBean resultdata) {
        this.resultdata = resultdata;
    }

    public static class ResultdataBean {
        /**
         * Sign : app_id=2017112400140180&method=alipay.trade.app.pay&version=1.0&charset=utf-8&notify_url=http%3a%2f%2fwww.lkyyzj.com%2fswagger%2fui%2findex%2fPay%2fPay_ZfbNotiryRecharge&sign_type=RSA2&timestamp=2018-01-06+15%3a58%3a11&biz_content=%7b%22out_trade_no%22%3a%22E1295090129644%22%2c%22total_amount%22%3a0.01%2c%22product_code%22%3a%22QUICK_MSECURITY_PAY%22%2c%22body%22%3a%22%e4%bd%99%e9%a2%9d%e5%85%85%e5%80%bc%22%2c%22subject%22%3a%22%e9%b1%bc%e5%8f%8b%e4%b9%8b%e5%ae%b6%22%7d&sign=P8bJZlq%2buRSc1KqJF51KZ27zExSK60SYubrPKaTstlaBi44OPUmv%2b1dHzM%2f7ZYi%2b2GFK87U%2fRO%2fjgGQeBl0qtOYGjyM33ZBHQbrkMhtJfXIcFEe%2bmAjntXLQssFSIgmg2DM9PHAtiDkux0Xh6haFZ6i73A45v1ZhOu8HyTD9%2b13%2fflBTos6W%2fdPlht18OCbXaF8BuPf0nbnaU7YtOxt0dg00%2fpLA55TjYxQNeJIWyyJRuvayvvyMWIgPFQiJhqf6Jb69KJ14Mt%2fjIXJCFoY17HNU0ELvstn1wAL%2fFxkv6jU%2bwrWqSJuWAkXbBPwmmnhLmwBSbhcUnH%2fdw1jVvir1%2bA%3d%3d
         */

        private String Sign;

        public String getSign() {
            return Sign;
        }

        public void setSign(String Sign) {
            this.Sign = Sign;
        }
    }
}
