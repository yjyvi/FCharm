package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * /api/Enterprise/GetIndustry 获取行业
 */

public class OrganizationDetailsPresenter extends PresenterBase {
    private GetOrganizationDetailsListener mGetOrganizationDetailsListener;

    public OrganizationDetailsPresenter(GetOrganizationDetailsListener getOrganizationDetailsListener) {
        this.mGetOrganizationDetailsListener = getOrganizationDetailsListener;

    }

    public void getOrganizationDetails(String industryID) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("OtherID", industryID);
        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.GetEnterpriseDetails), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                OrganizationDetailsBean organizationDetailsBean = JSON.parseObject(st, OrganizationDetailsBean.class);
                if (organizationDetailsBean.getType() == 1 && organizationDetailsBean.getResultdata() != null) {
                    mGetOrganizationDetailsListener.getOrganizationDetailsSuccess(organizationDetailsBean.getResultdata());
                } else {
                    mGetOrganizationDetailsListener.getOrganizationDetailsField();
                    ToastUtils.showToast(organizationDetailsBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                mGetOrganizationDetailsListener.getOrganizationDetailsField();
            }
        });
    }


    public interface GetOrganizationDetailsListener {
        void getOrganizationDetailsSuccess(OrganizationDetailsBean.ResultdataBean beans);

        void getOrganizationDetailsField();
    }

}
