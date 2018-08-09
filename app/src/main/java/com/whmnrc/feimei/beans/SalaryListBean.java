package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/8.
 */

public class SalaryListBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : [{"ID":"b8fb275b-70d1-4576-ad5c-c6cdf08afb54","Name":"初中及以下","Type":0,"Sort":0},{"ID":"d83bbd34-0be4-40cf-b9dc-45b33456c835","Name":"高中/中专","Type":0,"Sort":1},{"ID":"4aa514bf-60c4-41db-86cf-cb8ad27181b8","Name":"大专","Type":0,"Sort":2},{"ID":"a557e330-c9ae-4e46-bdd7-45194726ebcb","Name":"本科","Type":0,"Sort":3},{"ID":"64092e2f-0866-4d41-a62c-5e9a5fde4f0c","Name":"硕士","Type":0,"Sort":4},{"ID":"ca56cad8-8da0-4e0f-8313-e9c8325686e8","Name":"博士","Type":0,"Sort":5},{"ID":"79304e77-030f-4cea-9e4d-4fe4c2df53b1","Name":"2千以下","Type":1,"Sort":0},{"ID":"8335f496-c223-4e2e-b122-53e1ccc03ca8","Name":"2千-3千","Type":1,"Sort":1},{"ID":"802833d3-cfa4-4b23-8988-e988d644c1e0","Name":"3千-4千","Type":1,"Sort":2},{"ID":"dc9ef070-6d25-4bdd-8318-9f2bbce8f398","Name":"5千-6千","Type":1,"Sort":3},{"ID":"33337d18-42d3-4dd8-a47b-9d12e559c548","Name":"6千-7千","Type":1,"Sort":4},{"ID":"95932f04-1e69-4819-97e5-b27844809586","Name":"7千-8千","Type":1,"Sort":5},{"ID":"fef53730-70f8-4ac3-8fe4-d9422695190e","Name":"8千-9千","Type":1,"Sort":6},{"ID":"114e7c3d-0285-4a01-aab8-c6fc132cde82","Name":"1万以上","Type":1,"Sort":7}]
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
         * ID : b8fb275b-70d1-4576-ad5c-c6cdf08afb54
         * Name : 初中及以下
         * Type : 0
         * Sort : 0
         */

        private String ID;
        private String Name;
        private int Type;
        private int Sort;

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

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }
    }
}
