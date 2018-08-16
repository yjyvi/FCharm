package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/16.
 */

public class ReadListBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"Sort","sord":"asc","records":1,"total":1,"conditionJson":""},"Read":[{"ID":"7e11101c-1f83-4963-aea4-fd44e0e01bf5","Img":"http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg","Name":"测试作者","Title":"测试标题","Subtitle":"测试副标题","CreateTime":"1534406201"}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"Sort","sord":"asc","records":1,"total":1,"conditionJson":""}
         * Read : [{"ID":"7e11101c-1f83-4963-aea4-fd44e0e01bf5","Img":"http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg","Name":"测试作者","Title":"测试标题","Subtitle":"测试副标题","CreateTime":"1534406201"}]
         */

        private PaginationBean Pagination;
        private List<ReadBean> Read;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<ReadBean> getRead() {
            return Read;
        }

        public void setRead(List<ReadBean> Read) {
            this.Read = Read;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : Sort
             * sord : asc
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

        public static class ReadBean {
            /**
             * ID : 7e11101c-1f83-4963-aea4-fd44e0e01bf5
             * Img : http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg
             * Name : 测试作者
             * Title : 测试标题
             * Subtitle : 测试副标题
             * CreateTime : 1534406201
             */

            private String ID;
            private String Img;
            private String Name;
            private String Title;
            private String Subtitle;
            private String CreateTime;

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

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }
        }
    }
}
