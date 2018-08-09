package com.whmnrc.feimei.ui;

import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;


/**
 * Presenter 基类
 *
 * @author Administrator
 */
public class PresenterBase {

    /**
     * 请求成功
     */
    public static final int REQUEST_SUCCESS = 1;


    public PresenterBase() {

    }

    protected String getUrl(int id) {
        return MyApplication.applicationContext.getResources().getString(R.string.service_host_address).concat(MyApplication.applicationContext.getString(id));
    }



}
