package com.whmnrc.feimei.beans;

public class UserBean {


    /**
     * code : 200
     * message : 申请注册成功
     * resultdata : {"CreateTime":"1533262691","HeadImg":"","Mobile":"13554542559","Money":0,"NickName":"菲魅_600001","VIP":"1533176291"}
     * type : 1
     */

    private int code;
    private String message;
    private ResultdataBean resultdata;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ResultdataBean {
        /**
         * CreateTime : 1533262691
         * HeadImg :
         * Mobile : 13554542559
         * Money : 0.0
         * NickName : 菲魅_600001
         * VIP : 1533176291
         */

        private String ID;
        private String CreateTime;
        private String HeadImg;
        private String Mobile;
        private double Money;
        private String NickName;
        private String VIP;
        private int AllOrder;
        private int NoPayOrder;
        private int PayOrder;

        public int getAllOrder() {
            return AllOrder;
        }

        public void setAllOrder(int allOrder) {
            AllOrder = allOrder;
        }

        public int getNoPayOrder() {
            return NoPayOrder;
        }

        public void setNoPayOrder(int noPayOrder) {
            NoPayOrder = noPayOrder;
        }

        public int getPayOrder() {
            return PayOrder;
        }

        public void setPayOrder(int payOrder) {
            PayOrder = payOrder;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getHeadImg() {
            return HeadImg;
        }

        public void setHeadImg(String HeadImg) {
            this.HeadImg = HeadImg;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getVIP() {
            return VIP;
        }

        public void setVIP(String VIP) {
            this.VIP = VIP;
        }
    }
}
