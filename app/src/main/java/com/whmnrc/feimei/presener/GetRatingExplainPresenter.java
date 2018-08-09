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

public class GetRatingExplainPresenter extends PresenterBase {

    private GetRatingExplainListener mGetRatingExplainListener;

    public GetRatingExplainPresenter(GetRatingExplainListener getRatingExplainListener) {
        this.mGetRatingExplainListener = getRatingExplainListener;

    }

    public void getRatingExplain() {
        HashMap<String, Object> params = new HashMap<>(4);
        OKHttpManager.postString(getUrl(R.string.GetRatingExplain), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mGetRatingExplainListener.getRatingExplainSuccess((String) data.getResultdata());
                } else {
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetRatingExplainListener {
        void getRatingExplainSuccess(String content);
    }

}
