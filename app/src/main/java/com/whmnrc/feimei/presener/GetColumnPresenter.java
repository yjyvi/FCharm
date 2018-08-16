package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ColumnBean;
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

public class GetColumnPresenter extends PresenterBase {

    private GetColumnListener mGetColumnListener;

    public GetColumnPresenter(GetColumnListener getColumnListener) {
        this.mGetColumnListener = getColumnListener;
    }


    public void getColumnList() {
        HashMap<String, Object> params = new HashMap<>(2);
        OKHttpManager.postString(getUrl(R.string.GetColumn), params, new CommonCallBack<ColumnBean>() {
            @Override
            protected void onSuccess(ColumnBean data) {
                if (data.getType() == 1) {
                    mGetColumnListener.getColumnSuccess(data.getResultdata());
                } else {
                    mGetColumnListener.getColumnField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetColumnListener {
        void getColumnSuccess(List<ColumnBean.ResultdataBean> resultdataBean);

        void getColumnField();
    }

}
