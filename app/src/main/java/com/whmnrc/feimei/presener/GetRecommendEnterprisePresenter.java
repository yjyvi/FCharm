package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.GetRecommendEnterpriseBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * /api/Enterprise/GetRecommendEnterprise 获取首页或者推荐企业
 */

public class GetRecommendEnterprisePresenter extends PresenterBase {

    private GetRecommendEnterpriseListener mGetRecommendEnterpriseListener;

    public GetRecommendEnterprisePresenter(GetRecommendEnterpriseListener getRecommendEnterpriseListener) {
        this.mGetRecommendEnterpriseListener = getRecommendEnterpriseListener;

    }

    public void getRecommendEnterpriseList(int isTop) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("IsTop", String.valueOf(isTop));
        OKHttpManager.postString(getUrl(R.string.GetRecommendEnterprise), params, new CommonCallBack<GetRecommendEnterpriseBean>() {
            @Override
            protected void onSuccess(GetRecommendEnterpriseBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mGetRecommendEnterpriseListener.getRecommendEnterpriseSuccess(data.getResultdata());
                } else {
                    mGetRecommendEnterpriseListener.getRecommendEnterpriseField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetRecommendEnterpriseListener {
        void getRecommendEnterpriseSuccess(List<GetRecommendEnterpriseBean.ResultdataBean> beans);

        void getRecommendEnterpriseField();
    }

}
