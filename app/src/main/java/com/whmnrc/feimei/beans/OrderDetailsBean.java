package com.whmnrc.feimei.beans;

/**
 * @author yjyvi
 * @data 2018/8/21.
 */

public class OrderDetailsBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"ID":"e1d08b85-9c0e-4e98-ab2e-90dd5657b433","OrderNo":"201808201953551381600001296","CreateTime":"1534766035","AddressName":"看风景大家","AddressMobile":"13554542559","AddressProvice":"","AddressCity":"","AddressRegion":"","AddressDetail":"","PayType":2,"State":0,"Money":8.9,"OrderType":5,"PayTime":"0","Img":"","Name":"光通讯跳槽那些事儿20180210.mp4","SubTitle":"视频","Type":4,"Number":1,"OtherID":"cabbe016-4475-45b8-8275-3131001b4723","UnitPrice":8.9,"RefundableTime":"0"}
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
         * ID : e1d08b85-9c0e-4e98-ab2e-90dd5657b433
         * OrderNo : 201808201953551381600001296
         * CreateTime : 1534766035
         * AddressName : 看风景大家
         * AddressMobile : 13554542559
         * AddressProvice :
         * AddressCity :
         * AddressRegion :
         * AddressDetail :
         * PayType : 2
         * State : 0
         * Money : 8.9
         * OrderType : 5
         * PayTime : 0
         * Img :
         * Name : 光通讯跳槽那些事儿20180210.mp4
         * SubTitle : 视频
         * Type : 4
         * Number : 1
         * OtherID : cabbe016-4475-45b8-8275-3131001b4723
         * UnitPrice : 8.9
         * RefundableTime : 0
         */

        private String ID;
        private String OrderNo;
        private String CreateTime;
        private String AddressName;
        private String AddressMobile;
        private String AddressProvice;
        private String AddressCity;
        private String AddressRegion;
        private String AddressDetail;
        private int PayType;
        private int State;
        private double Money;
        private int OrderType;
        private String PayTime;
        private String Img;
        private String Name;
        private String SubTitle;
        private int Type;
        private int Number;
        private String OtherID;
        private double UnitPrice;
        private String RefundableTime;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getAddressName() {
            return AddressName;
        }

        public void setAddressName(String AddressName) {
            this.AddressName = AddressName;
        }

        public String getAddressMobile() {
            return AddressMobile;
        }

        public void setAddressMobile(String AddressMobile) {
            this.AddressMobile = AddressMobile;
        }

        public String getAddressProvice() {
            return AddressProvice;
        }

        public void setAddressProvice(String AddressProvice) {
            this.AddressProvice = AddressProvice;
        }

        public String getAddressCity() {
            return AddressCity;
        }

        public void setAddressCity(String AddressCity) {
            this.AddressCity = AddressCity;
        }

        public String getAddressRegion() {
            return AddressRegion;
        }

        public void setAddressRegion(String AddressRegion) {
            this.AddressRegion = AddressRegion;
        }

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String AddressDetail) {
            this.AddressDetail = AddressDetail;
        }

        public int getPayType() {
            return PayType;
        }

        public void setPayType(int PayType) {
            this.PayType = PayType;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public int getOrderType() {
            return OrderType;
        }

        public void setOrderType(int OrderType) {
            this.OrderType = OrderType;
        }

        public String getPayTime() {
            return PayTime;
        }

        public void setPayTime(String PayTime) {
            this.PayTime = PayTime;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getSubTitle() {
            return SubTitle;
        }

        public void setSubTitle(String SubTitle) {
            this.SubTitle = SubTitle;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public String getOtherID() {
            return OtherID;
        }

        public void setOtherID(String OtherID) {
            this.OtherID = OtherID;
        }

        public double getUnitPrice() {
            return UnitPrice;
        }

        public void setUnitPrice(double UnitPrice) {
            this.UnitPrice = UnitPrice;
        }

        public String getRefundableTime() {
            return RefundableTime;
        }

        public void setRefundableTime(String RefundableTime) {
            this.RefundableTime = RefundableTime;
        }
    }
}
