package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/21.
 */

public class BannerListBean {

    /**
     * type : 1
     * code : 0
     * message : 操作成功。
     * resultdata : [{"Banner_Url":"http://www.optic-female.cn/Resource/PhotoFile/0dc7f5fd-4b1e-4ec5-b967-f9d68c1af505.jpg","Banner_LinkUrl":""}]
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
         * Banner_Url : http://www.optic-female.cn/Resource/PhotoFile/0dc7f5fd-4b1e-4ec5-b967-f9d68c1af505.jpg
         * Banner_LinkUrl :
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
}
