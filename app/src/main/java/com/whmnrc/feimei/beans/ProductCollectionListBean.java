package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/22.
 */

public class ProductCollectionListBean {


    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"ID":"e64c900e-e4da-45be-a19f-fea645ea451a","OtherID":"4c260b48-04f7-4571-8c10-219660f9d88b","Img":"http://www.optic-female.cn/Resource/PhotoFile/9cb003e4-d431-4a37-8205-3e377f3d475d.jpg","Name":"40G/100G CWDM4 TOSA/ROSA","Price":0,"Sales":1},{"ID":"17e82439-0e61-4b97-b147-d69917191cd8","OtherID":"09b215d6-fdc8-4645-83a1-b226c7000542","Img":"http://www.optic-female.cn/Resource/PhotoFile/5a44f7e8-3450-4dec-9e13-7409ba76dabc.jpg","Name":"半导体老化系统","Price":0,"Sales":33}]
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
         * ID : e64c900e-e4da-45be-a19f-fea645ea451a
         * OtherID : 4c260b48-04f7-4571-8c10-219660f9d88b
         * Img : http://www.optic-female.cn/Resource/PhotoFile/9cb003e4-d431-4a37-8205-3e377f3d475d.jpg
         * Name : 40G/100G CWDM4 TOSA/ROSA
         * Price : 0
         * Sales : 1
         */

        private String ID;
        private String OtherID;
        private String Img;
        private String Name;
        private String CreateTime;
        private String Title;
        private String Subtitle;
        private double Price;
        private int Type;
        private int IcoType;
        private int Sales;
        private boolean isShowEdit;
        private  boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public boolean isShowEdit() {
            return isShowEdit;
        }

        public void setShowEdit(boolean showEdit) {
            isShowEdit = showEdit;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getSubtitle() {
            return Subtitle;
        }

        public void setSubtitle(String subtitle) {
            Subtitle = subtitle;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public int getIcoType() {
            return IcoType;
        }

        public void setIcoType(int icoType) {
            IcoType = icoType;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getOtherID() {
            return OtherID;
        }

        public void setOtherID(String OtherID) {
            this.OtherID = OtherID;
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

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public int getSales() {
            return Sales;
        }

        public void setSales(int Sales) {
            this.Sales = Sales;
        }
    }
}
