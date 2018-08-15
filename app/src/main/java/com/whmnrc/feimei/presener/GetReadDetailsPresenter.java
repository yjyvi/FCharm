package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.BaseBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetReadDetailsPresenter extends PresenterBase {

    private GetReadDetailsListener mGetReadDetailsListener;
    private int page = 0;

    public GetReadDetailsPresenter(GetReadDetailsListener getReadDetailsListener) {
        this.mGetReadDetailsListener = getReadDetailsListener;
    }


    public void getReadDetails(String readId) {
        HashMap<String, Object> params = new HashMap<>(4);

        params.put("Mobile", UserManager.getUser() == null ? "" : UserManager.getUser().getMobile());
        params.put("OtherID", readId);

        OKHttpManager.postString(getUrl(R.string.GetReadDetails), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mGetReadDetailsListener.getReadDetailsSuccess();
                } else {
                    mGetReadDetailsListener.getReadDetailsField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface GetReadDetailsListener {
        void getReadDetailsSuccess();

        void getReadDetailsField();
    }

}
