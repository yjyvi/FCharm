package com.whmnrc.feimei.presener;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetRecruitPresenter extends PresenterBase {
    private int page = 0;
    private GetRecruitListener mGetRecruitListener;

    public GetRecruitPresenter(GetRecruitListener getRecruitListener) {
        this.mGetRecruitListener = getRecruitListener;

    }

    public void getRecruit(final boolean isRefresh, String name, String provincial, String city, String enterpriseId, String qualificationsId, String salaryId,String createTime) {

        HashMap<String, Object> params = new HashMap<>(7);
        HashMap<String, String> conditionJson = new HashMap<>(7);

        if (!TextUtils.isEmpty(name)) {
            conditionJson.put("Name", name);
        }

        if (!TextUtils.isEmpty(provincial)) {
            conditionJson.put("Provincial", provincial.trim());
        }

        if (!TextUtils.isEmpty(city)) {
            conditionJson.put("City", city.trim());
        }

        if (!TextUtils.isEmpty(enterpriseId)) {
            conditionJson.put("Enterprise_ID", enterpriseId);
        }

        if (!TextUtils.isEmpty(createTime)) {
            conditionJson.put("CreateTime", createTime);
        }


        if (!TextUtils.isEmpty(qualificationsId)) {
            conditionJson.put("QualificationsID", qualificationsId);
        }

        if (!TextUtils.isEmpty(salaryId)) {
            conditionJson.put("SalaryID", salaryId);
        }


        params.put("rows", "10");
        if (isRefresh) {
            page = 1;
        } else {
            ++page;
        }
        params.put("page", String.valueOf(page));
        params.put("sidx", "");
        params.put("sord", "");
        params.put("conditionJson", JSON.toJSONString(conditionJson));
        OKHttpManager.postString(getUrl(R.string.GetRecruit), params, new CommonCallBack<GetRecruitBean>() {
            @Override
            protected void onSuccess(GetRecruitBean data) {
                if (data.getType() == 1) {
                    mGetRecruitListener.getRecruitSuccess(data.getResultdata(), isRefresh);
                } else {
                    mGetRecruitListener.getRecruitField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetRecruitListener {
        void getRecruitSuccess(GetRecruitBean.ResultdataBean bean, boolean isRefresh);

        void getRecruitField();
    }

}
