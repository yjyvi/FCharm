package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/16.
 */

public class ColumnBean implements Parcelable{

    public ColumnBean() {
    }

    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"ID":"9829b704-e6dd-40d0-b7e1-0c867ef00ff3","Img":"http://www.optic-female.cn/Resource/PhotoFile/af404a8a-882b-40cd-8bfd-1d0e5c89593a.jpg","Name":"测试专栏","Introduce":"测试的专栏","Sort":0}]
     */

    private int type;
    private int code;
    private String message;
    private List<ResultdataBean> resultdata;

    protected ColumnBean(Parcel in) {
        type = in.readInt();
        code = in.readInt();
        message = in.readString();
        resultdata = in.createTypedArrayList(ResultdataBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(code);
        dest.writeString(message);
        dest.writeTypedList(resultdata);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ColumnBean> CREATOR = new Creator<ColumnBean>() {
        @Override
        public ColumnBean createFromParcel(Parcel in) {
            return new ColumnBean(in);
        }

        @Override
        public ColumnBean[] newArray(int size) {
            return new ColumnBean[size];
        }
    };

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

    public static class ResultdataBean implements Parcelable{
        /**
         * ID : 9829b704-e6dd-40d0-b7e1-0c867ef00ff3
         * Img : http://www.optic-female.cn/Resource/PhotoFile/af404a8a-882b-40cd-8bfd-1d0e5c89593a.jpg
         * Name : 测试专栏
         * Introduce : 测试的专栏
         * Sort : 0
         */

        private String ID;
        private String Img;
        private String Name;
        private String Introduce;
        private int Sort;

        public ResultdataBean() {
        }

        protected ResultdataBean(Parcel in) {
            ID = in.readString();
            Img = in.readString();
            Name = in.readString();
            Introduce = in.readString();
            Sort = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ID);
            dest.writeString(Img);
            dest.writeString(Name);
            dest.writeString(Introduce);
            dest.writeInt(Sort);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ResultdataBean> CREATOR = new Creator<ResultdataBean>() {
            @Override
            public ResultdataBean createFromParcel(Parcel in) {
                return new ResultdataBean(in);
            }

            @Override
            public ResultdataBean[] newArray(int size) {
                return new ResultdataBean[size];
            }
        };

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

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        public int getSort() {
            return Sort;
        }

        public void setSort(int Sort) {
            this.Sort = Sort;
        }
    }
}
