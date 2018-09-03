package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.EnterpriseListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 * /api/Enterprise/GetIndustry 获取行业
 */

public class GetEnterprisePresenter extends PresenterBase {
    int page = 0;
    private GetEnterpriseListener mGetEnterpriseListener;

    public GetEnterprisePresenter(GetEnterpriseListener getEnterpriseListener) {
        this.mGetEnterpriseListener = getEnterpriseListener;

    }

    public void searchEnterpriseList(final boolean isRefresh,String name, String provincial, String city, String enterpriseTypeID, String industryPID, String industryID) {

        HashMap<String, Object> params = new HashMap<>(7);
        HashMap<String, String> conditionJson = new HashMap<>(3);

        if (!TextUtils.isEmpty(name)) {
            conditionJson.put("Name", name);
        }

        if (!TextUtils.isEmpty(provincial)) {
            conditionJson.put("Provincial", provincial.trim());
        }

        if (!TextUtils.isEmpty(city)) {
            conditionJson.put("City", city.trim());
        }

        if (!TextUtils.isEmpty(enterpriseTypeID)) {
            conditionJson.put("EnterpriseTypeID", enterpriseTypeID);
        }

        if (!TextUtils.isEmpty(industryPID)) {
            conditionJson.put("Industry_PID", industryPID);
        }

        if (!TextUtils.isEmpty(industryID)) {
            conditionJson.put("Industry_ID", industryID);
        }

        params.put("rows", "5");
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", String.valueOf(page));
        params.put("sidx", "");
        params.put("sord", "");
        params.put("conditionJson", JSON.toJSONString(conditionJson));
        OKHttpManager.postString(getUrl(R.string.GetEnterprise), params, new CommonCallBack<EnterpriseListBean>() {
            @Override
            protected void onSuccess(EnterpriseListBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mGetEnterpriseListener.getEnterpriseSuccess(data.getResultdata(), isRefresh);
                } else {
                    mGetEnterpriseListener.getEnterpriseField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetEnterpriseListener {
        void getEnterpriseSuccess(EnterpriseListBean.ResultdataBean beans, boolean isRefresh);

        void getEnterpriseField();
    }

}
