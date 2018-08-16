package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/16.
 */

public class RegulationBookListBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","records":3,"total":1,"conditionJson":""},"Read":[{"ID":"e72396b2-ce72-4bcc-8352-c8d6348da573","Name":"EXO2904 Datasheet-EN","Subtitle":"1.25-29Gbps x 4ch any date rate BERT","CreateTime":"2018/8/12 0:00:00","ClickNumber":2,"Type":2,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=e72396b2-ce72-4bcc-8352-c8d6348da573","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-12/6157e640-1074-4963-ae98-573395f24643.pdf","IsCollection":0},{"ID":"d39d142a-1df8-4e81-b3ac-a948fe9755b0","Name":"EXO2904 手册 _ 中文","Subtitle":"1.25-29Gbps 4通道任意速率误码仪","CreateTime":"2018/8/12 0:00:00","ClickNumber":1,"Type":2,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=d39d142a-1df8-4e81-b3ac-a948fe9755b0","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-12/415c017d-e3e9-435e-9bae-778a845fa4e9.pdf","IsCollection":0},{"ID":"6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","Name":"测试","Subtitle":"测试副标题","CreateTime":"2018/8/8 0:00:00","ClickNumber":0,"Type":1,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-10/020314a0-38fc-46b3-8a59-7699d2e25066.doc","IsCollection":0}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"CreateTime","sord":"desc","records":3,"total":1,"conditionJson":""}
         * Read : [{"ID":"e72396b2-ce72-4bcc-8352-c8d6348da573","Name":"EXO2904 Datasheet-EN","Subtitle":"1.25-29Gbps x 4ch any date rate BERT","CreateTime":"2018/8/12 0:00:00","ClickNumber":2,"Type":2,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=e72396b2-ce72-4bcc-8352-c8d6348da573","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-12/6157e640-1074-4963-ae98-573395f24643.pdf","IsCollection":0},{"ID":"d39d142a-1df8-4e81-b3ac-a948fe9755b0","Name":"EXO2904 手册 _ 中文","Subtitle":"1.25-29Gbps 4通道任意速率误码仪","CreateTime":"2018/8/12 0:00:00","ClickNumber":1,"Type":2,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=d39d142a-1df8-4e81-b3ac-a948fe9755b0","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-12/415c017d-e3e9-435e-9bae-778a845fa4e9.pdf","IsCollection":0},{"ID":"6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","Name":"测试","Subtitle":"测试副标题","CreateTime":"2018/8/8 0:00:00","ClickNumber":0,"Type":1,"Conten":"https://www.optic-female.cn/AppPage/Registration?ID=6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-10/020314a0-38fc-46b3-8a59-7699d2e25066.doc","IsCollection":0}]
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
             * sidx : CreateTime
             * sord : desc
             * records : 3
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
             * ID : e72396b2-ce72-4bcc-8352-c8d6348da573
             * Name : EXO2904 Datasheet-EN
             * Subtitle : 1.25-29Gbps x 4ch any date rate BERT
             * CreateTime : 2018/8/12 0:00:00
             * ClickNumber : 2
             * Type : 2
             * Conten : https://www.optic-female.cn/AppPage/Registration?ID=e72396b2-ce72-4bcc-8352-c8d6348da573
             * FilePath : http://www.optic-female.cn/Upload/file/2018-08-12/6157e640-1074-4963-ae98-573395f24643.pdf
             * IsCollection : 0
             */

            private String ID;
            private String Name;
            private String Subtitle;
            private String CreateTime;
            private int ClickNumber;
            private int Type;
            private String Conten;
            private String FilePath;
            private int IsCollection;

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

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
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
