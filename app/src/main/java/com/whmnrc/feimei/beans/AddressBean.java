package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/30.
 */

public class AddressBean {


    /**
     * type : 1
     * code : 200
     * message :
     * resultdata : [{"ID":"8c6ac8f4-87e9-43c1-b0bb-1d0cc94371bc","Mobile":"17754436736","Name":"我是测试","Provice":"湖北","City":"武汉","Region":"江岸区","Detail":"某个地方的测试收货地址","CreateTime":"1534732387","IsDefault":1},{"ID":"d7b17fca-a92e-4f06-b0e0-dc90f2c9852d","Mobile":"17754436736","Name":"我是测试","Provice":"湖北","City":"武汉","Region":"江岸区","Detail":"某个地方的测试收货地址","CreateTime":"1534731711","IsDefault":0}]
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
         * ID : 8c6ac8f4-87e9-43c1-b0bb-1d0cc94371bc
         * Mobile : 17754436736
         * Name : 我是测试
         * Provice : 湖北
         * City : 武汉
         * Region : 江岸区
         * Detail : 某个地方的测试收货地址
         * CreateTime : 1534732387
         * IsDefault : 1
         */

        private String ID;
        private String Mobile;
        private String Name;
        private String Provice;
        private String City;
        private String Region;
        private String Detail;
        private String CreateTime;
        private int IsDefault;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getProvice() {
            return Provice;
        }

        public void setProvice(String Provice) {
            this.Provice = Provice;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getRegion() {
            return Region;
        }

        public void setRegion(String Region) {
            this.Region = Region;
        }

        public String getDetail() {
            return Detail;
        }

        public void setDetail(String Detail) {
            this.Detail = Detail;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }
    }
}
