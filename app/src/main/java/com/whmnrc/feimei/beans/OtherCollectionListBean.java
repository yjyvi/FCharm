package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/22.
 */

public class OtherCollectionListBean {


    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"ID":"7ae0a405-8e9a-40d9-a3d2-446c0fccd015","OtherID":"3ae88c85-df34-42b4-82a1-860a7b550a4b","Type":2,"CreateTime":1534928089,"Img":"http://www.optic-female.cn/Resource/PhotoFile/4533c831-77fd-4a2a-825f-152891c8f409.jpg","Name":"测试文库","Title":"测试文库视频","Subtitle":"测试文库视频副标题","IcoType":"4"},{"ID":"f75556f6-c2b0-438e-854c-0cd90baf52a0","OtherID":"57cd42f2-2ce1-4832-bc1b-a8d040c1ac5c","Type":2,"CreateTime":1534928075,"Img":"","Name":"光通信器件现状和演进","Title":"光通信器件现状和演进","Subtitle":"陈益新老师","IcoType":"3"},{"ID":"3bd64e50-a826-43b0-86f7-94691e4ec602","OtherID":"b56c0852-5fe7-442c-9476-88da6864c7ce","Type":2,"CreateTime":1534928065,"Img":"","Name":"测试文件","Title":"测试标题","Subtitle":"测试副标题","IcoType":"0"}]
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
         * ID : 7ae0a405-8e9a-40d9-a3d2-446c0fccd015
         * OtherID : 3ae88c85-df34-42b4-82a1-860a7b550a4b
         * Type : 2
         * CreateTime : 1534928089
         * Img : http://www.optic-female.cn/Resource/PhotoFile/4533c831-77fd-4a2a-825f-152891c8f409.jpg
         * Name : 测试文库
         * Title : 测试文库视频
         * Subtitle : 测试文库视频副标题
         * IcoType : 4
         */

        private String ID;
        private String OtherID;
        private int Type;
        private int CreateTime;
        private String Img;
        private String Name;
        private String Title;
        private String Subtitle;
        private String IcoType;

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

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(int CreateTime) {
            this.CreateTime = CreateTime;
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

        public String getIcoType() {
            return IcoType;
        }

        public void setIcoType(String IcoType) {
            this.IcoType = IcoType;
        }
    }
}
