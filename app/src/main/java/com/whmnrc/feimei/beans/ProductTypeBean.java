package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ProductTypeBean {


    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"ID":"24a21e04-2121-4ede-a5d6-2260c7cc3ddf","Img":"http://www.optic-female.cn/Resource/PhotoFile/9809cec4-20d2-401c-8404-712d915edf4e.jpg","Name":"产品","Sort":"产品        "}]
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
         * ID : 24a21e04-2121-4ede-a5d6-2260c7cc3ddf
         * Img : http://www.optic-female.cn/Resource/PhotoFile/9809cec4-20d2-401c-8404-712d915edf4e.jpg
         * Name : 产品
         * Sort : 产品
         */

        private String ID;
        private String Img;
        private String Name;
        private int Sort;

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

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }
    }
}
