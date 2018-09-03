package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/20.
 */

public class OrderListBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","conditionJson":"","records":1,"total":1},"Orders":[{"ID":"2ddbd1f1-5244-4d7d-a9fb-dad3dfef7394","Img":"http://www.optic-female.cn/Resource/PhotoFile/5cc2ae0d-b9d8-4ba4-90c1-9572b906a01d.jpg","CreateTime":"1534758219","Name":"测试作者","Money":10,"Number":1,"State":0,"OrderType":4,"Type":0,"SubTitle":"测试副标题","UnitPrice":10}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","conditionJson":"","records":1,"total":1}
         * Orders : [{"ID":"2ddbd1f1-5244-4d7d-a9fb-dad3dfef7394","Img":"http://www.optic-female.cn/Resource/PhotoFile/5cc2ae0d-b9d8-4ba4-90c1-9572b906a01d.jpg","CreateTime":"1534758219","Name":"测试作者","Money":10,"Number":1,"State":0,"OrderType":4,"Type":0,"SubTitle":"测试副标题","UnitPrice":10}]
         */

        private PaginationBean Pagination;
        private List<OrdersBean> Orders;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<OrdersBean> getOrders() {
            return Orders;
        }

        public void setOrders(List<OrdersBean> Orders) {
            this.Orders = Orders;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : CreateTime
             * sord : desc
             * conditionJson :
             * records : 1
             * total : 1
             */

            private int rows;
            private int page;
            private String sidx;
            private String sord;
            private String conditionJson;
            private int records;
            private int total;

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public String getSidx() {
                return sidx;
            }

            public void setSidx(String sidx) {
                this.sidx = sidx;
            }

            public String getSord() {
                return sord;
            }

            public void setSord(String sord) {
                this.sord = sord;
            }

            public String getConditionJson() {
                return conditionJson;
            }

            public void setConditionJson(String conditionJson) {
                this.conditionJson = conditionJson;
            }

            public int getRecords() {
                return records;
            }

            public void setRecords(int records) {
                this.records = records;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class OrdersBean {
            /**
             * ID : 2ddbd1f1-5244-4d7d-a9fb-dad3dfef7394
             * Img : http://www.optic-female.cn/Resource/PhotoFile/5cc2ae0d-b9d8-4ba4-90c1-9572b906a01d.jpg
             * CreateTime : 1534758219
             * Name : 测试作者
             * Money : 10
             * Number : 1
             * State : 0
             * OrderType : 4
             * Type : 0
             * SubTitle : 测试副标题
             * UnitPrice : 10
             */

            private String ID;
            private String Img;
            private String CreateTime;
            private String Name;
            private String OtherID;
            private double Money;
            private int Number;
            private int State;
            private int OrderType;
            private int Type;
            private String SubTitle;
            private double UnitPrice;


            public String getOtherID() {
                return OtherID;
            }

            public void setOtherID(String otherID) {
                OtherID = otherID;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
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

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public int getOrderType() {
                return OrderType;
            }

            public void setOrderType(int OrderType) {
                this.OrderType = OrderType;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getSubTitle() {
                return SubTitle;
            }

            public void setSubTitle(String SubTitle) {
                this.SubTitle = SubTitle;
            }

            public double getUnitPrice() {
                return UnitPrice;
            }

            public void setUnitPrice(double UnitPrice) {
                this.UnitPrice = UnitPrice;
            }
        }
    }
}
