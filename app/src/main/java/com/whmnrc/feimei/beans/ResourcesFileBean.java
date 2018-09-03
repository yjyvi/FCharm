package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/16.
 */

public class ResourcesFileBean {


    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Pagination":{"rows":10,"page":1,"sidx":"Sort asc,CreateTime","sord":"desc","records":1,"total":1,"conditionJson":""},"Librarys":[{"ID":"b56c0852-5fe7-442c-9476-88da6864c7ce","Title":"测试标题","Subtitle":"测试副标题","Name":"测试文件","CreateTime":"1534334487","FreeConten":"https://www.optic-female.cn/AppPage/Read?Type=0&ID=b56c0852-5fe7-442c-9476-88da6864c7ce","ChargeConten":"https://www.optic-female.cn/AppPage/Read?Type=1&ID=b56c0852-5fe7-442c-9476-88da6864c7ce","ChargePage":7,"Price":10,"ClickNumber":0,"Type":"0","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-15/9b4197f8-da07-4115-ab7c-6904723a0763.xls","DownloadNumber":0,"IsCollection":0,"IsPay":0}]}
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
         * Pagination : {"rows":10,"page":1,"sidx":"Sort asc,CreateTime","sord":"desc","records":1,"total":1,"conditionJson":""}
         * Librarys : [{"ID":"b56c0852-5fe7-442c-9476-88da6864c7ce","Title":"测试标题","Subtitle":"测试副标题","Name":"测试文件","CreateTime":"1534334487","FreeConten":"https://www.optic-female.cn/AppPage/Read?Type=0&ID=b56c0852-5fe7-442c-9476-88da6864c7ce","ChargeConten":"https://www.optic-female.cn/AppPage/Read?Type=1&ID=b56c0852-5fe7-442c-9476-88da6864c7ce","ChargePage":7,"Price":10,"ClickNumber":0,"Type":"0","FilePath":"http://www.optic-female.cn/Upload/file/2018-08-15/9b4197f8-da07-4115-ab7c-6904723a0763.xls","DownloadNumber":0,"IsCollection":0,"IsPay":0}]
         */

        private PaginationBean Pagination;
        private List<LibrarysBean> Librarys;

        public PaginationBean getPagination() {
            return Pagination;
        }

        public void setPagination(PaginationBean Pagination) {
            this.Pagination = Pagination;
        }

        public List<LibrarysBean> getLibrarys() {
            return Librarys;
        }

        public void setLibrarys(List<LibrarysBean> Librarys) {
            this.Librarys = Librarys;
        }

        public static class PaginationBean implements Parcelable {
            public PaginationBean() {
            }

            /**
             * rows : 10
             * page : 1
             * sidx : Sort asc,CreateTime
             * sord : desc
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

            protected PaginationBean(Parcel in) {
                rows = in.readInt();
                page = in.readInt();
                sidx = in.readString();
                sord = in.readString();
                records = in.readInt();
                total = in.readInt();
                conditionJson = in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(rows);
                dest.writeInt(page);
                dest.writeString(sidx);
                dest.writeString(sord);
                dest.writeInt(records);
                dest.writeInt(total);
                dest.writeString(conditionJson);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<PaginationBean> CREATOR = new Creator<PaginationBean>() {
                @Override
                public PaginationBean createFromParcel(Parcel in) {
                    return new PaginationBean(in);
                }

                @Override
                public PaginationBean[] newArray(int size) {
                    return new PaginationBean[size];
                }
            };

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

        public static class LibrarysBean implements Parcelable {

            public LibrarysBean() {
            }

            /**
             * ID : b56c0852-5fe7-442c-9476-88da6864c7ce
             * Title : 测试标题
             * Subtitle : 测试副标题
             * Name : 测试文件
             * CreateTime : 1534334487
             * FreeConten : https://www.optic-female.cn/AppPage/Read?Type=0&ID=b56c0852-5fe7-442c-9476-88da6864c7ce
             * ChargeConten : https://www.optic-female.cn/AppPage/Read?Type=1&ID=b56c0852-5fe7-442c-9476-88da6864c7ce
             * ChargePage : 7
             * Price : 10
             * ClickNumber : 0
             * Type : 0
             * FilePath : http://www.optic-female.cn/Upload/file/2018-08-15/9b4197f8-da07-4115-ab7c-6904723a0763.xls
             * DownloadNumber : 0
             * IsCollection : 0
             * IsPay : 0
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
            private int Type;
            private String FilePath;
            private String Img;
            private int DownloadNumber;
            private int CommentCount;
            private int IsCollection;
            private int IsPay;

            protected LibrarysBean(Parcel in) {
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
                Type = in.readInt();
                FilePath = in.readString();
                Img = in.readString();
                DownloadNumber = in.readInt();
                IsCollection = in.readInt();
                IsPay = in.readInt();
                CommentCount = in.readInt();
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
                dest.writeInt(Type);
                dest.writeString(FilePath);
                dest.writeString(Img);
                dest.writeInt(DownloadNumber);
                dest.writeInt(IsCollection);
                dest.writeInt(IsPay);
                dest.writeInt(CommentCount);
            }


            public String getImg() {
                return Img;
            }

            public void setImg(String img) {
                Img = img;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<LibrarysBean> CREATOR = new Creator<LibrarysBean>() {
                @Override
                public LibrarysBean createFromParcel(Parcel in) {
                    return new LibrarysBean(in);
                }

                @Override
                public LibrarysBean[] newArray(int size) {
                    return new LibrarysBean[size];
                }
            };

            public int getCommentCount() {
                return CommentCount;
            }

            public void setCommentCount(int commentCount) {
                CommentCount = commentCount;
            }

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

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }

            public int getDownloadNumber() {
                return DownloadNumber;
            }

            public void setDownloadNumber(int DownloadNumber) {
                this.DownloadNumber = DownloadNumber;
            }

            public int getIsCollection() {
                return IsCollection;
            }

            public void setIsCollection(int IsCollection) {
                this.IsCollection = IsCollection;
            }

            public int getIsPay() {
                return IsPay;
            }

            public void setIsPay(int IsPay) {
                this.IsPay = IsPay;
            }
        }
    }
}
