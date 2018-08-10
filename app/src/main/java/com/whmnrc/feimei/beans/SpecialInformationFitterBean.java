package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class SpecialInformationFitterBean {

    private int position;
    private String name;
    private List<DataListBean> mDataListBeans;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataListBean> getDataListBeans() {
        return mDataListBeans;
    }

    public void setDataListBeans(List<DataListBean> dataListBeans) {
        mDataListBeans = dataListBeans;
    }


    public static class DataListBean {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
