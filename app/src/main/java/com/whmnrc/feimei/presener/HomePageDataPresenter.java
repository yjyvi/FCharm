package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.HomeDataBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class HomePageDataPresenter extends PresenterBase {

    private HomePageDataListener mHomePageDataListener;

    public HomePageDataPresenter(HomePageDataListener homePageDataListener ) {
        this.mHomePageDataListener = homePageDataListener;

    }

    public void getHomeData() {
        HashMap<String, Object> params = new HashMap<>(3);
        OKHttpManager.postString(getUrl(R.string.GetFirstPage), params, new CommonCallBack<HomeDataBean>() {
            @Override
            protected void onSuccess(HomeDataBean data) {
                if (data.getType() == 1) {
                    mHomePageDataListener.loadHomeDataSuccess(data.getResultdata());
                } else {
                    mHomePageDataListener.loadHomeDataField();
                    ToastUtils.showToast(data.getMessage());
                }
            }

            @Override
            protected void onError(String msg) {
                super.onError(msg);
                mHomePageDataListener.loadHomeDataField();
            }
        });


    }


    public interface HomePageDataListener {
        void loadHomeDataSuccess(HomeDataBean.ResultdataBean homeDataBean);
        void loadHomeDataField();
    }

}
