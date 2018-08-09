package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/6.
 */

public class IndustryBean  {


    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"ID":"9a1a9432-3dbc-4000-854b-8eff361e1f8f","Name":"建筑业","Subset":[{"ID":"1680037d-becf-4f59-a4f7-f95549b5ea32","Name":"建筑","PID":"9a1a9432-3dbc-4000-854b-8eff361e1f8f"}]}]
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
         * ID : 9a1a9432-3dbc-4000-854b-8eff361e1f8f
         * Name : 建筑业
         * Subset : [{"ID":"1680037d-becf-4f59-a4f7-f95549b5ea32","Name":"建筑","PID":"9a1a9432-3dbc-4000-854b-8eff361e1f8f"}]
         */

        private String ID;
        private String Name;
        private List<SubsetBean> Subset;

        /**
         * rows : 5
         * page : 1
         * sidx : Sort
         * sord : asc
         * records : 1
         * total : 1
         * conditionJson : {"City":"武汉","Provincial":"湖北省"}
         */

        private int rows;
        private int page;
        private String sidx;
        private String sord;
        private int records;
        private int total;
        private String conditionJson;

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

        public List<SubsetBean> getSubset() {
            return Subset;
        }

        public void setSubset(List<SubsetBean> Subset) {
            this.Subset = Subset;
        }



        public static class SubsetBean {
            /**
             * ID : 1680037d-becf-4f59-a4f7-f95549b5ea32
             * Name : 建筑
             * PID : 9a1a9432-3dbc-4000-854b-8eff361e1f8f
             */

            private String ID;
            private String Name;
            private String PID;

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

            public String getPID() {
                return PID;
            }

            public void setPID(String PID) {
                this.PID = PID;
            }
        }


    }
}
