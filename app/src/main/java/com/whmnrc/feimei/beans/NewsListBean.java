package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/17.
 */

public class NewsListBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"Sort asc,CreateTime","sord":"desc","records":1,"total":1,"conditionJson":""},"News":[{"ID":"d2eb80b7-5a21-40ff-9739-259f4eec8f08","Title":"测试标题","Img":"http://www.optic-female.cn/Resource/PhotoFile/284a93ba-68f7-484a-b473-50574ea9132f.jpg","CreateTime":"2018/8/16 20:23:59","ClickNumber":0,"Type":"0","Conten":"https://www.optic-female.cn/AppPage/News?ID=d2eb80b7-5a21-40ff-9739-259f4eec8f08","IsCollection":0}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"Sort asc,CreateTime","sord":"desc","records":1,"total":1,"conditionJson":""}
         * News : [{"ID":"d2eb80b7-5a21-40ff-9739-259f4eec8f08","Title":"测试标题","Img":"http://www.optic-female.cn/Resource/PhotoFile/284a93ba-68f7-484a-b473-50574ea9132f.jpg","CreateTime":"2018/8/16 20:23:59","ClickNumber":0,"Type":"0","Conten":"https://www.optic-female.cn/AppPage/News?ID=d2eb80b7-5a21-40ff-9739-259f4eec8f08","IsCollection":0}]
         */

        private PaginationBean Pagination;
        private List<NewsBean> News;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<NewsBean> getNews() {
            return News;
        }

        public void setNews(List<NewsBean> News) {
            this.News = News;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : Sort asc,CreateTime
             * sord : desc
             * records : 1
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

        public static class NewsBean {
            /**
             * ID : d2eb80b7-5a21-40ff-9739-259f4eec8f08
             * Title : 测试标题
             * Img : http://www.optic-female.cn/Resource/PhotoFile/284a93ba-68f7-484a-b473-50574ea9132f.jpg
             * CreateTime : 2018/8/16 20:23:59
             * ClickNumber : 0
             * Type : 0
             * Conten : https://www.optic-female.cn/AppPage/News?ID=d2eb80b7-5a21-40ff-9739-259f4eec8f08
             * IsCollection : 0
             */

            private String ID;
            private String Title;
            private String Img;
            private String CreateTime;
            private int ClickNumber;
            private int Type;
            private String Conten;
            private int IsCollection;

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

            public int getClickNumber() {
                return ClickNumber;
            }

            public void setClickNumber(int ClickNumber) {
                this.ClickNumber = ClickNumber;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            public int getIsCollection() {
                return IsCollection;
            }

            public void setIsCollection(int IsCollection) {
                this.IsCollection = IsCollection;
            }
        }
    }
}
