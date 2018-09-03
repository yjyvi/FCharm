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

public class DelAddressPresenter extends PresenterBase {

    private DelAddressListener mDelAddressListener;

    public DelAddressPresenter(DelAddressListener addressListener) {
        this.mDelAddressListener = addressListener;
    }

    public void delAddress(String addressId) {
        HashMap<String, Object> params = new HashMap<>(3);
        params.put("ID", addressId);
        OKHttpManager.postString(getUrl(R.string.DelAddress), params, new CommonCallBack<BaseBean>() {
            @Override
            protected void onSuccess(BaseBean data) {
                if (data.getType() == 1) {
                    mDelAddressListener.delAdressSuccess();
                } else {
                    mDelAddressListener.delAddressField();
                    ToastUtils.showToast(data.getMessage());
                }
            }
        });
    }


    public interface DelAddressListener {
        void delAdressSuccess();

        void delAddressField();
    }

}
