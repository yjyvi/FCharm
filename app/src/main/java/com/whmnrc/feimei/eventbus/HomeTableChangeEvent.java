package com.whmnrc.feimei.eventbus;

/**
 * @author yjyvi
 * @data 2018/7/31.
 */

public class HomeTableChangeEvent extends BaseEvent {

    /**
     * 跳转企业名录
     */
    public static final int TO_ORGANIZATION = 10001;
    /**
     * 跳转行业资源
     */
    public static final int TO_INDUSTRY_RESOURCES = 10002;
    /**
     * 跳转行业产品库
     */
    public static final int TO_PRODUCT_LIBRARY = 10003;
    /**
     * 跳转特聘信息
     */
    public static final int TO_SPECIAL_INFORMATION = 10004;

}
