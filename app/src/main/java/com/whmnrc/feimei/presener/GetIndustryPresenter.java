package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.IndustryBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * /api/Enterprise/GetIndustry 获取行业
 */

public class GetIndustryPresenter extends PresenterBase {

    private GetIndustryListener mGetIndustryListener;

    public GetIndustryPresenter(GetIndustryListener getIndustryListener) {
        this.mGetIndustryListener = getIndustryListener;

    }

    public void getIndustryList() {
        HashMap<String, Object> params = new HashMap<>(2);
        OKHttpManager.postString(getUrl(R.string.GetIndustry), params, new CommonCallBack<IndustryBean>() {
            @Override
            protected void onSuccess(IndustryBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mGetIndustryListener.getIndustrySuccess(data.getResultdata());
                } else {
                    mGetIndustryListener.getIndustryField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetIndustryListener {
        void getIndustrySuccess(List<IndustryBean.ResultdataBean> beans);

        void getIndustryField();
    }

}
