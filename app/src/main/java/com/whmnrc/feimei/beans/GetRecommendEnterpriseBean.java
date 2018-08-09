package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/6.
 */

public class GetRecommendEnterpriseBean {

    /**
     * code : 200
     * message : 成功
     * resultdata : [{"City":"武汉市","ID":"d6b06ff1-89d6-4896-ac1d-6efd5e1035d0","Label":"人才","MainExplain":"猎聘 劳务派遣 人才培训 就业咨询","Name":"武汉菲魅人力资源服务有限公司","Phone":"18627112546","Provincial":"湖北省","Rating":4,"Sort":1,"Subtitle":"菲魅人力资源"},{"City":"武汉市","ID":"d9cc5a27-cee9-476a-80a8-af7e1c7bfbc2","Label":"认证","MainExplain":"光通信供应链平台 品牌推广服务 光通信项目承接 人才服务","Name":"菲魅（武汉）通信技术有限公司","Phone":"18140517646","Provincial":"武汉市","Rating":5,"Sort":1,"Subtitle":"菲魅"},{"City":"黄石","ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","Label":"测试","MainExplain":"这里是主营说明,列表的","Name":"测试企业","Phone":"18671232222","Provincial":"湖北","Rating":4,"Sort":1,"Subtitle":"测试副标题 列表"},{"City":"深圳市","ID":"8cdab6b9-c9cf-40e0-9c95-7872759ee451","Label":"","MainExplain":"GBIC,SFP, SFP BIDI, DWDM SFP, CWDM SFP, Copper SFP, 10G XFP, CWDM XFP, DWDM XFP, 10G SFP+, CWDM SFP+, X2, DWDM X2, Xenpak, DWDM Xenpak, 40G Transceiver, 100G CFP, GPON，以及光纤收发器及波分系统等","Name":"深圳市赛克斯康科技有限公司","Phone":"0755-85293396","Provincial":"广东省","Rating":4,"Sort":3,"Subtitle":""}]
     * type : 1
     */

    private int code;
    private String message;
    private int type;
    private List<ResultdataBean> resultdata;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ResultdataBean> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<ResultdataBean> resultdata) {
        this.resultdata = resultdata;
    }

    public static class ResultdataBean {
        /**
         * City : 武汉市
         * ID : d6b06ff1-89d6-4896-ac1d-6efd5e1035d0
         * Label : 人才
         * MainExplain : 猎聘 劳务派遣 人才培训 就业咨询
         * Name : 武汉菲魅人力资源服务有限公司
         * Phone : 18627112546
         * Provincial : 湖北省
         * Rating : 4
         * Sort : 1
         * Subtitle : 菲魅人力资源
         */

        private String City;
        private String ID;
        private String Label;
        private String MainExplain;
        private String Name;
        private String Phone;
        private String Provincial;
        private int Rating;
        private int Sort;
        private String Subtitle;

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLabel() {
            return Label;
        }

        public void setLabel(String Label) {
            this.Label = Label;
        }

        public String getMainExplain() {
            return MainExplain;
        }

        public void setMainExplain(String MainExplain) {
            this.MainExplain = MainExplain;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getProvincial() {
            return Provincial;
        }

        public void setProvincial(String Provincial) {
            this.Provincial = Provincial;
        }

        public int getRating() {
            return Rating;
        }

        public void setRating(int Rating) {
            this.Rating = Rating;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }

        public String getSubtitle() {
            return Subtitle;
        }

        public void setSubtitle(String Subtitle) {
            this.Subtitle = Subtitle;
        }
    }
}
