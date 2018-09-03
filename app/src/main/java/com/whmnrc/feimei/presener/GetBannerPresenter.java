package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BannerListBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetBannerPresenter extends PresenterBase {

    private GetBannerListener mGetBannerListener;

    public GetBannerPresenter(GetBannerListener getCodeListener) {
        this.mGetBannerListener = getCodeListener;

    }

    /**
     * @param type 1-产品库  2-行业资源  3-企业名录
     */
    public void getBanner(int type) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("Type", type);
        OKHttpManager.postString(getUrl(R.string.GetBanner), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                BannerListBean bannerListBean = JSON.parseObject(st, BannerListBean.class);
                if (bannerListBean.getType() == 1) {
                    mGetBannerListener.getBannerSuccess(bannerListBean.getResultdata());
                } else {
                    ToastUtils.showToast(bannerListBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {

            }

        });
    }


    public interface GetBannerListener {
        void getBannerSuccess(List<BannerListBean.ResultdataBean> bean);


    }

}
