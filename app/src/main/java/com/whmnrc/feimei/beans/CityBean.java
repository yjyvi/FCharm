package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * Created by yong hao zeng on 2018/3/10.
 */

public class CityBean {

    /**
     * resultdata : ["北京","天津","河北省","山西省","内蒙古自治区","辽宁省","吉林省","黑龙江省","上海","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","广西壮族自治区","海南省","重庆","四川省","贵州省","云南省","西藏自治区","陕西省","甘肃省","青海省","宁夏回族自治区","新疆维吾尔自治区","香港特别行政区","澳门特别行政区","台湾省"]
     * type : 1
     * code : 0
     * message : 操作成功。
     */

    private int type;
    private int code;
    private String message;
    private List<String> resultdata;

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

    public List<String> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<String> resultdata) {
        this.resultdata = resultdata;
    }
}
