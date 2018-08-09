package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class GetCodePresenter extends PresenterBase {

    private GetCodeListener mGetCodeListener;

    public GetCodePresenter(GetCodeListener getCodeListener) {
        this.mGetCodeListener = getCodeListener;

    }

    public void getCode(String code, String tel) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("Code", code);
        params.put("Mobile", tel);
        OKHttpManager.postString(getUrl(R.string.SendPhoneCode), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                if (data.getType() == 1 ) {
                    mGetCodeListener.getCodeSuccess();
                } else {
                    mGetCodeListener.getCodeField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface GetCodeListener {
        void getCodeSuccess();

        void getCodeField();
    }

}
