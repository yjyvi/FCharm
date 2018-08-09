package com.whmnrc.feimei.beans;

/**
 * Created by yong hao zeng on 2017/12/20.
 */

public class MediaBean {
    int type;
    String locationPath;
    String netimgPath;
    String netVideoPath;
    int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public String getNetimgPath() {
        return netimgPath;
    }

    public void setNetimgPath(String netimgPath) {
        this.netimgPath = netimgPath;
    }

    public String getNetVideoPath() {
        return netVideoPath;
    }

    public void setNetVideoPath(String netVideoPath) {
        this.netVideoPath = netVideoPath;
    }
}
