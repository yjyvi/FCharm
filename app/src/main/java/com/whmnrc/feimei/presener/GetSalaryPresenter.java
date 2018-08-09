package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.SalaryListBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetSalaryPresenter extends PresenterBase {

    private GetSalaryListener mGetSalaryListener;

    public GetSalaryPresenter(GetSalaryListener getSalaryListener) {
        this.mGetSalaryListener = getSalaryListener;

    }

    public void getSalary() {
        HashMap<String, Object> params = new HashMap<>(2);
        OKHttpManager.postString(getUrl(R.string.GetSalary), params, new CommonCallBack<SalaryListBean>() {
            @Override
            protected void onSuccess(SalaryListBean data) {
                if (data.getType() == 1) {
                    mGetSalaryListener.getSalarySuccess(data.getResultdata());
                } else {
                    mGetSalaryListener.getSalaryField();
                ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetSalaryListener {
        void getSalarySuccess(List<SalaryListBean.ResultdataBean> beans);

        void getSalaryField();
    }

}
