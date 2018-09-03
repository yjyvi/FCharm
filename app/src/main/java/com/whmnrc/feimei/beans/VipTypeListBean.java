package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/20.
 */

public class VipTypeListBean {


    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"PayType_ID":"55eebed8-cff5-4039-af5c-14fd542ad524","Name":"包一个月","Money":15,"Value":1,"Type":0,"Sort":0},{"PayType_ID":"8e388fb9-01c8-459c-9cf5-38509c9fdc6b","Name":"包三个月","Money":60,"Value":3,"Type":0,"Sort":1},{"PayType_ID":"250c4105-6de1-4478-ba6a-93fa3dde4b6f","Name":"包年","Money":199,"Value":12,"Type":0,"Sort":2}]
     */

    private int type;
    private int code;
    private String message;
    private List<ResultdataBean> resultdata;

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

    public List<ResultdataBean> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<ResultdataBean> resultdata) {
        this.resultdata = resultdata;
    }

    public static class ResultdataBean {
        /**
         * PayType_ID : 55eebed8-cff5-4039-af5c-14fd542ad524
         * Name : 包一个月
         * Money : 15
         * Value : 1
         * Type : 0
         * Sort : 0
         */

        private String PayType_ID;
        private String Name;
        private double Money;
        private int Value;
        private int Type;
        private int Sort;

        public String getPayType_ID() {
            return PayType_ID;
        }

        public void setPayType_ID(String PayType_ID) {
            this.PayType_ID = PayType_ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public int getValue() {
            return Value;
        }

        public void setValue(int Value) {
            this.Value = Value;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }
    }
}
