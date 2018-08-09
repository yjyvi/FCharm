package com.whmnrc.feimei.presener;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.MyApplication;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.UserBean;
import com.whmnrc.feimei.network.CommonCallBack;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.ui.PresenterBase;
import com.whmnrc.feimei.utils.EncryptUtils;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.ToastUtils;

import java.util.HashMap;

/**
 * @author yjyvi
 * @data 2018/5/29.
 */

public class LoginPresenter extends PresenterBase {

    private LoginListener mLoginListener;

    public LoginPresenter(LoginListener emailLoginListener) {
        this.mLoginListener = emailLoginListener;

    }

    public void login(String pwd, String phoneCode, String tel) {
        HashMap<String, Object> params = new HashMap<>(7);
        params.put("Pwd", EncryptUtils.encryptMD5ToString(pwd));
        params.put("PhoneCode", phoneCode);
        params.put("Auth_EquipmentCode", SPUtils.getStringToken(MyApplication.applicationContext, CommonConstant.Common.DEVICE_TOKEN));
        params.put("Auth_Type", "1");
        params.put("Mobile", tel);
        OKHttpManager.postString(getUrl(R.string.Login), params, new CommonCallBack<UserBean>() {
            @Override
            protected void onSuccess(UserBean data) {
                if (data.getType() == 1 && data.getResultdata() != null) {
                    mLoginListener.loginSuccess(data.getResultdata());
                } else {
                    mLoginListener.loginField();
                }
                ToastUtils.showToast(data.getMessage());
            }
        });
    }

    public interface LoginListener {
        void loginSuccess(UserBean.ResultdataBean userBean);

        void loginField();
    }

}
