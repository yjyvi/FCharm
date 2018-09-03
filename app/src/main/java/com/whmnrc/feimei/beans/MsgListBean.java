package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/29.
 */

public class MsgListBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"ID","sord":"desc","records":0,"total":0,"conditionJson":""},"Push":[{"ID":"1","Title":"基本原则","SubTitle":"世博会","CreateTime":"1535522636","Conten":"https://www.optic-female.cn/AppPage/Registration?ID=1","IsRead":0}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"ID","sord":"desc","records":0,"total":0,"conditionJson":""}
         * Push : [{"ID":"1","Title":"基本原则","SubTitle":"世博会","CreateTime":"1535522636","Conten":"https://www.optic-female.cn/AppPage/Registration?ID=1","IsRead":0}]
         */

        private PaginationBean Pagination;
        private List<PushBean> Push;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<PushBean> getPush() {
            return Push;
        }

        public void setPush(List<PushBean> Push) {
            this.Push = Push;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : ID
             * sord : desc
             * records : 0
             * total : 0
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

        public static class PushBean {
            /**
             * ID : 1
             * Title : 基本原则
             * SubTitle : 世博会
             * CreateTime : 1535522636
             * Conten : https://www.optic-female.cn/AppPage/Registration?ID=1
             * IsRead : 0
             */

            private String ID;
            private String Title;
            private String SubTitle;
            private String CreateTime;
            private String Conten;
            private int IsRead;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getSubTitle() {
                return SubTitle;
            }

            public void setSubTitle(String SubTitle) {
                this.SubTitle = SubTitle;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            public int getIsRead() {
                return IsRead;
            }

            public void setIsRead(int IsRead) {
                this.IsRead = IsRead;
            }
        }
    }
}
