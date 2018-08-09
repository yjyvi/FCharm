package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
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

public class LoginOutPresenter extends PresenterBase {

    private LoginListener mLoginListener;

    public LoginOutPresenter(LoginListener emailLoginListener) {
        this.mLoginListener = emailLoginListener;

    }

    public void onLoginOut(){
        HashMap<String, Object> params = new HashMap<>(3);
        if (UserManager.getUser() == null) {
            return;
        }
        params.put("Mobile", UserManager.getUser().getMobile());
        OKHttpManager.postString(getUrl(R.string.Cancellation), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                if (data.getType() == 1 ) {
                    mLoginListener.loginOutSuccess();
                }else {
                    mLoginListener.loginOutField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }


    public interface LoginListener {
        void loginOutSuccess();
        void loginOutField();
    }

}
