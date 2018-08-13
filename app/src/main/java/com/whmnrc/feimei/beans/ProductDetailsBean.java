package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/11.
 */

public class ProductDetailsBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Commodity":{"ID":"edf70630-b456-47e4-8742-0ce86313b1dc","Name":"测试","Sales":0,"ClickNumber":0,"Price":10,"Img":"http://www.optic-female.cn/Resource/PhotoFile/65606988-73d0-449a-9c12-642b4be0f445.jpg","Salesman":"测试","Mail":"113454646@1231.cs","Phone":"154821321","Label":"hot","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RegulationBookID":"6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","Conten":"http://www.optic-female.cn/AppPage/Registration?ID=edf70630-b456-47e4-8742-0ce86313b1dc","EnterpriseName":"测试企业","ImgAdd":["http://www.optic-female.cn/Resource/PhotoFile/9774eacd-1499-4ad1-8d7f-4092c796c70d.jpg","http://www.optic-female.cn/Resource/PhotoFile/e2695686-ffcb-47e3-92a0-7b12af58c268.jpg"]},"Comment":[{"UserID":"8ac1722a-47c9-4f2c-a34e-3b155756cdd2","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/df0b53dd-6499-496f-a055-18c145278ac9.jpg","UserName":"考会计交","Conten":"stringstringstringstring","CreateTime":"1533968515","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/9999fe62-32c9-4288-bd02-e1af1249465c.jpg","http://www.optic-female.cn/Resource/Qualification/42c16fa7-ecb1-4054-a2ee-e654c1802bd7.jpg"]},{"UserID":"8ac1722a-47c9-4f2c-a34e-3b155756cdd2","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/df0b53dd-6499-496f-a055-18c145278ac9.jpg","UserName":"考会计交","Conten":"stringstringstringstring","CreateTime":"1533968432","CommentAdd":[]}],"CommentCount":2}
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
         * Commodity : {"ID":"edf70630-b456-47e4-8742-0ce86313b1dc","Name":"测试","Sales":0,"ClickNumber":0,"Price":10,"Img":"http://www.optic-female.cn/Resource/PhotoFile/65606988-73d0-449a-9c12-642b4be0f445.jpg","Salesman":"测试","Mail":"113454646@1231.cs","Phone":"154821321","Label":"hot","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RegulationBookID":"6ea9695d-752f-4daa-8e7c-7e2f7662c9e4","Conten":"http://www.optic-female.cn/AppPage/Registration?ID=edf70630-b456-47e4-8742-0ce86313b1dc","EnterpriseName":"测试企业","ImgAdd":["http://www.optic-female.cn/Resource/PhotoFile/9774eacd-1499-4ad1-8d7f-4092c796c70d.jpg","http://www.optic-female.cn/Resource/PhotoFile/e2695686-ffcb-47e3-92a0-7b12af58c268.jpg"]}
         * Comment : [{"UserID":"8ac1722a-47c9-4f2c-a34e-3b155756cdd2","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/df0b53dd-6499-496f-a055-18c145278ac9.jpg","UserName":"考会计交","Conten":"stringstringstringstring","CreateTime":"1533968515","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/9999fe62-32c9-4288-bd02-e1af1249465c.jpg","http://www.optic-female.cn/Resource/Qualification/42c16fa7-ecb1-4054-a2ee-e654c1802bd7.jpg"]},{"UserID":"8ac1722a-47c9-4f2c-a34e-3b155756cdd2","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/df0b53dd-6499-496f-a055-18c145278ac9.jpg","UserName":"考会计交","Conten":"stringstringstringstring","CreateTime":"1533968432","CommentAdd":[]}]
         * CommentCount : 2
         */

        private CommodityBean Commodity;
        private int CommentCount;
        private List<CommentBean> Comment;

        public CommodityBean getCommodity() {
            return Commodity;
        }

        public void setCommodity(CommodityBean Commodity) {
            this.Commodity = Commodity;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public List<CommentBean> getComment() {
            return Comment;
        }

        public void setComment(List<CommentBean> Comment) {
            this.Comment = Comment;
        }

        public static class CommodityBean implements Parcelable {

            public CommodityBean() {
            }

            /**
             * ID : edf70630-b456-47e4-8742-0ce86313b1dc
             * Name : 测试
             * Sales : 0
             * ClickNumber : 0
             * Price : 10
             * Img : http://www.optic-female.cn/Resource/PhotoFile/65606988-73d0-449a-9c12-642b4be0f445.jpg
             * Salesman : 测试
             * Mail : 113454646@1231.cs
             * Phone : 154821321
             * Label : hot
             * Enterprise_ID : e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             * RegulationBookID : 6ea9695d-752f-4daa-8e7c-7e2f7662c9e4
             * Conten : http://www.optic-female.cn/AppPage/Registration?ID=edf70630-b456-47e4-8742-0ce86313b1dc
             * EnterpriseName : 测试企业
             * ImgAdd : ["http://www.optic-female.cn/Resource/PhotoFile/9774eacd-1499-4ad1-8d7f-4092c796c70d.jpg","http://www.optic-female.cn/Resource/PhotoFile/e2695686-ffcb-47e3-92a0-7b12af58c268.jpg"]
             */

            private String ID;
            private String Name;
            private int Sales;
            private int ClickNumber;
            private int Price;
            private String Img;
            private String Salesman;
            private String Mail;
            private String Phone;
            private String Label;
            private String Enterprise_ID;
            private String RegulationBookID;
            private String Conten;
            private String EnterpriseName;
            private List<String> ImgAdd;

            protected CommodityBean(Parcel in) {
                ID = in.readString();
                Name = in.readString();
                Sales = in.readInt();
                ClickNumber = in.readInt();
                Price = in.readInt();
                Img = in.readString();
                Salesman = in.readString();
                Mail = in.readString();
                Phone = in.readString();
                Label = in.readString();
                Enterprise_ID = in.readString();
                RegulationBookID = in.readString();
                Conten = in.readString();
                EnterpriseName = in.readString();
                ImgAdd = in.createStringArrayList();
            }

            public static final Creator<CommodityBean> CREATOR = new Creator<CommodityBean>() {
                @Override
                public CommodityBean createFromParcel(Parcel in) {
                    return new CommodityBean(in);
                }

                @Override
                public CommodityBean[] newArray(int size) {
                    return new CommodityBean[size];
                }
            };

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

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public String getSalesman() {
                return Salesman;
            }

            public void setSalesman(String Salesman) {
                this.Salesman = Salesman;
            }

            public String getMail() {
                return Mail;
            }

            public void setMail(String Mail) {
                this.Mail = Mail;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getLabel() {
                return Label;
            }

            public void setLabel(String Label) {
                this.Label = Label;
            }

            public String getEnterprise_ID() {
                return Enterprise_ID;
            }

            public void setEnterprise_ID(String Enterprise_ID) {
                this.Enterprise_ID = Enterprise_ID;
            }

            public String getRegulationBookID() {
                return RegulationBookID;
            }

            public void setRegulationBookID(String RegulationBookID) {
                this.RegulationBookID = RegulationBookID;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            public String getEnterpriseName() {
                return EnterpriseName;
            }

            public void setEnterpriseName(String EnterpriseName) {
                this.EnterpriseName = EnterpriseName;
            }

            public List<String> getImgAdd() {
                return ImgAdd;
            }

            public void setImgAdd(List<String> ImgAdd) {
                this.ImgAdd = ImgAdd;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeString(Name);
                dest.writeInt(Sales);
                dest.writeInt(ClickNumber);
                dest.writeInt(Price);
                dest.writeString(Img);
                dest.writeString(Salesman);
                dest.writeString(Mail);
                dest.writeString(Phone);
                dest.writeString(Label);
                dest.writeString(Enterprise_ID);
                dest.writeString(RegulationBookID);
                dest.writeString(Conten);
                dest.writeString(EnterpriseName);
                dest.writeStringList(ImgAdd);
            }
        }

        public static class CommentBean {
            /**
             * UserID : 8ac1722a-47c9-4f2c-a34e-3b155756cdd2
             * UserHeadImg : http://www.optic-female.cn/Resource/Qualification/df0b53dd-6499-496f-a055-18c145278ac9.jpg
             * UserName : 考会计交
             * Conten : stringstringstringstring
             * CreateTime : 1533968515
             * CommentAdd : ["http://www.optic-female.cn/Resource/Qualification/9999fe62-32c9-4288-bd02-e1af1249465c.jpg","http://www.optic-female.cn/Resource/Qualification/42c16fa7-ecb1-4054-a2ee-e654c1802bd7.jpg"]
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
