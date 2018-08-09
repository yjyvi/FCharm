package com.whmnrc.feimei.beans;

/**
 * Created by yong hao zeng on 2018/1/19.
 */

public class WxPayBean {


    /**
     * type : 1
     * code : 0
     * message : 成功
     * resultdata : {"WeCharPay":{"success":true,"data":{"appId":"wx40ca58b47b16d576","partnerId":"1370418702","prepayId":"wx201801191708437cf978470a0484936954","nonceStr":"8c235f89a8143a28a1d6067e959dd858","timeStamp":"1516352923","packageValue":"Sign=WXPay","sign":"19B21EED750E073456DD2C2E67FFEDCE"}}}
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
         * WeCharPay : {"success":true,"data":{"appId":"wx40ca58b47b16d576","partnerId":"1370418702","prepayId":"wx201801191708437cf978470a0484936954","nonceStr":"8c235f89a8143a28a1d6067e959dd858","timeStamp":"1516352923","packageValue":"Sign=WXPay","sign":"19B21EED750E073456DD2C2E67FFEDCE"}}
         */

        private WeCharPayBean WeCharPay;

        public WeCharPayBean getWeCharPay() {
            return WeCharPay;
        }

        public void setWeCharPay(WeCharPayBean WeCharPay) {
            this.WeCharPay = WeCharPay;
        }

        public static class WeCharPayBean {
            /**
             * success : true
             * data : {"appId":"wx40ca58b47b16d576","partnerId":"1370418702","prepayId":"wx201801191708437cf978470a0484936954","nonceStr":"8c235f89a8143a28a1d6067e959dd858","timeStamp":"1516352923","packageValue":"Sign=WXPay","sign":"19B21EED750E073456DD2C2E67FFEDCE"}
             */

            private boolean success;
            private DataBean data;

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * appId : wx40ca58b47b16d576
                 * partnerId : 1370418702
                 * prepayId : wx201801191708437cf978470a0484936954
                 * nonceStr : 8c235f89a8143a28a1d6067e959dd858
                 * timeStamp : 1516352923
                 * packageValue : Sign=WXPay
                 * sign : 19B21EED750E073456DD2C2E67FFEDCE
                 */

                private String appId;
                private String partnerId;
                private String prepayId;
                private String nonceStr;
                private String timeStamp;
                private String packageValue;
                private String sign;

                public String getAppId() {
                    return appId;
                }

                public void setAppId(String appId) {
                    this.appId = appId;
                }

                public String getPartnerId() {
                    return partnerId;
                }

                public void setPartnerId(String partnerId) {
                    this.partnerId = partnerId;
                }

                public String getPrepayId() {
                    return prepayId;
                }

                public void setPrepayId(String prepayId) {
                    this.prepayId = prepayId;
                }

                public String getNonceStr() {
                    return nonceStr;
                }

                public void setNonceStr(String nonceStr) {
                    this.nonceStr = nonceStr;
                }

                public String getTimeStamp() {
                    return timeStamp;
                }

                public void setTimeStamp(String timeStamp) {
                    this.timeStamp = timeStamp;
                }

                public String getPackageValue() {
                    return packageValue;
                }

                public void setPackageValue(String packageValue) {
                    this.packageValue = packageValue;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
