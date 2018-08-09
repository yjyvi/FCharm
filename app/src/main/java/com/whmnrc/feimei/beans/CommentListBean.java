package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/8.
 */

public class CommentListBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","records":1,"total":1,"conditionJson":"{'OtherID': 'e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78'}"},"Comment":[{"UserID":"465a62bd-6564-4a14-ad28-8c841dcb80a0","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg","UserName":"测试","Conten":"测试评论","CreateTime":"2018-08-07T00:00:00","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","records":1,"total":1,"conditionJson":"{'OtherID': 'e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78'}"}
         * Comment : [{"UserID":"465a62bd-6564-4a14-ad28-8c841dcb80a0","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg","UserName":"测试","Conten":"测试评论","CreateTime":"2018-08-07T00:00:00","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]}]
         */

        private PaginationBean Pagination;
        private List<CommentBean> Comment;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<CommentBean> getComment() {
            return Comment;
        }

        public void setComment(List<CommentBean> Comment) {
            this.Comment = Comment;
        }

        public static class PaginationBean {
            /**
             * rows : 10
             * page : 1
             * sidx : CreateTime
             * sord : desc
             * records : 1
             * total : 1
             * conditionJson : {'OtherID': 'e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78'}
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

        public static class CommentBean  {
            /**
             * UserID : 465a62bd-6564-4a14-ad28-8c841dcb80a0
             * UserHeadImg : http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg
             * UserName : 测试
             * Conten : 测试评论
             * CreateTime : 2018-08-07T00:00:00
             * CommentAdd : ["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]
             */

            private String UserID;
            private String UserHeadImg;
            private String UserName;
            private String Conten;
            private String CreateTime;
            private List<String> CommentAdd;

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getUserHeadImg() {
                return UserHeadImg;
            }

            public void setUserHeadImg(String UserHeadImg) {
                this.UserHeadImg = UserHeadImg;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public List<String> getCommentAdd() {
                return CommentAdd;
            }

            public void setCommentAdd(List<String> CommentAdd) {
                this.CommentAdd = CommentAdd;
            }
        }
    }
}
