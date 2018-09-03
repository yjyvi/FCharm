package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ProductListBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"Sort","sord":"desc","records":2,"total":1,"conditionJson":""},"Enterprise":[{"ID":"a0956bc7-f8b5-4dc8-99c0-225b946e6c5e","Name":"测试2","Price":15,"Img":"http://www.optic-female.cn/Resource/PhotoFile/4feb70b7-a10d-4f17-8615-f6c599ca44e7.jpg","Sales":0,"ClickNumber":0},{"ID":"edf70630-b456-47e4-8742-0ce86313b1dc","Name":"测试","Price":10,"Img":"http://www.optic-female.cn/Resource/PhotoFile/65606988-73d0-449a-9c12-642b4be0f445.jpg","Sales":0,"ClickNumber":0}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"Sort","sord":"desc","records":2,"total":1,"conditionJson":""}
         * Enterprise : [{"ID":"a0956bc7-f8b5-4dc8-99c0-225b946e6c5e","Name":"测试2","Price":15,"Img":"http://www.optic-female.cn/Resource/PhotoFile/4feb70b7-a10d-4f17-8615-f6c599ca44e7.jpg","Sales":0,"ClickNumber":0},{"ID":"edf70630-b456-47e4-8742-0ce86313b1dc","Name":"测试","Price":10,"Img":"http://www.optic-female.cn/Resource/PhotoFile/65606988-73d0-449a-9c12-642b4be0f445.jpg","Sales":0,"ClickNumber":0}]
         */

        private PaginationBean Pagination;
        private List<EnterpriseBean> Enterprise;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<EnterpriseBean> getEnterprise() {
            return Enterprise;
        }

        public void setEnterprise(List<EnterpriseBean> Enterprise) {
            this.Enterprise = Enterprise;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : Sort
             * sord : desc
             * records : 2
             * total : 1
             * conditionJson :
             */

            private int rows;
            private int page;
            private String sidx;
            private String sord;
            private int records;
            private int total;
            private String conditionJson;

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

            public String getConditionJson() {
                return conditionJson;
            }

            public void setConditionJson(String conditionJson) {
                this.conditionJson = conditionJson;
            }
        }

        public static class EnterpriseBean {
            /**
             * ID : a0956bc7-f8b5-4dc8-99c0-225b946e6c5e
             * Name : 测试2
             * Price : 15
             * Img : http://www.optic-female.cn/Resource/PhotoFile/4feb70b7-a10d-4f17-8615-f6c599ca44e7.jpg
             * Sales : 0
             * ClickNumber : 0
             */

            private String ID;
            private String Name;
            private double Price;
            private String Img;
            private int Sales;
            private int ClickNumber;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public int getSales() {
                return Sales;
            }

            public void setSales(int Sales) {
                this.Sales = Sales;
            }

            public int getClickNumber() {
                return ClickNumber;
            }

            public void setClickNumber(int ClickNumber) {
                this.ClickNumber = ClickNumber;
            }
        }
    }
}
