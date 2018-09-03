package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yjyvi
 * @data 2018/8/16.
 */

public class ReadDetailsBean {

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Read":{"ID":"7e11101c-1f83-4963-aea4-fd44e0e01bf5","Title":"测试标题","Subtitle":"测试副标题","Name":"测试作者","CreateTime":"1534406201","FreeConten":"https://www.optic-female.cn/AppPage/Read?Type=0&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5","ChargeConten":"https://www.optic-female.cn/AppPage/Read?Type=1&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5","ChargePage":7,"Price":10,"ClickNumber":6,"Img":"http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg"},"CommentCount":0,"IsCollection":0}
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
         * Read : {"ID":"7e11101c-1f83-4963-aea4-fd44e0e01bf5","Title":"测试标题","Subtitle":"测试副标题","Name":"测试作者","CreateTime":"1534406201","FreeConten":"https://www.optic-female.cn/AppPage/Read?Type=0&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5","ChargeConten":"https://www.optic-female.cn/AppPage/Read?Type=1&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5","ChargePage":7,"Price":10,"ClickNumber":6,"Img":"http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg"}
         * CommentCount : 0
         * IsCollection : 0
         */

        private ReadBean Read;
        private int CommentCount;
        private int IsCollection;
        private int IsPay;

        public int getIsPay() {
            return IsPay;
        }

        public void setIsPay(int isPay) {
            IsPay = isPay;
        }

        public ReadBean getRead() {
            return Read;
        }

        public void setRead(ReadBean Read) {
            this.Read = Read;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public int getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(int IsCollection) {
            this.IsCollection = IsCollection;
        }

        public static class ReadBean implements Parcelable{
            public ReadBean() {
            }

            /**
             * ID : 7e11101c-1f83-4963-aea4-fd44e0e01bf5
             * Title : 测试标题
             * Subtitle : 测试副标题
             * Name : 测试作者
             * CreateTime : 1534406201
             * FreeConten : https://www.optic-female.cn/AppPage/Read?Type=0&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5
             * ChargeConten : https://www.optic-female.cn/AppPage/Read?Type=1&ID=7e11101c-1f83-4963-aea4-fd44e0e01bf5
             * ChargePage : 7
             * Price : 10
             * ClickNumber : 6
             * Img : http://www.optic-female.cn/Resource/PhotoFile/20ba5c34-41ae-4c4e-a38c-6e30fde3f8c4.jpg
             */



            private String ID;
            private String Title;
            private String Subtitle;
            private String Name;
            private String CreateTime;
            private String FreeConten;
            private String ChargeConten;
            private int ChargePage;
            private double Price;
            private int ClickNumber;
            private String Img;

            protected ReadBean(Parcel in) {
                ID = in.readString();
                Title = in.readString();
                Subtitle = in.readString();
                Name = in.readString();
                CreateTime = in.readString();
                FreeConten = in.readString();
                ChargeConten = in.readString();
                ChargePage = in.readInt();
                Price = in.readDouble();
                ClickNumber = in.readInt();
                Img = in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeString(Title);
                dest.writeString(Subtitle);
                dest.writeString(Name);
                dest.writeString(CreateTime);
                dest.writeString(FreeConten);
                dest.writeString(ChargeConten);
                dest.writeInt(ChargePage);
                dest.writeDouble(Price);
                dest.writeInt(ClickNumber);
                dest.writeString(Img);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<ReadBean> CREATOR = new Creator<ReadBean>() {
                @Override
                public ReadBean createFromParcel(Parcel in) {
                    return new ReadBean(in);
                }

                @Override
                public ReadBean[] newArray(int size) {
                    return new ReadBean[size];
                }
            };

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
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

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getFreeConten() {
                return FreeConten;
            }

            public void setFreeConten(String FreeConten) {
                this.FreeConten = FreeConten;
            }

            public String getChargeConten() {
                return ChargeConten;
            }

            public void setChargeConten(String ChargeConten) {
                this.ChargeConten = ChargeConten;
            }

            public int getChargePage() {
                return ChargePage;
            }

            public void setChargePage(int ChargePage) {
                this.ChargePage = ChargePage;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
                this.Price = Price;
            }

            public int getClickNumber() {
                return ClickNumber;
            }

            public void setClickNumber(int ClickNumber) {
                this.ClickNumber = ClickNumber;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }
        }
    }
}
