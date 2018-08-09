package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.ui.PresenterBase;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class HomePageDataPresenter extends PresenterBase {

    private HomePageBannerListener mHomePageDataListener;

    public HomePageDataPresenter(HomePageBannerListener homePageBannerListener ) {
        this.mHomePageDataListener = homePageBannerListener;

    }

    public void getHomePageBanner() {
//        HashMap<String, String> params = new HashMap<>(1);
//        OKHttpManager.get(getUrl(R.string.InitHomePage), params, new CommonCallBack<HomeDataBean>() {
//            @Override
//            protected void onSuccess(HomeDataBean data) {
//                if (data.getType() == 1) {
//                    mHomePageDataListener.loadHomeData(data);
//                } else {
//                    mHomePageDataListener.laodHomeDataField();
//                    ToastUtils.showToast(data.getMessage());
//                }
//            }
//
//            @Override
//            protected void onError(String msg) {
//                super.onError(msg);
//                mHomePageDataListener.laodHomeDataField();
//            }
//        });


    }


    public interface HomePageBannerListener {
        void loadHomeData();
        void laodHomeDataField();
    }

}
