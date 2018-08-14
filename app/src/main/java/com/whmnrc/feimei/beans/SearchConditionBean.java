package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yjyvi
 * @data 2018/8/14.
 */

public class SearchConditionBean implements Parcelable {

    /**
     * 薪资范围
     */
    private String mSalaryID;
    /**
     * 企业ID
     */
    private String mEnterpriseId;
    /**
     * 学历
     */
    private String mQualificationsId;
    /**
     * 省
     */
    private String mProvincial;
    /**
     * 市
     */
    private String mCity;
    /**
     * 发布时间
     */
    private String mCrateTime;

    /**
     * 产品ID
     */
    private String mCommodityClassId;
    /**
     * 升序降序
     */
    private String mDesc;
    /**
     * 排序条件
     */
    private String mSort;

    /**
     * 企业子分类ID
     */
    public String mIndustryId;
    /**
     * 企业父分类ID
     */
    private String mIndustryPid;

    /**
     * 企业分类ID
     */
    private  String enterpriseTypeID;
    /**
     * 搜索名称
     */
    private String content;


    public SearchConditionBean() {
    }

    protected SearchConditionBean(Parcel in) {
        mSalaryID = in.readString();
        mEnterpriseId = in.readString();
        mQualificationsId = in.readString();
        mProvincial = in.readString();
        mCity = in.readString();
        mCrateTime = in.readString();
        mCommodityClassId = in.readString();
        mDesc = in.readString();
        mSort = in.readString();
        mIndustryId = in.readString();
        mIndustryPid = in.readString();
        content = in.readString();
        enterpriseTypeID = in.readString();
    }

    public static final Creator<SearchConditionBean> CREATOR = new Creator<SearchConditionBean>() {
        @Override
        public SearchConditionBean createFromParcel(Parcel in) {
            return new SearchConditionBean(in);
        }

        @Override
        public SearchConditionBean[] newArray(int size) {
            return new SearchConditionBean[size];
        }
    };


    public String getEnterpriseTypeID() {
        return enterpriseTypeID;
    }

    public void setEnterpriseTypeID(String enterpriseTypeID) {
        this.enterpriseTypeID = enterpriseTypeID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSalaryID() {
        return mSalaryID;
    }

    public void setSalaryID(String salaryID) {
        mSalaryID = salaryID;
    }

    public String getEnterpriseId() {
        return mEnterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        mEnterpriseId = enterpriseId;
    }

    public String getQualificationsId() {
        return mQualificationsId;
    }

    public void setQualificationsId(String qualificationsId) {
        mQualificationsId = qualificationsId;
    }

    public String getProvincial() {
        return mProvincial;
    }

    public void setProvincial(String provincial) {
        mProvincial = provincial;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCrateTime() {
        return mCrateTime;
    }

    public void setCrateTime(String crateTime) {
        mCrateTime = crateTime;
    }

    public String getCommodityClassId() {
        return mCommodityClassId;
    }

    public void setCommodityClassId(String commodityClassId) {
        mCommodityClassId = commodityClassId;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getSort() {
        return mSort;
    }

    public void setSort(String sort) {
        mSort = sort;
    }

    public String getIndustryId() {
        return mIndustryId;
    }

    public void setIndustryId(String industryId) {
        mIndustryId = industryId;
    }

    public String getIndustryPid() {
        return mIndustryPid;
    }

    public void setIndustryPid(String industryPid) {
        mIndustryPid = industryPid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSalaryID);
        dest.writeString(mEnterpriseId);
        dest.writeString(mQualificationsId);
        dest.writeString(mProvincial);
        dest.writeString(mCity);
        dest.writeString(mCrateTime);
        dest.writeString(mCommodityClassId);
        dest.writeString(mDesc);
        dest.writeString(mSort);
        dest.writeString(mIndustryId);
        dest.writeString(mIndustryPid);
        dest.writeString(content);
        dest.writeString(enterpriseTypeID);
    }
}
