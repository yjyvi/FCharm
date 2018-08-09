package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/6.
 */

public class GetEnterpriseTypeBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : [{"ID":"1","Img":"http://www.optic-female.cn/Resource/PhotoFile/60246a55-6971-4264-9b44-ceb18402ec67.jpg","Name":"光模块","Sort":1},{"ID":"1a8c7589-b4a0-4fee-9301-869fe0026733","Img":"http://www.optic-female.cn/Resource/PhotoFile/e2bba7ac-e7c9-4273-b881-2bbdb91fea26.jpg","Name":"人力资源","Sort":6},{"ID":"3883537d-f836-453b-a73a-af2e1698d0d9","Img":"http://www.optic-female.cn/Resource/PhotoFile/d5d53df1-8445-436d-8759-db54d2fa268b.jpg","Name":"光纤","Sort":4},{"ID":"424f688c-9297-4f31-94a0-1aaa9fc0145f","Img":"http://www.optic-female.cn/Resource/PhotoFile/65c2f2fc-6d98-491b-b521-454284e9cd62.jpg","Name":"光器件","Sort":2},{"ID":"cfd47ab1-5de4-4a34-8231-3b32a6116eed","Img":"http://www.optic-female.cn/Resource/PhotoFile/199588e4-3080-48eb-99dc-3e4671725a28.jpg","Name":"共享平台","Sort":5},{"ID":"d9dd26a0-4df5-427e-87dc-a3305904163b","Img":"http://www.optic-female.cn/Resource/PhotoFile/1f090f61-cc5a-4988-ad66-e56b0f97a88b.jpg","Name":"仪器仪表","Sort":3}]
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
         * ID : 1
         * Img : http://www.optic-female.cn/Resource/PhotoFile/60246a55-6971-4264-9b44-ceb18402ec67.jpg
         * Name : 光模块
         * Sort : 1
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
