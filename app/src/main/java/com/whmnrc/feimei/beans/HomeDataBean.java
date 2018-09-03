package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/21.
 */

public class HomeDataBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Banners":[{"Banner_ID":"4e1d6da1-3216-4d3a-8b13-798327aee4bb","Banner_Url":"/Resource/PhotoFile/0dc7f5fd-4b1e-4ec5-b967-f9d68c1af505.jpg","Banner_LinkUrl":"","AppBanner_LikUrl":"","Category":"0","Banner_Sort":3}],"Commodity":[{"ID":"edf70630-b456-47e4-8742-0ce86313b1dc","Name":"测试","Price":0.01,"Img":"http://www.optic-female.cn/Resource/PhotoFile/4aec178a-4686-4fe4-aebd-1b960a18b5cf.jpg","Sales":0,"ClickNumber":42},{"ID":"4c260b48-04f7-4571-8c10-219660f9d88b","Name":"40G/100G CWDM4 TOSA/ROSA","Price":0,"Img":"http://www.optic-female.cn/Resource/PhotoFile/9cb003e4-d431-4a37-8205-3e377f3d475d.jpg","Sales":1,"ClickNumber":11},{"ID":"1099e7af-6aed-4b78-8984-3a9d53150561","Name":"华三（H3C） SFP-GE-SX- 千兆光模块 850nm 550m","Price":0,"Img":"http://www.optic-female.cn/Resource/PhotoFile/d33aa3e3-8099-4a11-b997-816e3d4ca906.jpg","Sales":11,"ClickNumber":21}],"Recruit":[{"ID":"518ce617-4d83-4832-9f2d-1debee404e53","Name":"测试招聘信息不要删","ClickNumber":13,"CreateTime":"1533139200","ValidityTime":"1535644800","Provincial":"北京","City":"北京","Label":"高薪诚聘","WorkYear":"10年","QualificationsName":"海归博士","SalaryName":"50K以上","Describe":"https://www.optic-female.cn/AppPage/Recruit?ID=518ce617-4d83-4832-9f2d-1debee404e53"},{"ID":"49819ec8-ecf4-494b-a64f-e58ca1c0eed6","Name":"测试主管","ClickNumber":233,"CreateTime":"1534089600","ValidityTime":"1534089600","Provincial":"江苏","City":"泰州","Label":"急聘","WorkYear":"3年以上","QualificationsName":"硕士","SalaryName":"5K-10K","Describe":"https://www.optic-female.cn/AppPage/Recruit?ID=49819ec8-ecf4-494b-a64f-e58ca1c0eed6"},{"ID":"2fa3513c-c1d1-41ef-a50e-e9b9dce75ba8","Name":"硬件工程师","ClickNumber":4,"CreateTime":"1534089600","ValidityTime":"1539360000","Provincial":"湖北","City":"武汉、成都","Label":"高薪急聘","WorkYear":"5年以上","QualificationsName":"本科","SalaryName":"10K-20K","Describe":"https://www.optic-female.cn/AppPage/Recruit?ID=2fa3513c-c1d1-41ef-a50e-e9b9dce75ba8"}],"Enterprise":[{"ID":"d6b06ff1-89d6-4896-ac1d-6efd5e1035d0","Name":"武汉菲魅人力资源服务有限公司","Rating":4,"MainExplain":"猎聘\n劳务派遣\n人才培训\n就业咨询","Subtitle":"菲魅人力资源","Label":"人才","Provincial":"湖北省","City":"武汉市","Phone":"18627112546","Sort":1},{"ID":"d9cc5a27-cee9-476a-80a8-af7e1c7bfbc2","Name":"菲魅（武汉）通信技术有限公司","Rating":5,"MainExplain":"光通信供应链平台\n品牌推广服务\n光通信项目承接\n人才服务","Subtitle":"菲魅","Label":"认证","Provincial":"湖北","City":"武汉","Phone":"18140517646","Sort":1},{"ID":"8cdab6b9-c9cf-40e0-9c95-7872759ee451","Name":"深圳市赛克斯康科技有限公司","Rating":4,"MainExplain":"GBIC,SFP, SFP BIDI, DWDM SFP, CWDM SFP, Copper SFP, 10G XFP, CWDM XFP, DWDM XFP, 10G SFP+, CWDM SFP+, X2, DWDM X2, Xenpak, DWDM Xenpak, 40G Transceiver, 100G CFP, GPON，以及光纤收发器及波分系统等","Subtitle":"","Label":"","Provincial":"广东省","City":"深圳市","Phone":"0755-85293396","Sort":3},{"ID":"b979383f-59e4-433c-a34c-30ff38a07c26","Name":"武汉驿天诺科技有限公司","Rating":4,"MainExplain":"光模块、测试设备","Subtitle":"认证","Label":"热门","Provincial":"湖北","City":"武汉","Phone":"18271497619","Sort":9999}],"Read":[{"ID":"7e11101c-1f83-4963-aea4-fd44e0e01bf5","Img":"http://www.optic-female.cn/Resource/PhotoFile/5cc2ae0d-b9d8-4ba4-90c1-9572b906a01d.jpg","Name":"测试作者","Title":"测试标题","Subtitle":"测试副标题","CreateTime":"1534348800"},{"ID":"6b7b0251-113f-4e20-b7d6-5ab19a0c8a1e","Img":"http://www.optic-female.cn/Resource/PhotoFile/a6dbbc9d-b51e-44fb-a0f2-080cdbb7e60e.jpg","Name":"匡国华","Title":"Y4T225 光迅","Subtitle":"公司","CreateTime":"1534730805"},{"ID":"a5dfd633-2972-4f0c-92ac-2f57e28cf042","Img":"http://www.optic-female.cn/Resource/PhotoFile/40cfa686-34ea-43e3-a13f-1f7afe4e9f8e.jpg","Name":"测试专栏1","Title":"测试专栏1","Subtitle":"测试专栏1","CreateTime":"1534407031"}]}
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
        private List<BannersBean> Banners;
        private List<CommodityBean> Commodity;
        private List<RecruitBean> Recruit;
        private List<EnterpriseBean> Enterprise;
        private List<ReadBean> Read;

        public List<BannersBean> getBanners() {
            return Banners;
        }

        public void setBanners(List<BannersBean> Banners) {
            this.Banners = Banners;
        }

        public List<CommodityBean> getCommodity() {
            return Commodity;
        }

        public void setCommodity(List<CommodityBean> Commodity) {
            this.Commodity = Commodity;
        }

        public List<RecruitBean> getRecruit() {
            return Recruit;
        }

        public void setRecruit(List<RecruitBean> Recruit) {
            this.Recruit = Recruit;
        }

        public List<EnterpriseBean> getEnterprise() {
            return Enterprise;
        }

        public void setEnterprise(List<EnterpriseBean> Enterprise) {
            this.Enterprise = Enterprise;
        }

        public List<ReadBean> getRead() {
            return Read;
        }

        public void setRead(List<ReadBean> Read) {
            this.Read = Read;
        }

        public static class BannersBean {
            /**
             * Banner_ID : 4e1d6da1-3216-4d3a-8b13-798327aee4bb
             * Banner_Url : /Resource/PhotoFile/0dc7f5fd-4b1e-4ec5-b967-f9d68c1af505.jpg
             * Banner_LinkUrl :
             * AppBanner_LikUrl :
             * Category : 0
             * Banner_Sort : 3
             */

            private String Banner_Url;
            private String Banner_LinkUrl;


            public String getBanner_Url() {
                return Banner_Url;
            }

            public void setBanner_Url(String Banner_Url) {
                this.Banner_Url = Banner_Url;
            }

            public String getBanner_LinkUrl() {
                return Banner_LinkUrl;
            }

            public void setBanner_LinkUrl(String Banner_LinkUrl) {
                this.Banner_LinkUrl = Banner_LinkUrl;
            }

        }

        public static class CommodityBean {
            /**
             * ID : edf70630-b456-47e4-8742-0ce86313b1dc
             * Name : 测试
             * Price : 0.01
             * Img : http://www.optic-female.cn/Resource/PhotoFile/4aec178a-4686-4fe4-aebd-1b960a18b5cf.jpg
             * Sales : 0
             * ClickNumber : 42
             */

            private String ID;
            private String Name;
            private double Price;
            private String Img;
            private int Sales;
            private int ClickNumber;

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

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public int getSales() {
                return Sales;
            }

            public void setSales(int Sales) {
                this.Sales = Sales;
            }

            public int getClickNumber() {
                return ClickNumber;
            }

            public void setClickNumber(int ClickNumber) {
                this.ClickNumber = ClickNumber;
            }
        }

        public static class RecruitBean {
            /**
             * ID : 518ce617-4d83-4832-9f2d-1debee404e53
             * Name : 测试招聘信息不要删
             * ClickNumber : 13
             * CreateTime : 1533139200
             * ValidityTime : 1535644800
             * Provincial : 北京
             * City : 北京
             * Label : 高薪诚聘
             * WorkYear : 10年
             * QualificationsName : 海归博士
             * SalaryName : 50K以上
             * Describe : https://www.optic-female.cn/AppPage/Recruit?ID=518ce617-4d83-4832-9f2d-1debee404e53
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

        public static class EnterpriseBean {
            /**
             * ID : d6b06ff1-89d6-4896-ac1d-6efd5e1035d0
             * Name : 武汉菲魅人力资源服务有限公司
             * Rating : 4
             * MainExplain : 猎聘
             劳务派遣
             人才培训
             就业咨询
             * Subtitle : 菲魅人力资源
             * Label : 人才
             * Provincial : 湖北省
             * City : 武汉市
             * Phone : 18627112546
             * Sort : 1
             */

            private String ID;
            private String Name;
            private int Rating;
            private String MainExplain;
            private String Subtitle;
            private String Label;
            private String Provincial;
            private String City;
            private String Phone;
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

            public int getRating() {
                return Rating;
            }

            public void setRating(int Rating) {
                this.Rating = Rating;
            }

            public String getMainExplain() {
                return MainExplain;
            }

            public void setMainExplain(String MainExplain) {
                this.MainExplain = MainExplain;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public String getLabel() {
                return Label;
            }

            public void setLabel(String Label) {
                this.Label = Label;
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

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public int getSort() {
                return Sort;
            }

            public void setSort(int Sort) {
                this.Sort = Sort;
            }
        }

        public static class ReadBean {
            /**
             * ID : 7e11101c-1f83-4963-aea4-fd44e0e01bf5
             * Img : http://www.optic-female.cn/Resource/PhotoFile/5cc2ae0d-b9d8-4ba4-90c1-9572b906a01d.jpg
             * Name : 测试作者
             * Title : 测试标题
             * Subtitle : 测试副标题
             * CreateTime : 1534348800
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
