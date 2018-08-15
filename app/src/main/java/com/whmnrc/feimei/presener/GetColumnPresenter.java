package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

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
        OKHttpManager.postString(getUrl(R.string.GetColumn), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mGetColumnListener.getColumnSuccess();
                } else {
                    mGetColumnListener.getColumnField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetColumnListener {
        void getColumnSuccess();

        void getColumnField();
    }

}
