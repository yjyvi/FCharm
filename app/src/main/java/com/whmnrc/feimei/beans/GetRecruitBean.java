package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/8.
 */

public class GetRecruitBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"Sort","sord":"asc","records":1,"total":1,"conditionJson":""},"Recruit":[{"ID":"59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04","Name":"测试招聘","ClickNumber":0,"CreateTime":"1533657600","ValidityTime":"1533744000","Provincial":"湖北","City":"武汉","Label":"急聘","WorkYear":"2-3年","QualificationsName":"大专","SalaryName":"5千-6千","Describe":"http://www.optic-female.cn/AppPage/Registration?ID=59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04"}]}
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
         * Recruit : [{"ID":"59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04","Name":"测试招聘","ClickNumber":0,"CreateTime":"1533657600","ValidityTime":"1533744000","Provincial":"湖北","City":"武汉","Label":"急聘","WorkYear":"2-3年","QualificationsName":"大专","SalaryName":"5千-6千","Describe":"http://www.optic-female.cn/AppPage/Registration?ID=59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04"}]
         */

        private PaginationBean Pagination;
        private List<RecruitBean> Recruit;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<RecruitBean> getRecruit() {
            return Recruit;
        }

        public void setRecruit(List<RecruitBean> Recruit) {
            this.Recruit = Recruit;
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

        public static class RecruitBean {
            /**
             * ID : 59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04
             * Name : 测试招聘
             * ClickNumber : 0
             * CreateTime : 1533657600
             * ValidityTime : 1533744000
             * Provincial : 湖北
             * City : 武汉
             * Label : 急聘
             * WorkYear : 2-3年
             * QualificationsName : 大专
             * SalaryName : 5千-6千
             * Describe : http://www.optic-female.cn/AppPage/Registration?ID=59c7d9d8-27af-4881-9fbd-6b0e6eaa3a04
             */

            private String ID;
            private String Name;
            private int ClickNumber;
            private String CreateTime;
            private String ValidityTime;
            private String Provincial;
            private String City;
            private String Label;
            private String WorkYear;
            private String QualificationsName;
            private String SalaryName;
            private String Describe;

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

            public int getClickNumber() {
                return ClickNumber;
            }

            public void setClickNumber(int ClickNumber) {
                this.ClickNumber = ClickNumber;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getValidityTime() {
                return ValidityTime;
            }

            public void setValidityTime(String ValidityTime) {
                this.ValidityTime = ValidityTime;
            }

            public String getProvincial() {
                return Provincial;
            }

            public void setProvincial(String Provincial) {
                this.Provincial = Provincial;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getLabel() {
                return Label;
            }

            public void setLabel(String Label) {
                this.Label = Label;
            }

            public String getWorkYear() {
                return WorkYear;
            }

            public void setWorkYear(String WorkYear) {
                this.WorkYear = WorkYear;
            }

            public String getQualificationsName() {
                return QualificationsName;
            }

            public void setQualificationsName(String QualificationsName) {
                this.QualificationsName = QualificationsName;
            }

            public String getSalaryName() {
                return SalaryName;
            }

            public void setSalaryName(String SalaryName) {
                this.SalaryName = SalaryName;
            }

            public String getDescribe() {
                return Describe;
            }

            public void setDescribe(String Describe) {
                this.Describe = Describe;
            }
        }
    }
}
