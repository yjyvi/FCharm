package com.whmnrc.feimei.presener;

import com.alibaba.fastjson.JSON;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.VipTypeListBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetVipTypePresenter extends PresenterBase {

    private GetVipTypeListener mGetVipTypeListener;

    public GetVipTypePresenter(GetVipTypeListener getVipTypeListener) {
        this.mGetVipTypeListener = getVipTypeListener;

    }

    public void getVipTypeList(int type) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("Type", type);
        OKHttpManager.postString(getUrl(R.string.GetPayType), params, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                VipTypeListBean vipTypeListBean = JSON.parseObject(st, VipTypeListBean.class);
                if (vipTypeListBean.getType() == 1) {
                    mGetVipTypeListener.getVipTypeSuccess(vipTypeListBean.getResultdata());
                } else {
                    mGetVipTypeListener.getVipTypeField();
                    ToastUtils.showToast(vipTypeListBean.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                mGetVipTypeListener.getVipTypeField();
            }
        });

    }


    public interface GetVipTypeListener {
        void getVipTypeSuccess(List<VipTypeListBean.ResultdataBean> resultdataBeans);

        void getVipTypeField();
    }

}
