package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.GetEnterpriseTypeBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * /api/Enterprise/GetEnterpriseType 获取企业类型
 */

public class GetEnterpriseTypePresenter extends PresenterBase {

    private GetEnterpriseTypeListener mGetEnterpriseTypeListener;

    public GetEnterpriseTypePresenter(GetEnterpriseTypeListener getRecommendEnterpriseListener) {
        this.mGetEnterpriseTypeListener = getRecommendEnterpriseListener;

    }

    public void getRecommendEnterpriseList() {
        HashMap<String, Object> params = new HashMap<>(2);
        OKHttpManager.postString(getUrl(R.string.GetEnterpriseType), params, new CommonCallBack<GetEnterpriseTypeBean>() {
            @Override
            protected void onSuccess(GetEnterpriseTypeBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mGetEnterpriseTypeListener.getEnterpriseTypeSuccess(data.getResultdata());
                } else {
                    mGetEnterpriseTypeListener.getEnterpriseTypeField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetEnterpriseTypeListener {
        void getEnterpriseTypeSuccess(List<GetEnterpriseTypeBean.ResultdataBean> beans);

        void getEnterpriseTypeField();
    }

}
