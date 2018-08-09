package com.whmnrc.feimei.beans;

/**
 * @author yjyvi
 * @data 2018/8/6.
 */

public class VersionBean {

    /**
     * type : 1
     * code : 1
     * message : 成功
     * resultdata : {"Version_ID":"1","Number":1,"AndroidVersion":"0.0.0.1","IosVersion":"0.0.0.1","Explain":"初始版本0.1","URL":"暂无","IsForce":0}
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
         * Version_ID : 1
         * Number : 1
         * AndroidVersion : 0.0.0.1
         * IosVersion : 0.0.0.1
         * Explain : 初始版本0.1
         * URL : 暂无
         * IsForce : 0
         */

        private String Version_ID;
        private int Number;
        private String  AndroidVersion;
        private String IosVersion;
        private String Explain;
        private String URL;
        private int IsForce;

        public String getVersion_ID() {
            return Version_ID;
        }

        public void setVersion_ID(String Version_ID) {
            this.Version_ID = Version_ID;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public String getAndroidVersion() {
            return AndroidVersion;
        }

        public void setAndroidVersion(String AndroidVersion) {
            this.AndroidVersion = AndroidVersion;
        }

        public String getIosVersion() {
            return IosVersion;
        }

        public void setIosVersion(String IosVersion) {
            this.IosVersion = IosVersion;
        }

        public String getExplain() {
            return Explain;
        }

        public void setExplain(String Explain) {
            this.Explain = Explain;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public int getIsForce() {
            return IsForce;
        }

        public void setIsForce(int IsForce) {
            this.IsForce = IsForce;
        }
    }
}
